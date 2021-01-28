package main.java.proj.Controller;

import main.java.proj.Exceptions.FormException;
import main.java.proj.Exceptions.InternalException;

import main.java.proj.Framework.Registery;
import main.java.proj.Model.Product;
import main.java.proj.Model.Restaurant;
import main.java.proj.Services.Entity.Entity;
import main.java.proj.Services.Entity.EntityManager;
import main.java.proj.View.Home;
import main.java.proj.View.ProductView;
import main.java.proj.View.SwingModules.Theme;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class ProductController extends AbstractController {
    public final static String TITLE = "Catalogue des Produits";
    public final static String TITLE_ADD = "Ajouter un produit";

    private final EntityManager entityManager;
    private final ProductView productView;

    protected static String NUMBER_ERROR = "Le nombre choisi n'est pas le bon, vérifiez la présence de virgule";

    public ProductController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Product.class);
        productView = new ProductView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws InternalException {

        productView.productAdd.submit(e -> {
            try {
                productView.productAdd.validate();
                SimpleDateFormat frt = new SimpleDateFormat("MM/dd/yyyy");
                Date expiration = frt.parse(productView.expiration.getText());
                Restaurant restaurant = (Restaurant) productView.restaurant.getSelectedItem();
                Product car = new Product(
                    productView.type.getText(),
                    expiration
                );
                this.entityManager.add(car);
                restaurant.addRestaurant(car);
                productView.productAdd.reset(true);
            } catch (FormException formException) {
                this.orderDialog(formException.getMessage());
                return;
            } catch (NumberFormatException numExeption) {
                this.orderDialog(NUMBER_ERROR);
                return;
            } catch (ParseException parseException) {
                this.orderDialog("Erreur dans le rensignement de la date, il faut : MM/dd/yyyy");

            }
        });

        this.getLayout().home.page(Home.PRODUCTS).onOpen(e -> {
            productView.productList.getDetails(this.entityManager.getAll());
        });
    }

    public ArrayList<Entity> getRestaurants() throws InternalException {
        return this.getEntityManager(Restaurant.class).getAll();
    }

    protected void orderDialog(String txt) {
        JOptionPane.showMessageDialog(productView.productAdd.getPanel(), txt, Theme.dialogErrorTxt,
            JOptionPane.ERROR_MESSAGE);
    }
}