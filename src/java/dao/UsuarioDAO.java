/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Usuario;

public class UsuarioDAO implements Serializable {

    protected EntityManager em;

    public UsuarioDAO() {
        EntityManagerFactory emf;
        try {
            emf = Conexao.getConexao();
            em = emf.createEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger("Não foi possível realizar a conexão com a unidade de persistência. Verifique a conexão");

        }

    }

    public Boolean incluir(Usuario obj) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            Logger.getLogger("Erro ao incluir, veja o código acima");

            em.getTransaction().rollback();

            retorno = false;
            throw e;
        } finally {
            //em.close();

        }
        return retorno;
    }

    public Boolean alterar(Usuario obj) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            Logger.getLogger("Erro ao alterar, veja o código acima");
            em.getTransaction().rollback();
            retorno = false;
            throw e;
        } finally {
            // em.close();
        }
        return retorno;
    }

    public Boolean excluir(Usuario obj) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            Logger.getLogger("Erro ao excluir, veja o código acima");

            em.getTransaction().rollback();
            retorno = false;
        } finally {
            //em.close();
        }
        return retorno;
    }

    public List<Usuario> listar() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    public List<Usuario> listar(String filtro) throws Exception {
        return em.createNamedQuery("Usuario.findFilter").setParameter("filtro", "%" + filtro + "%").getResultList();
    }

    public Usuario logar(String email, String senha) {
        Usuario u;
        try{
            u = (Usuario) em.createNamedQuery("Usuario.logar").setParameter("email", email).setParameter("senha", senha).getSingleResult();
        }catch(Exception e){
            u = null;
        }
        return u;
    }

    public Usuario buscarPorChavePrimaria(Long chaveprimaria) {
        return em.find(Usuario.class, chaveprimaria);
    }

    public void fecharConexao() {
        Conexao.closeConexao();
    }
}
