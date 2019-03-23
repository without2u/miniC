package yal.table.Symboles;

import yal.arbre.instructions.Type;

public class SymboleTableau extends Symbole{
    public SymboleTableau(Type type) {
        super(type);
    }
    private int nbElement;
    private int deplacement;

    public void setNbElement(int nbElement) {
        this.nbElement = nbElement;
    }

    public int getNbElement() {
        return nbElement;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    @Override
    public String toString() {
        return "SymboleTableau{" +
                "nbElement=" + nbElement +
                ", deplacement=" + deplacement +
                '}';
    }
}
