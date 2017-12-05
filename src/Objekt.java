public class Objekt {
    public static final int VAR = 0, CONST = 1, PRO = 2, CLASS = 3, BLOCK = 4, ERROR = 5;
    String name; //Bezeichnername
    int objectClass;  // Objektart: VAR, CONST, PRO, CLASS, BLOCK, etc.
    Type type;	//Typbeschreibung des Objektes

    //Konstantenbeschreibung
    long intVal; //boolsche oder ganzzahlige Konstante

    //Prozedurbeschreibungen

    Objekt parameterliste; //Parameterliste
    Type resulttype; 	 //Resultatstyp
    Objekt scope;		// Definitionen in der Prozedur

//Klassenbeschreibungen

    Objekt vardef; 	//Variablendefinitionen
    Objekt methoddef; 	//Methodendefinitionen in der Klasse
    Symboltabelle symtable;
    Objekt next;


    public Objekt(){
     this.name = "Fehlerobjekt";
     this.objectClass = ERROR;
     this.type = new Type();
    }
}
