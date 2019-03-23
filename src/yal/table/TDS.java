package yal.table;

import yal.exceptions.AnalyseException;
import yal.exceptions.DoublonsException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleFonction;
import yal.table.Symboles.SymboleTableau;
import yal.table.Symboles.SymboleVar;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeFonction;
import yal.table.tabDesEntrees.EntreeTableau;
import yal.table.tabDesEntrees.EntreeVar;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class TDS implements Iterable<Entree>{

    private static TDS table = new TDS();
    private HashMap<Entree,Symbole> tableDeSymbole;
    private static int decalage = 4;
    private static int numBloc = 0;
    private static int etiquette = 0;
    private Stack<Integer> pileAbloc;
    private String typeSymboleEntrer=null;


    //constructeur de table de symboles
    public TDS(){

        tableDeSymbole = new HashMap<Entree,Symbole>();
        pileAbloc = new Stack<Integer>();
        pileAbloc.push(numBloc);

    }

    //on effectue une nouvelle entree dans la table de symbole
    public void ajouter(Entree e, Symbole s ,int no) throws AnalyseException {
        //si le symbole existe deja (pour eviter les doublons)


        if (!tableDeSymbole.containsKey(e)) {
            if(e instanceof EntreeFonction) {
                typeSymboleEntrer=" fonction ";
                etiquette++;
                ((SymboleFonction)s).setEtiquetteSymbole(etiquette);
                ((SymboleFonction)s).setNbrParamSymbole(((EntreeFonction)e).getNombreParamFonction());
            }else if (e instanceof EntreeVar){
                typeSymboleEntrer=" variable ";
                decalage -= 4;
                ((SymboleVar)s).setDeplacement(decalage);
            }else if (e instanceof EntreeTableau){
                decalage -= 4;
                ((SymboleTableau)s).setDeplacement(decalage);
            }
            tableDeSymbole.put(e,s);
        }else {

            DoublonsException erreur= new DoublonsException("ligne "+no+" la"+typeSymboleEntrer+" \"" + e + "\" est declaree plusieurs fois !");
            ListeDErreurs.getErreurs().addErreur(erreur);


        }
    }

    private boolean dejaExiste(String nom, int numeroBloc, int no) {

        for(Entree e : tableDeSymbole.keySet()) {
            if((e instanceof EntreeVar ) && (e.getNomEntree().equals(nom)) && e.getNoBloc() == numeroBloc) {


                AnalyseException erreur = new DoublonsException("ligne "+no+" la variable " + e + " est declaree plusieurs fois !");
                ListeDErreurs.getErreurs().addErreur(erreur);
                return true;

            }
        }
        return false;
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