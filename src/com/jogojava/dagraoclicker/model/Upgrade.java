package com.jogojava.dagraoclicker.model;

public enum Upgrade {
    // Nome no Enum("Nome para Exibição", custoInicial, bonusCps, caminhoDaImagem)
    ESPADA("Espada", 20, 0.1, "imgs/espada.png"), // Você precisará criar uma imagem "espada.png"
    GUERREIRO("Guerreiro", 150, 1, "imgs/guerreiro.png"), // Você precisará criar a imagem "guerreiro.png"
    ARQUEIRO("Arqueiro", 1000, 10, "imgs/arquero.jpg"),
    BRUXA("Bruxa", 12000, 100, "imgs/bruxa.jpg"),
    GIGANTE_DE_MADEIRA("Gigante de Madeira", 150000, 1000, "imgs/arvere_somos_nois.jpg"),
    O_ESCOLHIDO("O Escolhido", 2000000, 10000, "imgs/oEscolhido.jpg");

    private final String nome;
    private final int custo;
    private final double bonusCps;
    private final String imagePath;

    Upgrade(String nome, int custo, double bonusCps, String imagePath) {
        this.nome = nome;
        this.custo = custo;
        this.bonusCps = bonusCps;
        this.imagePath = imagePath;
    }

    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }

    public double getBonusCps() {
        return bonusCps;
    }

    public String getImagePath() {
        return imagePath;
    }
}