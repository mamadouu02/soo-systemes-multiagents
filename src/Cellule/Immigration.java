package Cellule;

import java.awt.*;

import gui.GUISimulator;
import gui.Rectangle;

public class Immigration extends Grille {

    private final int nbEtats;
    private int voisinEtatPlus1;

    public Immigration(Cellule[][] grille, int nbEtats, GUISimulator window) {
        super(grille, window);
        this.nbEtats = nbEtats;
    }

    @Override
    protected void dessiner(Cellule[][] grilleAvant, Cellule[][] grilleDessin) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grilleAvant[i][j] = new Cellule(grilleDessin[i][j]);
                int etat = grilleAvant[i][j].getEtat();
                window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK,
                        Color.decode("#" + (etat * 10) + (etat * 10)), 50, 50));
            }
        }
    }

    @Override
    public void next() {
        window.reset();

        for (int i = 0; i < n; i++) {
            voisinsLigne = new int[] { (i - 1) % n == -1 ? n - 1 : (i - 1) % n, i % n, (i + 1) % n };

            for (int j = 0; j < m; j++) {
                int etat = grilleAvant[i][j].getEtat();
                voisinsColonne = new int[] { (j - 1) % m == -1 ? m - 1 : (j - 1) % m, j % m, (j + 1) % m };
                voisinEtatPlus1 = 0;

                for (int voisinLigne : voisinsLigne) {
                    for (int voisinColonne : voisinsColonne) {
                        if (!(voisinLigne == i % n && voisinColonne == j % m)) {
                            if (grilleAvant[voisinLigne][voisinColonne].getEtat() == (etat + 1) % nbEtats) {
                                voisinEtatPlus1++;
                            }
                        }
                    }
                }

                if (voisinEtatPlus1 >= 3) {
                    grilleApres[i][j].setEtat((etat + 1) % nbEtats);
                }
            }
        }

        dessiner(grilleAvant, grilleApres);
    }
}
