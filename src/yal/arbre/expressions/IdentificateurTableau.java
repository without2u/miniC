package yal.arbre.expressions;

import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.ListeDErreurs;
import yal.exceptions.VariablePasDeclareeException;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleTableau;
import yal.table.Symboles.SymboleVar;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeTableau;
import yal.table.tabDesEntrees.EntreeVar;

public class IdentificateurTableau extends Expression{
    private String nom;
    private SymboleTableau symbole;
    private Expression indice;
    private int numBloc,dep;
    public IdentificateurTableau(String nom, Expression indice, int n) {
        super(n);
        this.nom = nom;
        this.indice = indice;
        this.valeur = indice.getValeur();
        numBloc = TDS.getInstance().getNoBlocCourant();


    }



    @Override
    public void verifier() throws AnalyseException {

        Entree e =new EntreeTableau(this.nom);
        e.setNoBloc(noBloc);
        symbole = (SymboleTableau)TDS.getInstance().getSymboleTable(e);
            System.out.println(symbole.toString());
            dep=symbole.getDeplacement();
            if(symbole == null) {
            e.setNoBloc(0);
            symbole = (SymboleTableau) TDS.getInstance().getSymboleTable(e);

            if (symbole == null) {
                VariablePasDeclareeException erreur = new VariablePasDeclareeException("ligne " + noLigne + "\n\t le tableau " + nom + " n'est pas declarée !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            } else {
                setType(Type.ENTIER);
            }
        }else {
            setType(Type.ENTIER);
        }

    }

    @Override
    public String toMIPS() {

        return codeToMips();

    }

    public int getDeplacement() {

        return dep;

    }

    @Override
    public String toString() {

        return nom + "[" + indice + "]";
    }
    public Type getType() {

        if (symbole == null){
            return null;

        }else {
            return  symbole.getTypeS();
        }

    }

    public int getNumBloc() {
        return numBloc;
    }

    public void setNumBloc(int numBloc) {
        this.numBloc = numBloc;
    }

    public Symbole getSymbole() {
        return symbole;
    }
    @Override
    public String codeToMips() {

       return null;
    }
}
