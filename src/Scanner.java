
public class Scanner {
private Input in;
private char c;
public boolean EOT = false;

public static final int ident = 0, number = 1, lparen = 3, rparen = 4, 
						lcparen = 5, rcparen = 6, plus = 7, minus = 8, 
						times = 9, div = 10, lt = 11, leq = 12, gt = 13,
						geq = 14, comp = 15, comma = 16, semicolon = 17, 
						equals = 18, other = 19, comment = 20, CLASS = 21,
						PUBLIC = 22, FINAL = 23, VOID = 24, INT = 25, IF = 26,
                        ELSE = 27, WHILE = 28, RETURN = 29, EOF = 30;

public static final String[] keywords = new String[9];{
	keywords[0] = "class"; //21
	keywords[1] = "public"; //22
	keywords[2] = "final"; //23
	keywords[3] = "void"; //24
	keywords[4] = "int"; //25
	keywords[5] = "if";  //26
	keywords[6] = "else"; //27
	keywords[7] = "while"; //28
	keywords[8] = "return"; //29
}

public int sym;
public String id;
public String num;

Scanner(Input in) {
	this.in = in;
}

public void getSym() {
	while(c <= ' ') {
		c = in.next();
	}
    id = "";
    num = "";
    
	switch (c) {
	case '0':
	case '1':
	case '2':
	case '3':
	case '4':
	case '5':
	case '6':
	case '7':
	case '8':
	case '9': sym = number; 
			  num = "";
			  do {
				  num += c;
				  c = in.next();
			  } while (c >= '0' && c <= '9'); break;
	case 'a':
	case 'b':	
	case 'c':	
	case 'd':	
	case 'e':	
	case 'f':	
	case 'g':	
	case 'h':	
	case 'i':	
	case 'j':	
	case 'k':	
	case 'l':	
	case 'm':	
	case 'n':	
	case 'o':	
	case 'p':	
	case 'q':	
	case 'r':	
	case 's':	
	case 't':	
	case 'u':	
	case 'v':	
	case 'w':	
	case 'x':	
	case 'y':	
	case 'z':	
	case 'A':
	case 'B':	
	case 'C':	
	case 'D':	
	case 'E':	
	case 'F':	
	case 'G':	
	case 'H':	
	case 'I':	
	case 'J':	
	case 'K':	
	case 'L':	
	case 'M':	
	case 'N':	
	case 'O':	
	case 'P':	
	case 'Q':	
	case 'R':	
	case 'S':	
	case 'T':	
	case 'U':	
	case 'V':	
	case 'W':	
	case 'X':	
	case 'Y':	
	case 'Z': sym = ident;
			  id = "";
			  do {
				  id += c;
				  c = in.next();
			  }while (c >= '0' && c <= '9' || 
					  c >= 'A' && c <= 'Z' || 
					  c >= 'a' && c <= 'z');break;
	case '(': sym = lparen;
			  id = "(";
			  c = in.next(); break;
	case ')': sym = rparen;
			  id = "(";
			  c = in.next(); break;
	case '{': sym = lcparen;
			  id = "{";
			  c = in.next(); break;
	case '}': sym = rcparen;
			  id = "}";
			  c = in.next(); break;
	case '+': sym = plus;
			  id = "+";
			  c = in.next(); break;
	case '-': sym = minus;
			  id = "-";
			  c = in.next(); break;
	case '*': sym = times;
			  id = "*";
			  c = in.next(); break;
	case '/': sym = div;
			  id = "/";
			  c = in.next(); 
			  if (c == '*') {
				  do {
					  c = in.next();
					  if ( c == '*') {
						  c = in.next();
						  if (c == '/') {
							  sym = comment;
							  id = "/* ... */";
							  c = in.next();
							  break;
						  }
					  }
				  } while (c != (char) -1);	
				  if ( c == (char) -1) {
					  sym = other;
					  id = "Kommentar nicht geschlossen";
				  }
			  }break;
	case '<': sym = lt;
			  id = "<";
			  c = in.next();
			  if (c == '=') {
				  sym = leq;
				  id = "<=";
				  c = in.next();
			  }break;
	case '>': sym = gt;
			  id = ">";
			  c = in.next();
			  if (c == '=') {
				  sym = geq;
				  id = ">=";
				  c = in.next();
			  }break;
	case '=': sym = equals;
			  id = "=";
			  c = in.next();
			  if (c == '=') {
				  sym = comp;
				  id = "==";
				  c = in.next();
			  }break;
	case ',': sym = comma;
			  id = ",";
			  c = in.next();break;
	case ';': sym = semicolon;
			  id = ";";
			  c = in.next();break;
	case (char) -1 : sym = EOF;
	                 EOT = true;
			         id = "EOF"; break;
	default: sym = other;
			 id = "Syntaxfehler: ";
			 id += c;
		     c = in.next(); break;		  
	}
	
	if (sym == ident){
		for (int i = 0; i < keywords.length; i++) {
			if (id.equals(keywords[i])) {
				sym = 21 + i;
				break;
			}
		}
	}

	if (sym == comment){
	    getSym();
    }
	
}



}