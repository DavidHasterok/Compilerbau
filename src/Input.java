import java.io.*;


public class Input {

InputStream inputstream;	
Reader reader;
public int line = 1;


public Input(String filename) {

try {
	this.inputstream = new FileInputStream(filename);
	this.reader = new InputStreamReader(inputstream);
	} catch (IOException e) {
		System.out.println("File not found.");
	}
}

public char next() {
	char c;
	try {
		int i = this.reader.read();
		if (i != -1) {
			c = (char) i;
		} else {
			this.inputstream.close();
			c = (char) -1;
		}
	} catch (IOException e) {
		c = (char) -1;
	}
    if (c == '\n'){
	    line++;
    }
	return c;
}

}
