package controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDAO;
import java.util.Map;
import model.User;
import org.apache.struts2.dispatcher.SessionMap;

public class LoginActionSupport extends ActionSupport {

    public LoginActionSupport() {
    }

    private User usr = new User();
    UserDAO dao = new UserDAO();
    private Map session;
    
    
    private String message;

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

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    @Override
    public String execute() throws Exception {
        return "success";
    }
    
    public String logIn(){
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") > 1) {
            return "success";
        } else {
            if (dao.checkLogin(usr.getUsername(), usr.getPassword())) {
                usr = dao.getUserByUsername(usr.getUsername());

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
                session.put("ID",usr.getId());
                
                
                return "success";
            } else {
                message="Đăng nhập thất bại!";
                return "error";
            }
        }
    }
    

    public String logOut() {
        session = ActionContext.getContext().getSession();
//        session.remove("USER");
//        session.remove("ROLE");
//        session.remove("AVATAR");
//        session.remove("ID");
        session.clear();
        ((SessionMap)this.session).invalidate();
        return "success";
    }

}
