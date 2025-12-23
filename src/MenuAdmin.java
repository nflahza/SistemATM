package src;

import java.util.Scanner;

public class MenuAdmin {

    static int tanggal = SistemATM.tanggal;
    static int bulan = SistemATM.bulan;
    static int tahun = SistemATM.tahun;

    public static void adminMenu(Scanner input) {
        String tanggalString = SistemATM.tanggalString;

        System.out.println(" +--------------------------------------------+ ");
        System.out.println(" |               MENU ADMIN ATM               | ");
        System.out.println(" +--------------------------------------------+ " + "\n");
        System.out.println("Tanggal: " + tanggalString + "\n");
        System.out.println(" 1 " + " Lihat Semua Data Nasabah ");
        System.out.println(" 2 " + " Cari Data Nasabah ");
        System.out.println(" 3 " + " Edit Data Nasabah ");
        System.out.println(" 4 " + " Tambah Data Nasabah ");
        System.out.println(" 5 " + " Hapus Data Nasabah ");
        System.out.println(" 6 " + " Ganti Tanggal ");
        System.out.println(" 7 " + " Logout ");
        System.out.println(" 0 " + " Keluar Dari Sistem ");

        System.out.print("\nPilihan Anda -> ");
        int pilihanStart = -1;
        try {
            pilihanStart = input.nextInt();
        } catch (Exception e) {
            System.out.println("Pilihan tidak valid, silahkan coba lagi ");
            adminMenu(input);
        }

        System.out.println("");
        switch (pilihanStart) {
            case 1:
                lihatDataNasabah(input);
                adminMenu(input);
                break;
            case 2:
                Nasabah nasabah = cariDataNasabah(input);
                tampilkanDataNasabah(nasabah);
                adminMenu(input);
                break;
            case 3:
                editDataNasabah(input);
                adminMenu(input);
                break;
            case 4:
                tambahDataNasabah(input);
                adminMenu(input);
                break;
            case 5:
                hapusDataNasabah(input);
                adminMenu(input);
                break;
            case 6:
                gantiTanggal(input);
                adminMenu(input);
                break;
            case 7:
                System.out.println("Logout Berhasil");
                SistemATM.startMenuATM(input);
                break;
            case 0:
                System.out.println("Terima Kasih Telah Menggunakan Sistem ATM Kami");
                input.close();
                System.exit(0);
                return;
            default:
                System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi\n");
                adminMenu(input);
        }
    }

    public static void lihatDataNasabah(Scanner input) {
        int max_widthNama = 14;
        int max_widthRekening = 16;
        int max_widthBank = 6;
        int max_widthSaldo = 7;
        int max_widthKartu = 13;
        int max_widthPin = 5;

        for (Nasabah nasabah : SistemATM.dataNasabah) {
            if (nasabah != null) {
                if (nasabah.nama.length() > max_widthNama) {
                    max_widthNama = nasabah.nama.length();
                }
                if (String.valueOf(nasabah.nomorRekening).length() > max_widthRekening) {
                    max_widthRekening = String.valueOf(nasabah.nomorRekening).length();
                }
                if (nasabah.bank.nama.length() > max_widthBank) {
                    max_widthBank = nasabah.bank.nama.length();
                }
                if (String.valueOf(nasabah.saldo).length() > max_widthSaldo) {
                    max_widthSaldo = String.valueOf(nasabah.saldo).length() + ((int) Math.log10(nasabah.saldo) / 3) + 2;
                }
                if (String.valueOf(nasabah.nomorKartu).length() > max_widthKartu) {
                    max_widthKartu = String.valueOf(nasabah.nomorKartu).length();
                }
                if (String.valueOf(nasabah.pin).length() > max_widthPin) {
                    max_widthPin = String.valueOf(nasabah.pin).length();
                }
            }
        }
        max_widthNama += 2;
        max_widthRekening += 2;
        max_widthBank += 2;
        max_widthSaldo += 4;
        max_widthKartu += 2;
        max_widthPin += 2;

        String line = String.format(
                "+%" + (max_widthNama + 2) + "s+%" + (max_widthRekening + 2) + "s+%" + (max_widthBank + 2) + "s+%"
                        + (max_widthSaldo + 2) + "s+%" + (max_widthKartu + 2) + "s+%" + (max_widthPin + 2) + "s+",
                " ", " ", " ", " ", " ", " ").replace(' ', '-');

        System.out.println("Data Nasabah Sistem ATM" + "\n");
        System.out.println(line);
        System.out.printf(
                "| %" + max_widthNama + "s | %" + max_widthRekening + "s | %" + max_widthBank + "s | %" + max_widthSaldo
                        + "s | %" + max_widthKartu + "s | %" + max_widthPin + "s |" + "\n",
                "Nama Nasabah", "Nomor Rekening", "Bank", "Saldo", "Nomor Kartu", "PIN");
        System.out.println(line);
        for (Nasabah nasabah : SistemATM.dataNasabah) {
            if (nasabah != null) {
                System.out.printf(
                        "| %" + max_widthNama + "s | %" + max_widthRekening + "s | %" + max_widthBank + "s | %,"
                                + max_widthSaldo + ".2f | %" + max_widthKartu + "s | %" + max_widthPin + "s |" + " \n",
                        nasabah.nama, nasabah.nomorRekening, nasabah.bank.nama, nasabah.saldo,
                        nasabah.nomorKartu, nasabah.pin);
            } else {
                break;
            }
        }
        System.out.println(line);
        System.out.println("");
    }

