package seedu.exercise.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.exercise.commons.core.LogsCenter;
import seedu.exercise.model.resource.Exercise;
import seedu.exercise.model.resource.Regime;
import seedu.exercise.model.resource.Resource;
import seedu.exercise.model.resource.Schedule;

/**
 * Placeholder Panel for Specific Information displayed in center panel.
 */
public class InfoDisplayPanel extends UiPart<Region> {

    private static final String FXML = "InfoDisplayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoDisplayPanel.class);

    private ExerciseInfoPanel exerciseInfoPanel;
    private RegimeInfoPanel regimeInfoPanel;
    private ScheduleInfoPanel scheduleInfoPanel;

    @FXML
    private StackPane infoPanelPlaceholder;

    public InfoDisplayPanel() {
        super(FXML);
        exerciseInfoPanel = new ExerciseInfoPanel();
        regimeInfoPanel = new RegimeInfoPanel();
        scheduleInfoPanel = new ScheduleInfoPanel();

        infoPanelPlaceholder.getChildren().addAll(exerciseInfoPanel.getRoot(), regimeInfoPanel.getRoot(), scheduleInfoPanel.getRoot());
    }

    //TODO somehow solve this update method to swap out the info panels.
    public void update(Resource resource) {
//        if (infoPanelPlaceholder.getChildren().size() != 0) {
//            infoPanelPlaceholder.getChildren().remove(0);
//        }
        if (resource instanceof Exercise) {
            exerciseInfoPanel.updateText((Exercise) resource);
            exerciseInfoPanel.show();
            scheduleInfoPanel.hide();
            regimeInfoPanel.hide();
        } else if (resource instanceof Regime) {
            regimeInfoPanel.updateText((Regime) resource);
            exerciseInfoPanel.hide();
            scheduleInfoPanel.hide();
            regimeInfoPanel.show();
        } else if (resource instanceof Schedule) {
            scheduleInfoPanel.updateText((Schedule) resource);
            exerciseInfoPanel.hide();
            scheduleInfoPanel.show();
            regimeInfoPanel.hide();
        }
    }
}
