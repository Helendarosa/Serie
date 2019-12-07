/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDAO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import util.Criptografia;

/**
 *
 * @author dappo
 */
@WebServlet(name = "LogarWS", urlPatterns = {"/admin/logar/LogarWS"})
public class LogarWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("txtEmail");
        String senha = null;
        try {
            senha = Criptografia.convertPasswordToMD5(request.getParameter("txtSenha"));
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(LogarWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.logar(email, senha);
        if(u!=null){
            request.getSession().setAttribute("admin", u);
            request.getSession().setAttribute("erro", "");
            response.sendRedirect("../usuario/UsuarioWS");
        }else{
            request.getSession().setAttribute("erro", "Login ou senha incorreto!");
            response.sendRedirect("../../admin.jsp");
        }
    }

}
