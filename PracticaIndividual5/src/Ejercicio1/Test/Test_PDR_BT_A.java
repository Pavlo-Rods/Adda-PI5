package Ejercicio1.Test;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import Ejercicio1.Grafo.CafeVertex;
import Ejercicio1.CafeHeuristic;
import Ejercicio1.LecturaCafe;
import Ejercicio1.SolucionCafe;
import Ejercicio1.Grafo.CafeEdge;

import org.jgrapht.GraphPath;

import us.lsi.common.String2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.DPR;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class Test_PDR_BT_A {
	
	public static <V, E> void toConsole(String type, GraphPath<V, E> path, Function<GraphPath<V, E>, ?> fSolution) {
		if (path != null)
			String2.toConsole("Solucion %s: %s", type, fSolution.apply(path));
		else
			String2.toConsole("%s no obtuvo solucion", type);

		String2.toConsole(String2.linea());
	}

	public static EGraph<CafeVertex, CafeEdge> buildGraph(String fichero) {

		LecturaCafe.iniDatos(fichero);

		CafeVertex vInicial = CafeVertex.initial();

		EGraph<CafeVertex, CafeEdge> graph = EGraph
				.virtual(vInicial, CafeVertex.goal(), PathType.Sum, Type.Min)
				.greedyEdge(CafeVertex::greedyEdge).heuristic(CafeHeuristic::heuristic).build();

		return graph;

	}

	public static GraphPath<CafeVertex, CafeEdge> getGreedySolution(String fichero) {

		EGraph<CafeVertex, CafeEdge> graph = buildGraph(fichero);

		GreedyOnGraph<CafeVertex, CafeEdge> gs = GreedyOnGraph.of(graph);
		GraphPath<CafeVertex, CafeEdge> gp = gs.path();

		toConsole("Greedy", gp, CafeVertex::getSolucion);

		return gp;

	}

	public static void getAStarSolution(String fichero,
			GraphPath<CafeVertex, CafeEdge> greedySolution) {

		EGraph<CafeVertex, CafeEdge> graph = buildGraph(fichero);

		AStar<CafeVertex, CafeEdge, SolucionCafe> gs = AStar.of(graph, null,
				greedySolution.getWeight(), greedySolution);
		GraphPath<CafeVertex, CafeEdge> gp = gs.search().get();

		toConsole("A*", gp, CafeVertex::getSolucion);
		System.out.println(gp.getEdgeList().stream().map(e-> e.action()).toList());
	}

	public static void getPDRSolution(String fichero, GraphPath<CafeVertex, CafeEdge> greedySolution) {

		EGraph<CafeVertex, CafeEdge> graph = buildGraph(fichero);

		DPR<CafeVertex, CafeEdge, SolucionCafe> gs = DPR.of(graph, null,
				greedySolution.getWeight(), greedySolution, true);
		GraphPath<CafeVertex, CafeEdge> gp = gs.search().get();

		toConsole("PDR", gp, CafeVertex::getSolucion);
	}

	public static void getBTSolution(String fichero, GraphPath<CafeVertex, CafeEdge> greedySolution) {

		EGraph<CafeVertex, CafeEdge> graph = buildGraph(fichero);

		BT<CafeVertex, CafeEdge, SolucionCafe> gs = BT.of(graph, null,
				greedySolution.getWeight(), greedySolution, true);
		GraphPath<CafeVertex, CafeEdge> gp = gs.search().get();

		toConsole("BT", gp, CafeVertex::getSolucion);
	}

	public static void main(String[] args) {

		Locale.setDefault(new Locale("en", "US"));

		List.of(1, 2).forEach(num_test -> {
			String input_arg = String.format("files/Ejercicio1DatosEntrada%d.txt", num_test);

			GraphPath<CafeVertex, CafeEdge> greedySolution = getGreedySolution(input_arg);
			getAStarSolution(input_arg, greedySolution);
			getPDRSolution(input_arg, greedySolution);
			getBTSolution(input_arg, greedySolution);
		});

	}

}
