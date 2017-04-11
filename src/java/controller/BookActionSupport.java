/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Char.CharControl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.BookDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import model.Book;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author hieuhx1
 */
public class BookActionSupport extends ActionSupport {

    private List<Book> list;
    private Book b;
    private int id;
    private int page;
    private int noOfPages;
    private int noOfRecords;
    private int currentPage;
    private String searchString;

    private String message;
    private String alert;

    private Map session;

    private File bookImage;
    private String bookImageContentType;
    private String bookImageFileName;

    private static final String[] allowedExtension = new String[]{"jpg", "png", "gif"};
    private static final String[] allowedMimetype = new String[]{"image/jpeg", "image/png", "image/gif"};

    BookDAO dao = new BookDAO();
    CharControl cc = new CharControl();

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public Book getB() {
        return b;
    }

    public void setB(Book b) {
        this.b = b;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public File getBookImage() {
        return bookImage;
    }

    public void setBookImage(File bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookImageContentType() {
        return bookImageContentType;
    }

    public void setBookImageContentType(String bookImageContentType) {
        this.bookImageContentType = bookImageContentType;
    }

    public String getBookImageFileName() {
        return bookImageFileName;
    }

    public void setBookImageFileName(String bookImageFileName) {
        this.bookImageFileName = bookImageFileName;
    }

    public BookActionSupport() {
    }

    public String execute() throws Exception {
        return "success";
    }

    public String getBookPaging() {
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") > 1) {
            if (session.get("message") != null) {
                alert = (String) session.get("message");
                session.remove("message");
            }
            page = 1;
            int recordsPerPage = 5;
            if (ServletActionContext.getRequest().getParameter("page") != null) {
                page = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
            }
            list = new ArrayList<Book>();
            list = dao.getListBookPaging((page - 1) * recordsPerPage, recordsPerPage, this.searchString);
            noOfRecords = dao.getNoOfRecords();
            noOfPages = noOfRecords / recordsPerPage + ((noOfRecords % recordsPerPage == 0) ? 0 : 1);
            currentPage = page;
            return "success";
        } else {
            return "fail";
        }

    }

    public String insertB() {
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") > 1) {
            if (session.get("message") != null) {
                alert = (String) session.get("message");
                session.remove("message");
            }
            return "success";
        } else {
            return "fail";
        }
    }

    public String insertBook() {
        String result = "fail";
        try {
            if (this.bookImageFileName != null) {
//                if (cc.checkExtensionImgUpload(this.bookImageFileName)) {
                if (validateFile()) {
                    String filePath = ServletActionContext.getServletContext().getRealPath("/");

                    int a = filePath.indexOf("\\build\\web");
                    filePath = filePath.substring(0, a);
                    filePath = filePath.concat("/web/images/book");

//                String fileName = cc.getSafeFileName(this.bookImageFileName);
//                File fileToCreate = new File(filePath, fileName);
                    String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                    fileName = fileName.concat(cc.randomString(6)).concat(".").concat(this.bookImageFileName.substring(this.bookImageFileName.lastIndexOf(".") + 1).toLowerCase());

                    File fileToCreate = new File(filePath, fileName);
                    FileUtils.copyFile(this.bookImage, fileToCreate);

                    b.setImage(fileName);
                }
                else{
                    return "fail";
                }
            } else {
                b.setImage("default-user-image.png");
            }
            if (dao.insertBook(b)) {
                message = "Thêm sách thành công!";
                alert(message);
                result = "success";
            } else {
                message = "Thêm sách thất bại!";
                alert(message);
                result = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public String editBook() {
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") > 1) {
            if (session.get("message") != null) {
                alert = (String) session.get("message");
                session.remove("message");
            }
            if (id == 0) {
                id = (int) session.get("bookId");
                session.remove("bookId");
            }
            b = dao.getBookById(id);
            return "success";
        } else {
            return "fail";
        }
    }

    public String prEditBook() {
        String result = "fail";
        try {
            if (this.bookImageFileName != null) {
//                if (cc.checkExtensionImgUpload(this.bookImageFileName)) {
                if (validateFile()) {
                    String filePath = ServletActionContext.getServletContext().getRealPath("/");

                    int a = filePath.indexOf("\\build\\web");
                    filePath = filePath.substring(0, a);
                    filePath = filePath.concat("/web/images/book");

                    String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                    fileName = fileName.concat(cc.randomString(6)).concat(".").concat(this.bookImageFileName.substring(this.bookImageFileName.lastIndexOf(".") + 1).toLowerCase());

                    File fileToCreate = new File(filePath, fileName);
                    FileUtils.copyFile(this.bookImage, fileToCreate);

                    b.setImage(fileName);
                } else {
                    session = ActionContext.getContext().getSession();
                    session.put("bookId", b.getId());
                    return "fail";
                }
            } else {
                b.setImage("default-user-image.png");
            }

            if (dao.editBook(b)) {
                message = "Sửa sách <strong>thành công!</strong>";
                alert(message);
                result = "success";
            } else {
                message = "Sửa sách <strong>thất bại!</strong>";
                alert(message);
                result = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String deleteBook() {
        String result = "fail";
        try {
            if (dao.deleteBook(id) > 0) {
                message = "Xóa sách <strong>thành công!</strong>";
                alert(message);
                result = "success";
            } else {
                message = "Xóa sách <strong>thất bại!</strong>";
                alert(message);
                result = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public void alert(String mess) {
        session = ActionContext.getContext().getSession();
        session.put("message", mess);
        session.put("counter", (int) 1);
    }

    private boolean validateFile() {
        boolean ok = true;

        // Kiểm tra phần mở rộng của file
        String extension = FilenameUtils.getExtension(bookImageFileName);
        boolean extAccepted = Arrays.asList(allowedExtension).contains(extension);
        if (!extAccepted) {
            //addActionError("Extension not allowed, Choose " + Arrays.toString(allowedExtension));
            message = "Phần mở rộng không hợp lệ";
            alert(message);
            ok &= false;
            if (!ok) {
                return ok;
            }
        }

        // Kiểm tra mime type
        boolean mimeTypeAccepted = Arrays.asList(allowedMimetype).contains(bookImageContentType);
        if (!mimeTypeAccepted) {
            //addActionError("Mime-Type not allowed, Choose " + Arrays.toString(allowedMimetype));
            message = "File không hợp lệ";
            alert(message);
            ok &= false;
            if (!ok) {
                return ok;
            }
        }

        // Kiểm tra nội dung file có phải file ảnh hay không?
        try {
            BufferedImage image = ImageIO.read(bookImage);
            ImageIO.write(image, extension, bookImage);
        } catch (Exception e) {
            //addActionError("Cannot read image file, File Upload must be image");
            message = "Không thể đọc file. Hãy upload file ảnh";
            alert(message);
            ok &= false;
            if (!ok) {
                return ok;
            }
        }

        return ok;
    }

}
