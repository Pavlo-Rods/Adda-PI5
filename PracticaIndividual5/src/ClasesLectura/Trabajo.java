package ClasesLectura;

import java.util.List;
import java.util.ArrayList;

import us.lsi.common.Pair;

public record Trabajo(Integer id, Integer calidad, List<Pair<Integer, Integer>> reparto) {
	
	public static Trabajo of(Integer id, Integer calidad, List<Pair<Integer, Integer>> reparto) {
		return new Trabajo(id, calidad, reparto);
	}
	
	public static Trabajo of(String linea) {
		String[] li = linea.split("->");
		String[] l = li[1].split(";");
		
		Integer id = Integer.valueOf(li[0].trim().substring(1));
		Integer calidad = Integer.valueOf(l[0].split("=")[1].trim());
		List<Pair<Integer, Integer>> reparto = getReparto(l[1].split("=")[1]);
		
		return of(id, calidad, reparto);
	}
	
	private static List<Pair<Integer, Integer>> getReparto(String linea){
		List<Pair<Integer, Integer>> res = new ArrayList<>();
		String[] l = linea.split(",");
		
		for(String s:l) {
			String[] fs = s.split(":");
			
			Integer first = Integer.valueOf(fs[0].trim().substring(1));
			Integer second = Integer.valueOf(fs[1].trim().substring(0, fs[1].trim().length() - 1));
			res.add(Pair.of(first, second));
		}
		
		return res;
	}

}
