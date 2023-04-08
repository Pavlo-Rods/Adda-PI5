package ClasesLectura;

import java.util.Set;
import java.util.HashSet;

public record Curso(Set<Integer> tematicas, Double precio, Integer centro) {

	public static Curso of(Set<Integer> tematicas, Double precio, Integer centro) {
		return new Curso(tematicas, precio, centro);
	}
	
	public static Curso of(String linea) {
		String[] li = linea.split(":");
		String[] l = li[0].trim().substring(1, li[0].trim().length() - 1).split(",");
		
		Set<Integer> tematicas = new HashSet<>();
		for(String s:l) {
			tematicas.add(Integer.valueOf(s));
		}
		
		Double precio = Double.valueOf(li[1].trim());
		Integer centro = Integer.valueOf(li[2].trim());
		
		return of(tematicas, precio, centro);
	}
}
