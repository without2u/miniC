package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;

public class Lire extends Instruction {

    protected Identificateur exp ;

    public Lire (Identificateur e, int n) {
        super(n) ;
        exp = e ;
    }


    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        return   exp.toMIPS() +
                "    li $v0, 5\n" +
                "    syscall\n" +
                "sw $v0, " + exp.getDeplacement() + "($s7)\n\t";


    }

}
