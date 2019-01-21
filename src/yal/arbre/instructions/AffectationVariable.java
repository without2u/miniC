package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;

public class AffectationVariable extends Affectation {

    private Identificateur filsGauche;
    private Expression filsDroite;

    public AffectationVariable(Identificateur gauche,Expression droite, int n){

        super(n);
        this.filsGauche= gauche;
        this.filsDroite = droite;
    }

    @Override
    public void verifier() throws AnalyseException {

        filsGauche.verifier();
        filsDroite.verifier();
        //verifier si les deux variables sont du meme type
        if(filsGauche.getType() != filsDroite.getType()) {
            AnalyseSemantiqueException exception =  new AnalyseSemantiqueException(getNoLigne()," affectation: types " + filsGauche + " et " + filsDroite + " incompatibles !");
            //CompilationErreurs.getInstance().ajouter(exception);
        } else {

            filsGauche.setValeur(filsDroite.getValeur());
        }
    }

    @Override
    public String toMIPS() {

        return filsDroite.toMIPS()+"sw $v0, "+filsGauche.getDeplacement()+"($s7)\n\t";

    }

}
