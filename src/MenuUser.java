package src;

import java.util.Scanner;

public class MenuUser {
    // static int tanggal = SistemATM.tanggal;
    // static int bulan = SistemATM.bulan;
    // static int tahun = SistemATM.tahun;

    public static void userMenu(Scanner input, Nasabah nasabah) {
        String tanggalString = SistemATM.tanggalToString(SistemATM.tanggal, SistemATM.bulan, SistemATM.tahun);

        System.out.println(" +--------------------------------------------+ ");
        System.out.println(" |               MENU PENGGUNA ATM            | ");
        System.out.println(" +--------------------------------------------+ " + "\n");
        System.out.println("Selamat datang, " + nasabah.getNama());
        System.out.println("Tanggal: " + tanggalString + "\n");
        System.out.println(" 1  Transfer               Riwayat Transaksi  5 ");
        System.out.println(" 2  Tarik Tunai                       Logout  6 ");
        System.out.println(" 3  Cek Saldo             Keluar dari Sistem  0 ");
        System.out.println(" 4  Ganti Pin                                 - ");

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
                userMenu(input, nasabah);
                break;
            case 2:
                tarikTunai(input, nasabah);
                userMenu(input, nasabah);
                break;
            case 3:
                cekSaldo(nasabah);
                userMenu(input, nasabah);
                break;
            case 4:
                gantiPin(input, nasabah);
                userMenu(input, nasabah);
                break;
            case 5:
                riwayatTransaksi(nasabah);
                userMenu(input, nasabah);
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
                nasabah.tambahTransaksi(new InfoTransaksi(-jumlahTransfer, nasabah.getBank(), "Transfer", nasabahTujuan,
                        SistemATM.tanggal, SistemATM.bulan, SistemATM.tahun));
                nasabahTujuan.tambahTransaksi(new InfoTransaksi(jumlahTransfer, nasabahTujuan.getBank(), "Transfer",
                        nasabah, SistemATM.tanggal, SistemATM.bulan, SistemATM.tahun));

                System.out.println("");
                System.out.println("Transfer Berhasil!");
                System.out.println("Sisa Saldo: " + nasabah.getSaldo());
            } else if (jumlahTransfer <= 0) {
                System.out.println("Jumlah Transfer Harus Positif");
            } else {
                System.out.println("Saldo Tidak Cukup");
            }

            System.out.println("");

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
                nasabah.tambahTransaksi(new InfoTransaksi(-jumlahPenarikan, nasabah.getBank(), "Tarik Tunai", null,
                        SistemATM.tanggal, SistemATM.bulan, SistemATM.tahun));
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
            // System.out.println("Pin Lama Salah, Silahkan Coba Lagi");
            // pinLama = input.nextInt();
            // } else if (pinLama < 100000 || pinLama > 999999) {
            // System.out.println("Pin Harus 6 Digit, Silahkan Coba Lagi");
            // System.out.print("Masukkan PIN Lama: ");
            // pinLama = input.nextInt();
            // } else if (pinLama == -1) {
            // System.out.println("");
            // userMenu(new Scanner(System.in), nasabah);
            // return;
            // }
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

        } catch (Exception e) {
            System.out.println("Input tidak valid, silahkan coba lagi.");
            System.out.println("");
            userMenu(new Scanner(System.in), nasabah);
        }
    }

    public static void riwayatTransaksi(Nasabah nasabah) {
        // System.out.println("Riwayat Transaksi Anda: ");
        // for (InfoTransaksi transaksi : nasabah.riwayatTransaksi) {
        // if (transaksi != null) {
        // System.out.println("- " + transaksi.jenisTransaksi + " sebesar " +
        // transaksi.nominal + " di "
        // + transaksi.bank.getNama() + " ");
        // }
        // }

        // System.out.println("");

        int max_widthjenisTransaksi = 14;
        int max_widthnominal = 16;
        int max_widthBank = 6;
        int max_nasabahTarget = 16;
        int max_widthtanggal = 16;

        for (InfoTransaksi transaksi : nasabah.riwayatTransaksi) {
            if (transaksi != null) {
                if (transaksi.jenisTransaksi.length() > max_widthjenisTransaksi) {
                    max_widthjenisTransaksi = transaksi.jenisTransaksi.length();
                }
                if (String.valueOf(transaksi.nominal).length() > max_widthnominal) {
                    max_widthnominal = String.valueOf(transaksi.nominal).length();
                }
                if (transaksi.bank.getNama().length() > max_widthBank) {
                    max_widthBank = transaksi.bank.getNama().length();
                }
                if (transaksi.nasabahTarget != null && transaksi.nasabahTarget.getNama().length() > max_nasabahTarget) {
                    max_nasabahTarget = transaksi.nasabahTarget.getNama().length();
                }
                if (String.valueOf(transaksi.tanggal).length() > max_widthtanggal) {
                    max_widthtanggal = String.valueOf(transaksi.tanggal).length();
                }
            }
        }
        max_widthjenisTransaksi += 2;
        max_widthnominal += 2;
        max_widthBank += 2;
        max_nasabahTarget += 2;
        max_widthtanggal += 2;

        String line = String.format(
                "+%" + (max_widthjenisTransaksi + 2) + "s+%" + (max_widthnominal + 2) + "s+%" + (max_widthBank + 2)
                        + "s+%" + (max_nasabahTarget + 2) + "s+%" + (max_widthtanggal + 2) + "s+",
                " ", " ", " ", " ", " ").replace(' ', '-');

        System.out.println("Riwayat Transaksi Anda:" + "\n");
        System.out.println(line);
        System.out.printf(
                "| %" + max_widthjenisTransaksi + "s | %" + max_widthnominal + "s | %" + max_widthBank + "s | %"
                        + max_nasabahTarget
                        + "s | %" + max_widthtanggal + "s |"
                        + "\n",
                "Jenis Transaksi", "Nominal", "Bank", "Nasabah Target", "Tanggal");
        System.out.println(line);
        for (InfoTransaksi transaksi : nasabah.riwayatTransaksi) {

            if (transaksi != null) {
                String tanggal = SistemATM.tanggalToString(transaksi.tanggal, transaksi.bulan, transaksi.tahun);

                System.out.printf(
                        "| %" + max_widthjenisTransaksi + "s | %+," + max_widthnominal + ".2f | %" + max_widthBank
                                + "s | %"
                                + max_nasabahTarget + "s | %" + max_widthtanggal + "s |" + " \n",
                        transaksi.getJenisTransaksi(), transaksi.getNominal(), transaksi.getBank().getNama(),
                        transaksi.getNasabahTarget() != null ? transaksi.getNasabahTarget().getNama() : "-",
                        tanggal);
            }
        }
        System.out.println(line);
        System.out.println("");

    }

}
