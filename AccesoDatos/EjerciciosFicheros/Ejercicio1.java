package EjerciciosFicheros;

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
			int caracter;
			
			while((caracter = reader.read()) != -1) {
				count ++;
			}
			System.out.print(count);
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error");
		}
		sc.close();
	}
}