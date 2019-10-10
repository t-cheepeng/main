package seedu.exercise.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.exercise.commons.core.Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX;
import static seedu.exercise.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.exercise.logic.commands.CommandTestUtil.CALORIES_DESC_AEROBICS;
import static seedu.exercise.logic.commands.CommandTestUtil.DATE_DESC_AEROBICS;
import static seedu.exercise.logic.commands.CommandTestUtil.NAME_DESC_AEROBICS;
import static seedu.exercise.logic.commands.CommandTestUtil.QUANTITY_DESC_AEROBICS;
import static seedu.exercise.logic.commands.CommandTestUtil.UNIT_DESC_AEROBICS;
import static seedu.exercise.testutil.Assert.assertThrows;
import static seedu.exercise.testutil.TypicalExercises.AEROBICS;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.exercise.logic.commands.AddCommand;
import seedu.exercise.logic.commands.CommandResult;
import seedu.exercise.logic.commands.ListCommand;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.logic.parser.exceptions.ParseException;
import seedu.exercise.model.Model;
import seedu.exercise.model.ModelManager;
import seedu.exercise.model.ReadOnlyExerciseBook;
import seedu.exercise.model.UserPrefs;
import seedu.exercise.model.exercise.Exercise;
import seedu.exercise.storage.JsonExerciseBookStorage;
import seedu.exercise.storage.JsonUserPrefsStorage;
import seedu.exercise.storage.StorageManager;
import seedu.exercise.testutil.ExerciseBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonExerciseBookStorage exerciseBookStorage =
            new JsonExerciseBookStorage(temporaryFolder.resolve("exerciseBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(exerciseBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonExerciseBookIoExceptionThrowingStub
        JsonExerciseBookStorage exerciseBookStorage =
            new JsonExerciseBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionExerciseBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(exerciseBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AEROBICS + DATE_DESC_AEROBICS + CALORIES_DESC_AEROBICS
            + QUANTITY_DESC_AEROBICS + UNIT_DESC_AEROBICS;
        Exercise expectedExercise = new ExerciseBuilder(AEROBICS).withMuscles().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addExercise(expectedExercise);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredExerciseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExerciseList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAllData(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonExerciseBookIoExceptionThrowingStub extends JsonExerciseBookStorage {
        private JsonExerciseBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveExerciseBook(ReadOnlyExerciseBook exerciseBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}