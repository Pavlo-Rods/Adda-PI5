package Ejercicio2;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import ClasesLectura.Curso;

import us.lsi.common.Files2;

public class LecturaCurso {
	
	public static void main(String[] args) {
		for(int i = 1; i < 4; i++) {
			iniDatos("files/Ejercicio2DatosEntrada" + i + ".txt");
		}
	}
	
	private static List<Curso> Cursos;
	private static Integer MaxCentro;
	private static Set<Integer> Tematicas;
	private static Set<Integer> Centros;
	
	public static void iniDatos(String fichero) {
		Cursos = new ArrayList<>();
		Tematicas = new HashSet<>();
		Centros = new HashSet<>();
		
		Iterator<String> it = Files2.streamFromFile(fichero).iterator();
		
		MaxCentro = Integer.valueOf(it.next().split("=")[1].trim());
		Curso c;
		
		while(it.hasNext()) {
			c = Curso.of(it.next());
			
			Cursos.add(c);
			Tematicas.addAll(c.tematicas());
			Centros.add(c.centro());
		}
		
		toConsole(fichero);
		
	}

	private static void toConsole(String fichero) {
		System.out.println("lectura de: " + fichero);
		System.out.println("Cursos");
		System.out.println(Cursos);
		System.out.println("Maximo de centros = " + MaxCentro);
		System.out.println("Tematicas: " + Tematicas);
		System.out.println("Centros: " + Centros);
		System.out.println("=========================================================================================");
		
	}
}
