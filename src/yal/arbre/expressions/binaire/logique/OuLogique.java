package yal.arbre.expressions.binaire.logique;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseException;

public class OuLogique extends BinaireLogique{
    public OuLogique(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }

    @Override
    public String getOperateur() {
        return " ou ";
    }

    @Override
    public String codeToMips() {
        return "or $v0, $t8, $v0 \n";
    }

    @Override
    public void verifier() throws AnalyseException {
        super.verifier();
    }
}
