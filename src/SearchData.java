package src;

public class SearchData {
    public static Nasabah cariNasabahByKartu(long nomorKartu) {
        for (Nasabah nasabah : SistemATM.dataNasabah) {
            if (nasabah.nomorKartu == nomorKartu) {
                return nasabah;
            }
        }
        System.out.println("\nNasabah dengan nomor kartu tersebut tidak ditemukan\n");
        return null;
    }

    public static Nasabah cariNasabahByRekening(long nomorRekening) {
        for (Nasabah nasabah : SistemATM.dataNasabah) {
            if (nasabah.nomorRekening == nomorRekening) {
                return nasabah;
            }
        }
        System.out.println("\nNasabah dengan nomor rekening tersebut tidak ditemukan\n");

        return null;
    }

    public static Bank cariBankByNama(String namaBank) {
        for (Bank bank : SistemATM.dataBank) {
            if (bank.nama.equalsIgnoreCase(namaBank)) {
                return bank;
            }
        }
        System.out.println("\nBank dengan nama tersebut tidak ditemukan\n");
        return null;
    }
}