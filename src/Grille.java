import java.awt.*;

import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;

public abstract class Grille implements Simulable {
    protected int n;
    protected int m;
    protected Cellule[][] grilleInitiale;
    protected Cellule[][] grilleAvant;
    protected Cellule[][] grilleApres;

    protected GUISimulator window;

    public Grille(Cellule[][] grille, int n, int m, GUISimulator window) {
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
                this.grilleApres[i][j].setEtat(grille[i][j].getEtat());
            }
        }
    }
    protected abstract void dessiner(Cellule[][] grilleAvant, Cellule[][] grilleDessin);
    @Override
    public abstract void next();

    @Override
    public abstract void restart();
}
