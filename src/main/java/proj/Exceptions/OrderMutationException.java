package main.java.proj.Exceptions;

public class OrderMutationException extends Exception{
    public OrderMutationException() {
        super("Impossible de changer une commande terminée");
    }
}
