package Controller;

import Model.EntityManager;
import View.AbstractDetails;
import View.Client.ClientForm;
import View.Order.OrderDetails;
import View.Order.OrderForm;

/**
 * Process order and manage CRUD operations
 */
public class OrderController {
    static String databaseFile = "orders_db.txt";
    private EntityManager entityManager;

    private OrderForm orderForm;
    private OrderDetails orderDetails;

    public OrderController(OrderForm orderForm, OrderDetails orderDetails) {
        this.entityManager = new EntityManager();
        this.orderForm = orderForm;
        this.orderDetails = orderDetails;
    }
}
