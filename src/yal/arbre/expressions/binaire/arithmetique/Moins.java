package yal.arbre.expressions.binaire.arithmetique;

import yal.arbre.expressions.Expression;

public class Moins extends BinaireArithmetique{
    public Moins(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }

    @Override
    public String getOperateur() {
        return " - ";
    }

    @Override
    public String codeToMips() {
        return "sub $v0, $t8, $v0\n\t";
    }

    @Override
    public int getValeur() {
        return getFilsGauche().getValeur() - getFilsDroite().getValeur();
    }
}
