package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

public class LeftRightPanel extends UiPart<Region> {

    private static final String FXML = "LeftRightPanel.fxml";

    @FXML
    private TextArea leftPanel;

    @FXML
    private TextArea rightPanel;

    public LeftRightPanel() {
        super(FXML);
    }

    public void setLeftPanelText(String text) {
        leftPanel.setText(text);
    }

    public void setRightPanelText(String text) {
        rightPanel.setText(text);
    }

    public void clearAllText() {
        leftPanel.setText("");
        rightPanel.setText("");
    }
}
