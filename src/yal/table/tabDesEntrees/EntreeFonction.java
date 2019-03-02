package yal.table.tabDesEntrees;

public class EntreeFonction extends Entree {

    private int nbParam;


    public EntreeFonction(String nomEntree, int nbParam) {
        super(nomEntree);
        this.nbParam = nbParam;
    }


    public int getNbParam() {
        return nbParam;
    }


    public void setNbParam(int nbParam) {
        this.nbParam = nbParam;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        EntreeFonction ef = (EntreeFonction) obj;
        return this.nomEntree.equals(ef.getNomEntree()) &&
                this.numeroBloc == ef.getNumeroBloc() &&
                this.nbParam == ef.getNbParam();
    }


    @Override
    public int hashCode() {
        return ("fonction: " + nomEntree + " bloc: " + numeroBloc + " nbParam: " + nbParam).hashCode();
    }


    @Override
    public String toString() {
        return nomEntree;
    }

}
