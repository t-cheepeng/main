package seedu.exercise.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import seedu.exercise.model.resource.Resource;

/**
 * Panel containing the list of resources.
 */
public abstract class ResourceListPanel extends UiPart<Region> {

    private static final String FXML = "ResourceListPanel.fxml";

    private onItemSelectListener itemSelectListener;
    private ObservableList<? extends Resource> resourceList;

    public ResourceListPanel(String FXML, ObservableList<? extends Resource> resourceList) {
        super(FXML);
        this.resourceList = resourceList;

        setResourceListListener();
    }

    public void setOnItemSelectListener(onItemSelectListener listener) {
        requireNonNull(listener);
        itemSelectListener = listener;
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

    void notifyOnSelectListener(Resource r) {
        if (itemSelectListener != null) {
            itemSelectListener.onItemSelect(r);
        }
    }

    protected abstract void selectGivenIndex(int index);

    public interface onItemSelectListener {
        void onItemSelect(Resource selected);
    }

}
