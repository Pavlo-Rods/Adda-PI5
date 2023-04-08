package Ejercicio4;

import org.jgrapht.Graph;

import ClasesLectura.Cliente;
import ClasesLectura.Ruta;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class LecturaReparto {
	
	public static void main(String[] args) {
		for(int i = 1; i < 3; i++) {
			iniDatos("files/Ejercicio4DatosEntrada" + i + ".txt");
		}
		
	}
	
	private static Graph<Cliente, Ruta> Caminos;
	
	public static void iniDatos(String fichero) {
		Caminos = GraphsReader.newGraph(
				fichero, Cliente::ofFormat,
				Ruta::ofFormat,
				Graphs2::simpleWeightedGraph, Ruta::kms);
		
		toConsole(fichero);
	}
	
	private static void toConsole(String fichero) {
		System.out.println("Lectura del fichero: " + fichero);
		System.out.println("Clientes");
		System.out.println(Caminos.vertexSet());
		System.out.println("Rutas");
		System.out.println(Caminos.edgeSet());
		System.out.println("====================================================================================");
	}

}
