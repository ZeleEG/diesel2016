/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.dao.object;

import com.pulsiraj.beans.LegalEntity;
import com.pulsiraj.dao.pattern.GenericDAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Dejan
 */
public class LegalEntityDAO extends GenericDAOImpl<LegalEntity> {
    public LegalEntityDAO() {
        super((Class<LegalEntity>)new LegalEntity().getClass());
    }
}
