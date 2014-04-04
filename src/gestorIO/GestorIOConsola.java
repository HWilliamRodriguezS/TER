package gestorIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class GestorIOConsola extends GestorIO{

	private static BufferedReader b = new BufferedReader(
			new InputStreamReader(System.in));

	GestorIOConsola(){
		
	}
	
	public String inString() {
		String entrada = null;
		try {
			entrada = b.readLine();
		} catch (Exception e) {
			this.salir();
		}
		return entrada;
	}

	public void out(String salida) {
		System.out.print(salida);
	}

}