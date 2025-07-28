package com.jogojava.dagraoclicker.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    /**
     * Carrega uma imagem de um caminho, a redimensiona e a retorna como um ImageIcon.
     * Se a imagem não for encontrada, retorna um placeholder quadrado da mesma dimensão.
     * @param path O caminho para o arquivo de imagem.
     * @param width A largura desejada para o ícone.
     * @param height A altura desejada para o ícone.
     * @return Um ImageIcon redimensionado ou um placeholder em caso de erro.
     */
    public static ImageIcon loadIcon(String path, int width, int height) {
        try {
            Image image = ImageIO.read(new File(path));
            return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            System.err.println("Não foi possível carregar o ícone: " + path);
            // Retorna um placeholder cinza em caso de erro
            BufferedImage placeholder = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = placeholder.createGraphics();
            g2d.setColor(Color.GRAY);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();
            return new ImageIcon(placeholder);
        }
    }
}
