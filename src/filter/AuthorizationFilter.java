package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter" , urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
            HttpSession httpSession = httpServletRequest.getSession(false);

            String requestURI = httpServletRequest.getRequestURI();
            if (requestURI.indexOf("/login.xhtml") >= 0
                    || (httpSession != null && httpSession.getAttribute("username") != null)
                    || requestURI.indexOf("/public/") >= 0
                    || requestURI.contains("javax.faces.resourse")){
                filterChain.doFilter(servletRequest , servletResponse);
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/faces/login.xhtml");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
