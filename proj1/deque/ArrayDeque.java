package deque;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private static final int INITIAL_SIZE = 8;
    private static final int RESIZE_FACTOR = 2;

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
        nextFirst = nextFirstIndex(nextFirst);
        size++;
    }

    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(size * RESIZE_FACTOR);
        }
        items[nextLast] = item;
        nextLast = nextLastIndex(nextLast);
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
        int currentFirst = previousFirstIndex(nextFirst);
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
        int currentLast = previousLastIndex(nextLast);
        T remove = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        size--;
        return remove;
    }

    @Override
    public T get(int index) {
        return (isEmpty() || indexNotInRange(index)) ? null : items[index];
    }

    private boolean isFull() {
        return size == items.length;
    }

    private boolean isSpacious() {
        return (size > INITIAL_SIZE) && (size * 4 < items.length);
    }

    private boolean indexNotInRange(int index) {
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

        int position = firstItemIndex();
        for (int i = 0; i < size; i++) {
            ordered[i] = items[position];
            position = nextItemIndex(position);
        }
        return ordered;
    }

    private int nextFirstIndex(int index) {
        return index == 0 ? items.length - 1 : index - 1;
    }

    private int nextLastIndex(int index) {
        return index == items.length - 1 ? 0 : index + 1;
    }

    private int previousFirstIndex(int index) {
        return index == items.length - 1 ? 0 : index + 1;
    }

    private int previousLastIndex(int index) {
        return index == 0 ? items.length - 1 : index - 1;
    }

    private int firstItemIndex() {
        return previousFirstIndex(nextFirst);
    }

    private int nextItemIndex(int index) {
        return previousFirstIndex(index);
    }
}
