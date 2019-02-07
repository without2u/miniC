package yal.arbre.expressions.binaire.arithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.AnalyseSyntaxiqueException;
import yal.exceptions.ListeDErreurs;

public class Division extends BinaireArithmetique {
    public Division(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }

    @Override
    public String getOperateur() {
        return " / ";
    }

    @Override
    public String codeToMips() {
        StringBuilder sb = new StringBuilder();
        sb.append("bne $v0, 0, division\n");
        sb.append("li $v0, 4\n");
        sb.append("la $a0, " + "error_div" +"\n");
        sb.append("syscall\n");
        sb.append("j end\n");
        sb.append("division : \n");
        sb.append("div $t8, $v0\n");
        sb.append("mflo $v0\n");
        return sb.toString();
    }

    @Override
    public int getValeur() {
        return getFilsGauche().getValeur() / getFilsDroite().getValeur();
    }

    @Override
    public void verifier() throws AnalyseException {
        getFilsDroite().verifier();
        getFilsGauche().verifier();


        if((getFilsGauche().getType() != Type.ENTIER) || (getFilsDroite().getType() != Type.ENTIER)) {
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(getNoLigne() ,": les types des operandes doivent être ENTIER ");
            ListeDErreurs.getErreurs().addErreur(a);
        } else {
            setType(Type.ENTIER);

        }
        if(getFilsDroite().getValeur() == 0)
            throw new AnalyseSyntaxiqueException(getNoLigne() + ": " + "impossible !! Division par 0 !");
        else
            setValeur(getValeur());
    }
}