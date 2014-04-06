package unit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import tresEnRaya.CoordenadaTresEnRaya;

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
	
}
