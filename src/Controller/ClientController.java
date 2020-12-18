package Controller;

import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Model.Client;
import Services.Entity.EntityManager;
import View.ClientView;

import javax.swing.*;

public class ClientController extends AbstractController {
    // Dependencies
    private final EntityManager entityManager;
    // Views
    private final ClientView clientView;

    public ClientController(Registery registery) throws ServiceRegisteryException {
        super(registery);
        this.entityManager = this.getEntityManager(Client.class);
        clientView = new ClientView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() {
        // submit user
        clientView.createForm.submit(e -> {
            String firstname = clientView.getFirstname().trim();
            String lastname = clientView.getLastname().trim();

            // simple validations
            if (firstname.isEmpty()) {
                JOptionPane.showMessageDialog(clientView.createForm.getPanel(), "First Name Required.", "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            } else if (lastname.isEmpty()) {
                JOptionPane.showMessageDialog(clientView.createForm.getPanel(), "Last Name Required.", "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.entityManager.add(new Client(firstname, lastname));

            clientView.createForm.reset(true);
        });

        // load users
        clientView.createForm.list(e -> {
            clientView.list.getDetails(this.entityManager.getAll());
        });

        // TODO delete here
    }
}
