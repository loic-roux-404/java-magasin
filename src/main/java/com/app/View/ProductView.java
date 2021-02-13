package com.app.View;

import com.app.Controller.MagasinController;
import com.app.Services.IEntity;
import com.app.Services.Layout;
import com.app.View.SwingModules.FormBuilder;
import com.app.Controller.ArticleController;
import com.app.Exceptions.InternalException;
import com.app.View.SwingModules.Form;
import com.app.View.SwingModules.TableList;
import com.app.View.SwingModules.PageBtn;

import javax.swing.*;
import java.util.List;

public class ProductView {
    static String[] tableColumn = {"Référence", "Nom", "PrixHT", "PrixTTC", "Quantitée"};
    static final String LIST = "product_list";
    static final String ADD = "product_add";

    // Core
    public PageBtn carPageBtn = new PageBtn("Ajouter un produit");

    public JTextField intitule = new JTextField(25);
    public JTextField prixHT = new JTextField(25);
    public JTextField qteStock = new JTextField(25);
    
    public JComboBox magasin = new JComboBox();

    // Components
    public TableList productTableList = new TableList(tableColumn);
    public Form productAdd = this.CREATE();

    public ProductView(Layout ly, ArticleController controller) throws InternalException {
        controller.getLayout().addPage(productAdd.getPanel(), ADD, carPageBtn);

        // Home access
        ly.home.page(Home.PRODUCTS).onOpen(e -> {
            ly.openPage(productTableList, LIST);
            ly.setPageTitle(ArticleController.TITLE);
        });
        // Functional
        carPageBtn.onOpen(e -> {
            try {
                this.fillForm(controller.getMagasins());
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
        // Warning : we open car form from builder
        productTableList.backButton.onClick(e -> ly.openHome());

        productAdd.getBackButton().onClick(e -> {
            ly.openPage(ly.getPage(MagasinView.ADD), MagasinView.ADD);
            ly.setPageTitle(MagasinController.TITLE);
        });
    }

    public void fillForm(List<IEntity> builders) {
        magasin.removeAllItems(); 
        magasin.addItem(FormBuilder.NO_SELECT);
        // The solution
        for (IEntity builder : builders) {
        	magasin.addItem(builder);
        }
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
            .addField("Intitule", intitule)
            .addField("PrixHT", prixHT)
            .addField("Quantitée Stock", qteStock)
            .addField("Magasin", magasin)
            .disableListBtn();

        return builder.create(null);
    }
}
