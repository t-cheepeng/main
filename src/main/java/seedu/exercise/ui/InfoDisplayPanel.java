package seedu.exercise.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.exercise.commons.core.LogsCenter;
import seedu.exercise.model.resource.Exercise;
import seedu.exercise.model.resource.Resource;

/**
 * Placeholder Panel for Specific Information displayed in center panel.
 */
public class InfoDisplayPanel extends UiPart<Region> {

    private static final String FXML = "InfoDisplayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoDisplayPanel.class);

    @FXML
    private StackPane infoPanelPlaceholder;

    public InfoDisplayPanel() {
        super(FXML);
    }

    public void update(Resource resource) {
        if (infoPanelPlaceholder.getChildren().size() != 0) {
            infoPanelPlaceholder.getChildren().remove(0);
        }
            ExerciseInfoPanel panel = new ExerciseInfoPanel((Exercise) resource);
            infoPanelPlaceholder.getChildren().add(panel.getRoot());
    }
}