    public static Nasabah cariDataNasabah(Scanner input) {
        try {
            System.out.println("Cari Data Nasabah Berdasarkan: ");
            System.out.println(" 1 " + " Nomor Rekening Nasabah ");
            System.out.println(" 2 " + " Nomor Kartu Nasabah ");
            System.out.print("\nPilihan Anda -> ");

            int pilihanCari = input.nextInt();
            switch (pilihanCari) {
                case 1:
                    System.out.print("Masukkan Nomor Rekening Nasabah Yang Ingin Dicari -> ");
                    long nomorRekening = input.nextLong();
                    Nasabah nasabahRekening = SearchData.cariNasabahByRekening(nomorRekening);
                    return nasabahRekening;
                case 2:
                    System.out.print("Masukkan Nomor Kartu Nasabah Yang Ingin Dicari -> ");
                    long nomorKartu = input.nextLong();
                    Nasabah nasabahKartu = SearchData.cariNasabahByKartu(nomorKartu);
                    return nasabahKartu;
                default:
                    System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi\n");
                    adminMenu(new Scanner(System.in));
            }

        } catch (NullPointerException e) {
            System.out.println("Data Nasabah Tidak Ditemukan, Silahkan Coba Lagi\n");
            adminMenu(new Scanner(System.in));
        }
        return null;
    }

    public static void tampilkanDataNasabah(Nasabah nasabah) {
        System.out.println("");
        System.out.println("Data Nasabah Ditemukan\n");
        System.out.printf("%-20s: %s\n", "Nama ", nasabah.nama);
        System.out.printf("%-20s: %s\n", "Nomor Rekening ", nasabah.nomorRekening);
        System.out.printf("%-20s: %s\n", "Bank ", nasabah.bank.nama);
        System.out.printf("%-20s: %,.2f\n", "Saldo ", nasabah.saldo);
        System.out.printf("%-20s: %s\n", "Nomor Kartu ", nasabah.nomorKartu);
        System.out.printf("%-20s: %s\n\n", "PIN ", nasabah.pin);
    }

