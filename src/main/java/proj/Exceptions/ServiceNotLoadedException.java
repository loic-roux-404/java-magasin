package main.java.proj.Exceptions;

public class ServiceNotLoadedException extends ServiceRegisteryException {
    public ServiceNotLoadedException(String name) {
        super("Not loaded service : ", name);
    }
}
