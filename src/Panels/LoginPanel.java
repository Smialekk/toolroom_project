package Panels;

import Models.UserModel;
import Repositories.UsersRepository;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JFrame {
    private JButton zalogujButton;
    private JPasswordField passField;
    private JButton zapomniałemHasłaButton;
    private JPanel mainPanel;
    private JLabel labelPhoto;
    private JTextField loginField;

    public ImageIcon setSizeIcon(ImageIcon ic) {
        Image im = ic.getImage();
        Image newImg = im.getScaledInstance(450, 107, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public LoginPanel(){
        super("Zaloguj się");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(450, 350);
        this.setResizable(false);

        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((objDimension.width - this.getWidth()) / 2, (objDimension.height - this.getHeight()) / 2);

        labelPhoto.setIcon(setSizeIcon(new ImageIcon(getClass().getResource("../logo.jpg"))));

        zalogujButton.addActionListener(actionEvent -> {
            var login = new String(loginField.getText());
            var password = new String(passField.getPassword());

            UsersRepository usersRepository = new UsersRepository();

            UserModel user = usersRepository.login(login, password);

            if (user != null) {
                if (user.getRole().equals("user")) {
                    this.dispose();

                    UserPanel userPanel = new UserPanel(user);
                    userPanel.setVisible(true);
                }
                else if (user.getRole().equals("admin")) {
                    this.dispose();

                    AdminPanel adminPanel = new AdminPanel(user);
                    adminPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Niepoprawny użytkownik. Skontaktuj się z administratorem.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Błędny login lub hasło, spróbuj ponownie");
            }
        });

        zapomniałemHasłaButton.addActionListener(actionEvent -> JOptionPane.showMessageDialog(null, "Skontaktuj się z administratorem."));
    }
}
