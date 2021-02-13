package com.app.Controller;

import com.app.Exceptions.FormException;
import com.app.Exceptions.InternalException;
import com.app.Framework.Registery;
import com.app.Model.Article;
import com.app.Model.Magasin;
import com.app.Services.EntityManagerProxy;
import com.app.Services.IEntity;
import com.app.View.Home;
import com.app.View.ProductView;
import com.app.View.SwingModules.Theme;

import javax.swing.*;
import java.util.List;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class ArticleController extends AbstractController {
    public final static String TITLE = "Catalogue des Produits";
    public final static String TITLE_ADD = "Ajouter un produit";

    private final EntityManagerProxy entityManager;
    private final ProductView productView;

    protected static String NUMBER_ERROR = "Le format du nombre n'est pas correct";

    public ArticleController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManagerProxy(Article.class);
        productView = new ProductView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws InternalException {

        productView.productAdd.submit(e -> {
            try {
                productView.productAdd.validate();
                Magasin magasin = (Magasin) productView.magasin.getSelectedItem();
                Article leProduit = new Article(
                    productView.intitule.getText(),
                    Float.parseFloat(productView.prixHT.getText()),
                    Integer.parseInt(productView.qteStock.getText())
                );
                this.entityManager.add(leProduit);
                magasin.addArticle(leProduit);
                productView.productAdd.reset(true);
            } catch (FormException formException) {
                this.orderDialog(formException.getMessage());
                return;
            } catch (NumberFormatException numExeption) {
                this.orderDialog(NUMBER_ERROR);
                return;
            }
        });

        this.getLayout().home.page(Home.PRODUCTS).onOpen(e -> {
            productView.productTableList.getDetails(this.entityManager.getAll());
        });
    }

    public List<IEntity> getMagasins() throws InternalException {
        return this.getEntityManagerProxy(Magasin.class).getAll();
    }

    protected void orderDialog(String txt) {
        JOptionPane.showMessageDialog(productView.productAdd.getPanel(), txt, Theme.dialogErrorTxt,
            JOptionPane.ERROR_MESSAGE);
    }
}