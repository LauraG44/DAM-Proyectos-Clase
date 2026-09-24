package ejerciciosFicheros;

import java.io.*;

public class VerInf {  
	public static void main(String[] args) {  
		System.out.println("Infromación sobre el fichero:");
		
		File f = new File("AccesoDatos/ejerciciosFicheros/popo.txt");
		if (f.exists()){    
		System.out.println("Nombre del fichero:  "+f.getName());    
		System.out.println("Ruta              :  "+f.getAbsolutePath()); 
		System.out.println("Tamaño            :  "+f.length());
		} 
	}
}

