package View.Order;

import View.AbstractDetails;

public class OrderDetails extends AbstractDetails {

    static String[] tableColumn = {"ID", "CAR", "CLIENT"};

    public OrderDetails() {
        super(tableColumn);
    }
}
