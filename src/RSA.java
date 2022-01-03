import java.util.Scanner;

//https://www.youtube.com/watch?v=rlJTMUBXhKE
public class RSA {

    static int gcd(int num1, int num2)
    {
        if (num2 == 0)
            return num1;
        return gcd(num2, num1 % num2);
    }

    static int performRSA(int p, int q, int msg){
        int n = p * q;
        int e = (p - 1) * (q - 1); // Euler toilent
        int tmp = 0;
        int encryptedkey = 0;
        for(int ekey = 2; ekey < e; ekey++){
            tmp = gcd(e,ekey);
            if(tmp == 1){
                encryptedkey = ekey;
                break;
            }
        }
        int decryptedkey = 0;
        int x;
        for(int i = 0; i < 10; i++) {
            x = 1 + i * e;
            if (x % encryptedkey == 0) {
                decryptedkey = x / encryptedkey;
                break;
            }
        }
        double encryptedpow = (Math.pow((double) msg, (double) encryptedkey));
        int encryptedmsg = (int) encryptedpow % n;

        System.out.println("Euler Totient :" + String.valueOf(e));
        System.out.println("Public Key :" + String.valueOf(encryptedkey));
        System.out.println("Private Key :" + String.valueOf(decryptedkey));

//        double decryptedpow = (Math.pow((double) encryptedmsg, (double) decryptedkey));
//        int decryptedmsg = (int) decryptedpow % n;
//
//        System.out.println("Decrypted Msg : " + String.valueOf(decryptedmsg));

        return encryptedmsg;
    }
    public static void main(String[] args) {

        System.out.println("Enter the Two Prime Numbers");
        Scanner sc = new Scanner(System.in);
        int p1 = sc.nextInt();
        int p2 = sc.nextInt();
        System.out.println("Enter the Message in numbers like A - 0, B - 1...");
        int msg = sc.nextInt();
        System.out.println("Your Cipher Text : " + String.valueOf(performRSA(p1,p2,msg)));
        sc.close();
    }
}
