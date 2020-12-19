package Controller;

import Exceptions.InternalException;
import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Framework.Service;
import Services.Entity.EntityManager;
import Services.Layout;
import View.MainFrame;
import View.SwingModules.PageBtn;

import java.util.HashMap;

public abstract class AbstractController {
    private Registery registery;
    public HashMap<String, PageBtn> routes = new HashMap<>();

    public AbstractController(Registery registery) throws ServiceRegisteryException {
        setRegistery(registery);
    }

    public EntityManager getEntityManager(Class entityClass) throws ServiceRegisteryException {
        // Init first services
        String name = entityClass.getSimpleName();
        return (EntityManager) (this.registery.has(name)
            ? this.registery.get(name)
            : this.registery.add(name, new EntityManager(entityClass)).get(name));
    }

    public Layout getLayout() throws ServiceRegisteryException {
        return (Layout) this.getService(Layout.NAME);
    }

    public Service getService(String name) throws ServiceRegisteryException {
        return this.registery.get(name);
    }

    public Registery getRegistery() {
        return registery;
    }

    public void setRegistery(Registery registery) {
        this.registery = registery;
    }

    abstract protected void actions() throws InternalException;
}
