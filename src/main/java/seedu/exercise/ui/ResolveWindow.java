package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.logic.commands.CommandResult;

/**
 * Controller for resolving schedule conflicts.
 * <p>
 * This window acts as a visual feedback
 * for the conflicting schedules. Provides a left right panel for display of
 * the schedules and a command box. Window will block all events until it is
 * closed or resolved.
 * </p>
 */
public class ResolveWindow extends UiPart<Stage> {

    private static final String FXML = "ResolveWindow.fxml";

    private LeftRightPanel leftRightPanel;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane commandBoxPlaceHolder;

    @FXML
    private StackPane resultDisplayPlaceHolder;

    @FXML
    private StackPane leftRightPanelPlaceHolder;


    public ResolveWindow(Stage root) {
        super(FXML, root);
        blockEvents(root);
        fillInnerParts();
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

    /**
     * Hides the window and clears the text in LeftRightPanel
     *
     * @see LeftRightPanel#clearAllText()
     */
    public void hideAndClearText() {
        getRoot().hide();
        leftRightPanel.clearAllText();
    }

    public void focus() {
        getRoot().requestFocus();
    }

    public void setLeftRightText(String leftPanelText, String rightPanelText) {
        leftRightPanel.setLeftPanelText(leftPanelText);
        leftRightPanel.setRightPanelText(rightPanelText);
    }

    private void fillInnerParts() {
        fillLeftRightPanel();
        fillCommandBox();
        fillResultDisplay();
    }

    private void fillResultDisplay() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceHolder.getChildren().add(resultDisplay.getRoot());
    }

    private void fillLeftRightPanel() {
        leftRightPanel = new LeftRightPanel();
        leftRightPanelPlaceHolder.getChildren().add(leftRightPanel.getRoot());
    }

    private void fillCommandBox() {
        CommandBox commandBox = new CommandBox(this::execute);
        commandBoxPlaceHolder.getChildren().add(commandBox.getRoot());
    }

    private void blockEvents(Stage root) {
        root.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Executor for resolve window's command box.
     * <p>
     *     This executor will only allow resolve commands to be executed.
     * </p>
     * @param commandText user input
     * @return Result from executing a valid command
     */
    private CommandResult execute(String commandText) {
        if (commandText.equals("resolve")) {
            hideAndClearText();
            return new CommandResult("Resolved", false, false);
        } else {
            CommandResult result = new CommandResult("Not resolve command", false, false);
            resultDisplay.setFeedbackToUser(result.getFeedbackToUser());
            return result;
        }
    }
}
