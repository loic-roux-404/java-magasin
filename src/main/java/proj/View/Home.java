package main.java.proj.View;

import javax.swing.*;
import java.util.HashMap;

import main.java.proj.Controller.*;
import main.java.proj.View.SwingModules.FormBuilder;
import main.java.proj.View.SwingModules.PageBtn;

public class Home extends JPanel {
    public FormBuilder form = new FormBuilder(false).disableAllBtn();

    public HashMap<String, PageBtn> pages = new HashMap<>();

    public final static String MAGASINS = "magasins";
    public final static String PRODUCTS = "products";

    public Home() {
        this.addPageBtn(ProductController.TITLE, PRODUCTS);
        this.addPageBtn(MagasinController.TITLE, MAGASINS);

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
