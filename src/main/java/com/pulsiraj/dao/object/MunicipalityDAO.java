/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.dao.object;

import com.pulsiraj.beans.Municipality;
import com.pulsiraj.beans.User;
import com.pulsiraj.dao.pattern.GenericDAOImpl;
import com.pulsiraj.db.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Dejan
 */
public class MunicipalityDAO extends GenericDAOImpl<Municipality>{
    public MunicipalityDAO() {
        super((Class<Municipality>)new Municipality().getClass());
    }
    
    public List<Municipality> listMunicipalityForCity(int c_id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        List results;
        try (Session session = factory.openSession()) {
            String hql = "FROM Municipality M WHERE M.city.c_id = " + c_id;
            Query query = session.createQuery(hql);
            results = query.list();
            session.close();
        }
        return results == null || results.isEmpty() ? null : results;
    }  
}
