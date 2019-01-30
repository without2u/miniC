package yal.arbre.instructions;

import yal.table.TDC;

public class EcrireChaine extends Instruction {

    protected String constanteChaine;
    public EcrireChaine(String constanteChaine,int n) {
        super(n);
        this.constanteChaine=constanteChaine;

    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("li $v0, 4\n\t");
        sb.append("la $a0, " + "str" + TDC.getInstance().identifier(constanteChaine) + "\n\t");
        sb.append("syscall\n\t");
        return sb.toString();
    }
}
