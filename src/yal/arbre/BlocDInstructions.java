package yal.arbre;

import yal.exceptions.AnalyseException;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<ArbreAbstrait> list ;


    public BlocDInstructions(int n) {
        super(n);
        list= new ArrayList<ArbreAbstrait>();
    }

    public void ajouter(ArbreAbstrait a) {
        list.add(a);
    }


    @Override
    public void verifier() throws AnalyseException {
        for(ArbreAbstrait a : list)
            a.verifier();
    }


    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for(ArbreAbstrait a: list)
            sb.append(a.toMIPS());

        return sb.toString();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArbreAbstrait a : list) {
            sb.append(a + "\n");
        }
        return sb.toString();
    }

}