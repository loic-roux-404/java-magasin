package Controller;

import Model.Order;
import Model.EntityManager;
import View.SwingModules.List;
import View.SwingModules.Form;

/**
 * Process order and manage CRUD operations
 */
public class OrderController {
    private EntityManager entityManager;

    private Form orderForm;
    private List orderDetails;

    public OrderController(Form orderForm, List orderDetails) {
        this.entityManager = new EntityManager(Order.class);
        this.orderForm = orderForm;
        this.orderDetails = orderDetails;
    }
}
