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
import View.SwingModules.Theme;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class CarController extends AbstractController {
    public final static String TITLE = "Catalogue des Bagnoles";
    public final static String TITLE_ADD = "Gestion des Bagnoles";

    private final EntityManager entityManager;
    private final CarView carView;

    public CarController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Car.class);
        carView = new CarView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws InternalException {

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
                JOptionPane.showMessageDialog(carView.carAdd.getPanel(), formException.getMessage(), Theme.dialogErrorTxt,
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        });

        this.getLayout().home.page(Home.CARS).onOpen(e -> {
            carView.carList.getDetails(this.entityManager.getAll());
        });

        carView.carList.update(e -> {
            if (e.getColumn() <= -1) return;
            int col = e.getColumn();
            int row = e.getFirstRow();
            TableModel model = (TableModel) e.getSource();

            Car en = (Car) this.entityManager.getById(e.getFirstRow());

            switch (col) {
                case 0:
                    en.setBrandName((String) model.getValueAt(row, col));
                    break;
                case 1:
                    en.setModelName((String) model.getValueAt(row, col));
                    break;
                /*
                case 2:
                    en.setLength((Integer) model.getValueAt(row, col));
                    break;
                case 3:
                    en.setWeight((Integer) model.getValueAt(row, col));
                    break;

                 */
            }
        });
    }

    public ArrayList<Entity> getBuilders() throws InternalException {
        return this.getEntityManager(Builder.class).getAll();
    }
}
