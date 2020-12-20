package Controller;

import Exceptions.FormException;
import Exceptions.InternalException;

import Framework.Registery;
import Model.Builder;
import Model.Car;
import Services.Entity.Entity;
import Services.Entity.EntityManager;
import View.CarView;
import View.Home;

import javax.swing.*;
import java.util.ArrayList;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class CarController extends AbstractController {
    public final static String TITLE = "Catalogue des Bagnoles";
    public final static String TITLE_ADD = "Gestion des Bagnoles";

    private final EntityManager entityManager;
    private final CarView carView;

    // data
    public ArrayList<Entity> builders;

    public CarController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Car.class);
        carView = new CarView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws InternalException {
        builders = this.getBuilders();

        carView.carAdd.submit(e -> {
            try {
                carView.carAdd.validate();
                Builder builder = (Builder) carView.builderSelect.getSelectedItem();
                Car car = new Car(
                    carView.modelName.getText(),
                    carView.brandName.getText(),
                    (Integer) carView.length.getValue(),
                    (Integer) carView.weight.getValue()
                );
                this.entityManager.add(car);
                builder.addCar(car);
                carView.carAdd.reset(true);
            } catch (FormException formException) {
                JOptionPane.showMessageDialog(carView.carAdd.getPanel(), formException.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        });

        this.getLayout().home.page(Home.CARS).onOpen(e -> {
            carView.carList.getDetails(this.entityManager.getAll());
        });
    }

    protected ArrayList<Entity> getBuilders() throws InternalException {
        return this.getEntityManager(Builder.class).getAll();
    }
}
