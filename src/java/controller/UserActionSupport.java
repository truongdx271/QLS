package controller;

import Char.CharControl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import dao.UserDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import model.Role;
import model.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

public class UserActionSupport extends ActionSupport {

    private List<User> list;
    private User usr;
    private int id;
    private int page;
    private int noOfPages;
    private int noOfRecords;
    private int currentPage;
    private String searchString;

    private String listName;
    private String message;
    private String alert;

    private Map session;

    private List<String> role;
    private List<Role> role2;

    private static final String[] allowedExtension = new String[]{"jpg", "png", "gif"};
    private static final String[] allowedMimetype = new String[]{"image/jpeg", "image/png", "image/gif"};

    private File userImage;
    private String userImageContentType;
    private String userImageFileName;

    UserDAO dao = new UserDAO();
    CharControl cc = new CharControl();

    public List<Role> getRole2() {
        return role2;
    }

    public void setRole2(List<Role> role2) {
        this.role2 = role2;
    }

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

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @VisitorFieldValidator
    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public UserActionSupport() {
    }

    @Override
    public String execute() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet.");
        return "success";
    }

    public String getUser() {

        list = new ArrayList<User>();
        list = dao.getlistUser();
        if (list.isEmpty()) {
            return "empty";
        } else {
            return "success";
        }
    }

    public String getUserPaging() {
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") == 3) {
            if (session.get("message") != null) {
                alert = (String) session.get("message");
                session.remove("message");
            }

//            if (session.get("currentPageUSR") != null || page != 0) {
//                page = (int) session.get("currentPageUSR");
//                session.remove("currentPageUSR");
//            } else {
                page = 1;
//            }
//            if (session.get("searchStringUSR") != null || searchString != null) {
//                searchString = (String) session.get("searchStringUSR");
//                session.remove("searchStringUSR");
//            } else {
//                searchString = null;
//            }

            int recordsPerPage = 5;
            if (ServletActionContext.getRequest().getParameter("page") != null) {
                page = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
            }
            list = new ArrayList<User>();
            list = dao.getListUserPaging((page - 1) * recordsPerPage, recordsPerPage, this.searchString);
            noOfRecords = dao.getNoOfRecords();
            noOfPages = noOfRecords / recordsPerPage + ((noOfRecords % recordsPerPage == 0) ? 0 : 1);
            currentPage = page;

//            session.put("currentPageUSR", currentPage);
//            if(searchString != null){
//                session.put("searchStringUSR", searchString);
//            }
//            else{
//                session.remove("searchStringUSR");
//            }
            

            return "success";
        } else {
            return "fail";
        }

    }

    public String insertUsr() {
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") == 3) {
            if (session.get("message") != null) {
                alert = (String) session.get("message");
                session.remove("message");
            }
//            role = new ArrayList<String>();
//            role.add("Người dùng");
//            role.add("Quản lý");
//            role.add("Admin");
            role2 = new ArrayList<Role>();
            role2.add(new Role(1, "Người dùng"));
            role2.add(new Role(2, "Quản lý"));
            role2.add(new Role(3, "Admin"));

            return "success";
        } else {
            return "fail";
        }

    }

    public String insertUser() {
        String result = "fail";
        if (dao.checkExistUser(usr.getUsername())) {
            message = "Tài khoản đã tồn tại";
            alert(message);
            return result;
        } else {
            if (!dao.isValidUsername(usr.getUsername())) {
                message = "Tài khoản không hợp lệ";
                alert(message);
                return result;
            } else {
                if (!dao.isStrongPassword(usr.getPassword())) {
                    message = "Mật khẩu không hợp lệ";
                    alert(message);
                    return result;
                } else {
                    try {
                        if (this.userImageFileName != null) {
//                            if (cc.checkExtensionImgUpload(this.userImageFileName)) {
                            if (validateFile()) {
                                String filePath = ServletActionContext.getServletContext().getRealPath("/");

                                int a = filePath.indexOf("\\build\\web");
                                filePath = filePath.substring(0, a);
                                filePath = filePath.concat("/web/images");

                                String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                                fileName = fileName.concat(cc.randomString(6)).concat(".").concat(this.userImageFileName.substring(this.userImageFileName.lastIndexOf(".") + 1).toLowerCase());

                                File fileToCreate = new File(filePath, fileName);
                                FileUtils.copyFile(this.userImage, fileToCreate);

                                usr.setAvatar(fileName);
                            } else {
                                return "fail";
                            }
                        } else {
                            usr.setAvatar("default-user-image.png");
                        }
                        usr.setRole(Integer.parseInt(listName));
                        if (dao.insertUser(usr)) {
                            message = "Thêm tài khoản thành công!";
                            alert(message);
                            result = "success";
                        } else {
                            message = "Thêm tài khoản thất bại!";
                            alert(message);
                            result = "fail";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return result;
                }
            }
        }
    }

    public String editUser() {
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") == 3) {
            if (session.get("message") != null) {
                alert = (String) session.get("message");
                session.remove("message");
            }
            if (id == 0) {
                id = (int) session.get("userId");
                session.remove("userId");
            }

            usr = dao.getUserById(id);
            role2 = new ArrayList<Role>();
            role2.add(new Role(1, "Người dùng"));
            role2.add(new Role(2, "Quản lý"));
            role2.add(new Role(3, "Admin"));
            return "success";
        } else {
            return "fail";
        }
    }

    public String preditUser() {
        String result = "fail";
        try {
            if (this.userImageFileName != null) {
//                if (cc.checkExtensionImgUpload(this.userImageFileName)) {
                if (validateFile()) {
                    String filePath = ServletActionContext.getServletContext().getRealPath("/");

                    int a = filePath.indexOf("\\build\\web");
                    filePath = filePath.substring(0, a);
                    filePath = filePath.concat("/web/images");

                    String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                    fileName = fileName.concat(cc.randomString(6)).concat(".").concat(this.userImageFileName.substring(this.userImageFileName.lastIndexOf(".") + 1).toLowerCase());

                    File fileToCreate = new File(filePath, fileName);
                    FileUtils.copyFile(this.userImage, fileToCreate);

                    usr.setAvatar(fileName);
                } else {
                    session = ActionContext.getContext().getSession();
                    session.put("userId", usr.getId());
                    return "fail";
                }
            }
            usr.setRole(Integer.parseInt(listName));

            if (dao.editUser(usr)) {
                message = "Sửa tài khoản thành công!";
                alert(message);
                result = "success";
            } else {
                message = "Sửa tài khoản thất bại!";
                alert(message);
                result = "fail";
            }
        } catch (Exception e) {
            //return "fail";
            e.printStackTrace();
        }
        return result;

    }

    public String deleteUser() {
        String result = "fail";
        try {
            if (dao.deleteUser(id) > 0) {
                message = "Khóa tài khoản <strong>thành công!</strong>";
                alert(message);
                result = "success";
            } else {
                message = "Khóa tài khoản <strong>thất bại!</strong>";
                alert(message);
                result = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        list = new ArrayList<User>();
        list = dao.getlistUser();
        return result;
    }

    public String resetPassword() {
        String result = "fail";
        if (dao.resetPassword(id)) {
            message = "Reset mật khẩu <strong>thành công</strong>";
            alert(message);
            result = "success";
        } else {
            message = "<strong>Thất bại</strong>";
            alert(message);
            result = "fail";
        }

        list = new ArrayList<User>();
        list = dao.getlistUser();
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
        String extension = FilenameUtils.getExtension(userImageFileName);
        boolean extAccepted = Arrays.asList(allowedExtension).contains(extension);
        if (!extAccepted) {
            //addActionError("Extension not allowed, Choose " + Arrays.toString(allowedExtension));
            message = "Phần mở rộng ảnh không hợp lệ";
            alert(message);
            ok &= false;
            if (!ok) {
                return ok;
            }
        }

        // Kiểm tra mime type
        boolean mimeTypeAccepted = Arrays.asList(allowedMimetype).contains(userImageContentType);
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
            BufferedImage image = ImageIO.read(userImage);
            ImageIO.write(image, extension, userImage);
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
