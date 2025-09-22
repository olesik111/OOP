package ru.nsu.kataeva;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * Hash table realization.
 *
 * @param <K> key.
 * @param <V> value.
 */
public class HashTable<K, V> implements Iterable<Map.Entry<K, V>> {

    private static final float LOAD = 0.75f;

    private LinkedList<Pair<K, V>>[] table;
    private int size;
    private int modCount;

    /**
     * Constructor.
     */
    public HashTable() {
        table = new LinkedList[1];
        size = 0;
        modCount = 0;
    }

    /**
     * hash function.
     */
    private int hash(Object key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Resize if no space for new pair.
     */
    private void resize(){
        int newCap = table.length * 2;
        LinkedList<Pair<K, V>>[] newTable = new LinkedList[newCap];

        for (LinkedList<Pair<K, V>> elem : table) {
            if (elem != null) {
                for (Pair<K, V> pair : elem) {
                    int index = (pair.getKey() == null) ? 0 : Math.abs(pair.getKey().hashCode()) % newCap;
                    if (newTable[index] == null) {
                        newTable[index] = new LinkedList<>();
                    }
                    newTable[index].add(pair);
                }
            }
        }
        table = newTable;
    }

    /**
     * Put new value or update it.
     *
     * @param key key.
     * @param value value.
     */
    public void put(K key, V value){
        if ((size + 1) > table.length * LOAD) {
            resize();
        }

        int index = hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Pair<K, V> pair : table[index]) {
            if (pair.keyEquals(key)) {
                pair.setValue(value);
                return;
            }
        }

        table[index].add(new Pair<>(key, value));
        size++;
        modCount++;

    }

    /**
     * Get the value by key.
     *
     * @param key key.
     */
    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Pair<K, V> pair : table[index]) {
                if (pair.keyEquals(key)) {
                    return pair.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Check for certain key.
     *
     * @param key key.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void remove(K key){
        int index = hash(key);
        if (table[index] != null) {
            var iterator = table[index].iterator();
            while (iterator.hasNext()) {
                Pair<K, V> pair = iterator.next();
                if (pair.keyEquals(key)) {
                    iterator.remove();
                    size--;
                    modCount++;
                    return;
                }
            }
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new MyIterator<>(this);
    }

    /**
     * Get table.
     */
    public LinkedList<Pair<K, V>>[] getTable() {
        return table;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HashTable)) return false;

        HashTable<K, V> other = (HashTable<K, V>) obj;
        if (this.size != other.size) return false;

        for (Map.Entry<K, V> entry : this) {
            K key = entry.getKey();
            V value = entry.getValue();
            V otherValue = other.get(key);

            if (!Objects.equals(value, otherValue)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, V> entry : this) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(";\n");
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Modification counter.
     */
    public int getModCount() {
        return modCount;
    }

}
