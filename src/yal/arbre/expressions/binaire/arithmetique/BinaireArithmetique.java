package yal.arbre.expressions.binaire.arithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;

public abstract class BinaireArithmetique extends Binaire {
    public BinaireArithmetique(Expression filsGauche, Expression filsDroite) {
        super(filsGauche, filsDroite);

    }

    @Override
    public void verifier() throws AnalyseException {
        getFilsDroite().verifier();
        getFilsGauche().verifier();

        if((getFilsGauche().getType() != Type.ENTIER) || (getFilsDroite().getType() != Type.ENTIER)) {
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(getNoLigne() ,": les types des operandes doivent être ENTIER ");
            ListeDErreurs.getErreurs().addErreur(a);
        } else {
            //le type d'expression
            setType(Type.ENTIER);
            setValeur(this.getValeur());
        }
    }
}
