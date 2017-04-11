/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import com.opensymphony.xwork2.validator.annotations.*;

/**
 *
 * @author hieuhx1
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String avatar;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @RequiredStringValidator(message="Hãy nhập tài khoản")
//    @StringLengthFieldValidator(minLength = "4", message = "Tài khoản có độ dài tối thiểu 4 kí tự")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//
//    @RequiredStringValidator(message="Hãy nhập mật khẩu")
//    @StringLengthFieldValidator(minLength = "6", message = "Tài khoản có độ dài tối thiểu 6 kí tự")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @RequiredStringValidator(message="Hãy nhập họ và tên")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

//    @EmailValidator(message = "Email không hợp lệ")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
}
