package seedu.exercise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.exercise.logic.commands.exceptions.CommandException;
import seedu.exercise.model.Model;

/**
 * Schedules a regime on a certain date
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Schedules a regime at a specific date. Parameters: "
            + PREFIX_NAME + "REGIME NAME "
            + PREFIX_DATE + "DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "cardio "
            + PREFIX_DATE + "19/12/2019";

    public static final String MESSAGE_SUCCESS = "Regime %1$s scheduled on %2$s";
    public static final String MESSAGE_CONFLICT = "Regime to be scheduled conflicts with another regime. "
            + "Opening resolve window...";

    private String regimeName;
    private String dateToSchedule;

    public ScheduleCommand(String name, String date) {
        //TODO take in a regime object and date object to be scheduled.
        regimeName = name;
        dateToSchedule = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        /*TODO check if date has something already scheduled on it.
        if it does, throw Schedule Exception.
        model.hasRegimeOnDate(Date date) */

        /*
         * TODO if no schedule conflicts make the regime at the date
         * model.scheduleRegimeAt(Regime regime, Date date);
         */
        return new CommandResult(String.format(MESSAGE_SUCCESS, regimeName, dateToSchedule));
    }
}
