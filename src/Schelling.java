package cellule;

import java.awt.*;
import java.util.*;

import gui.GUISimulator;
import gui.Rectangle;

public class Schelling extends Grille {
    
    private final int K;

    // Une file des habitations actuellement libres.
    private final Queue<Point> vacantAvant = new LinkedList<Point>();

    // Une file des habitations qu'on va rendre libres à l'état d'après (après un next).
    private final Queue<Point> tmp = new LinkedList<Point>();

    public Schelling(Cellule[][] grille, int nbEtats, int K, GUISimulator window) {
        super(grille, window);
        this.K = K;

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if (grilleInitiale[i][j].getEtat() == 0) {
                    vacantAvant.add(new Point(i, j));
                }
            }
        }
    }

    @Override
    public void dessiner(Cellule[][] grilleAvant, Cellule[][] grilleDessin) {
        dessiner(grilleAvant, this.grilleApres, grilleDessin, this.vacantAvant);
    }

    public void dessiner(Cellule[][] grilleAvant, Cellule[][] grilleApres, Cellule[][] grilleDessin,
            Queue<Point> vacantAvant) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                grilleAvant[i][j] = new Cellule(grilleDessin[i][j]);
                if (grilleApres != null) {
                    grilleApres[i][j] = new Cellule(grilleDessin[i][j]);
                }

                if (vacantAvant != null) {
                    if (grilleDessin[i][j].getEtat() == 0) {
                        vacantAvant.add(new Point(i, j));
                    }
                }

                int etat = grilleAvant[i][j].getEtat();

                if (etat == 0) {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.WHITE, 50, 50));
                } else {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK,
                            Color.decode(hexColor(etat)), 50, 50));
                }
            }
        }
    }

    @Override
    public void next() {
        for (int i = 0; i < n; i++) {
            voisinsLigne = new int[] { (i - 1) % n == -1 ? n - 1 : (i - 1) % n, i % n, (i + 1) % n };

            for (int j = 0; j < m; j++) {
                int etat = grilleAvant[i][j].getEtat();

                if (etat != 0) {
                    voisinsColonne = new int[] { (j - 1) % m == -1 ? m - 1 : (j - 1) % m, j % m, (j + 1) % m };

                    // Un ensemble de couleurs distinctes pour compter le nombre de couleurs différentes sur les
                    // voisins d'une cellule.
                    Set<Integer> couleursDiff = new HashSet<Integer>();

                    for (int voisinLigne : voisinsLigne) {
                        for (int voisinColonne : voisinsColonne) {
                            if (!(voisinLigne == i && voisinColonne == j)) {
                                if (grilleAvant[voisinLigne][voisinColonne].getEtat() != etat
                                        && grilleAvant[voisinLigne][voisinColonne].getEtat() != 0) {
                                    couleursDiff.add(grilleAvant[voisinLigne][voisinColonne].getEtat());
                                }
                            }
                        }
                    }

                    if (couleursDiff.size() > K) {
                        if (!vacantAvant.isEmpty()) {
                            grilleApres[i][j].setEtat(0);
                            tmp.add(new Point(i, j));
                            Point nouveau = vacantAvant.remove();
                            grilleApres[nouveau.x][nouveau.y] = new Cellule(grilleAvant[i][j]);
                        }
                    }
                }
            }
        }

        // On vide tmp pour remplir VacantAvant, correspondant donc aux logements vacants actuels.
        while (!tmp.isEmpty()) {
            Point point = tmp.remove();
            vacantAvant.add(new Point(point));
        }

        window.reset();
        dessiner(grilleAvant, null, grilleApres, null);

    }

    @Override
    public void restart() {

        while (!(vacantAvant.isEmpty())) {
            vacantAvant.remove();
        }

        super.restart();
    }
}
