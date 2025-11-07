package src;

public class DataAwal {
    
    public static void initialize() {
        Bank BCA = new Bank("Bank Central Asia", "Jakarta", "Jl. MH Thamrin No.1, Jakarta");
        Bank Mandiri = new Bank("Bank Mandiri", "Bandung", "Jl. Asia Afrika No.10, Bandung");
        Bank BNI = new Bank("Bank Negara Indonesia", "Surabaya", "Jl. Basuki Rahmat No.5, Surabaya");

        Nasabah Anto = new Nasabah("Anto", 123456789, BCA, 5000000, 111222333, 1234);
        Nasabah Budi = new Nasabah("Budi", 987654321, Mandiri, 3000000, 444555666, 5678);
        Nasabah Citra = new Nasabah("Citra", 555666777, BNI, 7000000, 777888999, 9999);
        SistemATM.dataBank = new Bank[]{BCA, Mandiri, BNI};
        SistemATM.dataNasabah = new Nasabah[]{Anto, Budi, Citra};
    }
}
