package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private static final int INITIAL_SIZE = 8;
    private static final int RESIZE_FACTOR = 2;

    /* Methods */

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_SIZE];
        nextFirst = INITIAL_SIZE / 2 - 1;
        nextLast = INITIAL_SIZE / 2;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(size * RESIZE_FACTOR);
        }
        items[nextFirst] = item;
        nextFirst = nextFirstIndex();
        size++;
    }

    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(size * RESIZE_FACTOR);
        }
        items[nextLast] = item;
        nextLast = nextLastIndex();
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        T[] ordered = makeOrdered();
        for (T x : ordered) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (isSpacious()) {
            resize(size * RESIZE_FACTOR);
        }
        int currentFirst = relativeIndexOf(0);
        T remove = items[currentFirst];
        items[currentFirst] = null;
        nextFirst = currentFirst;
        size--;

        return remove;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (isSpacious()) {
            resize(size * RESIZE_FACTOR);
        }
        int currentLast = relativeIndexOf(size - 1);
        T remove = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        size--;

        return remove;
    }

    @Override
    public T get(int index) {
        return (isEmpty() || isIndexNotInRange(index)) ? null : items[relativeIndexOf(index)];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /*@Override
    public boolean equals(Object obj) {
        // For saving calculation time, if OBJ is self, return true
        if (this == obj) {
            return true;
        }

        // If two things are not the same object,
        // then check two things are equal or not
        if (obj instanceof ArrayDeque other) {
            if (this.size != other.size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                T x = this.get(i);
                T y = (T) other.get(i);
                if (!x.equals(y)) {
                    return false;
                }
            }
            return true;
        }
        // Not the same and not equal
        return false;
    }*/

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof Deque))
            return false;
        Deque<T> oc = (Deque<T>) o;
        if (!(oc.size() == this.size))
            return false;
        for (int i = 0; i < size; i++) {
            if (!(this.get(i).equals(oc.get(i))))
                return false;
        }
        return true;
    }

    /* Helper members */

    private boolean isFull() {
        return size == items.length;
    }

    private boolean isSpacious() {
        return (size > INITIAL_SIZE) && (size * 4 < items.length);
    }

    private boolean isIndexNotInRange(int index) {
        return index < 0 || index >= size;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        T[] tmp = makeOrdered();
        int dstIndex = (capacity - size) / 2;
        System.arraycopy(tmp, 0, newArray, dstIndex, size);

        items = newArray;
        nextFirst = dstIndex - 1;
        nextLast = dstIndex + size;
    }

    // Return an ordered array with exactly length = SIZE
    // which first item at index 0 and last item at index SIZE-1
    private T[] makeOrdered() {
        T[] ordered = (T[]) new Object[size];

        for (int i = 0; i < size; i++) {
            int position = relativeIndexOf(i);
            ordered[i] = items[position];
        }
        return ordered;
    }

    private int relativeIndexOf(int index) {
        int first = currentFirstIndex();
        int relative = first + index;
        int length = items.length;
        return relative >= length ? relative - length : relative;
    }

    private int currentFirstIndex() {
        return nextFirst == items.length - 1 ? 0 : nextFirst + 1;
    }

    private int nextFirstIndex() {
        return nextFirst == 0 ? items.length - 1 : nextFirst - 1;
    }

    private int nextLastIndex() {
        return nextLast == items.length - 1 ? 0 : nextLast + 1;
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int position;

        public ArrayDequeIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            return get(position++);
        }
    }
}
