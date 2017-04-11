package dao;

import Char.CharControl;
import Encryptor.MD5Encryptor;
import Encryptor.SHA256Encryptor;
import connection.DBUtil;
import java.io.*;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private int noOfRecords;
    MD5Encryptor md5 = null;
    SHA256Encryptor sha = null;
    CharControl cc = new CharControl();

    public UserDAO() {
        conn = DBUtil.getConnection();
    }

    public boolean checkLogin(String username, String password) {
        boolean status = false;
        if (username != null && password != null) {
            byte[] salt = getSalt(username);
            String encryptedPwd = sha.Encryptor(password, salt);
            try {
                String sql = "SELECT * FROM tbluser WHERE Username=? AND Password=? AND (Role=3 OR Role=2)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, encryptedPwd);

//                String sql = "SELECT * FROM tbluser WHERE Username='" + username + "' AND Password='" + encryptedPwd + "'";
//                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                status = rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public boolean checkLoginClient(String username, String password) {
        boolean status = false;
        if (username != null && password != null) {
            byte[] salt = getSalt(username);
            String encryptedPwd = sha.Encryptor(password, salt);

            try {
                String sql = "SELECT * FROM tbluser WHERE Username=? AND Password=? AND Role <> 0";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, encryptedPwd);

                rs = ps.executeQuery();
                status = rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public boolean checkExistUser(String username) {
        boolean status = false;
        try {
            String sql = "SELECT Username FROM tbluser WHERE Username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public boolean isValidUsername(String username) {
        if (username == null || username.equals("")) {
            return false;
        }
        return username.matches("^[a-zA-Z0-9_]{6,30}$");
    }

    public boolean isStrongPassword(String password) {
        if (password == null) {
            return false;
        }
        return (password.matches("^((?=(.*[a-zA-Z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s)).{8,}$") && !isBlacklistPassword(password));
    }

    private boolean isBlacklistPassword(String password) {
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(UserDAO.class.getResourceAsStream("/password_blacklist.txt")));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                if (password.equals(line)) {
//                    return true;
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException ex) {
//                }
//            }
//        }
//        return false;
        boolean result = false;

        String BlackListPassword[] = {"123456a@"};
        for (int i = 0; i < BlackListPassword.length; i++) {
            if (password.equals(BlackListPassword[i])) {
                result = true;
                break;
            }
        }
        return result;

    }

    public List<User> getlistUser() {
        List<User> list = null;
        try {
            String sql = "SELECT * FROM tbluser";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs != null) {
                list = new ArrayList<User>();
                User u = null;
                while (rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("Id"));
                    u.setUsername(rs.getString("Username"));
                    u.setPassword(rs.getString("Password"));
                    u.setFullname(rs.getString("Fullname"));
                    u.setEmail(rs.getString("Email"));
                    u.setAvatar(rs.getString("Avatar"));
                    u.setRole(rs.getInt("Role"));

                    list.add(u);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<User> getListUserPaging(int offset, int noOfRecords, String searchString) {
        List<User> list = null;
        try {
            String sql;
            if (searchString != null) {
                searchString = cc.replaceChar(searchString);
                //sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbluser WHERE Username LIKE '%" + searchString + "%' OR Fullname LIKE '%" + searchString + "%' OR Email LIKE '%" + searchString + "%' LIMIT " + offset + "," + noOfRecords;
                sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbluser WHERE Username LIKE ? OR Fullname LIKE ? OR Email LIKE ? ORDER BY Role DESC, Id DESC LIMIT ?,?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + searchString + "%");
                ps.setString(2, "%" + searchString + "%");
                ps.setString(3, "%" + searchString + "%");
                ps.setInt(4, offset);
                ps.setInt(5, noOfRecords);
            } else {
                //sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbluser LIMIT " + offset + "," + noOfRecords;
                sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbluser ORDER BY Role DESC, Id DESC LIMIT ?,?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, offset);
                ps.setInt(2, noOfRecords);
            }
//            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs != null) {
                list = new ArrayList<User>();
                User u = null;
                while (rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("Id"));
                    u.setUsername(rs.getString("Username"));
                    u.setPassword(rs.getString("Password"));
                    u.setFullname(rs.getString("Fullname"));
                    u.setEmail(rs.getString("Email"));
                    u.setAvatar(rs.getString("Avatar"));
                    u.setRole(rs.getInt("Role"));

                    list.add(u);
                }
                rs.close();

                rs = ps.executeQuery("SELECT FOUND_ROWS()");
                if (rs.next()) {
                    this.noOfRecords = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertUser(User usr) {
        boolean result = false;
        byte[] salt = new byte[20];
        new SecureRandom().nextBytes(salt);

        String encryptedPwd = sha.Encryptor(usr.getPassword(), salt);
        try {
            String sql = "INSERT INTO `tbluser` ("
                    + "`Username`,`Password`,`Fullname`,`Email`,`Avatar`,`Role`)"
                    + "VALUES(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usr.getUsername());
            ps.setString(2, encryptedPwd);
            ps.setString(3, usr.getFullname());
            ps.setString(4, usr.getEmail());
            ps.setString(5, usr.getAvatar());
            ps.setInt(6, usr.getRole());

            if (ps.executeUpdate() > 0) {
                usr = getUserByUsername(usr.getUsername());
                if (insertSalt(usr.getId(), salt)) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertSalt(int id, byte[] salt) {
        boolean result = false;
        try {
            String sql = "INSERT INTO `tblsalt`(`UserId`,`Salt`) VALUES (?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setBytes(2, salt);

            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateSalt(int id, byte[] salt) {
        boolean result = false;
        try {
            String sql = "UPDATE `tblsalt` SET `salt` = ? WHERE `UserId` =? ";
            ps = conn.prepareStatement(sql);

            ps.setBytes(1, salt);
            ps.setInt(2, id);

            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public byte[] getSalt(String username) {
        byte[] salt = new byte[20];
        User user = getUserByUsername(username);
        try {
            String sql = "SELECT * FROM `tblsalt` WHERE `UserId`=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());

            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    salt = rs.getBytes("Salt");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salt;
    }

    public boolean editUser(User usr) {
        boolean result = false;
        try {
            String sql = "UPDATE `tbluser` SET Fullname=?,Email=?,Avatar=?,Role=? WHERE Id =?";
            ps = conn.prepareStatement(sql);
//            ps.setString(1, usr.getUsername());
//            ps.setString(2, usr.getPassword());
            ps.setString(1, usr.getFullname());
            ps.setString(2, usr.getEmail());
            ps.setString(3, usr.getAvatar());
            ps.setInt(4, usr.getRole());
            ps.setInt(5, usr.getId());

            if (ps.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getUserById(int id) {
        User usr = null;
        try {
            String sql = "SELECT * FROM `tbluser` WHERE Id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs != null) {
                usr = new User();
                while (rs.next()) {
                    usr.setId(rs.getInt("Id"));
                    usr.setUsername(rs.getString("Username"));
                    usr.setPassword(rs.getString("Password"));
                    usr.setFullname(rs.getString("Fullname"));
                    usr.setEmail(rs.getString("Email"));
                    usr.setAvatar(rs.getString("Avatar"));
                    usr.setRole(rs.getInt("Role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usr;
    }

    public User getUserByUsername(String username) {
        User usr = null;
        try {
            String sql = "SELECT * FROM `tbluser` WHERE username = '" + username + "'";
            ps = conn.prepareStatement(sql);
            //  ps.setString(1, username);

            rs = ps.executeQuery();
            if (rs != null) {
                usr = new User();
                while (rs.next()) {
                    usr.setId(rs.getInt("Id"));
                    usr.setUsername(rs.getString("Username"));
                    usr.setPassword(rs.getString("Password"));
                    usr.setFullname(rs.getString("Fullname"));
                    usr.setEmail(rs.getString("Email"));
                    usr.setAvatar(rs.getString("Avatar"));
                    usr.setRole(rs.getInt("Role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usr;
    }

    public int deleteUser(int id) {
        try {
            String sql = "UPDATE `tbluser` SET `Role`=0 WHERE `Id` = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean resetPassword(int id) {
        boolean result = false;

        String password = "1234567a@";
        byte[] salt = new byte[20];
        new SecureRandom().nextBytes(salt);
        String encryptedPwd = sha.Encryptor(password, salt);
        try {
            String sql = "UPDATE `tbluser` SET Password=? WHERE Id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, encryptedPwd);
            ps.setInt(2, id);
            if (ps.executeUpdate() > 0) {
                //Sua thanh update
                if (updateSalt(id, salt)) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean changePassword(int id, String newPassword) {
//        String str1 = userName.concat(newPassword);
//        String encryptedPwd = md5.Encryptor(str1);
//        encryptedPwd = md5.Encryptor(encryptedPwd.concat(newPassword));
        boolean result = false;
        byte[] salt = new byte[20];
        new SecureRandom().nextBytes(salt);
        String encryptedPwd = sha.Encryptor(newPassword, salt);
        try {
            String sql = "UPDATE `tbluser` SET Password = ? WHERE Id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, encryptedPwd);
            ps.setInt(2, id);

            if (ps.executeUpdate() > 0) {
                if (updateSalt(id, salt)) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
