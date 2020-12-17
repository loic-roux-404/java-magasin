package Controller;

import Exceptions.ServiceRegisteryException;
import Model.Shop;
import Services.Entity.EntityManager;
import Services.Layout;
import Framework.Registery;

public class ShopController extends AbstractController {
    private EntityManager entityManager;

    // Views
    // private ShopView shopView;

    public ShopController(Registery registery) throws ServiceRegisteryException {
        super(registery);
        this.entityManager = new EntityManager(Shop.class);
        this.actions();
    }

    @Override
    protected void actions() throws ServiceRegisteryException {
        Layout ly = this.getLayout();
    }
}
