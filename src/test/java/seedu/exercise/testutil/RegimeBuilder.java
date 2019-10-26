package seedu.exercise.testutil;

import seedu.exercise.model.UniqueResourceList;
import seedu.exercise.model.property.Name;
import seedu.exercise.model.resource.Exercise;
import seedu.exercise.model.resource.Regime;

public class RegimeBuilder {

    private static final String DEFAULT_NAME = "cardio";

    private Name regimeName;
    private UniqueResourceList<Exercise> regimeExercises;

    public RegimeBuilder() {
        regimeName = new Name(DEFAULT_NAME);
        regimeExercises = new UniqueResourceList<>();
    }

    public RegimeBuilder withName(String name) {
        this.regimeName = new Name(name);
        return this;
    }

    public RegimeBuilder withExerciseList(UniqueResourceList<Exercise> regimeExercises) {
        this.regimeExercises = regimeExercises;
        return this;
    }

    public Regime build() {
        return new Regime(regimeName, regimeExercises);
    }
}
