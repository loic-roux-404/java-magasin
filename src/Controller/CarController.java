package Controller;

import Exceptions.InternalException;
import Framework.Registery;
import Model.Client;
import Services.Entity.EntityManager;
import View.CarView;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class CarController extends AbstractController {
    private final EntityManager entityManager;

    private final CarView carView;

    public CarController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Client.class);
        carView = new CarView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws InternalException {

    }
}
