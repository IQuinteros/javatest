/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import conexion.Conexion;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.RecetaFavorito;

/**
 *
 * @author Yunnicio
 */
public class RecetaFavoritoJpaController implements Serializable {

    public RecetaFavoritoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public RecetaFavoritoJpaController(){
        this.emf = Conexion.getFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RecetaFavorito recetaFavorito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(recetaFavorito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RecetaFavorito recetaFavorito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            recetaFavorito = em.merge(recetaFavorito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = recetaFavorito.getId();
                if (findRecetaFavorito(id) == null) {
                    throw new NonexistentEntityException("The recetaFavorito with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecetaFavorito recetaFavorito;
            try {
                recetaFavorito = em.getReference(RecetaFavorito.class, id);
                recetaFavorito.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recetaFavorito with id " + id + " no longer exists.", enfe);
            }
            em.remove(recetaFavorito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RecetaFavorito> findRecetaFavoritoEntities() {
        return findRecetaFavoritoEntities(true, -1, -1);
    }

    public List<RecetaFavorito> findRecetaFavoritoEntities(int maxResults, int firstResult) {
        return findRecetaFavoritoEntities(false, maxResults, firstResult);
    }

    private List<RecetaFavorito> findRecetaFavoritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RecetaFavorito.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RecetaFavorito findRecetaFavorito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RecetaFavorito.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetaFavoritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RecetaFavorito> rt = cq.from(RecetaFavorito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
