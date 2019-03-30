package yal.table.Symboles;

import yal.arbre.instructions.Type;


public class SymboleTableau extends Symbole{

    private int nbElement;
    protected int deplacement;

    public SymboleTableau(Type type) {
        super(type);

    }

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
}
