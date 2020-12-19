package Controller;

import Exceptions.FormException;
import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Model.Client;
import Services.Entity.EntityManager;
import View.ClientView;

import javax.swing.*;

public class ClientController extends AbstractController {
    public final static String TITLE = "Gestion des clients";
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
            try {
                clientView.createForm.validate();
                String firstname = clientView.getFirstname().trim();
                String lastname = clientView.getLastname().trim();
                this.entityManager.add(new Client(firstname, lastname));

            } catch (FormException formException) {
                JOptionPane.showMessageDialog(clientView.createForm.getPanel(), formException.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            clientView.createForm.reset(true);
        });

        // load users
        clientView.createForm.list(e -> {
            clientView.list.getDetails(this.entityManager.getAll());
        });

        // TODO delete here
    }
}
