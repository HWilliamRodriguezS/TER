package unit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIOStub;

import org.junit.Test;

import util.Coordenada;

public class CoordenadaTest {

	public void testIguales(Coordenada una, Coordenada otra) {
		assertTrue("Iguales", una.iguales(otra));
	}
		
	@Test
	public void testIguales(){
		Coordenada[][] paresCoordenadas = {
				{new Coordenada(2,1), new Coordenada(2,1)},
				{new Coordenada(2,2), new Coordenada(2,2)},
				{new Coordenada(0,0), new Coordenada(0,0)}};
		for(Coordenada[] parCoordenadas : paresCoordenadas){
			this.testIguales(parCoordenadas[0], parCoordenadas[1]);
		}
	}
	
	public void testDistintos(Coordenada una, Coordenada otra) {
		assertFalse("Distintos", una.iguales(otra));
	}
	
	@Test
	public void testDistintos(){
		Coordenada[][] paresCoordenadas = {
				{new Coordenada(1,0), new Coordenada(2,1)},
				{new Coordenada(2,1), new Coordenada(2,2)},
				{new Coordenada(2,0), new Coordenada(0,0)}};
		for(Coordenada[] parCoordenadas : paresCoordenadas){
			this.testDistintos(parCoordenadas[0], parCoordenadas[1]);
		}
	}
	
	public void testRecoger(Coordenada entrada) {
		GestorIOStub gestorIO = FactoriaGestorIO.getInstanceStub();
		gestorIO.set(""+(entrada.getFila()+1));
		gestorIO.set(""+(entrada.getColumna()+1));
		Coordenada coordenada = new Coordenada();
		coordenada.recoger("Introduzca coordenada: ");
		assertTrue("Fila introducida", coordenada.getFila()==entrada.getFila());
		assertTrue("Columna introducida", coordenada.getColumna()==entrada.getColumna());
		FactoriaGestorIO.reset();
	}
	
	@Test
	public void testRecoger(){
		Coordenada[] coordenadas = {
				new Coordenada(1,0), 
				new Coordenada(2,2), 
				new Coordenada(0,0)};
		for (Coordenada coordenada: coordenadas){
			this.testRecoger(coordenada);
		}
	}
	
}
