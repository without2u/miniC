package yal.arbre.fonctions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleFonction;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeFonction;
import yal.table.tabDesEntrees.EntreeVar;

public class Fonction extends ArbreAbstrait{
    private BlocDInstructions bloc;
    private String nomFonction;
    private int noBlocFonction;
    private int nbrParamFonction;
    private Symbole symbole;
    private static int decalage = 4;
    public Fonction(String nomFonction, int n) {
        super(n);
        this.nomFonction=nomFonction;
        this.noBlocFonction = TDS.getInstance().getNoBlocCourant();


    }

    @Override
    public void verifier() throws AnalyseException {
        EntreeFonction entree = new EntreeFonction(nomFonction, nbrParamFonction);
        setNoBlocFonction(0);
        symbole = TDS.getInstance().getSymboleTable(entree);
        if(symbole == null) {
            AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " : la variable "+ this + " n'est pas declarée !");
            ListeDErreurs.getErreurs().addErreur(erreur);
        }
        else if(bloc != null) {
            bloc.verifier();
            if(!bloc.ifContientRetourne()) {
                AnalyseException erreur = new AnalyseSemantiqueException(bloc.LastInstruction().getNoLigne() , " la fonction " + nomFonction + "() retourne un resultat de type ENTIER !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            }
        }

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("# l'empilement de l'adresse de retour\n\t");
        sb.append("fonct" + getNoBlocFonction() + ": sw $ra, 0($sp)\n\t");
        sb.append("addi $sp, $sp, -4\n\t");
        sb.append("# empilement de l'ancienne base\n\t");
        sb.append("sw $s7, 0($sp)\n\t");
        sb.append("addi $sp, $sp, -4\n\t");
        sb.append("# l'empilement du numero de la region du bloc\n\t");
        sb.append("li $v0, " + getNoBlocFonction() + "\n\t");
        sb.append("sw $v0, ($sp)\n\t");
        sb.append("addi $sp, $sp, -4\n\t");
        sb.append("move $s7, $sp\n\t");
        int nbrVar = getNbrVariablesDeFonction();
        if(nbrVar != 0) {
            sb.append("addi $sp, $sp, " + nbrVar * (-decalage) + "\n\t");
        }
        if(getNbrParamFonction() > 0) {
            getMipsArgumentForFonction(sb);
        }
        sb.append("# liste d'instructions de la fonction\n\t");
        sb.append(bloc.toMIPS());
        return sb.toString();
    }

    public void setNbrParamFonction(int nbrParamFonction) {
        this.nbrParamFonction = nbrParamFonction;
    }

    public void setNoBlocFonction(int noBlocFonction) {
        this.noBlocFonction = noBlocFonction;
    }

    public int getNbrParamFonction() {
        return nbrParamFonction;
    }

    public int getNoBlocFonction() {
        return noBlocFonction;
    }
    private int getNbrVariablesDeFonction() {
        int nbr = 0;
        for(Entree entree : TDS.getInstance()) {
            if(entree instanceof EntreeVar && (entree.getNumeroBloc() == getNoBloc())) {
                nbr++;
            }
        }
        return nbr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fonction " + nomFonction + "(");

        for (int i = 0; i < nbrParamFonction; i++) {
            sb.append("ENTIER");
            if(i != (nbrParamFonction - 1))
                sb.append(" ; ");
        }
        sb.append(")");
        return super.toString();
    }

    private void getMipsArgumentForFonction(StringBuilder sb) {
        sb.append("# l'empilement des arguments de la fonction\n\t");
        sb.append("la $t8, " + (12 + getNbrParamFonction() * decalage) + "($s7)\n\t");

        for (int i = 0; i < getNbrParamFonction(); i++) {
            sb.append("lw $v0, " + i * (-decalage) + "($t8)\n\t");
            sb.append("sw $v0, " + i * (-decalage) +"($s7)\n\t");
        }
    }

}