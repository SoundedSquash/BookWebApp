/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.ejbs;

import edu.wctc.wtb.bookwebapp.models.Author;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author kanst_000
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.wtb_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
    public void addNew(String firstName, String lastName){
        Author a = new Author();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setDateAdded(new Date());
        
        this.create(a);
    }
    
    public int deleteAuthorById(int id){
        String jpql = "delete from Author a where a.authorId = :id";
        
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("id", id);
        int results = q.executeUpdate();
        
        return results;
    }
    
    public int updateById(int id, String firstName, String lastName){
        String jpql = "update Author a set a.firstName = :firstName, a.lastName = :lastName where a.authorId = :id";
        
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);        
        q.setParameter("id", id);
        int results = q.executeUpdate();
        
        return results;
    }
    
    public void addOrUpdate(int id, String firstName, String lastName){
        if (id == 0) {
            //new
        } else {
            //update
        }
    }
}
