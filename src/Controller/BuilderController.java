package Controller;

import Model.EntityManager;
import Model.Builder;
import View.SwingModules.List;
import View.SwingModules.Form;

public class BuilderController {
    private EntityManager entityManager;

    private Form create;
    private List list;

    public BuilderController(Form create, List list) {
        this.entityManager = new EntityManager(Builder.class);
        this.create = create;
        this.list = list;

        // Open list page from create form
        this.create.list(e -> {});

        this.create.submit(e -> {});
    }
}
