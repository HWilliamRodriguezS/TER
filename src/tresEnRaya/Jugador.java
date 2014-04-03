package tresEnRaya;

import util.Coordenada;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIO;

public class Jugador {
	private char color;
	
	public char getColor() {
		return color;
	}

	public Jugador(char color) {
		assert color=='x' || color=='o';
		this.color = color;
	}

	public void poner(Tablero tablero) {
		assert tablero!=null;
		FactoriaGestorIO.getInstance().out("juega: " + color);
		Coordenada destino = new Coordenada();
		do {
			destino.recoger("Coordenada destino de puesta");
		} while (!destino.valida() || tablero.ocupado(destino));
		assert tablero.vacio(destino);
		tablero.poner(destino, color);
		assert tablero.ocupado(destino, color);
	}

	public void mover(Tablero tablero) {
		assert tablero!=null;
		Coordenada origen = new Coordenada();
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
