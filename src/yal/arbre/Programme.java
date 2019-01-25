package yal.arbre;

import yal.exceptions.AnalyseException;
import yal.exceptions.EnsembleDErreurs;
import yal.exceptions.ListeDErreurs;
import yal.table.Tds;

public class Programme extends ArbreAbstrait {

    protected String nomProg;
    protected BlocDInstructions bloc;
    private static int decalage = 4;
    public Programme(String nomProg,int n) {

        super(n);
        this.nomProg=nomProg;
        this.bloc=new BlocDInstructions(n);

    }

    @Override
    public String toString() {
        return "Programme{" +
                "nomProg='" + nomProg + '\'' +
                ", bloc=" + bloc +
                '}';
    }

    @Override
    public void verifier() throws AnalyseException {

        bloc.verifier();
        if(!ListeDErreurs.getErreurs().isEmpty())
            throw new EnsembleDErreurs("");
    }

    @Override
    public String toMIPS() {

        StringBuilder sb = new StringBuilder("") ;

        sb.append(".data\n" +
                " fin:         .asciiz \n" +
                "              .align 2\n"+
                ".text\n" +
                " main :\n") ;
        if(!Tds.getInstance().isEmpty()) {

            sb.append("move $s7, $sp\n\t");
            sb.append("addi $sp, $sp, " + (Tds.getInstance().getSize()) *(-decalage) + "\n\n\t");

        }
        sb.append(bloc.toMIPS() + "\n");
        sb.append("end :\n" +
                "move $v1, $v0\n"+
                " li $v0, 10 \n" +
                " syscall\n") ;
        return sb.toString() ;
    }

    //ajouter un nouveau bloc au programme
    public void ajouterBloc(ArbreAbstrait a){

        bloc.ajouter(a);

    }
}
