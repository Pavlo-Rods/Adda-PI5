package ClasesLectura;

public record Investigador(Integer id, Integer capacidad, Integer especialidad) {
	
	public static Investigador of(Integer id, Integer capacidad, Integer especialidad) {
		return new Investigador(id, capacidad, especialidad);
	}
	
	public static Investigador of(String linea) {
		String[] li = linea.split(":");
		String[] l = li[1].split(";");
		
		Integer id = Integer.valueOf(li[0].trim().substring(3));
		Integer capacidad = Integer.valueOf(l[0].trim().substring(10));
		Integer especialidad = Integer.valueOf(l[1].trim().substring(13));
		
		return of(id, capacidad, especialidad);
	}

}
