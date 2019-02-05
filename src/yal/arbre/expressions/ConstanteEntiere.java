package yal.arbre.expressions;

import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
        this.type = Type.ENTIER;
        this.valeur = Integer.parseInt(cste);

    }

    @Override
    public String toMIPS() {
    return codeToMips();
    }

    public String codeToMips(){
        StringBuilder sb = new StringBuilder("") ;
        sb.append("li $v0,"+ cste + "\n") ;
        sb.append("\n") ;
        return sb.toString() ;
    }

}
