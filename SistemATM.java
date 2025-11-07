import java.util.Scanner;

public class SistemATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int bankIndex = input.nextInt();
        System.out.println("Sistem ATM | Bank ID: " + bankIndex);
        input.close();
    }
}