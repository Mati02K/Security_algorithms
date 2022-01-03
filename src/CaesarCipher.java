import java.util.Scanner;

public class CaesarCipher {

    static String encrypt(String plaintext){
        int shift = 3;
        String ciphertxt = "";
        char[] ch = plaintext.toCharArray();
        int originalAlphabetPosition = 0;
        int newAlphabetPosition = 0;
        char newCharacter;
        for (char character : ch) {
            if (character != ' ') {
                originalAlphabetPosition = character - 'a';
                //  e = (x + n) mod 26
                newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                newCharacter = (char) ('a' + newAlphabetPosition);
                ciphertxt += newCharacter;
            } else {
                ciphertxt += character;
            }
        }
        return ciphertxt.toUpperCase();
    }

    static String decrypt(String ciphertext){
        ciphertext = ciphertext.toLowerCase();
        int shift = 3;
        String plaintext = "";
        char[] ch = ciphertext.toCharArray();
        int originalAlphabetPosition = 0;
        int newAlphabetPosition = 0;
        char newCharacter;
        for (char character : ch) {
            if (character != ' ') {
                originalAlphabetPosition = character - 'a';
                newAlphabetPosition = (originalAlphabetPosition - shift) % 26;
                if (newAlphabetPosition < 0)
                {
                    newAlphabetPosition = 26 + newAlphabetPosition;
                }
                newCharacter = (char) ('a' + newAlphabetPosition);
                plaintext += newCharacter;
            } else {
                plaintext += character;
            }
        }
        return plaintext.toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println("Select any one of the options (1,2,3)");
        System.out.println("1. Encrypt a Text");
        System.out.println("2. Decrypt the Text");
        System.out.println("3. Exit");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        Scanner read = new Scanner(System.in);
        if(option == 1)
        {
            System.out.println("Enter the PlainText");
            String plainText = read.nextLine();
            System.out.println("The Cipher Text is: " +encrypt(plainText));
        }
        else if (option == 2)
        {
            System.out.println("Enter the CipherText");
            String cipherText = read.nextLine();
            System.out.println("The Plain Text is: " + decrypt(cipherText));
        }
        else
        {
            System.out.println("Bye Then...< / >");
        }
        read.close();
        sc.close();
//        System.out.println(encrypt("information technology"));
//        System.out.println(decrypt("LQIRUPDWLRQ WHFKQRORJB"));

    }
}
