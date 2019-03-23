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
    private int noBlocFonction;
    private BlocDInstructions bloc;
    private String nomFonction;
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
        entree.setNoBloc(0);
        symbole = TDS.getInstance().getSymboleTable(entree);
        if(symbole == null) {
            AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " : la fonction "+ this + " n'est pas declarée !");
            ListeDErreurs.getErreurs().addErreur(erreur);
        }
        else if(bloc != null) {
            bloc.verifier();

            if(!bloc.ifContientRetourne()) {
                AnalyseException erreur = new AnalyseSemantiqueException(bloc.LastInstruction().getNoLigne() ,
                        " la fonction " + nomFonction + "()  doit retourner un resultat de type ENTIER !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            }
        }

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        int nbr = getNbrVariablesDeFonction();
        sb.append("# la fonction " + nomFonction + " () elle a  " + getNbrParamFonction() + " parametres\n");
        sb.append("# l'empilement de l'adresse de retour de la fonction \n\t");
        sb.append("fonction" + getNoBlocFonction() + ": sw $ra, 0($sp)\n\t");
        sb.append("addi $sp, $sp, "+(-decalage)+"\n\t");
        sb.append("# l'empilement de l'ancienne base\n\t");
        sb.append("sw $s7, 0($sp)\n\t");
        sb.append("addi $sp, $sp, "+(-decalage)+"\n\t");
        sb.append("# l'empilement du numero de region \n\t");
        sb.append("li $v0, " + getNoBlocFonction() + "\n\t");
        sb.append("sw $v0, ($sp)\n\t");
        sb.append("addi $sp, $sp, "+(-decalage)+"\n\t");
        sb.append("# chargement dans $s7 la nouvelle base\n\t");
        sb.append("move $s7, $sp\n\t");
        if(nbr != 0) {
            sb.append("addi $sp, $sp, " + (nbr * (-4)) + "\n\t");
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
        return ((SymboleFonction)symbole).getNbrParamSymbole();
    }

    public int getNoBlocFonction() {
        return ((SymboleFonction)symbole).getEtiquetteSymbole();
    }
    private int getNbrVariablesDeFonction() {
        int nbr = 0;
        for(Entree entree : TDS.getInstance()) {
            if(entree instanceof EntreeVar && entree.getNoBloc() == getNoBlocFonction()) {
                nbr++;
            }
        }
        return nbr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fonction " + nomFonction + "(");

        for (int i = 0; i < getNbrParamFonction(); i++) {
            sb.append("ENTIER");
            if(i != (getNbrParamFonction() - 1)) {
                sb.append("; ");
            }
        }
        sb.append(")");

        return sb.toString();
    }

    private void getMipsArgumentForFonction(StringBuilder sb) {
        sb.append("# l'empilement des arguments de la fonction\n\t");
        sb.append("la $t8, " + (12 + getNbrParamFonction() * decalage) + "($s7)\n\t");

        for (int i = 0; i < getNbrParamFonction(); i++) {
            sb.append("lw $v0, " + i * (-decalage) + "($t8)\n\t");
            sb.append("sw $v0, " + i * (-decalage) +"($s7)\n\t");
        }
    }

    public void setBloc(BlocDInstructions bloc) {
        this.bloc = bloc;
    }

    public BlocDInstructions getBloc() {
        return bloc;
    }
}