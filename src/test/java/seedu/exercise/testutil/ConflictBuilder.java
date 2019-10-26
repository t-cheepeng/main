package seedu.exercise.testutil;

import seedu.exercise.model.conflict.Conflict;
import seedu.exercise.model.resource.Schedule;

public class ConflictBuilder {

    private Schedule conflictSchedule = TypicalSchedule.VALID_SCHEDULE_CARDIO_DATE;
    private Schedule scheduledSchedule = TypicalSchedule.VALID_SCHEDULE_LEG_DATE;

    public ConflictBuilder withConflict(Schedule schedule) {
        this.conflictSchedule = schedule;
        return this;
    }

    public ConflictBuilder withScheduled(Schedule scheduled) {
        this.scheduledSchedule = scheduled;
        return this;
    }

    public Conflict build() {
        return new Conflict(scheduledSchedule, conflictSchedule);
    }
}
