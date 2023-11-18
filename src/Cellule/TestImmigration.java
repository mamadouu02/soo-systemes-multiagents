package cellule;

import java.awt.*;

import gui.GUISimulator;

public class TestImmigration {

    public static void main(String[] args) {
        int nbEtats = 5;
        Cellule[][] grille = new Cellule[15][12];

        for (int i = 0; i < 15; i++) {
            grille[i] = new Cellule[12];

            for (int j = 0; j < 12; j++) {
                grille[i][j] = new Cellule(nbEtats);
            }
        }

        GUISimulator window = new GUISimulator(1000, 1000, Color.WHITE);
        window.setSimulable(new Immigration(grille, nbEtats, window));
    }
}
