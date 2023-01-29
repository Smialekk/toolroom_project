package Panels;

import Models.ItemModel;
import Repositories.ItemsRepository;
import Repositories.UsersRepository;

import javax.swing.*;
import java.awt.*;

public class itemRowAdmin extends JPanel{
    private JPanel panel1;
    private JLabel labelImage;
    private JLabel labelTitle;

    public itemRowAdmin(ItemModel item) {
        this.add(panel1);
        this.setVisible(true);

        labelImage.setIcon(setSizeIcon(new ImageIcon(getClass().getResource("../Assets/Items/" + item.getImage()))));
        labelTitle.setText(item.getName());
    }

    public ImageIcon setSizeIcon(ImageIcon ic) {
        Image im = ic.getImage();
        Image newImg = im.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
