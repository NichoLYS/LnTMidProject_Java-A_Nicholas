package main;

import java.util.Scanner;

public class Menu {
	Scanner scan = new Scanner(System.in);
	Program pg = new Program();
	public Menu() {
		while(true) {
			System.out.println("1. Insert Data Karyawan");
			System.out.println("2. View Data Karyawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data Karyawan");
			System.out.println("5. Exit");
			System.out.println(">> ");
			int choice = scan.nextInt();
			scan.nextLine();
			switch(choice) {
			case 1:
				pg.insert();
				break;
			case 2:
				pg.view();
				break;
			case 3:
				pg.update();
				break;
			case 4:
				pg.delete();
				break;
			case 5:
				System.out.println("Thank You :)");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Menu main = new Menu();

	}

}
