package View;

import Controller.BuilderController;
import Controller.CarController;
import Controller.ClientController;
import Controller.OrderController;
import View.SwingModules.FormBuilder;
import View.SwingModules.PageBtn;

import javax.swing.*;
import java.util.HashMap;

public class Home extends JPanel {
    public FormBuilder form = new FormBuilder(false).disableAllBtn();

    public HashMap<String, PageBtn> pages = new HashMap<>();

    public final static String CLIENTS = "clients";
    public final static String CARS = "cars";
    public final static String ORDERS = "orders";
    public final static String BUILDERS = "builders";

    public Home() {
        this.addPageBtn(ClientController.TITLE, CLIENTS);
        this.addPageBtn(CarController.TITLE, CARS);
        this.addPageBtn(OrderController.TITLE, ORDERS);
        this.addPageBtn(BuilderController.TITLE, BUILDERS);

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
