package src;

import java.util.Scanner;

public class SistemATM {
    public static Nasabah[] dataNasabah = new Nasabah[1000];
    public static Bank[] dataBank = new Bank[1000];

    public static int tanggal = 16;
    public static int bulan = 12;
    public static int tahun = 2025;
    public static String tanggalString = tanggalToString(tanggal, bulan, tahun);

    public static void startMenuATM(Scanner input) {
        tanggalString = tanggalToString(tanggal, bulan, tahun);
        System.out.println(" +--------------------------------------------+ ");
        System.out.println(" |               MENU SISTEM ATM              | ");
        System.out.println(" +--------------------------------------------+ " + "\n");
        System.out.println("Tanggal: " + tanggalString + "\n");
        System.out.println(" 1  Login Admin ");
        System.out.println(" 2  Login Pengguna ");
        System.out.println(" 0  Keluar Dari Sistem ");
        System.out.print("\nPilihan Anda -> ");

        int pilihanStart = -1;

        try {
            pilihanStart = input.nextInt();

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan, silahkan coba lagi ");
            startMenuATM(new Scanner(System.in));
            return;
        }

        System.out.println("");
        switch (pilihanStart) {
            case 1:
                System.out.println("Login Admin");
                adminLogin(input);
                break;
            case 2:
                System.out.println("Login Pengguna");
                userLogin(input);
                break;
            case 0:
                System.out.println("Terima Kasih telah menggunakan Sistem ATM kami ");
                input.close();
                System.exit(0);
                return;
            default:
                System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi ");
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

            nomorKartu = input.nextLong();
            if (nomorKartu == -1) {
                startMenuATM(new Scanner(System.in));
                return;
            }

            Nasabah nasabah = SearchData.cariNasabahByKartu(nomorKartu);
            if (nasabah.diblokir) {
                System.out.println("Kartu Anda Diblokir, Silahkan Hubungi Admin Bank\n");
                startMenuATM(new Scanner(System.in));
                return;
            } else {
                System.out.print("Masukkan PIN: ");
                pin = input.nextInt();

                while (nasabah.pin != pin) {
                    attempts++;
                    if (attempts == 3) {
                        System.out.println("Akses Ditolak, Kartu Anda Telah Diblokir\n");
                        nasabah.blokirKartu();
                        startMenuATM(input);
                        return;
                    }
                    System.out.println("Pin Salah, Silahkan Coba Lagi ");
                    System.out.println("Sisa Percobaan: " + (3 - attempts));
                    System.out.print("Masukkan PIN: ");
                    pin = input.nextInt();
                    if (pin == -1) {
                        startMenuATM(input);
                        return;
                    }
                }

                System.out.println("");
                MenuUser.userMenu(input, nasabah);

            }

        } catch (NullPointerException e) {
            System.out.println("Nasabah Tidak Ditemukan, Silahkan Coba Lagi\n");
            startMenuATM(new Scanner(System.in));
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan, Silahkan Coba Lagi\n");
            startMenuATM(new Scanner(System.in));
        }

    }

    public static void adminLogin(Scanner input) {
        String adminPass = "admin123";
        System.out.println("Masukkan Password Admin (masukkan -1 untuk batal)");

        try {
            System.out.print("Password Admin -> ");
            String password = input.next();

            if (password.equals(adminPass)) {
                System.out.println("Login Admin Berhasil");
                MenuAdmin.adminMenu(input);
            } else if (password.equals("-1")) {
                startMenuATM(input);
                return;
            } else {
                System.out.println("Password salah, Silahkan Coba Lagi ");
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

    public static void removeNasabah(Nasabah nasabah) {
        for (int i = 0; i < dataNasabah.length; i++) {
            if (dataNasabah[i] != null && dataNasabah[i].equals(nasabah)) {
                for (int j = i; j < dataNasabah.length - 1; j++) {
                    dataNasabah[j] = dataNasabah[j + 1];
                }
                dataNasabah[dataNasabah.length - 1] = null;
                break;
            }
        }
    }
}