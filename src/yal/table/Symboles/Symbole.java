package yal.table.Symboles;

import yal.arbre.instructions.Type;

public abstract class Symbole {
    protected Type typeS;
    protected int noBlocS;

    public Symbole(Type type) {

        this.typeS = type;

    }

    public int getNoBlocS() {
        return noBlocS;
    }

    public void setNoBlocS(int noBlocS) {
        this.noBlocS = noBlocS;
    }

    public void setTypeS(Type typeS) {
        this.typeS = typeS;
    }

    public Type getTypeS() {
        return typeS;
    }

    @Override
    public String toString() {
        return "";
    }
}
