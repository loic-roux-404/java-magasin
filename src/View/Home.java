package View;

import View.SwingModules.FormBuilder;
import View.SwingModules.PageBtn;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Optional;

public class Home extends JPanel {
    public FormBuilder form = new FormBuilder(false).disableAllBtn();

    public HashMap<String, PageBtn> pages = new HashMap<>();

    public final static String CLIENTS = "clients";
    public final static String CARS = "cars";
    public final static String ORDERS = "orders";
    public final static String BUILDERS = "builders";

    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public Home() {
        this.addPageBtn("Gestion des clients", CLIENTS);
        this.addPageBtn("Catalogue de voitures", CARS);
        this.addPageBtn("Gestion des commandes", ORDERS);
        this.addPageBtn("Gestion des Fabricants", BUILDERS);

        form.create(Optional.ofNullable(this));
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
