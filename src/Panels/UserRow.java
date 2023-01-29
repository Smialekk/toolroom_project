package Panels;

import Models.UserModel;
import Repositories.UsersRepository;

import javax.lang.model.type.NullType;
import javax.swing.*;
import java.util.function.Function;
import java.util.function.Function;

public class UserRow extends JPanel {
    private JLabel labelName;
    private JLabel labelSurname;
    private JLabel labelLogin;
    private JLabel labelPosition;
    private JPanel panel1;
    private JButton buttonEdit;
    private JButton buttonDelete;

    public UserRow(UserModel user, AdminPanel adminPanel) {
        this.add(panel1);
        this.setVisible(true);

        labelName.setText(user.getName());
        labelSurname.setText(user.getSurname());
        labelLogin.setText(user.getLogin());
        labelPosition.setText(user.getPosition());

        buttonEdit.addActionListener(actionEvent -> {
            AddUserPanel addUserPanel = new AddUserPanel(adminPanel, user);
            addUserPanel.setVisible(true);

        });

        buttonDelete.addActionListener(actionEvent -> {
            int result = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć użytkownika?");

            if (result == 0) {
                UsersRepository usersRepository = new UsersRepository();
                usersRepository.deleteUserById(user.getId());

                adminPanel.fillUsers();
            }
        });
    }
}
