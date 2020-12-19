package Controller;

import Exceptions.FormException;
import Exceptions.InternalException;
import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Model.Builder;
import Services.Entity.EntityManager;
import View.BuilderView;

import javax.swing.*;

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
                JOptionPane.showMessageDialog(builderview.builderCreateForm.getPanel(), formException.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            builderview.builderCreateForm.reset(true);
        });
    }
}
