package seedu.exercise.logic.parser;

import static seedu.exercise.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.exercise.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.exercise.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.exercise.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.exercise.commons.core.Messages;
import seedu.exercise.commons.core.index.Index;
import seedu.exercise.logic.commands.ScheduleCompleteCommand;
import seedu.exercise.logic.commands.ScheduleRegimeCommand;
import seedu.exercise.model.property.Date;
import seedu.exercise.model.property.Name;
import seedu.exercise.testutil.TypicalIndexes;

/**
 * Unit tests for parsing both {@code ScheduleRegimeCommand} and {@code ScheduleCompleteCommand}.
 */
public class ScheduleCommandParserTest {

    //For valid index commands
    public static final String VALID_INDEX = "1";
    public static final String VALID_PREFIX_INDEX = " " + PREFIX_INDEX + VALID_INDEX;
    public static final String VALID_INDEX_2 = "2";
    public static final String VALID_PREFIX_INDEX_2 = " " + PREFIX_INDEX + VALID_INDEX_2;

    //For valid name commands
    public static final String VALID_NAME_CARDIO = "cardio";
    public static final String VALID_NAME_LEGS = "legs";

    public static final String VALID_PREFIX_NAME_CARDIO = " " + PREFIX_NAME + VALID_NAME_CARDIO;
    public static final String VALID_PREFIX_NAME_LEGS = " " + PREFIX_NAME + VALID_NAME_LEGS;

    //For invalid name commands
    public static final String INVALID_NAME_NOT_ENGLISH = "我妈妈";
    public static final String INVALID_NAME_SYMBOLS = "^&*(_!";

    public static final String INVALID_PREFIX_NAME_NOT_ENGLISH = " " + PREFIX_NAME + INVALID_NAME_NOT_ENGLISH;
    public static final String INVALID_PREFIX_NAME_SYMBOLS = " " + PREFIX_NAME + INVALID_NAME_SYMBOLS;

    //For valid date commands
    public static final String VALID_DATE = "12/12/2019";
    public static final String VALID_DATE_2 = "13/12/2019";

    public static final String VALID_PREFIX_DATE = " " + PREFIX_DATE + VALID_DATE;
    public static final String VALID_PREFIX_DATE_2 = " " + PREFIX_DATE + VALID_DATE_2;

    //For invalid date commands
    public static final String INVALID_DATE_DAY = "33/12/2019";
    public static final String INVALID_DATE_WRONG_FORMAT = "12-12-2019";
    public static final String INVALID_DATE_ALPHABETS = "12/12a/2019";
    public static final String INVALID_DATE_SYMBOLS = "12/#12%/2019";

    public static final String INVALID_PREFIX_DATE_DAY = " " + PREFIX_DATE + INVALID_DATE_DAY;
    public static final String INVALID_PREFIX_DATE_WRONG_FORMAT = " " + PREFIX_DATE + INVALID_DATE_WRONG_FORMAT;
    public static final String INVALID_PREFIX_DATE_ALPHABETS = " " + PREFIX_DATE + INVALID_DATE_ALPHABETS;
    public static final String INVALID_PREFIX_DATE_SYMBOLS = " " + PREFIX_DATE + INVALID_DATE_SYMBOLS;

    //For invalids index commands
    public static final String INVALID_INDEX_ZERO = "0";
    public static final String INVALID_INDEX_NEGATIVE = "-1";
    public static final String INVALID_INDEX_EMPTY = "";
    public static final String INVALID_INDEX_ALPHABETS = "alkfj";
    public static final String INVALID_INDEX_SYMBOLS = "@@##%)!)*(";
    public static final String INVALID_INDEX_NOT_ENGLISH = "我妈妈";

    public static final String INVALID_PREFIX_INDEX_ZERO = " " + PREFIX_INDEX + INVALID_INDEX_ZERO;
    public static final String INVALID_PREFIX_INDEX_NEGATIVE = " " + PREFIX_INDEX + INVALID_INDEX_NEGATIVE;
    public static final String INVALID_PREFIX_INDEX_ALPHABETS = " " + PREFIX_INDEX + INVALID_INDEX_ALPHABETS;
    public static final String INVALID_PREFIX_INDEX_SYMBOLS = " " + PREFIX_INDEX + INVALID_INDEX_SYMBOLS;
    public static final String INVALID_PREFIX_INDEX_NOT_ENGLISH = " " + PREFIX_INDEX + INVALID_INDEX_NOT_ENGLISH;
    public static final String INVALID_PREFIX_INDEX_EMPTY = " " + PREFIX_INDEX + INVALID_INDEX_EMPTY;

    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_allFieldsPresentForScheduleComplete_success() {
        Index index = TypicalIndexes.INDEX_ONE_BASED_FIRST;

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + VALID_PREFIX_INDEX, new ScheduleCompleteCommand(index));

