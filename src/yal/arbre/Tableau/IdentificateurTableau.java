package yal.arbre.Tableau;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.exceptions.VariablePasDeclareeException;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleTableau;

import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeTableau;


public class IdentificateurTableau extends Identificateur {
    protected String nom;
    protected Symbole symbole;
    protected Expression indice;
    private static int cmpt = -1,tmp;
    public IdentificateurTableau(String nom, Expression indice, int n) {
        super(nom, n);
        this.nom = nom;
        this.indice = indice;
        noBloc = TDS.getInstance().getNoBlocCourant();
        cmpt++;
        this.tmp=cmpt;

    }



    @Override
    public void verifier() throws AnalyseException {

        Entree e =new EntreeTableau(this.nom);
        e.setNoBloc(noBloc);
        symbole = (SymboleTableau)TDS.getInstance().getSymboleTable(e);


            if(symbole == null) {
            e.setNoBloc(0);
            symbole = (SymboleTableau) TDS.getInstance().getSymboleTable(e);

            if (symbole == null) {
                VariablePasDeclareeException erreur = new VariablePasDeclareeException("ligne " + noLigne + "\n\t le tableau " + nom + " n'est pas declarée !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            } else {
                indice.verifier();
                if(indice.getType() != Type.ENTIER) {
                    AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " l'indice du tableau " + "\"" + nom + "\" n'est pas de type entier !");
                    ListeDErreurs.getErreurs().addErreur(erreur);
                }
                else {
                    if(Expression.estConstante(indice)) {
                        if(indice.getValeur() < 0) {
                            AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " l'index du tableau " + "\"" + nom + "\" : " + indice.getValeur()+ " outOfBounds !");
                            ListeDErreurs.getErreurs().addErreur(erreur);
                        }
                        if(getTailleTableau() > 0) {
                            if(indice.getValeur() >= getTailleTableau()) {
                                AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " l'index du tableau " + "\"" + nom + "\" : " + indice.getValeur()+ " outOfBounds !");
                                ListeDErreurs.getErreurs().addErreur(erreur);
                            }
                        }
                    }
                    setType(Type.ENTIER);
                }
            }
        }else {

                indice.verifier();
                if(indice.getType() != Type.ENTIER) {
                    AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " l'indice du tableau " + "\"" + nom + "\" n'est pas de type entier !");
                    ListeDErreurs.getErreurs().addErreur(erreur);
                }
                else {
                    if(Expression.estConstante(indice)) {
                        if(indice.getValeur() < 0) {
                            AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " l'index du tableau " + "\"" + nom + "\" : " + indice.getValeur()+ " outOfBounds !");
                            ListeDErreurs.getErreurs().addErreur(erreur);
                        }
                        if(getTailleTableau() > 0) {
                            if(indice.getValeur() >= getTailleTableau()) {
                                AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " l'index du tableau " + "\"" + nom + "\" : " + indice.getValeur()+ " outOfBounds !");
                                ListeDErreurs.getErreurs().addErreur(erreur);
                            }
                        }
                    }
                    setType(Type.ENTIER);
                }

        }




    }

    @Override
    public String toMIPS() {

        return codeToMips();

    }

    public int getDeplacement() {

        return ((SymboleTableau)symbole).getDeplacement();


    }

    @Override
    public String toString() {

        return nom + "[" + indice + "]";
    }
    public Type getType() {

        if (symbole == null){
            return null;

        }else {
            return  symbole.getTypeS();
        }

    }



    public Symbole getSymbole() {
        return symbole;
    }


    public StringBuilder codeGenerique() {
        cmpt++;
        Identificateur.cmpt++;
        StringBuilder sb = new StringBuilder();

        sb.append(indice.toMIPS());
        sb.append("# verifier l'indice " + indice + "\n\t");
        sb.append("bgez $v0, cmpt" + cmpt + "\n\t");
        sb.append("li $v0, 4\n\t");
        sb.append("la $a0, " + "error_indexTableau" +"\n\t");
        sb.append("syscall\n\t");
        sb.append("j end\n");
        sb.append("cmpt" + cmpt + ": ");
        cmpt++;
        if(getTailleTableau() > 0) {
            sb.append("# " + nom + " est un tableau statique on recupere sa taille dans la TDS\n\t");
            sb.append("slti $t2, $v0, " + getTailleTableau() + "\n\t");
        } else {
            sb.append("# " + nom + " est un tableau dynamique on recupere sa taille\n\t");
            sb.append("lw $t3, " + (getDeplacement() - 4) + "($s7)\n\t");
            sb.append("slt $t2, $v0, $t3\n\t");
        }
        sb.append("beq $t2, 1, cmpt" + cmpt + "\n\t");
        sb.append("li $v0, 4\n\t");
        sb.append("la $a0, " + "error_indexTableau" +"\n\t");
        sb.append("syscall\n\t");
        sb.append("j end\n");
        sb.append("cmpt" + cmpt + ": ");

        if(symbole.getNoBlocS() != getNoBloc() && getNoBloc() != 0) {
            //on augmente le compteur d identifiiannt
            Identificateur.cmpt++;
            sb.append("# charger la valeur " + nom  + "[" + indice + "]" + " du bloc " + symbole.getNoBlocS() + "\n\t");
            sb.append("lw $t7, 4($s7)\n\t");
            sb.append("la $t6, 0($s7)\n\t");
            sb.append("chainageDynamique"+ Identificateur.cmpt + ": beqz $t7, finchainageDynamique" + Identificateur.cmpt + "\n\t");
            sb.append("lw $t6, 8($t6)\n\t");
            sb.append("lw $t7, 4($t6)\n\t");
            sb.append("j chainageDynamique" + Identificateur.cmpt + "\n\t");
            if(getTailleTableau() == 0) {
                sb.append("finchainageDynamique" + Identificateur.cmpt + ": lw $t6, " + getDeplacement() + "($t6)\n\t");
            }else {
                sb.append("finchainageDynamique" + Identificateur.cmpt + ": la $t6, " + getDeplacement() + "($t6)\n\t");
            }
        } else {
            sb.append("# charger la valeur " + nom + "[" + indice + "]" + " du bloc " + symbole.getNoBlocS() + "\n\t");
            if(getTailleTableau() == 0) {
                sb.append("lw $t6, " + getDeplacement() + "($s7)\n\t");
            }else {
                sb.append("la $t6, " + getDeplacement() + "($s7)\n\t");
            }
        }
        sb.append("li $t2, 4\n\t");
        sb.append("mult $v0, $t2\n\t");
        sb.append("mflo $v0\n\t");
        sb.append("sub $t6, $t6, $v0\n\t");
        cmpt++;
        Identificateur.cmpt++;
        return sb;
    }


    @Override
    public String codeToMips() {
        StringBuilder sb = codeGenerique();
        instructionLoad(sb);
        return sb.toString();
    }

    public void instructionLoad(StringBuilder sb) {
        sb.append("lw $v0, ($t6)\n\t");
    }
    public String getNom() {
        return nom;
    }


    public int getTailleTableau() {
        if (symbole != null) {
            return ((SymboleTableau) symbole).getNbElement() ;
        }
        else return 0;
    }

    public Expression getIndice() {
        return indice;
    }
}
