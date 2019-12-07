/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDAO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "UsuarioWS", urlPatterns = {"/admin/usuario/UsuarioWS"})
public class UsuarioWS extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("txtAcao");
        String id = request.getParameter("txtId");
        List<Usuario> lista = null;
        RequestDispatcher destino;
        String pagina = null;
        UsuarioDAO dao = new UsuarioDAO();
        Usuario obj;
        Boolean deucerto;
        switch (String.valueOf(acao)) {
            case "sair":
                request.getSession().invalidate();
                pagina = "../../admin.jsp";
                break;
            case "del":
                obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
                deucerto = dao.excluir(obj);
                Usuario sessao = (Usuario)request.getSession().getAttribute("admin");
                if(deucerto){
                    request.setAttribute("msg", "Deletado com sucesso!");
                }
                else{
                     dao.excluir(obj);
                     request.setAttribute("msg", "Não pode ser deletado!");
                }
                lista = dao.listar();
                request.setAttribute("lista", lista);
                //request.setAttribute("msg", "Deletado com sucesso!");
                pagina = "list.jsp";
                break;
            case "edit":
                obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
                request.setAttribute("obj", obj);
                pagina = "edit.jsp";
                break;
            default:
                String filtro = request.getParameter("txtFiltro");
                if (filtro == null) {
                    lista = dao.listar();
                } else {
                    try {
                        lista = dao.listar(filtro);
                    } catch (Exception ex) {
                        Logger.getLogger(SerieWS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                request.setAttribute("lista", lista);
                pagina = "list.jsp";
        }
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina;
        RequestDispatcher destino;
        Boolean deucerto;
        String msg;
        Usuario obj;

        String id = request.getParameter("txtId");
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String senha = null;
        try {
            senha = Criptografia.convertPasswordToMD5(request.getParameter("txtSenha"));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        UsuarioDAO dao = new UsuarioDAO();

        if (id == null) {
            //adicionando
            obj = new Usuario();
            obj.setNome(nome);
            obj.setEmail(email);
            obj.setSenha(senha);
            deucerto = dao.incluir(obj);
            if (deucerto) {
                msg = obj.getNome() + " adicionado com sucesso!";
            } else {
                msg = "Problemas ao adicionar!";
            }
            pagina = "add.jsp";
        } else {
            //editando
            obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
            obj.setNome(nome);
            obj.setEmail(email);
            obj.setSenha(senha);
            deucerto = dao.alterar(obj);
            if (deucerto) {
                msg = obj.getNome() + " editado com sucesso!";
            } else {
                msg = "Problemas ao editar!";
            }
            List<Usuario> lista = dao.listar();
            request.setAttribute("lista", lista);
            pagina = "list.jsp";
        }
        request.setAttribute("msg", msg);
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
    }

    

}
