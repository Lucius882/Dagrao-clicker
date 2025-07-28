package com.jogojava.dagraoclicker.model;

public enum Upgrade {
    ESPADA("Espada", 10, 0.1, "imgs/espada.png"),
    GUERREIRO("Guerreiro", 100, 1, "imgs/guerreiro.png"),
    ARQUEIRO("Arqueiro", 100, 10, "imgs/arquero.png"),
    BRUXA("Bruxa", 120, 100, "imgs/bruxa.png"),
    GIGANTE_DE_MADEIRA("Gigante de Madeira", 150, 1000, "imgs/arvere_somos_nois.png"),
    O_ESCOLHIDO("O Escolhido", 200, 10000, "imgs/oEscolhido.png");

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
