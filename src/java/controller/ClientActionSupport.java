package controller;

import Char.CharControl;
import Encryptor.MD5Encryptor;
import Encryptor.SHA256Encryptor;
import com.captcha.botdetect.web.servlet.Captcha;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.BookDAO;
import dao.UserDAO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

public class ClientActionSupport extends ActionSupport {

    private List<Book> list;
    private List<Book> listHead;
    private List<Book> listTail;
    private List<Comment> listCom;
    private Book b;
    private int id;
    private int page;
    private int noOfPages;
    private int noOfRecords;
    private int currentPage;
    private String content;
    private String searchString;
    private Map<String, Object> session;
    private Comment c;
    private String oldPassword;
    private String newPassword;
    private String rePassword;

    private String message;

    private String captchaCode;

    private User usr;
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;
    private HttpServletRequest servletRequest;
    private Captcha captcha = null;

    private InputStream inputStream;

    private static final String[] allowedExtension = new String[]{"jpg", "png", "gif"};
    private static final String[] allowedMimetype = new String[]{"image/jpeg", "image/png", "image/gif"};

    BookDAO dao = new BookDAO();
    UserDAO usrDao = new UserDAO();
    CharControl cc = new CharControl();
    SHA256Encryptor sha = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public Captcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
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

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Comment getC() {
        return c;
    }

