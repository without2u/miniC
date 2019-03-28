package yal.table.Symboles;

import yal.arbre.instructions.Type;

public class SymboleVar extends Symbole{
    private int deplacement;

    private int espace;
    public SymboleVar(Type typeV) {

        super(typeV);

        espace = 4;

    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    public int getDeplacement() {
        return deplacement;
    }

    @Override
    public String toString() {
        return "type :" + typeS + " bloc : " + noBlocS + " deplacement :" + deplacement;
    }

    public int getEspace() {
        return espace;
    }
}
