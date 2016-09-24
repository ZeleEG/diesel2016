/*
 * Copyright (C) 2016 Dejan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pulsiraj.dao.pattern;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.pulsiraj.db.HibernateUtil;

/**
 *
 * @author Dejan
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private final Class<T> type;

    public GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    /* Method to READ all entities in the database */
    @Override
    public List<T> listEntities() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        List results;
        try (Session session = factory.openSession()) {
            String hql = "FROM " + type.getName();
            Query query = session.createQuery(hql);
            results = query.list();
            session.close();
        }
        return results == null || results.isEmpty() ? null : (List<T>) results;
    }

    /* Method to CREATE an entity in the database */
    @Override
    public Integer createEntity(T ent) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Integer entID = null;
        try {
            tx = session.beginTransaction();
            entID = (Integer) session.save(ent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entID;
    }
    
    @Override
    public T createWeakEntity(T ent) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        T entID = null;
        try {
            tx = session.beginTransaction();
            entID = (T) session.save(ent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            entID = null;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entID;
    }

    /* Method to UPDATE an entity from the records */
    @Override
    public boolean updateEntity(T ent) {
        boolean statusFlag = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trns = null;
        try {
            trns = session.beginTransaction();
            session.update(ent);
            session.flush();
            trns.commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            statusFlag = false;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return statusFlag;
    }

    /* Method to READ an entity from the records */
    @Override
    public T readEntity(int id) {
        Session session = null;
        T ent = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            ent = session.get(type, id);
            Hibernate.initialize(ent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return ent;
    }

    /* Method to DELETE an entity from the records */
    @Override
    public void deleteEntity(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            T ent
                    = (T) session.get(type, id);
            session.delete(ent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public boolean deleteEntity(T ent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean statusFlag = true;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            statusFlag = false;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return statusFlag;
    }
}
