package Cellule;

import gui.GUISimulator;
import gui.Simulable;

public abstract class Grille implements Simulable {

    protected int n;
    protected int m;
    protected Cellule[][] grilleInitiale;
    protected Cellule[][] grilleAvant;
    protected Cellule[][] grilleApres;

    protected GUISimulator window;

    protected int[] voisinsLigne;
    protected int[] voisinsColonne;

    public Grille(Cellule[][] grille, GUISimulator window) {
        this.n = grille.length;
        this.m = grille[0].length;
        this.grilleInitiale = new Cellule[n][m];
        this.grilleAvant = new Cellule[n][m];
        this.grilleApres = new Cellule[n][m];
        this.window = window;

        for (int i = 0; i < n; i++) {
            this.grilleInitiale[i] = new Cellule[m];
            this.grilleAvant[i] = new Cellule[m];
            this.grilleApres[i] = new Cellule[m];

            for (int j = 0; j < m; j++) {
                this.grilleInitiale[i][j] = new Cellule(grille[i][j]);
                this.grilleAvant[i][j] = new Cellule(grille[i][j]);
                this.grilleApres[i][j] = new Cellule(grille[i][j]);
            }
        }
    }

    protected abstract void dessiner(Cellule[][] grilleAvant, Cellule[][] grilleDessin);

    @Override
    public abstract void next();

    @Override
    public void restart() {
        window.reset();
        dessiner(grilleAvant, grilleInitiale);
    }
}
