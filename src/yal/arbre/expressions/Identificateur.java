package yal.arbre.expressions;

import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.exceptions.VariablePasDeclareeException;
import yal.table.Symboles.SymboleTableau;
import yal.table.Symboles.SymboleVar;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeTableau;
import yal.table.tabDesEntrees.EntreeVar;
import yal.table.Symboles.Symbole;
import yal.table.TDS;

public class Identificateur extends Expression{

    private Symbole symboleVar,symboleTab;
    private String nom;
    private int numBloc;

    public static int cmpt=-1;

    public Identificateur(String nom, int n) {

        super(n);
        this.numBloc = TDS.getInstance().getNoBlocCourant();
        this.nom = nom;
        this.valeur = 0;

    }

    @Override
    public void verifier() throws AnalyseException {
        Entree entree = new EntreeVar(nom);
        entree.setNoBloc(numBloc);

        Entree entree2 = new EntreeTableau(nom);
        entree2.setNoBloc(numBloc);
        symboleVar = (SymboleVar)TDS.getInstance().getSymboleTable(entree);
        symboleTab = (SymboleTableau)TDS.getInstance().getSymboleTable(entree2);
        if(numBloc == 0) {
            if(symboleVar == null) {
                if(symboleTab != null) {
                    symboleVar = symboleTab;
                    setType(Type.TABLEAU);
                } else {
                    AnalyseException a = new AnalyseSemantiqueException(noLigne , ": la variable " + "\"" + nom + "\"" + " n'est pas declarée !");
                    ListeDErreurs.getErreurs().addErreur(a);                }
            } else {
                setType(Type.ENTIER);
            }

        } else {//num bloc != 0
            if(symboleVar == null) {
                if(symboleTab != null) {
                    symboleVar = symboleTab;
                    setType(Type.TABLEAU);
                } else {
                    entree.setNoBloc(0);
                    entree2.setNoBloc(0);
                    symboleVar = (SymboleVar)TDS.getInstance().getSymboleTable(entree);
                    symboleTab = (SymboleTableau)TDS.getInstance().getSymboleTable(entree2);
                    if(symboleVar == null) {
                        if(symboleTab != null) {
                            symboleVar = symboleTab;
                            setType(Type.TABLEAU);
                        } else {
                            AnalyseException a = new AnalyseSemantiqueException(noLigne ,": la variable " + "\"" + nom + "\"" + " n'est pas declarée !");
                            ListeDErreurs.getErreurs().addErreur(a);
                        }
                    } else {
                        setType(Type.ENTIER);
                    }
                }
            } else{
                setType(Type.ENTIER);}

        }
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public String toMIPS() {
        return codeToMips();
    }


    public Type getType() {

        if (symboleVar == null){
            return null;

        }else {
            return  symboleVar.getTypeS();
        }
    }

    @Override
    public String codeToMips() {
        StringBuilder sb = new StringBuilder();
        if(symboleVar.getNoBlocS() != getNoBloc() && getNoBloc() != 0) {
            cmpt++;
            sb.append("# la valeur de la variable " + nom + " du bloc " + symboleVar.getNoBlocS() + " avec ( chainage dynamique ) : \n\t");
            sb.append("lw $t7, 4($s7)\n\t");
            sb.append("la $t6, 0($s7)\n\t");
            sb.append("chainageDynamique"+ cmpt );
            sb.append(": beqz $t7, finchainageDynamique" + cmpt + "\n\t");
            sb.append("lw $t6, 8($t6)\n\t");
            sb.append("lw $t7, 4($t6)\n\t");
            sb.append("j chainageDynamique" + cmpt + "\n\t");
            sb.append("finchainageDynamique" + cmpt );
            sb.append(": lw $v0, " + getDeplacement() + "($t6)\n\t");
        } else {
            sb.append("# la valeur de la variable " + nom + " du bloc " + symboleVar.getNoBlocS() + "\n\t");
            sb.append("lw $v0, " + getDeplacement() + "($s7)\n\t");
        }
        return sb.toString();

    }

    public int getDeplacement() {
        return ((SymboleVar)symboleVar).getDeplacement();
    }

    public Symbole getSymbole() {
        return symboleVar;
    }

    public int getNumBloc() {
        return numBloc;
    }

    public void setNumBloc(int numBloc) {
        this.numBloc = numBloc;
    }
    public void verifierS1(){

    }
}