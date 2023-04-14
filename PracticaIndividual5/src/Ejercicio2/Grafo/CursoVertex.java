package Ejercicio2.Grafo;

import org.jgrapht.GraphPath;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import Ejercicio2.LecturaCurso;
import Ejercicio2.SolucionCurso;

import us.lsi.graphs.virtual.VirtualVertex;

public record CursoVertex(Integer index, Set<Integer> tematicas, Set<Integer> centros) 
	implements VirtualVertex<CursoVertex, CursoEdge, Integer>{
	
	private static Integer M = LecturaCurso.getM();
	private static Integer N = LecturaCurso.getN();
	private static Integer MC = LecturaCurso.MaxCentros();
	
	public static CursoVertex of(Integer index, Set<Integer> tematicas, Set<Integer> centros) {
		return new CursoVertex(index, tematicas, centros);
	}
	
	public static CursoVertex initial() {
		Set<Integer> tematicas = new HashSet<>();
		Set<Integer> centros = new HashSet<>();
		
		return of(0, tematicas, centros);
	}
	
	@Override
	public List<Integer> actions() {
		List<Integer> res = List.of(0);
		
		if(this.index < M) {
			res.add(1);
		}
		
		return res;
	}

	@Override
	public CursoVertex neighbor(Integer a) {
		Set<Integer> tematica = this.tematicas;
		Set<Integer> centro = this.centros;
		
		if(a == 1) {
				tematica.addAll(LecturaCurso.getTematica(index));
				centro.add(LecturaCurso.getCentro(index));
		}
		
		return of(index + 1, tematica, centro);
	}

	@Override
	public CursoEdge edge(Integer a) {
		return CursoEdge.of(this, this.neighbor(a), a);
	}

	@Override
	public String toString() {
		return String.format("%d; tematicas: %d, centros, %d", 
				this.index, this.tematicas, this.centros);
	}

	@Override
	public Boolean isValid() {
		Boolean flag = false;
		
		if(index <= M &&
				tematicas.size() <= N &&
				centros.size() <= MC) {
			flag = true;
		}
		
		return flag;
	}
	
	public static SolucionCurso getSolucion(GraphPath<CursoVertex, CursoEdge> path) {
		return CursoVertex.getSolucion(path.getEdgeList());
	}
	
	private static SolucionCurso getSolucion(List<CursoEdge> ls) {
		return SolucionCurso.ofRange(
				ls.stream().map(e-> e.action()).toList());
	}
	
	
	
	

}
