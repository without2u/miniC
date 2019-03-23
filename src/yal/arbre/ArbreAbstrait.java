package yal.arbre;


import yal.exceptions.AnalyseException;

public abstract class ArbreAbstrait {
    protected int noLigne;
    protected int noBloc;
    
    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }
    
    public int getNoLigne() {
            return noLigne ;
    }

    public abstract void verifier() throws AnalyseException;
    public abstract String toMIPS();

    public int getNoBloc() {
        return noBloc;
    }


    public void setNoBloc(int noBloc) {
        this.noBloc = noBloc;
    }

    public void setNoLigne(int noLigne) {
        this.noLigne = noLigne;
    }
}
