package Controller;

import Exceptions.FormException;
import Exceptions.InternalException;
import Exceptions.PhoneNumberDigitsException;
import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Model.Restaurant;
import Services.Entity.EntityManager;
import View.RestaurantView;
import View.SwingModules.Theme;

import javax.swing.*;

public class RestaurantController extends AbstractController{
    public final static String TITLE = "Gestion des Restaurants";
    private final EntityManager entityManager;

    private RestaurantView restaurantView;

    // Form create, List list
    public RestaurantController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Restaurant.class);
        restaurantView = new RestaurantView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws ServiceRegisteryException {
        // Open list page from create form
        restaurantView.restaurCreateForm.list(e -> {
            restaurantView.builderList.getDetails(this.entityManager.getAll());
        });

        restaurantView.restaurCreateForm.submit(e -> {
            try {
                restaurantView.restaurCreateForm.validate();
                String restaurantPhone = restaurantView.getPhoneNumber().trim();
                if (restaurantPhone.length() != 10) {
                    throw new PhoneNumberDigitsException();
                }
                this.entityManager.add(new Restaurant(restaurantPhone));
            } catch (FormException formException) {
                JOptionPane.showMessageDialog(
                    restaurantView.restaurCreateForm.getPanel(),
                    formException.getMessage(),
                    Theme.dialogErrorTxt,
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            restaurantView.restaurCreateForm.reset(true);
        });
    }
}
