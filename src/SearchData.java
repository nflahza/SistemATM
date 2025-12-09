package src;

public class SearchData {
    public static Nasabah cariNasabahByKartu(int nomorKartu) {
        for (Nasabah nasabah : SistemATM.dataNasabah) {
            if (nasabah.getNomorKartu() == nomorKartu) {
                return nasabah;
            }
        }
        return null; // Jika tidak ditemukan
    }
    public static Nasabah cariNasabahByRekening(int nomorRekening) {
        for (Nasabah nasabah : SistemATM.dataNasabah) {
            if (nasabah.getNomorRekening() == nomorRekening) {
                return nasabah;
            }
        }
        return null; // Jika tidak ditemukan
    }
    public static Bank cariBankByNama(String namaBank) {
        for (Bank bank : SistemATM.dataBank) {
            if (bank.getNama().equalsIgnoreCase(namaBank)) {
                return bank;
            }
        }
        return null; // Jika tidak ditemukan
    }
    
    public static Bank cariBankByAlamat(String alamatBank) {
        for (Bank bank : SistemATM.dataBank) {
            if (bank.getAlamat().equalsIgnoreCase(alamatBank)) {
                return bank;
            }
        }
        return null; // Jika tidak ditemukan

        
    }
    
    
}