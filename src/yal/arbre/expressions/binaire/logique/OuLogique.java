package yal.arbre.expressions.binaire.logique;

import yal.arbre.expressions.Expression;

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
        return "or $v0, $t8, $v0\n\t";
    }
}
