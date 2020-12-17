package Model;

import Services.Entity.Entity;

public class Client implements Entity {
    private int id;

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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Client setId(int id) {
        this.id = id;

        return this;
    }

    @Override
    public String toString() {
        return firstname + ", " + lastname;
    }
}
