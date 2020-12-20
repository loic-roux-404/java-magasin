package Model;

import Services.Entity.Entity;

public class Order implements Entity {

    private int id;
    private statuses status;
    private Car car;
    private Builder builder;
    private Client client;

    static enum statuses {
        PENDING,
        PROCESSING,
        DONE,
        CANCELLED,
    }

    /**
     * Default constructor, used by entityManager
     */
    public Order() {

    }

    public Order(Client client, Car car, Builder builder) {
        this.client = client;
        this.car = car;
        this.builder = builder;
        status = statuses.valueOf("PENDING");
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Order setId(int id) {
        this.id = id;

        return this;
    }

    public statuses getStatus() {
        return status;
    }

    public void setStatus(statuses status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString(boolean list) {
        return id +
            ", " + status +
            ", " + client.toString() +
            ", " + car.getBrandName() +
            ", " + car.getModelName() +
            ", " + builder.toString();
    }

    @Override
    public String toString() {
        return id +
            ", " + client.toString() +
            ", " + car.getModelName() +
            ", " + builder.getName();
    }
}
