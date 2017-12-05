public class Objekt {
    public static final int VAR = 0, CONST = 1, PRO = 2, CLASS = 3, BLOCK = 4, ERROR = 5;
    String name;        //Bezeichnername
    int objectClass;    // Objektart: VAR, CONST, PRO, CLASS, BLOCK, etc.
    Type type;	        //Typbeschreibung des Objektes

   //Konstantenbeschreibung

    long intVal;        //boolsche oder ganzzahlige Konstante

    //Prozedurbeschreibungen

    Objekt parameterliste;  //Parameterliste
    Type resulttype; 	    //Resultatstyp
    Objekt scope;		    // Definitionen in der Prozedur

//Klassenbeschreibungen

    Objekt vardef; 	        //Variablendefinitionen
    Objekt methoddef; 	    //Methodendefinitionen in der Klasse
    Symboltabelle symtable; //zur Klasse gehörende Symboltabelle



    Objekt next;            //nächstes Objekt


    public Objekt(){
     this.name = "Fehlerobjekt";
     this.objectClass = ERROR;
     this.type = new Type();
    }

    public Objekt(String name, int klass,Symboltabelle symtable){//Klassenobjekt
        this.name = name;
        this.objectClass = klass;
//        this.vardef = vardef;
  //      this.methoddef = methoddef;
        this.symtable = symtable;
    }

    public Objekt(String name, int klass, Type type){  //Variable
        this.name = name;
        this.objectClass = klass;
        this.type = type;
    }

    public Objekt(String name, int klass, Type type, long val){ // Konstante
        this.name = name;
        this.objectClass = klass;
        this.type = type;
        this.intVal = val;
    }

    public Objekt(String name, int klass, Type result, Objekt params, Symboltabelle symtable ){
        this.name = name;
        this.objectClass = klass;
        this.resulttype = result;
        this.parameterliste = params;
        this.symtable = symtable;
    }

    public void printObject(){
        String typ;
        if (type == null){
            if (resulttype == null) {
                typ = "null";
            } else {
                typ = String.valueOf(resulttype.kind);
            }
        } else {
            typ = String.valueOf(type.kind);
        }
        System.out.println("name: " + name + ", Type: " + typ + ", Class: " + objectClass);
        if (symtable != null){
            symtable.printTable();
        }
    }

}
