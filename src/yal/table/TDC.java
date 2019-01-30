package yal.table;

import java.util.HashMap;
import java.util.Iterator;

public class TDC implements Iterable<String> {

    // compteur de chaines de caractères
    private static int compteur = -1;

    // table des chaines: une chaine ---> un id entier
    private HashMap<String, Integer> tdc;
    private static TDC instance = new TDC();


    // constructeur privé de notre table des chaines de caractères
    private TDC() {
        this.tdc = new HashMap<String, Integer>();
    }


    public void ajouterChaine(String chaine) {
        if(!tdc.containsKey(chaine)) {
            compteur++;
            tdc.put(chaine, compteur);
        }
    }

    // retourne le numéro id de la chaine passée en paramètre
    public Integer identifier(String chaine) {
        Integer num = tdc.get(chaine);
        return (num != null) ? num : -1;
    }

    // retourne un itérateur de notre collection
    public Iterator<String> iterator() {
        return tdc.keySet().iterator();
    }

    // singleton de la table des chaines
    public static TDC getInstance() {
        return instance;
    }

    // representation textuelle de la table
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String str : tdc.keySet())
            sb.append("chaine: " + str + " numéro: " + tdc.get(str) + "\n");
        return sb.toString();
    }

}
