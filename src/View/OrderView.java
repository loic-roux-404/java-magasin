package View;

import Controller.OrderController;
import Model.Order;
import Model.Car.Car;
import Model.Car.CarBrand;
import Model.Car.CarModel;
import Model.EntityManager;
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
	OrderController oc;
	static final String ADD = "order_add";
	static final String LIST = "order_list";
	
	private JTextField id;
	private JComboBox clientList;
	private JComboBox carList;
	
	public OrderView(CardLayout cardLayout, MainFrame mainFrame, Home home) throws HeadlessException {
		Form orderForm = this.CREATE();
		View.SwingModules.List orderList = this.LIST();
		// initialize user controller
		oc = new OrderController(orderForm, orderList, this);

		orderForm.getBackButton().onClick(e -> cardLayout.show(mainFrame.getContentPane(), "Home"));
		// adds view to card layout with unique constraints
		mainFrame.add(orderForm.getPanel(), ADD);
		mainFrame.add(orderList, LIST);
		// Home access
		home.ordersPage(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
		// switch view according to its constraints on click
		orderForm.list(e -> cardLayout.show(mainFrame.getContentPane(), LIST));
		orderList.backButton.onClick(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
	}

	public View.SwingModules.List LIST() {
		return new List(tableColumn);
	}

	public Form CREATE() {
		EntityManager managerCar=new EntityManager(Car.class);
		managerCar.add(new Car(new CarBrand("Renault"), new CarModel("Megane", "2.5", 1320)));
		managerCar.save();
		managerCar.add(new Car(new CarBrand("Renault"), new CarModel("Laguna", "3", 1562.5)));        
		managerCar.save();
		Object[] listeClients = this.getClients();
		Object[] listeCars = this.getCars();

		id = new JTextField(25);
		
		clientList = new JComboBox();		
		carList = new JComboBox();

		clientList.addItem("Select...");		
		carList.addItem("Select...");

		
		
		for (var i = 0; i < listeClients.length; i++) {
			clientList.addItem(listeClients[i]);		
		}
		for (var i = 0; i < listeCars.length; i++) {
			carList.addItem(listeCars[i]);		
		}

		FormBuilder builder = (new FormBuilder(true))
				.addField("id", id)
				.addField("voiture", carList)
				.addField("client", clientList);

		return builder.create(Optional.empty());
	}
	
    public Object[] getClients() {
    	return (new EntityManager(Client.class)).loadEntities();
    }
    public Object[] getCars() {
    	return (new EntityManager(Car.class)).loadEntities();
    }

	public String getId() {
		return id.getText();
	}

	public JComboBox getClientList() {
		return clientList;
	}

	public JComboBox getCarList() {
		return carList;
	}
    
    
   
}
