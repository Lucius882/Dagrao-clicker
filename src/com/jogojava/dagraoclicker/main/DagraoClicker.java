package com.jogojava.dagraoclicker.main;

import com.jogojava.dagraoclicker.controller.GameController;
import com.jogojava.dagraoclicker.model.GameState;
import com.jogojava.dagraoclicker.view.GameWindow;
import javax.swing.*;

public class DagraoClicker {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Cria o Model
            GameState model = new GameState();

            // 2. Cria a View
            GameWindow view = new GameWindow();

            // 3. Cria o Controller para ligar os dois
            new GameController(model, view);

            // 4. Torna o jogo visível
            view.setVisible(true);
        });
    }
}