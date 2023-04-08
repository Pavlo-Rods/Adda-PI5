package ClasesLectura;

public record Cafe(Integer id, Integer kg) {
	
	public static Cafe of(Integer id, Integer kg) {
		return new Cafe(id, kg);
	}
	
	public static Cafe of(String linea) {
		String[] l = linea.split(":");
		
		Integer id = Integer.valueOf(l[0].trim().substring(1));
		Integer kg = Integer.valueOf(l[1].trim().substring(14, l[1].trim().length() - 1));
		
		return of(id, kg);
	}

}
