package com.jogojava.dagraoclicker.view;

import com.jogojava.dagraoclicker.model.Upgrade;
import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class ShopPanel extends JPanel {

    private Map<Upgrade, JButton> upgradeButtons = new EnumMap<>(Upgrade.class);

    public ShopPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(50, 50, 50));
        setPreferredSize(new Dimension(350, 0));

        JLabel labelLoja = new JLabel("Loja de Upgrades");
        labelLoja.setFont(new Font("Arial", Font.BOLD, 24));
        labelLoja.setForeground(Color.WHITE);
        labelLoja.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelLoja);
        add(Box.createRigidArea(new Dimension(0, 20)));

        for (Upgrade upgrade : Upgrade.values()) {
            JButton button = createUpgradeButton(upgrade);
            upgradeButtons.put(upgrade, button);
            add(button);
            add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    private JButton createUpgradeButton(Upgrade upgrade) {
        ImageIcon icon = new ImageIcon(new ImageIcon(upgrade.getImagePath()).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        String text = String.format("<html><b>%s</b><br>Custo: %d | CPS: %.1f</html>",
                upgrade.getNome(), upgrade.getCusto(), upgrade.getBonusCps());

        JButton button = new JButton(text);
        button.setIcon(icon);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 80));
        button.setPreferredSize(new Dimension(300, 80));
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        return button;
    }

    public Map<Upgrade, JButton> getUpgradeButtons() {
        return upgradeButtons;
    }
}