package Controller;

import Model.EntityManager;
import View.AbstractDetails;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class CarController {
    static String databaseFile = "cars_db.txt";
    private EntityManager entityManager;

    public CarController(AbstractDetails abstractDetails) {
        this.entityManager = new EntityManager();
        // Handle actions here and views data recuperation
    }
}
