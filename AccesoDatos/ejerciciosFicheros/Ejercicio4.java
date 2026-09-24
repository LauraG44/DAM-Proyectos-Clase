package ejerciciosFicheros;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Ejercicio4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Por favor, indica el nombre o ruta del archivo: ");
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
		
		String line;
		int counter = 0;
		ArrayList<String> lineas = new ArrayList<>();
		try(BufferedReader flr = new BufferedReader (new FileReader(fl))){
			while(((line = flr.readLine()) != null)) {
				lineas.add(line + " - line: " + (counter +1));
				counter++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: No se ha encontrado el archivo.");
		} catch (IOException e1) {
			System.err.println("ERROR: Ha ocurrido un error al leer el archivo.");
		}
		
		int size = lineas.size();
		int limit = Math.min(10, size );

		for (int i = 0; i < limit; i++){
			System.out.println(lineas.get(size - 1 - i));
		}
		sc.close();
	}
}
