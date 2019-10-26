package seedu.exercise.testutil;

import seedu.exercise.model.property.Date;
import seedu.exercise.model.resource.Regime;
import seedu.exercise.model.resource.Schedule;

public class ScheduleBuilder {

    private Regime regime = TypicalRegime.VALID_REGIME_CARDIO;
    private Date date = TypicalDates.DATE_1;

    public ScheduleBuilder withRegime(Regime regime) {
        this.regime = regime;
        return this;
    }

    public ScheduleBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public Schedule build() {
        return new Schedule(regime, date);
    }
}
