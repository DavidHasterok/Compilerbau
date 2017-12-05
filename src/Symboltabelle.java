public class Symboltabelle {
    Objekt head; //Liste eingetragener Objekte in ST
    Symboltabelle enclose; //Symbol des übergeordneten Blocks

    public void insert(Objekt obj) {
		boolean dupe = false;  //flag für doppeleintrag
        Objekt tmp = head;  //pointer
		if (head == null){ //wenn tabelle leer, füge ein als kopf
		    head = obj;
        }
        else {  //tabelle hat einträge
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
}
