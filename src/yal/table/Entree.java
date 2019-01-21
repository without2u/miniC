package yal.table;

import java.util.Objects;

public class Entree {
    //une Entrée est un identificateur de variable.
    private  String nomDentree;

    public Entree(String nom){

        this.nomDentree=nom;

    }

    public String getNomDentree() {
        return nomDentree;
    }

    public void setNomDentree(String nomDentree) {
        this.nomDentree = nomDentree;
    }

    @Override
    public String toString() {
        return this.nomDentree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entree entree = (Entree) o;
        return Objects.equals(nomDentree, entree.nomDentree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomDentree);
    }
}
