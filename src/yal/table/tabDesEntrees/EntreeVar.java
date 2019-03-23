package yal.table.tabDesEntrees;

public class EntreeVar extends Entree{
    //une Entrée est un identificateur de variable.

    public EntreeVar(String nomEntree) {
        super(nomEntree);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        EntreeVar ev = (EntreeVar) obj;
        return this.nomEntree.equals(ev.getNomEntree()) &&
                this.noBloc == ev.getNoBloc();
    }

    @Override
    public int hashCode() {
        return ("Entree variable: " + nomEntree + " bloc: " + noBloc).hashCode();
    }

    @Override
    public String toString() {
        return nomEntree;
    }
}
