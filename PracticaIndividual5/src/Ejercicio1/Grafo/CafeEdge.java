package Ejercicio1.Grafo;

import Ejercicio1.LecturaCafe;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CafeEdge(CafeVertex source, CafeVertex target, Integer action, Double weight)
	implements SimpleEdgeAction<CafeVertex, Integer>{
	
	public static CafeEdge of(CafeVertex source, CafeVertex target, Integer action, Double weight) {
		return new CafeEdge(source, target, action, weight);
	}
	
	public static CafeEdge of(CafeVertex source, CafeVertex target, Integer action) {
		Double weight = action * LecturaCafe.getBeneficio(source.index());
		
		return of(source, target, action, weight);
	}
	
	public String toString() {
		return String.format("%d; %.1f", this.action, this.weight);
	}

}
