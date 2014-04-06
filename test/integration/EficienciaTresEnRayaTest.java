package integration;

import org.junit.Test;

import java.util.Random;

import util.Coordenada;
import tresEnRaya.Tablero;

public class EficienciaTresEnRayaTest {

	private final int muestraTableros = 1000000;

	public Coordenada getCoordenadaAleatoria() {
		Random random = new Random();
		return new Coordenada(random.nextInt(3), random.nextInt(3));
	}

	@Test
	public void testEficienciaTresEnRaya() {
		Tablero[] tableros = new Tablero[muestraTableros];
		for (int i = 0; i < tableros.length; i++) {
			tableros[i] = new Tablero();
			try {
				for (int j = 0; j < 3; j++) {
					tableros[j].poner(this.getCoordenadaAleatoria(), Tablero.colores[0]);
					tableros[j].poner(this.getCoordenadaAleatoria(), Tablero.colores[1]);
				}
			} catch (Exception ex) {
			}
		}
		long inicio = System.currentTimeMillis();
		for (Tablero tablero : tableros) {
			tablero.hayTER(Tablero.colores[0]);
		}
		long fin = System.currentTimeMillis();
		System.out.println("Tiempo: " + (fin - inicio));
	}
}
