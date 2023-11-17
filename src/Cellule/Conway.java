package Cellule;

import java.awt.*;

import gui.GUISimulator;
import gui.Rectangle;

public class Conway extends Grille {

    private int voisinsVivant;

    public Conway(Cellule[][] grille, GUISimulator window) {
        super(grille, window);
    }

    @Override
    protected void dessiner(Cellule[][] grilleAvant, Cellule[][] grilleDessin) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grilleAvant[i][j] = new Cellule(grilleDessin[i][j]);

                // On ajoute la cellule sous forme d'un rectangle de couleur différente (noir ou blanc)  selon son état.
                if (grilleAvant[i][j].getEtat() == 1) {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.BLACK, 50, 50));
                } else {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.WHITE, 50, 50));
                }
            }
        }
    }

    @Override
    public void next() {
        window.reset();

        for (int i = 0; i < n; i++) {
            voisinsLigne = new int[] { (i - 1) % n == -1 ? n - 1 : (i - 1) % n, i % n, (i + 1) % n };

            for (int j = 0; j < m; j++) {
                voisinsColonne = new int[] { (j - 1) % m == -1 ? m - 1 : (j - 1) % m, j % m, (j + 1) % m };
                voisinsVivant = 0;

                for (int voisinLigne : voisinsLigne) {
                    for (int voisinColonne : voisinsColonne) {
                        if (!(voisinLigne == i % n && voisinColonne == j % m)) {
                            if (grilleAvant[voisinLigne][voisinColonne].getEtat() == 1) {
                                voisinsVivant++;
                            }
                        }
                    }
                }

                if (grilleAvant[i][j].getEtat() == 0 && voisinsVivant == 3) {
                    grilleApres[i][j].setEtat(1);
                }

                else if (!(grilleAvant[i][j].getEtat() == 1 && (voisinsVivant == 2 || voisinsVivant == 3))) {
                    grilleApres[i][j].setEtat(0);
                }
            }
        }

        dessiner(grilleAvant, grilleApres);
    }
}
