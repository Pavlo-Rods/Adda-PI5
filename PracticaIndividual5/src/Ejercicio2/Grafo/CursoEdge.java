package Ejercicio2.Grafo;

import Ejercicio2.LecturaCurso;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CursoEdge(CursoVertex source, CursoVertex target, Integer action, Double weight) 
	implements SimpleEdgeAction<CursoVertex, Integer>{
	
	public static CursoEdge of(CursoVertex source, CursoVertex target, Integer action, Double weight) {
		return new CursoEdge(source, target, action, weight);
	}
	
	public static CursoEdge of(CursoVertex source, CursoVertex target, Integer action) {
		Double weight = action * LecturaCurso.getPrecio(source.index());
		
		return of(source, target, action, weight);
	}

}
