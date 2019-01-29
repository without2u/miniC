package yal.arbre.expressions;

import yal.arbre.instructions.Type;

public class ConstanteBoolean extends Constante{
    public ConstanteBoolean(String texte, int n) {
        super(texte, n) ;
        this.type = Type.ENTIER;
    }


    @Override
    public void verifier() {

    }


    @Override
    public String toMIPS() {
        return instructionMIPS();
    }



    public String instructionMIPS() {
        StringBuilder sb = new StringBuilder();
        switch(cste) {
            case "vrai" : sb.append("li $v0, 1\n\t");break;
            case "faux" : sb.append("li $v0, 0\n\t");break;
        }
        return sb.toString();
    }
}
