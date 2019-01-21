package yal.exceptions;

import java.util.ArrayList;
import java.util.Iterator;

public class ListeDErreurs implements Iterable<AnalyseException> {

    private static ListeDErreurs erreurs=new ListeDErreurs();
    private ArrayList<AnalyseException> listErreurs;

    @Override
    public Iterator<AnalyseException> iterator() {
        return listErreurs.iterator();
    }
    public ListeDErreurs(){
        listErreurs=new ArrayList<AnalyseException>();
    }

    public void addErreur(AnalyseException e){
        listErreurs.add(e);
    }

    public static ListeDErreurs getErreurs() {
        return erreurs;
    }

    public ArrayList<AnalyseException> getListErreurs() {
        return listErreurs;
    }

}
