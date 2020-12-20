package Model;

public class Client extends AbstractActor {
    private String firstname;

    /**
     * Default constructor useful in some cases
     */
    public Client() {}

    public Client(String firstname, String name) {
        super(name);
        this.firstname = firstname;
    }

    // getters
    public String getFirstname() {
        return firstname;
    }

    // setters

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString(boolean list) {
        return firstname + ", " + this.getName();
    }

    @Override
    public String toString() {
        return this.toString(true);
    }
}
