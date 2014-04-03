package util;

import gestorIO.FactoriaGestorIO;
import gestorIO.GestorIO;

public class Intervalo {
	
	private static final double ORIGEN = 0.0;
	private double extremoInferior;
	private double extremoSuperior;
	
	private double puntoMedio;
	private double longitud;
	
	private double getExtremoInferior() {
		return extremoInferior;
	}

	private double getExtremoSuperior() {
		return extremoSuperior;
	}

	private void setExtremoSuperior(double extremoSuperior) {
		this.extremoSuperior = extremoSuperior;
	}

	private void setExtremoInferior(double extremoInferior) {
		this.extremoInferior = extremoInferior;
	}


	

	
	private boolean valido(){
		return this.getExtremoInferior()<=extremoSuperior;
	}
	
	public Intervalo() {
		this(ORIGEN, ORIGEN);
	}

	public Intervalo(double extremo) {
		this(ORIGEN<=extremo?ORIGEN:extremo, ORIGEN>extremo?ORIGEN:extremo);
	}

	public Intervalo(Intervalo intervalo) {
		this(intervalo.extremoInferior, intervalo.extremoSuperior);
	}
	
	public Intervalo(double extremoInferior, double extremoSuperior) {
		this.extremoInferior = extremoInferior;
		this.extremoSuperior = extremoSuperior;
		assert this.valido();
	}

	public void recoger() {
		GestorIO gestorIO = FactoriaGestorIO.getInstance();
		do {
			gestorIO.out("Extremo inferior: ");
			this.extremoInferior = gestorIO.inDouble();
			gestorIO.out("Extremo superior: ");
			this.extremoSuperior = gestorIO.inDouble();
		} while (!this.valido());
		assert this.valido();
		
	}

	public void mostrar() {
		GestorIO gestorIO = FactoriaGestorIO.getInstance();
		gestorIO.out('[');
		gestorIO.out(this.extremoInferior);
		gestorIO.out(',');
		gestorIO.out(this.extremoSuperior);
		gestorIO.out(']');
	}

	public double longitud() {
		return this.extremoSuperior - this.extremoInferior;
	}

	public double puntoMedio() {
		return this.extremoInferior + this.longitud() / 2;
	}

	public boolean igual(Intervalo intervalo) {
		assert intervalo!=null;
		return this.extremoInferior == intervalo.extremoInferior
				&& this.extremoSuperior == intervalo.extremoSuperior;
	}

	public boolean distinto(Intervalo intervalo) {
		assert intervalo!=null;
		return !this.igual(intervalo);
	}

	public boolean anterior(Intervalo intervalo) {
		assert intervalo!=null;
		return this.extremoSuperior < intervalo.extremoInferior;
	}

	public boolean posterior(Intervalo intervalo) {
		assert intervalo!=null;
		return this.extremoInferior > intervalo.extremoSuperior;
	}

	public void escalar(double factor) {
		double puntoMedio = this.puntoMedio();
		double longitudMitad = this.longitud() * factor / 2;
		this.extremoInferior = puntoMedio - longitudMitad;
		this.extremoSuperior = puntoMedio + longitudMitad;
		assert puntoMedio==this.puntoMedio();
		assert longitudMitad==this.longitud()/2;
	}

	public void desplazar(double desplazamiento) {
		this.extremoInferior = this.extremoInferior + desplazamiento;
		this.extremoSuperior = this.extremoSuperior + desplazamiento;
	}

	public void desplazar() {
		this.desplazar(-this.extremoInferior);
	}

	public Intervalo desplazado(double desplazamiento) {
		Intervalo resultado = this.copia();
		resultado.desplazar(desplazamiento);
		return resultado;
	}

	public Intervalo desplazado() {
		return this.desplazado(-this.extremoInferior);
	}

	public boolean incluye(double punto) {
		return this.extremoInferior <= punto && punto <= this.extremoSuperior;
	}

	public boolean incluye(Intervalo intervalo) {
		assert intervalo!=null;
		return this.incluye(intervalo.extremoInferior)
				&& this.incluye(intervalo.extremoSuperior);
	}

	public boolean intersecta(Intervalo intervalo) {
		assert intervalo!=null;
		return this.incluye(intervalo.extremoInferior)
				|| this.incluye(intervalo.extremoSuperior)
				|| intervalo.incluye(this);
	}

	public Intervalo interseccion(Intervalo intervalo) {
		assert intervalo!=null;
		assert this.intersecta(intervalo);
		Intervalo resultado;
		if (this.incluye(intervalo))
			resultado = intervalo.copia();
		else if (intervalo.incluye(this))
			resultado = this.copia();
		else if (this.incluye(intervalo.extremoInferior))
			resultado = new Intervalo(intervalo.extremoInferior,
					this.extremoSuperior);
		else
			resultado = new Intervalo(this.extremoInferior,
					intervalo.extremoSuperior);
		return resultado;
	}

	public Intervalo union(Intervalo intervalo) {
		assert intervalo!=null;
		assert this.intersecta(intervalo);
		double extremoInferior;
		if (this.incluye(intervalo.extremoInferior))
			extremoInferior = this.extremoInferior;
		else
			extremoInferior = intervalo.extremoInferior;
		double extremoSuperior;
		if (this.incluye(intervalo.extremoSuperior))
			extremoSuperior = this.extremoSuperior;
		else
			extremoSuperior = intervalo.extremoSuperior;
		return new Intervalo(extremoInferior, extremoSuperior);
	}

	public Intervalo entre(Intervalo intervalo) {
		assert intervalo!=null;
		assert !this.intersecta(intervalo);
		assert intervalo!=null;
		Intervalo resultado;
		if (this.anterior(intervalo))
			resultado = new Intervalo(this.extremoSuperior,
					intervalo.extremoInferior);
		else
			resultado = new Intervalo(intervalo.extremoSuperior,
					this.extremoInferior);
		return resultado;
	}

	public Intervalo simetrico() {
		return new Intervalo(-this.extremoSuperior, -this.extremoInferior);
	}

	public Intervalo copia() {
		return new Intervalo(this);
	}

}
