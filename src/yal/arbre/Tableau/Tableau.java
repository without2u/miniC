package yal.arbre.Tableau;

import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleTableau;
import yal.table.TDS;
import yal.table.tabDesEntrees.EntreeTableau;

public class Tableau extends IdentificateurTableau {

    private Symbole symbole;
    public Tableau(String nom, Expression indice, int n) {
        super(nom, indice, n);
    }

    public int getDeplacement() {

        return ((SymboleTableau)symbole).getDeplacement();


    }

    @Override
    public void verifier() throws AnalyseException {
        EntreeTableau e = new EntreeTableau(nom);
        e.setNoBloc(noBloc);

        symbole = (SymboleTableau) TDS.getInstance().getSymboleTable(e);

        if(symbole == null) {
            e.setNoBloc(0);
            symbole = (SymboleTableau)TDS.getInstance().getSymboleTable(e);
            if(symbole == null) {
                AnalyseException erreur = new AnalyseSemantiqueException(noLigne , ": tableau " + "\"" + nom + "\"" + " non declaré !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            } else {
                indice.verifier();
                verifierConstante();
                verifierSizeValeur();
            }
        } else {
            indice.verifier();
            if(indice.getType() != Type.ENTIER) {
                AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " l'indice du tableau " + "\"" + nom + "\" n'est pas de type entier !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            } else {
                if(noBloc == 0) {
                    if(Expression.estConstante(indice)) {
                        verifierConstante();
                        verifierSizeValeur();
                    }
                }
                else if(Expression.estConstante(indice)) {
                    verifierSizeValeur();
                }
            }
        }
    }

    private void verifierConstante() {
        if(!Expression.estConstante(indice)) {
            AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " la taille du tableau " + "\"" + nom + "\" n'est pas une constante entière positive!");
            ListeDErreurs.getErreurs().addErreur(erreur);
        }
    }

    private void verifierSizeValeur() {
        if(indice.getValeur() < 0) {
            AnalyseException erreur = new AnalyseSemantiqueException(noLigne , " l'index du tableau " + "\"" + nom + "\" : " + indice.getValeur()+ " outOfBounds !");
            ListeDErreurs.getErreurs().addErreur(erreur);
        }
    }

}
