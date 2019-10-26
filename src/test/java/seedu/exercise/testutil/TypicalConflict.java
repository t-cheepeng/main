package seedu.exercise.testutil;

import seedu.exercise.model.conflict.Conflict;

public class TypicalConflict {

    public static final Conflict VALID_CONFLICT = new ConflictBuilder()
            .withScheduled(TypicalSchedule.VALID_SCHEDULE_CARDIO_DATE)
            .withConflict(TypicalSchedule.VALID_SCHEDULE_LEG_DATE)
            .build();
}
