import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dagrao_clicker extends JFrame {

    private int pontos = 0;
    private int multiplicador = 1;

    private JLabel labelPontos;
    private JButton botaoCookie;

    private JLabel labelUpgradeInfo;
    private JButton botaoComprarUpgrade;

    private ImageIcon[] frames;
    private int frameWidth;
    private int frameHeight;
    private int escala = 6;

    public Dagrao_clicker() {
        super("Cookie Clicker com Abas");
        carregarSprites();

        int larguraBotao = frameWidth * escala;
        int alturaBotao = frameHeight * escala;

        JTabbedPane abas = new JTabbedPane();

        JPanel painelJogo = new JPanel(null);
        painelJogo.setBackground(new Color(240, 230, 200));

        labelPontos = new JLabel("Pontos: 0", SwingConstants.CENTER);
        labelPontos.setFont(new Font("Arial", Font.BOLD, 32));
        labelPontos.setBounds(50, 20, 500, 50);
        painelJogo.add(labelPontos);

        botaoCookie = new JButton(frames[0]);
        botaoCookie.setMargin(new Insets(0, 0, 0, 0));
        botaoCookie.setBorderPainted(false);
        botaoCookie.setContentAreaFilled(false);
        botaoCookie.setFocusPainted(false);
        botaoCookie.setOpaque(false);
        botaoCookie.setBounds(200, 150, larguraBotao, alturaBotao);

        botaoCookie.addActionListener(e -> {
            pontos += multiplicador;
            atualizarPontuacao();
            balancarBotao(botaoCookie);
        });

        painelJogo.add(botaoCookie);

        iniciarAnimacaoSprite();

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

    private void atualizarPontuacao() {
        labelPontos.setText("Pontos: " + pontos);
    }

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

    private void carregarSprites() {
        try {
            File imagem = new File("imgs/ovo_dagrao.png");
            if (!imagem.exists()) {
                System.out.println("Arquivo não encontrado: " + imagem.getAbsolutePath());
                return;
            }

            BufferedImage spriteSheet = javax.imageio.ImageIO.read(imagem);
            frameWidth = spriteSheet.getWidth() / 2;
            frameHeight = spriteSheet.getHeight();

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
        Timer animacao = new Timer(300, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                botaoCookie.setIcon(frames[index]);
                index = (index + 1) % frames.length;
            }
        });
        animacao.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dagrao_clicker jogo = new Dagrao_clicker();
            jogo.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jogo.setUndecorated(true);
            jogo.setVisible(true);
        });
    }
}
