public class Type {
    public static final int INT = 0, BYTE = 1, SHORT = 2, LONG = 3, FLOAT = 4, DOUBLE = 5, REF = 6, ERROR = 7;
    int kind; //byte, short, integer, long, float, double, Referenztyp, Arraytyp
//Referenztyp
    Objekt klass; //Klassenbeschreibung

    public Type(){
        this.kind = ERROR;
    }
}