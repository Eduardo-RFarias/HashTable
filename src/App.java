import java.util.ArrayList;
import java.util.Collections;

import data.HashTable;
import model.Person;

public class App {
    public static void main(String[] args) throws Exception {
        HashTable<Person> hashTable = new HashTable<>();

        for (int id : getRandomIds(30)) {
            hashTable.add(new Person(id, "Person " + id));
        }

        Person search = hashTable.get(new Person(27, "Person 27")).orElseThrow();

        System.out.println(search);
    }

    private static ArrayList<Integer> getRandomIds(int quantity) {
        ArrayList<Integer> ids = new ArrayList<>(quantity);

        for (int i = 0; i < quantity; i++) {
            ids.add(i);
        }

        Collections.shuffle(ids);

        return ids;
    }
}
