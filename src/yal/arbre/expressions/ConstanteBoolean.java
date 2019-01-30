package yal.arbre.expressions;

import yal.arbre.instructions.Type;

public class ConstanteBoolean extends Constante{
    public ConstanteBoolean(String texte, int n) {
        super(texte, n) ;
        type=Type.BOOLEAN;


    }


    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        if(cste=="vrai"){
            sb.append("li $v0, 1\n\t");
        }
        if(cste=="faux"){
            sb.append("li $v0, 0\n\t");
        }
        return sb.toString();
    }


}
