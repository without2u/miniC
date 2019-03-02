package yal.arbre;

import yal.arbre.fonctions.Retourne;
import yal.exceptions.AnalyseException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait implements Iterable<ArbreAbstrait>{

    protected ArrayList<ArbreAbstrait> listDesBlocs ;

    public BlocDInstructions(int n) {
        super(n);
        listDesBlocs= new ArrayList<ArbreAbstrait>();
    }
    public void ajouter(ArbreAbstrait a) {
        listDesBlocs.add(a);
    }
    public ArbreAbstrait LastInstruction() {
        return listDesBlocs.get(listDesBlocs.size() - 1);
    }

    public boolean ifContientRetourne() {
        for(ArbreAbstrait a : listDesBlocs) {
            if(a instanceof Retourne) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void verifier() throws AnalyseException {
        for(ArbreAbstrait a : listDesBlocs)
            a.verifier();
    }


    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for(ArbreAbstrait a: listDesBlocs)
            sb.append(a.toMIPS());

        return sb.toString();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArbreAbstrait a : listDesBlocs) {
            sb.append(a + "\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<ArbreAbstrait> iterator() {
        return listDesBlocs.iterator();
    }
}