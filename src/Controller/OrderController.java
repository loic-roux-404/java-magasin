package Controller;

import Exceptions.*;
import Framework.Registery;
import Model.Car;
import Model.Client;
import Model.Order;
import Model.Builder;
import Services.Entity.Entity;
import Services.Entity.EntityManager;
import View.Home;
import View.OrderView;
import View.SwingModules.Form;
import View.SwingModules.Theme;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Process order and manage CRUD operations
 */
public class OrderController extends AbstractController {
    public final static String TITLE = "Gestion des commandes";

    public ArrayList<Entity> cars = new ArrayList<>();
    public ArrayList<Entity> clients = new ArrayList<>();
    public ArrayList<Entity> builders = new ArrayList<>();
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
    public void actions() throws InternalException {
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
                Builder availableBuilder = null;

                // Search available builder
                this.builders = this.getEntityManager(Builder.class).getAll();

                for(int i = 0 ; i < this.builders.size() ; i++) {
                	Builder currentBuilder = (Builder) this.builders.get(i);
                	if (currentBuilder.isSupportedCar(car)) {
                		availableBuilder = (Builder) this.builders.get(i);
                		break;
                    }
                }

                if (availableBuilder == null) {
                    throw new InvalidOrderException("Aucun constructeur ne peut gérer votre commande");
                }

                // Add new order
                this.entityManager.add(new Order(client, car, availableBuilder));
                // Reset form after
                this.orderView.orderForm.reset(true);
            } catch (FormException | InvalidOrderException orderException) {
                JOptionPane.showMessageDialog(
                    orderForm.getPanel(),
                    orderException.getMessage(),
                    Theme.dialogErrorTxt,
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            } catch (ServiceRegisteryException serviceRegisteryException) {
                serviceRegisteryException.printStackTrace();
            }
        });

        // Load orders // load users
        orderView.orderForm.list(e -> {
            this.fillList();
        });

        orderView.onValid(e -> {
            try {
                ((Order) orderView.orderList.getEntity()).valid();
            } catch (OrderMutationException exception) {
                this.exceptionPane(exception);
            }
            this.fillList();
        });

        orderView.onCancel(e -> {
            try {
                ((Order) orderView.orderList.getEntity()).cancel();
            } catch (OrderMutationException orderException) {
                this.exceptionPane(orderException);
                return;
            }
            this.fillList();
        });
    }

    protected void fillList() {
        orderView.orderList.getDetails(this.entityManager.getAll());
    }

    protected void exceptionPane(Exception e) {
        JOptionPane.showMessageDialog(
            null,
            e.getMessage(),
            Theme.dialogErrorTxt,
            JOptionPane.ERROR_MESSAGE
        );
    }
}
