/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;


import dao.GeneroDAO;
import dao.PlataformaDAO;
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
import modelo.Plataforma;
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
        
         String pagina = "index.jsp";
      
         SerieDAO sdao = new  SerieDAO();
          List<Serie>  series = sdao.listar();
        request.setAttribute("series", series);
        
         GeneroDAO gdao = new GeneroDAO();
        List<Genero> generos = gdao.listar();
        request.setAttribute("generos", generos);
        
        PlataformaDAO pdao = new PlataformaDAO();
        List<Plataforma> plataformas = pdao.listar();
        request.setAttribute("plataformas", plataformas);
        
        
        
        
        String acao = request.getParameter("txtAcao");
        Long id;
        switch (String.valueOf(acao)) {
            case "listGenero":
                id = Long.parseLong(request.getParameter("id"));
                series = sdao.listarGenero(id);
                request.setAttribute("series", series);
                break;
            case "listPlataforma":
                id = Long.parseLong(request.getParameter("id"));
                series = sdao.listarPlataforma(id);
                request.setAttribute("series", series);
                break;
            default:
                series = sdao.listar();
                request.setAttribute("series", series);
        }
        
       
        RequestDispatcher destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       

    
    }
}

 