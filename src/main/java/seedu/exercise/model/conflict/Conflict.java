package seedu.exercise.model.conflict;

import java.util.List;
import java.util.Objects;

import seedu.exercise.model.resource.Exercise;
import seedu.exercise.model.resource.Schedule;

/**
 * Represents a scheduling conflict between two schedules.
 *
 * A conflict can only happen when the {@code date} between two
 * {@code schedules} are the same as specified by {@link Schedule#isSameResource}.
 */
public class Conflict {

    private final Schedule scheduled;
    private final Schedule conflicted;

    public Conflict(Schedule scheduled, Schedule conflicted) {
        this.scheduled = scheduled;
        this.conflicted = conflicted;
    }

    public Schedule getScheduled() {
        return scheduled;
    }

    public Schedule getConflicted() {
        return conflicted;
    }

    public List<Exercise> getScheduledExerciseList() {
        return scheduled.getExercises();
    }

    public List<Exercise> getConflictedExerciseList() {
        return conflicted.getExercises();
    }

    public String getScheduledName() {
        return scheduled.getRegimeName();
    }

    public String getConflictedName() {
        return conflicted.getRegimeName();
    }

    @Override
    public boolean equals(Object other) {
        return (other == this)
            || (other instanceof Conflict)
            && ((scheduled.equals(((Conflict) other).scheduled)
                && conflicted.equals(((Conflict) other).conflicted)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduled, conflicted);
    }
}
