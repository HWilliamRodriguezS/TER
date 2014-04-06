package tresEnRaya;

import gestorIO.FactoriaGestorIO;

public class Jugador {
	private char color;
	public static final Turno turno = new Turno();

	public char getColor() {
		return color;
	}

	public Jugador(char color) {
		//assert color=='x' || color=='o';
		this.color = color;
	}

	public Turno getTurno() {
		return turno;
	}

	public void poner(Tablero tablero) {
		assert tablero!=null;
		FactoriaGestorIO.getInstance().out("juega: " + color);
		CoordenadaTresEnRaya destino = new CoordenadaTresEnRaya();

		do {
			String msg  = "Coordenada destino de puesta";
			if(!destino.valida())msg  = "Coordenadas erroneas o fuera de rango";
			destino.recoger(msg);
		} while (!destino.valida() || tablero.ocupado(destino));
		assert tablero.vacio(destino);
		assert !tablero.igualOrigen(destino);
		tablero.poner(destino, color);
		assert tablero.ocupado(destino, color);
	}

	public void mover(Tablero tablero) {
		assert tablero!=null;
		CoordenadaTresEnRaya origen = new CoordenadaTresEnRaya();
		do {
			origen.recoger("Coordenada origen de movimiento");
		} while (!origen.valida() || !tablero.ocupado(origen, color));
		assert tablero.ocupado(origen, color);
		tablero.sacar(origen);
		assert tablero.vacio(origen);
		this.poner(tablero);
	}

	public void victoria() {
		FactoriaGestorIO.getInstance().out("las " + color + " han ganda....");
	}



}

