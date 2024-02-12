package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Program {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	ArrayList<Data> karyawanList = new ArrayList<>();
//	ArrayList<KaryawanRaise> karyawanRaises = new ArrayList<>();
	int countManager = 0, countSupervisor = 0, countAdmin = 0;
	
	public void insert() {
		String kodeKaryawan, nama, jenisKelamin, jabatan;
		int number = 1, gaji = 0;
		double bonus = 0;
		ArrayList<String> idList = new ArrayList<>();
		kodeKaryawan = generateId();
		do {
			System.out.print("Input nama karyawan [>= 3] :");
			nama = scan.nextLine();
		}while(nama.length() < 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			jenisKelamin = scan.nextLine();
		} while(!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan"));
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
			if(jabatan.equals("Manager")) {
				countManager++;
			}else if(jabatan.equals("Supervisor")) {
				countSupervisor++;
			}else if(jabatan.equals("Admin")) {
				countAdmin++;
			}
		} while(!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		
		switch(jabatan) {
		case "Manager" :
			gaji = 8000000;
			break;
		case "Supervisor" :
			gaji = 6000000;
			break;
		case "Admin" :
			gaji = 4000000;
			break;
		}
		
		karyawanList.add(new Data(number, kodeKaryawan, nama, jenisKelamin, jabatan, gaji));
		System.out.println("Berhasil menambahkan karyawan dengan id AA-5512");
		
		if(countManager > 3 && countManager % 3 == 1) {
			int count = 0;
			for(int i = 0; i < karyawanList.size(); i++) {
				Data currentKaryawan = karyawanList.get(i);
				if(karyawanList.get(i).jabatan.equals("Manager")) {
					idList.add(karyawanList.get(i).kodeKaryawan);
					bonus = 0.1;
					applyBonus(currentKaryawan, bonus);
					count++;
					if(count == countManager - 1)break;
				}
			}
			System.out.println("Bonus " + bonus * 100 + "% applied to employee " + idList.toString());
		}
		
		if(countSupervisor > 3 && countSupervisor % 3 == 1) {
			int count = 0;
			for(int i = 0; i < karyawanList.size(); i++) {
				Data currentKaryawan = karyawanList.get(i);
				if(karyawanList.get(i).jabatan.equals("Supervisor")) {
					bonus = 0.075;
					applyBonus(currentKaryawan, bonus);
					count++;
					if(count == countSupervisor - 1)break;					
				}
			}
			System.out.println("Bonus " + bonus * 100 + "% applied to employee " + idList.toString());
		}
		 
		if(countAdmin > 3 && countAdmin % 3 == 1) {
			int count = 0;
			for(int i = 0; i < karyawanList.size(); i++) {
				Data currentKaryawan = karyawanList.get(i);
				 if(karyawanList.get(i).jabatan.equals("Admin")) {	
					idList.add(karyawanList.get(i).kodeKaryawan);
					bonus = 0.05;
					applyBonus(currentKaryawan, bonus);
					count++;
					if(count == countAdmin - 1)break;
				}
			}
			System.out.println("Bonus " + bonus * 100 + "% applied to employee " + idList.toString());
		}
		
		System.out.println("Enter to return");
		scan.nextLine();
	}
	
	private int applyBonus(Data currentKaryawan, double bonusPercentage) {
	    double bonus = currentKaryawan.gaji * bonusPercentage;
	    return currentKaryawan.gaji += bonus;
	}


	
	
	public String generateId() {
		StringBuilder kodeKaryawanBuilder = new StringBuilder();
		
		for(int i = 0; i < 2; i++) {
			char randChar = (char) (rand.nextInt(26) + 'A');
			kodeKaryawanBuilder.append(randChar);
		}
			kodeKaryawanBuilder.append('-');
			
		for(int i = 0; i < 4; i++) {
			int randNum = rand.nextInt(10);
			kodeKaryawanBuilder.append(randNum);
		}
		
		return kodeKaryawanBuilder.toString();
	}

	public void view() {
		if(karyawanList.size() == 0) {
			System.out.println("Maaf Tidak Ada Data.");
			System.out.println("Tekan Enter untuk melanjutkan...");
			scan.nextLine();
		} else {
			sort();
			System.out.println("|----|--------------------|------------------------------|---------------|---------------|---------------|");
			System.out.printf("|%-3s |%-20s|%-30s|%-15s|%-15s|%-15s|%n", "No","Kode Karyawan","Nama Karyawan","Jenis Kelamin","Jabatan","Gaji");
			System.out.println("|----|--------------------|------------------------------|---------------|---------------|---------------|");
			for(int i = 0; i < karyawanList.size(); i++) {
				System.out.printf("|%3d |%20s|%30s|%15s|%15s|%15s|%n", karyawanList.get(i).number = i + 1, karyawanList.get(i).kodeKaryawan, 
						karyawanList.get(i).nama, karyawanList.get(i).jenisKelamin, karyawanList.get(i).jabatan, karyawanList.get(i).gaji);
			}
			System.out.println("|----|--------------------|------------------------------|---------------|---------------|---------------|");
		}
		
	}
	
	public void sort() {
		for(int i = 0; i < karyawanList.size(); i++) {
			int min = i;
			for(int j = i+1; j < karyawanList.size(); j++) {
				if(karyawanList.get(min).nama.compareTo(karyawanList.get(j).nama) > 0) {
					min = j;
				}
			}
			Data temp = karyawanList.get(min);
			karyawanList.set(min, karyawanList.get(i));
			karyawanList.set(i, temp);
		}
	}

	public void update() {
		if(karyawanList.size() == 0) {
			System.out.println("Maaf Tidak Ada Data.");
			System.out.println("Tekan Enter untuk melanjutkan...");
			scan.nextLine();
		} else {
			view();
			int num;
			boolean check = false;
			String newNama, newJenisKelamin, newJabatan;
			
			System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
			num = scan.nextInt();
			scan.nextLine();
			
			do {
				System.out.print("Input nama karyawan [>= 3] :");
				newNama = scan.nextLine();
				for(int i = 0 ; i < karyawanList.size(); i++) {
					if(karyawanList.get(i).number == num) {
						karyawanList.get(i).nama = newNama;
					}
				}
			}while(newNama.length() < 3);
			
			do {
				System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
				newJenisKelamin = scan.nextLine();
				for(int i = 0; i < karyawanList.size(); i++) {
					if(karyawanList.get(i).number == num) {
						karyawanList.get(i).jenisKelamin = newJenisKelamin;
					}
				}
			} while(!newJenisKelamin.equals("Laki-laki") && !newJenisKelamin.equals("Perempuan"));
			
			do {
				System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
				newJabatan = scan.nextLine();
				for(int i = 0; i < karyawanList.size(); i++) {
					if(karyawanList.get(i).number == num) {
						karyawanList.get(i).jabatan = newJabatan;
					}
				}
			} while(!newJabatan.equals("Manager") && !newJabatan.equals("Supervisor") && !newJabatan.equals("Admin"));

			for(int i = 0; i < karyawanList.size(); i++) {
				if(karyawanList.get(i).number == num) {
					System.out.println("Berhasil mengupdate karyawan dengan id " + karyawanList.get(i).kodeKaryawan);
					System.out.println("Enter to return");
				} 
			}
		}
		
	}

	public void delete() {
		if(karyawanList.size() == 0) {
			System.out.println("Maaf Tidak Ada Data.");
			System.out.println("Tekan Enter untuk melanjutkan...");
			scan.nextLine();
		} else {
			view();
			boolean check = false;
			int deleteNum;
			do {
				System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
				deleteNum = scan.nextInt();
				scan.nextLine();
				
				for(int i = 0; i < karyawanList.size(); i++) {
					if(karyawanList.get(i).number == deleteNum) {
						check = true;
						karyawanList.remove(i);
					}
				}
			} while(!check);
		}
		
	}

}
