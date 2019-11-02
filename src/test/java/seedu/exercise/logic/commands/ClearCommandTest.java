package seedu.exercise.logic.commands;

import static seedu.exercise.commons.core.CommonComparator.EXERCISE_DESCENDING_DATE_COMPARATOR;
import static seedu.exercise.commons.core.CommonComparator.REGIME_ASCENDING_NAME_COMPARATOR;
import static seedu.exercise.commons.core.CommonComparator.SCHEDULE_ASCENDING_DATE_COMPARATOR;
import static seedu.exercise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.exercise.model.util.DefaultPropertyBookUtil.getDefaultPropertyBook;
import static seedu.exercise.testutil.typicalutil.TypicalExercises.getTypicalExerciseBook;

import org.junit.jupiter.api.Test;

import seedu.exercise.model.Model;
import seedu.exercise.model.ModelManager;
import seedu.exercise.model.ReadOnlyResourceBook;
import seedu.exercise.model.UserPrefs;
import seedu.exercise.ui.ListResourceType;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS,
                ListResourceType.SUGGEST);
        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalExerciseBook(),
                new ReadOnlyResourceBook<>(REGIME_ASCENDING_NAME_COMPARATOR),
                new ReadOnlyResourceBook<>(EXERCISE_DESCENDING_DATE_COMPARATOR),
                new ReadOnlyResourceBook<>(SCHEDULE_ASCENDING_DATE_COMPARATOR), new UserPrefs(),
            getDefaultPropertyBook());
        Model expectedModel = new ModelManager(getTypicalExerciseBook(),
                new ReadOnlyResourceBook<>(REGIME_ASCENDING_NAME_COMPARATOR),
                new ReadOnlyResourceBook<>(EXERCISE_DESCENDING_DATE_COMPARATOR),
                new ReadOnlyResourceBook<>(SCHEDULE_ASCENDING_DATE_COMPARATOR), new UserPrefs(),
            getDefaultPropertyBook());
        expectedModel.setExerciseBook(new ReadOnlyResourceBook<>(EXERCISE_DESCENDING_DATE_COMPARATOR));

        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS,
                ListResourceType.SUGGEST);
        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

}
