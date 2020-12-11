package Controller;

import Model.EntityManager;
import Model.Client;
import View.Client.ClientForm;
import View.AbstractDetails;
import javax.swing.*;
import java.io.File;

public class ClientController {
    static String databaseFile = "users_db.txt";
    // Dependencies
    private EntityManager entityManager;
    private ClientForm clientForm;
    private AbstractDetails userDetails;

    public ClientController(ClientForm clientForm, AbstractDetails abstractDetails) {
        this.entityManager = new EntityManager();
        this.clientForm = clientForm;
        this.userDetails = abstractDetails;

        // submit user
        this.clientForm.submitUsers(e -> {
            String firstname = this.clientForm.getFirstname().trim();
            String lastname = this.clientForm.getLastname().trim();

            // simple validations
            if(firstname.isEmpty()) {
                JOptionPane.showMessageDialog(this.clientForm, "First Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if(lastname.isEmpty()) {
                JOptionPane.showMessageDialog(this.clientForm, "Last Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.entityManager.add(new Client(firstname, lastname));
            this.entityManager.save(new File(databaseFile));
            this.clientForm.reset(true);
        });

        // load users
        this.clientForm.viewUsers(e -> {
            this.userDetails.getDetails(this.entityManager.loadEntities(new File(databaseFile)));
        });
    }
}
