public class Symboltabelle {
    Objekt head; //Liste eingetragener Objekte in ST
    Symboltabelle enclose; //Symbol des übergeordneten Blocks
    String level;

    public Symboltabelle(Symboltabelle symtab){
        this.enclose = symtab;
    }

    public Symboltabelle(){

    }

    public void insert(Objekt obj) {
        Objekt tmp = new Objekt();
		boolean dupe = false;  //flag für doppeleintrag
		if (head == null){ //wenn tabelle leer, füge ein als kopf
		    head = obj;
        }
        else {  //tabelle hat einträge
            tmp = head;  //pointer
		    if (tmp.name.equals(obj.name) && obj.objectClass == tmp.objectClass && obj.type.kind == tmp.type.kind ){
		        dupe = true; //Objekt mit dem namen und typ existiert schon
		        System.out.println("Doppeleintrag: " + tmp.name + ", " + tmp.objectClass + ", " + tmp.type);
            }
            else {
		        while (tmp.next != null){ //gehe durch Tabelle bis zum Ende
                    tmp = tmp.next;
                    if (tmp.name.equals(obj.name) && obj.objectClass == tmp.objectClass && obj.type.kind == tmp.type.kind){
                        dupe = true; //Objekt existiert schon
                        System.out.println("Doppeleintrag: " + tmp.name + ", " + tmp.objectClass + ", " + tmp.type);
                    }
                }
            }
        }
        if (!dupe){ //wenn kein Doppeleintrag, füge ans ende an
		    tmp.next = obj;
        }
    }

    public Objekt getObjekt(String name, int objektClass, Type type){
		Objekt tmp = head;
		while (tmp != null){
		    if (name.equals(tmp.name) && objektClass == tmp.objectClass && type.kind == tmp.type.kind){
		        return tmp;
            }
        }
        return new Objekt();
        // Aufsuchen eines Elements, Kein Eintrag vorhanden erzeugt Fehler
    }

    public void printTable(){
        System.out.println(level);
        Objekt tmp = this.head;
        if (this.head == null) {
            System.out.println("-");
        }
        while (tmp != null){
            tmp.printObject();
            tmp = tmp.next;
        }
    }
}
