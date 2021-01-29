package com.app.Controller;

import com.app.Exceptions.FormException;
import com.app.Exceptions.InternalException;
import com.app.Exceptions.PhoneNumberDigitsException;
import com.app.Exceptions.ServiceRegisteryException;
import com.app.Framework.Registery;
import com.app.Model.Magasin;
import com.app.Services.Entity.EntityManager;
import com.app.View.MagasinView;
import com.app.View.SwingModules.Theme;

import javax.swing.*;

public class MagasinController extends AbstractController{
    public final static String TITLE = "Gestion des Magasins";
    private final EntityManager entityManager;

    private MagasinView magasinView;

    // Form create, List list
    public MagasinController(Registery registery) throws InternalException {
        super(registery);
        this.entityManager = this.getEntityManager(Magasin.class);
        magasinView = new MagasinView(this.getLayout(), this);
        this.actions();
    }

    @Override
    protected void actions() throws ServiceRegisteryException {
        // Open list page from create form
        magasinView.magasinCreateForm.list(e -> {
            magasinView.builderList.getDetails(this.entityManager.getAll());
        });

        magasinView.magasinCreateForm.submit(e -> {
            try {
                magasinView.magasinCreateForm.validate();
                String phoneNumber = magasinView.getPhoneNumber().trim();
                String address = magasinView.getAddress().trim();
                int postalCode = magasinView.getPostalCode();

                if (phoneNumber.length() != 10) {
                    throw new PhoneNumberDigitsException();
                }

                this.entityManager.add(new Magasin(phoneNumber, address, postalCode));
            } catch (FormException formException) {
                JOptionPane.showMessageDialog(
                    magasinView.magasinCreateForm.getPanel(),
                    formException.getMessage(),
                    Theme.dialogErrorTxt,
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            magasinView.magasinCreateForm.reset(true);
        });
    }
}
