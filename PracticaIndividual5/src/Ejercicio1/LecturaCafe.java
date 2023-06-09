package Ejercicio1;

import java.util.List;

import ClasesLectura.Cafe;
import ClasesLectura.Variedad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class LecturaCafe {
	
	public static void main(String[] args) {
		for(int i = 1; i < 4; i++) {
			iniDatos("files/Ejercicio1DatosEntrada" + i + ".txt");
		}
	}
	
	private static List<Cafe> Cafes;
	private static List<Variedad> Variedades;
	
	public static void iniDatos(String fichero) {
		Cafes = new ArrayList<>();
		Variedades = new ArrayList<>();
		
		Iterator<String> it = Files2.streamFromFile(fichero).iterator();
		
		Boolean flag = false;
		String l;
		it.next();
		
		while(it.hasNext()) {
			l = it.next();
			if(!flag) {
				
				if(l.equals("// VARIEDADES")) {
					flag = true;
				} else {
					Cafes.add(Cafe.of(l));
				}
			} else {
				Variedades.add(Variedad.of(l, Cafes.size()));
			}
		}
		
		toConsole(fichero);
	}
	
	private static void toConsole(String fichero) {
		System.out.println("Lectura de" + fichero);
		System.out.println("Cafes:");
		System.out.println(Cafes);
		System.out.println("Variedades");
		System.out.println(Variedades);
		System.out.println("================================================================================================================ ");
	}
	
	//Getters
	//TODO: Directrices de resolucion en ev
	
	public static Integer getN() {
		return Cafes.size();
	}
	
	public static Integer getM() {
		return Variedades.size();
	}
	
	public static Integer getKgs(Integer c){
		return Cafes.get(c).kg();
	}
	
	public static Double getPorcentaje(Integer c, Integer v) {
		return Variedades.get(v).composicion().get(c).second();
	}
	
	public static Double getBeneficio(Integer v) {
		return Variedades.get(v).beneficio();
	}
	
	public static List<Variedad> getVariedad(){
		return Variedades;
	}
	
	public static List<Integer> mismaComposicion(Integer v){
		List<Integer> res = new ArrayList<>();
		
		List<Integer> comp = List2.rangeList(0, getN() - 1).stream()
				.filter(e-> getPorcentaje(e, v) != 0).toList();
		
		for(int i = v + 1; i < getM(); i++) {
			List<Integer> aux = List2.rangeList(0, getN() - 1).stream()
					.filter(e-> getPorcentaje(e, v) != 0).toList();
			
			if(!Collections.disjoint(comp, aux)) {
				res.add(i);
			}
		}
		
		return res;
	}
	
	public static List<Double> getUniverso(){
		return Cafes.stream().map(e-> e.kg().doubleValue()).toList();
	}

}
