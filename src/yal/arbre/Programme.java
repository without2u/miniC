package yal.arbre;

import yal.arbre.fonctions.Fonction;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.EnsembleDErreurs;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleTableau;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeTableau;
import yal.table.tabDesEntrees.EntreeVar;

import java.util.ArrayList;

public class Programme extends ArbreAbstrait {

    protected String nomProg;
    protected BlocDInstructions bloc;
    private static int decalage = 4;
    private ArrayList<ArbreAbstrait> listeDesDecl;

    public Programme(String nomProg,int n) {
        super(n);
        this.nomProg=nomProg;
    }

    // on genere le code mips pour les fonctions
    private void getMipsForFonction(StringBuilder sb) {

        if(listeDesDecl != null) {
            for(ArbreAbstrait a : listeDesDecl) {
                if((a instanceof Fonction)) {
                    sb.append(((Fonction)a).toMIPS()).append(" \n ");
                }

            }
        }

    }
   /* private void getMipsForTableau(StringBuilder sb) {

        if(tableaux != null) {
            for(ArbreAbstrait a : tableaux) {
                if((a instanceof DeclarationTableau)) {
                    sb.append(((DeclarationTableau)a).toMIPS()).append(" \n ");
                }
            }
        }

    }
    */

    // on compte le nombre des variables dans le bloc principal 0
    private int getNbrVariable() {
        int cmp = 0;
        for(Entree e : TDS.getInstance()) {
            if((e instanceof EntreeVar) && (e.getNoBloc() == 0)) {
                cmp++;
            }
            if(e instanceof EntreeTableau && (e.getNoBloc() == 0)) {
                Symbole symbole = TDS.getInstance().getSymboleTable(e);
                int nbrDelement = ((SymboleTableau)symbole).getNbElement();
                if(nbrDelement > 0) {
                    cmp += nbrDelement;
                }
            }
        }

        return cmp;
    }

    @Override
    public void verifier() throws AnalyseException {

        //si la liste des declaration est vide
        if(listeDesDecl != null) {

            for(ArbreAbstrait a : listeDesDecl) {
                a.verifier();
            }
        }
        //si le bloc est vide
        if(bloc != null) {
            bloc.verifier();
            if(bloc.ifContientRetourne()) {
                AnalyseSemantiqueException erreur =  new AnalyseSemantiqueException(bloc.getNoLigne() , ": le programme principal " +
                        nomProg +" ne retourne pas de valeur ");
                ListeDErreurs.getErreurs().addErreur(erreur);

            }
        }
        //si y a des erreurs a afficher
        if(!ListeDErreurs.getErreurs().isEmpty()){

            throw new EnsembleDErreurs("");
        }

    }
    //a revoir
    public void mainAndData(StringBuilder sb) {
        sb.append(".data\n");
        sb.append(" finLigne:   .asciiz \"\\n\"\n" );
        sb.append("vrai:\t" + ".asciiz \"vrai\"" + "\n");
        sb.append("faux:\t" + ".asciiz \"faux\"" + "\n");
        sb.append("error_div:\t" + ".asciiz \"Erreur: Division par 0 !\"" + "\n");
        sb.append("error_returnFonction:\t" + ".asciiz \"Erreur: le type de retour de la fonction doit etre entier !\"" + "\n");
        sb.append("error_indexTableau:\t" + ".asciiz \"Erreur: index tableau superieur ou negatif !\"" + "\n");
        sb.append("error_affectation_tableau:\t" + ".asciiz \"Erreur: probleme d'affectation tableau !\"" + "\n");
        sb.append("\n");
        sb.append(".text\n main :\n\t");
    }
    public void base(StringBuilder sb) {
        sb.append("addi $sp, $sp, -4\n\t"+
                "sw $ra, 0($sp)\n\t"+
                "addi $sp, $sp, -4\n\t"+
                "sw $s7, 0($sp)\n\t"+
                "addi $sp, $sp, -4\n\t"+
                "li $v0, 0\n\t"+
                "sw $v0, ($sp)\n\t"+
                "addi $sp, $sp, -4\n\t"+
                "move $s7, $sp\n\t");
    }
    @Override
    public String toMIPS() {
        int cmp = getNbrVariable();
        StringBuilder sb = new StringBuilder("") ;
        mainAndData(sb);
        base(sb);
        if(cmp != 0) {
            //on reserve de la memoire pour les variable du bloc local
            sb.append("addi $sp, $sp, " + cmp*(-decalage) + "\n");
        }
        base(sb);

        sb.append(bloc.toMIPS() + "\n");
        sb.append("end :\n" +
                "move $v1, $v0\n"+
                " li $v0, 10 \n" +
                " syscall\n") ;

        getMipsForFonction(sb);
        return sb.toString() ;

    }


    public void setBloc(BlocDInstructions bloc) {
        this.bloc = bloc;
    }
    public void setListeDesDecl(ArrayList<ArbreAbstrait> listeDesDecl) {
        this.listeDesDecl = listeDesDecl;
    }
}
