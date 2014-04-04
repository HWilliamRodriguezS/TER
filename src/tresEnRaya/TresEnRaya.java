package tresEnRaya;

import util.Intervalo;

public class TresEnRaya {
	//public static final int DIM = 3;
	//public static final Intervalo RANGO = new Intervalo(0, TresEnRaya.DIM-1);
	private final Tablero tablero = new Tablero();
	//private final Jugador jugadores[] = new Jugador[2];
	private final Turno turno = new Turno();

	public TresEnRaya() {
	//	jugadores[0] = new Jugador('o');
		//jugadores[1] = new Jugador('x');
		tablero.setJugador(new Jugador('o'));
		tablero.setJugador(new Jugador('x'));
		this.turno.aleatorio();
//		for (int i = 0; i < jugadores.length; i++) {
//			jugadores[1] = new Jugador(i);
//		}
	}

	public void jugar() {
		tablero.mostrar();
		do {
			if (!tablero.lleno()){				
				tablero.getJugador(turno.toca()).poner(tablero);
				// turno.toca().poner(;
			}else{
				tablero.getJugador(turno.toca()).mover(tablero);
			}
			tablero.mostrar();
			turno.cambiar();	
		}while (!tablero.hayTER(tablero.getJugador(turno.toca()).getColor()));
		tablero.getJugador(turno.toca()).victoria();
	}

	public static void main(String arg[]) {
		TresEnRaya partida = new TresEnRaya();
		partida.jugar();
	}
}

// pruebas y diseño por contrato:
// Intervalo
// Jugador
// TresEnRaya

// refactorizar:
//* método jugar con un solo bucle con cuerpo alternativo para poner y mover según fichas puestas
//* optimizar tresEnRaya buscando solo la del jugador posible
// mover DIM y RANGO a Tablero
// turno con arranque aleatorio
//w asociar Turno a Jugador
//a asociar Jugador a Tablero
//w mensajes de error en coordenadas erroneas
//a corregir que no se puede mover sobre el mismo sitio
//w concentrar color en tablero en static {'x','o'}
//a desacoplar color de los caracteres particulares
//w desacoplar coordenada de TresEnRaya con clase derivada CoordenadaTresEnRaya
// - moviendo válida
// - redefiniendo recoger
//a En Tablero "fichas[coordenada.getFila()][coordenada.getColumna()]" DRY con get/set
//w recodificar Tablero como gestor de Coordenadas
//a jugar contra la máquina y demo con JugadorAutomático y arranque paramétrico