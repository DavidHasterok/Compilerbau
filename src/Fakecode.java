class Fakecode {
    int a;
	int b;
	int c;

	public void main(int x, int y, int z){

		a = 0;

		if (x > y) {
			a = x - y + z;
			c = a * a;
		} else {
			a = y - x + z;
			/*if (a >= x) {
			    b = a * a;
            } else {
			    c = a * a;
            }*/

		}

		while (a == 0){
			a = a + 1;
		}

	}
}
