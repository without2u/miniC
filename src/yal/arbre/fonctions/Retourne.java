package yal.arbre.fonctions;

import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Instruction;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleFonction;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeFonction;
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
            AnalyseSemantiqueException erreur = new AnalyseSemantiqueException(getNoLigne(),": la valeur de retour doit être de type entier !");
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
                        sb.append("sw $v0, " + (16 + ((SymboleFonction)symbole).getNbrParamSymbole() * 4) + "($s7)\n\t");
                    } else {
                        sb.append("sw $v0, 16($s7)\n\t");
                    }
                    break;
                }
            }
        }
        int compteur = 0;
        for(Entree entree : TDS.getInstance()) {
            if(entree instanceof EntreeVar && entree.getNoBloc() == numBlocRetourne)
                compteur++;
        }
        sb.append("# dépilement des " + compteur +" variables locales\n\t");
        sb.append("addi $sp, $sp, " + (compteur * 4) + "\n\t");
        sb.append("# dépilement du numero bloc + ancienne base\n\t");
        sb.append("addi $sp, $sp, 8\n\t");
        sb.append("# chargement dans $s7 de l'ancienne base pour le retour de la fonction\n\t");
        sb.append("lw $s7, 0($sp)\n\t");
        sb.append("# dépilement de l'adresse de retour de l'appelant\n\t");
        sb.append("addi $sp, $sp, 4\n\t");
        sb.append("# chargement dans $ra de l'adresse de retour de l'appelant\n\t");
        sb.append("lw $ra, 0($sp)\n\t");
        sb.append("# branchement vers la prochaine instruction de l'appelant\n\t");
        sb.append("jr $ra\n\t");
        return sb.toString();
    }

    public int getNumBlocRetourne() {
        return numBlocRetourne;
    }

    public void setNumBlocRetourne(int numBlocRetourne) {
        this.numBlocRetourne = numBlocRetourne;
    }
}