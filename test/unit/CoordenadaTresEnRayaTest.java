package unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tresEnRaya.CoordenadaTresEnRaya;
import tresEnRaya.Tablero;
import util.Direccion;

public class CoordenadaTresEnRayaTest {

	public void testValida(CoordenadaTresEnRaya coordenada) {
		assertTrue("Valida", coordenada.valida());
	}
		
	@Test
	public void testValida(){
		CoordenadaTresEnRaya[][] paresCoordenadas = {
				{new CoordenadaTresEnRaya(2,1)},
				{new CoordenadaTresEnRaya(2,2)},
				{new CoordenadaTresEnRaya(0,0)}};
		for(CoordenadaTresEnRaya[] parCoordenadas : paresCoordenadas){
			this.testValida(parCoordenadas[0]);
		}
	}
	
	public void testDireccion(Direccion direccion,
			CoordenadaTresEnRaya coordenada_1, CoordenadaTresEnRaya coordenada_2) {
		assertTrue(coordenada_1.direccion(coordenada_2) == direccion);
	}
	
	@Test
	public void testDireccion() {
		
		Direccion [] direcciones = { 				
				Direccion.HRIZONTAL, 
				Direccion.VERTICAL, 
				Direccion.DIAGONAL_PRINCIPAL, 
				Direccion.DIAGONAL_SECUNDARIA,
				Direccion.SIN_DIRECCION};
		
		CoordenadaTresEnRaya[][] paresCoordenadas = {
				{new CoordenadaTresEnRaya(2,0), new CoordenadaTresEnRaya(2,1)},
				{new CoordenadaTresEnRaya(0,2), new CoordenadaTresEnRaya(1,2)},
				{new CoordenadaTresEnRaya(0,0), new CoordenadaTresEnRaya(2,2)},
				{new CoordenadaTresEnRaya(2,0), new CoordenadaTresEnRaya(1,1)},
				{new CoordenadaTresEnRaya(2,0), new CoordenadaTresEnRaya(0,1)}};
		int i = 0;
		for(CoordenadaTresEnRaya[] parCoordenadas : paresCoordenadas){
			this.testDireccion(direcciones[i], parCoordenadas[0], parCoordenadas[1]);
			i++;
		}
		
	}
	
}
