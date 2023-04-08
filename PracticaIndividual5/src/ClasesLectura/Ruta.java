package ClasesLectura;

public record Ruta(Integer idCliente1, Integer idCliente2, Double kms) {
	
	public static Ruta of(Integer idCliente1, Integer idCliente2, Double kms) {
		return new Ruta(idCliente1, idCliente2, kms);
	}
	
	public static Ruta ofFormat(String[] s) {
		return of(Integer.valueOf(s[0].trim()), Integer.valueOf(s[1].trim()),
				Double.valueOf(s[2].trim()));
	}

}
