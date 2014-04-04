package util;

import tresEnRaya.Tablero;
import tresEnRaya.TresEnRaya;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIO;

public class Coordenada {
	
	private int fila;
	private int columna;

	public Coordenada(){
	}
	
	public Coordenada(int fila, int columna){
		this.fila = fila;
		this.columna = columna;
		//assert this.valida();
	}
	
	public boolean iguales(Coordenada coordenada){
		assert coordenada!=null;
		//assert coordenada.valida();
		//assert this.valida();
		return this.fila == coordenada.fila &&
				this.columna == coordenada.columna;
	}
	
	public void recoger(String titulo) {
		GestorIO gestorIO = FactoriaGestorIO.getInstance();
		gestorIO.out(titulo + "\n");
		gestorIO.out("Dame fila: ");
		fila = gestorIO.inInt()-1;
		gestorIO.out("Dame columna: ");
		columna = gestorIO.inInt()-1;
	}

	public boolean valida() {
		return Tablero.getRango().incluye(fila) &&
				Tablero.getRango().incluye(columna);
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

}
