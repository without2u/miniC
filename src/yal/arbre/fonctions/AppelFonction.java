package yal.arbre.fonctions;


import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleFonction;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeFonction;

import java.util.ArrayList;

public class AppelFonction extends Expression {

    private String nomFonction;
    private int noBlocAppelF;
    private Symbole symbole;
    private ArrayList<Expression> listE;
    private static int decalage = 4;

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(nomFonction + "(");
        for (int i = 0; i < listE.size(); i++) {
            sb.append(listE.get(i));

            if(i != listE.size() - 1) {
                sb.append(", ");
            }

        }

        sb.append(")");

        return sb.toString();
    }

    public AppelFonction(String nomFonction, int n) {
        super(n);
        this.nomFonction=nomFonction;
        this.noBlocAppelF = TDS.getInstance().getNoBlocCourant();
        this.valeur = 0;
    }

    private void empilementDArguments(StringBuilder sb) {

        if(listE != null) {

            for (int i = 0; i < getNombreParametresFonction(); i++) {

                sb.append("# empilements d'arguments :\n\t");
                sb.append(listE.get(i).toMIPS());
                sb.append("sw $v0, 0($sp)\n\t");
                sb.append("addi $sp, $sp, -4\n\t");

            }

        }

    }

    @Override
    public String codeToMips() {
        return null;
    }

    @Override
    public void verifier() throws AnalyseException {
        int taille=listE.size();
        Entree entree = new EntreeFonction(nomFonction, taille);
        entree.setNumeroBloc(0);

        symbole = (SymboleFonction)TDS.getInstance().getSymboleTable(entree);

        if(symbole == null) {

            AnalyseException erreur = new AnalyseSemantiqueException(noLigne, ": la fonction " + "\"" + nomFonction + "\"" + " n'est pas declarée !");
            ListeDErreurs.getErreurs().addErreur(erreur);

        }else {
            if(listE != null) {

                for(int i = 0; i < taille; i++) {

                    Expression e = listE.get(i);
                    e.verifier();

                    if(e.getType() != Type.ENTIER) {

                        AnalyseException erreur = new AnalyseSemantiqueException(noLigne , ": l'argument : " + (i+1) +
                                " qui appartient a la fonction \"" + nomFonction + "()\"" + " n'est pas de type ENTIER !");
                        ListeDErreurs.getErreurs().addErreur(erreur);

                    }
                }

                setType(Type.ENTIER);
            }
        }

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("addi $sp, $sp, -4\n\t");

        if(getNombreParametresFonction() > 0) {

            empilementDArguments(sb);
        }
        sb.append("jal fonct" + ((SymboleFonction)symbole).getNumBloc() +"\n\t");

        if(getNombreParametresFonction() > 0) {
            sb.append("addi $sp, $sp, " + getNombreParametresFonction() * decalage +"\n\t");
        }
        sb.append("addi $sp, $sp, 4\n\t");
        return sb.toString();
    }
    public int getNombreParametresFonction(){
        return ((SymboleFonction)symbole).getNbrParamSymbole();
    }

    public ArrayList<Expression> getListE() {
        return listE;
    }

    public void setListE(ArrayList<Expression> listE) {
        this.listE = listE;
    }
}

