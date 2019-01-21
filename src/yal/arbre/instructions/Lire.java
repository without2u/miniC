package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;

public class Lire extends Instruction {

    protected Identificateur i ;

    public Lire (Identificateur i, int n) {
        super(n) ;
        this.i = i ;
    }


    @Override
    public void verifier() {
        i.verifier();
    }

    @Override
    public String toMIPS() {
        return   i.toMIPS() +
                "    li $v0, 5\n" +
                "    syscall\n" +
                "sw $v0, " + i.getDeplacement() + "($s7)\n\t";


    }

}
