package unit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIOStub;

import org.junit.Before;
import org.junit.Test;

import tresEnRaya.TresEnRaya;
import tresEnRaya.Tablero;
import util.Coordenada;

public class TableroTest {

	private Tablero tablero;

	@Before
	public void ini() {
		this.tablero = new Tablero();
	}

	private void testCasillaVacia(Coordenada coordenada) {
		assertTrue("Casilla vacía", this.tablero.vacio(coordenada));
		assertFalse("Casilla no ocupada", this.tablero.ocupado(coordenada));
	}

	private void testCasillaOcupada(Coordenada coordenada) {
		assertFalse("Casilla NO vacía", this.tablero.vacio(coordenada));
		assertTrue("Casilla ocupada", this.tablero.ocupado(coordenada));
	}

	private void testCasillaOcupada(Coordenada coordenada, char color) {
		this.testCasillaOcupada(coordenada);
		assertTrue("Casilla ocupada por " + color,
				this.tablero.ocupado(coordenada, color));
	}

	@Test
	public void testTableroInicialVacio() {
		for (int i = 0; i < Tablero.getDim(); i++) {
			for (int j = 0; j < Tablero.getDim(); j++) {
				this.testCasillaVacia(new Coordenada(i, j));
			}
		}
	}

	private void testPonerYSacar(Coordenada destino, char color) {
		this.tablero.poner(destino, color);
		for (int i = 0; i < Tablero.getDim(); i++) {
			for (int j = 0; j < Tablero.getDim(); j++) {
				Coordenada coordenada = new Coordenada(i, j);
				if (coordenada.iguales(destino)) {
					this.testCasillaOcupada(coordenada, color);
				} else {
					this.testCasillaVacia(coordenada);
				}
			}
		}
		this.tablero.sacar(destino);
		for (int i = 0; i < Tablero.getDim(); i++) {
			for (int j = 0; j < Tablero.getDim(); j++) {
				Coordenada coordenada = new Coordenada(i, j);
				this.testCasillaVacia(coordenada);
			}
		}
	}

	@Test
	public void testPonerYSacar() {
		Coordenada[] coordenadas = { new Coordenada(0, 0),
				new Coordenada(1, 2), new Coordenada(2, 2) };
		char[] colores = { 'x', 'o', 'o' };
		assert coordenadas.length == colores.length;
		for (int i = 0; i < coordenadas.length; i++) {
			this.testPonerYSacar(coordenadas[i], colores[i]);
		}
	}

	private void testMover(char color, Coordenada origen, Coordenada destino) {
		this.tablero.poner(origen, color);
		this.testCasillaOcupada(origen, color);
		this.testCasillaVacia(destino);
		this.tablero.sacar(origen);
		this.testCasillaVacia(origen);
		this.testCasillaVacia(destino);
		this.tablero.poner(destino, color);
		this.testCasillaVacia(origen);
		this.testCasillaOcupada(destino, color);
	}

	@Test
	public void testMover() {
		Coordenada[][] paresCoordenadas = {
				{ new Coordenada(0, 0), new Coordenada(1, 0) },
				{ new Coordenada(1, 2), new Coordenada(2, 1) },
				{ new Coordenada(2, 2), new Coordenada(0, 0) } };
		char[] colores = { 'x', 'o', 'o' };
		assert paresCoordenadas.length == colores.length;
		for (int i = 0; i < paresCoordenadas.length; i++) {
			this.testMover(colores[i], paresCoordenadas[i][0],
					paresCoordenadas[i][1]);
		}
	}

	private void ponerFichas(char[] colores, Coordenada[] coordenadas) {
		for (int i = 0; i < coordenadas.length; i++) {
			this.tablero.poner(coordenadas[i], colores[i]);
		}
	}

	private void ponerFichas(char color, Coordenada[] coordenadas) {
		char[] colores = new char[coordenadas.length];
		for (int i = 0; i < colores.length; i++) {
			colores[i] = color;
		}
		this.ponerFichas(colores, coordenadas);
	}

	private void testTresEnRaya(char color, Coordenada[] coordenadas) {
		this.ponerFichas(color, coordenadas);
		assertTrue("Tres en raya", this.tablero.hayTER(color));
	}

	private void testTresEnRayaEnFila(char color, int fila) {
		this.testTresEnRaya('x', new Coordenada[] { new Coordenada(fila, 0),
				new Coordenada(fila, 1), new Coordenada(fila, 2) });
	}

	@Test
	public void testTresEnRayaEnFila() {
		int[] filas = { 0, 1, 2 };
		char[] colores = { 'x', 'o', 'o' };
		assert filas.length == colores.length;
		for (int i = 0; i < filas.length; i++) {
			this.testTresEnRayaEnFila(colores[i], filas[i]);
		}
	}

