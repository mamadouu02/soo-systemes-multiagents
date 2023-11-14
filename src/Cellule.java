import java.util.Random;

public class Cellule {
    private int etat;
    private int nbEtats;

    public Cellule() {
        Random random = new Random();
        setEtat(random.nextInt(2));
        this.nbEtats = 2;
    }

    public Cellule(int nbEtats) {
        Random random = new Random();
        setEtat(random.nextInt(nbEtats));
        this.nbEtats = nbEtats;
    }

    public Cellule(int etat, int nbEtats) {
        setEtat(etat);
        this.nbEtats = nbEtats;
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
        return "Etat :" + this.etat;
    }
}
