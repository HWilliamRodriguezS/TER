package tresEnRaya;

import java.util.ArrayList;
import java.util.List;
import util.Direccion;
import util.Intervalo;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIO;

public class Tablero {
	private final char fichas[][] = new char[getDim()][getDim()];
	private int cont;
	private static final char VACIO = '-';
	private static int dim = 3;
	private static Intervalo rango = new Intervalo(0, getDim() - 1);
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	public static char colores[] = { 'x', 'o' };
	private CoordenadaTresEnRaya coord[][] = new CoordenadaTresEnRaya[3][2];
	private CoordenadaTresEnRaya origen;

	public Tablero() {
		for (int i = 0; i < getDim(); i++)
			for (int j = 0; j < getDim(); j++)
				fichas[i][j] = Tablero.VACIO;
	}

	public void mostrar() {
		GestorIO gestorIO = FactoriaGestorIO.getInstance();
		for (int i = 0; i < getDim(); i++) {
			for (int j = 0; j < getDim(); j++) {
				gestorIO.out(fichas[i][j]);
				gestorIO.out(' ');
			}
			gestorIO.out('\n');
		}
		gestorIO.out('\n');
	}

	public boolean hayTER(char color) {
		//Probe el assert en direccion pero no estaba funcionando
		if ((coord[0][getColor(color)] != null)
				&& (coord[1][getColor(color)] != null)
				&& (coord[2][getColor(color)] != null)) {
			if (coord[0][getColor(color)].direccion(coord[1][getColor(color)]) != Direccion.SIN_DIRECCION
					&& coord[0][getColor(color)]
							.direccion(coord[1][getColor(color)]) == coord[1][getColor(color)]
							.direccion(coord[2][getColor(color)])) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean ocupado(CoordenadaTresEnRaya coordenada) {
		assert coordenada != null;
		assert coordenada.valida();
		return getFicha(coordenada) != Tablero.VACIO;
	}

	public boolean ocupado(CoordenadaTresEnRaya coordenada, char color) {
		assert coordenada != null;
		assert coordenada.valida();
		assert color == colores[0] || color == colores[1];
		return getFicha(coordenada) == color;
	}

	public void poner(CoordenadaTresEnRaya coordenada, char color) {
		assert coordenada != null;
		assert coordenada.valida();
		assert color == colores[0] || color == colores[1];
		assert this.vacio(coordenada);
		cont++;
		setFicha(coordenada, color);
		setCoord(coordenada, color);
		assert this.ocupado(coordenada, color);
	}

	public boolean vacio(CoordenadaTresEnRaya coordenada) {
		assert coordenada != null;
		assert coordenada.valida();
		return (!this.ocupado(coordenada));
	}

	public void sacar(CoordenadaTresEnRaya coordenada) {
		assert coordenada != null;
		assert coordenada.valida();
		assert this.ocupado(coordenada);
		cont--;
		origen = coordenada;
		setFicha(coordenada, Tablero.VACIO);
		sacCoord(coordenada);
		assert this.vacio(coordenada);
	}

	public boolean igualOrigen(CoordenadaTresEnRaya coordenada) {
		assert coordenada != null;
		assert coordenada.valida();
		assert !this.ocupado(coordenada);
		return origen.iguales(coordenada);
	}

	public boolean lleno() {
		return cont == getDim() * 2;
	}

	public static int getDim() {
		return dim;
	}

	public static Intervalo getRango() {
		return rango;
	}

	public Jugador getJugador(int index) {
		return jugadores.get(index);
	}

	public void setJugador(Jugador jugador) {
		if (this.jugadores.size() < 2)
			this.jugadores.add(jugador);
	}

	private char getFicha(CoordenadaTresEnRaya coordenada) {
		return fichas[coordenada.getFila()][coordenada.getColumna()];
	}

	private void setFicha(CoordenadaTresEnRaya coordenada, char color) {
		fichas[coordenada.getFila()][coordenada.getColumna()] = color;
	}

	private void setCoord(CoordenadaTresEnRaya coordenada, char color) {

		for (int i = 0; i < 3; i++) {
			if (coord[i][getColor(color)] == null) {
				coord[i][getColor(color)] = coordenada;
				break;
			}
		}
	}

	private void sacCoord(CoordenadaTresEnRaya coordenada) {

		for (int i = 0; i < 3; i++) {
			if (coord[i][getColor(getFicha(coordenada))].iguales(coordenada)) {
				coord[i][getColor(getFicha(coordenada))] = null;
				break;
			}
		}
	}

	private int getColor(char color) {
		if (colores[0] == color)
			return 0;
		else
			return 1;
	}
}
