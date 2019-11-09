package seedu.exercise.ui.testutil;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.stream.IntStream;

import javafx.scene.control.SelectionModel;

/**
 * Utility methods for assertions of GUI tests.
 */
public class GuiAssert {

    /**
     * Asserts that none of the items in {@code model} is selected.
     */
    public static <T> void assertSelectionModelNonSelected(SelectionModel<T> model, int size) {
        IntStream.range(0, size)
                 .forEach(index -> assertFalse(model.isSelected(index)));
    }
}
