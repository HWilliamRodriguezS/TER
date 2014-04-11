package tresEnRaya;

import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIO;

public class TresEnRaya {
	private final Tablero tablero = new Tablero();

	public TresEnRaya(int i) {
		switch (i) {
		case 1:
			tablero.setJugador(new Jugador('o'));
			tablero.setJugador(new JugadorAutomatico('x'));
			break;
		case 2:
			tablero.setJugador(new Jugador('o'));
			tablero.setJugador(new Jugador('x'));
			break;
		case 3:
			tablero.setJugador(new JugadorAutomatico('o'));
			tablero.setJugador(new JugadorAutomatico('x'));
			break;
		default:
			break;
		}
	}

	public void jugar() {
		tablero.mostrar();
		do {
			if (!tablero.lleno()) {
				tablero.getJugador(Jugador.turno.toca()).poner(tablero);
			} else {
				tablero.getJugador(Jugador.turno.toca()).mover(tablero);
			}
			tablero.mostrar();
			Jugador.turno.cambiar();
		} while (!tablero.hayTER(tablero.getJugador(Jugador.turno.noToca())
				.getColor()));
		tablero.getJugador(Jugador.turno.noToca()).victoria();
	}

	public static void main(String arg[]) {
		GestorIO gestorIO = FactoriaGestorIO.getInstance();
		gestorIO.out("Digite opcion\n" + "1- Contra la maquina	\n"
				+ "2- Dos Jugadores \n" + "3- Demo\n");

		int opcion = gestorIO.inInt();

		TresEnRaya partida = new TresEnRaya(opcion);
		partida.jugar();
	}
}

// pruebas y diseño por contrato:
// Intervalo
// Jugador
// TresEnRaya

// refactorizar:
// ***Realizado** método jugar con un solo bucle con cuerpo alternativo para
// poner y mover según fichas puestas
// ***Realizado** optimizar tresEnRaya buscando solo la del jugador posible
// ***Realizado** mover DIM y RANGO a Tablero
// ***Realizado** turno con arranque aleatorio
// ***Realizado** asociar Turno a Jugador
// **Realizado** asociar Jugador a Tablero
// **Realizado** mensajes de error en coordenadas erroneas
// **Realizado** corregir que no se puede mover sobre el mismo sitio
// **Realizado** concentrar color en tablero en static {'x','o'}
// **Realizado** desacoplar color de los caracteres particulares
// **Realizado** desacoplar coordenada de TresEnRaya con clase derivada
// CoordenadaTresEnRaya
// - moviendo válida
// - redefiniendo recoger
// **Realizado** a En Tablero
// "fichas[coordenada.getFila()][coordenada.getColumna()]" DRY con get/set
// w recodificar Tablero como gestor de Coordenadas
// a jugar contra la máquina y demo con JugadorAutomático y arranque paramétrico
