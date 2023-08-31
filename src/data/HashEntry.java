package data;

public class HashEntry<T> {
    private final int key;
    private final T value;

    public HashEntry(T value) {
        this.key = value.hashCode();
        this.value = value;
    }

    public int getKey() {
        return this.key;
    }

    public T getValue() {
        return this.value;
    }
}
