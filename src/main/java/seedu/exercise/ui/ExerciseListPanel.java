package seedu.exercise.ui;

import static java.util.Objects.checkFromIndexSize;
import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.exercise.commons.core.LogsCenter;
import seedu.exercise.model.resource.Exercise;

/**
 * Panel containing the list of exercises.
 */
public class ExerciseListPanel extends ResourceListPanel {

    private static final String FXML = "ExerciseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private ListView<Exercise> exerciseListView;

    @FXML
    private Label exerciseTitle;

    public ExerciseListPanel(ObservableList<Exercise> exerciseList) {
        super(FXML, exerciseList);
        exerciseListView.setItems(exerciseList);
        exerciseListView.setCellFactory(listView -> new ExerciseListViewCell());
        exerciseListView.getFocusModel().focusedItemProperty().addListener(new ChangeListener<Exercise>() {
            @Override
            public void changed(ObservableValue<? extends Exercise> observableValue, Exercise exercise, Exercise t1) {
                notifyOnSelectListener(t1);
            }
        });
    }

    public boolean isExerciseSelected() {
        return exerciseListView.getSelectionModel().getSelectedIndex() >= 0;
    }

    public Optional<Exercise> getSelectedExercise() {
        if (isExerciseSelected()) {
            return Optional.of(exerciseListView.getSelectionModel().getSelectedItem());
        } else {
            return Optional.empty();
        }
    }

    public ListView<Exercise> getExerciseListView() {
        return exerciseListView;
    }

    public void setPanelTitleText(String title) {
        requireNonNull(title);
        exerciseTitle.setText(title);
    }

    @Override
    protected void selectGivenIndex(int index) {
        if (index >= 0) {
            exerciseListView.getSelectionModel().select(index);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Exercise} using a {@code ExerciseInfoPanel}.
     */
    class ExerciseListViewCell extends ListCell<Exercise> {
        @Override
        protected void updateItem(Exercise exercise, boolean empty) {
            super.updateItem(exercise, empty);

            if (empty || exercise == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExerciseListCard(exercise, getIndex() + 1).getRoot());
            }
        }
    }
}
