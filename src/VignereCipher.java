
import java.util.Scanner;

public class VignereCipher {

    static String extendKey(String txt, String key){
        StringBuilder res = new StringBuilder();
        int len = txt.length();
        int keylen = key.length();
        int pointer = 0;
        char tmp;
        res.append(key);
        while(keylen != len){
            if(pointer >= key.length())
            {
                pointer = 0;
            }
            tmp = key.charAt(pointer);
            res.append(tmp);
            pointer += 1;
            keylen += 1;
        }
        return res.toString();
    }

    static int[] buildTable(String txt){
        int[] res = new int[txt.length()];
        char[] character = txt.toCharArray();
        int calc;
        int pos = 0;
        for(char ch: character){
            if(ch != ' '){
                calc = ch - 'a';
                res[pos] = calc;
                pos++;
            }
        }

        return res;
    }
    static String encrypt(String plaintxt, String key){
        plaintxt = plaintxt.toLowerCase();
        key = key.toLowerCase();
        String ciphertxt = "";
        int len = plaintxt.length();
        int keylen = key.length();

        if(keylen < len){
            key = extendKey(plaintxt,key);
        }

        int[] keyTable = buildTable(key);
        int[] txtTable = buildTable(plaintxt);
        int[] encryptedTable = new int[len];

        for(int i = 0; i < len; i++){
            encryptedTable[i] = (keyTable[i] + txtTable[i]) % 26;
        }
        char tmp;
        String lookup = "abcdefghijklmnopqrstuvwxyz";
        for(int pos : encryptedTable){
            tmp = lookup.charAt(pos);
            ciphertxt += tmp;
        }

        return ciphertxt.toUpperCase();
    }

    static int findCorrectDet(int num)
    {
        if(num < 0){
            return 26 + num;
        }
        return num;
    }

    static String decrypt(String cipherTxt, String key){
        String plaintxt = "";
        cipherTxt = cipherTxt.toLowerCase();
        key = key.toLowerCase();

        int len = cipherTxt.length();
        int keylen = key.length();

        if(keylen < len){
            key = extendKey(cipherTxt,key);
        }

        int[] keyTable = buildTable(key);
        int[] txtTable = buildTable(cipherTxt);
        int[] decryptedTable = new int[len];

        for(int i = 0; i < len; i++){
            decryptedTable[i] = findCorrectDet((txtTable[i] - keyTable[i]) % 26);
        }
        char tmp;
        String lookup = "abcdefghijklmnopqrstuvwxyz";
        for(int pos : decryptedTable){
            tmp = lookup.charAt(pos);
            plaintxt += tmp;
        }

        return plaintxt.toUpperCase();
    }

    public static void main(String[] args) {
//        Encrytion Formula C = (P at pos + k at pos) mod 26
//        Decryption Formula P = (C - K) mod 26
        System.out.println("Enter the key");
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        System.out.println("Choose a Option (1, 2, 3)");
        System.out.println("1. Encryption");
        System.out.println("2. Decryption");
        System.out.println("3. Exit");
        int option = sc.nextInt();
        Scanner input = new Scanner(System.in);
        if(option == 1){
            System.out.println("Enter the Plain Text");
            String txt = input.nextLine();
            System.out.println("Cipher Text :" + encrypt(txt,key));
        }
        else if(option == 2){
            System.out.println("Enter the Cipher Text");
            String cipher = input.nextLine();
            System.out.println("Plain Text :" + decrypt(cipher,key));
        }
        else {
            System.out.println("Ok Bye !!!");
        }

    }
}
