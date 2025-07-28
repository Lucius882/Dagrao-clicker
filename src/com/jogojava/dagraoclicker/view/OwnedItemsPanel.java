package com.jogojava.dagraoclicker.view;

import com.jogojava.dagraoclicker.model.Upgrade;
import com.jogojava.dagraoclicker.utils.ImageUtils; // Importa a nova classe
import javax.swing.*;
import java.awt.*;

public class OwnedItemsPanel extends JPanel {

    public OwnedItemsPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(new Color(40, 40, 40));
    }

    public void addUpgrade(Upgrade upgrade) {
        // Usa o ImageUtils para carregar o ícone
        ImageIcon icon = ImageUtils.loadIcon(upgrade.getImagePath(), 64, 64);
        JLabel label = new JLabel(icon);
        label.setToolTipText(String.format("%s (+%.1f CPS)", upgrade.getNome(), upgrade.getBonusCps()));
        add(label);
        revalidate();
        repaint();
    }
}
