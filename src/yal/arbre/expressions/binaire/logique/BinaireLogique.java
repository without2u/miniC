package yal.arbre.expressions.binaire.logique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;

public abstract class BinaireLogique extends Binaire {
    public BinaireLogique(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);
    }
    @Override
    public void verifier() throws AnalyseException {
        if(getFilsGauche().getType() == Type.BOOLEAN && getFilsDroite().getType() == Type.BOOLEAN) {
            setType(Type.BOOLEAN);
        } else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(getNoLigne() ,": les types des operandes doivent être BOOLEAN ");
            ListeDErreurs.getErreurs().addErreur(a);
        }
    }

}
