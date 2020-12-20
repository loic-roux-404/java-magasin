package Controller;

import Exceptions.FormException;
import Exceptions.InternalException;
import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Model.Builder;
import Model.Client;
import Services.Entity.EntityManager;
import View.BuilderView;
import View.SwingModules.Theme;

import javax.swing.*;
import javax.swing.table.TableModel;

public class BuilderController extends AbstractController {
    public final static String TITLE = "Gestion des Fabricants";
    private final EntityManager entityManager;

    private BuilderView builderview;

    // Form create, List list
    public BuilderController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Builder.class);
        builderview = new BuilderView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws ServiceRegisteryException {
        // Open list page from create form
        builderview.builderCreateForm.list(e -> {
            builderview.builderList.getDetails(this.entityManager.getAll());
        });

        builderview.builderCreateForm.submit(e -> {
            try {
                builderview.builderCreateForm.validate();
                String builderName = builderview.getName().trim();
                this.entityManager.add(new Builder(builderName));
            } catch (FormException formException) {
                JOptionPane.showMessageDialog(
                    builderview.builderCreateForm.getPanel(),
                    formException.getMessage(),
                    Theme.dialogErrorTxt,
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            builderview.builderCreateForm.reset(true);
        });

        builderview.builderList.update(e -> {
            if (e.getColumn() <= -1) return;
            int col = e.getColumn();
            int row = e.getFirstRow();
            TableModel model = (TableModel) e.getSource();

            Builder en = (Builder) this.entityManager.getById(e.getFirstRow());

            switch (col) {
                case 0:
                    en.setName((String) model.getValueAt(row, col));
                    break;
                default:
                    break;
            }
        });
    }
}
