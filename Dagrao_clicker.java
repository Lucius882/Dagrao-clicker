import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CookieClickerComAba extends JFrame {

    private int pontos = 0;
    private int multiplicador = 1;

    private JLabel labelPontos;
    private JButton botaoCookie;

    private JLabel labelUpgradeInfo;
    private JButton botaoComprarUpgrade;

    public CookieClickerComAba() {
        super("Cookie Clicker com Abas");

        JTabbedPane abas = new JTabbedPane();

        JPanel painelJogo = new JPanel(null);
        painelJogo.setBackground(new Color(240, 230, 200));

        labelPontos = new JLabel("Pontos: 0", SwingConstants.CENTER);
        labelPontos.setFont(new Font("Arial", Font.BOLD, 32));
        labelPontos.setBounds(50, 20, 500, 50);
        painelJogo.add(labelPontos);

        botaoCookie = new JButton("🍪") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.translate(1, 1);
                }
                super.paintComponent(g);
            }
        };
        botaoCookie.setFont(new Font("SansSerif", Font.PLAIN, 100));
        botaoCookie.setFocusPainted(false);
        botaoCookie.setContentAreaFilled(false);
        botaoCookie.setOpaque(true);
        botaoCookie.setBackground(new Color(255, 240, 200));
        botaoCookie.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3, true));
        botaoCookie.setBounds(200, 150, 200, 200);

        botaoCookie.addActionListener(e -> {
            pontos += multiplicador;
            atualizarPontuacao();
            balancarBotao(botaoCookie);
        });

        painelJogo.add(botaoCookie);

        JPanel painelUpgrades = new JPanel();
        painelUpgrades.setLayout(new BoxLayout(painelUpgrades, BoxLayout.Y_AXIS));
        painelUpgrades.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelUpgrades.setBackground(new Color(220, 220, 220));

        labelUpgradeInfo = new JLabel("Multiplicador atual: x1");
        labelUpgradeInfo.setFont(new Font("Arial", Font.PLAIN, 18));
        labelUpgradeInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoComprarUpgrade = new JButton("Comprar Upgrade (10 pontos)");
        botaoComprarUpgrade.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoComprarUpgrade.setFont(new Font("Arial", Font.BOLD, 16));
        botaoComprarUpgrade.addActionListener(e -> {
            if (pontos >= 10) {
                pontos -= 10;
                multiplicador++;
                atualizarPontuacao();
                labelUpgradeInfo.setText("Multiplicador atual: x" + multiplicador);
                botaoComprarUpgrade.setText("Comprar Upgrade (" + (10) + " pontos)");
            } else {
                JOptionPane.showMessageDialog(this, "Você precisa de pelo menos 10 pontos!", "Sem pontos", JOptionPane.WARNING_MESSAGE);
            }
        });

        painelUpgrades.add(labelUpgradeInfo);
        painelUpgrades.add(Box.createRigidArea(new Dimension(0, 20)));
        painelUpgrades.add(botaoComprarUpgrade);

        abas.addTab("Jogo", painelJogo);
        abas.addTab("Upgrades", painelUpgrades);

        add(abas);
    }

    // Atualiza a label de pontuação
    private void atualizarPontuacao() {
        labelPontos.setText("Pontos: " + pontos);
    }

    // Animação de balanço
    private void balancarBotao(JButton botao) {
        final Point localOriginal = botao.getLocation();
        final int deslocamento = 1;
        final int duracao = 100;
        final int passos = 10;

        Timer timer = new Timer(duracao / passos, null);
        timer.addActionListener(new ActionListener() {
            int count = 0;
            boolean direita = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= passos) {
                    timer.stop();
                    botao.setLocation(localOriginal);
                } else {
                    int dx = (direita ? deslocamento : -deslocamento);
                    botao.setLocation(localOriginal.x + dx, localOriginal.y);
                    direita = !direita;
                    count++;
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CookieClickerComAba jogo = new CookieClickerComAba();

            jogo.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jogo.setUndecorated(true);

            jogo.setVisible(true);
        });
    }
}