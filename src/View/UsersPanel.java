package View;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

public class UsersPanel {


    public UsersPanel(CardLayout cardLayout, MainFrame mainFrame) throws HeadlessException {
        UserForm userForm = new UserForm();
        UserDetails userDetails = new UserDetails();
        // initialize user controller
        new UserController(userForm, userDetails);

        // adds view to card layout with unique constraints
        mainFrame.add(userForm, "form");
        mainFrame.add(userDetails, "User details");
        // switch view according to its constraints on click
        userForm.viewUsers(e -> cardLayout.show(mainFrame.getContentPane(), "User details"));
        userDetails.backButton(e -> cardLayout.show(mainFrame.getContentPane(), "form"));
    }
}
