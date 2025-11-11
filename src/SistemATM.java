package src;

import java.sql.Blob;
import java.util.Scanner;

public class SistemATM {
    static Nasabah[] dataNasabah;
    static Bank[] dataBank;

    public final static String BLUE_BACKGROUND = "\u001B[44m";
    public final static String GREEN_BACKGROUND = "\u001B[42m";
    // publoc final static String WHITE_BACKGROUND = "\u001B[47m";
    public final static String RESET = "\u001B[0m";

    public static void startMenuATM() {
        System.out.println(BLUE_BACKGROUND + " ┌────────────────────────────────────────────┐ " + RESET);
        System.out.println(BLUE_BACKGROUND + " │               MENU SISTEM ATM              │ " + RESET);
        System.out.println(BLUE_BACKGROUND + " └────────────────────────────────────────────┘ " + RESET + "\n");
        System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " LOGIN ADMIN " + RESET);
        System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " LOGIN PENGGUNA " + RESET);
        System.out.println(GREEN_BACKGROUND + " 0 " + BLUE_BACKGROUND + " KELUAR DARI SISTEM " + RESET);
        System.out.print(BLUE_BACKGROUND + "PILIHAN ANDA -> " + RESET + " ");
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Inisialisai data sampel
        DataAwal.initialize();
        
        startMenuATM();
        int pilihanStart = -1;
        
        // System.out.print(RESET);
        // System.out.println(pilihanStart);
        while (pilihanStart < 0 || pilihanStart > 2) {
            try {
                pilihanStart = input.nextInt();
            } catch (Exception e) {
                System.out.println(BLUE_BACKGROUND + "PILIHAN TIDAK VALID, SILAHKAN COBA LAGI " + RESET);
                input.nextLine();
            }
            switch (pilihanStart) {
                case 1:
                    adminLogin(input);
                    break;
                case 2:
                    System.out.print(BLUE_BACKGROUND + "Masukkan Nomor Kartu: " + RESET);
                    int nomorKartu = input.nextInt();
                    System.out.print(BLUE_BACKGROUND + "Masukkan PIN: " + RESET);
                    int pin = input.nextInt();
                    break;
                case 0:
                    System.out.println(BLUE_BACKGROUND + " TERIMA KASIH TELAH MENGGUNAKAN SISTEM ATM KAMI " + RESET);
                    System.exit(0);
                    break;
                }
        }
        input.close();
    }
    public static void userLogin(Scanner input) {
        int nomorKartu = 0;
        int pin = 0;
        try {
            System.out.print(BLUE_BACKGROUND + "Masukkan Nomor Kartu: " + RESET);
            nomorKartu = input.nextInt();
            System.out.print(BLUE_BACKGROUND + "Masukkan PIN: " + RESET);
            pin = input.nextInt();
        } catch (Exception e) {
            System.out.println(BLUE_BACKGROUND + "NOMOR KARTU ATAU PIN" + RESET);
        }
    }

    public static void userMenu(Scanner input, Nasabah nasabah) {
        System.out.println(BLUE_BACKGROUND + " ┌────────────────────────────────────────────┐ " + RESET);
        System.out.println(BLUE_BACKGROUND + " │               MENU PENGGUNA ATM            │ " + RESET);
        System.out.println(BLUE_BACKGROUND + " └────────────────────────────────────────────┘ " + RESET + "\n");
        System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " TRANSFER                     GANTI PIN " + GREEN_BACKGROUND + " 4 ");
        System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " TARIK TUNAI          RIWAYAT TRANSAKSI " + GREEN_BACKGROUND + " 5 ");
        System.out.println(GREEN_BACKGROUND + " 3 " + BLUE_BACKGROUND + " CEK SALDO                       KELUAR " + GREEN_BACKGROUND + " 0 ");
        
        System.out.print(BLUE_BACKGROUND + "PILIHAN ANDA -> " + RESET + " ");
        int pilihanStart = -1;
        try {
            pilihanStart = input.nextInt();
        } catch (Exception e) {
            System.out.println(BLUE_BACKGROUND + "PILIHAN TIDAK VALID, SILAHKAN COBA LAGI " + RESET);
            input.nextLine();
        }
        
        System.out.println(pilihanStart);
        
    }

    public static void adminLogin(Scanner input) {
        String username = "";
        String password = "";
        try {
            System.out.print(BLUE_BACKGROUND + "Username Password " + RESET);
            username = input.next();
            password = input.next();
            System.out.println("");
        } catch (Exception e) {
            System.out.println(BLUE_BACKGROUND + "USERNAME ATAU PASSWORD TIDAK VALID, SILAHKAN COBA LAGI " + RESET);
        }
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println(BLUE_BACKGROUND + "LOGIN ADMIN BERHASIL" + RESET);
            adminMenu(input);
        } else {
            System.out.println(BLUE_BACKGROUND + "USERNAME ATAU PASSWORD SALAH, SILAHKAN COBA LAGI " + RESET);
            adminLogin(input);
        }
    }

    public static void adminMenu(Scanner input) {
        System.out.println(BLUE_BACKGROUND + " ┌────────────────────────────────────────────┐ " + RESET);
        System.out.println(BLUE_BACKGROUND + " │               MENU ADMIN ATM               │ " + RESET);
        System.out.println(BLUE_BACKGROUND + " └────────────────────────────────────────────┘ " + RESET + "\n");
        System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " EDIT DATA NASABAH " + RESET);
        System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " LOGOUT " + RESET);
        System.out.println(GREEN_BACKGROUND + " 0 " + BLUE_BACKGROUND + " KELUAR DARI SISTEM " + RESET);
        System.out.print(BLUE_BACKGROUND + "PILIHAN ANDA -> " + RESET + " ");
        int pilihanStart = -1;
        try {
            pilihanStart = input.nextInt();
        } catch (Exception e) {
            System.out.println(BLUE_BACKGROUND + "PILIHAN TIDAK VALID, SILAHKAN COBA LAGI " + RESET);
            input.nextLine();
        }
        
        System.out.println(pilihanStart);
        
    }



}