        // multiple indexes - last index accepted
        assertParseSuccess(parser,
                VALID_PREFIX_INDEX_2 + VALID_PREFIX_INDEX, new ScheduleCompleteCommand(index));
    }

    @Test
    public void parse_allFieldsPresentForScheduleRegime_success() {
        Name regimeName = new Name(VALID_NAME_CARDIO);
        Date expectedDate = new Date(VALID_DATE);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_PREFIX_NAME_CARDIO + VALID_PREFIX_DATE,
                new ScheduleRegimeCommand(regimeName, expectedDate));

        // multiple name - last name accepted
        assertParseSuccess(parser, VALID_PREFIX_NAME_LEGS + VALID_PREFIX_NAME_CARDIO + VALID_PREFIX_DATE,
                new ScheduleRegimeCommand(regimeName, expectedDate));

        // multiple dates - last date accepted
        assertParseSuccess(parser, VALID_PREFIX_DATE_2 + VALID_PREFIX_DATE + VALID_PREFIX_NAME_CARDIO,
                new ScheduleRegimeCommand(regimeName, expectedDate));
    }

    @Test
    public void parse_compulsoryFieldMissingForScheduleComplete_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleCompleteCommand.MESSAGE_USAGE);

        //missing indexes prefix
        assertParseFailure(parser, VALID_INDEX, expectedMessage);

    }

    @Test
    public void parse_compulsoryFieldMissingForScheduleRegime_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleRegimeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_CARDIO + VALID_PREFIX_DATE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, VALID_DATE + VALID_PREFIX_NAME_CARDIO, expectedMessage);

        // all prefix missing
        assertParseFailure(parser, VALID_DATE + VALID_NAME_CARDIO, expectedMessage);
    }

    @Test
    public void parse_invalidValueForScheduleComplete_failure() {
        String expectedIndexInvalidMessage = Index.MESSAGE_CONSTRAINTS;

        //Invalid index types
        assertParseFailure(parser, INVALID_PREFIX_INDEX_ZERO, expectedIndexInvalidMessage);
        assertParseFailure(parser, INVALID_PREFIX_INDEX_ALPHABETS, expectedIndexInvalidMessage);
        assertParseFailure(parser, INVALID_PREFIX_INDEX_EMPTY, expectedIndexInvalidMessage);
        assertParseFailure(parser, INVALID_PREFIX_INDEX_NEGATIVE, expectedIndexInvalidMessage);
        assertParseFailure(parser, INVALID_PREFIX_INDEX_NOT_ENGLISH, expectedIndexInvalidMessage);
        assertParseFailure(parser, INVALID_PREFIX_INDEX_SYMBOLS, expectedIndexInvalidMessage);

        //non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_PREFIX_INDEX,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCompleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValueForScheduleRegime_failure() {
        // invalid names
        assertParseFailure(parser, INVALID_PREFIX_NAME_NOT_ENGLISH + VALID_PREFIX_DATE,
                Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_PREFIX_NAME_SYMBOLS + VALID_PREFIX_DATE,
                Name.MESSAGE_CONSTRAINTS);

        // invalid dates
        assertParseFailure(parser, INVALID_PREFIX_DATE_ALPHABETS + VALID_PREFIX_NAME_CARDIO,
                Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_PREFIX_DATE_DAY + VALID_PREFIX_NAME_CARDIO,
                Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_PREFIX_DATE_WRONG_FORMAT + VALID_PREFIX_NAME_CARDIO,
                Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_PREFIX_DATE_SYMBOLS + VALID_PREFIX_NAME_CARDIO,
                Date.MESSAGE_CONSTRAINTS);

        // non-empty preambles
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_PREFIX_NAME_CARDIO + VALID_PREFIX_DATE,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ScheduleRegimeCommand.MESSAGE_USAGE));
    }
}
