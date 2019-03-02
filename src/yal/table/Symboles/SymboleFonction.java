package yal.table.Symboles;

import yal.arbre.instructions.Type;

public class SymboleFonction extends Symbole {

    private int etiquette;
    private int nbParam;

    public SymboleFonction(Type type) {
        super(type);
    }

    public int getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(int etiquette) {
        this.etiquette = etiquette;
    }


    public int getNbParam() {
        return nbParam;
    }

    public void setNbParam(int nbParam) {
        this.nbParam = nbParam;
    }

    @Override
    public String toString() {
        return "type retour: " + type + " bloc " + getNumeroBloc() + " etiquette:" + etiquette;
    }

}
