package src;

import java.util.Scanner;

public class SistemATM {
    public static Nasabah[] dataNasabah;
    public static Bank[] dataBank;

    public final static String BLUE_BACKGROUND = "\u001B[44m";
    public final static String GREEN_BACKGROUND = "\u001B[42m";
    // public final static String WHITE_BACKGROUND = "\u001B[47m";
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
        System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " TAMBAH DATA NASABAH " + RESET);
        System.out.println(GREEN_BACKGROUND + " 3 " + BLUE_BACKGROUND + " LOGOUT " + RESET);
        System.out.println(GREEN_BACKGROUND + " 0 " + BLUE_BACKGROUND + " KELUAR DARI SISTEM " + RESET);
        
        // System.out.println(dataNasabah[0].getNomorRekening());

        System.out.print(BLUE_BACKGROUND + "PILIHAN ANDA -> " + RESET + " ");
        int pilihanStart = -1;
        try {
            pilihanStart = input.nextInt();
        } catch (Exception e) {
            System.out.println(BLUE_BACKGROUND + "PILIHAN TIDAK VALID, SILAHKAN COBA LAGI " + RESET);
            input.nextLine();
        }
        
        // System.out.println(SearchData.cariNasabahByRekening(123456789).getNama());
        switch (pilihanStart) {
            case 1:
                editDataNasabah(input);
                adminMenu(input);
                break;
            case 2:
                tambahDataNasabah(input);
                adminMenu(input);
                break;
            case 3:
                System.out.println(BLUE_BACKGROUND + " LOGOUT BERHASIL " + RESET);
                startMenuATM();
                break;
            case 0:
                System.out.println(BLUE_BACKGROUND + " TERIMA KASIH TELAH MENGGUNAKAN SISTEM ATM KAMI " + RESET);
                System.exit(0);
                break;
            default:
                System.out.println(BLUE_BACKGROUND + "PILIHAN TIDAK VALID, SILAHKAN COBA LAGI " + RESET);
                adminMenu(input);
        }
    }
        
    public static void editDataNasabah(Scanner input) {
        System.out.print(BLUE_BACKGROUND + "MASUKKAN NOMOR REKENING NASABAH -> " + RESET);
        int nomorRekening = input.nextInt();
        Nasabah nasabahDitemukan = SearchData.cariNasabahByRekening(nomorRekening);
        if (nasabahDitemukan != null) {
            System.out.println(BLUE_BACKGROUND + "EDIT DATA NASABAH " + nasabahDitemukan.getNama() + RESET);
            System.out.println(BLUE_BACKGROUND + "SILAHKAN EDIT DATA NASABAH SESUAI PILIHAN DI BAWAH INI: " + RESET);
            System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " NAMA NASABAH " + RESET);
            System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " NOMOR REKENING NASABAH " + RESET);
            System.out.println(GREEN_BACKGROUND + " 3 " + BLUE_BACKGROUND + " BANK NASABAH " + RESET);
            System.out.println(GREEN_BACKGROUND + " 4 " + BLUE_BACKGROUND + " SALDO NASABAH " + RESET);
            System.out.println(GREEN_BACKGROUND + " 5 " + BLUE_BACKGROUND + " NOMOR KARTU NASABAH " + RESET);
            System.out.println(GREEN_BACKGROUND + " 6 " + BLUE_BACKGROUND + " PIN NASABAH " + RESET);
            System.out.println(GREEN_BACKGROUND + " 0 " + BLUE_BACKGROUND + " KEMBALI " + RESET);

            System.out.print(BLUE_BACKGROUND + "PILIHAN ANDA -> " + RESET + " ");
            int pilihanEdit = input.nextInt();
            System.out.print(BLUE_BACKGROUND + "MASUKKAN DATA BARU -> " + RESET);
            input.nextLine(); // Clear buffer
            switch (pilihanEdit) {
                case 1:
                    String namaBaru = input.nextLine();
                    nasabahDitemukan.setNama(namaBaru);
                    break;
                case 2:
                    int rekeningBaru = input.nextInt();
                    nasabahDitemukan.setNomorRekening(rekeningBaru);
                    break;
                case 3:
                    String namaBankBaru = input.nextLine();
                    Bank bankBaru = SearchData.cariBankByNama(namaBankBaru);
                    if (bankBaru != null) {
                        nasabahDitemukan.setBank(bankBaru);
                    } else {
                        System.out.println(BLUE_BACKGROUND + "BANK TIDAK DITEMUKAN" + RESET);
                    }
                    break;
                case 4:
                    double saldoBaru = input.nextDouble();
                    nasabahDitemukan.setSaldo(saldoBaru);
                    break;
                case 5:
                    int kartuBaru = input.nextInt();
                    nasabahDitemukan.setNomorKartu(kartuBaru);
                    break;
                case 6:
                    int pinBaru = input.nextInt();
                    nasabahDitemukan.setPin(pinBaru);
                    break;
                case 0:
                    return;
                default:
                    System.out.println(BLUE_BACKGROUND + "PILIHAN TIDAK VALID" + RESET);
            }
            System.out.println(BLUE_BACKGROUND + "DATA NASABAH SUDAH DIUPDATE" + RESET);
        } else {
            System.out.println(BLUE_BACKGROUND + "NASABAH DENGAN NOMOR REKENING TERSEBUT TIDAK DITEMUKAN" + RESET);
        }
    }

    public static void tambahDataNasabah(Scanner input) {
        System.out.println(BLUE_BACKGROUND + "TAMBAH DATA NASABAH BARU" + RESET);
        System.out.print(BLUE_BACKGROUND + "MASUKKAN NAMA NASABAH -> " + RESET);
        String nama = input.nextLine();
        if (nama.isEmpty()) {
            nama = input.nextLine();
        }
        System.out.print(BLUE_BACKGROUND + "MASUKKAN NOMOR REKENING NASABAH -> " + RESET);
        try {
            int nomorRekening = input.nextInt();
            System.out.print(BLUE_BACKGROUND + "MASUKKAN NAMA BANK NASABAH -> " + RESET);
            input.nextLine(); // Clear buffer
            String namaBank = input.nextLine();
            Bank bank = SearchData.cariBankByNama(namaBank);
            while (bank == null) {
                System.out.print(BLUE_BACKGROUND + "MASUKKAN NAMA BANK NASABAH -> " + RESET);
                namaBank = input.nextLine();
                bank = SearchData.cariBankByNama(namaBank);
            }
            System.out.print(BLUE_BACKGROUND + "MASUKKAN SALDO AWAL NASABAH -> " + RESET);
            double saldo = input.nextDouble();
            System.out.print(BLUE_BACKGROUND + "MASUKKAN NOMOR KARTU NASABAH -> " + RESET);
            int nomorKartu = input.nextInt();
            System.out.print(BLUE_BACKGROUND + "MASUKKAN PIN NASABAH -> " + RESET);
            int pin = input.nextInt();
            Nasabah nasabahBaru = new Nasabah(nama, nomorRekening, bank, saldo, nomorKartu, pin);
            // Menambahkan nasabah baru ke array dataNasabah
            Nasabah[] nasabahSementara = new Nasabah[SistemATM.dataNasabah.length + 1];
            for (int i = 0; i < SistemATM.dataNasabah.length; i++) {
                nasabahSementara[i] = SistemATM.dataNasabah[i];
            }
            nasabahSementara[SistemATM.dataNasabah.length] = nasabahBaru;
            SistemATM.dataNasabah = nasabahSementara;
        } catch (Exception e) {
            System.out.println(BLUE_BACKGROUND + "INPUT TIDAK VALID, SILAHKAN COBA LAGI " + RESET);
            input.nextLine();
            return;
        }
    }

}