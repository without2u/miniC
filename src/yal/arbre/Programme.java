package yal.arbre;

import yal.arbre.Tableau.DeclarationTableau;
import yal.arbre.Tableau.IdentificateurTableau;
import yal.arbre.fonctions.Fonction;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.EnsembleDErreurs;
import yal.exceptions.ListeDErreurs;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeVar;

import java.util.ArrayList;

public class Programme extends ArbreAbstrait {

    protected String nomProg;
    protected BlocDInstructions bloc;
    private static int decalage = 4;
    private ArrayList<ArbreAbstrait> listeDesDecl;
    private BlocDInstructions tableaux;
    private int tailleZoneDesVariables;

    public Programme(String nomProg,int n) {
        super(n);
        this.nomProg=nomProg;
        tableaux = new BlocDInstructions(n + 1);
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
    private void getMipsForTableau(StringBuilder sb) {

        if(listeDesDecl != null) {
            for(ArbreAbstrait a : listeDesDecl) {
                if((a instanceof DeclarationTableau)) {
                    sb.append(((DeclarationTableau)a).toMIPS()).append(" \n ");
                }
            }
        }

    }

    // on compte le nombre des variables dans le bloc principal 0
    private int getNbrVariable() {
        int cmp = 0;
        for(Entree e : TDS.getInstance()) {
            if((e instanceof EntreeVar) && (e.getNoBloc() == 0)) {
                cmp++;
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
        tailleZoneDesVariables = TDS.getInstance().tailleZoneDesVariables();
    }

    public void base(StringBuilder mips) {
        mips.append("# Empile le numéro de région\n");
        mips.append("li $t8, 0\n");
        mips.append("sw $t8, 0($sp)\n");
        mips.append("addi $sp, $sp, -4\n");
        mips.append("\n");

        mips.append("# Initialisation de la base des variables\n");
        mips.append("move $s7, $sp\n");
        mips.append("\n");

        mips.append("# Réservation de l'espace pour ");
        mips.append(tailleZoneDesVariables / 4);
        mips.append(" variable(s)\n");

        mips.append("addi $sp, $sp, ");
        mips.append(- tailleZoneDesVariables);
        mips.append("\n\n");

        mips.append("# Initialisation des variables\n");
        mips.append("li $t8, 0\n");

        for (int depl = 0; depl > - tailleZoneDesVariables; depl -= 4) {
            mips.append("sw $t8, ");
            mips.append(depl);
            mips.append("($s7)\n");
        }

        mips.append("\n");
    }
    @Override
    public String toMIPS() {
        int cmp = getNbrVariable();
        StringBuilder sb = new StringBuilder("") ;
        sb.append(".data\n" +
                " finLigne:   .asciiz \"\\n\"\n" +
                "              .align 2\n"+
                "vrai:\t" + ".asciiz \"vrai\"\n"+
                "faux:\t" + ".asciiz \"faux\"\n"+
                "error_div:\t" + ".asciiz \"Erreur: Division par 0 !\n"+
                "erreurAccesTableauInvalide :\n"+

        ".text\n" +
                " main :\n") ;
        sb.append("move $s7, $sp\n");
        if(cmp != 0) {
            //on reserve de la memoire pour les variable du bloc local
            sb.append("addi $sp, $sp, " + cmp*(-decalage) + "\n");
        }
        base(sb);
        getMipsForTableau(sb);
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
