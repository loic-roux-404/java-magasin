package Controller;

import Model.EntityManager;
import Model.Order;
import View.SwingModules.List;
import View.SwingModules.Form;

public class ShopController {
    private EntityManager entityManager;

    private Form form;
    private List list;

    public ShopController(Form orderForm, List orderDetails) {
        this.entityManager = new EntityManager(Order.class);
        this.form = orderForm;
        this.list = orderDetails;
    }
}
