package yal.arbre.expressions.binaire;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Identificateur;
import yal.exceptions.AnalyseException;

public abstract class Binaire extends Expression {
    private Expression filsGauche;
    private Expression filsDroite;
    public Binaire(Expression filsGauche, Expression filsDroite){
        super(filsGauche.getNoLigne());
        this.filsDroite=filsDroite;
        this.filsGauche=filsGauche;
    }
    public abstract String getOperateur() ;

    @Override
    public String toString() {

            return "(" + filsGauche + getOperateur() + filsDroite + ")" ;
    }

    @Override
    public String toMIPS() {

        StringBuilder sb = new StringBuilder();
        sb.append("# on clacule l'expression " +this+"\n\t");
        if(filsGauche instanceof Identificateur) {
            sb.append("lw $v0, " + ((Identificateur)filsGauche).getDeplacement() + "($s7)\n");
        } else{
            sb.append(filsGauche.toMIPS());
        }
        sb.append("sw $v0, 0($sp)\n\t");
        sb.append("addi $sp, $sp, -4\n\t");

        if(filsDroite instanceof Identificateur) {
            sb.append("lw $v0, " + ((Identificateur)filsDroite).getDeplacement() + "($s7)\n");
        } else {
            sb.append(filsDroite.toMIPS());
        }
        sb.append("addi $sp, $sp, 4\n\t");
        sb.append("lw $t8, 0($sp)\n\t");
        sb.append(codeToMips());

        return sb.toString();
    }

    @Override
    public void verifier() throws AnalyseException {
        filsGauche.verifier();
        filsDroite.verifier();
    }

    public Expression getFilsDroite() {
        return filsDroite;
    }

    public Expression getFilsGauche() {
        return filsGauche;
    }
}
