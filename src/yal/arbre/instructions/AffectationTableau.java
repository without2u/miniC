package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.Tableau.IdentificateurTableau;
import yal.exceptions.AnalyseException;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.ListeDErreurs;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleTableau;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeTableau;
import yal.table.tabDesEntrees.EntreeVar;

public class AffectationTableau extends Affectation {
    private Expression indice;
    private Expression droite;
    private IdentificateurTableau idf;
    public static int deplVar;
    private SymboleTableau symbole;



    public AffectationTableau(IdentificateurTableau idf, Expression indice, Expression droite, int no) {
        super(no);
        this.idf=idf;
        this.indice = indice;
        this.droite = droite;
        this.noBloc = TDS.getInstance().getNoBlocCourant();
       // this.deplVar=- TDS.getInstance().tailleZoneDesVariables();
    }

    @Override
    public void verifier() throws AnalyseException {
        idf.verifier();
        indice.verifier();
        droite.verifier();
        if(indice.getType() != Type.ENTIER || droite.getType() != Type.ENTIER) {
            AnalyseSemantiqueException exception =  new AnalyseSemantiqueException(getNoLigne() , ": affectation tableau: types indice " + indice + " et " + droite + " incompatibles !");
            ListeDErreurs.getErreurs().addErreur(exception);
        }
        Entree e =new EntreeTableau(this.idf.getNom());
        e.setNoBloc(noBloc);
        symbole = (SymboleTableau)TDS.getInstance().getSymboleTable(e);
        deplVar = ((SymboleTableau)symbole).getDeplacement();

    }

    @Override
    public String toMIPS() {
        StringBuilder aff = new StringBuilder(50);

        aff.append("# Affectation d'une valeur à une case d'un tableau\n");
        aff.append(droite.toMIPS());
       // System.out.println(idf.toString());

        aff.append("# Empile la valeur à mettre dans la variable\n");
        aff.append("sw $v0, 0($sp)\n");
        aff.append("add $sp, $sp, -4\n");

		/* Code pour récupérer l'adresse de la case du tableau
		   où mettre la valeur de l'expression */

        /* Base courante */
        aff.append("# Récupère la base courante\n");
        aff.append("move $t2, $s7\n");

        /* Numéro de région où est déclarée la variable */
        aff.append("# Récupère le numéro de région où est déclaré le tableau\n");
        aff.append("li $v1, ");
        aff.append(noBloc);
        aff.append("\n");

        /* Entrée TantQue */
        aff.append("tq_");
        aff.append(hashCode());
        aff.append(" :\n");

        /* On récupère le numéro de région de l'environnement courant */
        aff.append("lw $v0, 4($t2) \n");
        aff.append("sub $v0, $v0, $v1\n");

        /* Branchement vers la fin si les deux numéros sont égaux */
        aff.append("beqz $v0, fintq_");
        aff.append(hashCode());
        aff.append("\n");

        /* On repart au début et on essaye avec l'environnement précédent sinon */
        aff.append("lw $t2, 8($t2) \n");
        aff.append("j tq_");
        aff.append(hashCode());
        aff.append("\n");

        /* Sortie TantQue */
        aff.append("fintq_");
        aff.append(hashCode());
        aff.append(" :\n");

        /* Chargement de l'adresse du tableau dans $t8 */
        aff.append("# Chargement de l'adresse du tableau dans $t8\n");
        aff.append("lw $t8, ");
        aff.append(deplVar);
        aff.append("($t2)");

        aff.append("\n");

        /* Indice dans $v0 */
        aff.append(indice.toMIPS());

        /* Accès avec un indice inférieur à 0 */
        aff.append("bltz $v0, erreurAccesTableauInvalide\n");

        /* On charge la longueur du tableau dans $t2 */
        aff.append("lw $t2, 0($t8)\n");

        /* La longueur moins l'indice dans $t2 */
        aff.append("sub $t2, $t2, $v0\n");

        /* Accès avec un indice supérieur à la longueur du tableau */
        aff.append("blez $t2, erreurAccesTableauInvalide\n");

        /* $v0 = indiceTableau, $t8 = adresse début tableau */
        /* $t1 va contenir le déplacement pour aller au bon indice */
        aff.append("li $t3, -4\n");
        aff.append("mult $v0, $t3\n");
        aff.append("mflo $t1\n");

        /* On retire -4 à $t1 (place de la longueur) */
        aff.append("add $t1, $t1, -4");

        aff.append("# Dépile la valeur à mettre dans la variable\n");
        aff.append("add $sp, $sp, 4\n");
        aff.append("lw $v0, 0($sp)\n");

        /* Ajout du déplacement de $t1 à $t8 */
        aff.append("add $t8, $t8, $t1\n");

        /* Chargement de la valeur dans la case du tableau */
        aff.append("sw $v0, 0($t8)\n");

        return aff.toString();
    }
}
