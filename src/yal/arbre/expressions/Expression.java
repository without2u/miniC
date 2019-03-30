package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.binaire.arithmetique.BinaireArithmetique;
import yal.arbre.expressions.unaire.MoinsUnaire;
import yal.arbre.instructions.Type;

public abstract class Expression extends ArbreAbstrait {
    protected Type type;
    protected int valeur;
    protected int noBlocE;
    protected Expression(int n) {
        super(n) ;
    }

    public int getNoBlocE() {
        return noBlocE;
    }

    public void setNoBlocE(int noBlocE) {
        this.noBlocE = noBlocE;
    }

    public Type getType() {
        return type;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public abstract String codeToMips();
    public static boolean estConstante(Expression expression) {
        Boolean estVrai=false;
        if(expression instanceof ConstanteEntiere) {
            estVrai=true;
        }else if(expression instanceof BinaireArithmetique) {
            estVrai = (estConstante(((BinaireArithmetique)expression).getFilsGauche())
                    && estConstante(((BinaireArithmetique)expression).getFilsDroite())
                    && expression.getType() == Type.ENTIER);
        }
        else if(expression instanceof MoinsUnaire) {
            estVrai = (estConstante(((MoinsUnaire)expression).getE())  &&
                    expression.getType() == Type.ENTIER);
        } else {
            estVrai =false;
        }
        return estVrai;
    }
}
