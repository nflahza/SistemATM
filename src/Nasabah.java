package src;

public class Nasabah {
    String nama;
    long nomorRekening;
    Bank bank;
    double saldo = 0;
    InfoTransaksi[] riwayatTransaksi = new InfoTransaksi[1000];
    long nomorKartu;
    int pin;
    boolean diblokir = false;

    public Nasabah(String nama, long nomorRekening, Bank bank, double saldo, long nomorKartu, int pin,
            boolean diblokir) {
        this.nama = nama;
        this.nomorRekening = nomorRekening;
        this.bank = bank;
        this.saldo = saldo;
        this.nomorKartu = nomorKartu;
        this.pin = pin;
        this.diblokir = false;
    }

    // Procedure
    public void tambahTransaksi(InfoTransaksi transaksiBaru) {
        for (int i = 0; i < riwayatTransaksi.length; i++) {
            if (riwayatTransaksi[i] == null) {
                riwayatTransaksi[i] = transaksiBaru;
                break;
            }
        }
        sortTransaksi();
    }

    public void kirimTransfer(int jumlah, Nasabah penerima) {
        if (saldo >= jumlah) {
            this.saldo -= jumlah;
            penerima.saldo += jumlah;
        } else {
            System.out.println("Saldo anda tidak cukup!");
        }
    }

    public void tarikTunai(int jumlah) {
        if (saldo >= jumlah) {
            this.saldo -= jumlah;
        } else {
            System.out.println("Saldo anda tidak cukup!");
        }
    }

    public void editPIN(int pinBaru) {
        this.pin = pinBaru;
    }

    public void blokirKartu() {
        this.diblokir = true;
    }

    public void unblokirKartu() {
        this.diblokir = false;
    }

    // Sort Tanggal Transaksi (Selection Sort)
    public void sortTransaksi() {
        int n = 0;
        for (int i = 0; i < riwayatTransaksi.length; i++) {
            if (riwayatTransaksi[i] != null) {
                n++;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if (compareTanggal(riwayatTransaksi[j], riwayatTransaksi[idx])) {
                    idx = j;
                }
            }
            InfoTransaksi temp = riwayatTransaksi[i];
            riwayatTransaksi[i] = riwayatTransaksi[idx];
            riwayatTransaksi[idx] = temp;
        }
    }

    public boolean compareTanggal(InfoTransaksi transaksi1, InfoTransaksi transaksi2) {
        int tahun1 = transaksi1.tahun;
        int bulan1 = transaksi1.bulan;
        int tanggal1 = transaksi1.tanggal;

        int tahun2 = transaksi2.tahun;
        int bulan2 = transaksi2.bulan;
        int tanggal2 = transaksi2.tanggal;

        if (tahun1 != tahun2) {
            return tahun1 > tahun2;
        } else if (bulan1 != bulan2) {
            return bulan1 > bulan2;
        } else {
            return tanggal1 > tanggal2;
        }
    }

}
