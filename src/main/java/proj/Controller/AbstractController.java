package main.java.proj.Controller;

import main.java.proj.Exceptions.FormException;
import main.java.proj.Exceptions.InternalException;
import main.java.proj.Exceptions.ServiceRegisteryException;
import main.java.proj.Framework.Registery;
import main.java.proj.Framework.Service;
import main.java.proj.Services.Entity.EntityManager;
import main.java.proj.Services.Layout;
import main.java.proj.View.SwingModules.PageBtn;

import java.util.HashMap;

/**
 * Core controller to store essential things like :
 * - services : Utils to make specific actions
 * - entity managers : object database operations
 * - Layout : many Swing components and Card layout to switch between views
 * - Home : Store pages
 */
public abstract class AbstractController {
    private Registery registery;
    public HashMap<String, PageBtn> routes = new HashMap<>();

    public AbstractController(Registery registery) {
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

    /**
     * We always need this function
     * Actions are update / delete / list operation on entities, triggered by
     * user form submission and clicks
     *
     * @throws InternalException
     * @throws FormException
     */
    abstract protected void actions() throws InternalException, FormException;
}
