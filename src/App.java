import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import data.HashTable;
import model.Person;

public class App {
    public static void main(String[] args) throws Exception {
        HashTable<Person> hashTable = new HashTable<>();

        List<Integer> randomIds = getRandomIds(100);
        for (int id : randomIds) {
            hashTable.add(new Person(id, "Person " + id));
        }

        var query = new Person(randomIds.get(25), "Person " + randomIds.get(25));

        hashTable.get(query).orElseThrow(() -> new RuntimeException("Get doesn't work"));

        hashTable.remove(query);

        hashTable.get(query).ifPresent((p) -> new RuntimeException("remove doesn't work"));

        System.out.println("Success");
    }

    /**
     * Get a list of N integers in range 1 to 10001.
     */
    private static List<Integer> getRandomIds(int N) {
        ArrayList<Integer> ids = new ArrayList<>(10000);

        for (int i = 1; i < 10001; i++) {
            ids.add(i);
        }

        Collections.shuffle(ids);

        return ids.subList(0, N);
    }
}
