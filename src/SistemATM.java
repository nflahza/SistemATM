package src;

import java.util.Scanner;

public class SistemATM {
    public static Nasabah[] dataNasabah = new Nasabah[1000];
    public static Bank[] dataBank = new Bank[1000];

    public final static String BLUE_BACKGROUND = "\u001B[44m";
    public final static String GREEN_BACKGROUND = "\u001B[42m";
    public final static String YELLOW_BACKGROUND = "\u001B[43m";
    public final static String RED_BACKGROUND = "\u001B[41m";
    public final static String RESET = "\u001B[0m";

    public static int tanggal = 16;
    public static int bulan = 12;
    public static int tahun = 2025;
    public static String tanggalString = tanggalToString(tanggal, bulan, tahun);

    public static void startMenuATM(Scanner input) {
        tanggalString = tanggalToString(tanggal, bulan, tahun);
        System.out.println(BLUE_BACKGROUND + " ┌────────────────────────────────────────────┐ " + RESET);
        System.out.println(BLUE_BACKGROUND + " │               MENU SISTEM ATM              │ " + RESET);
        System.out.println(BLUE_BACKGROUND + " └────────────────────────────────────────────┘ " + RESET + "\n");
        System.out.println(BLUE_BACKGROUND + "Tanggal: " + tanggalString + RESET + "\n");
        System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " Login Admin " + RESET);
        System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " Login Pengguna " + RESET);
        System.out.println(GREEN_BACKGROUND + " 0 " + BLUE_BACKGROUND + " Keluar Dari Sistem " + RESET);
        System.out.print("\nPilihan Anda -> ");
        
        int pilihanStart = -1;
        
        
        try {
            pilihanStart = input.nextInt();
            
        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Terjadi kesalahan, silahkan coba lagi " + RESET);
            startMenuATM(new Scanner(System.in));
            return;
        }
        
        System.out.println("");
        switch (pilihanStart) {
            case 1:
                System.out.println(BLUE_BACKGROUND + "Login Admin" + RESET);
                adminLogin(input);
                break;
            case 2:
                System.out.println(BLUE_BACKGROUND + "Login Pengguna" + RESET);
                userLogin(input);
                break;
            case 0:
                System.out.println(BLUE_BACKGROUND + "Terima Kasih telah menggunakan Sistem ATM kami " + RESET);
                input.close();
                System.exit(0);
                return;
            default:
                System.out.println(RED_BACKGROUND + "Pilihan Tidak Valid, Silahkan Coba Lagi " + RESET);
                startMenuATM(input);
                break;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Inisialisai data sampel
        DataAwal.initialize();
        
        startMenuATM(input);
        
    }
    public static void userLogin(Scanner input) {
        long nomorKartu;
        int pin;
        int attempts = 0;
        try {
            System.out.print("Masukkan Nomor Kartu: ");
            
            try {
                nomorKartu = input.nextLong();
            } catch (Exception e) {
                System.out.println(RED_BACKGROUND + "Nomor Kartu Tidak Valid, Silahkan Coba Lagi " + RESET);
                startMenuATM(new Scanner(System.in));
                return;
            }
            if (nomorKartu == -1) {
                startMenuATM(new Scanner(System.in));
                return;
            }
            // nomorKartu = input.nextInt();

            Nasabah nasabah = SearchData.cariNasabahByKartu(nomorKartu);
            
            while (nasabah == null) {
                System.out.println(RED_BACKGROUND + "Nomor Kartu Tidak Ditemukan, Silahkan Coba Lagi " + RESET);
                System.out.print("Masukkan Nomor Kartu: ");

                nomorKartu = input.nextLong();
                if (nomorKartu == -1) {
                    startMenuATM(new Scanner(System.in));
                    return;
                }
                nasabah = SearchData.cariNasabahByKartu(nomorKartu);
            }

            System.out.print("Masukkan PIN: ");
            pin = input.nextInt();


            while (nasabah.getPin() != pin) {
                attempts++;
                if (attempts == 3) {
                    System.out.println(RED_BACKGROUND + "Akses Ditolak, Kartu Anda Telah Diblokir " + RESET);
                    nasabah.blokirKartu();
                    startMenuATM(input);
                    return;
                }
                System.out.println(RED_BACKGROUND + "Pin Salah, Silahkan Coba Lagi " + RESET);
                System.out.println(RED_BACKGROUND + "Sisa Percobaan: " + (3 - attempts) + RESET);
                System.out.print("Masukkan PIN: ");
                pin = input.nextInt();
                if (pin == -1) {
                    startMenuATM(input);
                    return;
                }
            }

            System.out.println("");
            MenuUser.userMenu(input, nasabah);
                
            
        } catch (Exception e) {
            System.out.println(BLUE_BACKGROUND + "TERJADI KESALAHAN, SILAHKAN COBA LAGI" + RESET);
            startMenuATM(new Scanner(System.in));
        }
        
    }

    

    public static void adminLogin(Scanner input) {
        String adminName = "admin";
        String adminPass = "admin123";
        
        try {
            System.out.print("Username Pass -> ");
            String username = input.next();
            String password = input.next();
            
            if (username.equals(adminName) && password.equals(adminPass)) {
                System.out.println(BLUE_BACKGROUND + "Login Admin Berhasil" + RESET);
                MenuAdmin.adminMenu(input);
            } else {
                System.out.println(BLUE_BACKGROUND + "Username atau Password salah, Silahkan Coba Lagi " + RESET);
                adminLogin(input);
            }
        } catch (Exception e) {
            startMenuATM(new Scanner(System.in));
        }
    }

    public static String tanggalToString(int tanggal, int bulan, int tahun) {
        String stringBulan = "";
        switch (bulan) {
            case 1:
                stringBulan = "Januari";
                break;
            case 2:
                stringBulan = "Februari";
                break;
            case 3:
                stringBulan = "Maret";
                break;
            case 4:
                stringBulan = "April";
                break;
            case 5:
                stringBulan = "Mei";
                break;
            case 6:
                stringBulan = "Juni";
                break;
            case 7:
                stringBulan = "Juli";
                break;
            case 8:
                stringBulan = "Agustus";
                break;
            case 9:
                stringBulan = "September";
                break;
            case 10:
                stringBulan = "Oktober";
                break;
            case 11:
                stringBulan = "November";
                break;
            case 12:
                stringBulan = "Desember";
                break;
            default:
                break;
        }
        return tanggal + " " + stringBulan + " " + tahun;
    }
    
    
}