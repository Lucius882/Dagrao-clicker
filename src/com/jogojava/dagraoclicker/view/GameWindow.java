package com.jogojava.dagraoclicker.view;

import javax.swing.*;
import java.awt.*;
import com.jogojava.dagraoclicker.model.Upgrade;

public class GameWindow extends JFrame {

    private ClickerPanel clickerPanel;
    private ShopPanel shopPanel;
    private OwnedItemsPanel ownedItemsPanel;

    public GameWindow() {
        super("Dagrão Clicker");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Painel Principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Painel Esquerda (Clicker)
        clickerPanel = new ClickerPanel();
        painelPrincipal.add(clickerPanel, BorderLayout.WEST);

        // Painel Central (Itens Comprados)
        ownedItemsPanel = new OwnedItemsPanel();
        JScrollPane scrollCentro = new JScrollPane(ownedItemsPanel);
        scrollCentro.setBorder(null);
        painelPrincipal.add(scrollCentro, BorderLayout.CENTER);

        // Painel Direita (Loja)
        shopPanel = new ShopPanel();
        JScrollPane scrollDireita = new JScrollPane(shopPanel);
        scrollDireita.setBorder(null);
        painelPrincipal.add(scrollDireita, BorderLayout.EAST);

        add(painelPrincipal);
    }

    public ClickerPanel getClickerPanel() {
        return clickerPanel;
    }

    public ShopPanel getShopPanel() {
        return shopPanel;
    }

    public void updatePointsLabel(double pontos) {
        clickerPanel.getLabelPontos().setText("Pontos: " + String.format("%,.0f", pontos));
    }

    public void addOwnedUpgrade(Upgrade upgrade) {
        ownedItemsPanel.addUpgrade(upgrade);
    }
}