	private void testTresEnRayaEnColumna(char color, int columna) {
		this.testTresEnRaya('o', new Coordenada[] { new Coordenada(0, columna),
				new Coordenada(1, columna), new Coordenada(2, columna) });
	}

	@Test
	public void testTresEnRayaEnColumna() {
		int[] columnas = { 0, 1, 2 };
		char[] colores = { 'x', 'o', 'o' };
		assert columnas.length == colores.length;
		for (int i = 0; i < columnas.length; i++) {
			this.testTresEnRayaEnColumna(colores[i], columnas[i]);
		}
	}

	@Test
	public void testTresEnRayaoEnDiagonalPrincipal() {
		Coordenada[] coordenadas = { new Coordenada(0, 0),
				new Coordenada(1, 1), new Coordenada(2, 2) };
		this.testTresEnRaya('x', coordenadas);
	}

	@Test
	public void testTresEnRayaoEnDiagonalSecundaria() {
		Coordenada[] coordenadas = { new Coordenada(0, 2),
				new Coordenada(1, 1), new Coordenada(2, 0) };
		this.testTresEnRaya('o', coordenadas);
	}

	private void sacarFichas(Coordenada[] coordenadas) {
		for (Coordenada coordenada : coordenadas) {
			this.tablero.sacar(coordenada);
		}
	}

	private void testNoTresEnRaya(char color, Coordenada[] coordenadas) {
		this.ponerFichas(color, coordenadas);
		assertFalse("No tres en raya", this.tablero.hayTER(color));
		this.sacarFichas(coordenadas);
	}

	@Test
	public void testNoTresEnRaya() {
		Coordenada[][] triosCoordenadas = {
				{ new Coordenada(0, 1), new Coordenada(1, 0),
						new Coordenada(2, 2) },
				{ new Coordenada(0, 1), new Coordenada(0, 2),
						new Coordenada(2, 2) } };
		char[] colores = { 'x', 'o' };
		assert triosCoordenadas.length == colores.length;
		for (int i = 0; i < triosCoordenadas.length; i++) {
			this.testNoTresEnRaya('x', triosCoordenadas[i]);
		}
	}
	
	public void testTableroIguales(String[] tablero, GestorIOStub gestorIO){
		System.out.println("\nPRUEBA MOSTRAR:\n");
		
		String stringGenerado = "";
		while (!gestorIO.empty()){
			stringGenerado += gestorIO.get();
		}
		System.out.println("\nTablero generado:\n" + stringGenerado + ".");

		String stringSuministrado = "";
		for (int i = 0; i < tablero.length; i++) {
			stringSuministrado += tablero[i];
		}
		stringSuministrado += "\n";
		System.out.println("\nTablero esperado:\n" + stringSuministrado + ".");

		assertTrue("Tablero iguales: ",
				stringSuministrado.equals(stringGenerado));
	}

	private void testMostrar(String[] tablero, Coordenada[] coordenadas,
			char[] colores) {
		GestorIOStub gestorIO = FactoriaGestorIO.getInstanceStub();
		this.ponerFichas(colores, coordenadas);
		this.tablero.mostrar();
		this.testTableroIguales(tablero, gestorIO);
		this.sacarFichas(coordenadas);
		FactoriaGestorIO.reset();
	}

	@Test
	public void testMostrar() {
		String[][] tableros = { 
				{ "- - - \n", 
				  "- - - \n", 
				  "- - - \n" },

				{ "- x - \n", 
				  "o - - \n", 
				  "- o x \n" },

				{ "- - x \n", 
				  "- - - \n", 
				  "- - - \n" } };
		Coordenada[][] coordenadas = {
				{},
				{ new Coordenada(0, 1), 
				  new Coordenada(1, 0),
				  new Coordenada(2, 1), 
				  new Coordenada(2, 2) },
				{ new Coordenada(0, 2) } };
		char[][] colores = { 
				{}, 
				{ 'x', 
				  'o', 
				  'o', 
				  'x' },
				{ 'x' } };
		assert tableros.length == coordenadas.length;
		assert tableros.length == colores.length;
		for (int i = 0; i < tableros.length; i++) {
			assert coordenadas[i].length == colores[i].length;
			this.testMostrar(tableros[i], coordenadas[i], colores[i]);
		}
	}
	
	@Test
	public void testTableroLleno(){
		Tablero tablero = new Tablero();
		for (int i=0; i<Tablero.getDim(); i++){
			tablero.poner(new Coordenada(0,i), 'x');
			tablero.poner(new Coordenada(1,i), 'o');
		}
		assertTrue(tablero.lleno());
	}

}
