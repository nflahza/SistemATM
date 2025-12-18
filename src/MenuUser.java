package src;

import java.util.Scanner;

public class MenuUser {
    static int tanggal = SistemATM.tanggal;
    static int bulan = SistemATM.bulan;
    static int tahun = SistemATM.tahun;


    public static void userMenu(Scanner input, Nasabah nasabah) {
        String tanggalString = SistemATM.tanggalString;
        
        System.out.println(" ┌────────────────────────────────────────────┐ ");
        System.out.println(" │               MENU PENGGUNA ATM            │ ");
        System.out.println(" └────────────────────────────────────────────┘ " + "\n");
        System.out.println("Selamat datang, " + nasabah.getNama());
        System.out.println("Tanggal: " + tanggalString + "\n");
        System.out.println(" 1  Transfer               Riwayat Transaksi  5 ");
        System.out.println(" 2  Tarik Tunai                       Logout  6 ");
        System.out.println(" 3  Cek Saldo             Keluar dari Sistem  0 ");
        System.out.println(" 4  Ganti Pin                                - ");
        
        System.out.print("\nPilihan Anda -> ");
        int pilihanStart = -1;
        try {
            pilihanStart = input.nextInt();
        } catch (Exception e) {
            System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi ");
            userMenu(input, nasabah);
        }
        System.out.println("");
        switch (pilihanStart) {
            case 1:
                transfer(input, nasabah);
                break;
            case 2:
                tarikTunai(input, nasabah);
                break;
            case 3:
                cekSaldo(nasabah);
                break;
            case 4:
                gantiPin(input, nasabah);
                break;
            case 5:
                riwayatTransaksi(nasabah);
                break;
            case 6:
                System.out.println(" Terima Kasih telah menggunakan Sistem ATM kami ");
                SistemATM.startMenuATM(new Scanner(System.in));
                break;
            case 0:
                System.out.println(" Terima Kasih telah menggunakan Sistem ATM kami ");
                input.close();
                System.exit(0);
                return;
            default:
                System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi ");
                userMenu(input, nasabah);
                break;
        }

    }

    public static void transfer(Scanner input, Nasabah nasabah) {
        try {
            System.out.print("Masukkan Nomor Rekening Tujuan: ");
            long nomorRekeningTujuan = input.nextLong();
            
            Nasabah nasabahTujuan = SearchData.cariNasabahByRekening(nomorRekeningTujuan);
            
            while (nasabahTujuan == null) {
                System.out.println("Nomor Rekening Tujuan Tidak Ditemukan");
                System.out.print("Masukkan Nomor Rekening Tujuan: ");
                nomorRekeningTujuan = input.nextLong();
                if (nomorRekeningTujuan == -1) {
                    userMenu(new Scanner(System.in), nasabah);
                    return;
                }
                nasabahTujuan = SearchData.cariNasabahByRekening(nomorRekeningTujuan);
            }
            
            System.out.print("Masukkan Jumlah Transfer: ");
            double jumlahTransfer = input.nextDouble();

            
            if (nasabah.getSaldo() >= jumlahTransfer && jumlahTransfer > 0) {
                nasabah.setSaldo(nasabah.getSaldo() - jumlahTransfer);
                nasabahTujuan.setSaldo(nasabahTujuan.getSaldo() + jumlahTransfer);
                nasabah.tambahTransaksi(new InfoTransaksi(jumlahTransfer, nasabah.getBank(), "Transfer", nasabahTujuan, tanggal, bulan, tahun));
                nasabahTujuan.tambahTransaksi(new InfoTransaksi(jumlahTransfer, nasabahTujuan.getBank(), "Transfer", nasabah, tanggal, bulan, tahun));
                System.out.println("");
                System.out.println("Transfer Berhasil!");
                System.out.println("Sisa Saldo: " + nasabah.getSaldo());
            } else if (jumlahTransfer <= 0) {
                System.out.println("Jumlah Transfer Harus Positif");
            } else {
                System.out.println("Saldo Tidak Cukup");
            }

            System.out.println("");
            userMenu(input, nasabah);

        
        } catch (Exception e) {
            System.out.println("Input tidak valid, silahkan coba lagi.");
            System.out.println("");
            userMenu(new Scanner(System.in), nasabah);
        }
    }

