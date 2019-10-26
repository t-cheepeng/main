package seedu.exercise.testutil;

import static seedu.exercise.testutil.TypicalSchedule.VALID_SCHEDULE_DATE;

import seedu.exercise.model.property.Date;
import seedu.exercise.model.resource.Regime;
import seedu.exercise.model.resource.Schedule;

public class ScheduleBuilder {

    private Regime regime = TypicalRegime.VALID_REGIME_CARDIO;
    private Date date = new Date(VALID_SCHEDULE_DATE);

    public ScheduleBuilder withRegime(Regime regime) {
        this.regime = regime;
        return this;
    }

    public ScheduleBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    public Schedule build() {
        return new Schedule(regime, date);
    }
}
