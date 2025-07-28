package com.jogojava.dagraoclicker.model;

public class GameState {
    private double pontos;
    private double clicksPerSecond;

    public GameState() {
        this.pontos = 0;
        this.clicksPerSecond = 0;
    }

    public double getPontos() {
        return pontos;
    }

    public void addPontos(double amount) {
        this.pontos += amount;
    }

    public void spendPontos(double amount) {
        this.pontos -= amount;
    }

    public double getClicksPerSecond() {
        return clicksPerSecond;
    }

    public void addClicksPerSecond(double amount) {
        this.clicksPerSecond += amount;
    }
}