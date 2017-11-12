package Interfaces;

import Objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by artem on 08.07.2017.
 */
public class CollectionAddressBook implements AddressBook {
    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void update(Person person) {
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public void print() {
        int number = 0;
        System.out.println();
        for (Person person : personList) {
            number++;
            System.out.println(number + ") fio - " + person.getFio() + "; phone - " + person.getPhone());
        }
    }

    public void fillTestData() {
        personList.add(new Person("Иван", "23948723948"));
        personList.add(new Person("Роман", "345345345"));
        personList.add(new Person("Антон", "345345345"));
        personList.add(new Person("Джон", "23423423"));
        personList.add(new Person("Джек", "234234"));
        personList.add(new Person("Алиса", "456456"));
        personList.add(new Person("Боб", "34534345"));
    }
}
