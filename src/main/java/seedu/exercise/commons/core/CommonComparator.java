package seedu.exercise.commons.core;

import java.util.Comparator;

import seedu.exercise.model.resource.Exercise;
import seedu.exercise.model.resource.Regime;
import seedu.exercise.model.resource.Schedule;

/**
 * Holds common comparators for sorting of resource lists.
 */
public class CommonComparator {

    public static final Comparator<Exercise> EXERCISE_DESCENDING_DATE_COMPARATOR = (o1, o2)
            -> -o1.getDate().value.compareTo(o2.getDate().value);

    public static final Comparator<Regime> REGIME_ASCENDING_NAME_COMPARATOR =
            Comparator.comparing(o -> o.getRegimeName().toString());

    public static final Comparator<Schedule> SCHEDULE_ASCENDING_DATE_COMPARATOR =
            Comparator.comparing(o -> o.getDate().value);
}
