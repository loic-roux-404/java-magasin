package Controller;

import Exceptions.FormException;
import Exceptions.InternalException;
import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Model.Car;
import Model.Client;
import Model.Order;
import Services.Entity.Entity;
import Services.Entity.EntityManager;
import View.Home;
import View.OrderView;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Process order and manage CRUD operations
 */
public class OrderController extends AbstractController {
    public final static String TITLE = "Gestion des commandes";

    public ArrayList<Entity> cars = new ArrayList<>();
    public ArrayList<Entity> clients = new ArrayList<>();

    // Dependencies
    private final EntityManager entityManager;
    // Associated View
    private final OrderView orderView;

    public OrderController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Order.class);
        this.orderView = new OrderView(this.getLayout(), this);
        this.actions();
    }

    @Override
    public void actions() throws ServiceRegisteryException {
        this.getLayout().home.page(Home.ORDERS).onOpen(e -> {
            try {
                this.cars = this.getEntityManager(Car.class).getAll();
                this.clients = this.getEntityManager(Client.class).getAll();
            } catch (ServiceRegisteryException serviceRegisteryException) {
                serviceRegisteryException.printStackTrace();
            }
        });

        Form orderForm = this.orderView.orderForm;
        // submit order
        orderForm.submit(e -> {
            try {
                orderForm.validate();
                Client client = (Client) orderView.getClientSelect().getSelectedItem();
                Car car = (Car) orderView.getCarSelect().getSelectedItem();

                this.entityManager.add(new Order(client, car));

                this.orderView.orderForm.reset(true);
            } catch (FormException formException) {
                JOptionPane.showMessageDialog(orderForm.getPanel(), formException.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        });

        // Load orders // load users
        orderView.orderForm.list(e -> {
            orderView.orderList.getDetails(this.entityManager.getAll());
        });
    }
}
