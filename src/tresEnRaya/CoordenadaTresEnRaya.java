package tresEnRaya;

import util.Coordenada;

public class CoordenadaTresEnRaya extends Coordenada {
	
	public CoordenadaTresEnRaya(){			
		super();		
	}
	
	public CoordenadaTresEnRaya(int fila, int columna){			
		super(fila,columna);
		assert this.valida();
	}
	
	public boolean valida() {
		return Tablero.getRango().incluye(this.getFila()) &&
				Tablero.getRango().incluye(this.getColumna());
	}
}
