package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.table.TDS;

public class Lire extends Instruction {

    protected Identificateur exp ;

    public Lire (Identificateur e, int n) {
        super(n) ;
        exp = e ;
        this.noBloc = TDS.getInstance().getNoBlocCourant();
    }


    @Override
    public void verifier() {
        exp.setNumBloc(getNoBloc());
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        return   exp.toMIPS() +
                "    li $v0, 5\n" +
                "    syscall\n" +
                "    sw $v0, " + exp.getDeplacement() + "($s7)\n";


    }

}
