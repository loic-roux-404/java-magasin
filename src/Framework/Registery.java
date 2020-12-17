package Framework;

import Exceptions.ServiceNotLoadedException;
import Exceptions.ServiceRegisteryException;

import java.util.HashMap;

public class Registery {

    public HashMap<String, Service> services = new HashMap<>();

    public Registery(HashMap<String, Service> services) {
        this.services = services != null ? services : new HashMap<>();
    }

    public Registery add(String name, Service service) throws ServiceNotLoadedException {

        if (!service.isLoaded()) {
            throw new ServiceNotLoadedException(name);
        }

        this.services.put(name, service);

        return this;
    }

    public Service get(String name) throws ServiceRegisteryException {
        try {
            return services.get(name);
        } catch (Exception e) {
            throw new ServiceRegisteryException(name, e.getMessage());
        }
    }

    public boolean has(String name) {
        return services.containsKey(name);
    }
}
