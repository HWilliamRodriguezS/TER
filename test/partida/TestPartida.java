package partida;

import org.junit.Test;

public class TestPartida {

	@Test
	public void testMover(){
		Partida partida = new Partida();
		Movimiento movimiento = new Movimiento();
		partida.mover(movimiento);
	}
}

