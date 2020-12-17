package Controller;

import Exceptions.ServiceRegisteryException;
import Services.Entity.EntityManager;
import Model.Builder;
import Services.Layout;
import Framework.Registery;
import View.BuilderView;

public class BuilderController extends AbstractController {
    private EntityManager entityManager;

    private BuilderView view;
    // Form create, List list
    public BuilderController(Registery registery) throws ServiceRegisteryException {
        super(registery);
        this.entityManager = new EntityManager(Builder.class);
        view = new BuilderView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws ServiceRegisteryException {
        Layout ly = this.getLayout();
        // Open list page from create form
        view.builderCreateForm.list(e -> {
            this.refresh();
        });

        view.builderCreateForm.submit(e -> {
            this.refresh();
        });
    }
}
