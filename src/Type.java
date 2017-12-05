public class Type {
    public static final int INT = 25, VOID = 24, ERROR = -1;
    int kind; //byte, short, integer, long, float, double, Referenztyp, Arraytyp

    public Type(){
        this.kind = ERROR;
    }

    public Type(int i) {
        if (i == INT || i == VOID){
            this.kind = i;
        } else {
            this.kind = ERROR;
        }
    }
}