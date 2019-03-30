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

    private Symbole symbole;
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

        symbole = (SymboleVar)TDS.getInstance().getSymboleTable(entree);
        Symbole symbole2 = (SymboleTableau)TDS.getInstance().getSymboleTable(entree2);
        //setType(Type.ENTIER);
		/*if(symbole == null) {
			entree.setNumeroBloc(0);
			symbole = (SymboleVariable)TDS.getInstance().identifier(entree);
			if(symbole == null) {
				AnalyseException a = new AnalyseSemantiqueException(noLigne + ": variable " + "\"" + nom + "\"" + " non declarée !");
				CompilationErreurs.getInstance().ajouter(a);
			} else {
				setType(Type.ENTIER);
			}
		} else
			setType(Type.ENTIER);*/

        if(numBloc != 0) {
            if(symbole == null) {
                if(symbole2 != null) {
                    symbole = symbole2;
                    setType(Type.TABLEAU);
                } else {
                    entree.setNoBloc(0);
                    entree2.setNoBloc(0);
                    symbole = (SymboleVar)TDS.getInstance().getSymboleTable(entree);
                    symbole2 = (SymboleTableau)TDS.getInstance().getSymboleTable(entree2);
                    if(symbole == null) {
                        if(symbole2 != null) {
                            symbole = symbole2;
                            setType(Type.TABLEAU);
                        } else {
                            AnalyseException a = new AnalyseSemantiqueException(noLigne ,": variable " + "\"" + nom + "\"" + " non declarée !");
                            ListeDErreurs.getErreurs().addErreur(a);
                        }
                    } else {
                        setType(Type.ENTIER);
                    }
                }
            } else
                setType(Type.ENTIER);
        } else {
            if(symbole == null) {
                if(symbole2 != null) {
                    symbole = symbole2;
                    setType(Type.TABLEAU);
                } else {
                    AnalyseException a = new AnalyseSemantiqueException(noLigne , ": variable " + "\"" + nom + "\"" + " non declarée !");
                    ListeDErreurs.getErreurs().addErreur(a);                }
            } else {
                setType(Type.ENTIER);
            }

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

        if (symbole == null){
            return null;

        }else {
            return  symbole.getTypeS();
        }
    }

    @Override
    public String codeToMips() {
        StringBuilder sb = new StringBuilder();
        if(symbole.getNoBlocS() != getNoBloc() && getNoBloc() != 0) {
            cmpt++;
            sb.append("# la valeur de la variable " + nom + " du bloc " + symbole.getNoBlocS() + " avec ( chainage dynamique ) : \n\t");
            sb.append("lw $t7, 4($s7)\n\t");
            sb.append("la $t6, 0($s7)\n\t");
            sb.append("chain"+ cmpt + ": beqz $t7, endchain" + cmpt + "\n\t");
            sb.append("lw $t6, 8($t6)\n\t");
            sb.append("lw $t7, 4($t6)\n\t");
            sb.append("j chain" + cmpt + "\n\t");
            sb.append("endchain" + cmpt + ": lw $v0, " + getDeplacement() + "($t6)\n\t");
        } else {
            sb.append("# la valeur de la variable " + nom + " du bloc " + symbole.getNoBlocS() + "\n\t");
            sb.append("lw $v0, " + getDeplacement() + "($s7)\n\t");
        }
        return sb.toString();

    }

    public int getDeplacement() {
        return ((SymboleVar)symbole).getDeplacement();
    }

    public Symbole getSymbole() {
        return symbole;
    }

    public int getNumBloc() {
        return numBloc;
    }

    public void setNumBloc(int numBloc) {
        this.numBloc = numBloc;
    }
}