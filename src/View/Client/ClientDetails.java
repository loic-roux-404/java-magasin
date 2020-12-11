package View.Client;

import View.AbstractDetails;

public class ClientDetails extends AbstractDetails {

    static String[] tableColumn = {"FIRST NAME", "LAST NAME"};

    public ClientDetails() {
        super(tableColumn);
    }
}
