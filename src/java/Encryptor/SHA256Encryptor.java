/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryptor;



//import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.captcha.botdetect.internal.infrastructure.io.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author vm-sv04
 */
public class SHA256Encryptor {
    public static String Encryptor(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = new String(salt) + password;
            byte[] hash = digest.digest(input.getBytes());
            return Base64.encodeBytes(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
