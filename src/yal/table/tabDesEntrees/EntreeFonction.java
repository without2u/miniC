package yal.table.tabDesEntrees;

public class EntreeFonction extends Entree {

    private int nombreParamFonction;

    public EntreeFonction(String nomEntreefonction, int nombreParamFonction) {
        super(nomEntreefonction);
        this.nombreParamFonction = nombreParamFonction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        EntreeFonction ef = (EntreeFonction) obj;
        return this.nomEntree.equals(ef.getNomEntree()) &&
                this.noBloc == ef.getNoBloc() &&
                this.nombreParamFonction == ef.getNombreParamFonction();
    }

    public int getNombreParamFonction() {
        return nombreParamFonction;
    }


    @Override
    public String toString() {
        return nomEntree;
    }

    @Override
    public int hashCode() {
        return ("Entree fonction: " + nomEntree + " bloc: " + noBloc +
                " nbParam: " + nombreParamFonction).hashCode();
    }
}
