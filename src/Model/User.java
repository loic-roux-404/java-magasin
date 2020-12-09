package Model;

public class User implements Entity {
    private String firstname;
    private String lastname;

    public User() {
        // empty constructor
    }

    public User(String firstname, String lastname) {
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
}
