package tresEnRaya;

import gestorIO.FactoriaGestorIO;

public class JugadorAutomatico extends Jugador {

	public JugadorAutomatico(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void poner(Tablero tablero) {		 
		assert tablero != null;
		FactoriaGestorIO.getInstance().out("juega: " + color);
		CoordenadaTresEnRaya destino = new CoordenadaTresEnRaya();

		do {
			String msg = "Coordenada destino de puesta";
			if (!destino.valida())	
				msg = "Coordenadas erroneas o fuera de rango";
			destino  = new CoordenadaTresEnRaya(new java.util.Random().nextInt(3),new java.util.Random().nextInt(3));
		} while (!destino.valida() || tablero.ocupado(destino));
		assert tablero.vacio(destino);
		assert !tablero.igualOrigen(destino);
		tablero.poner(destino, color);
		assert tablero.ocupado(destino, color);
		System.out.println();
	}

	@Override
	public void mover(Tablero tablero) {
		assert tablero != null;
		CoordenadaTresEnRaya origen = new CoordenadaTresEnRaya();
		do {
			origen= new CoordenadaTresEnRaya(new java.util.Random().nextInt(3),new java.util.Random().nextInt(3));
		} while (!origen.valida() || !tablero.ocupado(origen, color));
		assert tablero.ocupado(origen, color);
		tablero.sacar(origen);
		assert tablero.vacio(origen);
		this.poner(tablero);
		System.out.println();
	}
	
	
	

}
