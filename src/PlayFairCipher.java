import java.util.Scanner;

//https://www.youtube.com/watch?v=whEJfas9MAI

public class PlayFairCipher {
    private static String KeyWord        = "";
    private static String Key            = "";
    private static final char[][]  matrix_arr = new char[5][5];

//    We are just removing the redundant characters to form a key (for ex if key is eeaab it will become eab)
    static void setKey(String k)
    {
        k = k.toLowerCase();
        String K_adjust = "";
        boolean flag = false;
        K_adjust = K_adjust + k.charAt(0);
        for (int i = 1; i < k.length(); i++)
        {
            for (int j = 0; j < K_adjust.length(); j++)
            {
                if (k.charAt(i) == K_adjust.charAt(j)) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                K_adjust = K_adjust + k.charAt(i);
            flag = false;
        }
        KeyWord = K_adjust;
        System.out.println("Your Keyword : " + KeyWord);
    }

    static void KeyGen()
    {
        boolean flag = true;
        char current;
        Key = KeyWord;
        for (int i = 0; i < 26; i++)
        {
            current = (char) (i + 97);
            if (current == 'j')
                continue;
            for (int j = 0; j < KeyWord.length(); j++)
            {
                if (current == KeyWord.charAt(j))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
                Key = Key + current;
            flag = true;
        }
        System.out.println("The Codec Matrix :");
        matrix();
    }
//Forming a matrix with the key
    static void matrix()
    {
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                matrix_arr[i][j] = Key.charAt(counter);
                System.out.print(matrix_arr[i][j] + " ");
                counter++;
            }
            System.out.println();
        }
    }

    static String format(String plain_text)
    {
        int i = 0;
        int len = 0;
        String text = "";
        len = plain_text.length();
        for (int tmp = 0; tmp < len; tmp++)
        {
            if (plain_text.charAt(tmp) == 'j')
            {
                text = text + 'i';
            }
            else
                text = text + plain_text.charAt(tmp);
        }
        len = text.length();
        for (i = 0; i < len-1; i = i + 2)
        {
            if (text.charAt(i + 1) == text.charAt(i))
            {
                text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
            }
        }
        return text;
    }

//    Split the string into two pairs (for forming Diagram)
    static String[] Divid2Pairs(String new_string)
    {
        String Original = format(new_string);
        int size = Original.length();
        if (size % 2 != 0)
        {
            size++;
            Original = Original + 'x';
        }
        String[] x = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++)
        {
            x[i] = Original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
    }

//    Getting the position of the character in the char array
    static int[] GetDiminsions(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix_arr[i][j] == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

    static String encryptMessage(String Source)
    {
        Source = Source.toLowerCase();
        String[] src_arr = Divid2Pairs(Source);
        String Code = "";
        char one;
        char two;
        int[] part1 = new int[2];
        int[] part2 = new int[2];
        for(int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
//            Same Row
            if (part1[0] == part2[0])
            {
                if (part1[1] < 4)
                    part1[1]++;
                else
                    part1[1] = 0;
                if (part2[1] < 4)
                    part2[1]++;
                else
                    part2[1] = 0;
            }
//            Same Column
            else if (part1[1] == part2[1])
            {
                if (part1[0] < 4)
                    part1[0]++;
                else
                    part1[0] = 0;
                if (part2[0] < 4)
                    part2[0]++;
                else
                    part2[0] = 0;
            }
//            Rectangle Last case Swap the Columns
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code = Code + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return Code;
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to PlayFair Cipher");
        System.out.println("Enter the Keyword");
        Scanner sc = new Scanner(System.in);
        String keyword = sc.nextLine();
        setKey(keyword);
        KeyGen();
        System.out.println("Enter the plaintext to be encrypted");
        String key_input = sc.nextLine();
        System.out.println("Your Encrypted Message: " + encryptMessage(key_input));

    }
}
