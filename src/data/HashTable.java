package data;

import java.util.Optional;

public class HashTable<T> {
    private final HashEntry<T>[] array;
    private int size = 0;

    private int maxSize = 128;
    private int maxCollisions = 10;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.array = new HashEntry[maxSize];

        for (int i = 0; i < maxSize; i++) {
            this.array[i] = null;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.maxSize = size;
        this.array = new HashEntry[size];

        for (int i = 0; i < size; i++) {
            this.array[i] = null;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTable(int size, int maxCollisions) {
        this.maxSize = size;
        this.maxCollisions = maxCollisions;
        this.array = new HashEntry[size];

        for (int i = 0; i < size; i++) {
            this.array[i] = null;
        }
    }

    public void setMaxCollisions(int maxCollisions) {
        this.maxCollisions = maxCollisions;
    }

    public void put(T value) {
        if (size == maxSize) {
            throw new RuntimeException("Max size reached");
        }

        for (int hash = value.hashCode() % maxSize,
                collisions = 0; collisions < maxCollisions; hash = (hash + 1) % maxSize, collisions++) {
            if (array[hash] == null) {
                array[hash] = new HashEntry<T>(value);
                size++;
                return;
            }
        }

        throw new RuntimeException("Max collisions reached");
    }

    public Optional<T> get(T value) {
        int key = value.hashCode();

        for (int hash = key % maxSize,
                collisions = 0; collisions < maxCollisions; hash = (hash + 1) % maxSize, collisions++) {
            if (array[hash] != null && array[hash].getKey() == key) {
                return Optional.of(array[hash].getValue());
            }
        }

        return Optional.empty();
    }
}
