package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.arbre.expressions.binaire.arithmetique.Division;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.EnsembleDErreurs;
import yal.exceptions.ListeDErreurs;
import yal.table.TDS;

public class AffectationVariable extends Affectation {

    private Identificateur filsGauche;
    private Expression filsDroite;

    public AffectationVariable(Identificateur gauche,Expression droite, int n){

        super(n);
        this.filsGauche= gauche;
        this.filsDroite = droite;

        this.noBloc = TDS.getInstance().getNoBlocCourant();
    }

    @Override
    public String toString() {
        return "affectation de " + filsDroite + " dans " + filsGauche;
    }

    @Override
    public void verifier() throws AnalyseException {

        filsGauche.verifier();
        filsDroite.verifier();
        //verifier si les deux variables sont du meme type
        if(filsGauche.getType() != filsDroite.getType()) {

            AnalyseSemantiqueException erreur =  new AnalyseSemantiqueException(getNoLigne()," Erreur d'affectation: le type de " +
                    filsGauche + " et de " + filsDroite + " n'est pas compatibles !");
            ListeDErreurs.getErreurs().addErreur(erreur);

        } else {

            if(filsDroite instanceof Division) {
                if(((Division) filsDroite).getFilsDroite().getValeur() != 0) {

                    filsGauche.setValeur(filsDroite.getValeur());

                }
            }

        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append(filsDroite.toMIPS());
        if(filsGauche.getSymbole().getNoBlocS() == getNoBloc()) {

            sb.append("# Affectarion de la valeur de " + filsDroite + " dans la variable " + filsGauche + " du bloc numero : "
                    + filsGauche.getSymbole().getNoBlocS() + "\n\t");
            sb.append("sw $v0, " + filsGauche.getDeplacement() + "($s7)\n\t");
            sb.append("# stockage ok \n\t");

        } else {
            sb.append("# on charge dans t8 l'anceinne valeur de la base\n\t");
            sb.append("lw $t8, 8($s7)\n\t");
            sb.append("# stockage de la valeur de " + filsDroite + " dans la variable " + filsGauche + " du bloc numero : "
                    + filsGauche.getSymbole().getNoBlocS() + "\n\t");
            sb.append("sw $v0, " + filsGauche.getDeplacement() + "($t8)\n\t");
            sb.append("# stockage ok \n\t");

        }
        return sb.toString();

    }

}