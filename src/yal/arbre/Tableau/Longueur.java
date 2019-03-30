package yal.arbre.Tableau;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleTableau;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeTableau;


public class Longueur extends Expression {

    private String nomTab;
    private Symbole s;
    private int decalage;

    public Longueur(String nomTab, int n) {
        super(n);
        this.nomTab=nomTab;
    }

    @Override
    public String codeToMips() {
        StringBuilder sb = new StringBuilder();
        Identificateur.cmpt++;

        if(s.getNoBlocS() != getNoBloc() && getNoBloc() != 0) {
            if(getTailleTableau() > 0) {
                sb.append("li $v0, " + valeur + "\n\t");
            } else {
                sb.append("# charger la taille de " + nomTab + " du bloc " + s.getNoBlocS() + "\n\t");
                sb.append("lw $t7, 4($s7)\n\t");
                sb.append("la $t6, 0($s7)\n\t");
                sb.append("chain"+ Identificateur.cmpt + ": beqz $t7, endchain" + Identificateur.cmpt + "\n\t");
                sb.append("lw $t6, 8($t6)\n\t");
                sb.append("lw $t7, 4($t6)\n\t");
                sb.append("j chain" + Identificateur.cmpt + "\n\t");
                sb.append("endchain" + Identificateur.cmpt + ": lw $v0, " + (getDeplacement() - decalage) + "($t6)\n\t");
            }
        } else {
            if(getTailleTableau() > 0) {
                sb.append("li $v0, " + valeur + "\n\t");
            } else {
                sb.append("lw $v0, " + (getDeplacement() - decalage) + "($s7)\n\t");
            }
        }

        return sb.toString();
    }

    @Override
    public void verifier() throws AnalyseException {

        Entree e = new EntreeTableau(nomTab);
        e.setNoBloc(noBloc);

        s = (SymboleTableau) TDS.getInstance().getSymboleTable(e);

        if(s == null) {
            e.setNoBloc(0);
            s = (SymboleTableau)TDS.getInstance().getSymboleTable(e);
            if(s == null) {
                AnalyseException erreur = new AnalyseSemantiqueException(noLigne , ": le tableau " + "\"" + nomTab + "\"" + " n'est pas declaré !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            } else {
                if(getTailleTableau() > 0)
                    valeur = getTailleTableau();
                setType(Type.ENTIER);
            }
        } else {
            if(getTailleTableau() > 0) {
                valeur = getTailleTableau();
            }
            setType(Type.ENTIER);
        }

    }

    @Override
    public String toMIPS() {
        return codeToMips();
    }
    public int getTailleTableau() {
        if (s != null) {
            return ((SymboleTableau)s).getNbElement() ;
        }
        else return 0;
    }

    public int getDeplacement() {

        return ((SymboleTableau)s).getDeplacement();

    }
}
