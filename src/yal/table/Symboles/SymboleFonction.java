package yal.table.Symboles;


import yal.arbre.instructions.Type;

public class SymboleFonction extends Symbole {

    private int etiquetteSymbole;
    private int nbrParamSymbole;


    public SymboleFonction(Type type) {
        super(type);
    }



    public void setEtiquetteSymbole(int etiquetteSymbole) {
        this.etiquetteSymbole = etiquetteSymbole;
    }


    public int getEtiquetteSymbole() {
        return etiquetteSymbole;
    }

    public int getNbrParamSymbole() {
        return nbrParamSymbole;
    }

    @Override
    public String toString() {

        return " type de retour : "+ getTypeS() +
                " numero bloc :" + getNoBlocS() +
                " etiquette symbole :" + getEtiquetteSymbole();
    }

    public void setNbrParamSymbole(int nbrParamSymbole) {
        this.nbrParamSymbole = nbrParamSymbole;
    }
}
