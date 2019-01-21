package yal.arbre.expressions;

import yal.arbre.instructions.Type;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
        type = Type.ENTIER;
    }


    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append("li $v0, ") ;
        sb.append(cste) ;
        sb.append("\n") ;
        return sb.toString() ;
    }

    @Override
    public String toString() {
        return "ConstanteEntiere{}";
    }
}
