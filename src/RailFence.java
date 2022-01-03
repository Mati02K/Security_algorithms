import java.util.Arrays;
import java.util.Scanner;

public class RailFence {

    public static final char[][] code = new char[1000][1000];

    static String encrypt(String plaintext,int depth){
        int position = 0;
        int cur = 0;
        boolean downflag = false;
//        Start Positions
        int row = 0;
        int col = 0;
        while(position < plaintext.length()){
            cur = 1;
            while(cur < depth){
                if(plaintext.charAt(position) != ' ')
                {
                // Down
                if(downflag){
                    if(cur == 1){
                        code[row][col] = plaintext.charAt(position);
                    }
                    else {
                        row += 1;
                        col += 1;
                        code[row][col] = plaintext.charAt(position);
                    }
                }
                //Up
                else{
                    if(cur == 1){
                        code[row][col] = plaintext.charAt(position);
                    }
                    else {
                        row -= 1;
                        col += 1;
                        code[row][col] = plaintext.charAt(position);
                    }
                }
                cur += 1;
                }
                position += 1;
            }
            if(downflag){
                row -= 1;
                col += 1;
                downflag = false;
            }
            else{
                row += 1;
                col += 1;
                downflag = true;
            }
        }

        StringBuilder cipherTxt = new StringBuilder();
        char tmp;
        for(int r = 0; r < depth; r++){
            for(int c = 0; c < code[r].length; c++){
                tmp = code[r][c];
                if((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z')){
                    cipherTxt.append(tmp);
                }
            }
        }
        return String.valueOf(cipherTxt).toUpperCase();
    }

    public static void main(String[] args) {
//        Problem works only for depth 2
        System.out.println("Enter the No of Rails or Depth");
        Scanner sc = new Scanner(System.in);
        int depth = sc.nextInt();
        System.out.println("Enter the PlainText");
        Scanner txt = new Scanner(System.in);
        String plaintxt = txt.nextLine();
        System.out.println("Your RailFence Cipher : " + encrypt(plaintxt,depth));

    }
}
