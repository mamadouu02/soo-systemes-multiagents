package Cellule;

import java.awt.*;

import gui.GUISimulator;

public class TestSchelling {

    public static void main(String[] args) {
        int nbEtats = 4;
        int K = 2;
        Cellule[][] grille = new Cellule[10][10];

        for (int i = 0; i < 10; i++) {
            grille[i] = new Cellule[10];

            for (int j = 0; j < 10; j++) {
                grille[i][j] = new Cellule(nbEtats);
            }
        }

        GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
        window.setSimulable(new Schelling(grille, nbEtats, K, window));
    }
}
