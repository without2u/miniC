package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.Tableau.IdentificateurTableau;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.TDS;


public class AffectationTableau extends Affectation {

    private IdentificateurTableau idf;
    private Expression droite;

    public AffectationTableau(IdentificateurTableau idf, Expression droite, int no) {
        super(no);
        this.idf=idf;
        this.droite = droite;
        this.noBloc = TDS.getInstance().getNoBlocCourant();

    }

    @Override
    public void verifier() throws AnalyseException {

        idf.verifier();
        droite.verifier();
        if(droite.getType() != Type.ENTIER && droite.getType() != idf.getType()) {
            AnalyseSemantiqueException exception =  new AnalyseSemantiqueException(getNoLigne() , ": erreur affectation tableau: le type de  " + idf + " et " + droite + " incompatibles !");
            ListeDErreurs.getErreurs().addErreur(exception);
        }

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append(droite.toMIPS());
        sb.append("move $t5, $v0\n\t");
        sb.append(idf.codeGenerique().toString());
        sb.append("sw $t5, 0($t6)\n\t");
        return sb.toString();
    }
}
