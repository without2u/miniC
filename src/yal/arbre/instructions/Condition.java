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
    private int tmp;
    public Condition(Expression e,int n) {
        super(n);
        this.e=e;
        alors=new BlocDInstructions(n);
        sinon=new BlocDInstructions(n);
        cmpt++;
        tmp=cmpt;
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

        return "# debut condition"+ tmp +"\n"+ e.toMIPS()+
        "si" + tmp + ": beq $v0, 0, sinon" + tmp + "\n"+
        "alors" + tmp + ": " + alors.toMIPS() + "\n"+
        "j fsi" + tmp + "\n"+
        "sinon" + tmp + ": " + sinon.toMIPS() +"\n"+
        "# fin condition"+ tmp +"\n"+
        "fsi" + tmp + ": \n\t";
    }

    public void addConditionAlors(ArbreAbstrait a){
                alors.ajouter(a);
    }
    public void addConditionSinon(ArbreAbstrait a){
                sinon.ajouter(a);
    }
}
