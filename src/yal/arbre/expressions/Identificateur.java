package yal.arbre.expressions;

import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.exceptions.VariablePasDeclareeException;
import yal.table.Symboles.SymboleVar;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeVar;
import yal.table.Symboles.Symbole;
import yal.table.TDS;

public class Identificateur extends Expression{

    private Symbole symbole;
    private String nom;
    private int numBloc;


    public Identificateur(String nom, int n) {

        super(n);
        this.numBloc = TDS.getInstance().getNoBlocCourant();
        this.nom = nom;

    }

    @Override
    public void verifier() throws AnalyseException {

        Entree e =new EntreeVar(this.nom);
        e.setNumeroBloc(numBloc);
        symbole = (SymboleVar)TDS.getInstance().getSymboleTable(e);
        if(symbole == null) {
            e.setNumeroBloc(0);
            symbole = (SymboleVar) TDS.getInstance().getSymboleTable(e);

            if (symbole == null) {
                VariablePasDeclareeException erreur = new VariablePasDeclareeException("ligne " + noLigne + "\n\t la variable " + nom + " n'est pas declarée !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            } else {
                setType(Type.ENTIER);
            }
        }else {
            setType(Type.ENTIER);
        }
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public String toMIPS() {
        return codeToMips();
    }


    public Type getType() {

        if (symbole == null){
            return null;

        }else {
            return  symbole.getType();
        }
    }

    @Override
    public String codeToMips() {

        StringBuilder sb = new StringBuilder();
        if( (getNumBloc() != 0) && (symbole.getNumBloc() != getNumBloc()))  {
            sb.append("# chargement de la variable " + nom + " du bloc " + symbole.getNumBloc() + "\n\t");
            sb.append("lw $t8, 8($s7)\n\t");
            sb.append("lw $v0, " + getDeplacement() + "($t8)\n\t");
        } else {
            sb.append("# chargement de la variable  " + nom + " du bloc " + symbole.getNumBloc() + "\n\t");
            sb.append("lw $v0, " + getDeplacement() + "($s7)\n\t");
        }
        return sb.toString();

    }

    public int getDeplacement() {
        return ((SymboleVar)symbole).getDeplacement();
    }

    public Symbole getSymbole() {
        return symbole;
    }

    public int getNumBloc() {
        return numBloc;
    }

    public void setNumBloc(int numBloc) {
        this.numBloc = numBloc;
    }
}