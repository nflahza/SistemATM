package src;

public class Bank {
    String nama;
    String cabang;
    String alamat;

    public Bank(String nama, String cabang, String alamat) {
        this.nama = nama;
        this.cabang = cabang;
        this.alamat = alamat;
    }
    // Getter Methods
    public String getNama() {
        return nama;
    }
    public String getCabang() {
        return cabang;
    }
    public String getAlamat() {
        return alamat;
    }
    // Setter Methods
    public void setNama(String namaBaru) {
        this.nama = namaBaru;
    }
    public void setCabang(String cabangBaru) {
        this.cabang = cabangBaru;
    }
    public void setAlamat(String alamatBaru) {
        this.alamat = alamatBaru;
    }
}
