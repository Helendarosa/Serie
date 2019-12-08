/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.GeneroDAO;
import dao.PlataformaDAO;
import dao.SerieDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Genero;

import modelo.Plataforma;
import modelo.Serie;
import util.FormataData;

/**
 *
 * @author Acer
 */
@WebServlet(name = "SerieWS", urlPatterns = {"/admin/serie/SerieWS"})
public class SerieWS extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("txtAcao");
        RequestDispatcher destino;
        String pagina;
        SerieDAO dao = new SerieDAO();
        Serie obj;
        List<Serie> series;
        Boolean deucerto;
        String msg;
        
        //variáveis de lista
        PlataformaDAO pdao;
        List<Plataforma> plataformas;
        GeneroDAO gdao;
        List<Genero> generos;
        
        switch(String.valueOf(acao)){
            case "add":  //Abrir tela e talvez buscar dados
                
                pdao = new PlataformaDAO();
                gdao = new GeneroDAO();
                plataformas = pdao.listar();
                generos = gdao.listar();
                
                //enviar dados

                request.setAttribute("plataformas", plataformas);
                request.setAttribute("generos", generos);
                
                //chamar página
                pagina = "add.jsp";
                break;
                
            case "edit":
                //Abrir tela e buscar dados
                pdao = new PlataformaDAO();
                gdao = new GeneroDAO();
                plataformas = pdao.listar();
                generos = gdao.listar();
                
                request.setAttribute("plataformas", plataformas);
                request.setAttribute("generos", generos);
                
                //abrir tela e buscar dados
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
                    msg = "Problema ao excluir o serie " + obj.getNome();
                }
                series = dao.listar();
                request.setAttribute("msg", msg);
                request.setAttribute("lista", series);
                pagina = "list.jsp";
                break;
                
            default:
                //listar ou listar com filtro
                String filtro = request.getParameter("txtFiltro");
                if(filtro == null){
                    //lista todos
                    series = dao.listar();
                }else{
                    //lista com filtro
                    try {                
                        series = dao.listar(filtro);
                    } catch (Exception ex) {
                        series = dao.listar();
                        msg = "Problema ao filtrar";
                        request.setAttribute("msg", msg);
                    }
                }
                request.setAttribute("lista", series);
                pagina = "list.jsp";
                break;
        }
        
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     //criar variaveis
        Serie obj;
        SerieDAO dao = new SerieDAO();
        Boolean deucerto;
        String msg;
        String pagina = null;
        RequestDispatcher destino;
        List<Serie>series;
        
          //Receber dados
         request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("txtId");
        String nome = request.getParameter("txtNome");
       
        String id_plataforma = request.getParameter("txtPlataforma"); 
        //usado para receber seleect multiplo
        //String [] ids_generos = request.getParameterValues("txtGeneros");
        //usado para receber checkbox
        String[] ids_generos = request.getParameter("txtGeneros").split(";");
        String temporada = request.getParameter("txtTemporada");
        String criador = request.getParameter("txtCriador");
        String encerrar = request.getParameter("txtStatus");
        Boolean encerrado;
        encerrado = encerrar.endsWith("Sim");
        
        String data = request.getParameter("txtData");
        String sinopse = request.getParameter("txtSinopse");      
        String foto = request.getParameter("txtFoto");
        
        //Tratar os dados (transformar os dados no formato solicitado)
        
        Integer temporadas = Integer.parseInt(temporada);
        Date dataestreia = FormataData.formata(data, "yyyy-MM-dd");
       
        PlataformaDAO pdao = new PlataformaDAO();
        Plataforma plataforma = pdao.buscarPorChavePrimaria(Long.parseLong(id_plataforma));
        
        GeneroDAO gdao = new GeneroDAO();
        List <Genero> generos = new ArrayList<>(); //<Genero>: caso de erro// recebe a lista de generos selecionados na lista do select
        for(String id_genero :ids_generos){ //percorre a lista
            Genero genero = gdao.buscarPorChavePrimaria(Long.parseLong(id_genero)); //da a localização na lista
            generos.add(genero);
        }
        
        if(id != null){
            //busca o que existe
           obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
        }else{
            //cria um novo
           obj = new Serie(); 
        }
        
        //adicionar os dados recebidos
        obj.setNome(nome);
        obj.setTemporada(temporadas);
        obj.setCriador(criador);
        obj.setDataestreia(dataestreia);
        obj.setSinopse(sinopse);
        obj.setPlataforma(plataforma);
        obj.setGeneros(generos);
        obj.setFoto(foto);
        obj.setEncerrado(encerrado);
        
        if(id != null){
            deucerto = dao.alterar(obj);
            pagina = "list.jsp";
            series = dao.listar();
            request.setAttribute("lista", series);
            if(deucerto){
                msg = obj.getNome() + " alterado com sucesso!";
            }else{
                msg = "Problema ao editar o serie " + obj.getNome();
            }
        }else{
            deucerto = dao.incluir(obj);
              //buscar dados
          
            pdao = new PlataformaDAO();
            gdao = new GeneroDAO();
            List<Plataforma> plataformas = pdao.listar();
            generos = gdao.listar();
            //enviar dados
      
            request.setAttribute("plataformas", plataformas);
            request.setAttribute("generos", generos);    
          
            if(deucerto){
                msg = obj.getNome() + " adicionado com sucesso!";
            }else{
                msg = "Problema ao adicionar o serie " + obj.getNome();
            }
        }
        request.setAttribute("msg", msg);
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
        
    }


   
}
