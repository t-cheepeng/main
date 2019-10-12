package seedu.exercise.logic.parser;

import static seedu.exercise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.exercise.logic.commands.ScheduleCommand;
import seedu.exercise.logic.parser.exceptions.ParseException;
import seedu.exercise.model.exercise.Date;
import seedu.exercise.model.regime.RegimeName;

/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * ScheduleCommand and returns a ScheduleCommand object for execution.
     *
     * @throws ParseException if the user input is not the expected format
     */
    @Override
    public ScheduleCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_DATE);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_NAME, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        RegimeName regimeName = ParserUtil.parseRegimeName(argumentMultimap.getValue(PREFIX_NAME).get());
        Date date = ParserUtil.parseDate(argumentMultimap.getValue(PREFIX_DATE).get());

        return new ScheduleCommand(regimeName, date);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap multimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .allMatch(prefix -> multimap.getValue(prefix).isPresent());
    }
}
