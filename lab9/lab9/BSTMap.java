package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key == null) {
            throw new IllegalArgumentException("Empty argument not allowed");
        }
        if (p.key.equals(key)) {
            return p.value;
        }
        if (key.compareTo(p.key) > 0) {
            if (p.right != null) {
                return getHelper(key, p.right);
            }
        } else {
            if (p.left != null) {
                return getHelper(key, p.left);
            }
        }
        return null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        Node newNode = new Node(key, value);
        if (p == null) {
            root = newNode;
            size += 1;
            return root;
        }
        if (p.key.equals(key)) {
            p.value = value;
            return p;
        } else if (key.compareTo(p.key) > 0) {
            if (p.right != null) {
                return putHelper(key, value, p.right);
            } else {
                p.right = newNode;
                size += 1;
                return p;
            }
        } else {
            if (p.left != null) {
                return putHelper(key, value, p.left);
            } else {
                p.left = newNode;
                size += 1;
                return p;
            }
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Null key not allowed.");
        }
        if (value == null) {
            throw new IllegalArgumentException("Null values not allowed.");
        }
        putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /** Traverse the tree */
    private void traveralTree(Node start, Set<K> storage) {
        assert start != null;
        storage.add(start.key);
        if (start.left != null) {
            traveralTree(start.left, storage);
        }
        if (start.right != null) {
            traveralTree(start.right, storage);
        }
    }
    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> mapSet = new HashSet<>();
        traveralTree(root, mapSet);
        return mapSet;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
    }
}
