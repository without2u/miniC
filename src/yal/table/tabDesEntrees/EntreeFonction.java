package yal.table.tabDesEntrees;

public class EntreeFonction extends Entree {

    private int nombreParamFonction;


    public EntreeFonction(String nomEntreefonction, int nombreParamFonction) {
        super(nomEntreefonction);
        this.nombreParamFonction = nombreParamFonction;
    }

    public int getNombreParamFonction() {
        return nombreParamFonction;
    }

    public void setNombreParamFonction(int nombreParamFonction) {
        this.nombreParamFonction = nombreParamFonction;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        EntreeFonction ef = (EntreeFonction) o;
        return this.nomEntree.equals(ef.getNomEntree()) &&
                this.numeroBloc == ef.getNumeroBloc() &&
                this.nombreParamFonction == ef.getNombreParamFonction();
    }

    @Override
    public int hashCode() {
        return ("fonction: " + nomEntree + " bloc: " + numeroBloc + " nbParam: " + nombreParamFonction).hashCode();
    }

    @Override
    public String toString() {
        return nomEntree;
    }
}
