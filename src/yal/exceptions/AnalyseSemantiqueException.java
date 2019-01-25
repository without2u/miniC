package yal.exceptions;

public class AnalyseSemantiqueException extends AnalyseException {
 
    public AnalyseSemantiqueException(int ligne, String m) {
        super("ERREUR SEMANTIQUE :\n\t ligne " + ligne +":" + m + "\n") ;
    }

}
