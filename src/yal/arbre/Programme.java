package yal.arbre;

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
