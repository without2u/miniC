package yal.exceptions;

public class EnsembleDErreurs extends AnalyseException {


    public EnsembleDErreurs(String m) {
        super("LISTE DES ERREURS DETECTEES:\n\t"+m);

    }
}