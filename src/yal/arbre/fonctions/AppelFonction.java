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

    private int numeroBloc;
    private String nom;
    private Symbole symbole;
    private ArrayList<Expression> liste;


    public AppelFonction(String nom, int n) {
        super(n);
        this.nom = nom;
        this.numeroBloc = TDS.getInstance().getNoBlocCourant();
        this.valeur = 0;
    }


    public int getNbParam() {
        return ((SymboleFonction)symbole).getNbrParamSymbole();
    }


    public void setListe(ArrayList<Expression> liste) {
        this.liste = liste;
    }

    private void empilerArguments(StringBuilder sb) {
        if(liste != null) {
            for (int i = 0; i < getNbParam(); i++) {
                sb.append(liste.get(i).toMIPS());
                sb.append("sw $v0, 0($sp)\n\t");
                sb.append("addi $sp, $sp, -4\n\t");
            }
        }
    }

    @Override
    public void verifier() throws AnalyseException {
        //System.out.println("Cet appel de fonction a comme numero de bloc " + numeroBloc);
        Entree entree = new EntreeFonction(nom, liste.size());
        entree.setNumeroBloc(0);
        symbole = (SymboleFonction)TDS.getInstance().getSymboleTable(entree);

        if(symbole == null) {
            AnalyseException a = new AnalyseSemantiqueException(noLigne , ": fonction " + "\"" + nom + "\"" + " non declarée !");
            ListeDErreurs.getErreurs().addErreur(a);
        }else {
            if(liste != null) {
                for(int i = 0; i < liste.size(); i++) {
                    Expression exp = liste.get(i);
                    exp.verifier();
                    if(exp.getType() != Type.ENTIER) {
                        AnalyseException a = new AnalyseSemantiqueException(noLigne , ": l'argument " + (i + 1) + " de \"" + nom + "()\"" + " n'est pas de type ENTIER !");
                        ListeDErreurs.getErreurs().addErreur(a);
                    }
                }
                setType(Type.ENTIER);
            }
        }
    }





    public String toMIPS() {
        return codeToMips();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nom + "(");
        for (int i = 0; i < liste.size(); i++) {
            sb.append(liste.get(i));
            if(i != liste.size() - 1)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String codeToMips() {
        StringBuilder sb = new StringBuilder();
        sb.append("addi $sp, $sp, -4\n\t");
        if(getNbParam() > 0) {
            empilerArguments(sb);
        }
        sb.append("jal fonct" + ((SymboleFonction)symbole).getNumBloc() +"\n\t");
        if(getNbParam() > 0) {
            sb.append("addi $sp, $sp, " + getNbParam() * 4 +"\n\t");
        }
        sb.append("addi $sp, $sp, 4\n\t");
        return sb.toString();
    }
}

