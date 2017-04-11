/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hieuhx1
 */
public class DBUtil {
    private static Connection conn = null;
    public static Connection getConnection(){
        if(conn!=null){
            return conn;
        }
        else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/quanlysach?useUnicode=yes&characterEncoding=UTF-8","root","root");
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            catch(SQLException e){
               e.printStackTrace();
            }
            return conn;
        }
    }
}
