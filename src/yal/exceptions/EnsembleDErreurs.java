package yal.exceptions;

public class EnsembleDErreurs extends AnalyseException {


    protected EnsembleDErreurs(String m) {
        super("LISTE DES ERREURS DETECTEES\n"+m);
    }
}