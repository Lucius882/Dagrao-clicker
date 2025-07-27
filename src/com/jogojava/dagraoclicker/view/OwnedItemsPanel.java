package com.jogojava.dagraoclicker.view;

import com.jogojava.dagraoclicker.model.Upgrade;
import javax.swing.*;
import java.awt.*;

public class OwnedItemsPanel extends JPanel {

    public OwnedItemsPanel() {
        // Usar um FlowLayout com alinhamento à esquerda para os ícones
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(new Color(40, 40, 40));
    }

    public void addUpgrade(Upgrade upgrade) {
        ImageIcon icon = new ImageIcon(new ImageIcon(upgrade.getImagePath()).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        JLabel label = new JLabel(icon);
        label.setToolTipText(String.format("%s (+%.1f CPS)", upgrade.getNome(), upgrade.getBonusCps()));
        add(label);
        revalidate();
        repaint();
    }
}