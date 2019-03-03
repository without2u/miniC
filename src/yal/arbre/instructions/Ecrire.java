package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseException;
import yal.table.TDS;

public class Ecrire extends Instruction {

    protected Expression exp ;
    private static int cmp = -1;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
        noBloc = TDS.getInstance().getNoBlocCourant();

    }

    @Override
    public void verifier() throws AnalyseException {
        exp.verifier();

    }

    @Override
    public String toMIPS() {

        StringBuilder sb = new StringBuilder();

        if(exp.getType()==Type.ENTIER) {
                sb.append("# affichichage de l'expression entiere : " + exp + "\n");
                sb.append(exp.toMIPS());
                sb.append("move $t8, $v0\n");
                sb.append("li $v0, 1\n");
                sb.append("move $a0, $t8\n");
                sb.append("syscall\n");
                sb.append("li $v0, 4      # retour à la ligne\n");
                sb.append("la $a0, finLigne\n");
                sb.append("syscall\n");
        }
        if(exp.getType()==Type.BOOLEAN) {
            cmp++;
            sb.append("# affichichage d'une expression booleenne\n");
            sb.append(exp.toMIPS());
            sb.append("move $t8, $v0\n");
            sb.append("li $v0, 1\n");
            sb.append("beq $v0, $t8, Ecrire" + cmp + "\n");
            sb.append("li $v0, 4\n");
            sb.append("la $a0, faux\n");
            sb.append("syscall\n");
            sb.append("j finEcrire" + cmp + "\n");
            sb.append("Ecrire" + cmp +": li $v0, 4\n");
            sb.append("la $a0, vrai\n");
            sb.append("syscall\n");
            sb.append("finEcrire" + cmp + ":\n");
            sb.append("li $v0, 4      # retour à la ligne\n");
            sb.append("la $a0, finLigne\n");
            sb.append("syscall\n");

        }

        return sb.toString();
    }

}
