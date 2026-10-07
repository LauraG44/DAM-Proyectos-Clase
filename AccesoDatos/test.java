
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Por favor, indica el nombre o ruta del fichero de Cuentas: ");
        String fileNameCuentas = sc.nextLine();
        System.out.print("Por favor, indica el nombre o ruta del fichero de Movimientos: ");
        String fileNameMovimientos = sc.nextLine();
        
        File cuentasFile = new File(fileNameCuentas);
        File movimientosFile = new File(fileNameMovimientos);
        File neuFile = new File("CuentasClientes_nuevo.txt");
        
        // Abrimos los recursos de forma segura en el try-with-resources
        try (Scanner readerCuentas = new Scanner(cuentasFile);
             Scanner readerMovimientos = new Scanner(movimientosFile);
             Formatter neuCuentas = new Formatter(neuFile)) {
            
            // 1. Corregido el formato de cabecera: Todo en un solo String de formato
            // %-10s -> String alineado a la izquierda con 10 caracteres de ancho
            // %-30s -> String alineado a la izquierda con 30 caracteres de ancho
            // %n    -> Salto de línea independiente de la plataforma
            neuCuentas.format("%-10s %-30s %-15s%n", "Código", "Nombre", "Importe Bono");
            neuCuentas.format("%-10s %-30s %-15s%n", "---------", "------------------------------", "---------");
            
            // 2. Bucle para iterar mientras AMBOS archivos tengan líneas que leer
            while (readerCuentas.hasNextLine() && readerMovimientos.hasNextLine()) {
                String lineCuentas = readerCuentas.nextLine();
                String lineMovimientos = readerMovimientos.nextLine();
                
                // Procesamos las líneas actuales con Scanners independientes
                Scanner scLineaCuentas = new Scanner(lineCuentas).useDelimiter("\t");
                Scanner scLineaMovimientos = new Scanner(lineMovimientos).useDelimiter("\t");
                
                if (scLineaCuentas.hasNextInt() && scLineaMovimientos.hasNextInt()) {
                    // Guardamos el código una sola vez para no "adelantar" el Scanner sin querer
                    int codeCuentas = scLineaCuentas.nextInt();
                    int codeMovimientos = scLineaMovimientos.nextInt();
                    
                    if (codeCuentas == codeMovimientos) {
                        String name = scLineaCuentas.next();
                        
                        int bonusOriginal = scLineaCuentas.nextInt();
                        int importeOperacion = scLineaMovimientos.nextInt();
                        
                        int nuevoBonus = bonusOriginal + importeOperacion;
                        
                        // 3. Corregido el formato de datos:
                        // %-10d -> Entero alineado a la izquierda, ancho 10
                        // %-30s -> Texto alineado a la izquierda, ancho 30
                        // %-15d -> Entero alineado a la izquierda, ancho 15
                        neuCuentas.format("%-10d %-30s %-15d%n", codeCuentas, name, nuevoBonus);
                    } else {
                        // Aquí puedes gestionar qué pasa si los códigos no coinciden
                        System.out.println("Aviso: Desincronización de códigos (" + codeCuentas + " vs " + codeMovimientos + ")");
                    }
                }
                
                // Importante cerrar los scanners de línea en cada iteración
                scLineaCuentas.close();
                scLineaMovimientos.close();
            }
            
            System.out.println("Proceso finalizado. Archivo 'CuentasClientes_nuevo.txt' generado con éxito.");
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró uno de los archivos -> " + e.getMessage());
        }
        sc.close();
    }
}
