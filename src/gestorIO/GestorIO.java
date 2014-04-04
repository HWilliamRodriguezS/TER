package gestorIO;

public abstract class GestorIO {

	protected void salir() {
		this.out("ERROR de entrada/salida");
		System.exit(0);
	}
	
	abstract public String inString();

	public int inInt() {
		int entrada = 0;
		try {
			entrada = Integer.parseInt(this.inString());
		} catch (Exception e) {
			this.salir();
		}
		return entrada;
	}

	public float inFloat() {
		float entrada = 0;
		try {
			entrada = Float.parseFloat(this.inString());
		} catch (Exception e) {
			this.salir();
		}
		return entrada;
	}

	public double inDouble() {
		double entrada = 0.0;
		try {
			entrada = Double.parseDouble(this.inString());
		} catch (Exception e) {
			this.salir();
		}
		return entrada;
	}

	public long inLong() {
		long entrada = 0;
		try {
			entrada = Long.parseLong(this.inString());
		} catch (Exception e) {
			this.salir();
		}
		return entrada;
	}

	public byte inByte() {
		byte entrada = 0;
		try {
			entrada = Byte.parseByte(this.inString());
		} catch (Exception e) {
			this.salir();
		}
		return entrada;
	}

	public short inShort() {
		short entrada = 0;
		try {
			entrada = Short.parseShort(this.inString());
		} catch (Exception e) {
			this.salir();
		}
		return entrada;
	}

	public char inChar() {
		char caracter = ' ';
		String entrada = this.inString();
		if (entrada.length() > 1) {
			this.salir();
		} else
			caracter = entrada.charAt(0);
		return caracter;
	}

	public boolean inBoolean() {
		boolean entradaLogica = true;
		String entrada = this.inString();
		if (entrada.equalsIgnoreCase("true")
				|| entrada.equalsIgnoreCase("false"))
			entradaLogica = Boolean.valueOf(entrada).booleanValue();
		else {
			this.salir();
		}
		return entradaLogica;
	}

	abstract public void out(String salida);

	public void out(int salida) {
		this.out(new Integer(salida).toString());
	}

	public void out(float salida) {
		this.out(new Float(salida).toString());
	}

	public void out(double salida) {
		this.out(new Double(salida).toString());
	}

	public void out(long salida) {
		this.out(new Long(salida).toString());
	}

	public void out(byte salida) {
		this.out(new Byte(salida).toString());
	}

	public void out(short salida) {
		this.out(new Short(salida).toString());
	}

	public void out(char salida) {
		this.out(new Character(salida).toString());
	}

	public void out(boolean salida) {
		this.out(new Boolean(salida).toString());
	}

}
