package yal.table.Symboles;


import yal.arbre.instructions.Type;

public class SymboleFonction extends Symbole {

    private int etiquetteSymbole;
    private int nbrParamSymbole;


    public SymboleFonction(Type type) {
        super(type);
    }

    @Override
    public int getNumBloc() {
        return super.getNumBloc();
    }

    @Override
    public void setNumBloc(int numBloc) {
        super.setNumBloc(numBloc);
    }

    @Override
    public void setType(Type type) {
        super.setType(type);
    }

    public void setEtiquetteSymbole(int etiquetteSymbole) {
        this.etiquetteSymbole = etiquetteSymbole;
    }

    @Override
    public Type getType() {
        return super.getType();
    }

    public int getEtiquetteSymbole() {
        return etiquetteSymbole;
    }

    public int getNbrParamSymbole() {
        return nbrParamSymbole;
    }

    @Override
    public String toString() {

        return " type de retour: "+ getType() +
                " bloc " + getNumBloc() +
                " etiquette:" + getEtiquetteSymbole();
    }

    public void setNbrParamSymbole(int nbrParamSymbole) {
        this.nbrParamSymbole = nbrParamSymbole;
    }
}
