package View;

import Controller.RestaurantController;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.PageBtn;
import Controller.ProductController;
import Exceptions.InternalException;
import Services.Layout;
import View.SwingModules.List;

import javax.swing.*;
import java.awt.*;

public class RestaurantView {
    static final String ADD = "restaurant_add";
    static final String LIST = "restaurant_list";
    // Entity fields to show
    static String[] tableColumn = {"Numéro", "Téléphone", "Adresse", "Produits"};

    // Form fields
    private JTextField phoneNumber = new JTextField(25);

    // Paging
    private PageBtn productPageBtn;
    // Components
    public Form restaurCreateForm;
    public List builderList;

    public RestaurantView(Layout ly, RestaurantController controller) throws HeadlessException, InternalException {
        // Recuperate the page object
        productPageBtn = controller.getLayout().home.page(ProductView.ADD);

        restaurCreateForm = this.CREATE();
        builderList = this.LIST();

        restaurCreateForm.getBackButton().onClick(e -> ly.openHome());
        // Home access
        ly.home.page(Home.RESTAURANTS).onOpen(e -> {
            ly.openPage(restaurCreateForm.getPanel(), ADD);
            ly.setPageTitle(RestaurantController.TITLE);
        });
        // switch view according to its constraints on click
        controller.getLayout().home.page(ProductView.ADD).onOpen(e -> {
            ly.openPage(ly.getPage(ProductView.ADD), ProductView.ADD);
            ly.setPageTitle(ProductController.TITLE_ADD);
        });
        restaurCreateForm.list(e -> {
            ly.openPage(builderList, LIST);
            ly.setPageTitle(RestaurantController.TITLE);
        });
        builderList.backButton.onClick(e -> {
            ly.openPage(restaurCreateForm.getPanel(), ADD);
            ly.setPageTitle(RestaurantController.TITLE);
        });
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
            .addField("téléphone", phoneNumber)
            .addButton("Gestion des produits", productPageBtn.getBtn());

        return builder.create(null);
    }

    public List LIST() {
        return new List(tableColumn);
    }

    public String getPhoneNumber() {
        return phoneNumber.getText();
    }
}