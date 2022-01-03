
import java.util.Scanner;

public class HillCipher {

    static StringBuilder doEncrypt(StringBuilder txt, int[][] key) {
//        Getting the position of char
        int[] txtarr = {txt.charAt(0) - 'a', txt.charAt(1) - 'a', txt.charAt(2) - 'a'};
        String lookup = "abcdefghijklmnopqrstuvwxyz";
//        Applying the formula
        int firstno = (txtarr[0] * key[0][0]) + (txtarr[1] * key[1][0]) + (txtarr[2] * key[2][0]);
        int secondno = (txtarr[0] * key[0][1]) + (txtarr[1] * key[1][1]) + (txtarr[2] * key[2][1]);
        int thirdno = (txtarr[0] * key[0][2]) + (txtarr[1] * key[1][2]) + (txtarr[2] * key[2][2]);
        firstno = firstno % 26;
        secondno = secondno % 26;
        thirdno = thirdno % 26;
//        Extracting the text
        char first = lookup.charAt(firstno);
        char second = lookup.charAt(secondno);
        char third = lookup.charAt(thirdno);
        StringBuilder result = new StringBuilder();
        result.append(first);
        result.append(second);
        result.append(third);

        return result;
    }

    static String encryption(String plainText, int[][] key) {
        plainText = plainText.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        int range = 3;
        char[] character = plainText.toCharArray();
        int count = 0;
        StringBuilder tmp = new StringBuilder();
        for(char ch : character){
            if(ch != ' ') {
                tmp.append(ch);
                count++;
                if(count == range){
                    cipherText.append(doEncrypt(tmp,key));
                    tmp.delete(0,3);
                    count = 0;
                }
            }
        }
//        Boundary Condition if it is not 3
        if(tmp.length() > 0){
            int n = tmp.length();
            int res = n;
            while(res != range){
                tmp.append('x');
                res += 1;
            }
            StringBuilder restmp = new StringBuilder();
            restmp.append(doEncrypt(tmp,key));
            restmp.delete(range-n,n);
            cipherText.append(restmp);
        }
        return String.valueOf(cipherText).toUpperCase();
    }

    static int getDeterminant(int[][] matrix) {
        int firstpart = matrix[0][0] * ((matrix[1][1] * matrix[2][2]) - (matrix[2][1] * matrix[1][2]));
        int secondpart = matrix[0][1] * ((matrix[1][0] * matrix[2][2]) - (matrix[2][0] * matrix[1][2]));
        int thirdpart = matrix[0][2] * ((matrix[1][0] * matrix[2][1]) - (matrix[2][0] * matrix[1][1]));
        int determinant = firstpart - secondpart + thirdpart;
        return determinant;
    }

    //    Get determinant of a 2x2 matrix
    static int getTwoDetermiant(int[][] matrix) {
        int firstpart = matrix[0][0] * matrix[1][1];
        int secondpart = matrix[0][1] * matrix[1][0];
        int determinant = firstpart - secondpart;
        return determinant;
    }

    static int findCorrectDet(int num)
    {
        if(num < 0){
            return 26 + num;
        }
        return num;
    }

    static int[][] getAdjoint(int[][] matrix) {
        int[][] adjoint = new int[3][3];

        int[][] a1 = {{matrix[1][1], matrix[1][2]}, {matrix[2][1], matrix[2][2]}};
        int first = getTwoDetermiant(a1);

        int[][] a2 = {{matrix[1][0], matrix[1][2]}, {matrix[2][0], matrix[2][2]}};
        int second = -1 * getTwoDetermiant(a2);

        int[][] a3 = {{matrix[1][0], matrix[1][1]}, {matrix[2][0], matrix[2][1]}};
        int third = getTwoDetermiant(a3);

        int[][] b1 = {{matrix[0][1], matrix[0][2]}, {matrix[2][1], matrix[2][2]}};
        int fourth = -1 * getTwoDetermiant(b1);

        int[][] b2 = {{matrix[0][0], matrix[0][2]}, {matrix[2][0], matrix[2][2]}};
        int fifth = getTwoDetermiant(b2);

        int[][] b3 = {{matrix[0][0], matrix[0][1]}, {matrix[2][0], matrix[2][1]}};
        int sixth = -1 * getTwoDetermiant(b3);

        int[][] c1 = {{matrix[0][1], matrix[0][2]}, {matrix[1][1], matrix[1][2]}};
        int seventh = getTwoDetermiant(c1);

        int[][] c2 = {{matrix[0][0], matrix[0][2]}, {matrix[1][0], matrix[1][2]}};
        int eight = -1 * getTwoDetermiant(c2);

        int[][] c3 = {{matrix[0][0], matrix[0][1]}, {matrix[1][0], matrix[1][1]}};
        int ninth = getTwoDetermiant(c3);

//        Mimicing the Transpose of a matrix
        adjoint[0][0] = findCorrectDet(first % 26);
        adjoint[0][1] = findCorrectDet(fourth % 26);
        adjoint[0][2] = findCorrectDet(seventh % 26);

        adjoint[1][0] = findCorrectDet(second % 26);
        adjoint[1][1] = findCorrectDet(fifth % 26);
        adjoint[1][2] = findCorrectDet(eight % 26);

        adjoint[2][0] = findCorrectDet(third % 26);
        adjoint[2][1] = findCorrectDet(sixth % 26);
        adjoint[2][2] = findCorrectDet(ninth % 26);

        return adjoint;
    }

