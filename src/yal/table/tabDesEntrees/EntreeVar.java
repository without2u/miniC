package yal.table.tabDesEntrees;

import java.util.Objects;

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
                this.numeroBloc == ev.getNumeroBloc();
    }


    @Override
    public int hashCode() {
        return ("variable: " + nomEntree + " bloc: " + numeroBloc).hashCode();
    }


    @Override
    public String toString() {
        return nomEntree;
    }
}
