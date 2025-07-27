package com.jogojava.dagraoclicker.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ClickerPanel extends JPanel {

    private JLabel labelPontos;
    private JButton botaoDragao;

    private ImageIcon[] frames;
    private int escala = 6;

    public ClickerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(34, 34, 34)); // Cor de fundo mais escura
        setPreferredSize(new Dimension(350, 0)); // Altura será gerenciada pelo layout

        carregarSprites();

        labelPontos = new JLabel("Pontos: 0", SwingConstants.CENTER);
        labelPontos.setFont(new Font("Arial", Font.BOLD, 32));
        labelPontos.setForeground(Color.WHITE);
        labelPontos.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(labelPontos);

        botaoDragao = new JButton(frames[0]);
        botaoDragao.setBorderPainted(false);
        botaoDragao.setContentAreaFilled(false);
        botaoDragao.setFocusPainted(false);
        botaoDragao.setOpaque(false);
        botaoDragao.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 50)));
        add(botaoDragao);

        iniciarAnimacaoSprite();
    }

    private void carregarSprites() {
        try {
            File imagem = new File("imgs/ovo_dagrao.png");
            if (!imagem.exists()) {
                System.err.println("Arquivo não encontrado: " + imagem.getAbsolutePath());
                // Carrega um placeholder se a imagem não for encontrada
                frames = new ImageIcon[]{new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB))};
                return;
            }

            BufferedImage spriteSheet = ImageIO.read(imagem);
            int frameWidth = spriteSheet.getWidth() / 2;
            int frameHeight = spriteSheet.getHeight();

            frames = new ImageIcon[2];
            for (int i = 0; i < 2; i++) {
                BufferedImage frame = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
                Image imgEscalada = frame.getScaledInstance(frameWidth * escala, frameHeight * escala, Image.SCALE_SMOOTH);
                frames[i] = new ImageIcon(imgEscalada);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarAnimacaoSprite() {
        Timer animacao = new Timer(300, e -> {
            int index = (int) ((System.currentTimeMillis() / 300) % frames.length);
            botaoDragao.setIcon(frames[index]);
        });
        animacao.start();
    }

    public JButton getBotaoDragao() {
        return botaoDragao;
    }

    public JLabel getLabelPontos() {
        return labelPontos;
    }
}