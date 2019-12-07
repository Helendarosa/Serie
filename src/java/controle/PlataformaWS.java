/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PlataformaDAO;
import dao.PlataformaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Plataforma;

/**
 *
 * @author Acer
 */
@WebServlet(name = "PlataformaWS", urlPatterns = {"/admin/plataforma/PlataformaWS"})
public class PlataformaWS extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String acao = request.getParameter("txtAcao");
        RequestDispatcher destino;
        String pagina;
        PlataformaDAO dao = new PlataformaDAO();
        Plataforma obj;
        List<Plataforma> plataformas;
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
                    msg = "Problema ao excluir o plataforma " + obj.getNome();
                }
                plataformas = dao.listar();
                request.setAttribute("msg", msg);
                request.setAttribute("lista", plataformas);
                pagina = "list.jsp";
                break;
            default:
                //listar ou listar com filtro
                String filtro = request.getParameter("txtFiltro");
                if(filtro == null){
                    //lista todos
                    plataformas = dao.listar();
                }else{
                    //lista com filtro
                    try {                
                        plataformas = dao.listar(filtro);
                    } catch (Exception ex) {
                        plataformas = dao.listar();
                        msg = "Problema ao filtrar";
                        request.setAttribute("msg", msg);
                    }
                }
                request.setAttribute("lista", plataformas);
                pagina = "list.jsp";
                break;
        }
        
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Plataforma obj;
        PlataformaDAO dao = new PlataformaDAO();
        Boolean deucerto;
        String msg;
        String pagina;
        RequestDispatcher destino;
        List<Plataforma> plataformas;
        
        
        //Receber dados
        String id = request.getParameter("txtId");
        String nome = request.getParameter("txtNome");
        String logo = request.getParameter("txtLogo");
        
        if(id != null){
            //busca o que existe
           obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
        }else{
            //cria um novo
           obj = new Plataforma(); 
        }
        
        //adicionar os dados recebidos
        obj.setNome(nome);
        obj.setLogo(logo);
        
        
        if(id != null){
            deucerto = dao.alterar(obj);
            pagina = "list.jsp";
            plataformas = dao.listar();
            request.setAttribute("lista", plataformas);
            if(deucerto){
                msg = obj.getNome() + " alterado com sucesso!";
            }else{
                msg = "Problema ao editar o plataforma " + obj.getNome();
            }
        }else{
            deucerto = dao.incluir(obj);
            pagina = "add.jsp";
            if(deucerto){
                msg = obj.getNome() + " adicionado com sucesso!";
            }else{
                msg = "Problema ao adicionar o plataforma " + obj.getNome();
            }
        }
        request.setAttribute("msg", msg);
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
        
        
    }

}
