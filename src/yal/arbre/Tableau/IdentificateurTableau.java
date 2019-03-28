package yal.arbre.Tableau;

import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Type;
import yal.exceptions.AnalyseException;
import yal.exceptions.ListeDErreurs;
import yal.exceptions.VariablePasDeclareeException;
import yal.table.Symboles.Symbole;
import yal.table.Symboles.SymboleTableau;
import yal.table.Symboles.SymboleVar;
import yal.table.TDS;
import yal.table.tabDesEntrees.Entree;
import yal.table.tabDesEntrees.EntreeTableau;
import yal.table.tabDesEntrees.EntreeVar;

public class IdentificateurTableau extends Expression {
    private String nom;
    private Symbole symbole;
    private Expression indice;
    private int numBloc,tmp;
    public static int deplVar;
    private static int cmpt = 0;
    public IdentificateurTableau(String nom, Expression indice, int n) {
        super(n);
        this.nom = nom;
        this.indice = indice;
        this.valeur = indice.getValeur();
        numBloc = TDS.getInstance().getNoBlocCourant();

        cmpt++;
        this.tmp=cmpt;

    }



    @Override
    public void verifier() throws AnalyseException {

        Entree e =new EntreeTableau(this.nom);
        e.setNoBloc(noBloc);
        symbole = (SymboleTableau)TDS.getInstance().getSymboleTable(e);


            if(symbole == null) {
            e.setNoBloc(0);
            symbole = (SymboleTableau) TDS.getInstance().getSymboleTable(e);

            if (symbole == null) {
                VariablePasDeclareeException erreur = new VariablePasDeclareeException("ligne " + noLigne + "\n\t le tableau " + nom + " n'est pas declarée !");
                ListeDErreurs.getErreurs().addErreur(erreur);
            } else {
                setType(Type.ENTIER);
            }
        }else {

            setType(Type.ENTIER);
                deplVar = getDeplacement();
        }




    }

    @Override
    public String toMIPS() {

        return codeToMips();

    }

    public int getDeplacement() {

        return ((SymboleTableau)symbole).getDeplacement();


    }

    @Override
    public String toString() {

        return nom + "[" + indice + "]";
    }
    public Type getType() {

        if (symbole == null){
            return null;

        }else {
            return  symbole.getTypeS();
        }

    }

    public int getNumBloc() {
        return numBloc;
    }

    public void setNumBloc(int numBloc) {
        this.numBloc = numBloc;
    }

    public Symbole getSymbole() {
        return symbole;
    }
   /* public String codeDeclaration(){

    }*/
    @Override
    public String codeToMips() {

        StringBuilder accesCase = new StringBuilder(50);

        accesCase.append("# Place la valeur d'une case de tableau dans $v0\n");

        /* Base courante */
        accesCase.append("# Récupère la base courante\n");
        accesCase.append("move $t2, $s7\n");

        /* Numéro de région où est déclarée la variable */
        accesCase.append("# Récupère le numéro de région où est déclaré le tableau\n");
        accesCase.append("li $v1, ");
        accesCase.append(numBloc);
        accesCase.append("\n");

        /* Entrée TantQue */
        accesCase.append("tq_");
        accesCase.append(hashCode());
        accesCase.append(" :\n");

        /* On récupère le numéro de région de l'environnement courant */
        accesCase.append("lw $v0, 4($t2) \n");
        accesCase.append("sub $v0, $v0, $v1\n");

        /* Branchement vers la fin si les deux numéros sont égaux */
        accesCase.append("beqz $v0, fintq_");
        accesCase.append(hashCode());
        accesCase.append("\n");

        /* On repart au début et on essaye avec l'environnement précédent sinon */
        accesCase.append("lw $t2, 8($t2) \n");
        accesCase.append("j tq_");
        accesCase.append(hashCode());
        accesCase.append("\n");

        /* Sortie TantQue */
        accesCase.append("fintq_");
        accesCase.append(hashCode());
        accesCase.append(" :\n");

        /* Chargement de l'adresse du tableau dans $t8 */
        accesCase.append("# Chargement de l'adresse du tableau dans $t8\n");
        accesCase.append("lw $t8, ");
        accesCase.append(deplVar);
        accesCase.append("($t2)");
        accesCase.append("\n");

        /* Indice dans $v0 */
        accesCase.append(indice.toMIPS());

        /* Accès avec un indice inférieur à 0 */
        accesCase.append("bltz $v0, erreurAccesTableauInvalide\n");

        /* On charge la longueur du tableau dans $t2 */
        accesCase.append("lw $t2, 0($t8)\n");

        /* La longueur moins l'indice dans $t2 */
        accesCase.append("sub $t2, $t2, $v0\n");

        /* Accès avec un indice supérieur à la longueur du tableau */
        accesCase.append("blez $t2, erreurAccesTableauInvalide\n");

        /* $v0 = indiceTableau, $t8 = adresse début tableau */
        /* $t1 va contenir le déplacement pour aller au bon indice */
        accesCase.append("li $t3, -4\n");
        accesCase.append("mult $v0, $t3\n");
        accesCase.append("mflo $t1\n");

        /* On retire -4 à $t1 (place de la longueur) */
        accesCase.append("add $t1, $t1, -4\n");

        /* Ajout du déplacement de $t1 à $t8 */
        accesCase.append("add $t8, $t8, $t1\n");

        /* Chargement de la case dans $v0 */
        accesCase.append("lw $v0, 0($t8)\n");

        return accesCase.toString();
    }

    public String getNom() {
        return nom;
    }

}
