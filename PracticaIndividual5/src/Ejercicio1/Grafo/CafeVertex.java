package Ejercicio1.Grafo;

import java.util.List;
import java.util.function.Predicate;
import java.util.ArrayList;

import org.jgrapht.GraphPath;

import Ejercicio1.LecturaCafe;
import Ejercicio1.SolucionCafe;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record CafeVertex(Integer index, List<Double> remaining)
	implements VirtualVertex<CafeVertex, CafeEdge, Integer>{
	
	private static Integer N = LecturaCafe.getN();
	private static Integer M = LecturaCafe.getM();

	public static CafeVertex of(Integer index, List<Double> remaining) {
		return new CafeVertex(index, remaining);
	}
	
	public static CafeVertex initial() {
		List<Double> remaining = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			remaining.add(LecturaCafe.getKgs(i).doubleValue());
		}
		
		return of(0, remaining);
	}

	@Override
	public List<Integer> actions() {
		List<Integer> res;
		
		if(index < M) {
			Integer max = getMax(index, remaining);
			res = List2.rangeList(0, max);
		}else {
			res = List.of(0);
		}
		
		return res;
	}

	@Override
	public CafeVertex neighbor(Integer a) {
		List<Double> remain = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			Double aux = remaining.get(i) - 
					a * LecturaCafe.getPorcentaje(i, index);
			remain.add(aux);
		}
		
		return of(index + 1, remain);
	}

	@Override
	public CafeEdge edge(Integer a) {
		return CafeEdge.of(this, this.neighbor(a), a);
	}
	
	public Boolean isValid() {
		return index >= 0 && index <= M && 
				remaining.stream().mapToDouble(e-> e).sum() >= 0;
	}
	
	public static Predicate<CafeVertex> goal(){
		return e-> e.index == CafeVertex.M;
	}
	
	public static SolucionCafe getSolucion(GraphPath<CafeVertex, CafeEdge> path) {
		return CafeVertex.getSolucion(path.getEdgeList());
	}
	
	public static SolucionCafe getSolucion(List<CafeEdge> ls) {
		List<Integer> actions = ls.stream().map(e-> e.action()).toList();
		
		return SolucionCafe.ofRange(actions);
	}
	
	private static Integer getMax(Integer index, List<Double> remaining) {
		Double r = List2.rangeList(0, N - 1).stream()
				.filter(e-> LecturaCafe.getPorcentaje(e, index) > 0)
				.mapToDouble(e-> remaining.get(e)/LecturaCafe.getPorcentaje(e, index))
				.min().orElse(0);
		return r.intValue();
	}
}
