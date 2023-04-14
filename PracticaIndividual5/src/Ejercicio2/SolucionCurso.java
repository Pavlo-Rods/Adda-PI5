package Ejercicio2;

import java.util.List;
import java.util.ArrayList;

import ClasesLectura.Curso;

public class SolucionCurso {
	
	private Double beneficio;
	private List<Curso> solucion;
	
	private SolucionCurso() {
		beneficio = 0.;
		solucion = new ArrayList<>();
	}
	
	private SolucionCurso(List<Integer> ls) {
		beneficio = 0.;
		solucion = new ArrayList<>();
		
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) != 0) {
				solucion.add(LecturaCurso.getCurso(i));
				beneficio += LecturaCurso.getPrecio(i);
			}
		}		
	}
	
	public static SolucionCurso empty() {
		return new SolucionCurso();
	}
	
	public static SolucionCurso ofRange(List<Integer> values) {
		return new SolucionCurso(values);
	}

	@Override
	public String toString() {
		String aux = "";
		
		for(int i = 0; i < solucion.size(); i++) {
			aux += String.format("\nCurso nº: %s", solucion.get(i).id());
		}
		
		return String.format("Cursos seleccionadas:%s\nBeneficio: ", aux) + beneficio;
	}
	
	

}
