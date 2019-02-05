package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;

public class MoinsUnaire extends Unaire{
    public MoinsUnaire(Expression e) {
        super(e);
    }

    @Override
    public String getOperateur() {
        return "- ";
    }

    @Override
    public String toString() {
        return getOperateur();
    }

    @Override
    public void verifier() {
        super.verifier();
        if(e.getType() != Type.ENTIER) {
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(getNoLigne() ," : " +
                    " moinsUnaire :  les expressions doivent etre de type ENTIER \n");
            ListeDErreurs.getErreurs().addErreur(a);
        }else {

            setType(Type.ENTIER);
            setValeur(getValeur());

        }
    }

    @Override
    public int getValeur() {
        return - e.getValeur();
    }


    public String codeToMips() {
        return  " li $t8, 0 \n"+
                " sub $v0, $t8, $v0\n";
    }
}
