package seedu.exercise.guihandlers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import seedu.exercise.model.resource.Exercise;

/**
 * Handle for exercise list views
 */
public class ExerciseListViewHandle extends NodeHandle<ListView<Exercise>> {

    public static final String LIST_VIEW_ID = "#exerciseListView";

    public ExerciseListViewHandle(ListView<Exercise> rootNode) {
        super(rootNode);
    }

    /**
     * Selects the item at {@code index}
     */
    public void select(int index) {
        if (index >= getListSize()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        getRootNode().getSelectionModel().select(index);
    }

    public int getListSize() {
        return getRootNode().getItems().size();
    }

    public SelectionModel<Exercise> getSelectionModel() {
        return getRootNode().getSelectionModel();
    }

    public ListView<Exercise> getListView() {
        return getRootNode();
    }

    /**
     * Adds an exercise to the underlying list view
     */
    public void addExercise(Exercise e) {
        List<Exercise> current = getRootNode().getItems();
        List<Exercise> added = new ArrayList<>(current);
        added.add(e);
        getRootNode().setItems(FXCollections.observableList(added));
    }
}
