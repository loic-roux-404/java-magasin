package Controller;

import Exceptions.InternalException;
import Exceptions.ServiceRegisteryException;
import Model.Order;
import Model.Car.Car;

import javax.swing.JOptionPane;

import Model.Client;
import View.OrderView;
import View.SwingModules.Form;
import Services.Entity.EntityManager;
import Framework.Registery;

/**
 * Process order and manage CRUD operations
 */
public class OrderController extends AbstractController {
    private EntityManager entityManager;
    // Associated View
    protected OrderView orderView;

    public OrderController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Order.class);
        this.orderView = new OrderView(this.getLayout(), this);
        this.actions();
    }

    @Override
    public void actions() {
        Form orderForm = this.orderView.orderForm;
        // submit order
        orderForm.submit(e -> {
            Object clientSelected = orderView.getClientSelect().getSelectedItem();
            Object carSelect = orderView.getClientSelect().getSelectedItem();

            if (clientSelected == this.orderView.NO_SELECT || carSelect == this.orderView.NO_SELECT) {
                JOptionPane.showMessageDialog(orderForm.getPanel(), "Sélectionnez un champs", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Client client = (Client) clientSelected;
            Car car = (Car) clientSelected;
            String id = orderView.getId().trim();

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
            this.refresh();
        });
    }
}
