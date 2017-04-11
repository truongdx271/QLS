/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Char;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author hieuhx1
 */
public class CharControl {

    private static final String[] allowedExtension = new String[]{"jpg", "png", "gif"};
    private static final String[] allowedMimetype = new String[]{"image/jpeg", "image/png", "image/gif"};

    public String replaceChar(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                case '/':
                    sb.append("&#x2F;");
                    break;
                case '\'':
                    sb.append("&#x27;");
                    break;
                case '\n':
                    sb.append("&lt;br/&gt;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public String getSafeFileName(String input) {
//        StringBuilder sb = new StringBuilder();
//        String fileName = input.substring(0, input.lastIndexOf("."));
//        String extension = input.substring(input.lastIndexOf("."));
//        for (int i = 0; i < fileName.length(); i++) {
//            char c = fileName.charAt(i);
//            if (c != '/' && c != '\\' && c != 0 && c != '.') {
//                sb.append(c);
//            }
//        }
//        sb.append(extension);
//        return sb.toString();
        return input;

        //        String[] typeList = {"jpg", "jpeg", "png"};
//        StringBuilder sb = new StringBuilder();
//
//        if (input.contains(".")) {
//            String fileName = input.substring(0, input.lastIndexOf("."));
//            String fileType = input.substring(input.lastIndexOf(".") + 1, input.length()).toLowerCase();
//            for (int i = 0; i < fileName.length(); i++) {
//                char c = fileName.charAt(i);
////                if (c != '/' && c != '\\' && c != 0 && c != '.') {
////                    sb.append(c);
////                }
//                sb.append(c);
//            }
//            sb.append('.');
//            for (int i = 0; i < typeList.length; i++) {
//                if (fileType.compareTo(typeList[i]) == 0) {
//                    sb.append(fileType);
//                    break;
//                }
//            }
//            return sb.toString();
//        } else {
//            return null;
//        }
    }

    public boolean checkExtensionImgUpload(String fileName) {
        if (fileName != null) {
            if (fileName.contains(".")) {
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (extension.equals("jpg") || extension.equals("jpeg")
                        || extension.equals("png") || extension.equals("bmp")) {
                    return true;
                }
                return false;

            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public String randomString(int size) {

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

    public boolean isValidInput(String input) {
        if (input == null) {
            return false;
        }
        return input.matches("^[a-zA-Z0-9]*$");
    }

    public boolean isInValidInput(String input) {
        if (input == null) {
            return true;
        }
        Pattern p = Pattern.compile("[()='\\[\\]:,*/ ]");
        Matcher m = p.matcher(input);
        return m.find();
    }

//    private boolean validateFile() {
//	boolean ok = true;
//
//	// Kiểm tra phần mở rộng của file
//	String extension = FilenameUtils.getExtension(fileUploadFileName);
//	boolean extAccepted = Arrays.asList(allowedExtension).contains(extension);
//	if (!extAccepted) {
//		//addActionError("Extension not allowed, Choose " + Arrays.toString(allowedExtension));
//		ok &= false;
//		if (!ok) return ok;
//	}
//
//	// Kiểm tra mime type
//	boolean mimeTypeAccepted = Arrays.asList(allowedMimetype).contains(fileUploadContentType);
//	if (!mimeTypeAccepted) {
//		//addActionError("Mime-Type not allowed, Choose " + Arrays.toString(allowedMimetype));
//		ok &= false;
//		if (!ok) return ok;
//	}
//
//	// Kiểm tra nội dung file có phải file ảnh hay không?
//	try {
//		BufferedImage image = ImageIO.read(fileUpload);
//		ImageIO.write(image, extension, fileUpload);
//	} catch (Exception e) {
//		//addActionError("Cannot read image file, File Upload must be image");
//		ok &= false;
//		if (!ok) return ok;
//	}
//
//	return ok;
//}
}
