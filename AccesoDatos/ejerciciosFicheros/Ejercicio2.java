package ejerciciosFicheros;

import java.util.Scanner;
import java.io.*;

public class Ejercicio2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Por favor, introduzca el nombre o ruta del fichero: ");
		String route = sc.nextLine();
		
		File fl = new File(route);
		
		if(!fl.exists()) {
			System.err.println("El fichero indicado no existe.");
			sc.close();
			return;
		} else if (fl.length() == 0) {
			System.err.println("El fichero está vacío.");
			sc.close();
			return;
		}
		
		int counter = 1;
		String line;
		try (BufferedReader flr = new BufferedReader (new FileReader(fl))){
			
			while((line = flr.readLine()) != null) {
				System.out.println(line + " - line: " + counter);
				counter++;	
			}
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error al leer el archivo.");
		}
		sc.close();
	}
}
