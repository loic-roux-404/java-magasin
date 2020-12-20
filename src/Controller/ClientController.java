package Controller;

import Exceptions.FormException;
import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Model.Client;
import Services.Entity.EntityManager;
import Utils.StrUtils;
import View.ClientView;
import View.SwingModules.Theme;

import javax.swing.*;
import javax.swing.table.TableModel;

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
                JOptionPane.showMessageDialog(
                    clientView.createForm.getPanel(),
                    formException.getMessage(),
                    Theme.dialogErrorTxt,
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            clientView.createForm.reset(true);
        });

        // load users in table list
        clientView.createForm.list(e -> {
            clientView.list.getDetails(this.entityManager.getAll());
        });

        /**
         * Update data of table, we only support text field
         * Also we use table column to determine entity field
         */
        clientView.list.update(e -> {
            if (e.getColumn() <= -1) return;
            int col = e.getColumn();
            int row = e.getFirstRow();
            TableModel model = (TableModel) e.getSource();

            Client en = (Client) this.entityManager.getById(e.getFirstRow());

            switch (col) {
                case 0:
                    en.setFirstname(StrUtils.updateString(model.getValueAt(row, col)));
                    break;
                case 1:
                    en.setName(StrUtils.updateString(model.getValueAt(row, col)));
                    break;
            }
        });
    }
}
