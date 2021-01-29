package com.app.View;

import com.app.Controller.MagasinController;
import com.app.Services.Entity.Entity;
import com.app.Services.Layout;
import com.app.View.SwingModules.FormBuilder;
import com.app.Controller.ArticleController;
import com.app.Exceptions.InternalException;
import com.app.View.SwingModules.Form;
import com.app.View.SwingModules.List;
import com.app.View.SwingModules.PageBtn;

import javax.swing.*;
import java.util.ArrayList;

public class ProductView {
    static String[] tableColumn = {"Référence", "Nom", "PrixHT", "PrixTTC", "Quantitée"};
    static final String LIST = "product_list";
    static final String ADD = "product_add";

    // Core
    public PageBtn carPageBtn = new PageBtn("Ajouter un produit");

    public JTextField reference = new JTextField(25);
    public JTextField intitule = new JTextField(25);
    public JTextField prixHT = new JTextField(25);
    public JTextField qteStock = new JTextField(25);
    
    public JComboBox magasin = new JComboBox();

    // Components
    public List productList = new List(tableColumn);
    public Form productAdd = this.CREATE();

    public ProductView(Layout ly, ArticleController controller) throws InternalException {
        controller.getLayout().addPage(productAdd.getPanel(), ADD, carPageBtn);

        // Home access
        ly.home.page(Home.PRODUCTS).onOpen(e -> {
            ly.openPage(productList, LIST);
            ly.setPageTitle(ArticleController.TITLE);
        });
        // Functional
        carPageBtn.onOpen(e -> {
            try {
                this.fillForm(controller.getRestaurants()); // @todo transformer en magasin
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
        // Warning : we open car form from builder
        productList.backButton.onClick(e -> ly.openHome());

        productAdd.getBackButton().onClick(e -> {
            ly.openPage(ly.getPage(MagasinView.ADD), MagasinView.ADD);
            ly.setPageTitle(MagasinController.TITLE);
        });
    }

    public void fillForm(ArrayList<Entity> builders) {
        magasin.removeAllItems(); 
        magasin.addItem(FormBuilder.NO_SELECT);
        // The solution
        for (Entity builder : builders) {
        	magasin.addItem(builder);
        }
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
            .addField("Reference", reference)
            .addField("Intitule", intitule)
            .addField("PrixHT", prixHT)
            .addField("Quantitée Stock", qteStock)
            .addField("Magasin", magasin)
            .disableListBtn();

        return builder.create(null);
    }
}
