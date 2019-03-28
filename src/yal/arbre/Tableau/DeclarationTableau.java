package yal.arbre.Tableau;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.instructions.Instruction;
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


public class DeclarationTableau extends Instruction {
    private int noBlocTableau;
    private BlocDInstructions bloc;
    private String nomTableau;
    private int nbrParamFonction,tmp;
    private Symbole symbole;
    private static int decalage = 4;
    private static int cmpt = 0;
    public static int deplVar;


    public DeclarationTableau(String nomTableau,int n) {
        super(n);
        this.nomTableau=nomTableau;
        this.noBlocTableau = TDS.getInstance().getNoBlocCourant();
        cmpt++;
        this.tmp=cmpt;
    }

    @Override
    public void verifier() throws AnalyseException {
        Entree e =new EntreeTableau(this.nomTableau);

        e.setNoBloc(noBloc);
        symbole = (SymboleTableau)TDS.getInstance().getSymboleTable(e);
        //System.out.println(symbole.toString());
        if(symbole == null) {
            AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " : le tableau "+ nomTableau
                    + " n'est pas declarée !");
            ListeDErreurs.getErreurs().addErreur(erreur);
        }
        else if(bloc != null) {
            bloc.verifier();
            deplVar = ((SymboleTableau)symbole).getDeplacement();

        }

    }

    @Override
    public String toMIPS() {
        StringBuilder init = new StringBuilder(60);


        init.append("# Déclaration du tableau " + nomTableau + "\n");

        init.append("# Stocke l'adresse de début du tableau\n");
        init.append("sw $sp, ");
        init.append(deplVar);
        init.append("($s7)\n");

        init.append("# Place la valeur de la longueur dans $v0\n");
        init.append("li $v0,"+ ((SymboleTableau)symbole).getNbElement() + "\n") ;

        init.append("\n") ;

        init.append("# Vérifie que la longueur est strictement supérieure à 0\n");
        init.append("blez $v0, erreurLongueurInvalide\n");

        init.append("# Stocke la longueur\n");
        init.append("sw $v0, 0($sp)\n");

        init.append("# Initialisation du tableau\n");

        init.append("tq_");
        init.append(tmp);
        init.append(" :\n");

        init.append("beqz $v0, fintq_");
        init.append(tmp);
        init.append("\n");

        init.append("addi $v0, $v0, -1\n");
        init.append("addi $sp, $sp, -4\n");
        init.append("sw $zero, 0($sp)\n");

        init.append("j tq_");
        init.append(tmp);
        init.append("\n");

        init.append("fintq_");
        init.append(tmp);
        init.append(" :\n");

        // Je crois
        init.append("addi $sp, $sp, -4\n");


        return init.toString();
    }



    public void setNoBlocTableau(int noBlocTableau) {
        this.noBlocTableau = noBlocTableau;
    }





    public void setBloc(BlocDInstructions bloc) {
        this.bloc = bloc;
    }

    public BlocDInstructions getBloc() {
        return bloc;
    }
}
