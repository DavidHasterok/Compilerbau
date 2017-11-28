
public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Parser parser = new Parser(new Scanner(new Input(args[0])));
		parser.klasse();
		if (!parser.s.EOT) {
		    parser.error("Keine korrekte Schachtelung! " + parser.s.id);
        }
	}
}

