package src;

import src.InfoTransaksi;

public class Nasabah {
    String nama;
    int nomorRekening;
    Bank bank;
    double saldo = 0;
    InfoTransaksi[] riwayatTransaksi;
    int nomorKartu;
    int pin;

    public Nasabah(String nama, int nomorRekening, Bank bank, double saldo, int nomorKartu, int pin) {
        this.nama = nama;
        this.nomorRekening = nomorRekening;
        this.bank = bank;
        this.saldo = saldo;
        this.nomorKartu = nomorKartu;
        this.pin = pin;
    }
    // Getter Methods
    public String getNama() {
        return nama;
    }

    public int getNomorRekening() {
        return nomorRekening;
    }

    public Bank getBank() {
        return bank;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNomorKartu() {
        return nomorKartu;
    }

    public int getPin() {
        return pin;
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
    // Methods
    public void tambahTransaksi(InfoTransaksi transaksiBaru) {
        if (riwayatTransaksi == null) {
            riwayatTransaksi = new InfoTransaksi[1];
            riwayatTransaksi[0] = transaksiBaru;
        } else {
            InfoTransaksi[] temp = new InfoTransaksi[riwayatTransaksi.length + 1];
            for (int i = 0; i < riwayatTransaksi.length; i++) {
                temp[i] = riwayatTransaksi[i];
            }
            temp[riwayatTransaksi.length] = transaksiBaru;
            riwayatTransaksi = temp;
        }
    }
    public void terimaTransfer(int jumlah) {
        this.saldo += jumlah;
    }
    public void kirimTransfer(int jumlah) {
        if (saldo >= jumlah) {
            this.saldo -= jumlah;
        } else {
            System.out.println("Saldo anda tidak cukup!");
        }
    }
}
