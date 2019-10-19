package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.exercise.commons.core.Messages.MESSAGE_INVALID_CONTEXT;
import static seedu.exercise.commons.util.CollectionUtil.areListsEmpty;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CONFLICT_INDEX;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.exercise.MainApp;
import seedu.exercise.commons.core.Messages;
import seedu.exercise.commons.core.State;
import seedu.exercise.commons.core.index.Index;
import seedu.exercise.commons.core.index.IndexUtil;
import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;
import seedu.exercise.model.UniqueResourceList;
import seedu.exercise.model.conflict.Conflict;
import seedu.exercise.model.property.Name;
import seedu.exercise.model.resource.Regime;

public class ResolveCommand extends Command {

    public static final String COMMAND_WORD = "resolve";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resolves a scheduling conflict. Parameters: "
            + PREFIX_NAME + "NEW_REGIME_NAME "
            + "[" + PREFIX_INDEX + "INDEX_OF_SCHEDULED_EXERCISES" + "]"
            + "[" + PREFIX_CONFLICT_INDEX + "INDEX_OF_CONFLICTING_EXERCISES" + "]"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "cardio new "
            + PREFIX_INDEX + "1 "
            + PREFIX_INDEX + "3 "
            + PREFIX_CONFLICT_INDEX + "1 "
            + PREFIX_CONFLICT_INDEX + "4 ";

    public static final String MESSAGE_SUCCESS = "Successfully resolved conflict between regime %1$s and regime %2$s";
    public static final String MESSAGE_DUPLICATE_NAME = "Regime name %1$s already exists. Try another name";
    public static final String MESSAGE_INVALID_NAME = "Regime name is neither the scheduled regime"
            + " or the conflicting regime";

    private Name regimeName;
    private Conflict conflict;
    private List<Index> indexToTakeFromSchedule;
    private List<Index> indexToTakeFromConflict;

    public ResolveCommand(Name regimeName, List<Index> indexToTakeFromSchedule, List<Index> indexToTakeFromConflict) {
        this.regimeName = regimeName;
        this.indexToTakeFromSchedule = indexToTakeFromSchedule;
        this.indexToTakeFromConflict = indexToTakeFromConflict;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        checkIfProgramStateIsValid();

        conflict = getConflictFromModel(model);

        checkValidIndexes();
        if (!areListsEmpty(indexToTakeFromSchedule, indexToTakeFromConflict)) {
            checkNonDuplicateRegimeNameFromModel(model);
        } else {
            checkNameIsFromConflictingSchedules();
        }

        resolveConflict(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                conflict.getScheduledName(),
                conflict.getConflictedName()));
    }

    private void resolveConflict(Model model) {
        requireNonNull(model);
        model.resolveConflict(regimeName, indexToTakeFromSchedule, indexToTakeFromConflict);
        MainApp.setState(State.NORMAL);
    }

    private Conflict getConflictFromModel(Model model) {
        requireNonNull(model);
        return model.getConflict();
    }

    private void checkValidIndexes() throws CommandException {
        if(IndexUtil.areIndexesOutOfBounds(indexToTakeFromSchedule, conflict.getScheduledExerciseList())
            || IndexUtil.areIndexesOutOfBounds(indexToTakeFromConflict, conflict.getConflictedExerciseList())) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
        }
    }

    private void checkIfProgramStateIsValid() throws CommandException {
        if (MainApp.getState() != State.IN_CONFLICT) {
            throw new CommandException(String.format(MESSAGE_INVALID_CONTEXT, getClass().getSimpleName()));
        }
    }

    private void checkNonDuplicateRegimeNameFromModel(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasRegime(new Regime(regimeName, new UniqueResourceList<>()))) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_NAME, regimeName.toString()));
        }
    }

    private void checkNameIsFromConflictingSchedules() throws CommandException {
        String name = regimeName.toString();
        if (!name.equals(conflict.getConflictedName()) && !name.equals(conflict.getScheduledName())) {
            throw new CommandException(MESSAGE_INVALID_NAME);
        }
    }
}
