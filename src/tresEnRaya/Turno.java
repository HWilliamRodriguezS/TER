package tresEnRaya;

public class Turno {
	
	private int valor = 0;
	
	public void aleatorio(){
		valor = new java.util.Random().nextInt(2);
	}

	public void cambiar() {
		this.valor = (this.valor + 1) % 2;
	}

	public int toca() {
		return valor;
	}

	public int noToca() {
		return (valor + 1) % 2;
	}

}
