package Panels;

import Models.ItemModel;
import Models.UserModel;
import Repositories.UsersRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemRowSelected extends JPanel {
    private JLabel labelImage;
    private JButton buttonSelect;
    private JLabel labelTitle;
    private JPanel panel1;

    public ItemRowSelected(ItemModel item, UserModel user, UserPanel userPanel) {
        this.add(panel1);
        this.setVisible(true);

        labelImage.setIcon(setSizeIcon(new ImageIcon(getClass().getResource("../Assets/Items/" + item.getImage()))));
        labelTitle.setText(item.getName());

        buttonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UsersRepository usersRepository = new UsersRepository();

                usersRepository.unselectItem(user, item);

                userPanel.fillItemsSelected();
            }
        });
    }

    public ImageIcon setSizeIcon(ImageIcon ic) {
        Image im = ic.getImage();
        Image newImg = im.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
