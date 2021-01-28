package main.java.proj.View;

import main.java.proj.Controller.MagasinController;
import main.java.proj.View.SwingModules.*;
import main.java.proj.Controller.ProductController;
import main.java.proj.Exceptions.InternalException;
import main.java.proj.Services.Layout;
import main.java.proj.View.SwingModules.List;

import javax.swing.*;
import java.awt.*;

public class MagasinView {
    static final String ADD = "magasin_add";
    static final String LIST = "magasin_list";
    // Entity fields to show
    static String[] tableColumn = {"Numéro", "Téléphone", "Adresse", "Code Postal", "Articles"};

    // Form fields
    private JTextField phoneNumber = new JTextField(25);
    private JTextField address = new JTextField(25);
    private JSpinner postalCode = (new NumberField()).getField();

    // Paging
    private PageBtn articlePageBtn;
    // Components
    public Form magasinCreateForm;
    public List builderList;

    public MagasinView(Layout ly, MagasinController controller) throws HeadlessException, InternalException {
        // Recuperate the page object
        articlePageBtn = controller.getLayout().home.page(ProductView.ADD);

        magasinCreateForm = this.CREATE();
        builderList = this.LIST();

        magasinCreateForm.getBackButton().onClick(e -> ly.openHome());
        // Home access
        ly.home.page(Home.MAGASINS).onOpen(e -> {
            ly.openPage(magasinCreateForm.getPanel(), ADD);
            ly.setPageTitle(MagasinController.TITLE);
        });
        // switch view according to its constraints on click
        controller.getLayout().home.page(ProductView.ADD).onOpen(e -> {
            ly.openPage(ly.getPage(ProductView.ADD), ProductView.ADD);
            ly.setPageTitle(ProductController.TITLE_ADD);
        });
        magasinCreateForm.list(e -> {
            ly.openPage(builderList, LIST);
            ly.setPageTitle(MagasinController.TITLE);
        });
        builderList.backButton.onClick(e -> {
            ly.openPage(magasinCreateForm.getPanel(), ADD);
            ly.setPageTitle(MagasinController.TITLE);
        });
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
            .addField("Téléphone", phoneNumber)
            .addField("Adresse", address)
            .addField("Code Postal", postalCode)
            .addButton("Gestion des articles", articlePageBtn.getBtn());

        return builder.create(null);
    }

    public List LIST() {
        return new List(tableColumn);
    }

    public String getPhoneNumber() {
        return phoneNumber.getText();
    }

    public int getPostalCode() {
        return (int) postalCode.getValue();
    }

    public String getAddress() {
        return address.getText();
    }
}