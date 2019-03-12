package yal.table.tabDesEntrees;

public abstract class Entree{

    protected String nomEntree;
    protected int noBloc;
    public Entree(String nomEntree){

        this.nomEntree=nomEntree;


    }

    public int getNoBloc() {
        return noBloc;
    }

    public void setNoBloc(int noBloc) {
        this.noBloc = noBloc;
    }

    public void setNomEntree(String nomEntree) {
        this.nomEntree = nomEntree;
    }

    public String getNomEntree() {
        return nomEntree;
    }
}