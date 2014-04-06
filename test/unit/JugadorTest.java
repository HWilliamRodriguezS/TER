package unit;

import static org.junit.Assert.assertTrue;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIOStub;

import org.junit.Test;

import tresEnRaya.CoordenadaTresEnRaya;
import tresEnRaya.Jugador;
import tresEnRaya.Tablero;

public class JugadorTest {

	public void testPoner(String[] resultado, CoordenadaTresEnRaya[] coordenadas,
			char color) {
		GestorIOStub gestorIO = FactoriaGestorIO.getInstanceStub();
		for(CoordenadaTresEnRaya coordenada: coordenadas){
			gestorIO.set(""+(coordenada.getFila()+1));
			gestorIO.set(""+(coordenada.getColumna()+1));
		}
		
		System.out.println("\nPRUEBA PONER \n");
		Tablero tablero = new Tablero();
		Jugador jugador = new Jugador(color);
		
		for(CoordenadaTresEnRaya coordenada: coordenadas){
			jugador.poner(tablero);
			tablero.mostrar();		
		}
		for (int i=0; i<coordenadas.length; i++){
			for (int j=0; j<4; j++){
				System.out.println("Cabecera: " + gestorIO.get());
			}
			if (i<coordenadas.length-1){
				for(int j=0; j<22; j++){
					System.out.println("Tablero: " + gestorIO.get());
				}
			}
		}
		
		
		TableroTest tableroTest = new TableroTest();
		tableroTest.testTableroIguales(resultado, gestorIO);
		FactoriaGestorIO.reset();
	}

	@Test
	public void testPoner() {
		String[][] tableros = { 
				{ "- - - \n", 
				  "- x - \n", 
				  "- - - \n" },

				{ "- o - \n", 
				  "o - - \n", 
				  "- o - \n" },

				{ "- - x \n", 
				  "- - x \n", 
				  "- - - \n" } };
		CoordenadaTresEnRaya[][] coordenadas = { 
				{ new CoordenadaTresEnRaya(1, 1) },
				{ new CoordenadaTresEnRaya(0, 1),
				  new CoordenadaTresEnRaya(1, 0),
				  new CoordenadaTresEnRaya(2, 1) },
				{ new CoordenadaTresEnRaya(0, 2),
				  new CoordenadaTresEnRaya(1, 2) }};
		char[] colores = { 
				'x',  
				'o', 
				'x' };
		assert tableros.length == coordenadas.length;
		assert tableros.length == colores.length;
		for (int i=0; i<tableros.length; i++) {
			this.testPoner(tableros[i], coordenadas[i], colores[i]);
		}
	}
	
	@Test
	public void turnoJugador(){
		Jugador jugador1 = new Jugador('o');
		Jugador jugador2 = new Jugador('x');
		
		assertTrue(jugador1.getTurno().toca() == jugador2.getTurno().toca() );
		
		jugador1.getTurno().cambiar();
		assertTrue(jugador1.getTurno().toca() == jugador2.getTurno().toca() );
		
	}
	
}