    public void setC(Comment c) {
        this.c = c;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getListCom() {
        return listCom;
    }

    public void setListCom(List<Comment> listCom) {
        this.listCom = listCom;
    }

    public List<Book> getListHead() {
        return listHead;
    }

    public void setListHead(List<Book> listHead) {
        this.listHead = listHead;
    }

    public List<Book> getListTail() {
        return listTail;
    }

    public void setListTail(List<Book> listTail) {
        this.listTail = listTail;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public ClientActionSupport() {
    }

    public String execute() throws Exception {
        return "success";
    }

    public String GetAllPaging() {
        page = 1;
        int recordsPerPage = 8;
        if (ServletActionContext.getRequest().getParameter("page") != null) {
            page = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        }
        list = new ArrayList<Book>();
        list = dao.getListBookPaging((page - 1) * recordsPerPage, recordsPerPage, this.searchString);

        if (list.size() > 4) {
            listHead = list.subList(0, 4);
            listTail = list.subList(4, list.size());
        } else {
            listHead = list;
        }

        noOfRecords = dao.getNoOfRecords();
        noOfPages = noOfRecords / recordsPerPage + ((noOfRecords % recordsPerPage == 0) ? 0 : 1);
        currentPage = page;
        return "success";

    }

    public String bookDetail() {
        b = dao.getBookById(id);
        listCom = new ArrayList<Comment>();
        listCom = dao.getCommentByBook(id);
        return "success";
    }

    public String comment() {
        String result = "fail";
        session = ActionContext.getContext().getSession();
        if (session.containsKey("USER")) {
            try {
                c = new Comment();
                c.setBookId(b.getId());
                c.setContent(cc.replaceChar(content));
//                c.setContent(content);
                c.setUserId((int) session.get("ID"));
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                c.setCreatedDate(timeStamp);

                if (dao.insertComment(c)) {
                    listCom = new ArrayList<Comment>();
                    listCom = dao.getCommentByBook(c.getBookId());
                    b = dao.getBookById(c.getBookId());
                    result = "success";
                } else {
                    result = "fail";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = "fail";
        }

        return result;
    }

    public String register() {
        return "success";
    }

    public String prRegister() throws IOException {
        String result = "fail";
        session = ActionContext.getContext().getSession();
        Captcha captcha = Captcha.load(ServletActionContext.getRequest(), "exampleCaptcha");
        boolean isHuman = captcha.validate(captchaCode);

        if (isHuman) {
            if (usrDao.checkExistUser(usr.getUsername())) {
                message = "Tài khoản đã tồn tại";
                return result;
            } else {
                if (!usrDao.isValidUsername(usr.getUsername())) {
                    message = "Tài khoản không hợp lệ";
                    return result;
                } else {
                    if (!usrDao.isStrongPassword(usr.getPassword())) {
                        message = "Mật khẩu không hợp lệ";
                        return result;
                    } else {
                        if (!rePassword.equals(usr.getPassword())) {
                            message = "Mật khẩu không khớp";
                            return result;
                        } else {
                            try {
                                if (this.userImageFileName != null) {
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

//                                String str1 = usr.getUsername().concat(usr.getPassword());
//                                String encryptedPwd = md5.Encryptor(str1);
//                                encryptedPwd = md5.Encryptor(encryptedPwd.concat(usr.getPassword()));
//                                usr.setPassword(encryptedPwd);
                                usr.setRole(1);

                                if (usrDao.insertUser(usr)) {
                                    usr = usrDao.getUserByUsername(usr.getUsername());
                                    String name = null;
                                    if (usr.getFullname().contains(" ")) {
                                        name = usr.getFullname().substring(usr.getFullname().lastIndexOf(" "), usr.getFullname().length());
                                    } else {
                                        name = usr.getFullname();
                                    }

                                    session.put("USER", name);
                                    session.put("ROLE", usr.getRole());
                                    session.put("AVATAR", usr.getAvatar());
                                    session.put("ID", usr.getId());

                                    result = "success";
                                    message = "Đăng ký thành công!";
                                } else {
                                    message = "Đăng ký thất bại!";
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
        } else {
            message = "Hãy nhập lại captcha";
            return result;
        }
    }

    public String viewProfile() {
        session = ActionContext.getContext().getSession();

        usr = usrDao.getUserById((int) session.get("ID"));
        return "success";
    }

    public String changePassword() {
        return "success";
    }

    public String prChangePassword() {
        String result = "fail";
        session = ActionContext.getContext().getSession();

        Captcha captcha = Captcha.load(ServletActionContext.getRequest(), "exampleCaptcha");
        boolean isHuman = captcha.validate(captchaCode);

        usr = usrDao.getUserById((int) session.get("ID"));
//        String str1 = usr.getUsername().concat(oldPassword);
//        String encryptedPwd = md5.Encryptor(str1);
//        encryptedPwd = md5.Encryptor(encryptedPwd.concat(oldPassword));
        byte[] salt = usrDao.getSalt(usr.getUsername());
        String encryptedPwd = sha.Encryptor(oldPassword, salt);
        if (isHuman) {
            if (!encryptedPwd.equals(usr.getPassword())) {
                message = "Mật khẩu không đúng";
                return result;
            } else {
                if (!usrDao.isStrongPassword(newPassword)) {
                    message = "Mật khẩu không hợp lệ";
                    return result;
                } else {
                    if (!newPassword.equals(rePassword)) {
                        message = "Nhập lại mật khẩu không khớp";
                    } else {
                        try {
                            if (usrDao.changePassword(usr.getId(), newPassword)) {
                                message = "Đổi mật khẩu thành công";
                                result = "success";
                            } else {
                                message = "Đổi mật khẩu thất bại";
                                result = "fail";
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return result;
        } else {
            message = "Captcha không đúng!";
            return result;
        }
    }

    public String changeProfile() {
        session = ActionContext.getContext().getSession();
        usr = usrDao.getUserById((int) session.get("ID"));
        return "success";
    }

    public String prChangeProfile() {
        String result = "fail";
        try {
            if (this.userImageFileName != null) {
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
//                session = ActionContext.getContext().getSession();
//                usr = usrDao.getUserById((int) session.get("ID"));
                    return "fail";
                }
            }

            if (usrDao.editUser(usr)) {
                usr = usrDao.getUserById(usr.getId());
                message = "Đổi thông tin thành công";
                result = "success";
            } else {
                message = "Đổi thông tin thất bại";
                result = "fail";
            }
        } catch (Exception e) {
            //return "fail";
            e.printStackTrace();
        }
        return result;
    }

    public void alert(String mess) {
        session = ActionContext.getContext().getSession();
        session.put("alert", mess);
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
            //alert(message);
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
            // alert(message);
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
            //alert(message);
            ok &= false;
            if (!ok) {
                return ok;
            }
        }

        return ok;
    }

//    public String replaceChar(String input) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < input.length(); i++) {
//            char c = input.charAt(i);
//            switch (c) {
//                case '<':
//                    sb.append("&lt;");
//                    break;
//                case '>':
//                    sb.append("&gt;");
//                    break;
//                case '&':
//                    sb.append("&amp;");
//                    break;
//                case '"':
//                    sb.append("&quot;");
//                    break;
//                case '/':
//                    sb.append("&#x2F;");
//                    break;
//                case '\'':
//                    sb.append("&#x27;");
//                    break;
//                case '\n':
//                    sb.append("&lt;br/&gt;");
//                    break;
//                default:
//                    sb.append(c);
//                    break;
//            }
//        }
//        return sb.toString();
//    }
    //    private String validateCaptcha(String secret) {
//        String response = ServletActionContext.getRequest().getParameter("g-recaptcha-response");
//        JsonObject jsonObject = null;
//        URLConnection connection = null;
//        InputStream is = null;
//        String re = null;
//        String charset = java.nio.charset.StandardCharsets.UTF_8.name();
//
//        String url = "https://www.google.com/recaptcha/api/siteverify";
//        try {
//            String query = String.format("secret=%s&response=%s",
//                    URLEncoder.encode(secret, charset),
//                    URLEncoder.encode(response, charset)
//            );
//
//            connection = new URL(url + "?" + query).openConnection();
//            is = connection.getInputStream();
//            JsonReader rdr = Json.createReader(is);
//            jsonObject = rdr.readObject();
//            re = jsonObject.get("success").toString();
//        } catch (IOException ex) {
//
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                }
//
//            }
//        }
//        return re;
//    }
//    private static String readAll(Reader rd) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        int cp;
//        while ((cp = rd.read()) != -1) {
//            sb.append((char) cp);
//        }
//        return sb.toString();
//    }
//    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//        String recap = ServletActionContext.getRequest().getParameter("g-recaptcha-response");
//        url = url + "?secret=6LcrxBQUAAAAAJv0St8P-Eo6bI90T_nk_ENSn2g9&response=" + recap;
//        URL apiurl = new URL(url);
//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONObject json = new JSONObject(jsonText);
//            return json;
//        } finally {
//            is.close();
//        }
//        HttpURLConnection conn = (HttpURLConnection) apiurl.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setConnectTimeout(1000);
//        String line, outputString = "";
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(conn.getInputStream()));
//        while ((line = reader.readLine()) != null) {
//            outputString += line;
//        }
//        JSONObject json = new JSONObject(outputString);
//        return json;
//    }
}
