/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;


import dao.GeneroDAO;
import dao.SerieDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Genero;
import modelo.Serie;
import modelo.Usuario;
import util.Criptografia;
/**
 *
 * @author Cliente
 */
@WebServlet(name = "PublicWS", urlPatterns = {"/public/PublicWS"})
public class PublicWS extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pagina = null;
      
         SerieDAO sdao = new  SerieDAO();
          List<Serie>  series = sdao.listar();
        
         GeneroDAO gdao = new GeneroDAO();
        List<Genero> genero = gdao.listar();
        
     
        request.setAttribute("series", series);
        request.setAttribute("genero", genero);
           
        pagina = "admin.jsp";
        
        RequestDispatcher destino = request.getRequestDispatcher(pagina);
        destino.forward (request, response);
        
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina2;
        RequestDispatcher destino2;
        Boolean deucerto;
        String msg2;
        Usuario obj2;

       
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String senha = null;
        try {
            senha = Criptografia.convertPasswordToMD5(request.getParameter("txtSenha"));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        UsuarioDAO dao = new UsuarioDAO();

            obj2 = new Usuario();
            obj2.setNome(nome);
            obj2.setEmail(email);
            obj2.setSenha(senha);
            deucerto = dao.incluir(obj2);
            request.getSession().setAttribute("admin", obj2);
            if (deucerto) {
                response.sendRedirect("../admin/usuario/UsuarioWS");
            } else {
                msg2 = "Problemas ao adicionar!";
            }
        
      
      
    }

    
    }
    
 