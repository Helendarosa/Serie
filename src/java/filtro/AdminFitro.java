/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author dappo
 */
@WebFilter(filterName = "Admin", urlPatterns = {"/admin/*"})
public class AdminFitro implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpSession session = ((HttpServletRequest)request).getSession();
        Usuario u = (Usuario)session.getAttribute("admin");
        
        String path = ((HttpServletRequest)request).getRequestURI();
        
        if(u!=null||path.startsWith("/Series/admin/vendor") || path.startsWith("/Series/admin/css")
                || path.startsWith("/Series/admin/fonts") || path.startsWith("/Series/admin/images")
                || path.startsWith("/Series/admin/js")|| path.startsWith("/Series/admin/logar/LogarWS")){
            chain.doFilter(request, response);
        }else{
            //chain.doFilter(request, response);
            session.setAttribute("erro", "Você não está logado no Sistema. Por Favor faça login para prosseguir");
            ((HttpServletResponse)response).sendRedirect("../../admin.jsp");
        }
    }
    
    public void init(){
        //throw ...
    }

    public void destroy(){
        //throw
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
