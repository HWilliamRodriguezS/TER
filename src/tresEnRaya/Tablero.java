package tresEnRaya;

import java.util.ArrayList;
import java.util.List;

import util.Coordenada;
import util.Direccion;
import util.Intervalo;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIO;

public class Tablero {
	private final char fichas[][] = new char[getDim()][getDim()];
	private int cont;
	private static final char VACIO = '-';
	private static int dim = 3;
	private static Intervalo rango = new Intervalo(0, getDim() -1);
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	public static char colores[] = {'x','o'};

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
		int diagonal = 0;
		int inversa = 0;
		int filas[] = new int[getDim()];
		int columnas[] = new int[getDim()];

		for (int j = 0; j < getDim(); j++){
			columnas[j] = 0;
		}
		for (int i = 0; i < getDim(); i++) {
			filas[i] = 0;
			for (int j = 0; j < getDim(); j++)
				if (fichas[i][j] == color) {
					if (i == j)
						diagonal++;
					if (i + j == 2)
						inversa++;
					filas[i]++;
					columnas[j]++;
				}
		}
		if ((diagonal == getDim()) || (inversa == getDim()))
			return true;
		else
			for (int i = 0; i < getDim(); i++)
				if ((columnas[i] == getDim()) || (filas[i] == getDim()))
					return true;
		return false;
	}

	public boolean ocupado(CoordenadaTresEnRaya coordenada) {
		assert coordenada!=null;
		assert coordenada.valida();
		return getFicha(coordenada) != Tablero.VACIO;
	}

	public boolean ocupado(CoordenadaTresEnRaya coordenada, char color) {
		assert coordenada!=null;
		assert coordenada.valida();
		assert color==colores[0] || color==colores[1];
		return getFicha(coordenada) == color;
	}

	public void poner(CoordenadaTresEnRaya coordenada, char color) {
		assert coordenada!=null;
		assert coordenada.valida();
		assert color==colores[0] || color==colores[1];
		assert this.vacio(coordenada);
		cont++;
		setFicha(coordenada,color);
		assert this.ocupado(coordenada, color);
	}

	public boolean vacio(CoordenadaTresEnRaya coordenada) {
		assert coordenada!=null;
		assert coordenada.valida();
		return (!this.ocupado(coordenada));
	}

	public void sacar(CoordenadaTresEnRaya coordenada) {
		assert coordenada!=null;
		assert coordenada.valida();
		assert this.ocupado(coordenada);
		cont--;
		origen = coordenada;
		setFicha(coordenada,Tablero.VACIO);
		assert this.vacio(coordenada);
	}

	public boolean igualOrigen(CoordenadaTresEnRaya coordenada)
	{
		assert coordenada!=null;
		assert coordenada.valida();
		assert !this.ocupado(coordenada);
		return origen.iguales(coordenada);
	}
	
	public boolean lleno() {
		return cont==getDim()*2;
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
		if(this.jugadores.size() < 2)
		this.jugadores.add(jugador);
	}

	private char getFicha(CoordenadaTresEnRaya coordenada) {
		return fichas[coordenada.getFila()][coordenada.getColumna()];
	}
	
	private void setFicha(CoordenadaTresEnRaya coordenada, char color) {
		fichas[coordenada.getFila()][coordenada.getColumna()] = color;
	}

	public Direccion direccion(CoordenadaTresEnRaya coordenada_1, CoordenadaTresEnRaya coordenada_2) {
		
		Direccion direccion = Direccion.SIN_DIRECCION; 
		
		if(coordenada_1.getFila() == coordenada_2.getFila())
		{
			direccion = Direccion.HRIZONTAL;
		}else if (coordenada_1.getColumna() == coordenada_2.getColumna())
		{
			direccion = Direccion.VERTICAL;
		}else if (digPrincipal(coordenada_1) && digPrincipal(coordenada_2))
		{
			direccion = Direccion.DIAGONAL_PRINCIPAL;
		}else if(digSecundaria(coordenada_1) && digSecundaria(coordenada_2))
		{
			direccion = Direccion.DIAGONAL_SECUNDARIA;
		}		
		
		return direccion;
	}

	private boolean digPrincipal(CoordenadaTresEnRaya coordenada) {
		return (coordenada.getFila() == coordenada.getColumna());
	}
	
	private boolean digSecundaria(CoordenadaTresEnRaya coordenada) {
		return (coordenada.getFila() + coordenada.getColumna() == dim - 1);
	}
	
			
}	
