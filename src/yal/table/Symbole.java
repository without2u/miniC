package yal.table;

import yal.arbre.instructions.Type;

public class Symbole {
    //un Symbole est réduit au déplacement par rapport au début de la zone des variables

    private Type type;
    private int deplacement;

    public Symbole(Type t){
        this.type=t;
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
