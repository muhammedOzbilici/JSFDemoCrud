package beans;

import dao.LoginDAO;
import util.SessionUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name = "login")
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 7128370895360849852L;

    private String username;
    private String password;
    private String message;

    //validate login
    public String validateUsernamePassword(){

        boolean valid = LoginDAO.validate(username , password);
        if (valid){
            HttpSession httpSession = SessionUtils.getSession();
            httpSession.setAttribute("username" , username);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_WARN ,
                    "Hatalı kullanıcı adı ya da şifre girdiniz !" ,
                    "Lütfen kullanıcı adı ya da şifrenizi kontrol ediniz"));
            return "login";
        }
    }

    //logout
    public String logout(){
        HttpSession httpSession = SessionUtils.getSession();
        httpSession.invalidate();
        return "login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
