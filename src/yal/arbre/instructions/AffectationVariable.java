package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;

public class AffectationVariable extends Affectation {

    private Identificateur gauche;
    private Expression droite;

    public AffectationVariable(Identificateur gauche,Expression droite, int n){

        super(n);
        this.gauche= gauche;
        this.droite = droite;
    }

    @Override
    public void verifier() throws AnalyseException {

        gauche.verifier();
        droite.verifier();
        //verifier si les deux variables sont du meme type
        if(gauche.getType() != droite.getType()) {
            AnalyseSemantiqueException exception =  new AnalyseSemantiqueException(getNoLigne()," affectation: types " + gauche + " et " + droite + " incompatibles !");
            //CompilationErreurs.getInstance().ajouter(exception);
        } else {

            gauche.setValeur(droite.getValeur());
        }
    }

    @Override
    public String toMIPS() {

        return droite.toMIPS()+"sw $v0, "+gauche.getDeplacement()+"($s7)\n\t";

    }

}