    public static void tarikTunai(Scanner input, Nasabah nasabah) {
        try {
            System.out.print("Masukkan Jumlah Penarikan: ");
            double jumlahPenarikan = input.nextDouble();
            
            if (nasabah.getSaldo() >= jumlahPenarikan) {
                nasabah.setSaldo(nasabah.getSaldo() - jumlahPenarikan);
                System.out.println("");
                System.out.println("Penarikan Berhasil!");
                System.out.println("Sisa Saldo: " + nasabah.getSaldo());
                nasabah.tambahTransaksi(new InfoTransaksi(jumlahPenarikan, nasabah.getBank(), "Tarik Tunai", null, tanggal, bulan, tahun));
                System.out.println("");
                userMenu(new Scanner(System.in), nasabah);

            } else {
                System.out.println("Saldo Tidak Cukup");
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid, silahkan coba lagi.");
            System.out.println("");
            userMenu(new Scanner(System.in), nasabah);
        }
    }

    public static void cekSaldo(Nasabah nasabah) {
        System.out.println("Saldo Anda Saat Ini: " + nasabah.getSaldo());
        System.out.println("");
        userMenu(new Scanner(System.in), nasabah);
    }

    public static void gantiPin(Scanner input, Nasabah nasabah) {
        try {
            System.out.print("Masukkan PIN Lama: ");
            
            int pinLama = input.nextInt();
            // if (nasabah.getPin() != pinLama) {
            //         System.out.println("Pin Lama Salah, Silahkan Coba Lagi");
            //         pinLama = input.nextInt();
            //     } else if (pinLama < 100000 || pinLama > 999999) {
            //         System.out.println("Pin Harus 6 Digit, Silahkan Coba Lagi");
            //         System.out.print("Masukkan PIN Lama: ");
            //         pinLama = input.nextInt();
            //     } else if (pinLama == -1) {
            //         System.out.println("");
            //         userMenu(new Scanner(System.in), nasabah);
            //         return;
            //     }
            while (pinLama != nasabah.getPin() && pinLama != -1) {
                System.out.println("Pin Lama Salah, Silahkan Coba Lagi");
                System.out.print("Masukkan PIN Lama: ");
                pinLama = input.nextInt();
                if (pinLama == -1) {
                    System.out.println("");
                    userMenu(new Scanner(System.in), nasabah);
                    return;
                }
            }

            System.out.print("Masukkan PIN Baru: ");
            
            int pinBaru = input.nextInt();
            
            
            while ((pinBaru < 100000 || pinBaru > 999999) && pinBaru != -1) {
                System.out.println("Pin Harus 6 Digit, Silahkan Coba Lagi");
                System.out.print("Masukkan PIN Baru: ");
                pinBaru = input.nextInt();
                if (pinBaru == -1) {
                    System.out.println("");
                    userMenu(new Scanner(System.in), nasabah);
                    return;
                }
            }

            nasabah.setPin(pinBaru);
            System.out.println("");
            System.out.println("Pin Berhasil Diubah");
            System.out.println("");
            userMenu(new Scanner(System.in), nasabah);

            
        } catch (Exception e) {
            System.out.println("Input tidak valid, silahkan coba lagi.");
            System.out.println("");
            userMenu(new Scanner(System.in), nasabah);
        }
    }

    public static void riwayatTransaksi(Nasabah nasabah) {
        System.out.println("RIWAYAT TRANSAKSI ANDA: ");
        for (InfoTransaksi transaksi : nasabah.riwayatTransaksi) {
            if (transaksi != null) {
                System.out.println("- " + transaksi.jenisTransaksi + " sebesar " + transaksi.nominal + " di " + transaksi.bank.getNama() + " ");
            }
        }
        System.out.println("");
        userMenu(new Scanner(System.in), nasabah);
    }

}
