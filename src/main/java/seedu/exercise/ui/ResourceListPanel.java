package seedu.exercise.ui;

import static java.util.Objects.requireNonNull;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.exercise.model.resource.Resource;

/**
 * Panel containing the list of resources.
 */
public abstract class ResourceListPanel extends UiPart<Region> {

    private static final String FXML = "ResourceListPanel.fxml";

    private OnItemSelectListener itemSelectListener;
    private ObservableList<? extends Resource> resourceList;

    public ResourceListPanel(String FXML, ObservableList<? extends Resource> resourceList) {
        super(FXML);
        this.resourceList = resourceList;

        setResourceListListener();
    }

    public void setOnItemSelectListener(OnItemSelectListener listener) {
        requireNonNull(listener);
        itemSelectListener = listener;
    }

    void notifyOnSelectListener(Resource r) {
        if (itemSelectListener != null) {
            itemSelectListener.onItemSelect(r);
        }
    }

    void selectFocusAndScrollTo(ListView<? extends Resource> resources, int index) {
        resources.getSelectionModel().select(index);
        resources.getFocusModel().focus(index);
        resources.scrollTo(index);
    }

    ChangeListener<Resource> getDefaultListViewListener() {
        return new ChangeListener<Resource>() {
            @Override
            public void changed(ObservableValue<? extends Resource> observableValue, Resource resource, Resource t1) {
                if (t1 != null) {
                    notifyOnSelectListener(t1);
                }
            }
        };
    }

    OnItemSelectListener getDefaultListener() {
        return new OnItemSelectListener() {
            @Override
            public void onItemSelect(Resource selected) {
                notifyOnSelectListener(selected);
            }
        };
    }

    private void setResourceListListener() {
        resourceList.addListener(new ListChangeListener<Resource>() {
            @Override
            public void onChanged(Change<? extends Resource> change) {
                while (change.next()) {
                    int index = -1;
                    if (change.wasReplaced()) {
                        index = change.getFrom();
                    } else if (change.wasAdded()) {
                        index = resourceList.size() - 1;
                    }

                    if (index >= 0) {
                        selectGivenIndex(index);
                        notifyOnSelectListener(resourceList.get(index));
                    }
                }

            }
        });
    }

    protected abstract void selectGivenIndex(int index);

    public interface OnItemSelectListener {
        void onItemSelect(Resource selected);
    }

}
