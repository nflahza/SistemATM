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
        System.out.println(" 5 " + " Ganti Tanggal ");
        System.out.println(" 6 " + " Logout ");
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
                System.out.println(" Logout Berhasil ");
                SistemATM.startMenuATM(input);
                break;
            case 0:
                System.out.println(" Terima Kasih Telah Menggunakan Sistem ATM Kami ");
                input.close();
                System.exit(0);
                return;
            default:
                System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi ");
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

        String line1 = String.format("+%" + (max_widthNama+2) + "s+%" + (max_widthRekening+2) + "s+%" + (max_widthBank+2) + "s+%" + (max_widthSaldo+2) + "s+%" + (max_widthKartu+2) + "s+%" + (max_widthPin+2) + "s+", " ", " ", " ", " ", " ", " ");
        String line2 = String.format("+%" + (max_widthNama+2) + "s+%" + (max_widthRekening+2) + "s+%" + (max_widthBank+2) + "s+%" + (max_widthSaldo+2) + "s+%" + (max_widthKartu+2) + "s+%" + (max_widthPin+2) + "s+", " ", " ", " ", " ", " ", " ");
        String line3 = String.format("+%" + (max_widthNama+2) + "s+%" + (max_widthRekening+2) + "s+%" + (max_widthBank+2) + "s+%" + (max_widthSaldo+2) + "s+%" + (max_widthKartu+2) + "s+%" + (max_widthPin+2) + "s+", " ", " ", " ", " ", " ", " ");
        

        System.out.println("Data Nasabah Sistem ATM" + "\n");
        System.out.println(line1.replace(' ', '-'));
        System.out.printf("| %" + max_widthNama + "s | %" + max_widthRekening + "s | %" + max_widthBank + "s | %" + max_widthSaldo + "s | %" + max_widthKartu + "s | %" + max_widthPin + "s |" + "\n", "Nama Nasabah", "Nomor Rekening", "Bank", "Saldo", "Nomor Kartu", "PIN");
        System.out.println(line2.replace(' ', '-'));
        for (Nasabah nasabah : SistemATM.dataNasabah) {
            if (nasabah != null) {
                System.out.printf("| %" + max_widthNama + "s | %" + max_widthRekening + "s | %" + max_widthBank + "s | %" + max_widthSaldo + "s | %" + max_widthKartu + "s | %" + max_widthPin + "s |" + " \n", nasabah.getNama(), nasabah.getNomorRekening(), nasabah.getBank().getNama(), nasabah.getSaldo(), nasabah.getNomorKartu(), nasabah.getPin());
            } else {
                break;
            }
        }
        System.out.println(line3.replace(' ', '-'));
        System.out.println("");
    }

    public static Nasabah cariDataNasabah(Scanner input) {
        try {
            System.out.println("Cari Data Nasabah Berdasarkan:" + " ");
            System.out.println(" 1 " + " Nomor Rekening Nasabah ");
            System.out.println(" 2 " + " Nomor Kartu Nasabah ");
            System.out.print("\nPilihan Anda -> " + " ");
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
                    System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi ");
                    adminMenu(new Scanner(System.in));
            }
            return nasabahDitemukan;
        } catch (Exception e) {
            System.out.println("Input Tidak Valid, Silahkan Coba Lagi");
            System.out.println("");

            adminMenu(new Scanner(System.in));
        }
        return null;
    }

    public static void tampilkanDataNasabah(Nasabah nasabah) {
        if (nasabah != null) {
            System.out.println("");
            System.out.println("Data Nasabah Ditemukan ");
            System.out.println("Nama Nasabah: " + nasabah.getNama() + " ");
            System.out.println("Nomor Rekening: " + nasabah.getNomorRekening() + " ");
            System.out.println("Bank: " + nasabah.getBank().getNama() + " ");
            System.out.println("Saldo: " + nasabah.getSaldo() + " ");
            System.out.println("Nomor Kartu: " + nasabah.getNomorKartu() + " ");
            System.out.println("PIN: " + nasabah.getPin() + " ");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("Nasabah dengan nomor rekening tersebut tidak ditemukan ");
            System.out.println("");
            
        }
    }

    public static void editDataNasabah(Scanner input) {
        try {
            Nasabah nasabahDitemukan = cariDataNasabah(input);
            tampilkanDataNasabah(nasabahDitemukan);
            if (nasabahDitemukan != null) {
                System.out.println("\n" + "Silahkan edit data nasabah sesuai pilihan di bawah ini: ");
                System.out.println("");
                System.out.println(" 1 " + " Nama Nasabah ");
                System.out.println(" 2 " + " Nomor Rekening Nasabah ");
                System.out.println(" 3 " + " Bank Nasabah ");
                System.out.println(" 4 " + " Saldo Nasabah ");
                System.out.println(" 5 " + " Nomor Kartu Nasabah ");
                System.out.println(" 6 " + " Pin Nasabah ");
                System.out.println(" 7 " + " Blokir/Unblokir Kartu Nasabah ");
                System.out.println(" 0 " + " Kembali ");

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
                            System.out.println("Bank tidak Ditemukan");
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
                            System.out.println("Pin harus 6 digit");
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
                        System.out.println("Pilihan Tidak Valid");
                        System.out.println("");
                        adminMenu(new Scanner(System.in));
                    } 

            System.out.println("Data Nasabah Sudah Diupdate");
            System.out.println("");

            } else {
                System.out.println("Nasabah Dengan Nomor Rekening Tersebut Tidak Ditemukan");
            }

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
            System.out.print( "Masukkan Nama Bank Nasabah -> ");
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
