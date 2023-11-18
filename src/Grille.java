package cellule;

import gui.GUISimulator;
import gui.Simulable;

public abstract class Grille implements Simulable {

    protected int n;
    protected int m;
    protected Cellule[][] grilleInitiale;
    protected Cellule[][] grilleAvant;
    protected Cellule[][] grilleApres;

    protected GUISimulator window;

    // Liste des lignes où tu peux avoir un voisin (prenant en compte les bords).
    protected int[] voisinsLigne;
    // Pareil avec les colonnes.
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

    protected String hexColor(int etat) {
        // Calcule les composantes RGB en fonction de l'état.
        int red = (int) (Math.sin(etat * 0.3) * 127 + 128);
        int green = (int) (Math.sin(etat * 0.3 + 2) * 127 + 128);
        int blue = (int) (Math.sin(etat * 0.3 + 4) * 127 + 128);

        // Convertit les composantes RGB en hexadécimal.
        String color = String.format("#%02X%02X%02X", red, green, blue);
        return color;
    }

    @Override
    public abstract void next();

    @Override
    public void restart() {
        window.reset();
        dessiner(grilleAvant, grilleInitiale);
    }
}
