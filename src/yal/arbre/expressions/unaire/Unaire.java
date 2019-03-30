package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;

public abstract class Unaire extends Expression {

    protected Expression e;

    public Unaire(Expression e) {
        super(e.getNoLigne());
        this.e=e;
    }

    public abstract String getOperateur();

    @Override
    public void verifier() {
        this.e.verifier();
    }

    @Override
    public String toString() {
        return "(" + getOperateur() + e + ")" ;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        if(e instanceof Identificateur) {
            if(((Identificateur)e).getNumBloc() == getNoBloc()) {

                sb.append("lw $v0, " + ((Identificateur) e).getDeplacement() + "($s7)\n\t");

            } else {

                sb.append("sw $t8, $s7\n\t");
                sb.append("addi $t8, $t8, 8\n\t");
                sb.append("lw $v0, " + ((Identificateur)e).getDeplacement() + "($s7)\n\t");

            }
        } else {
            sb.append(e.toMIPS());
        }

        sb.append(codeToMips());

        return sb.toString();

    }

    public Expression getE() {
        return e;
    }
}
