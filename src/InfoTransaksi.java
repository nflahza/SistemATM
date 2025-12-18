package src;

import src.Bank;

public class InfoTransaksi {
    double nominal;
    Bank bank;
    String jenisTransaksi;
    Nasabah nasabahTarget;
    int tanggal;
    int bulan;
    int tahun;

    public InfoTransaksi(double nominal, Bank bank, String jenisTransaksi, Nasabah nasabahTarget, int tanggal, int bulan, int tahun) {
        this.nominal = nominal;
        this.bank = bank;
        this.jenisTransaksi = jenisTransaksi;
        this.nasabahTarget = nasabahTarget;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
    }

    // Getter Methods
    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public double getNominal() {
        return nominal;
    }

    public Bank getBank() {
        return bank;
    }

    public Nasabah getNasabahTarget() {
        return nasabahTarget;
    }

    public int getTanggal() {
        return tanggal;
    }

    public int getBulan() {
        return bulan;
    }

    public int getTahun() {
        return tahun;
    }

    // Setter Methods
    public void setJenisTransaksi(String jenisTransaksiBaru) {
        this.jenisTransaksi = jenisTransaksiBaru;
    }
    public void setNominal(double nominalBaru) {
        this.nominal = nominalBaru;
    }
    public void setBank(Bank bankBaru) {
        this.bank = bankBaru;
    }
    public void setNasabahTarget(Nasabah nasabahTargetBaru) {
        this.nasabahTarget = nasabahTargetBaru;
    }
    public void setTanggal(int tanggalBaru) {
        this.tanggal = tanggalBaru;
    }
    public void setBulan(int bulanBaru) {
        this.bulan = bulanBaru;
    }
    public void setTahun(int tahunBaru) {
        this.tahun = tahunBaru;
    }


}
