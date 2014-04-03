package unit;

import static org.junit.Assert.assertTrue;
import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIOStub;

import org.junit.Test;

import util.Intervalo;

public class IntervaloTest {

	private void testPuntoMedio(Intervalo intervalo, double resultado){
		assertTrue(intervalo.puntoMedio()==resultado);
	}
	
	@Test
	public void testPuntoMedio(){
		Intervalo[] intervalos = {
				new Intervalo(-6,-2),
				new Intervalo(-10,10),
				new Intervalo(2,3),
				new Intervalo(1,1)};
		double[] resultados = {
				-4,
				0,
				2.5,
				1 };
		for (int i=0; i<intervalos.length; i++){
			this.testPuntoMedio(intervalos[i],resultados[i]);
		}
	}
	
	private void testEscalar(Intervalo entrada, double escala){
		double puntoMedio = entrada.puntoMedio();
		double longitud = entrada.longitud();
		entrada.escalar(escala);
		assertTrue(puntoMedio == entrada.puntoMedio());
		assertTrue(longitud*escala == entrada.longitud());
	}
	
	@Test
	public void testEscalar(){
		Intervalo[] intervalos = {
				new Intervalo(-6,-2),
				new Intervalo(-10,10),
				new Intervalo(2,3),
				new Intervalo(1,1)};
		double[] factores = {
				4,
				2,
				0.5,
				1 };
		for (int i=0; i<intervalos.length; i++){
			this.testEscalar(intervalos[i], factores[i]);
		}
	}
	
	private void testInterseccion(Intervalo uno, 
			Intervalo otro, Intervalo resultado){
		assertTrue(uno.interseccion(otro).igual(resultado));
	}
	
	@Test
	public void testInterseccion(){
		Intervalo[] unos = {
				new Intervalo(-6,-2),
				new Intervalo(-10,10),
				new Intervalo(2,3),
				new Intervalo(1,1)};
		Intervalo[] otros = {
				new Intervalo(-6,-2),
				new Intervalo(-10,10),
				new Intervalo(2,3),
				new Intervalo(1,1)};
		Intervalo[] resultados = {
				new Intervalo(-6,-2),
				new Intervalo(-10,10),
				new Intervalo(2,3),
				new Intervalo(1,1)};
		for (int i=0; i<unos.length; i++){
			this.testInterseccion(unos[i], otros[i], resultados[i]);
		}
	}
	
	private void testMostrar(Intervalo intervalo){
		GestorIOStub gStub = FactoriaGestorIO.getInstanceStub();
		intervalo.mostrar();
		String resultado = "";
		while (!gStub.empty()){
			resultado += gStub.get();
		}
		FactoriaGestorIO.reset();
		assertTrue(resultado.equals("[3.0,8.0]"));
	}
	
	@Test
	public void testRecoger(){
		Intervalo intervalo = new Intervalo();
		GestorIOStub gStub = FactoriaGestorIO.getInstanceStub();
		gStub.set("2");
		gStub.set("5");
		intervalo.recoger();
		FactoriaGestorIO.reset();
		assertTrue(intervalo.igual(new Intervalo(2,5)));
	}
	
	
}
