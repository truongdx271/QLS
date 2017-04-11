package dao;

import Char.CharControl;
import connection.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Comment;

public class BookDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private int noOfRecords;

    CharControl cc = new CharControl();

    public BookDAO() {
        conn = DBUtil.getConnection();
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<Book> getListBookPaging(int offset, int noOfRecords, String searchString) {
        List<Book> list = null;
        try {
            String sql;
            if (searchString != null) {
                searchString = cc.replaceChar(searchString);
                sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tblbook WHERE Title LIKE ? OR Author LIKE ? ORDER BY Id DESC LIMIT ?,? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + searchString + "%");
                ps.setString(2, "%" + searchString + "%");
                ps.setInt(3, offset);
                ps.setInt(4, noOfRecords);
            } else {
                sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tblbook ORDER BY Id DESC LIMIT ?,?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, offset);
                ps.setInt(2, noOfRecords);
            }
            rs = ps.executeQuery();
            if (rs != null) {
                list = new ArrayList<Book>();
                Book b = null;
                while (rs.next()) {
                    b = new Book();
                    b.setId(rs.getInt("Id"));
                    b.setTitle(rs.getString("Title"));
                    b.setAuthor(rs.getString("Author"));
                    b.setPrice(rs.getLong("Price"));
                    b.setDescription(rs.getString("Description"));
                    b.setImage(rs.getString("Image"));

                    list.add(b);
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

    public boolean insertBook(Book b) {
        boolean result = false;
        try {
            String sql = "INSERT INTO `tblbook` ("
                    + "`Title`,`Author`,`Price`,`Description`,`Image`)"
                    + "VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setLong(3, b.getPrice());
            ps.setString(4, b.getDescription());
            ps.setString(5, b.getImage());

            if (ps.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean editBook(Book b) {
        boolean result = false;
        try {
            String sql = "UPDATE `tblbook` SET Title=?,Author=?,Price=?,Description=?,Image=? WHERE Id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setLong(3, b.getPrice());
            ps.setString(4, b.getDescription());
            ps.setString(5, b.getImage());
            ps.setInt(6, b.getId());

            if (ps.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Book getBookById(int id) {
        Book b = null;
        try {
            String sql = "SELECT * FROM `tblbook` WHERE Id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs != null) {
                b = new Book();
                while (rs.next()) {
                    b.setId(rs.getInt("Id"));
                    b.setTitle(rs.getString("Title"));
                    b.setAuthor(rs.getString("Author"));
                    b.setPrice(rs.getLong("Price"));
                    b.setDescription(rs.getString("Description"));
                    b.setImage(rs.getString("Image"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public int deleteBook(int id) {
        try {
            String sql = "DELETE FROM `tblbook` WHERE `Id` = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Comment> getCommentByBook(int id) {
        List<Comment> listCom = null;
        try {
            String sql = "SELECT C.*,B.Title,U.Fullname,U.Avatar FROM `tblcomment` C  INNER JOIN `tblbook` B ON C.BookId = B.Id INNER JOIN `tbluser` U ON C.UserId = U.Id WHERE BookId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs != null) {
                listCom = new ArrayList<Comment>();
                Comment c = null;
                while (rs.next()) {
                    c = new Comment();
                    c.setId(rs.getInt("Id"));
                    c.setBookId(rs.getInt("BookId"));
                    c.setBookTitle(rs.getString("Title"));
                    c.setUserId(rs.getInt("UserId"));
                    c.setUserName(rs.getString("Fullname"));
                    c.setContent(rs.getString("Content"));
                    c.setAvatar(rs.getString("Avatar"));
                    c.setCreatedDate(rs.getString("CreatedDate"));

                    listCom.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCom;
    }

    public boolean insertComment(Comment c) {
        boolean result = false;
        try {
            String sql = "INSERT INTO `tblcomment` ("
                    + "`BookId`,`UserId`,`Content`,`CreatedDate`)"
                    + "VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getBookId());
            ps.setInt(2, c.getUserId());
            ps.setString(3, c.getContent());
            ps.setString(4, c.getCreatedDate());

            if (ps.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
