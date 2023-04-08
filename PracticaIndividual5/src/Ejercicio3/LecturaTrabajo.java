package Ejercicio3;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import ClasesLectura.Investigador;
import ClasesLectura.Trabajo;

import us.lsi.common.Files2;

public class LecturaTrabajo {
	
	public static void main(String[] arg) {
		for(int i = 1; i < 4; i++) {
			iniDatos("files/Ejercicio3DatosEntrada" + i + ".txt");
		}
	}
	
	private static List<Trabajo> Trabajos;
	private static List<Investigador> Investigadores;
	private static Set<Integer> Especialidades;
	
	public static void iniDatos(String fichero) {
		Trabajos = new ArrayList<>();
		Investigadores = new ArrayList<>();
		Especialidades = new HashSet<>();
		
		Iterator<String> it = Files2.streamFromFile(fichero).iterator();
		
		Boolean flag = false;
		String l;
		Investigador i;
		it.next();
		
		while(it.hasNext()) {
			l = it.next();
			
			if(!flag) {
				if(l.equals("// TRABAJOS")) {
					flag = true;
				} else {
					i = Investigador.of(l);
					
					Investigadores.add(i);
					Especialidades.add(i.especialidad());
				}
			} else {
				Trabajos.add(Trabajo.of(l));
			}
		}
		
		toConsole(fichero);
	}
	
	private static void toConsole(String fichero) {
		System.out.println("Lectura de: " + fichero);
		System.out.println("Investigadores:");
		System.out.println(Investigadores);
		System.out.println("Trabajos:");
		System.out.println(Trabajos);
		System.out.println("Especialidades: " + Especialidades);
		System.out.println("==================================================================================================");
	}

}
