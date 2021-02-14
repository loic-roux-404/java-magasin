package com.app.Controller;

import com.app.Exceptions.*;
import com.app.Framework.Registery;
import com.app.Model.Magasin;
import com.app.Services.EntityManagerProxy;
import com.app.View.MagasinView;

public class MagasinController extends AbstractController {
    public final static String TITLE = "Gestion des Magasins";
    private final EntityManagerProxy entityManager;

    private MagasinView magasinView;

    // Form create, List list
    public MagasinController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManagerProxy(Magasin.class);
        magasinView = new MagasinView(this.getLayout(), this);
        this.actions();
    }

    protected void listMagasinActions() {
        // Open list page from create form
        magasinView.magasinCreateForm.list(e -> {
            try {
                magasinView.builderTableList.getDetails(this.entityManager.getAll());
            } catch (EntityManagerProxyException entityManagerProxyException) {}
        });
    }

    protected void submitMagasinAction() {
        magasinView.magasinCreateForm.submit(e -> {
            try {
                magasinView.magasinCreateForm.validate();
                String phoneNumber = magasinView.getPhoneNumber().trim();
                String address = magasinView.getAddress().trim();
                int postalCode = magasinView.getPostalCode();

                if (phoneNumber.length() != 10) {
                    throw new PhoneNumberDigitsException();
                }

                this.entityManager.persist(new Magasin(phoneNumber, address, postalCode));
            } catch (Exception exception) {
                magasinView.magasinCreateForm.errorDialog(exception.getMessage());
                return;
            }

            magasinView.magasinCreateForm.reset(true);
        });
    }

    @Override
    protected void actions() throws InternalException {
        this.listMagasinActions();
        this.submitMagasinAction();
    }
}
