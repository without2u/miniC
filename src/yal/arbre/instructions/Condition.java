package yal.arbre.instructions;
import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;

public class Condition extends Instruction {
    private Expression e;
    protected BlocDInstructions sinon;
    protected BlocDInstructions alors;
    private static int cmpt = 0;
    public Condition(Expression e,int n) {
        super(n);
        this.e=e;
        alors=new BlocDInstructions(n);
        sinon=new BlocDInstructions(n);
        cmpt++;
    }

    @Override
    public void verifier() {
        e.verifier();
        if( e.getType() != Type.BOOLEAN) {
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(getNoLigne() , " condition :l'espression doit etre du type boolean !");
            ListeDErreurs.getErreurs().addErreur(a);
        }
        alors.verifier();
        sinon.verifier();
    }

    @Override
    public String toMIPS() {

        return "# debut condition"+ cmpt +"\n"+ e.toMIPS()+
        "si" + cmpt + ": beq $v0, 0, sinon" + cmpt + "\n"+
        "alors" + cmpt + ": " + alors.toMIPS() + "\n"+
        "j fsi" + cmpt + "\n"+
        "sinon" + cmpt + ": " + sinon.toMIPS() +"\n"+
        "# fin condition"+ cmpt +"\n"+
        "fsi" + cmpt + ": \n\t";
    }

    public void addConditionAlors(ArbreAbstrait a){
                alors.ajouter(a);
    }
    public void addConditionSinon(ArbreAbstrait a){
                sinon.ajouter(a);
    }
}
