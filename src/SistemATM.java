package src;

import java.util.Scanner;

public class SistemATM {
    static Nasabah[] dataNasabah;
    static Bank[] dataBank;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Inisialisai data awal (sampel)
        DataAwal.initialize();
        
        final String BLUE_BACKGROUND = "\u001B[44m";
        final String GREEN_BACKGROUND = "\u001B[42m";
        final String RESET = "\u001B[0m";
        
        System.out.println(BLUE_BACKGROUND + " ┌────────────────────────────────────────────┐ " + RESET);
        System.out.println(BLUE_BACKGROUND + " │               MENU SISTEM ATM              │ " + RESET);
        System.out.println(BLUE_BACKGROUND + " └────────────────────────────────────────────┘ " + RESET + "\n");
        System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " LOGIN ADMIN " + RESET);
        System.out.println(GREEN_BACKGROUND + " 0 " + BLUE_BACKGROUND + " KELUAR DARI SISTEM " + RESET);
        System.out.print("PILIHAN ANDA -> ");
        int pilihanStart = input.nextInt();
        
        System.out.println(pilihanStart);
        input.close();
    }
}