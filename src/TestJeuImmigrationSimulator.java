import java.awt.*;

import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;

public class TestJeuImmigrationSimulator {
    public static void main(String[] args) {
        GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
        Cellule[][] grille = new Cellule[10][10];
        int nbEtats = 5;

        for (int i = 0; i < 10; i++) {
            grille[i] = new Cellule[10];

            for (int j = 0; j < 10; j++) {
                grille[i][j] = new Cellule(nbEtats);
            }
        }

        window.setSimulable(new JeuImmigrationSimulator(grille, 10, 10, nbEtats, window));
    }
}

class JeuImmigrationSimulator extends Grille {
    private final int nbEtats;

    public JeuImmigrationSimulator(Cellule[][] grille, int n, int m, int nbEtats, GUISimulator window) {
        super(grille, n, m, window);
        this.nbEtats = nbEtats;
    }

    @Override
    protected void dessiner(Cellule[][] grilleAvant, Cellule[][] grilleDessin) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grilleAvant[i][j].setEtat(grilleDessin[i][j].getEtat());

                int etat = grilleAvant[i][j].getEtat();
                window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.decode("#"+(etat*10)+(etat*10)), 50, 50));
            }
        }
    }

    int[] voisinsLigne;
    int[] voisinsColonne;
    int voisinEtatPlus1;
    @Override
    public void next() {
        window.reset();

        for (int i = 0; i < n; i++) {
            voisinsLigne = new int[] { (i-1)%n == -1 ? n-1 : (i-1)%n , i%n, (i+1)%n };

            for (int j = 0; j < m; j++) {
                int etat = grilleAvant[i][j].getEtat();
                voisinsColonne = new int[] { (j-1)%m == -1 ? m-1 : (j-1)%m, j%m, (j+1)%m };
                voisinEtatPlus1 = 0;

                for (int voisinLigne : voisinsLigne) {
                    for (int voisinColonne : voisinsColonne) {
                        if (!(voisinLigne == i%n && voisinColonne == j%m)) {
                            if (grilleAvant[voisinLigne][voisinColonne].getEtat() == (etat+1)%nbEtats) {
                                voisinEtatPlus1++;
                            }
                        }
                    }
                }
                if (voisinEtatPlus1 >= 3) {
                    grilleApres[i][j].setEtat((etat+1)%nbEtats);
                }
            }
        }
    dessiner(grilleAvant, grilleApres);
    }

    @Override
    public void restart() {
        window.reset();
        dessiner(grilleAvant, grilleInitiale);
    }
}
