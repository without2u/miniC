package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.exceptions.AnalyseException;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() throws AnalyseException {
        exp.verifier();

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        if(exp instanceof Identificateur) {
            sb.append("lw $v0, " + ((Identificateur)exp).getDeplacement() + "($s7)\n\t");
        }
        else {
            sb.append(exp.toMIPS());}

            sb.append("move $t8, $v0\n\t");
            sb.append("li $v0, 1\n\t");
            sb.append("move $a0, $t8\n\t");
            sb.append("syscall\n\t");

        return sb.toString();
    }

}
