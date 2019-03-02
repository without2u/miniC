package yal.table;

import yal.exceptions.AnalyseException;
import yal.exceptions.DoublonsException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleFonction;
import yal.table.Symboles.SymboleVar;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeFonction;
import yal.table.tabDesEntrees.EntreeVar;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class TDS implements Iterable<Entree>{

    private static TDS table = new TDS();
    private HashMap<Entree,Symbole> tableDeSymbole;
    //a revoir
    private static int decalage = 4;
    private static int numBloc = 0;
    private static int etiquette = 0;
    private Stack<Integer> pileAbloc;


    //constructeur de table de symboles
    public TDS(){

        tableDeSymbole = new HashMap<Entree,Symbole>();
        pileAbloc = new Stack<Integer>();
        pileAbloc.push(numBloc);

    }

    //on effectue une nouvelle entree dans la table de symbole
    public void ajouter(Entree e, Symbole s ,int no) throws AnalyseException {
        //si le symbole existe deja (pour eviter les doublons)
        if (tableDeSymbole.containsKey(e)) {
            String tmp = (e instanceof EntreeVar) ? "variable " : (e instanceof EntreeFonction) ? "fonction ": "z";
            DoublonsException erreur= new DoublonsException("ligne "+no+"\n\t "+tmp+"la variable " + e + " est declaree plusieurs fois !");
            ListeDErreurs.getErreurs().addErreur(erreur);

        }else {
            // si c'est une fonction on lui affecte une étiquette et un nombre de parametres donnee
            if(e instanceof EntreeFonction) {
                etiquette++;
                ((SymboleFonction)s).setEtiquette(etiquette);
                ((SymboleFonction)s).setNbParam(((EntreeFonction)e).getNbParam());
            }
            if(!existe(e.getNomEntree(), e.getNumeroBloc(), no)) {
                if(e instanceof EntreeVar) {
                    decalage -= 4;
                    ((SymboleVar)s).setDeplacement(decalage);
                }
            }
            tableDeSymbole.put(e,s);
        }
    }

    private boolean existe(String nom, int numeroBloc, int noLigne) {
        boolean found = false;
        for(Entree e : tableDeSymbole.keySet()) {
            if((e instanceof EntreeVar ) && (e.getNomEntree().equals(nom)) && e.getNumeroBloc() == numeroBloc) {
                found = true;
                AnalyseException erreur = new DoublonsException(noLigne+"\"" + nom + "\"" + " et variable " + "\"" + e + "\"" + " dupliquées !");
                ListeDErreurs.getErreurs().addErreur(erreur);
                break;
            }
        }
        return found;
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

    public void nouvelleEntree() {

        numBloc++;
        pileAbloc.push(numBloc);
        decalage = 4;
    }
    public void uneSortie() {

        pileAbloc.pop();
        decalage = 4;
    }

    @Override
    public Iterator<Entree> iterator() {
        return tableDeSymbole.keySet().iterator();
    }
    public int getNoBlocCourant() {

        int bloc = pileAbloc.peek();
        return bloc;
    }
}