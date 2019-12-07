/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.GeneroDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Genero;

/**
 *
 * @author Acer
 */
@WebServlet(name = "GeneroWS", urlPatterns = {"/admin/genero/GeneroWS"})
public class GeneroWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String acao = request.getParameter("txtAcao");
        RequestDispatcher destino;
        String pagina;
        GeneroDAO dao = new GeneroDAO();
        Genero obj;
        List<Genero> generos;
        Boolean deucerto;
        String msg;
        
        switch(String.valueOf(acao)){
            case "add":
                //Abrir tela e talvez buscar dados
                pagina = "add.jsp";
                break;
            case "edit":
                //Abrir tela e buscar dados
                obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtId")));
                request.setAttribute("obj", obj);
                pagina = "edit.jsp";
                break;
            case "del":
                //excluir dados e buscar dados
                obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtId")));
                deucerto = dao.excluir(obj);
                if(deucerto){
                    msg = obj.getNome() + " deletado com sucesso!";
                }else{
                    msg = "Problema ao excluir o genero " + obj.getNome();
                }
                generos = dao.listar();
                request.setAttribute("msg", msg);
                request.setAttribute("lista", generos);
                pagina = "list.jsp";
                break;
            default:
                //listar ou listar com filtro
                String filtro = request.getParameter("txtFiltro");
                if(filtro == null){
                    //lista todos
                    generos = dao.listar();
                }else{
                    //lista com filtro
                    try {                
                        generos = dao.listar(filtro);
                    } catch (Exception ex) {
                        generos = dao.listar();
                        msg = "Problema ao filtrar";
                        request.setAttribute("msg", msg);
                    }
                }
                request.setAttribute("lista", generos);
                pagina = "list.jsp";
                break;
        }
        
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Genero obj;
        GeneroDAO dao = new GeneroDAO();
        Boolean deucerto;
        String msg;
        String pagina;
        RequestDispatcher destino;
        List<Genero> generos;
        
        
        
        //Receber dados
        String id = request.getParameter("txtId");
        String nome = request.getParameter("txtNome");
        
        if(id != null){
            //busca o que existe
           obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
        }else{
            //cria um novo
           obj = new Genero(); 
        }
        
        //adicionar os dados recebidos
        obj.setNome(nome);
        
       
        
        if(id != null){
            deucerto = dao.alterar(obj);
            pagina = "list.jsp";
            generos = dao.listar();
            request.setAttribute("lista", generos);
            if(deucerto){
                msg = obj.getNome() + " alterado com sucesso!";
            }else{
                msg = "Problema ao editar o genero " + obj.getNome();
            }
        }else{
            deucerto = dao.incluir(obj);
            pagina = "add.jsp";
            if(deucerto){
                msg = obj.getNome() + " adicionado com sucesso!";
            }else{
                msg = "Problema ao adicionar o genero " + obj.getNome();
            }
        }
        request.setAttribute("msg", msg);
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
        
        
        
    }

}
