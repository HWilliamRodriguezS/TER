package unit;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import tresEnRaya.Turno;

public class TurnoTest {
   
    private Turno turno;

    @Before
    public void ini() {
        turno = new Turno();
    }

    public void toca(int valor){
        assertTrue("Inicialmente, debe estar invisible", this.turno.toca()==valor);
        assertTrue("Inicialmente, debe estar invisible", this.turno.noToca()==(valor+1)%2);    	
    }
    
    @Test
    public void testValoresIniciales() {
    	this.toca(0);
    }
    
    @Test
    public void testCamiar() {
    	turno.cambiar();
    	this.toca(1);
    }
    
    @Test
    public void testRecamiar() {
    	turno.cambiar();
    	turno.cambiar();
    	this.toca(0);
    }
    
    @Test
    public void testTurnoAleatorio(){
    	int ejecuciones = 1000;
    	int toca1 = 0;
    	int toca0 = 0;
    	for(int i= 0; i < ejecuciones ;i++){
    		this.turno.aleatorio();
    		int turno = this.turno.toca();
    			switch (turno) {
				case 0:
					toca0++;
					break;
				case 1:
					toca1++;
					break;

    		}
    	}
    	assertTrue(toca0 >= 400);
    	assertTrue(toca1 >= 400);
    	
    }

}

