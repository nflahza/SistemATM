package src;

import java.util.Scanner;

public class MenuUser {
    static String BLUE_BACKGROUND = SistemATM.BLUE_BACKGROUND;
    static String GREEN_BACKGROUND = SistemATM.GREEN_BACKGROUND;
    static String YELLOW_BACKGROUND = SistemATM.YELLOW_BACKGROUND;
    static String RED_BACKGROUND = SistemATM.RED_BACKGROUND;
    static String RESET = SistemATM.RESET;

    static int tanggal = SistemATM.tanggal;
    static int bulan = SistemATM.bulan;
    static int tahun = SistemATM.tahun;


    public static void userMenu(Scanner input, Nasabah nasabah) {
        String tanggalString = SistemATM.tanggalString;
        
        System.out.println(BLUE_BACKGROUND + " ┌────────────────────────────────────────────┐ " + RESET);
        System.out.println(BLUE_BACKGROUND + " │               MENU PENGGUNA ATM            │ " + RESET);
        System.out.println(BLUE_BACKGROUND + " └────────────────────────────────────────────┘ " + RESET + "\n");
        System.out.println(BLUE_BACKGROUND + "Selamat datang, " + nasabah.getNama() + RESET);
        System.out.println(BLUE_BACKGROUND + "Tanggal: " + tanggalString + RESET + "\n");

        System.out.println(GREEN_BACKGROUND + " 1 " + BLUE_BACKGROUND + " Transfer               Riwayat Transaksi " + GREEN_BACKGROUND + " 5 " + RESET);
        System.out.println(GREEN_BACKGROUND + " 2 " + BLUE_BACKGROUND + " Tarik Tunai                       Logout " + GREEN_BACKGROUND + " 6 " + RESET);
        System.out.println(GREEN_BACKGROUND + " 3 " + BLUE_BACKGROUND + " Cek Saldo             Keluar dari Sistem " + GREEN_BACKGROUND + " 0 " + RESET);
        System.out.println(GREEN_BACKGROUND + " 4 " + BLUE_BACKGROUND + " Ganti Pin                                " + GREEN_BACKGROUND + " - " + RESET);
        
        System.out.print("\nPilihan Anda -> ");
        int pilihanStart = -1;
        try {
            pilihanStart = input.nextInt();
        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Pilihan Tidak Valid, Silahkan Coba Lagi " + RESET);
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
                System.out.println(BLUE_BACKGROUND + " Terima Kasih telah menggunakan Sistem ATM kami " + RESET);
                SistemATM.startMenuATM(new Scanner(System.in));
                break;
            case 0:
                System.out.println(BLUE_BACKGROUND + " Terima Kasih telah menggunakan Sistem ATM kami " + RESET);
                input.close();
                System.exit(0);
                return;
            default:
                System.out.println(RED_BACKGROUND + "Pilihan Tidak Valid, Silahkan Coba Lagi " + RESET);
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
                System.out.println(RED_BACKGROUND + "Nomor Rekening Tujuan Tidak Ditemukan" + RESET);
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
                System.out.println(BLUE_BACKGROUND + "Transfer Berhasil!" + RESET);
                System.out.println(BLUE_BACKGROUND + "Sisa Saldo: " + nasabah.getSaldo() + RESET);
            } else if (jumlahTransfer <= 0) {
                System.out.println(YELLOW_BACKGROUND + "Jumlah Transfer Harus Positif" + RESET);
            } else {
                System.out.println(RED_BACKGROUND + "Saldo Tidak Cukup" + RESET);
            }

            System.out.println("");
            userMenu(input, nasabah);

        
        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Input tidak valid, silahkan coba lagi." + RESET);
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
                System.out.println(BLUE_BACKGROUND + "Penarikan Berhasil!" + RESET);
                System.out.println(BLUE_BACKGROUND + "Sisa Saldo: " + nasabah.getSaldo() + RESET);
                nasabah.tambahTransaksi(new InfoTransaksi(jumlahPenarikan, nasabah.getBank(), "Tarik Tunai", null, tanggal, bulan, tahun));
                System.out.println("");
                userMenu(new Scanner(System.in), nasabah);

            } else {
                System.out.println(RED_BACKGROUND + "Saldo Tidak Cukup" + RESET);
            }
        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Input tidak valid, silahkan coba lagi." + RESET);
            System.out.println("");
            userMenu(new Scanner(System.in), nasabah);
        }
    }

    public static void cekSaldo(Nasabah nasabah) {
        System.out.println(BLUE_BACKGROUND + "Saldo Anda Saat Ini: " + nasabah.getSaldo() + RESET);
        System.out.println("");
        userMenu(new Scanner(System.in), nasabah);
    }

    public static void gantiPin(Scanner input, Nasabah nasabah) {
        try {
            System.out.print("Masukkan PIN Lama: ");
            
            int pinLama = input.nextInt();
            // if (nasabah.getPin() != pinLama) {
            //         System.out.println(RED_BACKGROUND + "Pin Lama Salah, Silahkan Coba Lagi" + RESET);
            //         pinLama = input.nextInt();
            //     } else if (pinLama < 100000 || pinLama > 999999) {
            //         System.out.println(RED_BACKGROUND + "Pin Harus 6 Digit, Silahkan Coba Lagi" + RESET);
            //         System.out.print("Masukkan PIN Lama: ");
            //         pinLama = input.nextInt();
            //     } else if (pinLama == -1) {
            //         System.out.println("");
            //         userMenu(new Scanner(System.in), nasabah);
            //         return;
            //     }
            while (pinLama != nasabah.getPin() && pinLama != -1) {
                System.out.println(RED_BACKGROUND + "Pin Lama Salah, Silahkan Coba Lagi" + RESET);
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
                System.out.println(RED_BACKGROUND + "Pin Harus 6 Digit, Silahkan Coba Lagi" + RESET);
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
            System.out.println(BLUE_BACKGROUND + "Pin Berhasil Diubah" + RESET);
            System.out.println("");
            userMenu(new Scanner(System.in), nasabah);

            
        } catch (Exception e) {
            System.out.println(RED_BACKGROUND + "Input tidak valid, silahkan coba lagi." + RESET);
            System.out.println("");
            userMenu(new Scanner(System.in), nasabah);
        }
    }

    public static void riwayatTransaksi(Nasabah nasabah) {
        System.out.println(BLUE_BACKGROUND + "RIWAYAT TRANSAKSI ANDA: " + RESET);
        for (InfoTransaksi transaksi : nasabah.riwayatTransaksi) {
            if (transaksi != null) {
                System.out.println(BLUE_BACKGROUND + "- " + transaksi.jenisTransaksi + " sebesar " + transaksi.nominal + " di " + transaksi.bank.getNama() + " " + RESET);
            }
        }
        System.out.println("");
        userMenu(new Scanner(System.in), nasabah);
    }

}
