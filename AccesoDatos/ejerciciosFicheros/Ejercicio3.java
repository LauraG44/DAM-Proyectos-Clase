package ejerciciosFicheros;

import java.io.*;
import java.util.Scanner;

public class Ejercicio3 {
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
		}
		
		String line;
		int counter = 1;
		try(BufferedReader flr = new BufferedReader (new FileReader(fl))){
			while(((line = flr.readLine()) != null) && counter <= 10) {
				System.out.println(line + "- line: " + counter);
				counter++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: No se ha encontrado el archivo.");
		} catch (IOException e1) {
			System.err.println("ERROR: Ha ocurrido un error al leer el archivo.");
		}
		sc.close();
	}
}