package ClasesLectura;

import java.util.List;
import java.util.ArrayList;

import us.lsi.common.Pair;

public record Variedad(Integer id, Double beneficio, List<Pair<Integer, Double>> composicion) {
	
	public static Variedad of(Integer id, Double beneficio, List<Pair<Integer, Double>> composicion) {
		return new Variedad(id, beneficio, composicion);
	}
	
	public static Variedad of(String linea, Integer N) {
		String[] li = linea.split("->");
		String[] l = li[1].split(";");
		
		Integer id = Integer.valueOf(li[0].trim().substring(1));
		Double beneficio = Double.valueOf(l[0].trim().substring(10));
		List<Pair<Integer, Double>> composicion = getComposicion(l[1].split("=")[1].trim(), N);
		
		return of(id, beneficio, composicion);
	}
	
	private static List<Pair<Integer, Double>> getComposicion(String comp, Integer N) {
		List<Pair<Integer, Double>> res = new ArrayList<>();
		String[] li = comp.split(",");
		
		for(String s:li) {
			String[] l = s.trim().split(":");
			Integer first = Integer.valueOf(l[0].trim().substring(2));
			Double second = Double.valueOf(l[1].trim().substring(0, l[1].trim().length() - 1));
			
			res.add(Pair.of(first, second));
		}
		
		for(int i = 0; i < N; i++) {
			if(!res.stream().map(e-> e.first()).toList().contains(i)) {
				res.add(Pair.of(i, 0.));
			}
		}
		
		return res;
	}

}
