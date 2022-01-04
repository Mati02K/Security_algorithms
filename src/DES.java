import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES {
    public static void main(String[] args) {
        try {
            System.out.println("Message Encryption using DES");
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keyGenerator.generateKey();
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] text = "Secret Information".getBytes();
            System.out.println("Message [Byte Format]" + text);
            System.out.println("Message" + String.valueOf(text));
            byte[] textEncrypted = desCipher.doFinal(text);
            System.out.println("Encrypted Message" + textEncrypted);
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);
            System.out.println("Decrypted Message" + String.valueOf(textDecrypted));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
