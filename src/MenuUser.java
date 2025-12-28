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
        System.out.println("Selamat datang, " + nasabah.nama);
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

            System.out.println("Nomor Rekening Tujuan Tidak Ditemukan");
            System.out.print("Masukkan Nomor Rekening Tujuan: ");
            nomorRekeningTujuan = input.nextLong();
            if (nomorRekeningTujuan == -1) {
                userMenu(new Scanner(System.in), nasabah);
                return;
            }
            nasabahTujuan = SearchData.cariNasabahByRekening(nomorRekeningTujuan);

            System.out.print("Masukkan Jumlah Transfer: ");
            double jumlahTransfer = input.nextDouble();

            if (nasabah.saldo >= jumlahTransfer && jumlahTransfer > 0) {
                nasabah.saldo -= jumlahTransfer;
                nasabahTujuan.saldo += jumlahTransfer;
                nasabah.tambahTransaksi(new InfoTransaksi(-jumlahTransfer, nasabah.bank, "Transfer", nasabahTujuan,
                        SistemATM.tanggal, SistemATM.bulan, SistemATM.tahun));
                nasabahTujuan.tambahTransaksi(new InfoTransaksi(jumlahTransfer, nasabahTujuan.bank, "Transfer",
                        nasabah, SistemATM.tanggal, SistemATM.bulan, SistemATM.tahun));

                System.out.println("");
                System.out.println("Transfer Berhasil!");
                System.out.printf("Sisa Saldo: %,.2f \n", nasabah.saldo);
            } else if (jumlahTransfer <= 0) {
                System.out.println("Jumlah Transfer Harus Positif");
            } else {
                System.out.println("Saldo Tidak Cukup");
            }

            System.out.println("");

        } catch (NullPointerException e) {
            System.out.println("Nasabah Tujuan Tidak Ditemukan, silahkan coba lagi\n");
            userMenu(new Scanner(System.in), nasabah);
        } catch (Exception e) {
            System.out.println("Input Tidak Valid, Silahkan Coba Lagi\n");
            userMenu(new Scanner(System.in), nasabah);
        }
    }

    public static void tarikTunai(Scanner input, Nasabah nasabah) {
        try {
            System.out.print("Masukkan Jumlah Penarikan: ");
            double jumlahPenarikan = input.nextDouble();

            if (nasabah.saldo >= jumlahPenarikan) {
                nasabah.saldo -= jumlahPenarikan;
                System.out.println("");
                System.out.println("Penarikan Berhasil!");
                System.out.printf("Sisa Saldo: %,.2f \n", nasabah.saldo);
                nasabah.tambahTransaksi(new InfoTransaksi(-jumlahPenarikan, nasabah.bank, "Tarik Tunai", null,
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
        System.out.printf("Saldo Anda Saat Ini: %,.2f \n", nasabah.saldo);
        userMenu(new Scanner(System.in), nasabah);
    }

    public static void gantiPin(Scanner input, Nasabah nasabah) {
        try {
            System.out.print("Masukkan PIN Lama: ");

            int pinLama = input.nextInt();

            while (pinLama != nasabah.pin && pinLama != -1) {
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

            nasabah.pin = pinBaru;
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
                    max_widthnominal = String.valueOf(transaksi.nominal).length()
                            + ((int) Math.log10(nasabah.saldo) / 3) + 2;
                }
                if (transaksi.bank.nama.length() > max_widthBank) {
                    max_widthBank = transaksi.bank.nama.length();
                }
                if (transaksi.nasabahTarget != null && transaksi.nasabahTarget.nama.length() > max_nasabahTarget) {
                    max_nasabahTarget = transaksi.nasabahTarget.nama.length();
                }
                if (String.valueOf(transaksi.tanggal).length() > max_widthtanggal) {
                    max_widthtanggal = String.valueOf(transaksi.tanggal).length();
                }
            }
        }
        max_widthjenisTransaksi += 2;
        max_widthnominal += 4;
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
                        transaksi.jenisTransaksi, transaksi.nominal, transaksi.bank.nama,
                        transaksi.nasabahTarget != null ? transaksi.nasabahTarget.nama : "-",
                        tanggal);
            }
        }
        System.out.println(line);
        System.out.println("");

    }

}
