package Controller;

import Model.Order;
import Model.Car.Car;

import javax.swing.JOptionPane;

import Model.Client;
import Model.EntityManager;
import View.SwingModules.List;
import View.OrderView;
import View.SwingModules.Form;

/**
 * Process order and manage CRUD operations
 */
public class OrderController {
    private EntityManager entityManager;

    private Form orderForm;
    private List orderDetails;
    
    public OrderController(Form orderForm, List orderDetails, OrderView orderView) {
        this.entityManager = new EntityManager(Order.class);
        this.orderForm = orderForm;
        this.orderDetails = orderDetails;
        
        // submit order
        this.orderForm.submit(e -> {
        	Client client = (Client)orderView.getClientList().getSelectedItem();
            Car car = (Car)orderView.getCarList().getSelectedItem();
            String id = orderView.getId().trim();

            // simple validations
            if (client.toString().isEmpty()) {
                JOptionPane.showMessageDialog(this.orderForm.getPanel(), "Client Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (car.toString().isEmpty()) {
                JOptionPane.showMessageDialog(this.orderForm.getPanel(), "Car Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this.orderForm.getPanel(), "ID Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.entityManager.add(new Order(Integer.parseInt(id), client, car));
            this.entityManager.save();
            this.orderForm.reset(true);
        	
        });
    }
    

}