    public static void editDataNasabah(Scanner input) {
        try {
            Nasabah nasabahDitemukan = cariDataNasabah(input);
            tampilkanDataNasabah(nasabahDitemukan);
            if (nasabahDitemukan != null) {
                System.out.println("\n" + "Silahkan edit data nasabah sesuai pilihan di bawah ini: ");
                System.out.println("");
                System.out.println(" 1 " + " Nama ");
                System.out.println(" 2 " + " Nomor Rekening ");
                System.out.println(" 3 " + " Bank ");
                System.out.println(" 4 " + " Saldo ");
                System.out.println(" 5 " + " Nomor Kartu ");
                System.out.println(" 6 " + " Pin ");
                System.out.println(" 7 " + " Blokir/Unblokir Kartu Nasabah ");
                System.out.println(" 0 " + " Kembali ");

                System.out.print("\nPilihan Anda -> ");

                int pilihanEdit = input.nextInt();
                input.nextLine();
                switch (pilihanEdit) {
                    case 1:
                        System.out.print("Masukkan Nama Baru -> ");
                        String namaBaru = input.nextLine();
                        nasabahDitemukan.nama = namaBaru;
                        break;
                    case 2:
                        System.out.print("Masukkan Nomor Rekening Baru -> ");
                        int rekeningBaru = input.nextInt();
                        nasabahDitemukan.nomorRekening = rekeningBaru;
                        break;
                    case 3:
                        System.out.print("Masukkan Bank Baru -> ");
                        String namaBankBaru = input.nextLine();
                        Bank bankBaru = SearchData.cariBankByNama(namaBankBaru);
                        nasabahDitemukan.bank = bankBaru;
                        break;
                    case 4:
                        System.out.print("Masukkan Saldo Baru -> ");
                        double saldoBaru = input.nextDouble();
                        nasabahDitemukan.saldo = saldoBaru;
                        break;
                    case 5:
                        System.out.print("Masukkan Nomor Kartu Baru -> ");
                        int kartuBaru = input.nextInt();
                        nasabahDitemukan.nomorKartu = kartuBaru;
                        break;
                    case 6:
                        System.out.print("Masukkan Pin Baru -> ");
                        int pinBaru = input.nextInt();
                        while (pinBaru < 100000 || pinBaru > 999999) {
                            System.out.println("Pin harus 6 digit");
                            pinBaru = input.nextInt();
                        }
                        nasabahDitemukan.pin = pinBaru;
                        break;
                    case 7:
                        System.out.print(
                                "Masukkan Status Kartu Baru (true jika ingin blokir, false jika ingin unblokir) -> ");
                        boolean statusKartuBaru = input.nextBoolean();
                        nasabahDitemukan.diblokir = statusKartuBaru;
                        break;
                    case 0:
                        System.out.println("");
                        adminMenu(new Scanner(System.in));
                        System.out.println("");
                        break;
                    default:
                        System.out.println("");
                        System.out.println("Pilihan Tidak Valid");
                        System.out.println("");
                        adminMenu(new Scanner(System.in));
                }

                System.out.println("Data Nasabah Sudah Diupdate");
                System.out.println("");

            } else {
                System.out.println("Nasabah Dengan Nomor Rekening Tersebut Tidak Ditemukan");
            }

        } catch (NullPointerException e) {
            System.out.println("Data Tidak Ditemukan");
            adminMenu(new Scanner(System.in));
        } catch (Exception e) {
            System.out.println("Input Tidak Valid, Silahkan Coba Lagi");
            adminMenu(new Scanner(System.in));
        }

    }

