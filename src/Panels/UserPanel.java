package Panels;

import Models.UserModel;
import Repositories.ItemsRepository;

import javax.swing.*;
import java.awt.*;

public class UserPanel  extends JFrame {
    private JPanel panel1;
    private JPanel menu;
    private JScrollPane itemAllPane;
    private JPanel itemsAll;
    private JPanel itemsSelected;
    private JScrollPane itemSelectedPane;
    private JButton buttonLogout;
    private JLabel nameLabel;
    private UserModel user;

    public UserPanel(UserModel user) {
        super("Panel użytkownika");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setSize(800, 600);

        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((objDimension.width - this.getWidth()) / 2, (objDimension.height - this.getHeight()) / 2);

        this.user = user;

        itemsAll.removeAll();
        itemsAll.setLayout(new BoxLayout(itemsAll, BoxLayout.Y_AXIS));

        itemsSelected.removeAll();
        itemsSelected.setLayout(new BoxLayout(itemsSelected, BoxLayout.Y_AXIS));

        nameLabel.setText("Witaj "+ user.getName());

        fillItems();
        fillItemsSelected();

        buttonLogout.addActionListener(actionEvent -> {
            this.dispose();

            LoginPanel loginPanel = new LoginPanel();
            loginPanel.setVisible(true);
        });
    }
    void fillItems() {
        itemsAll.setVisible(false);

        itemsAll.removeAll();

        ItemsRepository itemsRepository = new ItemsRepository();

        var items = itemsRepository.getAllItems();

        items.forEach(item -> {
            itemsAll.add(new ItemRow(item, this.user, this));
        });

        itemsAll.setVisible(true);
    }

    void fillItemsSelected() {
        itemsSelected.setVisible(false);

        itemsSelected.removeAll();

        ItemsRepository itemsRepository = new ItemsRepository();

        var items = itemsRepository.getAllItemsByUser(user);

        items.forEach(item -> {
            itemsSelected.add(new ItemRowSelected(item, this.user, this));
        });

        itemsSelected.setVisible(true);
    }
}
