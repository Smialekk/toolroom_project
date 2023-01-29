package Panels;

import Models.UserModel;
import Repositories.UsersRepository;

import javax.swing.*;
import java.awt.*;

public class AddUserPanel extends JFrame {
    private JPanel panel1;
    private JTextField inputSurname;
    private JTextField inputName;
    private JTextField inputLogin;
    private JPasswordField inputPassword;
    private JButton buttonSave;
    private JTextField inputPosition;

    public AddUserPanel(AdminPanel adminPanel, UserModel user) {
        super("Dodaj użytkownika");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.setSize(600, 400);

        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((objDimension.width - this.getWidth()) / 2, (objDimension.height - this.getHeight()) / 2);

        if (user != null) {
            inputName.setText(user.getName());
            inputSurname.setText(user.getSurname());
            inputPosition.setText(user.getPosition());
            inputLogin.setText(user.getLogin());
            inputPassword.setText(user.getPassword());
        }

        buttonSave.addActionListener(actionEvent -> {
            var name = new String(inputName.getText());
            var surname = new String(inputSurname.getText());
            var position = new String(inputPosition.getText());
            var login = new String(inputLogin.getText());
            var password = new String(inputPassword.getPassword());

            if(inputName.getText().equals("")||inputSurname.getText().equals("")||inputPosition.getText().equals("")||inputLogin.getText().equals("")||inputPassword.getPassword().equals("")){
                JOptionPane.showMessageDialog(this, "Prosze uzupełnić wszystkie pola!");
            }else {
                // Dodajemy nowego uzytkownika
                if (user == null) {
                    UsersRepository usersRepository = new UsersRepository();

                    boolean result = usersRepository.createUser(new UserModel(null, name,surname, position, login, password, "user"));

                    if (result) {
                        adminPanel.fillUsers();

                        JOptionPane.showMessageDialog(null, "Dodano nowego użytkownika.");

                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Nie udało się dodać nowego użytkownika.");
                    }
                } else {
                    UsersRepository usersRepository = new UsersRepository();

                    boolean result = usersRepository.editUser(new UserModel(user.getId(), name,surname, position, login, password, user.getRole()));

                    if (result) {
                        adminPanel.fillUsers();

                        JOptionPane.showMessageDialog(null, "Edytowano użytkownika.");

                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Nie udało się edytowac użytkownika.");
                    }
                }

            }


        });


    }
}
