import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;

public class TestSchellingSimulation {
    public static void main(String[] args) {
        GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
        Cellule[][] grille = new Cellule[10][10];
        int nbEtats = 3;
        int K = 1;

        for (int i = 0; i < 10; i++) {
            grille[i] = new Cellule[10];

            for (int j = 0; j < 10; j++) {
                grille[i][j] = new Cellule(nbEtats);
            }
        }

        window.setSimulable(new SchellingSimulation(grille, 10, 10, nbEtats, K, window));
    }
}

class SchellingSimulation implements Simulable {
    private final int n;
    private final int m;
    private final int K;
    private final Cellule[][] grilleInitiale;
    private final Cellule[][] grilleAvant;
    private final Cellule[][] grilleApres;
    private final Queue<Point> vacantAvant = new LinkedList<Point> ();
    private final Queue<Point> tmp = new LinkedList<Point> ();
    private final GUISimulator window;

    public SchellingSimulation(Cellule[][] grille, int n, int m, int nbEtats, int K, GUISimulator window) {
        this.n = n;
        this.m = m;
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
                this.grilleInitiale[i][j] = new Cellule(nbEtats);
                this.grilleInitiale[i][j].setEtat(grille[i][j].getEtat());

                this.grilleAvant[i][j] = new Cellule(nbEtats);
                this.grilleAvant[i][j].setEtat(grille[i][j].getEtat());

                this.grilleApres[i][j] = new Cellule(nbEtats);
                this.grilleApres[i][j].setEtat(grille[i][j].getEtat());

                if (grilleInitiale[i][j].getEtat() == 0) {
                    vacantAvant.add(new Point(i, j));
                }
            }
        }
    }

    int[] voisinsLigne;
    int[] voisinsColonne;

    @Override
    public void next() {
        window.reset();

        for (int i = 0; i < n; i++) {
            voisinsLigne = new int[] { (i-1)%n == -1 ? n-1 : (i-1)%n , i%n, (i+1)%n };

            for (int j = 0; j < m; j++) {
                int etat = grilleAvant[i][j].getEtat();
                if (etat != 0) {
                    voisinsColonne = new int[]{(j - 1) % m == -1 ? m - 1 : (j - 1) % m, j % m, (j + 1) % m};
                    Set<Integer> couleursDiff = new HashSet<Integer>();

                    for (int voisinLigne : voisinsLigne) {
                        for (int voisinColonne : voisinsColonne) {
                            if (!(voisinLigne == i && voisinColonne == j)) {
                                if (grilleAvant[voisinLigne][voisinColonne].getEtat() != etat && grilleAvant[voisinLigne][voisinColonne].getEtat() != 0) {
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
                            grilleApres[nouveau.x][nouveau.y].setEtat(grilleAvant[i][j].getEtat());
                        }
                    }
                }
            }
        }

        while (!tmp.isEmpty()){
            vacantAvant.add(tmp.remove());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grilleAvant[i][j].setEtat(grilleApres[i][j].getEtat());

                int etat = grilleAvant[i][j].getEtat();
                if (etat == 0){
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.WHITE, 50, 50));
                } else {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.decode("#"+(etat*etat)+(etat*etat)), 50, 50));
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

                int etat = grilleAvant[i][j].getEtat();
                if (etat == 0){
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.WHITE, 50, 50));
                } else {
                    window.addGraphicalElement(new Rectangle(i * 50, j * 50, Color.BLACK, Color.decode("#" + (etat * etat) + (etat * etat)), 50, 50));
                }
            }
        }
    }
}