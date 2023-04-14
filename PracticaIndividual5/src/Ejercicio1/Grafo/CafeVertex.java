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
		return of(0, LecturaCafe.getUniverso());
	}

	@Override
	public List<Integer> actions() {
		List<Integer> res;
		
		if(index < M) {
			Integer max = getMax(index, remaining);
			res = List2.rangeList(0, max);
		}else {
			res = List.of();
		}
		
		return res;
	}

	@Override
	public CafeVertex neighbor(Integer a) {
		List<Double> remain = new ArrayList<>();
		
		for(int i = 0; i < N - 1; i++) {
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
	
	@Override
	public Boolean isValid() {
		return index >= 0 && index <= M && 
				remaining.stream().mapToDouble(e-> e).sum() >= 0;
	}
	
	public static Predicate<CafeVertex> goal(){
		return e-> e.index == CafeVertex.M;
	}
	
	public static Predicate<CafeVertex> goalHasSolution(){
		return e -> {
			Boolean flag = true;
			
			for(int i = 0; i < M; i++) {
				Integer aux = getMax(i, e.remaining).intValue();
				if(aux != 0) {
					flag = false;
					break;
				}
			}
			
			return flag;
		};
	}
	
	public static SolucionCafe getSolucion(GraphPath<CafeVertex, CafeEdge> path) {
		return CafeVertex.getSolucion(path.getEdgeList());
	}
	
	private static SolucionCafe getSolucion(List<CafeEdge> ls) {
		List<Integer> actions = ls.stream().map(e-> e.action()).toList();
		
		return SolucionCafe.ofRange(actions);
	}
	
	public CafeEdge greedyEdge() {
		return maxMejor() ? edge(0) : edge(getMax(index, remaining).intValue());
	}
	
	public String toString() {
		return String.format("%d; %d", this.index, this.remaining);
	}
	
	public static Integer getMax(Integer index, List<Double> remaining) {
		Double r = List2.rangeList(0, N - 1).stream()
				.filter(e-> LecturaCafe.getPorcentaje(e, index) > 0)
				.mapToDouble(e-> remaining.get(e)/LecturaCafe.getPorcentaje(e, index))
				.min().orElse(0);
		return r.intValue();
	}

	private Boolean maxMejor(){
		Double max = List2.rangeList(this.index + 1, M)
				.stream().filter(e-> LecturaCafe.mismaComposicion(index).contains(e))
				.mapToDouble(e-> LecturaCafe.getBeneficio(e) * getMax(e, remaining).intValue())
				.max().orElse(0);
		
		Double vertice = LecturaCafe.getBeneficio(index) * 
				getMax(index, remaining).intValue();
		
		
		return vertice < max;
	}
}

