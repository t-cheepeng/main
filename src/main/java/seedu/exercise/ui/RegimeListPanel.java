package seedu.exercise.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.exercise.commons.core.LogsCenter;
import seedu.exercise.model.resource.Regime;

/**
 * Panel containing list of regimes.
 */
public class RegimeListPanel extends ResourceListPanel {
    private static final String FXML = "RegimeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RegimeListPanel.class);

    @FXML
    private ListView<Regime> regimeListView;

    public RegimeListPanel(ObservableList<Regime> regimeList) {
        super(FXML, regimeList);
        regimeListView.setItems(regimeList);
        regimeListView.setCellFactory(listView -> new RegimeListViewCell());
        regimeListView.getFocusModel().focusedItemProperty().addListener(getDefaultListViewListener());
    }

    @Override
    protected void resetListSelection() {
        regimeListView.getSelectionModel().clearSelection();
    }

    @Override
    protected ListView<Regime> getResourceListView() {
        return regimeListView;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Regime} using a {@code RegimeCard}.
     */
    class RegimeListViewCell extends ListCell<Regime> {
        @Override
        protected void updateItem(Regime regime, boolean empty) {
            super.updateItem(regime, empty);

            if (empty || regime == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RegimeCard(regime, getIndex() + 1).getRoot());
            }
        }
    }
}
