package seedu.exercise.commons.core.index;

import java.util.List;

public class IndexUtil {

    public static <T> boolean isIndexOutOfBounds(Index index, List<T> list) {
        return index.getZeroBased() >= list.size();
    }

    public static <T> boolean areIndexesOutOfBounds(List<Index> indexList, List<T> list) {
        for (Index index : indexList) {
            if (isIndexOutOfBounds(index, list)) {
                return true;
            }
        }
        return false;
    }
}
