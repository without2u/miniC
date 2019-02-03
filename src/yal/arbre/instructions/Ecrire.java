package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.exceptions.AnalyseException;

public class Ecrire extends Instruction {

    protected Expression exp ;
    protected int cmp = -1;

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

        if(exp.getType()==Type.ENTIER) {
            if(exp instanceof Identificateur) {
                sb.append("lw $v0, " + ((Identificateur)exp).getDeplacement() + "($s7)\n\t");
            }
            else {
                sb.append(exp.toMIPS());}

            sb.append("move $t8, $v0\n\t");
            sb.append("li $v0, 1\n\t");
            sb.append("move $a0, $t8\n\t");
            sb.append("syscall\n\t");

        }
        if(exp.getType()==Type.BOOLEAN) {

            cmp++;

            sb.append(exp.toMIPS());
            sb.append("move $t8, $v0\n\t");
            sb.append("li $v0, 1\n\t");
            sb.append("beq $v0, $t8, Ecrire "+cmp+"\n\t");
            sb.append("li $v0, 4\n\t");
            sb.append("la $a0, faux\n\t");
            sb.append("syscall\n\t");
            sb.append("j fin "+cmp+"\n");
            sb.append("Ecrire "+cmp+": li $v0, 4\n\t");
            sb.append("la $a0, vrai\n\t");
            sb.append("syscall\n\t");
            sb.append("fin "+cmp+":\n\t");
        }

        return sb.toString();
    }

}
