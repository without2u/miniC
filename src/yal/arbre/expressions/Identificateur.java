package yal.arbre.expressions;

import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
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
    public void verifier() throws AnalyseException {

        Entree e =new Entree(this.nom);
        symbole = Tds.getInstance().getTableDeSymbole().get(e);
        if(symbole == null) {

            throw new VariablePasDeclareeException(noLigne+": variable "+nom+" n'est pas declarée !");

        }else{
            setType(Type.ENTIER);
        }
    }

    @Override
    public String toMIPS() {
        return "";
    }


    public Type getType() {
        return symbole.getType();
    }

    public int getDeplacement() {
        return symbole.getDeplacement();
    }

}
