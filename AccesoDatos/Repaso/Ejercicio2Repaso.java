package Repaso;

import java.util.Scanner;
public class Ejercicio2Repaso {
	public static void main(String[] args) {
		int age;
		Scanner sc = new Scanner(System.in);
		System.out.print("Por favor, ingrese su edad");
		age = sc.nextInt();
		if(age <= 12 && age > 0) {
			System.out.print("La entrada de niños cuesta 12€");
		}	else if(age > 13 && age <= 20){
			System.out.print("La entrada de jóvenes cuesta 20€");
		}	else {
			System.out.print("La entrada cuesta 30€");
		}
		sc.close();
	}
}