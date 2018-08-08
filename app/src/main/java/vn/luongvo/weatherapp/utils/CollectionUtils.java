package vn.luongvo.weatherapp.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luongvo
 */
public class CollectionUtils {

    private CollectionUtils() {
        // force do not allow create new instance
    }

    /**
     * Copies all of the elements from one list into another.
     */
    public static <T> List<T> copy(List<T> dest, List<T> src) {
        if (dest == null) {
            dest = new ArrayList<>();
        } else if (dest == src) {
            // ignore if the same reference
            return dest;
        } else {
            dest.clear();
        }
        if (src != null && !src.isEmpty()) {
            dest.addAll(src);
        }
        return dest;
    }

    public static <T> boolean equals(List<T> l1, List<T> l2) {
        int s1 = l1 == null ? 0 : l1.size();
        int s2 = l2 == null ? 0 : l2.size();

        if (s1 == s2) {
            if (l1 != null && l2 != null) {
                for (int i = 0; i < l1.size(); i++) {
                    if (!l1.get(i).equals(l2.get(i))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static <T> boolean isEmpty(List<T> collections) {
        return collections == null || collections.isEmpty();
    }

    public static int[] listToArray(@NonNull List<Integer> list) {
        int n = list.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
