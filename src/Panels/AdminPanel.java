package Panels;

import Models.UserModel;
import Repositories.ItemsRepository;
import Repositories.UsersRepository;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
    private JPanel MainPanel;
    private JButton buttonAddUser;
    private JButton buttonLogout;
    private JPanel listUsers;
    private JPanel listItems;
    private JLabel nameLabel;
    private UserModel user;

    public AdminPanel(UserModel user) {
        super("Panel administratora");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.setSize(1027, 768);

        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((objDimension.width - this.getWidth()) / 2, (objDimension.height - this.getHeight()) / 2);

        listItems.setLayout(new BoxLayout(listItems, BoxLayout.Y_AXIS));
        listUsers.setLayout(new BoxLayout(listUsers, BoxLayout.Y_AXIS));

        this.user = user;
        nameLabel.setText("Witaj "+ user.getName());

        buttonLogout.addActionListener(actionEvent -> {
            this.dispose();

            LoginPanel loginPanel = new LoginPanel();
            loginPanel.setVisible(true);
        });

        buttonAddUser.addActionListener(actionEvent -> {
            AddUserPanel addUserPanel = new AddUserPanel(this, null);
            addUserPanel.setVisible(true);
        });

        fillItems();
        fillUsers();
    }

    void fillItems() {
        listItems.removeAll();

        ItemsRepository itemsRepository = new ItemsRepository();

        var items = itemsRepository.getAllItems();

        items.forEach(item -> {
            listItems.add(new itemRowAdmin(item));
        });
    }

    void fillUsers() {
        listUsers.setVisible(false);

        listUsers.removeAll();

        UsersRepository usersRepository = new UsersRepository();

        var users = usersRepository.getAllUsers();

        users.forEach(user -> {
            listUsers.add(new UserRow(user, this));
        });

        listUsers.setVisible(true);
    }
}
