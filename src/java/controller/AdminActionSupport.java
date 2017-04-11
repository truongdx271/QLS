package controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import model.User;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class AdminActionSupport extends ActionSupport {

    private String fileName;
    private String projectPath;
    private String command;
    private Map session;
    private User usr;

    private String message;
    private String alert;

    UserDAO dao = new UserDAO();
    static final Logger logger = Logger.getLogger(AdminActionSupport.class);

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

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public AdminActionSupport() {
    }

    public String execute() throws Exception {
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") > 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    public String system() {
        session = ActionContext.getContext().getSession();
        if (session.get("USER") != null && (int) session.get("ROLE") == 3) {
            if (session.get("result") != null) {
                alert = (String) session.get("result");
                session.remove("result");
            }
            return "success";
        } else {
            return "fail";
        }
    }

    public String viewProfileAdm() {
        session = ActionContext.getContext().getSession();
        usr = dao.getUserById((int) session.get("ID"));

        return "success";
    }

    public String backUp() throws InterruptedException, IOException {
        String result = "fail";
//        String filePath = ServletActionContext.getServletContext().getRealPath("/");
//        int a = filePath.indexOf("\\build\\web");
//        projectPath = filePath.substring(0, a);
//        String file = "C:\\Users\\hieuhx1\\Desktop\\"+fileName;
//        command = "cmd /c 7z a -tzip " + file + " "+projectPath;
//        try{
//            Runtime rt = Runtime.getRuntime();
//            Process pr = rt.exec(command);
//            pr.waitFor();
//            if(pr.exitValue()==0){
//                result="success";
//            }
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }

        String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";
        String IPADDRESS_PATTERN
                = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        if (!fileName.matches(DOMAIN_NAME_PATTERN) && !fileName.matches(IPADDRESS_PATTERN)) {
            logger.debug("Log4j appender configuration is successful !!");
            System.out.print("hello word");
            result = "fail";
        } else {
            Process p = Runtime.getRuntime().exec("cmd /c ping " + fileName);
            p.waitFor();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                message = "";
                while ((line = reader.readLine()) != null) {
                    message += "<p>" + line + "</p>";
                }
                session = ActionContext.getContext().getSession();
                session.put("result", message);
                result = "success";
            }
        }

        //DOMConfigurator.configure("log4j-config.xml");
        //Log in console in and log file
        //logger.debug("Log4j appender configuration is successful !!");
        return result;
    }

}
