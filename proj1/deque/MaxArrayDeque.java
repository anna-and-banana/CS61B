package deque;

import java.util.Comparator;

// A special ArrayDeque which have a method to get the max item
public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    // returns the maximum element in the deque as governed by the COMPARATOR which given at initialize.
    // If the MaxArrayDeque is empty, simply return null.
    public T max() {
        if (isEmpty()) {
            return null;
        }

        T maxItem = this.get(0);
        for (T item : this) {
            if (comparator.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

    // returns the maximum element in the deque as governed by the parameter COMPARATOR.
    // If the MaxArrayDeque is empty, simply return null.
    public T max(Comparator<T> comparator) {
        if (isEmpty()) {
            return null;
        }

        T maxItem = this.get(0);
        for (T item : this) {
            if (comparator.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}
