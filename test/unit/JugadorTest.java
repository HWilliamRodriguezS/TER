package unit;

import org.junit.Test;

import tresEnRaya.Tablero;
import tresEnRaya.Jugador;
import util.Coordenada;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIOStub;

public class JugadorTest {

	public void testPoner(String[] resultado, Coordenada[] coordenadas,
			char color) {
		GestorIOStub gestorIO = FactoriaGestorIO.getInstanceStub();
		for(Coordenada coordenada: coordenadas){
			gestorIO.set(""+(coordenada.getFila()+1));
			gestorIO.set(""+(coordenada.getColumna()+1));
		}
		
		System.out.println("\nPRUEBA PONER \n");
		Tablero tablero = new Tablero();
		Jugador jugador = new Jugador(color);
		
		for(Coordenada coordenada: coordenadas){
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
		Coordenada[][] coordenadas = { 
				{ new Coordenada(1, 1) },
				{ new Coordenada(0, 1),
				  new Coordenada(1, 0),
				  new Coordenada(2, 1) },
				{ new Coordenada(0, 2),
				  new Coordenada(1, 2) }};
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
}
