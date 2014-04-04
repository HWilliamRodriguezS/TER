package gestorIO;

import java.util.ArrayList;

public class GestorIOStub extends GestorIO {

	private ArrayList<String> in = new ArrayList<String>();
	private ArrayList<String> out = new ArrayList<String>();

	GestorIOStub(){
		
	}
	
	public void set(String string){
		in.add(string);
	}
	
	public String inString() {
		String entrada = null;
		try {
			entrada = in.remove(0);
		} catch (Exception e) {
			this.salir();
		}
		return entrada;
	}

	public void out(String salida) {
		out.add(salida);
	}
	
	public boolean empty(){
		return out.size() == 0;
	}
	public String get(){
		return out.remove(0);
	}
	
	
	
}
