package Controller;

import Model.EntityManager;
import Model.Car.Car;
import View.SwingModules.List;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class CarController {
    private EntityManager entityManager;

    private List list;

    public CarController(List list) {
        this.entityManager = new EntityManager(Car.class);
        // Handle actions here and views data recuperation
        this.list = list;
    }
}
