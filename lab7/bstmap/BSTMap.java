package bstmap;

import java.util.Iterator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;
    private int size;x

    private class BSTNode{
        private K key;
        private V val;
        private BSTNode left, right;

        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     *  Initializes an empty BSTMap
     */
    public BSTMap() {
    }

    /**
     *  Prints out BSTMap in order of increasing Key (for debugging).
     */
    public void printInOrder() {
        Queue<BSTNode> nodes = traversal();
        for (BSTNode node : nodes) {
            System.out.println(node.key);
        }
    }

    /**
     *  In-Order Traversal of the BST,
     *  returns a queue of all nodes (for debugging).
     */
    public Queue<BSTNode> traversal(){
        Queue<BSTNode> queue = new LinkedList<>();
        traversal(root, queue);
        return queue;
    }

    private void traversal(BSTNode node, Queue<BSTNode> queue) {
        if (node == null) {
            return;
        }

        traversal(node.left, queue);
        queue.add(node);
        traversal(node.right,queue);
    }

    /**
     *  Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     *  Returns true if this map contains a mapping for the specified key, false otherwise.
     */
    @Override
    public boolean containsKey(K key) {
        return find(root, key) != null;
    }

    /**
     *  Returns the value to which the specified key is mapped,
     *  or null if this map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        BSTNode node = find(root, key);
        if (node != null) {
            return node.val;
        }
        return null;
    }

    private BSTNode find(BSTNode node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    /**
     *  Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *  Associates the specified value with the specified key in this map.
     */
    @Override
    public void put(K key, V val) {
        root = put(key, val, root);
        size++;
    }

    private BSTNode put(K key, V val, BSTNode node) {
        if (node == null) {
            return new BSTNode(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.val = val;
        } else if (cmp < 0) {
            node.left = put(key, val, node.left);
        } else {
            node.right = put(key, val, node.right);
        }
        return node;
    }

    /**
     *  Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (BSTNode node : traversal()) {
            keySet.add(node.key);
        }
        return keySet;
    }

    /**
     *  Removes the mapping for the specified key from this map if present.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     *  Removes the entry for the specified key only if it is currently mapped to the specified value.
     */
    @Override
    public V remove(K key, V val) {
        throw new UnsupportedOperationException();
    }

    /**
     *  Returns an iterator that iterates over the keys of the map.
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }

    /**
     * An iterator that iterates over the keys of the map.
     */
    private class BSTMapIter implements Iterator<K> {

        // Stores the current key-value pair
        private Queue<K> keys;

        /**
         *  Create a new BSTMapIter by adding all keys into a queue in ascending order.
         */
        public BSTMapIter() {
            for (BSTNode node : traversal()) {
                keys.add(node.key);
            }
        }

        @Override
        public boolean hasNext() {
            return keys.peek() != null;
        }

        @Override
        public K next() {
            return keys.remove();
        }
    }
}
