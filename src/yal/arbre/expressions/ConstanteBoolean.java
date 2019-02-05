package yal.arbre.expressions;

import yal.arbre.instructions.Type;

public class ConstanteBoolean extends Constante{
    public ConstanteBoolean(String texte, int n) {
        super(texte, n) ;
        this.type=Type.BOOLEAN;


    }

    @Override
    public String toMIPS() {
        return codeToMips();
    }


    @Override
    public String codeToMips() {
         StringBuilder sb = new StringBuilder();
        if(cste.equals("vrai")){
            sb.append("li $v0, 1\n");
        }
        else if(cste.equals("faux")){
            sb.append("li $v0, 0\n");
        }
        return sb.toString();
    }
}
