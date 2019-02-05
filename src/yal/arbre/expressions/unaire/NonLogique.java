package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;

public class NonLogique extends Unaire {
    public NonLogique(Expression e) {
        super(e);
    }

    @Override
    public String getOperateur() {
        return  " non ";
    }

    @Override
    public void verifier() {
        super.verifier();
        if(e.getType() != Type.BOOLEAN) {

            AnalyseSemantiqueException a = new AnalyseSemantiqueException(getNoLigne()," : " +
                    " nonLogique : les expressions doivent etre de type BOOLEAN \n");
            ListeDErreurs.getErreurs().addErreur(a);

        }else{
            setType(Type.BOOLEAN);
        }
    }



    @Override
    public String codeToMips() {
        return  " li $t8, 1\n\t"+
                " sub $v0, $t8, $v0\n";
    }
}
