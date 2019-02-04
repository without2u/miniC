package yal.arbre.expressions.binaire.comparaison;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;

public abstract class Comparaison extends Binaire {
    public Comparaison(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }

    @Override
    public void verifier() throws AnalyseException {
        getFilsGauche().verifier();
        getFilsDroite().verifier();

        if(getFilsGauche().getType() != Type.ENTIER || getFilsDroite().getType() != Type.ENTIER) {
            AnalyseSemantiqueException a =  new AnalyseSemantiqueException(getNoLigne() ," les types des operandes doivent être ENTIER pour l'opérateur : " + getOperateur());
            ListeDErreurs.getErreurs().addErreur(a);
        }

    }
}
