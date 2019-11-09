package seedu.exercise.ui.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import javafx.scene.control.SelectionModel;
import javafx.scene.input.Clipboard;
import seedu.exercise.guihandlers.GuiRobot;

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

    /**
     * Asserts that the system's {@code clipboard} is the same as {@code expectedContent}
     */
    public static void assertSystemClipboardStringContentEquals(String expectedContent, Clipboard clipboard) {
        new GuiRobot().interact(() -> {
            assertEquals(expectedContent, clipboard.getString());
        });
    }

    /**
     * Asserts that the window with {@code windowTitle} is shown.
     */
    public static void assertWindowShown(String windowTitle) {
        assertTrue(new GuiRobot().isWindowShown(windowTitle));
    }

    /**
     * Asserts that the window with {@code windowTitle} is not shown.
     */
    public static void assertWindowNotShown(String windowTitle) {
        assertFalse(new GuiRobot().isWindowShown(windowTitle));
    }

}
