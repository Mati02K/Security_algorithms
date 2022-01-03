import java.util.HashSet;
import java.util.Scanner;

public class ColumnTransformation {

    static int[] idetifySeq(String key){
        int[] keyseq = new int[key.length()];
        int pos = 0;
        for(char ch : key.toCharArray()){
            keyseq[pos] = ch - 'a';
            pos++;
        }
        int[] seq = new int[key.length()];
        HashSet<Integer> seen = new HashSet<>();
        for(int i = 0 ; i < seq.length; i++){
            int curpos = 1;
            for(int j = 0; j < seq.length; j++){
                if(j == i){
                    continue;
                }
                if(keyseq[j] < keyseq[i]){
                    curpos++;
                }
                if(keyseq[j] == keyseq[i] && seq[j] != 0 ){
                    seq[i] = seq[j] + 1;
                    seen.add(seq[j] + 1);
                    break;
                }
            }
            if(seq[i] == 0){
                if(seen.contains(curpos)){
                    seq[i] = curpos + 1;
                }
                else {
                    seen.add(curpos);
                    seq[i] = curpos;
                }
            }
        }
        return seq;
    }

    static String returnString(int col, char[][] matrix){
        StringBuilder res = new StringBuilder();
        for(int row = 0; row < matrix.length; row++){
            if(matrix[row][col] >= 'a' && matrix[row][col] <= 'z' || matrix[row][col] >= 'A' && matrix[row][col] <= 'Z'){
                res.append(matrix[row][col]);
            }
            else{
                res.append(" ");
            }

        }
        return res.toString();
    }
    static String encrypt(String plainTxt, String key){
        int n = key.length();

        char[][] code = new char[n][n];
        char[] characters = plainTxt.toCharArray();
        int position = 0;
        for(int row = 0; row < code.length; row++){
            for(int col = 0; col < code[row].length; col++){
                if(position >= characters.length){
                    break;
                }
                code[row][col] = characters[position];
                position++;
            }
            if(position >= characters.length){
                break;
            }
        }
        int[] order = idetifySeq(key.toLowerCase());
        StringBuilder ciphertxt = new StringBuilder();
        for (int j : order) {
            ciphertxt.append(returnString(j - 1, code));
        }
        return String.valueOf(ciphertxt).toUpperCase();
    }

    public static void main(String[] args) {

        System.out.println("Column Transformation Algorithm");
        System.out.println("Enter the Key");
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        System.out.println("Enter the Plain Text");
        String plaintxt = sc.nextLine();
        System.out.println("Cipher Text : " + encrypt(plaintxt,key));
        sc.close();
    }
}
