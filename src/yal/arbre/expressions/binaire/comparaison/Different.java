package yal.arbre.expressions.binaire.comparaison;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseException;

public class Different extends Comparaison{
    public Different(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }

    @Override
    public String getOperateur() {
        return " != ";
    }

    @Override
    public String codeToMips() {
        return "sne $v0, $t8, $v0\n\t";
    }

    @Override
    public void verifier() throws AnalyseException {
        super.verifier();
    }
}
