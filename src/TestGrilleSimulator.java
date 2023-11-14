import java.awt.*;

import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;

public class TestGrilleSimulator {
    public static void main(String[] args) {
        GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
        Cellule[][] grille = new Cellule[10][10];

        for (int i = 0; i < 10; i++) {
            grille[i] = new Cellule[10];

            for (int j = 0; j < 10; j++) {
                grille[i][j] = new Cellule();
            }
        }

        window.setSimulable(new GrilleSimulator(grille, 10, 10, window));
    }
}

class GrilleSimulator implements Simulable {

    private final int n;
    private final int m;
    private final Cellule[][] grilleInitiale;
    private final Cellule[][] grilleAvant;
    private final Cellule[][] grilleApres;
    private final GUISimulator window;

    public GrilleSimulator(Cellule[][] grille, int n, int m, GUISimulator window) {
        this.n = n;
        this.m = m;
        this.grilleInitiale = new Cellule[n][m];
        this.grilleAvant = new Cellule[n][m];
        this.grilleApres = new Cellule[n][m];
        this.window = window;

        for (int i = 0; i < this.n; i++) {
            this.grilleInitiale[i] = new Cellule[m];
            this.grilleAvant[i] = new Cellule[m];
            this.grilleApres[i] = new Cellule[m];

            for (int j = 0; j < this.m; j++) {
                this.grilleInitiale[i][j] = new Cellule();
                this.grilleInitiale[i][j].setEtat(grille[i][j].getEtat());

                this.grilleAvant[i][j] = new Cellule();
                this.grilleAvant[i][j].setEtat(grille[i][j].getEtat());

                this.grilleApres[i][j] = new Cellule();
            }
        }
    }

    int[] voisinsLigne;
    int[] voisinsColonne;
    int voisinVivant;

    @Override
    public void next() {
        window.reset();

        for (int i = 0; i < n; i++) {
            voisinsLigne = new int[] { (i-1)%n == -1 ? n-1 : (i-1)%n , i%n, (i+1)%n };

            for (int j = 0; j < m; j++) {
                voisinsColonne = new int[] { (j-1)%m == -1 ? m-1 : (j-1)%m, j%m, (j+1)%m };
                voisinVivant = 0;

                for (int voisinLigne : voisinsLigne) {
                    for (int voisinColonne : voisinsColonne) {
                        if (!(voisinLigne == i%n && voisinColonne == j%m)) {
                            if (grilleAvant[voisinLigne][voisinColonne].getEtat() == 1) {
                                voisinVivant++;
                            }
                        }
                    }
                }

                if (grilleAvant[i][j].getEtat() == 0 && voisinVivant == 3) {
                    grilleApres[i][j].setEtat(1);
                }

                else if (!(grilleAvant[i][j].getEtat() == 1 && (voisinVivant == 2 || voisinVivant == 3))) {
                    grilleApres[i][j].setEtat(0);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grilleAvant[i][j].setEtat(grilleApres[i][j].getEtat());

                if (grilleAvant[i][j].getEtat() == 1) {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.BLACK, 50, 50));
                } else {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.WHITE, 50, 50));
                }
            }
        }
    }

    @Override
    public void restart() {
        window.reset();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grilleAvant[i][j].setEtat(grilleInitiale[i][j].getEtat());

                if (grilleAvant[i][j].getEtat() == 1) {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.BLACK, 50, 50));
                } else {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.WHITE, 50, 50));
                }
            }
        }
    }
}
