package Model;

import Services.Entity.Entity;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Client implements Entity {

    private String firstname;
    private String lastname;

    public Client() {
        // empty constructor
    }

    public Client(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // getters
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String toString() {
        return firstname + ", " + lastname;
    }

    @Override
    public Entity factory(String[] dbData) {

        System.out.println(dbData);
        return new Client();
    }
}
