package seedu.exercise.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.exercise.model.resource.Schedule;

public class ScheduleInfoPanel extends UiPart<Region> {

    private static final String FXML = "ScheduleInfoPanel.fxml";

    private Schedule schedule;

    @FXML
    private Label scheduleDate;

    @FXML
    private AnchorPane cardPane;

    public ScheduleInfoPanel(Schedule schedule) {
        super(FXML);
        scheduleDate.setText(schedule.getDate().toString());
    }

    public void updateText(Schedule schedule) {
        this.schedule = schedule;
        scheduleDate.setText(schedule.getDate().toString());
    }

    public void hide() {
        cardPane.setVisible(false);
    }

    public void show() {
        cardPane.setVisible(true);
    }
}
