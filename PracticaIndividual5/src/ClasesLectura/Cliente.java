package ClasesLectura;

public record Cliente(Integer id, Double beneficio) {
	
	
	public static Cliente of(Integer id, Double beneficio) {
		return new Cliente(id, beneficio);
	}
	
	public static Cliente ofFormat(String[] s) {
		return of(Integer.valueOf(s[0].trim()), Double.valueOf(s[1].trim()));
	}

}
