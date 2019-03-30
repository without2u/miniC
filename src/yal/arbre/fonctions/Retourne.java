package yal.arbre.fonctions;

import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Instruction;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleFonction;
import yal.table.Symboles.SymboleTableau;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeFonction;
import yal.table.tabDesEntrees.EntreeTableau;
import yal.table.tabDesEntrees.EntreeVar;

public class Retourne extends Instruction {

    private Expression e;
    private int numBlocRetourne;
    private static int decalage = 4;
    public Retourne(Expression e, int n) {
        super(n);
        this.e=e;
        this.numBlocRetourne= TDS.getInstance().getNoBlocCourant();
    }

    @Override
    public String toString() {
        return "Retourne" + e;
    }

    @Override
    public void verifier() throws AnalyseException {
        e.verifier();
        if(e.getType() != Type.ENTIER) {
            AnalyseSemantiqueException erreur = new AnalyseSemantiqueException(getNoLigne(),": la valeur de retour de la fonction doit être de type entier !");
            ListeDErreurs.getErreurs().addErreur(erreur);
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append(e.toMIPS());
        sb.append("# sauvegarde du resultat " + e + " dans son emplacement retour\n\t");
        for(Entree entree : TDS.getInstance()) {
            if(entree instanceof EntreeFonction) {
                Symbole symbole = TDS.getInstance().getSymboleTable(entree);
                if(((SymboleFonction)symbole).getNoBlocS() == numBlocRetourne) {
                    if(((SymboleFonction)symbole).getNbrParamSymbole() > 0) {
                        sb.append("sw $v0, " + (16 + ((SymboleFonction)symbole).getNbrParamSymbole() * decalage) + "($s7)\n\t");
                    } else {
                        sb.append("sw $v0, 16($s7)\n\t");
                    }
                    break;
                }
            }
        }
        int cmp = getNbrVariablesDeFonction();
        sb.append("# dépilement des " + cmp +" variables locales\n\t");
        sb.append("addi $sp, $sp, " + (cmp * decalage) + "\n\t");
        depilementForTableauxDynamiques(sb);
        sb.append("addi $sp, $sp, 8\n\t");
        sb.append("lw $s7, 0($sp)\n\t");
        sb.append("addi $sp, $sp, 4\n\t");
        sb.append("lw $ra, 0($sp)\n\t");
        sb.append("jr $ra\n\t");
        return sb.toString();
    }
    private void depilementForTableauxDynamiques(StringBuilder sb) {
        for(Entree entree : TDS.getInstance()) {
            if(entree instanceof EntreeTableau && entree.getNoBloc() == noBloc) {
                Symbole symbole = TDS.getInstance().getSymboleTable(entree);
                if(((SymboleTableau)symbole).getNbElement() == 0) {
                    sb.append("# le dépilement des variables du tableau (dynamique) : \t");
                    sb.append("lw $t2, " + (((SymboleTableau)symbole).getDeplacement() - decalage) + "($s7)\n\t");
                    sb.append("li $t3, 4\n\t");
                    sb.append("mult $t2, $t3\n\t");
                    sb.append("mflo $t2\n\t");
                    sb.append("add $sp, $sp, $t2\n\t");
                }
            }
        }
    }
    private int getNbrVariablesDeFonction() {
        int cmp=0;
        for(Entree entree : TDS.getInstance()) {
            if(entree instanceof EntreeVar && entree.getNoBloc() == numBlocRetourne) {
                cmp++;
            }
            if(entree instanceof EntreeTableau && (entree.getNoBloc() == getNoBloc())) {
                Symbole symbole = TDS.getInstance().getSymboleTable(entree);
                if(((SymboleTableau)symbole).getNbElement() > 0) {
                    cmp += ((SymboleTableau)symbole).getNbElement();
                } else {
                    cmp += 2;
                }
            }
        }
        return cmp;
    }

    public int getNumBlocRetourne() {
        return numBlocRetourne;
    }

    public void setNumBlocRetourne(int numBlocRetourne) {
        this.numBlocRetourne = numBlocRetourne;
    }
}