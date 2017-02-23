/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author danie
 */
class SingletonEntityManager {

    private static EntityManager em;

    private SingletonEntityManager() {
        em = Persistence.createEntityManagerFactory("compartirpantalla").createEntityManager();
    }

    public static EntityManager getInstance() {
        if (em == null) {
            new SingletonEntityManager();
        }
        return em;
    }
}
