import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class DagraoClicker extends JFrame {
    private int pontos = 0;
    private int multiplicador = 1;
    private int autoClickers = 0;

    private JLabel labelPontos;
    private JButton botaoDragao;

    private JPanel painelMelhoriasCompradas;
    private Timer autoClickTimer;

    private ImageIcon[] frames;
    private int frameWidth, frameHeight;
    private int escala = 6;

    public DagraoClicker() {
        super("Dagrão Clicker");

        carregarSprites();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Painel Principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // PAINEL ESQUERDA - Dragão e Pontos
        JPanel painelEsquerda = new JPanel();
        painelEsquerda.setLayout(new BoxLayout(painelEsquerda, BoxLayout.Y_AXIS));
        painelEsquerda.setBackground(new Color(240, 230, 200));
        painelEsquerda.setPreferredSize(new Dimension(350, getHeight()));

        labelPontos = new JLabel("Pontos: 0", SwingConstants.CENTER);
        labelPontos.setFont(new Font("Arial", Font.BOLD, 32));
        labelPontos.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelEsquerda.add(Box.createRigidArea(new Dimension(0, 30)));
        painelEsquerda.add(labelPontos);

        botaoDragao = new JButton(frames[0]);
        botaoDragao.setBorderPainted(false);
        botaoDragao.setContentAreaFilled(false);
        botaoDragao.setFocusPainted(false);
        botaoDragao.setOpaque(false);
        botaoDragao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoDragao.addActionListener(e -> {
            pontos += multiplicador;
            atualizarPontuacao();
            balancarBotao(botaoDragao);
        });
        painelEsquerda.add(Box.createRigidArea(new Dimension(0, 50)));
        painelEsquerda.add(botaoDragao);

        painelPrincipal.add(painelEsquerda, BorderLayout.WEST);

        // PAINEL CENTRAL - Melhorias compradas
        painelMelhoriasCompradas = new JPanel();
        painelMelhoriasCompradas.setLayout(new BoxLayout(painelMelhoriasCompradas, BoxLayout.Y_AXIS));
        painelMelhoriasCompradas.setBackground(new Color(250, 245, 230));
        JScrollPane scrollCentro = new JScrollPane(painelMelhoriasCompradas);
        painelPrincipal.add(scrollCentro, BorderLayout.CENTER);

        // PAINEL DIREITA - Loja
        JPanel painelLoja = new JPanel();
        painelLoja.setLayout(new BoxLayout(painelLoja, BoxLayout.Y_AXIS));
        painelLoja.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelLoja.setBackground(new Color(220, 220, 220));
        painelLoja.setPreferredSize(new Dimension(300, getHeight()));

        JLabel labelLoja = new JLabel("Loja");
        labelLoja.setFont(new Font("Arial", Font.BOLD, 24));
        labelLoja.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoCursor = new JButton("Comprar Cursor (15 pontos)");
        botaoCursor.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCursor.setIcon(new ImageIcon("imgs/cursor.png")); // Substitua pelo seu ícone
        botaoCursor.addActionListener(e -> {
            if (pontos >= 15) {
                pontos -= 15;
                autoClickers++;
                atualizarPontuacao();
                painelMelhoriasCompradas.add(new JLabel("Cursor adquirido #" + autoClickers, new ImageIcon("imgs/cursor.png"), JLabel.LEFT));
                painelMelhoriasCompradas.revalidate();
                painelMelhoriasCompradas.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Você precisa de pelo menos 15 pontos!", "Sem pontos", JOptionPane.WARNING_MESSAGE);
            }
        });

        painelLoja.add(labelLoja);
        painelLoja.add(Box.createRigidArea(new Dimension(0, 20)));
        painelLoja.add(botaoCursor);
        painelPrincipal.add(painelLoja, BorderLayout.EAST);

        // Adiciona painel principal à janela
        add(painelPrincipal);

        iniciarAnimacaoSprite();
        iniciarAutoClick();
    }

    private void atualizarPontuacao() {
        labelPontos.setText("Pontos: " + pontos);
    }

    private void balancarBotao(JButton botao) {
        final Point localOriginal = botao.getLocation();
        final int deslocamento = 2;
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

    private void iniciarAutoClick() {
        autoClickTimer = new Timer(1000, e -> {
            if (autoClickers > 0) {
                pontos += autoClickers;
                atualizarPontuacao();
            }
        });
        autoClickTimer.start();
    }

    private void carregarSprites() {
        try {
            File imagem = new File("imgs/ovo_dagrao.png");
            if (!imagem.exists()) {
                System.out.println("Arquivo não encontrado: " + imagem.getAbsolutePath());
                return;
            }

            BufferedImage spriteSheet = ImageIO.read(imagem);
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
                botaoDragao.setIcon(frames[index]);
                index = (index + 1) % frames.length;
            }
        });
        animacao.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DagraoClicker jogo = new DagraoClicker();
            jogo.setVisible(true);
        });
    }
}
