package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.arbre.expressions.IdentificateurTableau;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.TDS;

public class AffectationTableau extends Affectation {
    private Expression indice;
    private Expression droite;
    private IdentificateurTableau idf;
    public AffectationTableau(IdentificateurTableau idf, Expression indice, Expression droite, int no) {
        super(no);
        this.idf=idf;
        this.indice = indice;
        this.droite = droite;
        this.noBloc = TDS.getInstance().getNoBlocCourant();
    }

    @Override
    public void verifier() throws AnalyseException {

        indice.verifier();
        droite.verifier();
        if(indice.getType() != Type.ENTIER || droite.getType() != Type.ENTIER) {
            AnalyseSemantiqueException exception =  new AnalyseSemantiqueException(getNoLigne() , ": affectation tableau: types indice " + indice + " et " + droite + " incompatibles !");
            ListeDErreurs.getErreurs().addErreur(exception);
        }


    }

    @Override
    public String toMIPS() {

        StringBuilder aff = new StringBuilder(50);
        //System.out.println("AFFECT"+deplacement);
        aff.append("# Affectation\n");
        aff.append("# On calcule la valeur de l'indice");
        aff.append(indice.toMIPS());
        aff.append("# On multiplie la valeur de l'indice par -4");
        aff.append("li $t8, -4 \n");
        aff.append("mult $v0, $t8\n");
        aff.append("mflo $v0\n");
        aff.append("# Empilement de la partie gauche\n");
        aff.append("sw $v0, 0($sp)\n");
        aff.append("add $sp, $sp, -4\n");
        aff.append("# Calcul de la partie droite\n");
        aff.append(droite.toMIPS());
        aff.append("# Dépilement de la partie gauche\n");
        aff.append("add $sp, $sp, 4\n");
        aff.append("lw $t8, 0($sp)\n");
        aff.append("add $s7,$s7, -"+idf.getDeplacement()+" \n");
        aff.append("add $s7,$s7,$t8");
        aff.append("sw $v0, ($s7)");
        aff.append("add $s7,$s7, "+idf.getDeplacement()+" \n");
        aff.append("sub $s7,$s7,$t8");

        return aff.toString();

    }
}
