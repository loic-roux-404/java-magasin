package Controller;

import Model.EntityManager;
import Model.User;
import View.UserForm;
import View.AbstractDetails;
import javax.swing.*;
import java.io.File;

public class UserController {
    static String databaseFile = "users_db.txt";
    // Dependencies
    private EntityManager entityManager;
    private UserForm userForm;
    private AbstractDetails userDetails;

    public UserController(UserForm userForm, AbstractDetails abstractDetails) {
        this.entityManager = new EntityManager();
        this.userForm = userForm;
        this.userDetails = abstractDetails;

        // submit user
        this.userForm.submitUsers(e -> {
            String firstname = this.userForm.getFirstname().trim();
            String lastname = this.userForm.getLastname().trim();

            // simple validations
            if(firstname.isEmpty()) {
                JOptionPane.showMessageDialog(this.userForm, "First Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if(lastname.isEmpty()) {
                JOptionPane.showMessageDialog(this.userForm, "Last Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.entityManager.add(new User(firstname, lastname));
            this.entityManager.save(new File(databaseFile));
            this.userForm.reset(true);
        });

        // load users
        this.userForm.viewUsers(e -> {
            this.userDetails.getDetails(this.entityManager.loadEntities(new File(databaseFile)));
        });
    }
}
