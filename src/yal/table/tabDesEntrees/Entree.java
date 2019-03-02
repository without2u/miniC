package yal.table.tabDesEntrees;

public abstract class Entree {
    protected String nomEntree;
    protected int numeroBloc;


    public Entree(String nomEntree) {
        this.nomEntree = nomEntree;
    }

    public int getNumeroBloc() {
        return numeroBloc;
    }

    public void setNumeroBloc(int numeroBloc) {
        this.numeroBloc = numeroBloc;
    }

    public String getNomEntree() {
        return nomEntree;
    }
}
