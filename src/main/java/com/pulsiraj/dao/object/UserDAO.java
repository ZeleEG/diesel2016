package com.pulsiraj.dao.object;

import com.pulsiraj.beans.User;
import com.pulsiraj.dao.pattern.GenericDAOImpl;
import com.pulsiraj.db.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dejan
 */
public class UserDAO extends GenericDAOImpl<User>{
    public UserDAO() {
        super((Class<User>)new User().getClass());
    }
    
    public User readEntity(String username) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        List results;
        try (Session session = factory.openSession()) {
            String hql = "FROM User U WHERE U.userName = '" + username + "'";
            Query query = session.createQuery(hql);
            results = query.list();
            session.close();
        }
        return results == null || results.isEmpty() ? null : (User)results.get(0);
    }    
}
