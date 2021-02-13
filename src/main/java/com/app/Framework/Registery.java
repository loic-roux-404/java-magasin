package com.app.Framework;

import com.app.Exceptions.NotLoadedException;
import com.app.Exceptions.RegisteryException;

import java.util.HashMap;

public class Registery {

    public HashMap<String, Service> services = new HashMap<>();

    public Registery(HashMap<String, Service> services) {
        this.services = services != null ? services : new HashMap<>();
    }

    public Registery add(String name, Service service) throws NotLoadedException {

        if (!service.isLoaded()) {
            throw new NotLoadedException(name);
        }

        this.services.put(name, service);

        return this;
    }

    public Service get(String name) throws RegisteryException {
        try {
            return services.get(name);
        } catch (Exception e) {
            throw new RegisteryException(name, e.getMessage());
        }
    }

    public boolean has(String name) {
        return services.containsKey(name);
    }
}
