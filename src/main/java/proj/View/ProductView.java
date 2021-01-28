package main.java.proj.View;

import main.java.proj.Controller.ProductController;
import main.java.proj.Controller.RestaurantController;
import main.java.proj.Services.Entity.Entity;
import main.java.proj.View.SwingModules.*;
import main.java.proj.Exceptions.InternalException;
import main.java.proj.Services.Layout;
import main.java.proj.View.SwingModules.Form;

import javax.swing.*;
import java.util.ArrayList;

public class ProductView {
    static String[] tableColumn = {"Référence", "type", "Expiration", "Créer le", "Mis à jour le"};
    static final String LIST = "product_list";
    static final String ADD = "product_add";

    // Core
    public PageBtn carPageBtn = new PageBtn("Ajouter un produit");

    public JTextField type = new JTextField(25);;
    public JTextField expiration = new JTextField(25);
    public JComboBox restaurant = new JComboBox();

    // Components
    public List productList = new List(tableColumn);
    public Form productAdd = this.CREATE();

    public ProductView(Layout ly, ProductController controller) throws InternalException {
        controller.getLayout().addPage(productAdd.getPanel(), ADD, carPageBtn);

        // Home access
        ly.home.page(Home.PRODUCTS).onOpen(e -> {
            ly.openPage(productList, LIST);
            ly.setPageTitle(ProductController.TITLE);
        });
        // Functional
        carPageBtn.onOpen(e -> {
            try {
                this.fillForm(controller.getRestaurants());
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
        // Warning : we open car form from builder
        productList.backButton.onClick(e -> ly.openHome());

        productAdd.getBackButton().onClick(e -> {
            ly.openPage(ly.getPage(RestaurantView.ADD), RestaurantView.ADD);
            ly.setPageTitle(RestaurantController.TITLE);
        });
    }

    public void fillForm(ArrayList<Entity> builders) {
        restaurant.removeAllItems();
        restaurant.addItem(FormBuilder.NO_SELECT);
        // The solution
        for (Entity builder : builders) {
            restaurant.addItem(builder);
        }
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
            .addField("type", type)
            .addField("Expiration", expiration)
            .addField("Restaurant", restaurant)
            .disableListBtn();

        return builder.create(null);
    }
}
