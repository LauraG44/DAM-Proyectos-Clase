package ejerciciosFicheros;

import java.io.*;
public class VerDir {
	public static void main(String[] args) {
		System.out.println("Ficheros en el directorio actual:");
		File f = new File(".");   //--------------------------------- File f = new File(“d:\\db”);
		String[] archivos = f.list();
		for (int i = 0; i < archivos.length; i++) {
			System.out.println(archivos[i]);
		}
	}
}

