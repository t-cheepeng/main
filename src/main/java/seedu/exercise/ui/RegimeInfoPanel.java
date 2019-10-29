package seedu.exercise.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.exercise.model.resource.Regime;

public class RegimeInfoPanel extends UiPart<Region> {
    private static final String FXML = "RegimeInfoPanel.fxml";

    private Regime regime;

    @FXML
    private AnchorPane cardPane;

    @FXML
    private Label regimeName;

    public RegimeInfoPanel() {
        super(FXML);
    }

    public void updateText(Regime regime) {
        this.regime = regime;
        regimeName.setText(regime.toString());
    }

    public void hide() {
        cardPane.setVisible(false);
    }

    public void show() {
        cardPane.setVisible(true);
    }
}
