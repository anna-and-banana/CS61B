package deque;

public class LinkedListDeque<T> implements Deque<T> {

    public class Node {
        private T item;
        private Node previous;
        private Node next;

        public Node(T item, Node previous, Node next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    // Creates an empty linked list deque.
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        // sentinel node first ...
        // Make the node point to sentinel and first
        Node first = sentinel.next;
        Node p = new Node(item, sentinel, first);
        // Make sentinel and first point to the node
        first.previous = p;
        sentinel.next = p;
        // Increase size
        size++;
    }

    @Override
    public void addLast(T item) {
        // sentinel ... last node
        // Make the node point to sentinel and last
        Node last = sentinel.previous;
        Node p = new Node(item, last, sentinel);
        // Make sentinel and last point to the node
        last.next = p;
        sentinel.previous = p;
        // Increase size
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        // Print until reached the second to last
        Node p = sentinel.next;
        Node last = sentinel.previous;
        while (p != last) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        // Print the last one
        System.out.println(last.item);
    }

    @Override
    public T removeFirst() {
        // sentinel first second ...
        if (size > 0) {
            Node first = sentinel.next;
            Node second = first.next;

            sentinel.next = second;
            second.previous = sentinel;

            size--;

            return first.item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        // sentinel ... secondToLast last
        if (size > 0) {
            Node last = sentinel.previous;
            Node secondToLast = last.previous;

            sentinel.previous = secondToLast;
            secondToLast.next = sentinel;

            size--;

            return last.item;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index < size) {
            Node p = sentinel;
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
            return p.item;
        }
        return null;
    }

    public T getRecursive(int index) {
        T item = null;
        if (index >= 0 && index < size) {
            Node p = sentinel.next;
            item = getRecursiveHelper(index, p);
        }
        return item;
    }

    private T getRecursiveHelper(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(--index, p.next);
    }
}
