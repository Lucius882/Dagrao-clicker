package com.jogojava.dagraoclicker.controller;

import com.jogojava.dagraoclicker.model.GameState;
import com.jogojava.dagraoclicker.model.Upgrade;
import com.jogojava.dagraoclicker.view.GameWindow;
import javax.swing.*;

public class GameController {

    private GameState gameState;
    private GameWindow gameWindow;
    private Timer autoClickTimer;

    public GameController(GameState gameState, GameWindow gameWindow) {
        this.gameState = gameState;
        this.gameWindow = gameWindow;

        initController();
    }

    private void initController() {
        // Ação de clicar no ovo
        gameWindow.getClickerPanel().getBotaoDragao().addActionListener(e -> {
            gameState.addPontos(1);
            updateView();
        });

        // Ações de comprar na loja
        gameWindow.getShopPanel().getUpgradeButtons().forEach((upgrade, button) -> {
            button.addActionListener(e -> buyUpgrade(upgrade));
        });

        // Timer para os cliques por segundo (CPS)
        autoClickTimer = new Timer(1000, e -> {
            if (gameState.getClicksPerSecond() > 0) {
                gameState.addPontos(gameState.getClicksPerSecond());
                updateView();
            }
        });
        autoClickTimer.start();
    }

    private void buyUpgrade(Upgrade upgrade) {
        if (gameState.getPontos() >= upgrade.getCusto()) {
            gameState.spendPontos(upgrade.getCusto());
            gameState.addClicksPerSecond(upgrade.getBonusCps());
            gameWindow.addOwnedUpgrade(upgrade); // Adiciona o ícone ao painel central
            updateView();
        } else {
            JOptionPane.showMessageDialog(gameWindow,
                    "Você precisa de pelo menos " + upgrade.getCusto() + " pontos!",
                    "Pontos insuficientes",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateView() {
        gameWindow.updatePointsLabel(gameState.getPontos());
    }
}