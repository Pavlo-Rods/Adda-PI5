package Ejercicio1;

import java.util.List;
import java.util.ArrayList;

import ClasesLectura.Variedad;

public class SolucionCafe {
	
	private Double beneficio;
	private List<Variedad> solucion;
	private List<Integer> solucionAlgoritmo;
	
	private SolucionCafe() {
		beneficio = 0.;
		solucion = new ArrayList<>();
		solucionAlgoritmo = new ArrayList<>();
	}
	
	private SolucionCafe(List<Integer> ls) {
		beneficio = 0.;
		solucion = new ArrayList<>();
		solucionAlgoritmo = new ArrayList<>();
		
		for(int i = 0; i < ls.size(); i++) {
			beneficio += ls.get(i) * LecturaCafe.getBeneficio(i);
			solucion.add(LecturaCafe.getVariedad().get(i));
			solucionAlgoritmo.add(ls.get(i));
		}
	}
	
	public static SolucionCafe empty() {
		return new SolucionCafe();
	}
	
	public static SolucionCafe ofRange(List<Integer> values) {
		return new SolucionCafe(values);
	}
	
	public String toString() {
		String aux = "";
		
		for(int i = 0; i < solucion.size(); i++) {
			if(solucionAlgoritmo.get(i) > 0) {
				aux += String.format("\n%s: %d kgs", solucion.get(i).id(), solucionAlgoritmo.get(i));
			}
		}
		
		return String.format("Variedades de cafe seleccionadas:%s\nBeneficio: ", aux) + beneficio;
	}

}
