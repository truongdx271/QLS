/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.captcha.botdetect.internal.infrastructure.io.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author hieuhx1
 */
public class NewClass {

    public void main(String args[]) {
//        String test = "tkbinhthuong";
//        String test2 = "123456a@";
//        String output = Encryptor(test.concat(test2));
//        System.out.println("Encrypt lan 1: output = " + output);
//        output = Encryptor(output.concat(test2));
//        System.out.println("Encrypt lan 2: output = " + output);

//        Encrypt SHA
//        String password = "123456a@";
//        for (int i = 0; i < 9; i++) {
//            byte[] salt = new byte[20];
//            new SecureRandom().nextBytes(salt);
//            String output = SHA256(password, salt);
//            System.out.println("Lan" + i + ": " + output + " salt: "+salt);
//        }
        Properties prop = new Properties();
        String filePath = "";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("message.properties");
            prop.load(inputStream);
            filePath = prop.getProperty("database");
            System.out.println("database: "+filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String Encryptor(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String SHA256(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = new String(salt) + password;
            byte[] hash = digest.digest(input.getBytes());
            return Base64.encodeBytes(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSafeFileName(String input) {
        String[] typeList = {"jpg", "jpeg", "png"};
        StringBuilder sb = new StringBuilder();

        if (input.contains(".")) {
            String fileName = input.substring(0, input.lastIndexOf("."));
            String fileType = input.substring(input.lastIndexOf(".") + 1, input.length());
            for (int i = 0; i < fileName.length(); i++) {
                char c = fileName.charAt(i);
                if (c != '/' && c != '\\' && c != 0 && c != '.') {
                    sb.append(randomString(1));
                }
            }

            sb.append('.');
            for (int i = 0; i < typeList.length; i++) {
                if (fileType.compareTo(typeList[i]) == 0) {
                    sb.append(fileType);
                    break;
                }
            }
            return sb.toString();
        } else {
            return null;
        }
    }

    public static String randomString(int size) {

        String str01 = "abcdefghijklmnopqrstuvwxyz";
        String str02 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str03 = "0123456789";

        // These are the valid charecters use to random.
        // Đây là các ký tự được dùng để chuỗi ký tự ngẫu nhiên.
        String strValid = str01 + str02 + str03;

        Random random = new Random();

        String mystring = "";
        for (int i = 0; i < size; i++) {
            int randnum = random.nextInt(strValid.length());
            mystring = mystring + strValid.charAt(randnum);
        }

        return mystring;

    }

}
