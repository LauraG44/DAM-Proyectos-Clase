package ejerciciosFicheros;

import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int count = 0;
		
		System.out.print("Por favor, introduce el nombre o la ruta del archivo a escanear: ");
		String route = sc.nextLine();
		File newFile = new File(route);
		
		try(FileReader reader = new FileReader(newFile)){			
			while(reader.read() != -1) {
				count ++;
			}
		System.out.print("El archivo contiene: " + count);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR - No se ha encontrado el archivo: " + e.getMessage());
		} catch (IOException e1) {
			System.out.println("ERROR: No se ha podido leer el archivo correctamente" + e1.getMessage());
		}
		sc.close();
	}
}