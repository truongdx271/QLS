/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Encryptor.MD5Encryptor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDAO;
import java.util.Map;
import model.User;
import org.apache.struts2.dispatcher.SessionMap;

/**
 *
 * @author hieuhx1
 */
public class Login2ActionSupport extends ActionSupport {

    public Login2ActionSupport() {
    }
    private String message;
    private User usr = new User();
    UserDAO dao = new UserDAO();
    MD5Encryptor md5 = new MD5Encryptor();
    private Map session;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {

//        String str1 = usr.getUsername().concat(usr.getPassword());
//        String encryptedPwd = md5.Encryptor(str1);
//        encryptedPwd = md5.Encryptor(encryptedPwd.concat(usr.getPassword()));

        return "success";
    }
    
    public String logIn(){
        if (dao.checkLoginClient(usr.getUsername(), usr.getPassword())) {
            usr = dao.getUserByUsername(usr.getUsername());
            session = ActionContext.getContext().getSession();

            String name = null;

            if (usr.getFullname().contains(" ")) {
                name = usr.getFullname().substring(usr.getFullname().lastIndexOf(" "), usr.getFullname().length());
            } else {
                name = usr.getFullname();
            }

            ((SessionMap)this.session).invalidate();
            session.put("USER", name);
            session.put("ROLE", usr.getRole());
            session.put("AVATAR", usr.getAvatar());
            session.put("ID", usr.getId());
            return "success";
        } else {
            message = "Đăng nhập thất bại";
            return "error";
        }
    }
    

    public String logOut() {
        session = ActionContext.getContext().getSession();
//        session.remove("USER");
//        session.remove("ROLE");
//        session.remove("AVATAR");
//        session.remove("ID");
//        session.invalidate();
        session.clear();
        ((SessionMap)this.session).invalidate();
        return "success";
    }

//    public String encrypt(String input) {
//        StringBuffer hexString = new StringBuffer();
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(input.getBytes());
//
//            byte byteData[] = md.digest();
//
//            for (int i = 0; i < byteData.length; i++) {
//                String hex = Integer.toHexString(0xff & byteData[i]);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return hexString.toString();
//    }
}
