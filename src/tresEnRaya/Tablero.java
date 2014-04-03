package tresEnRaya;

import util.Coordenada;
import util.Intervalo;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIO;

public class Tablero {
	
	private final char fichas[][] = new char[TresEnRaya.DIM][TresEnRaya.DIM];
	private int cont;
	public static final int DIM = 3;
	public static final Intervalo RANGO = new Intervalo(0, TresEnRaya.DIM-1);
	private static final char VACIO = '-';

	
	public Tablero() {
		for (int i = 0; i < TresEnRaya.DIM; i++)
			for (int j = 0; j < TresEnRaya.DIM; j++)
				fichas[i][j] = Tablero.VACIO;
	}

	public void mostrar() {
		GestorIO gestorIO = FactoriaGestorIO.getInstance();
		for (int i = 0; i < TresEnRaya.DIM; i++) {
			for (int j = 0; j < TresEnRaya.DIM; j++) {
				gestorIO.out(fichas[i][j]);
				gestorIO.out(' ');
			}
			gestorIO.out('\n');
		}
		gestorIO.out('\n');
	}



	public boolean hayTER(char color) {
		int diagonal = 0;
		int inversa = 0;
		int filas[] = new int[TresEnRaya.DIM];
		int columnas[] = new int[TresEnRaya.DIM];

		for (int j = 0; j < TresEnRaya.DIM; j++){
			columnas[j] = 0;
		}
		for (int i = 0; i < TresEnRaya.DIM; i++) {
			filas[i] = 0;
			for (int j = 0; j < TresEnRaya.DIM; j++)
				if (fichas[i][j] == color) {
					if (i == j)
						diagonal++;
					if (i + j == 2)
						inversa++;
					filas[i]++;
					columnas[j]++;
				}
		}
		if ((diagonal == TresEnRaya.DIM) || (inversa == TresEnRaya.DIM))
			return true;
		else
			for (int i = 0; i < TresEnRaya.DIM; i++)
				if ((columnas[i] == TresEnRaya.DIM) || (filas[i] == TresEnRaya.DIM))
					return true;
		return false;
	}

	public boolean ocupado(Coordenada coordenada) {
		assert coordenada!=null;
		assert coordenada.valida();
		return fichas[coordenada.getFila()][coordenada.getColumna()] != Tablero.VACIO;
	}
	
	public boolean ocupado(Coordenada coordenada, char color) {
		assert coordenada!=null;
		assert coordenada.valida();
		assert color=='x' || color=='o';
		return fichas[coordenada.getFila()][coordenada.getColumna()] == color;
	}

	public void poner(Coordenada coordenada, char color) {
		assert coordenada!=null;
		assert coordenada.valida();
		assert color=='x' || color=='o';
		assert this.vacio(coordenada);
		cont++;
		fichas[coordenada.getFila()][coordenada.getColumna()] = color;
		assert this.ocupado(coordenada, color);
	}

	public boolean vacio(Coordenada coordenada) {
		assert coordenada!=null;
		assert coordenada.valida();
		return (!this.ocupado(coordenada));
	}

	public void sacar(Coordenada coordenada) {
		assert coordenada!=null;
		assert coordenada.valida();
		assert this.ocupado(coordenada);
		cont--;
		fichas[coordenada.getFila()][coordenada.getColumna()] = Tablero.VACIO;
		assert this.vacio(coordenada);
	}

	public boolean lleno() {
		return cont==TresEnRaya.DIM*2;
	}

}
