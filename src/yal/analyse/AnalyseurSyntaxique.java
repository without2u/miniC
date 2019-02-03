
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sun Feb 03 21:06:20 CET 2019
//----------------------------------------------------

package yal.analyse;

import java.util.*;
import yal.arbre.*;
import yal.table.*;
import yal.arbre.expressions.*;
import yal.arbre.instructions.*;
import yal.exceptions.AnalyseSyntaxiqueException;
import yal.arbre.expressions.unaire.NonLogique;
import yal.arbre.expressions.unaire.MoinsUnaire;
import java_cup.runtime.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Sun Feb 03 21:06:20 CET 2019
  */
public class AnalyseurSyntaxique extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public AnalyseurSyntaxique() {super();}

  /** Constructor which sets the default scanner. */
  public AnalyseurSyntaxique(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalyseurSyntaxique(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\031\000\002\002\004\000\002\002\007\000\002\002" +
    "\010\000\002\005\004\000\002\005\003\000\002\012\005" +
    "\000\002\012\003\000\002\012\003\000\002\012\003\000" +
    "\002\003\003\000\002\003\003\000\002\003\004\000\002" +
    "\003\004\000\002\004\003\000\002\004\003\000\002\011" +
    "\006\000\002\006\004\000\002\006\003\000\002\007\003" +
    "\000\002\010\005\000\002\013\005\000\002\014\006\000" +
    "\002\014\007\000\002\014\010\000\002\014\011" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\004\004\005\001\002\000\004\002\066\001" +
    "\002\000\004\021\006\001\002\000\004\005\007\001\002" +
    "\000\014\010\015\012\017\013\011\014\024\021\012\001" +
    "\002\000\014\010\015\012\017\013\011\014\024\021\012" +
    "\001\002\000\014\020\033\021\027\022\026\024\032\025" +
    "\030\001\002\000\004\011\056\001\002\000\020\006\ufffd" +
    "\010\ufffd\013\ufffd\014\ufffd\016\ufffd\017\ufffd\021\ufffd\001" +
    "\002\000\020\006\ufffa\010\ufffa\013\ufffa\014\ufffa\016\ufffa" +
    "\017\ufffa\021\ufffa\001\002\000\004\021\054\001\002\000" +
    "\020\006\ufff9\010\ufff9\013\ufff9\014\ufff9\016\ufff9\017\ufff9" +
    "\021\ufff9\001\002\000\004\021\052\001\002\000\020\006" +
    "\ufffb\010\ufffb\013\ufffb\014\ufffb\016\ufffb\017\ufffb\021\ufffb" +
    "\001\002\000\014\010\ufff0\012\ufff0\013\ufff0\014\ufff0\021" +
    "\ufff0\001\002\000\014\006\051\010\015\013\011\014\024" +
    "\021\012\001\002\000\014\010\uffef\012\uffef\013\uffef\014" +
    "\uffef\021\uffef\001\002\000\014\020\033\021\027\022\026" +
    "\024\032\025\030\001\002\000\006\007\ufff8\015\ufff8\001" +
    "\002\000\006\007\ufff4\015\ufff4\001\002\000\006\007\ufff7" +
    "\015\ufff7\001\002\000\014\020\033\021\027\022\026\024" +
    "\032\025\030\001\002\000\004\015\035\001\002\000\006" +
    "\007\ufff3\015\ufff3\001\002\000\014\020\033\021\027\022" +
    "\026\024\032\025\030\001\002\000\006\007\ufff6\015\ufff6" +
    "\001\002\000\016\010\015\013\011\014\024\016\040\017" +
    "\037\021\012\001\002\000\016\010\015\013\011\014\024" +
    "\016\045\017\044\021\012\001\002\000\020\006\uffec\010" +
    "\uffec\013\uffec\014\uffec\016\uffec\017\uffec\021\uffec\001\002" +
    "\000\012\010\015\013\011\014\024\021\012\001\002\000" +
    "\014\010\015\013\011\014\024\017\043\021\012\001\002" +
    "\000\020\006\ufffe\010\ufffe\013\ufffe\014\ufffe\016\ufffe\017" +
    "\ufffe\021\ufffe\001\002\000\020\006\uffea\010\uffea\013\uffea" +
    "\014\uffea\016\uffea\017\uffea\021\uffea\001\002\000\020\006" +
    "\uffeb\010\uffeb\013\uffeb\014\uffeb\016\uffeb\017\uffeb\021\uffeb" +
    "\001\002\000\012\010\015\013\011\014\024\021\012\001" +
    "\002\000\014\010\015\013\011\014\024\017\047\021\012" +
    "\001\002\000\020\006\uffe9\010\uffe9\013\uffe9\014\uffe9\016" +
    "\uffe9\017\uffe9\021\uffe9\001\002\000\006\007\ufff5\015\ufff5" +
    "\001\002\000\004\002\000\001\002\000\004\007\053\001" +
    "\002\000\014\010\uffee\012\uffee\013\uffee\014\uffee\021\uffee" +
    "\001\002\000\004\007\055\001\002\000\020\006\uffed\010" +
    "\uffed\013\uffed\014\uffed\016\uffed\017\uffed\021\uffed\001\002" +
    "\000\014\020\033\021\027\022\026\024\032\025\030\001" +
    "\002\000\004\007\060\001\002\000\020\006\ufff2\010\ufff2" +
    "\013\ufff2\014\ufff2\016\ufff2\017\ufff2\021\ufff2\001\002\000" +
    "\004\007\062\001\002\000\020\006\ufffc\010\ufffc\013\ufffc" +
    "\014\ufffc\016\ufffc\017\ufffc\021\ufffc\001\002\000\014\010" +
    "\ufff1\012\ufff1\013\ufff1\014\ufff1\021\ufff1\001\002\000\014" +
    "\006\065\010\015\013\011\014\024\021\012\001\002\000" +
    "\004\002\uffff\001\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\004\002\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\022\005\021\006\007" +
    "\007\020\010\022\011\013\012\012\013\017\014\015\001" +
    "\001\000\020\005\063\007\062\010\022\011\013\012\012" +
    "\013\017\014\015\001\001\000\006\003\060\004\024\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\012\011\013\012\041" +
    "\013\017\014\015\001\001\000\002\001\001\000\006\003" +
    "\030\004\024\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\003\047\004\024\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\003\033\004\024" +
    "\001\001\000\002\001\001\000\014\005\035\011\013\012" +
    "\012\013\017\014\015\001\001\000\012\011\013\012\041" +
    "\013\017\014\015\001\001\000\002\001\001\000\014\005" +
    "\040\011\013\012\012\013\017\014\015\001\001\000\012" +
    "\011\013\012\041\013\017\014\015\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\014\005\045" +
    "\011\013\012\012\013\017\014\015\001\001\000\012\011" +
    "\013\012\041\013\017\014\015\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\006" +
    "\003\056\004\024\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\012\011\013\012\041\013\017\014\015\001\001\000" +
    "\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalyseurSyntaxique$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalyseurSyntaxique$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$AnalyseurSyntaxique$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




    public void report_error(String message, Object info) {

        HashMap<Integer, String> lesTerminaux = new HashMap<>() ;

        lesTerminaux.put(new Integer(CodesLexicaux.DEBUT), "debut") ;
        lesTerminaux.put(new Integer(CodesLexicaux.FIN), "fin") ;
        lesTerminaux.put(new Integer(CodesLexicaux.POINTVIRGULE), ";") ;

        StringBuffer m = new StringBuffer() ;

        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);

            if (s.left >= 0) {
                m.append("\tligne : " + (s.left + 1)) ;
                if (s.right >= 0)
                    m.append(" colonne : " + (s.right+1)) ;
            }

            if (s.value != null) {
                lesTerminaux.put(CodesLexicaux.CSTENTIERE, "" + s.value) ;
            }

            if (lesTerminaux.containsKey(new Integer(s.sym))) {
                m.append(" dernier token lu : " + lesTerminaux.get(new Integer(s.sym))) ;
            }
            else {
                m.append(" expression non terminée") ;
            }

        }
        throw new AnalyseSyntaxiqueException("" + m) ;
    }

    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
    }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$AnalyseurSyntaxique$actions {


             
  private final AnalyseurSyntaxique parser;

  /** Constructor */
  CUP$AnalyseurSyntaxique$actions(AnalyseurSyntaxique parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$do_action(
    int                        CUP$AnalyseurSyntaxique$act_num,
    java_cup.runtime.lr_parser CUP$AnalyseurSyntaxique$parser,
    java.util.Stack            CUP$AnalyseurSyntaxique$stack,
    int                        CUP$AnalyseurSyntaxique$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$result;

      /* select the action based on the action number */
      switch (CUP$AnalyseurSyntaxique$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // CONDITION ::= SI EXP ALORS LINST SINON LINST FINSI 
            {
              Instruction RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-5)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-5)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-5)).value;
		int li1left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).left;
		int li1right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).right;
		BlocDInstructions li1 = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).value;
		int li2left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int li2right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		BlocDInstructions li2 = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		
                            Instruction instruction= new Condition(e,eleft+1);
                            ((Condition)instruction).addConditionAlors(li1);
                            ((Condition)instruction).addConditionSinon(li2);
                            RESULT = instruction;
                        
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("CONDITION",10, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-6)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // CONDITION ::= SI EXP ALORS SINON LINST FINSI 
            {
              Instruction RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)).value;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		BlocDInstructions li = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		
                            Instruction instruction = new Condition(e,eleft+1);
                            ((Condition)instruction).addConditionSinon(li);
                            RESULT = instruction;
                        
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("CONDITION",10, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-5)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // CONDITION ::= SI EXP ALORS LINST FINSI 
            {
              Instruction RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).value;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		BlocDInstructions li = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		
                            Instruction instruction = new Condition(e,eleft+1);
                            ((Condition)instruction).addConditionAlors(li);
                            RESULT = instruction;
                        
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("CONDITION",10, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // CONDITION ::= SI EXP ALORS FINSI 
            {
              Instruction RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).value;
		
                              RESULT = new Condition(e,eleft+1);
                          
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("CONDITION",10, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // LECTURE ::= LIRE IDF POINTVIRGULE 
            {
              Instruction RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		  Identificateur id = new Identificateur(idf, idfleft + 1);
                            Instruction l = new Lire(id, idfleft + 1);
                            RESULT = l;
                        
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LECTURE",9, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // DECL_VAR ::= ENTIER IDF POINTVIRGULE 
            {
              Identificateur RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 	Entree e = new Entree(idf);
							Type type = Type.ENTIER;
							Symbole s = new Symbole(type);
							TDS.getInstance().ajouter(e,s,idfleft+1);
							Identificateur id = new Identificateur(idf,idfleft);
							RESULT = id;
						
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("DECL_VAR",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // DECL ::= DECL_VAR 
            {
              Identificateur RESULT =null;
		int dvleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int dvright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Identificateur dv = (Identificateur)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = dv; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("DECL",5, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // LDECL ::= DECL 
            {
              BlocDInstructions RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Identificateur d = (Identificateur)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
			BlocDInstructions bloc = new BlocDInstructions(dleft+1);
							bloc.ajouter(d);
							RESULT = bloc;
						
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LDECL",4, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // LDECL ::= LDECL DECL 
            {
              BlocDInstructions RESULT =null;
		int ldleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int ldright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		BlocDInstructions ld = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		int dleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Identificateur d = (Identificateur)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                        ld.ajouter(d);
                        RESULT = ld;
					
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LDECL",4, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // AFFECT ::= IDF EGAL EXP POINTVIRGULE 
            {
              Instruction RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		
					    Identificateur i = new Identificateur(idf, idfleft+1);
					    Instruction aff = new AffectationVariable(i, e, idfleft+1);
					    RESULT = aff;
					
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("AFFECT",7, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // CST ::= CSTBOOLEAN 
            {
              Expression RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		String b = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    RESULT = new ConstanteBoolean(b, bleft+1) ;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("CST",2, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // CST ::= CSTENTIERE 
            {
              Expression RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		String e = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    RESULT = new ConstanteEntiere(e, eleft+1) ;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("CST",2, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // EXP ::= MOINS EXP 
            {
              Expression RESULT =null;
		int meleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int meright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression me = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    RESULT = new MoinsUnaire(me);
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",1, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // EXP ::= NON EXP 
            {
              Expression RESULT =null;
		int neleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int neright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression ne = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    RESULT = new NonLogique(ne);
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",1, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // EXP ::= IDF 
            {
              Expression RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    RESULT = new Identificateur(idf, idfleft+1);
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",1, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // EXP ::= CST 
            {
              Expression RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression c = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    RESULT = c ;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",1, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // INST ::= CONDITION 
            {
              Instruction RESULT =null;
		int coleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int coright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Instruction co = (Instruction)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
        		    RESULT = co;
        		
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",8, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // INST ::= AFFECT 
            {
              Instruction RESULT =null;
		int affleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int affright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Instruction aff = (Instruction)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    RESULT = aff;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",8, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // INST ::= LECTURE 
            {
              Instruction RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Instruction l = (Instruction)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    RESULT = l ;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",8, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // INST ::= ECRIRE EXP POINTVIRGULE 
            {
              Instruction RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		
                    RESULT = new Ecrire(e, eleft + 1) ;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",8, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // LINST ::= INST 
            {
              BlocDInstructions RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Instruction i = (Instruction)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    BlocDInstructions b = new BlocDInstructions(ileft + 1) ;
                    b.ajouter(i) ;
                    RESULT = b ;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LINST",3, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // LINST ::= LINST INST 
            {
              BlocDInstructions RESULT =null;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		BlocDInstructions li = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Instruction i = (Instruction)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		
                    ((BlocDInstructions)li).ajouter(i) ;
                     RESULT = li ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LINST",3, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // PROG ::= PROGRAMME IDF DEBUT LDECL LINST FIN 
            {
              ArbreAbstrait RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)).value;
		int ldleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).left;
		int ldright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).right;
		BlocDInstructions ld = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).value;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		BlocDInstructions li = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		
                    Programme p = new Programme(idf,idfleft);
                    p.ajouterBloc(ld);
                    p.ajouterBloc(li);
                    RESULT = p;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG",0, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-5)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // PROG ::= PROGRAMME IDF DEBUT LINST FIN 
            {
              ArbreAbstrait RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).value;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		BlocDInstructions li = (BlocDInstructions)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		
                    Programme p = new Programme(idf,idfleft);
                    p.ajouterBloc(li);
                    RESULT = p;
                
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG",0, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= PROG EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait start_val = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		RESULT = start_val;
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$AnalyseurSyntaxique$parser.done_parsing();
          return CUP$AnalyseurSyntaxique$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

