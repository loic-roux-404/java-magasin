package Controller;

import Model.EntityManager;
import Model.Client;
import View.ClientView;
import View.SwingModules.List;
import View.SwingModules.Form;

import javax.swing.*;

public class ClientController {
    // Dependencies
    private EntityManager entityManager;
    private Form clientForm;
    private List userDetails;

    public ClientController(Form clientForm, List list, ClientView clientView) {
        this.entityManager = new EntityManager(Client.class);
        this.clientForm = clientForm;
        this.userDetails = list;

        // submit user
        this.clientForm.submit(e -> {
            String firstname = clientView.getFirstname().trim();
            String lastname = clientView.getLastname().trim();

            // simple validations
            if (firstname.isEmpty()) {
                JOptionPane.showMessageDialog(this.clientForm.getPanel(), "First Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (lastname.isEmpty()) {
                JOptionPane.showMessageDialog(this.clientForm.getPanel(), "Last Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.entityManager.add(new Client(firstname, lastname));
            this.entityManager.save();
            this.clientForm.reset(true);
        });

        // load users
        this.clientForm.list(e -> {
            this.userDetails.getDetails(this.entityManager.loadEntities());
        });

        // TODO delete here
    }
}
