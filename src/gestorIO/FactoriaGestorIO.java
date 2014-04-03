package gestorIO;

public class FactoriaGestorIO {

	private static ModoGestorIO modoGestorIO = ModoGestorIO.CONSOLA; 
	
	public static GestorIO getInstance(){
		return modoGestorIO.get();
	}
	
	private static void set(ModoGestorIO modoGestorIO){
		FactoriaGestorIO.modoGestorIO = modoGestorIO; 
	}
	
	public static GestorIOStub getInstanceStub(){
		FactoriaGestorIO.set(ModoGestorIO.STUB);
		return (GestorIOStub) FactoriaGestorIO.getInstance();
	}
	
	public static void reset(){
		FactoriaGestorIO.set(ModoGestorIO.CONSOLA);
	}
}
