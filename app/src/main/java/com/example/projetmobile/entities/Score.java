package com.example.projetmobile.entities;

public class Score extends BaseEntity{
    private String pseudo;
    private Integer score;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
