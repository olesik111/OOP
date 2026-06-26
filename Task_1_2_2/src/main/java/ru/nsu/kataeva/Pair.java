package ru.nsu.kataeva;

import java.util.Map;
import java.util.Objects;

/**
 * Key-value pair.
 */
public class Pair<K, V> implements Map.Entry<K, V> {

    private final K key;
    private V value;

    /**
     * Creates a new key-value pair.
     *
     * @param key key.
     * @param value value.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key for this pair.
     */
    @Override
    public K getKey() {
        return this.key;
    }

    /**
     * Returns the value for this pair.
     */
    @Override
    public V getValue() {
        return this.value;
    }

    /**
     * Sets the value for this pair.
     *
     * @param value the new value to set.
     */
    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    /**
     * Compares the key with another key using equals.
     *
     * @param otherKey the key to compare with.
     */
    public boolean keyEquals(K otherKey) {
        return Objects.equals(key, otherKey);
    }
}
