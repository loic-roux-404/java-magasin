package Controller;

import Exceptions.InternalException;
import Exceptions.ServiceRegisteryException;
import Model.Order;
import Model.Car;

import javax.swing.JOptionPane;

import Model.Client;
import Services.Entity.Entity;
import View.OrderView;
import View.SwingModules.Form;
import Services.Entity.EntityManager;
import Framework.Registery;

import java.util.ArrayList;

/**
 * Process order and manage CRUD operations
 */
public class OrderController extends AbstractController {
    public ArrayList<Entity> cars = new ArrayList<>();
    public ArrayList<Entity> clients = new ArrayList<>();
    
 // Dependencies
    private EntityManager entityManager;
    // Associated View
    private OrderView orderView;

    public OrderController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Order.class);
        this.orderView = new OrderView(this.getLayout(), this);
        this.actions();
    }

    @Override
    public void actions() throws ServiceRegisteryException {
        this.getEntityManager(Car.class).add(new Car("Renault", "Megane", 2.5, 1320.0));
        this.getEntityManager(Car.class).add(new Car("Renault", "Laguna", 2.5, 1320.0));

        this.getLayout().home.ordersPage(e -> {
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
            Object clientSelected = orderView.getClientSelect().getSelectedItem();
            Object carSelected = orderView.getCarSelect().getSelectedItem();

            if (clientSelected == this.orderView.NO_SELECT) {
                JOptionPane.showMessageDialog(orderForm.getPanel(), "Sélectionnez un client", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (carSelected == this.orderView.NO_SELECT) {
                JOptionPane.showMessageDialog(orderForm.getPanel(), "Sélectionnez une voiture", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }            

            Client client = (Client) clientSelected;
            Car car = (Car) carSelected;
            String id = orderView.getId().trim();

            System.out.println(id);
            System.out.println(clientSelected.toString());
            System.out.println(carSelected.toString());
            
            // simple validations
            if (client.toString().isEmpty()) {
                JOptionPane.showMessageDialog(orderForm.getPanel(), "Client Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (car.toString().isEmpty()) {
                JOptionPane.showMessageDialog(orderForm.getPanel(), "Car Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (id.isEmpty()) {
                JOptionPane.showMessageDialog(orderForm.getPanel(), "ID Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.entityManager.add(new Order(Integer.parseInt(id), client, car));
            
            this.orderView.orderForm.reset(true);
        });
        
        // Load orders // load users
        orderView.orderForm.list(e -> {
        	orderView.orderList.getDetails(this.entityManager.getAll());
        });
    }
}