    // A naive method to find modulor
    // multiplicative inverse of 'a'
    // under modulo 'm'
    static int modInverse(int a, int m)
    {
        for (int x = 1; x < m; x++)
            if (((a%m) * (x%m)) % m == 1)
                return x;
        return 1;
    }

    static int[][] getInverse(int[][] matrix){
        int[][] inverse = new int[3][3];
        int det = getDeterminant(matrix) % 26;
        det = findCorrectDet(det);
        det = modInverse(det,26);
        int[][] adjoint = getAdjoint(matrix);
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[row].length; col++){
                inverse[row][col] = (adjoint[row][col] * det) % 26;
            }
        }
        return inverse;
    }

    static StringBuilder doDecrypt(StringBuilder txt, int[][] key) {
//        Getting the position of char
        int[] txtarr = {txt.charAt(0) - 'a', txt.charAt(1) - 'a', txt.charAt(2) - 'a'};
        String lookup = "abcdefghijklmnopqrstuvwxyz";
//        Applying the formula
        int firstno = (txtarr[0] * key[0][0]) + (txtarr[1] * key[1][0]) + (txtarr[2] * key[2][0]);
        int secondno = (txtarr[0] * key[0][1]) + (txtarr[1] * key[1][1]) + (txtarr[2] * key[2][1]);
        int thirdno = (txtarr[0] * key[0][2]) + (txtarr[1] * key[1][2]) + (txtarr[2] * key[2][2]);
        firstno = firstno % 26;
        secondno = secondno % 26;
        thirdno = thirdno % 26;
//        Extracting the text
        char first = lookup.charAt(firstno);
        char second = lookup.charAt(secondno);
        char third = lookup.charAt(thirdno);
        StringBuilder result = new StringBuilder();
        result.append(first);
        result.append(second);
        result.append(third);

        return result;
    }

    static String decryption(String cipherText,int[][] key){
        cipherText = cipherText.toLowerCase();
        StringBuilder plaintext = new StringBuilder();
        int range = 3;
        char[] character = cipherText.toCharArray();
        int count = 0;
        StringBuilder tmp = new StringBuilder();
        int[][] keyInverse = getInverse(key);
        for(char ch : character){
            if(ch != ' ') {
                tmp.append(ch);
                count++;
                if(count == range){
                    plaintext.append(doDecrypt(tmp,keyInverse));
                    tmp.delete(0,3);
                    count = 0;
                }
            }
        }
        // Boundary Condition if it is not 3
        if(tmp.length() > 0){
            int n = tmp.length();
            int res = n;
            while(res != range){
                tmp.append('x');
                res += 1;
            }
            StringBuilder restmp = new StringBuilder();
            restmp.append(doDecrypt(tmp,keyInverse));
            restmp.delete(range-n,n);
            plaintext.append(restmp);
        }
        return plaintext.toString().toUpperCase();
    }

    public static void main(String[] args) {
        int[][] key = new int[3][3];
        int[][] dummykey = {{17,17,5},{21,18,21},{2,2,19}};
        int tmp = 0;
        System.out.println("Enter the 3 x 3 key (9 elements one by one)");
        Scanner sc = new Scanner(System.in);
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                tmp = sc.nextInt();
                key[row][col] = tmp;
            }
        }
        System.out.println("Enter the Operation you want to perform");
        System.out.println("1. Encryption");
        System.out.println("2. Decryption");
        System.out.println("3. Exit");
        Scanner txt = new Scanner(System.in);
        int option = sc.nextInt();
        if(option == 1){
            System.out.println("Enter the plain text");
            String plaintxt = txt.nextLine();
            System.out.println("Cipher text: " + encryption(plaintxt,key));
        }
        else if(option == 2){
            System.out.println("Enter the cipher text");
            String ciphertxt = txt.nextLine();
            System.out.println("Plain text: " + decryption(ciphertxt,key));
        }
        else{
            System.out.println("Ok Bye !!!!");
        }
        txt.close();
        sc.close();
//        String plaintext = "Paymoremoney";
//        System.out.println(encryption(plaintext,dummykey));
//        System.out.println(decryption("RRLMWBKASPDH",dummykey));

    }
}
