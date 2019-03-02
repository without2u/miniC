package yal.table.Symboles;

import yal.arbre.instructions.Type;

public abstract class Symbole {
    protected Type type;
    protected int numBloc;

    public Symbole(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public int getNumBloc() {
        return numBloc;
    }

    public void setNumBloc(int numBloc) {
        this.numBloc = numBloc;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
