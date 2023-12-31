package cellule;

import java.util.Random;

public class Cellule {

    private int etat;
    private int nbEtats;

    public Cellule() {
        this(2);
    }

    public Cellule(int nbEtats) {
        // La cellule prend un état aléatoire parmi ceux disponibles.
        Random random = new Random();
        setEtat(random.nextInt(nbEtats));
        setNbEtat(nbEtats);
    }

    public Cellule(int etat, int nbEtats) {
        setEtat(etat);
        setNbEtat(nbEtats);
    }

    public Cellule(Cellule other) {
        this(other.getEtat(), other.getNbEtat());
    }

    public int getEtat() {
        return this.etat;
    }

    public int getNbEtat() {
        return this.nbEtats;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setNbEtat(int nbEtats) {
        this.nbEtats = nbEtats;
    }

    @Override
    public String toString() {
        return "Etat : " + this.etat;
    }
}
