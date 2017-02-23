/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.dao;

import co.edu.eam.modelo.Usuario;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author danie
 */
public class UsuarioJpaController implements Serializable {
    
    protected EntityManager em;

    public UsuarioJpaController() {
        this.em = SingletonEntityManager.getInstance();
    }


    public void create(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public void edit(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    public void destroy(Usuario usuario) {
        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();
    }

    public Usuario findUsuario(String id) {
        return em.find(Usuario.class, id);
    }

}
