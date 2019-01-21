package yal.exceptions;

public class VariablePasDeclareeException extends AnalyseException {
    public VariablePasDeclareeException(String m) {
        super("ERREUR VARIABLE PAS DECLAREE :\n\t ligne "+m);
    }
}
