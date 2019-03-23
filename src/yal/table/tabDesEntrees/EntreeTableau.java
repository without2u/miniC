package yal.table.tabDesEntrees;

public class EntreeTableau extends Entree{
    public EntreeTableau(String nomEntree) {
        super(nomEntree);
    }

    @Override
    public int hashCode() {
        return ("tableau: " + nomEntree + " bloc: " + noBloc).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        EntreeTableau et = (EntreeTableau) obj;
        return this.nomEntree.equals(et.getNomEntree()) &&
                this.noBloc == et.getNoBloc();
    }

    @Override
    public String toString() {
        return nomEntree;
    }
}
