package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;

public class Boucle extends Instruction{
    private static int cmpt = 0;
    protected Expression e;
    protected BlocDInstructions bloc;
    public Boucle(Expression e,int n) {
        super(n);
        this.e=e;
        cmpt++;
        bloc = new BlocDInstructions(n);

    }

    @Override
    public void verifier() throws AnalyseException {
        e.verifier();
        if(e.getType() != Type.BOOLEAN) {

            AnalyseSemantiqueException a = new AnalyseSemantiqueException(getNoLigne() , " boucle : le type de la condition doit être du type boolean !");
            ListeDErreurs.getErreurs().addErreur(a);
        }
        bloc.verifier();

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("# debut boucle "+ cmpt +"\n\t");
        sb.append("tantque" + cmpt + ": " + e.toMIPS());
        sb.append("# evaluation de la condition\n\t");
        sb.append("beq $v0, 0, fintantque" + cmpt + "\n\t");
        sb.append(bloc.toMIPS());
        sb.append("j " + "tantque" + cmpt + "\n\t");
        sb.append("# fin de la boucle " + cmpt + "\n\t");
        sb.append("fintantque" + cmpt + ":\n\t");
        return sb.toString();
    }
    public void ajouter(ArbreAbstrait a){
        bloc.ajouter(a);
    }

}
