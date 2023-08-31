package data;

import java.util.Optional;

public class HashTable<T> {
    private final HashEntry<T>[] array;
    private final int maxSize;
    private final int maxCollisions;

    private int size = 0;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.maxSize = 151;
        this.maxCollisions = 10;

        this.array = new HashEntry[maxSize];

        for (int i = 0; i < maxSize; i++) {
            this.array[i] = null;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.maxSize = size;
        this.maxCollisions = 10;

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

    public void add(T value) {
        if (size == maxSize) {
            throw new RuntimeException("Max size reached");
        }

        int key = value.hashCode();
        int hash = key % maxSize;
        int collisions = 0;
        int chosenLocation = -1;

        while (collisions < maxCollisions) {
            // if the given position in the array is not empty, check if the key is the same
            // as the searched key
            if (array[hash] != null && array[hash].getKey() == key) {
                throw new RuntimeException("Key already exists");
            }

            // if the given position in the array is empty, and the chosen location is not
            // set, set the chosen location to the current position
            if (array[hash] == null && chosenLocation == -1) {
                chosenLocation = hash;
            }

            // keep searching until we find that the key is already in the array or we reach
            // the max number of collisions
            hash = (hash + 1) % maxSize;
            collisions++;
        }

        // if the loop ends before finding an empty location then the max number of
        // collisions was reached
        if (chosenLocation == -1) {
            throw new RuntimeException("Max collisions reached");
        }

        // if the code reaches this point, then the value can be inserted
        // in the chosen location.
        array[chosenLocation] = new HashEntry<>(value);
        size++;
    }

    public Optional<T> get(T value) {
        int key = value.hashCode();
        int hash = key % maxSize;
        int collisions = 0;

        while (collisions < maxCollisions) {
            // if the key exists, check if the value's key is the same as the searched key
            // if it is, return the value
            if (array[hash] != null && array[hash].getKey() == key) {
                T currentValue = array[hash].getValue();
                return Optional.of(currentValue);
            }

            // if the key doesn't exist or the value's key is different from the searched
            // key, keep searching
            hash = (hash + 1) % maxSize;
            collisions++;
        }

        // if the loop ends before finding the key, then the key doesn't exist
        return Optional.empty();
    }

    public int getMaxCollisions() {
        return maxCollisions;
    }

    public int getSize() {
        return size;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
