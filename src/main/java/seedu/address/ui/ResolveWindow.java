package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.logic.commands.CommandResult;

/**
 * Window for resolving scheduling conflicts. This window acts as a visual feedback
 * for the conflicting schedules. Provides a left right panel for display of
 * the schedules and a command box. Window will block all events until it is
 * closed or resolved.
 */
public class ResolveWindow extends UiPart<Stage> {

    private static final String FXML = "ResolveWindow.fxml";

    private LeftRightPanel leftRightPanel;

    @FXML
    private StackPane commandBoxPlaceHolder;

    @FXML
    private StackPane leftRightPanelPlaceHolder;

    public ResolveWindow(Stage root) {
        super(FXML, root);
        root.initModality(Modality.APPLICATION_MODAL);
    }

    public ResolveWindow() {
        this(new Stage());
    }

    /**
     * Shows the resolve window.
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void hide() {
        getRoot().hide();
    }

    public void focus() {
        getRoot().requestFocus();
    }

    public void fillInnerParts(String leftPanelText, String rightPanelText) {
        fillLeftRightPanel(leftPanelText, rightPanelText);
        fillCommandBox();
    }

    private void fillLeftRightPanel(String leftPanelText, String rightPanelText) {
        leftRightPanel = new LeftRightPanel();
        leftRightPanel.setLeftPanelText(leftPanelText);
        leftRightPanel.setRightPanelText(rightPanelText);
        leftRightPanelPlaceHolder.getChildren().add(leftRightPanel.getRoot());
    }

    private void fillCommandBox() {
        CommandBox commandBox = new CommandBox(this::execute);
        commandBoxPlaceHolder.getChildren().add(commandBox.getRoot());
    }

    private CommandResult execute(String commandText) {
        if(commandText.equals("resolve")) {
            hide();
            return new CommandResult("Resolved", false ,false);
        } else {
            return new CommandResult("Not resolved", false, false);
        }
    }
}
