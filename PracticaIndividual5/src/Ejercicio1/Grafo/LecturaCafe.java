package Ejercicio1.Grafo;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import ClasesLectura.Cafe;
import ClasesLectura.Variedad;

import us.lsi.common.Files2;

public class LecturaCafe {
	
	public static void main(String[] args) {
		for(int i = 1; i < 4; i++) {
			iniDatos("files/Ejercicio1DatosEntrada" + i + ".txt");
		}
	}
	
	private static List<Cafe> Cafes;
	private static List<Variedad> Variedades;
	
	public static void iniDatos(String fichero) {
		Cafes = new ArrayList<>();
		Variedades = new ArrayList<>();
		
		Iterator<String> it = Files2.streamFromFile(fichero).iterator();
		
		Boolean flag = false;
		String l;
		it.next();
		
		while(it.hasNext()) {
			l = it.next();
			if(!flag) {
				
				if(l.equals("// VARIEDADES")) {
					flag = true;
				} else {
					Cafes.add(Cafe.of(l));
				}
			} else {
				Variedades.add(Variedad.of(l, Cafes.size()));
			}
		}
		
		toConsole(fichero);
	}
	
	private static void toConsole(String fichero) {
		System.out.println("Lectura de" + fichero);
		System.out.println("Cafes:");
		System.out.println(Cafes);
		System.out.println("Variedades");
		System.out.println(Variedades);
		System.out.println("================================================================================================================ ");
	}
	
	//Getters
	//TODO: Directrices de resolucion en ev

}
