package com.jogojava.dagraoclicker.view;

import com.jogojava.dagraoclicker.model.Upgrade;
import com.jogojava.dagraoclicker.utils.ImageUtils;
import javax.swing.*;
import java.awt.*;

public class OwnedItemsPanel extends JPanel {

    private JPanel painelEspada;
    private JPanel painelGuerreiro;
    private JPanel painelArqueiro;
    private JPanel painelBruxa;
    private JPanel painelGigante;
    private JPanel painelEscolhido;

    private JLabel createTituloImagem(String caminhoImagem) {
        JLabel label = new JLabel(ImageUtils.loadIcon(caminhoImagem, 100, 50));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JPanel createBloco(String imagem, JPanel painel, Color bgColor) {
        JPanel bloco = new JPanel();
        bloco.setLayout(new BoxLayout(bloco, BoxLayout.Y_AXIS));
        bloco.setBackground(bgColor);

        JLabel titulo = createTituloImagem(imagem);
        painel.setBackground(bgColor);

        bloco.add(titulo);
        bloco.add(painel);

        return bloco;
    }

    public OwnedItemsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(40, 40, 40));

        painelEspada = createPainelLinha();
        painelGuerreiro = createPainelLinha();
        painelArqueiro = createPainelLinha();
        painelBruxa = createPainelLinha();
        painelGigante = createPainelLinha();
        painelEscolhido = createPainelLinha();

        add(createBloco("imgs/titulo_espada.png", painelEspada, new Color(60, 30, 30)));
        add(createBloco("imgs/titulo_guerreiro.png", painelGuerreiro, new Color(30, 60, 30)));
        add(createBloco("imgs/titulo_arqueiro.png", painelArqueiro, new Color(30, 30, 60)));
        add(createBloco("imgs/titulo_bruxa.png", painelBruxa, new Color(60, 0, 60)));
        add(createBloco("imgs/titulo_gigante.png", painelGigante, new Color(50, 35, 20)));
        add(createBloco("imgs/titulo_escolhido.png", painelEscolhido, new Color(40, 40, 40)));

        setPreferredSize(new Dimension(350, 900));
    }

    private JPanel createPainelLinha() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painel.setBackground(getBackground());
        painel.setPreferredSize(new Dimension(330, 128));
        painel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        return painel;
    }

    public void addUpgrade(Upgrade upgrade) {
        String tipo = upgrade.getNome().toUpperCase().replace(" ", "_");

        JPanel painelAlvo;

        switch (tipo) {
            case "ESPADA":
                painelAlvo = painelEspada;
                break;
            case "GUERREIRO":
                painelAlvo = painelGuerreiro;
                break;
            case "ARQUEIRO":
                painelAlvo = painelArqueiro;
                break;
            case "BRUXA":
                painelAlvo = painelBruxa;
                break;
            case "GIGANTE_DE_MADEIRA":
                painelAlvo = painelGigante;
                break;
            case "O_ESCOLHIDO":
                painelAlvo = painelEscolhido;
                break;
            default:
                System.out.println("Tipo desconhecido: " + tipo);
                return;
        }

        if (painelAlvo.getComponentCount() >= 36) {
            return;
        }

        ImageIcon icon = ImageUtils.loadIcon(upgrade.getImagePath(), 64, 64);
        if (icon == null) {
            icon = new ImageIcon();
        }

        JLabel label = new JLabel(icon);
        label.setToolTipText(String.format("%s (+%.1f CPS)", upgrade.getNome(), upgrade.getBonusCps()));

        painelAlvo.add(label);

        revalidate();
        repaint();
    }
}
