package yal.table.Symboles;

import yal.arbre.instructions.Type;
import yal.table.TDS;

public class SymboleTableau extends Symbole{

    private int nbElement;
    private int deplacement;

    private int espace;
    public SymboleTableau(Type type) {
        super(type);
        espace = 4;
        deplacement= - TDS.getInstance().tailleZoneDesVariables();


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

    @Override
    public String toString() {
        return "SymboleTableau{" +
                "nbElement=" + nbElement +
                ", deplacement=" + deplacement +
                '}';
    }

    public int getEspace() {
        return espace;
    }
}
