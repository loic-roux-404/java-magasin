package Exceptions;

import Model.Order;

public class UnavailbaleOrderException extends InternalException {
    public UnavailbaleOrderException(Order order) {
        super(
            "Problème avec la commande " + order.getId() +
                "\n"
                + "pour le client " + order.getClient().getFirstname()
                + " " + order.getClient().getLastname() + "\n"
                + " " + order.getCar().getBrandName()
                + " " + order.getCar().getModelName()
        );
    }
}
