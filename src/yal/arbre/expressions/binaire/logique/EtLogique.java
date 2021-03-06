package yal.arbre.expressions.binaire.logique;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseException;

public class EtLogique extends BinaireLogique{
    public EtLogique(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }

    @Override
    public String getOperateur() {
        return " et ";
    }

    @Override
    public String codeToMips() {
        return "and $v0, $t8, $v0 \n";
    }

    @Override
    public void verifier() throws AnalyseException {
        super.verifier();
    }
}
