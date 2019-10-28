package seedu.exercise.model.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.exercise.testutil.TestUtil.assertCommonEqualsTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.exercise.testutil.CommonTestData;
import seedu.exercise.testutil.typicalutil.TypicalSchedule;

public class ScheduleTest {

    private Schedule legSchedule = TypicalSchedule.VALID_SCHEDULE_LEG_DATE;
    private Schedule legScheduleOnAnotherDate = TypicalSchedule.VALID_SCHEDULE_LEG_DATE_2;

    @Test
    public void equals_variousScenarios_success() {
        assertCommonEqualsTest(legSchedule);

        //diff date -> false
        Assertions.assertFalse(legSchedule.equals(legScheduleOnAnotherDate));
    }

    @Test
    public void toString_returnsCorrectlyFormattedString() {
        assertEquals(CommonTestData.VALID_SCHEDULE_STRING_FOR_TYPICAL_SCHEDULE_LEG, legSchedule.toString());
    }
}
