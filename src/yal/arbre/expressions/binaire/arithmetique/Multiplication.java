package yal.arbre.expressions.binaire.arithmetique;

import yal.arbre.expressions.Expression;

public class Multiplication extends BinaireArithmetique{
    public Multiplication(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }

    @Override
    public String getOperateur() {
        return " * ";
    }

    @Override
    public String codeToMips() {
        return "mult $v0, $t8\n\t" +
                "mflo $v0\n\t";
    }

    @Override
    public int getValeur() {
        return getFilsGauche().getValeur() * getFilsDroite().getValeur();
    }
}
