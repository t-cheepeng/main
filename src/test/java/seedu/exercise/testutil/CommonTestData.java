package seedu.exercise.testutil;

import static seedu.exercise.logic.parser.CliSyntax.PREFIX_CONFLICT_INDEX;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.exercise.logic.parser.CliSyntax.PREFIX_NAME;

public class CommonTestData {

    //For valid index
    public static final String VALID_INDEX = "1";
    public static final String VALID_PREFIX_INDEX = " " + PREFIX_INDEX + VALID_INDEX;
    public static final String VALID_INDEX_2 = "2";
    public static final String VALID_PREFIX_INDEX_2 = " " + PREFIX_INDEX + VALID_INDEX_2;

    //For valid conflict index
    public static final String VALID_PREFIX_CONFLICT_INDEX = " " + PREFIX_CONFLICT_INDEX + VALID_INDEX;
    public static final String VALID_PREFIX_CONFLICT_INDEX_2 = " " + PREFIX_CONFLICT_INDEX + VALID_INDEX_2;

    //For valid name
    public static final String VALID_NAME_CARDIO = "cardio";
    public static final String VALID_NAME_LEGS = "legs";

    public static final String VALID_PREFIX_NAME_CARDIO = " " + PREFIX_NAME + VALID_NAME_CARDIO;
    public static final String VALID_PREFIX_NAME_LEGS = " " + PREFIX_NAME + VALID_NAME_LEGS;

    //For invalid name
    public static final String INVALID_NAME_NOT_ENGLISH = "我妈妈";
    public static final String INVALID_NAME_SYMBOLS = "^&*(_!";

    public static final String INVALID_PREFIX_NAME_NOT_ENGLISH = " " + PREFIX_NAME + INVALID_NAME_NOT_ENGLISH;
    public static final String INVALID_PREFIX_NAME_SYMBOLS = " " + PREFIX_NAME + INVALID_NAME_SYMBOLS;

    //For valid date
    public static final String VALID_DATE = "12/12/2019";
    public static final String VALID_DATE_2 = "13/12/2019";

    public static final String VALID_PREFIX_DATE = " " + PREFIX_DATE + VALID_DATE;
    public static final String VALID_PREFIX_DATE_2 = " " + PREFIX_DATE + VALID_DATE_2;

    //For invalid date
    public static final String INVALID_DATE_DAY = "33/12/2019";
    public static final String INVALID_DATE_WRONG_FORMAT = "12-12-2019";
    public static final String INVALID_DATE_ALPHABETS = "12/12a/2019";
    public static final String INVALID_DATE_SYMBOLS = "12/#12%/2019";

    public static final String INVALID_PREFIX_DATE_DAY = " " + PREFIX_DATE + INVALID_DATE_DAY;
    public static final String INVALID_PREFIX_DATE_WRONG_FORMAT = " " + PREFIX_DATE + INVALID_DATE_WRONG_FORMAT;
    public static final String INVALID_PREFIX_DATE_ALPHABETS = " " + PREFIX_DATE + INVALID_DATE_ALPHABETS;
    public static final String INVALID_PREFIX_DATE_SYMBOLS = " " + PREFIX_DATE + INVALID_DATE_SYMBOLS;

    //For invalids index
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

    //For invalid conflict index
    public static final String INVALID_PREFIX_CONFLICT_INDEX_ZERO = " " + PREFIX_CONFLICT_INDEX + INVALID_INDEX_ZERO;
    public static final String INVALID_PREFIX_CONFLICT_INDEX_NEGATIVE = " " + PREFIX_CONFLICT_INDEX + INVALID_INDEX_NEGATIVE;
    public static final String INVALID_PREFIX_CONFLICT_INDEX_ALPHABETS = " " + PREFIX_CONFLICT_INDEX + INVALID_INDEX_ALPHABETS;
    public static final String INVALID_PREFIX_CONFLICT_INDEX_SYMBOLS = " " + PREFIX_CONFLICT_INDEX + INVALID_INDEX_SYMBOLS;
    public static final String INVALID_PREFIX_CONFLICT_INDEX_NOT_ENGLISH = " " + PREFIX_CONFLICT_INDEX + INVALID_INDEX_NOT_ENGLISH;
    public static final String INVALID_PREFIX_CONFLICT_INDEX_EMPTY = " " + PREFIX_CONFLICT_INDEX + INVALID_INDEX_EMPTY;
}
