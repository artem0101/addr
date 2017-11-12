package Interfaces;

import Objects.Person;

/**
 * Created by artem on 08.07.2017.
 */
public interface AddressBook {
    void add(Person person);

    void update(Person person);

    void delete(Person person);
}
