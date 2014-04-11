package tresEnRaya;

import util.Coordenada;
import util.Direccion;

public class CoordenadaTresEnRaya extends Coordenada {

	public CoordenadaTresEnRaya() {
		super();
	}

	public CoordenadaTresEnRaya(int fila, int columna) {
		super(fila, columna);
		assert this.valida();
	}

	public boolean valida() {
		return Tablero.getRango().incluye(this.getFila())
				&& Tablero.getRango().incluye(this.getColumna());
	}

	public Direccion direccion(CoordenadaTresEnRaya coordenada) {

		Direccion direccion = Direccion.SIN_DIRECCION;

		if (this.getFila() == coordenada.getFila()) {
			direccion = Direccion.HRIZONTAL;
		} else if (this.getColumna() == coordenada.getColumna()) {
			direccion = Direccion.VERTICAL;
		} else if (digPrincipal(this) && digPrincipal(coordenada)) {
			direccion = Direccion.DIAGONAL_PRINCIPAL;
		} else if (digSecundaria(this) && digSecundaria(coordenada)) {
			direccion = Direccion.DIAGONAL_SECUNDARIA;
		}

		return direccion;
	}

	private boolean digPrincipal(CoordenadaTresEnRaya coordenada) {
		return (coordenada.getFila() == coordenada.getColumna());
	}

	private boolean digSecundaria(CoordenadaTresEnRaya coordenada) {
		return (coordenada.getFila() + coordenada.getColumna() == 2);
	}
}
