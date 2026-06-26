package ru.nsu.kataeva;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Iterator implementation for HashTable.
 */
public class MyIterator<K, V> implements Iterator<Map.Entry<K, V>> {

    private final HashTable<K, V> hashTable;
    private final int expectedModCount;
    private int bucketIndex;
    private Iterator<Pair<K, V>> bucketIterator;

    /**
     * Creates iterator for the given hash table.
     *
     * @param hashTable the table to iterate.
     */
    public MyIterator(HashTable<K, V> hashTable) {
        this.hashTable = hashTable;
        this.expectedModCount = hashTable.getModCount();
        this.bucketIndex = -1;
        this.bucketIterator = null;
        advanceBucket();
    }

    /**
     * Advances to the next non-empty bucket in the table.
     */
    private void advanceBucket() {
        while ((bucketIterator == null || !bucketIterator.hasNext())
                && ++bucketIndex < hashTable.getTable().length) {
            LinkedList<Pair<K, V>> bucket = hashTable.getTable()[bucketIndex];
            if (bucket != null) {
                bucketIterator = bucket.iterator();
            }
        }
    }

    /**
     * Checks if there are more elements to iterate.
     */
    @Override
    public boolean hasNext() {
        if (expectedModCount != hashTable.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return bucketIterator != null && bucketIterator.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     */
    @Override
    public Map.Entry<K, V> next() {
        if (expectedModCount != hashTable.getModCount()) {
            throw new ConcurrentModificationException();
        }
        if (bucketIterator == null || !bucketIterator.hasNext()) {
            throw new NoSuchElementException();
        }
        Map.Entry<K, V> entry = bucketIterator.next();
        if (!bucketIterator.hasNext()) {
            advanceBucket();
        }
        return entry;
    }
}

