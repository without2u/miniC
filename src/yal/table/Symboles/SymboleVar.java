package yal.table.Symboles;

import yal.arbre.instructions.Type;

public class SymboleVar  extends Symbole{
    private int deplacement;

    public SymboleVar(Type type) {
        super(type);
    }
    public Type getType() {
        return type;
    }
    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    @Override
    public String toString() {
        return "Symbole{" +
                "type=" + type +
                ", deplacement=" + deplacement +
                '}';
    }
}
