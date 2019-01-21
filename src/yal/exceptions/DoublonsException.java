package yal.exceptions;

public class DoublonsException extends AnalyseException {

    public DoublonsException(String m) {
        super("ERREUR DOUBLE DECLARATION :\n\t ligne "+m);
    }
}
