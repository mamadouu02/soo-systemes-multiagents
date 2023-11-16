package Cellule;
import java.awt.*;
import java.util.*;

import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;

public class Schelling implements Simulable {
    private final int n;
    private final int m;
    private final int K;
    private final Cellule[][] grilleInitiale;
    private final Cellule[][] grilleAvant;
    private final Cellule[][] grilleApres;
    private final Queue<Point> vacantAvant = new LinkedList<Point>();
    private final Queue<Point> tmp = new LinkedList<Point>();
    private final GUISimulator window;

    int[] voisinsLigne;
    int[] voisinsColonne;

    public Schelling(Cellule[][] grille, int nbEtats, int K, GUISimulator window) {
        this.n = grille.length;
        this.m = grille[0].length;
        this.grilleInitiale = new Cellule[n][m];
        this.grilleAvant = new Cellule[n][m];
        this.grilleApres = new Cellule[n][m];
        this.window = window;
        this.K = K;

        for (int i = 0; i < this.n; i++) {
            this.grilleInitiale[i] = new Cellule[m];
            this.grilleAvant[i] = new Cellule[m];
            this.grilleApres[i] = new Cellule[m];

            for (int j = 0; j < this.m; j++) {
                this.grilleInitiale[i][j] = new Cellule(grille[i][j]);
                this.grilleAvant[i][j] = new Cellule(grille[i][j]);
                this.grilleApres[i][j] = new Cellule(grille[i][j]);

                if (grilleInitiale[i][j].getEtat() == 0) {
                    vacantAvant.add(new Point(i, j));
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

                    if (couleursDiff.size() >= K) {
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

        while (!tmp.isEmpty()) {
            Point point = tmp.remove();
            vacantAvant.add(new Point(point));
        }

        window.reset();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grilleAvant[i][j] = new Cellule(grilleApres[i][j]);
                int etat = grilleAvant[i][j].getEtat();

                if (etat == 0) {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.WHITE, 50, 50));
                } else {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK,
                            Color.decode("#" + (etat * etat) + (etat * etat)), 50, 50));
                }
            }
        }
    }

    @Override
    public void restart() {
        window.reset();

        while (!(vacantAvant.isEmpty())) {
            vacantAvant.remove();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grilleAvant[i][j] = new Cellule(grilleInitiale[i][j]);
                grilleApres[i][j] = new Cellule(grilleInitiale[i][j]);

                if (grilleInitiale[i][j].getEtat() == 0) {
                    vacantAvant.add(new Point(i, j));
                }

                int etat = grilleAvant[i][j].getEtat();

                if (etat == 0) {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.WHITE, 50, 50));
                } else {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK,
                            Color.decode("#" + (etat * etat) + (etat * etat)), 50, 50));
                }
            }
        }
    }
}
