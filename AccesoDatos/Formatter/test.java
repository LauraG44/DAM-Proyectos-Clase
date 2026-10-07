package Formatter;

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

        try (Scanner readerCuentas = new Scanner(cuentasFile);
             Scanner readerMovimientos = new Scanner(movimientosFile);
             Formatter neuCuentas = new Formatter(neuFile)) {

            // --- 1. SALTAR LAS CABECERAS DE LOS ARCHIVOS DE ORIGEN ---
            // Leemos y descartamos las 2 primeras líneas de Cuentas si existen
            if (readerCuentas.hasNextLine()) readerCuentas.nextLine();
            if (readerCuentas.hasNextLine()) readerCuentas.nextLine();

            // Leemos y descartamos las 2 primeras líneas de Movimientos si existen
            if (readerMovimientos.hasNextLine()) readerMovimientos.nextLine();
            if (readerMovimientos.hasNextLine()) readerMovimientos.nextLine();

            // --- 2. ESCRIBIR LA NUEVA CABECERA EN EL ARCHIVO DE SALIDA ---
            neuCuentas.format("%-10s %-30s %-15s%n", "Código", "Nombre", "Importe Bono");
            neuCuentas.format("%-10s %-30s %-15s%n", "---------", "------------------------------", "---------");

            // --- 3. PROCESAR LOS DATOS REALES ---
            // Leemos la primera línea de datos (si existen)
            String lineCuentas = readerCuentas.hasNextLine() ? readerCuentas.nextLine().trim() : "";
            String lineMovimientos = readerMovimientos.hasNextLine() ? readerMovimientos.nextLine().trim() : "";

            while (!lineCuentas.isEmpty()) {
                Scanner scLineaCuentas = new Scanner(lineCuentas).useDelimiter("\\s+");

                if (!scLineaCuentas.hasNextInt()) {
                    scLineaCuentas.close();
                    break;
                }

                int codeCuentas = scLineaCuentas.nextInt();
                String name = scLineaCuentas.next();
                int bonusOriginal = scLineaCuentas.nextInt();
                scLineaCuentas.close();

                int importeOperacion = 0;

                // Si hay movimientos, comprobamos si corresponden a esta cuenta
                if (!lineMovimientos.isEmpty()) {
                    Scanner scLineaMovimientos = new Scanner(lineMovimientos).useDelimiter("\\s+");
                    if (scLineaMovimientos.hasNextInt()) {
                        int codeMovimientos = scLineaMovimientos.nextInt();

                        // SI COINCIDEN: Sumamos el importe y avanzamos el archivo de movimientos
                        if (codeCuentas == codeMovimientos) {
                            importeOperacion = scLineaMovimientos.nextInt();
                            lineMovimientos = readerMovimientos.hasNextLine() ? readerMovimientos.nextLine().trim() : "";
                        }
                        // SI EL MOVIMIENTO TIENE UN CÓDIGO MENOR: Significa que ese movimiento no existe en cuentas (se descarta)
                        else if (codeMovimientos < codeCuentas) {
                            while (codeMovimientos < codeCuentas && readerMovimientos.hasNextLine()) {
                                lineMovimientos = readerMovimientos.nextLine().trim();
                                Scanner scAux = new Scanner(lineMovimientos).useDelimiter("\\s+");
                                codeMovimientos = scAux.hasNextInt() ? scAux.nextInt() : codeCuentas + 1;
                                scAux.close();
                            }
                            // Volvemos a comprobar tras adelantar movimientos
                            if (codeCuentas == codeMovimientos) {
                                Scanner scAux = new Scanner(lineMovimientos).useDelimiter("\\s+");
                                scAux.nextInt(); // saltar codigo
                                importeOperacion = scAux.nextInt();
                                scAux.close();
                                lineMovimientos = readerMovimientos.hasNextLine() ? readerMovimientos.nextLine().trim() : "";
                            }
                        }
                    }
                    scLineaMovimientos.close();
                }

                // Guardamos SIEMPRE la cuenta (con o sin movimientos aplicados)
                int nuevoBonus = bonusOriginal + importeOperacion;
                neuCuentas.format("%-10d %-30s %-15d%n", codeCuentas, name, nuevoBonus);

                // Avanzamos siempre la línea de cuentas
                lineCuentas = readerCuentas.hasNextLine() ? readerCuentas.nextLine().trim() : "";
            }


            System.out.println("Proceso finalizado. Archivo 'CuentasClientes_nuevo.txt' generado con éxito.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró uno de los archivos -> " + e.getMessage());
        }
        sc.close();
    }
}
