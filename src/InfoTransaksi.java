package src;

public class InfoTransaksi {
    double nominal;
    Bank bank;
    String jenisTransaksi;
    Nasabah nasabahTarget;
    int tanggal;
    int bulan;
    int tahun;

    public InfoTransaksi(double nominal, Bank bank, String jenisTransaksi, Nasabah nasabahTarget, int tanggal,
            int bulan, int tahun) {
        this.nominal = nominal;
        this.bank = bank;
        this.jenisTransaksi = jenisTransaksi;
        this.nasabahTarget = nasabahTarget;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
    }
}
