package Controller;

import Exceptions.ServiceRegisteryException;
import Framework.Registery;
import Model.Builder;
import Services.Entity.EntityManager;
import Services.Layout;
import View.BuilderView;

public class BuilderController extends AbstractController {
    private final EntityManager entityManager;

    private final BuilderView view;

    // Form create, List list
    public BuilderController(Registery registery) throws ServiceRegisteryException {
        super(registery);
        this.entityManager = this.getEntityManager(Builder.class);
        view = new BuilderView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws ServiceRegisteryException {
        Layout ly = this.getLayout();
        // Open list page from create form
        view.builderCreateForm.list(e -> {

        });

        view.builderCreateForm.submit(e -> {

        });

        view.carPageBtn.onOpen(e -> {

        });
    }
}
