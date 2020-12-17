package View;

import Controller.AbstractController;
import Controller.OrderController;
import Exceptions.InternalException;
import Exceptions.ServiceRegisteryException;
import Model.Car.Car;
import Model.Car.CarBrand;
import Model.Car.CarModel;
import Services.Entity.Entity;
import Services.Entity.EntityManager;
import Services.Layout;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.List;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import Model.Client;

public class OrderView {
	static String[] tableColumn = { "ID", "CAR", "CLIENT" };
	OrderController controller;
	static final String ADD = "order_add";
	static final String LIST = "order_list";
	
	private JTextField id = new JTextField(25);;
	private JComboBox clientSelect = new JComboBox();
	private JComboBox carSelect = new JComboBox();

	public Form orderForm;
	public View.SwingModules.List orderList;

	public OrderView(Layout ly, OrderController controller) throws HeadlessException, InternalException {
		this.controller = controller;
		// Components
		orderForm = this.CREATE();
		orderList = this.LIST();

		orderForm.getBackButton().onClick(e -> ly.card.show(ly.mainFrame.getContentPane(), "Home"));
		// adds view to card layout with unique constraints
		ly.mainFrame.add(orderForm.getPanel(), ADD);
		ly.mainFrame.add(orderList, LIST);
		// Home access
		ly.home.ordersPage(e -> ly.card.show(ly.mainFrame.getContentPane(), ADD));
		// switch view according to its constraints on click
		orderForm.list(e -> ly.card.show(ly.mainFrame.getContentPane(), LIST));
		orderList.backButton.onClick(e -> ly.card.show(ly.mainFrame.getContentPane(), ADD));
	}

	public View.SwingModules.List LIST() {
		return new List(tableColumn);
	}

	public Form CREATE() throws InternalException {
		EntityManager managerCar = controller.getEntityManager(Car.class);
		managerCar.add(new Car(new CarBrand("Renault"), new CarModel("Megane", "2.5", 1320)));
		
		managerCar.add(new Car(new CarBrand("Renault"), new CarModel("Laguna", "3", 1562.5)));        
		
		ArrayList<Entity> listeClients = this.getClients();
		ArrayList<Entity> listeCars = this.getCars();

		clientSelect.addItem("Select...");
		carSelect.addItem("Select...");

		for (Entity client: listeClients) {
			clientSelect.addItem(client);
		}

		for (Entity car: listeCars) {
			carSelect.addItem(car);
		}

		FormBuilder builder = (new FormBuilder(true))
				.addField("id", id)
				.addField("voiture", carSelect)
				.addField("client", clientSelect);

		return builder.create(Optional.empty());
	}
	
    public ArrayList<Entity> getClients() throws ServiceRegisteryException {
    	return controller.getEntityManager(Client.class).getAll();
    }
    public ArrayList<Entity> getCars() throws ServiceRegisteryException {
    	return controller.getEntityManager(Car.class).getAll();
    }

	public String getId() {
		return id.getText();
	}

	public JComboBox getClientSelect() {
		return clientSelect;
	}

	public JComboBox getCarSelect() {
		return carSelect;
	}
}
