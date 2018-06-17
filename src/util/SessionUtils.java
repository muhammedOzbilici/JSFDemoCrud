package util;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    

    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getUsername(){
        HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return httpSession.getAttribute("username").toString();
    }

    public static String getUserId(){
        HttpSession httpSession = getSession();
        if (httpSession != null)
            return (String) httpSession.getAttribute("id");
        else {
            return null;
        }
    }

}
