package src;

public class DataAwal {

    public static void initialize() {
        Bank BCA = new Bank("Bank Central Asia", "Jakarta", "Jl. MH Thamrin No.1, Jakarta");
        Bank Mandiri = new Bank("Bank Mandiri", "Bandung", "Jl. Asia Afrika No.10, Bandung");
        Bank BNI = new Bank("Bank Negara Indonesia", "Surabaya", "Jl. Basuki Rahmat No.5, Surabaya");

        Nasabah Anto = new Nasabah("Anto", 87846483778L, BCA, 5000000, 1111222233334444L, 123456, false);
        Nasabah Budi = new Nasabah("Budi", 987654321012L, Mandiri, 3000000, 4444555566667777L, 567890, false);
        Nasabah Citra = new Nasabah("Citra", 555666777012L, BNI, 7000000, 1234567890123456L, 999999, false);
        SistemATM.dataBank[0] = BCA;
        SistemATM.dataBank[1] = Mandiri;
        SistemATM.dataBank[2] = BNI;
        SistemATM.dataNasabah[0] = Anto;
        SistemATM.dataNasabah[1] = Budi;
        SistemATM.dataNasabah[2] = Citra;

        Anto.tambahTransaksi(new InfoTransaksi(1000000, BCA, "Setor Tunai", null, 10, 1, 2024));
        Anto.tambahTransaksi(new InfoTransaksi(500000, Mandiri, "Transfer", Budi, 12, 12, 2024));
        Anto.tambahTransaksi(new InfoTransaksi(-500000, Mandiri, "Transfer", Citra, 12, 12, 2024));

        Budi.tambahTransaksi(new InfoTransaksi(-500000, Mandiri, "Transfer", Anto, 12, 12, 2024));
        Budi.tambahTransaksi(new InfoTransaksi(-300000, Mandiri, "Tarik Tunai", null, 13, 6, 2024));

        Citra.tambahTransaksi(new InfoTransaksi(1500000, BNI, "Setor Tunai", null, 14, 5, 2024));
        Citra.tambahTransaksi(new InfoTransaksi(-2000000, BNI, "Tarik Tunai", null, 15, 10, 2024));
        Citra.tambahTransaksi(new InfoTransaksi(500000, BNI, "Transfer", Anto, 12, 12, 2024));
    }
}
