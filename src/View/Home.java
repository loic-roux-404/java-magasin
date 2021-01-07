package View;

import javax.swing.*;
import java.util.HashMap;

import Controller.*;
import View.SwingModules.FormBuilder;
import View.SwingModules.PageBtn;

public class Home extends JPanel {
    public FormBuilder form = new FormBuilder(false).disableAllBtn();

    public HashMap<String, PageBtn> pages = new HashMap<>();

    public final static String RESTAURANTS = "restaurants";
    public final static String PRODUCTS = "products";

    public Home() {
        this.addPageBtn(ProductController.TITLE, PRODUCTS);
        this.addPageBtn(RestaurantController.TITLE, RESTAURANTS);

        form.create(this);
    }

    public void registerPage(String name, PageBtn pageBtn) {
        pages.put(name, pageBtn);
    }

    protected void addPageBtn(String name, String id) {
        PageBtn pageBtn = new PageBtn(name);
        pages.put(id, pageBtn);
        form.addField(name, pageBtn.getBtn());
    }

    public PageBtn page(String name) {
        return pages.get(name);
    }
}
