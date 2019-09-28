package seedu.address.ui;

import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Window for resolving scheduling conflicts. This window acts as a visual feedback
 * for the conflicting schedules. Provides a left right panel for display of
 * the schedules and a command box. Window will block all events until it is
 * closed or resolved.
 */
public class ResolveWindow extends UiPart<Stage> {

    private static final String FXML = "ResolveWindow.fxml";

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
}
