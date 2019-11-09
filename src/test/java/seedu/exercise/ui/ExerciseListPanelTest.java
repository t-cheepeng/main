package seedu.exercise.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.exercise.guihandlers.ExerciseListViewHandle.LIST_VIEW_ID;
import static seedu.exercise.guihandlers.LabelHandle.LABEL_ID;
import static seedu.exercise.testutil.typicalutil.TypicalExercises.getTypicalExercises;
import static seedu.exercise.ui.testutil.GuiAssert.assertSelectionModelNonSelected;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.exercise.guihandlers.ExerciseListViewHandle;
import seedu.exercise.guihandlers.LabelHandle;
import seedu.exercise.model.resource.Exercise;
import seedu.exercise.testutil.typicalutil.TypicalExercises;
import seedu.exercise.ui.testutil.GuiUnitTest;

public class ExerciseListPanelTest extends GuiUnitTest {

    private static final String TITLE_TEXT = "test title";

    private ObservableList<Exercise> testExercises;

    private ExerciseListPanel exerciseListPanel;
    private ExerciseListViewHandle exerciseListViewHandle;
    private LabelHandle labelHandle;

    @BeforeEach
    private void setUp() {
        testExercises = FXCollections.observableList(getTypicalExercises());
        exerciseListPanel = new ExerciseListPanel(testExercises);
        exerciseListViewHandle = new ExerciseListViewHandle(
                getChildNode(exerciseListPanel.getRoot(), LIST_VIEW_ID));
        labelHandle = new LabelHandle(
                getChildNode(exerciseListPanel.getRoot(), LABEL_ID));
        uiPartExtension.setUiPart(exerciseListPanel);
    }

    @Test
    public void setPanelTitleText_correctString_textShown() {
        guiRobot.interact(() -> exerciseListPanel.setPanelTitleText(TITLE_TEXT));
        assertEquals(TITLE_TEXT, labelHandle.getLabelText());
    }

    @Test
    public void resetListSelection_selectionModelResets_noSelectedItems() {
        exerciseListViewHandle.select(0);
        exerciseListPanel.resetListSelection();
        assertSelectionModelNonSelected(
                exerciseListViewHandle.getSelectionModel(), exerciseListViewHandle.getListSize());
    }

    @Test
    public void getResourceListView_testExercises_sameView() {
        assertEquals(exerciseListViewHandle.getListView(), exerciseListPanel.getResourceListView());
    }

    @Test
    public void setExerciseCard_newExercise_added() {
        guiRobot.interact(() -> exerciseListViewHandle.addExercise(TypicalExercises.SWIMMING));
        assertEquals(exerciseListViewHandle.getListView(), exerciseListPanel.getResourceListView());
    }
}
