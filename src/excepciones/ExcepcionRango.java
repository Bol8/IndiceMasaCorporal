package excepciones;

public class ExcepcionRango extends Exception {
	
	
	@Override 
	public String getMessage(){
		return "Peso o altura fuera de rango\nPeso:10-300\nAltura:100-300";
		
	}
	

}
