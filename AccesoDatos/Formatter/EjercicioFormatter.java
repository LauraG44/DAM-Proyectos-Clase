/*package ejerciciosFicheros;

import java.util.Formatter;
import java.util.Scanner;
import java.io.*;

public class EjercicioFormatter {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		/*
		 Se crean 2 scanners, 1 para el fichero de Cuentas y otro para el fichero de Movimientos, se va leyendo
		 en ambos a la vez y si el código (almacenado en una variable hasta que cambie) coincide,
		 se altera "Importe Bono" con el dato dado en "Importe Operación".

		
		System.out.print("Por favor, indica el nombre o ruta del fichero de Cuentas: ");
		String fileNameCuentas = sc.nextLine();
		System.out.print("Por favor, indica el nombre o ruta del fichero de Movimientos: ");
		String fileNameMovimientos = sc.nextLine();
		
		File cuentasFile = new File(fileNameCuentas);
		File movimientosFile =  new File(fileNameMovimientos);
		File neuFile = new File("CuentasClientes_nuevo.txt");
		
		try(Scanner readerCuentas = new Scanner(cuentasFile);
			Scanner readerMovimientos = new Scanner(movimientosFile);
			Formatter neuCuentas = new Formatter(neuFile)){
			
			neuCuentas.format("%-6s","%-30s","%-15n" ,"Código", "Nombre", "Importe Bono");
			neuCuentas.format("%-6s","%-30s","%-15n" ,"---------", "----------", "---------");
				
			while (readerCuentas.hasNext() && readerMovimientos.hasNext()) {
				String lineCuentas = readerCuentas.nextLine();
				String lineMovimientos = readerMovimientos.nextLine();
				
				Scanner scLineCuentas = new Scanner(lineCuentas).useDelimiter("\t");
				Scanner scLineMovimientos = new Scanner(lineMovimientos).useDelimiter("\t");
					
				if(readerCuentas.hasNextInt() == readerMovimientos.hasNextInt()) {
					//Se guardan ambos para no adelantar el Scanner sin querer
					int codeCuentas = scLineCuentas.nextInt();
					int codeMovimientos = scLineMovimientos.nextInt();
					//Comprobamos códigos
					if (codeCuentas == codeMovimientos)
				} else {
						
				}
				scLineCuentas.close();
			}
			
			
		}catch (FileNotFoundException e) {
			System.err.print(e.getMessage());
		}
		sc.close();
	}
}
*/
