package com.app.Controller;

import com.app.Exceptions.EntityManagerProxyException;
import com.app.Exceptions.FormException;
import com.app.Exceptions.InternalException;
import com.app.Framework.Registery;
import com.app.Model.Article;
import com.app.Model.Magasin;
import com.app.Services.EntityManagerProxy;
import com.app.Services.IEntity;
import com.app.View.Home;
import com.app.View.ProductView;

import java.util.List;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class ArticleController extends AbstractController {
    public final static String TITLE = "Catalogue des Produits";
    public final static String TITLE_ADD = "Ajouter un produit";

    private final EntityManagerProxy articleEntityManager;
    private final ProductView productView;

    protected static String NUMBER_ERROR = "Le format du nombre n'est pas correct";

    public ArticleController(Registery registery) throws InternalException {
        super(registery);
        articleEntityManager = this.getEntityManagerProxy(Article.class);
        productView = new ProductView(this.getLayout(), this);
        actions();
    }

    protected void submitArticleAction() {
        productView.productAdd.submit(e -> {
            try {
                productView.productAdd.validate();
                Magasin magasin = (Magasin) productView.magasin.getSelectedItem();
                Article leProduit = new Article(
                    productView.intitule.getText(),
                    Float.parseFloat(productView.prixHT.getText()),
                    Integer.parseInt(productView.qteStock.getText())
                );
                this.articleEntityManager.persist(leProduit);
                magasin.addArticle(leProduit);
                productView.productAdd.reset(true);
            } catch (FormException formException) {
                productView.productAdd.errorDialog(formException.getMessage());
                return;
            } catch (NumberFormatException numException) {
                productView.productAdd.errorDialog(NUMBER_ERROR);
                return;
            } catch (EntityManagerProxyException exception) {
                productView.productAdd.errorDialog(exception.getMessage());
            }
        });
    }

    protected void openMagasinArticlePageAction() throws InternalException {
        this.getLayout().home.page(Home.PRODUCTS).onOpen(e -> {
            try {
                productView.productTableList.getDetails(this.articleEntityManager.getAll());
            } catch (EntityManagerProxyException entityManagerProxy) {}
        });
    }

    public List<IEntity> getMagasins() throws InternalException {
        return this.getEntityManagerProxy(Magasin.class).getAll();
    }

    @Override
    protected void actions() throws InternalException {
        this.submitArticleAction();
        this.openMagasinArticlePageAction();
    }
}