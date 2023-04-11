package Ejercicio1;

import java.util.function.Predicate;

import Ejercicio1.Grafo.CafeVertex;


public class CafeHeuristic {
	
	public static Double heuristic(CafeVertex v1, Predicate<CafeVertex> goal, CafeVertex v2) {
		Double res = 0.;
		
		for(int i = v1.index(); i < LecturaCafe.getM(); i++) {
			res += CafeVertex.getMax(i, v1.remaining()) *
					LecturaCafe.getBeneficio(i);
		}
	
		return res;
	}

}
