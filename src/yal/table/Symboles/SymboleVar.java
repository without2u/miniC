package yal.table.Symboles;

import yal.arbre.instructions.Type;

public class SymboleVar extends Symbole{
    private int deplacement;

    public SymboleVar(Type typeV) {

        super(typeV);

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

}
