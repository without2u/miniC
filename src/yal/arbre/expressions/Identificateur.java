package yal.arbre.expressions;

import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.ListeDErreurs;
import yal.exceptions.VariablePasDeclareeException;
import yal.table.Entree;
import yal.table.Symbole;
import yal.table.Tds;

public class Identificateur extends Expression{

    private Symbole symbole;
    private String nom;


    public Identificateur(String nom, int n) {
        super(n);
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public void verifier() throws AnalyseException {

        Entree e =new Entree(this.nom);
        symbole = Tds.getInstance().getTableDeSymbole().get(e);
        if(symbole == null) {

           VariablePasDeclareeException erreur= new VariablePasDeclareeException(noLigne + ": la variable " + nom + " n'est pas declarée !");
           ListeDErreurs.getErreurs().addErreur(erreur);

        }else{
            setType(Type.ENTIER);

        }
    }

    @Override
    public String toMIPS() {
        return "";
    }


    public Type getType() {
        if (symbole == null){
            return null;

        }else {
            return  symbole.getType();
        }

    }

    public int getDeplacement() {
        return symbole.getDeplacement();
    }

}
