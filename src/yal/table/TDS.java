package yal.table;

import yal.exceptions.AnalyseException;
import yal.exceptions.DoublonsException;
import yal.exceptions.ListeDErreurs;

import java.util.HashMap;

public class TDS {

    private static TDS table = new TDS();
    private HashMap<Entree,Symbole> tableDeSymbole;
    //a revoir
    private static int decalage = 4;


    //constructeur de table de symboles
    public TDS(){

        tableDeSymbole = new HashMap<Entree,Symbole>();

    }

    //on effectue une nouvelle entree dans la table de symbole
    public void ajouter(Entree e, Symbole s ,int no) throws AnalyseException {
        //si le symbole existe deja (pour eviter les doublons)

        if (tableDeSymbole.containsKey(e)) {
            DoublonsException erreur= new DoublonsException("ligne "+no+"\n\t la variable " + e + " est declaree plusieurs fois !");

            ListeDErreurs.getErreurs().addErreur(erreur);

        }else {
            decalage -= 4;
            s.setDeplacement(decalage);
            tableDeSymbole.put(e,s);

        }


    }

    public static TDS getInstance() {
        return table;

    }

    public Symbole getSymboleTable(Entree e) {
        return tableDeSymbole.get(e);

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for(Entree e : tableDeSymbole.keySet()){

            sb.append(e+"  "+tableDeSymbole.get(e)+"\n");

        }

        return sb.toString();
    }
    public boolean isEmpty() {

        return tableDeSymbole.isEmpty();

    }
    public int getSize() {

        return tableDeSymbole.size();

    }

    public HashMap<Entree, Symbole> getTableDeSymbole() {
        return tableDeSymbole;
    }


}

