package src;

import src.InfoTransaksi;

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

    // Getter Methods
    public String getNama() {
        return nama;
    }

    public long getNomorRekening() {
        return nomorRekening;
    }

    public Bank getBank() {
        return bank;
    }

    public double getSaldo() {
        return saldo;
    }

    public long getNomorKartu() {
        return nomorKartu;
    }

    public int getPin() {
        return pin;
    }

    public boolean getDiblokir() {
        return diblokir;
    }

    // Setter Methods
    public void setNama(String namaBaru) {
        this.nama = namaBaru;
    }

    public void setNomorRekening(int nomorRekeningBaru) {
        this.nomorRekening = nomorRekeningBaru;
    }

    public void setBank(Bank bankBaru) {
        this.bank = bankBaru;
    }

    public void setSaldo(double saldoBaru) {
        this.saldo = saldoBaru;
    }

    public void setNomorKartu(int nomorKartuBaru) {
        this.nomorKartu = nomorKartuBaru;
    }

    public void setPin(int pinBaru) {
        this.pin = pinBaru;
    }

    public void setDiblokir(boolean statusBaru) {
        this.diblokir = statusBaru;
    }

    // Procedure
    public void tambahTransaksi(InfoTransaksi transaksiBaru) {
        for (int i = 0; i < riwayatTransaksi.length; i++) {
            if (riwayatTransaksi[i] == null) {
                riwayatTransaksi[i] = transaksiBaru;
                break;
            }
        }
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
}
