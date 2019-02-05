package yal.arbre.expressions.binaire.comparaison;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseException;

public class Superieur extends Comparaison{
    public Superieur(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }

    @Override
    public String getOperateur() {
        return " > ";
    }

    @Override
    public String codeToMips() {
        return "sgt $v0, $t8, $v0\n";
    }

    @Override
    public void verifier() throws AnalyseException {
        super.verifier();
    }
}
