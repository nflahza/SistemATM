package src;

import java.util.Scanner;

public class MenuAdmin {
    static String BLUE_BACKGROUND = SistemATM.BLUE_BACKGROUND;
    static String GREEN_BACKGROUND = SistemATM.GREEN_BACKGROUND;
    static String YELLOW_BACKGROUND = SistemATM.YELLOW_BACKGROUND;
    static String RED_BACKGROUND = SistemATM.RED_BACKGROUND;
    static String RESET = SistemATM.RESET;

    static int tanggal = SistemATM.tanggal;
    static int bulan = SistemATM.bulan;
    static int tahun = SistemATM.tahun;


    public static void adminMenu(Scanner input) {
        String tanggalString = SistemATM.tanggalString;

        System.out.println(BLUE_BACKGROUND + " ┌────────────────────────────────────────────┐ " + RESET);
        System.out.println(BLUE_BACKGROUND + " │               MENU ADMIN ATM               │ " + RESET);
        System.out.println(BLUE_BACKGROUND + " └────────────────────────────────────────────┘ " + RESET + "\n");
        System.out.println(BLUE_BACKGROUND + "Tanggal: " + tanggalString + RESET + "\n");
        System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " Lihat Semua Data Nasabah " + RESET);
        System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " Cari Data Nasabah " + RESET);
        System.out.println(GREEN_BACKGROUND + " 3 " + BLUE_BACKGROUND + " Edit Data Nasabah " + RESET);
        System.out.println(GREEN_BACKGROUND + " 4 " + BLUE_BACKGROUND + " Tambah Data Nasabah " + RESET);
        System.out.println(GREEN_BACKGROUND + " 5 " + BLUE_BACKGROUND + " Ganti Tanggal " + RESET);
        System.out.println(GREEN_BACKGROUND + " 6 " + BLUE_BACKGROUND + " Logout " + RESET);
        System.out.println(GREEN_BACKGROUND + " 0 " + BLUE_BACKGROUND + " Keluar Dari Sistem " + RESET);
        
        System.out.print("\nPilihan Anda -> ");
        int pilihanStart = -1;
        try {
            pilihanStart = input.nextInt();
        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Pilihan tidak valid, silahkan coba lagi " + RESET);
            adminMenu(input);
        }
        
        System.out.println("");
        switch (pilihanStart) {
            case 1:
                lihatDataNasabah(input);
                adminMenu(input);
                break;
            case 2:
                tampilkanDataNasabah(cariDataNasabah(input));
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
                gantiTanggal(input);    
                adminMenu(input);
                break;
            case 6:
                System.out.println(BLUE_BACKGROUND + " Logout Berhasil " + RESET);
                SistemATM.startMenuATM(input);
                break;
            case 0:
                System.out.println(BLUE_BACKGROUND + " Terima Kasih Telah Menggunakan Sistem ATM Kami " + RESET);
                input.close();
                System.exit(0);
                return;
            default:
                System.out.println(BLUE_BACKGROUND + "Pilihan Tidak Valid, Silahkan Coba Lagi " + RESET);
                System.out.println("");
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
                if (nasabah.getNama().length() > max_widthNama) {
                    max_widthNama = nasabah.getNama().length();
                }
                if (String.valueOf(nasabah.getNomorRekening()).length() > max_widthRekening) {
                    max_widthRekening = String.valueOf(nasabah.getNomorRekening()).length();
                }
                if (nasabah.getBank().getNama().length() > max_widthBank) {
                    max_widthBank = nasabah.getBank().getNama().length();
                }
                if (String.valueOf(nasabah.getSaldo()).length() > max_widthSaldo) {
                    max_widthSaldo = String.valueOf(nasabah.getSaldo()).length();
                }
                if (String.valueOf(nasabah.getNomorKartu()).length() > max_widthKartu) {
                    max_widthKartu = String.valueOf(nasabah.getNomorKartu()).length();
                }
                if (String.valueOf(nasabah.getPin()).length() > max_widthPin) {
                    max_widthPin = String.valueOf(nasabah.getPin()).length();
                }
            }
        }
        max_widthNama       += 2;
        max_widthRekening   += 2;
        max_widthBank       += 2;
        max_widthSaldo      += 2;
        max_widthKartu      += 2;
        max_widthPin        += 2;

        String line1 = String.format("┌%" + (max_widthNama+2) + "s┬%" + (max_widthRekening+2) + "s┬%" + (max_widthBank+2) + "s┬%" + (max_widthSaldo+2) + "s┬%" + (max_widthKartu+2) + "s┬%" + (max_widthPin+2) + "s┐", " ", " ", " ", " ", " ", " ");
        String line2 = String.format("├%" + (max_widthNama+2) + "s┼%" + (max_widthRekening+2) + "s┼%" + (max_widthBank+2) + "s┼%" + (max_widthSaldo+2) + "s┼%" + (max_widthKartu+2) + "s┼%" + (max_widthPin+2) + "s┤", " ", " ", " ", " ", " ", " ");
        String line3 = String.format("└%" + (max_widthNama+2) + "s┴%" + (max_widthRekening+2) + "s┴%" + (max_widthBank+2) + "s┴%" + (max_widthSaldo+2) + "s┴%" + (max_widthKartu+2) + "s┴%" + (max_widthPin+2) + "s┘", " ", " ", " ", " ", " ", " ");
        

        System.out.println(BLUE_BACKGROUND + "Data Nasabah Sistem ATM" + RESET + "\n");
        System.out.println(BLUE_BACKGROUND + line1.replace(' ', '─') + RESET);
        System.out.printf(BLUE_BACKGROUND + "│ %" + max_widthNama + "s │ %" + max_widthRekening + "s │ %" + max_widthBank + "s │ %" + max_widthSaldo + "s │ %" + max_widthKartu + "s │ %" + max_widthPin + "s │" + RESET + "\n", "Nama Nasabah", "Nomor Rekening", "Bank", "Saldo", "Nomor Kartu", "PIN");
        System.out.println(BLUE_BACKGROUND + line2.replace(' ', '─') + RESET);
        for (Nasabah nasabah : SistemATM.dataNasabah) {
            if (nasabah != null) {
                System.out.printf(BLUE_BACKGROUND + "│ %" + max_widthNama + "s │ %" + max_widthRekening + "s │ %" + max_widthBank + "s │ %" + max_widthSaldo + "s │ %" + max_widthKartu + "s │ %" + max_widthPin + "s │" + RESET + " \n", nasabah.getNama(), nasabah.getNomorRekening(), nasabah.getBank().getNama(), nasabah.getSaldo(), nasabah.getNomorKartu(), nasabah.getPin());
            } else {
                break;
            }
        }
        System.out.println(BLUE_BACKGROUND + line3.replace(' ', '─') + RESET);
        System.out.println("");
    }

    public static Nasabah cariDataNasabah(Scanner input) {
        try {
            System.out.println(BLUE_BACKGROUND + "Cari Data Nasabah Berdasarkan:" + RESET + " ");
            System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " Nomor Rekening Nasabah " + RESET);
            System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " Nomor Kartu Nasabah " + RESET);
            System.out.print("\nPilihan Anda -> " + RESET + " ");
            int pilihanCari = input.nextInt();
            Nasabah nasabahDitemukan = null;
            switch (pilihanCari) {
                case 1:
                    System.out.print("Masukkan Nomor Rekening Nasabah Yang Ingin Dicari -> ");
                    long nomorRekening = input.nextLong();
                    nasabahDitemukan = SearchData.cariNasabahByRekening(nomorRekening);
                    break;
                case 2:
                    System.out.print("Masukkan Nomor Rekening Nasabah Yang Ingin Dicari -> ");
                    long nomorKartu = input.nextLong();
                    nasabahDitemukan = SearchData.cariNasabahByKartu(nomorKartu);
                    break;
                default:
                    System.out.println(RED_BACKGROUND + "Pilihan Tidak Valid, Silahkan Coba Lagi " + RESET);
                    adminMenu(new Scanner(System.in));
            }
            return nasabahDitemukan;
        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Input Tidak Valid, Silahkan Coba Lagi" + RESET);
            System.out.println("");

            adminMenu(new Scanner(System.in));
        }
        return null;
    }

    public static void tampilkanDataNasabah(Nasabah nasabah) {
        if (nasabah != null) {
            System.out.println("");
            System.out.println(GREEN_BACKGROUND + "Data Nasabah Ditemukan " + RESET);
            System.out.println(BLUE_BACKGROUND + "Nama Nasabah: " + nasabah.getNama() + RESET + " ");
            System.out.println(BLUE_BACKGROUND + "Nomor Rekening: " + nasabah.getNomorRekening() + RESET + " ");
            System.out.println(BLUE_BACKGROUND + "Bank: " + nasabah.getBank().getNama() + RESET + " ");
            System.out.println(BLUE_BACKGROUND + "Saldo: " + nasabah.getSaldo() + RESET + " ");
            System.out.println(BLUE_BACKGROUND + "Nomor Kartu: " + nasabah.getNomorKartu() + RESET + " ");
            System.out.println(BLUE_BACKGROUND + "PIN: " + nasabah.getPin() + RESET + " ");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println(RED_BACKGROUND + "Nasabah dengan nomor rekening tersebut tidak ditemukan " + RESET);
            System.out.println("");
            
        }
    }

    public static void editDataNasabah(Scanner input) {
        try {
            Nasabah nasabahDitemukan = cariDataNasabah(input);
            tampilkanDataNasabah(nasabahDitemukan);
            if (nasabahDitemukan != null) {
                System.out.println("\n" + BLUE_BACKGROUND + "Silahkan edit data nasabah sesuai pilihan di bawah ini: " + RESET);
                System.out.println("");
                System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " Nama Nasabah " + RESET);
                System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " Nomor Rekening Nasabah " + RESET);
                System.out.println(GREEN_BACKGROUND + " 3 " + BLUE_BACKGROUND + " Bank Nasabah " + RESET);
                System.out.println(GREEN_BACKGROUND + " 4 " + BLUE_BACKGROUND + " Saldo Nasabah " + RESET);
                System.out.println(GREEN_BACKGROUND + " 5 " + BLUE_BACKGROUND + " Nomor Kartu Nasabah " + RESET);
                System.out.println(GREEN_BACKGROUND + " 6 " + BLUE_BACKGROUND + " Pin Nasabah " + RESET);
                System.out.println(GREEN_BACKGROUND + " 7 " + BLUE_BACKGROUND + " Blokir/Unblokir Kartu Nasabah " + RESET);
                System.out.println(GREEN_BACKGROUND + " 0 " + BLUE_BACKGROUND + " Kembali " + RESET);

                System.out.print("\nPilihan Anda -> ");
                
                int pilihanEdit = input.nextInt();
                System.out.print("Masukkan Data Baru -> ");
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
                            System.out.println(BLUE_BACKGROUND + "Bank tidak Ditemukan" + RESET);
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
                        if (pinBaru < 100000 || pinBaru > 999999) {
                            System.out.println(BLUE_BACKGROUND + "Pin harus 6 digit" + RESET);
                            break;
                        }
                        nasabahDitemukan.setPin(pinBaru);
                        break;
                    case 7:
                        boolean statusKartuBaru = input.nextBoolean();
                        nasabahDitemukan.setDiblokir(statusKartuBaru);
                        break;
                    case 0:
                        System.out.println("");
                        adminMenu(new Scanner(System.in));
                        System.out.println("");
                    default:
                        System.out.println("");
                        System.out.println(BLUE_BACKGROUND + "Pilihan Tidak Valid" + RESET);
                        System.out.println("");
                        adminMenu(new Scanner(System.in));
                    } 

            System.out.println(GREEN_BACKGROUND + "Data Nasabah Sudah Diupdate" + RESET);
            System.out.println("");

            } else {
                System.out.println(RED_BACKGROUND + "Nasabah Dengan Nomor Rekening Tersebut Tidak Ditemukan" + RESET);
            }

        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Input Tidak Valid, Silahkan Coba Lagi" + RESET);
            adminMenu(new Scanner(System.in));
        }
        
    }

    public static void tambahDataNasabah(Scanner input) {
        System.out.println(BLUE_BACKGROUND + "Tambah Data Nasabah Baru" + RESET);
        System.out.print("Masukkan Nama Nasabah -> ");
        try {
            String nama = input.nextLine();
            if (nama.isEmpty()) {
                nama = input.nextLine();
            }
            System.out.print("Masukkan Nomor Rekening Nasabah -> ");
            
            long nomorRekening = Long.parseLong(input.next());
            System.out.print( "Masukkan Nama Bank Nasabah -> ");
            input.nextLine(); // Clear buffer
            String namaBank = input.nextLine();
            Bank bank = SearchData.cariBankByNama(namaBank);
            while (bank == null) {
                System.out.println(YELLOW_BACKGROUND + "Bank Tidak Ditemukan, Silahkan Coba Lagi" + RESET);
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
                System.out.println(RED_BACKGROUND + "PIN harus 6 digit, silahkan coba lagi" + RESET);
                pin = input.nextInt();

            }
            
            
            Nasabah nasabahBaru = new Nasabah(nama, nomorRekening, bank, saldo, nomorKartu, pin, false);
            Nasabah[] dataNasabah = SistemATM.dataNasabah;
            // dataNasabah[dataNasabah.length] = nasabahBaru;
            // System.out.println("\n" + GREEN_BACKGROUND + "Data Nasabah Berhasil Ditambahkan" + RESET);
            // System.out.println("");
                    
            for (int i = 0; i < dataNasabah.length; i++) {
                if (dataNasabah[i] == null) {
                    dataNasabah[i] = nasabahBaru;
                    System.out.println("\n" + GREEN_BACKGROUND + "Data Nasabah Berhasil Ditambahkan" + RESET);
                    System.out.println("");
                    break;
                }
            }
        
        } catch (Exception e) {
            System.out.println("");
            System.out.println(RED_BACKGROUND + "Input Tidak Valid, Silahkan Coba Lagi" + RESET);
            System.out.println("");
            adminMenu(new Scanner(System.in));
            return;
        }
    }

    public static void gantiTanggal(Scanner input) {
        try {
            System.out.print("Masukkan Tahun Baru: " + RESET);
            int tahunBaru = input.nextInt();

            System.out.print("Masukkan Bulan Baru: " + RESET);
            int bulanBaru = input.nextInt();
            while (bulanBaru < 1 || bulanBaru > 12) {
                System.out.print(RED_BACKGROUND + "Bulan Tidak Valid, Silahkan Coba Lagi: " + RESET);
                bulanBaru = input.nextInt();
                
            }
            System.out.print("Masukkan Tanggal Baru: " + RESET);
            boolean kabisat = (tahunBaru % 4 == 0 && tahunBaru % 100 != 0) || (tahunBaru % 400 == 0);
            int jumlahHariDalamBulan = 31;
            switch (bulanBaru) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    jumlahHariDalamBulan = 31;
                    break;
                case 4: case 6: case 9: case 11:
                    jumlahHariDalamBulan = 30;
                    break;
                case 2:
                    jumlahHariDalamBulan = kabisat ? 29 : 28;
                    break;
            }
            int tanggalBaru = input.nextInt();
            while (tanggalBaru < 1 || tanggalBaru > jumlahHariDalamBulan) {
                System.out.print(RED_BACKGROUND + "Tanggal Tidak Valid, Silahkan Coba Lagi: " + RESET);
                tanggalBaru = input.nextInt();
            }

            SistemATM.tanggal = tanggalBaru;
            SistemATM.bulan = bulanBaru;        
            SistemATM.tahun = tahunBaru;
            SistemATM.tanggalString = SistemATM.tanggalToString(tanggalBaru, bulanBaru, tahunBaru);
            
            System.out.println(GREEN_BACKGROUND + "Tanggal Berhasil Diubah" + RESET + "\n");

            
        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Input Tidak Valid, Silahkan Coba Lagi" + RESET);
            adminMenu(new Scanner(System.in));
        } 
    }

}
