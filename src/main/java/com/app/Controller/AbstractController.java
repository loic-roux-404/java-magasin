package com.app.Controller;

import com.app.Exceptions.RegisteryException;
import com.app.Framework.Service;
import com.app.Services.EntityManagerProxy;
import com.app.Services.Layout;
import com.app.View.SwingModules.PageBtn;
import com.app.Exceptions.FormException;
import com.app.Exceptions.InternalException;
import com.app.Framework.Registery;

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

    public EntityManagerProxy getEntityManagerProxy(Class entityClass) throws RegisteryException {
        // Init first services
        String name = entityClass.getSimpleName();
        return (EntityManagerProxy) (this.registery.has(name)
            ? getService(name)
            : this.registery
                .add(name, new EntityManagerProxy(entityClass))
                .get(name))
            ;
    }

    public Layout getLayout() throws RegisteryException {
        return (Layout) this.getService(Layout.NAME);
    }

    public Service getService(String name) throws RegisteryException {
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
     * @throws InternalException Internal Exception
     * @throws FormException Form Exception
     */
    abstract protected void actions() throws InternalException, FormException;
}
