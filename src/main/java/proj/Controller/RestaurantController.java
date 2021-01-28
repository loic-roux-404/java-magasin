package main.java.proj.Controller;

import main.java.proj.Exceptions.FormException;
import main.java.proj.Exceptions.InternalException;
import main.java.proj.Exceptions.PhoneNumberDigitsException;
import main.java.proj.Exceptions.ServiceRegisteryException;
import main.java.proj.Framework.Registery;
import main.java.proj.Model.Restaurant;
import main.java.proj.Services.Entity.EntityManager;
import main.java.proj.View.RestaurantView;
import main.java.proj.View.SwingModules.Theme;

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