    public static void tambahDataNasabah(Scanner input) {
        System.out.println("Tambah Data Nasabah Baru");
        System.out.print("Masukkan Nama Nasabah -> ");
        try {
            String nama = input.nextLine();
            if (nama.isEmpty()) {
                nama = input.nextLine();
            }
            System.out.print("Masukkan Nomor Rekening Nasabah -> ");

            long nomorRekening = Long.parseLong(input.next());
            System.out.print("Masukkan Nama Bank Nasabah -> ");
            input.nextLine(); // Clear buffer
            String namaBank = input.nextLine();
            Bank bank = SearchData.cariBankByNama(namaBank);
            while (bank == null) {
                System.out.println("Bank Tidak Ditemukan, Silahkan Coba Lagi");
                System.out.print("Masukkan Nama Bank Nasabah -> ");
                namaBank = input.nextLine();
                bank = SearchData.cariBankByNama(namaBank);
            }
            System.out.print("Masukkan Saldo Awal Nasabah -> ");
            double saldo = input.nextDouble();
            System.out.print("Masukkan Nomor Kartu Nasabah -> ");
            long nomorKartu = Long.parseLong(input.next());
            System.out.print("Masukkan PIN Nasabah -> ");
            int pin = input.nextInt();
            while (pin < 100000 || pin > 999999) {
                System.out.println("PIN harus 6 digit, silahkan coba lagi");
                System.out.print("Masukkan PIN Nasabah -> ");
                pin = input.nextInt();

            }

            Nasabah nasabahBaru = new Nasabah(nama, nomorRekening, bank, saldo, nomorKartu, pin, false);
            Nasabah[] dataNasabah = SistemATM.dataNasabah;
            // dataNasabah[dataNasabah.length] = nasabahBaru;
            // System.out.println("\n" + "Data Nasabah Berhasil Ditambahkan");
            // System.out.println("");

            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i] == null) {
                    dataNasabah[i] = nasabahBaru;
                    System.out.println("\n" + "Data Nasabah Berhasil Ditambahkan");
                    System.out.println("");
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("");
            System.out.println("Input Tidak Valid, Silahkan Coba Lagi");
            System.out.println("");
            adminMenu(new Scanner(System.in));
            return;
        }
    }

    public static void hapusDataNasabah(Scanner input) {
        try {
            Nasabah nasabahDitemukan = cariDataNasabah(input);
            tampilkanDataNasabah(nasabahDitemukan);
            System.out.print("Apakah Anda Yakin Ingin Menghapus Data Nasabah Ini? (masukkan 'y' untuk konformasi) -> ");
            char pilihan = input.next().charAt(0);
            if (pilihan == 'y' || pilihan == 'Y') {
                SistemATM.removeNasabah(nasabahDitemukan);
                System.out.println("\nData Nasabah Berhasil Dihapus\n");
            } else {
                System.out.println("\nData Nasabah Batal Dihapus\n");
            }
        } catch (NullPointerException e) {
            System.out.println("Data Nasabah Tidak Ditemukan");
            adminMenu(new Scanner(System.in));
            return;
        } catch (Exception e) {
            System.out.println("Input Tidak Valid, Silahkan Coba Lagi");
            adminMenu(new Scanner(System.in));
            return;
        }
    }

    public static void gantiTanggal(Scanner input) {
        try {
            System.out.print("Masukkan Tahun Baru: ");
            int tahunBaru = input.nextInt();

            System.out.print("Masukkan Bulan Baru: ");
            int bulanBaru = input.nextInt();
            while (bulanBaru < 1 || bulanBaru > 12) {
                System.out.print("Bulan Tidak Valid, Silahkan Coba Lagi: ");
                bulanBaru = input.nextInt();

            }
            System.out.print("Masukkan Tanggal Baru: ");
            boolean kabisat = (tahunBaru % 4 == 0 && tahunBaru % 100 != 0) || (tahunBaru % 400 == 0);
            int jumlahHariDalamBulan = 31;
            switch (bulanBaru) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    jumlahHariDalamBulan = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    jumlahHariDalamBulan = 30;
                    break;
                case 2:
                    jumlahHariDalamBulan = kabisat ? 29 : 28;
                    break;
            }
            int tanggalBaru = input.nextInt();
            while (tanggalBaru < 1 || tanggalBaru > jumlahHariDalamBulan) {
                System.out.print("Tanggal Tidak Valid, Silahkan Coba Lagi: ");
                tanggalBaru = input.nextInt();
            }

            SistemATM.tanggal = tanggalBaru;
            SistemATM.bulan = bulanBaru;
            SistemATM.tahun = tahunBaru;
            SistemATM.tanggalString = SistemATM.tanggalToString(tanggalBaru, bulanBaru, tahunBaru);

            System.out.println("Tanggal Berhasil Diubah" + "\n");

        } catch (Exception e) {
            System.out.println("Input Tidak Valid, Silahkan Coba Lagi");
            adminMenu(new Scanner(System.in));
        }
    }

}