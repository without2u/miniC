package yal.arbre.fonctions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.Tableau.Tableau;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.arbre.instructions.Boucle;
import yal.arbre.instructions.Condition;
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

import java.util.ArrayList;

public class Fonction extends ArbreAbstrait{
    private int noBlocFonction;
    private ArrayList<Identificateur> listeDecl;
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
            AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " : la fonction "+ this.toString() + "() n'est pas declarée !");
            ListeDErreurs.getErreurs().addErreur(erreur);
        }
        else if(bloc != null) {
            bloc.verifier();
            if(!bloc.instructionExiste(Boucle.class.getName()) && !bloc.instructionExiste(Condition.class.getName()) &&
                    !bloc.instructionExiste(Retourne.class.getName())) {
                AnalyseException erreur = new AnalyseSemantiqueException(bloc.LastInstruction().getNoLigne()
                        , " la " + nomFonction +"() retourne un resultat de type ENTIER !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            }
        }else if(listeDecl != null) {
            for(Identificateur idf :listeDecl) {
                idf.verifier();
            }
        }

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        int nbrVariablesDeFonction = getNbrVariablesDeFonction();
        sb.append("# la fonction " + nomFonction + " () avec  " + getNbrParamFonction() + " parametres\n");
        sb.append("# l'empilement de l'adresse de retour de la fonction \n\t");
        sb.append("fonction" + getNoBlocFonction() + ": sw $ra, 0($sp)\n\t");
        sb.append("addi $sp, $sp, "+(-decalage)+"\n\t");
        sb.append("# l'empilement de l'ancienne base\n\t");
        sb.append("sw $s7, 0($sp)\n\t");
        sb.append("addi $sp, $sp, "+(-decalage)+"\n\t");
        sb.append("# l'empilement du numero de bloc (numero de region) \n\t");
        sb.append("li $v0, " + getNoBlocFonction() + "\n\t");
        sb.append("sw $v0, ($sp)\n\t");
        sb.append("addi $sp, $sp, "+(-decalage)+"\n\t");
        sb.append("# chargement dans $s7 la nouvelle base\n\t");
        sb.append("move $s7, $sp\n\t");
        if(nbrVariablesDeFonction != 0) {
            sb.append("addi $sp, $sp, " + (nbrVariablesDeFonction * (-decalage)) + "\n\t");
        }
        if(getNbrParamFonction() > 0) {
            getMipsArgumentForFonction(sb);
        }
        genererMipsForTableau(sb);
        sb.append("# liste d'instructions de la fonction\n\t");
        sb.append(bloc.toMIPS());
        sb.append("li $v0, 4\n\t");
        sb.append("la $a0, " + "error_returnFonction" +"\n\t");
        sb.append("syscall\n\t");
        sb.append("j end\n");

        return sb.toString();
    }

    public void setNbrParamFonction(int nbrParamFonction) {
        this.nbrParamFonction = nbrParamFonction;
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
            if(entree instanceof EntreeVar && (entree.getNoBloc() == getNoBlocFonction())) {
                nbr++;
            }
            if(entree instanceof EntreeTableau && (entree.getNoBloc() == getNoBlocFonction())) {
                Symbole symbole = TDS.getInstance().getSymboleTable(entree);
                if(((SymboleTableau)symbole).getNbElement() > 0) {
                    nbr += ((SymboleTableau)symbole).getNbElement();
                } else {
                    nbr += 2;
                }
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
    private void genererMipsForTableau(StringBuilder sb) {
        if(listeDecl != null) {
            for(Identificateur idf : listeDecl) {
                if(idf instanceof Tableau && !Expression.estConstante(((Tableau)idf).getIndice())) {
                    sb.append(idf.toMIPS());
                }
            }
        }
    }


    public void setBloc(BlocDInstructions bloc) {
        this.bloc = bloc;
    }

    public BlocDInstructions getBloc() {
        return bloc;
    }
}