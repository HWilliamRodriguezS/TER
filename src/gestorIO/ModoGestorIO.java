package gestorIO;

enum ModoGestorIO {

	CONSOLA(new GestorIOConsola()),
	STUB(new GestorIOStub());
	
	private GestorIO gestorIO;
	
	private ModoGestorIO(GestorIO gestorIO){
		this.gestorIO = gestorIO;
	}
	
	GestorIO get(){
		return gestorIO;
	}
	
}
