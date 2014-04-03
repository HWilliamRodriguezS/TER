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

}

