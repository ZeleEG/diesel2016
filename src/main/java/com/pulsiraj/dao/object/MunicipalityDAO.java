/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.dao.object;

import com.pulsiraj.beans.Municipality;
import com.pulsiraj.dao.pattern.GenericDAOImpl;

/**
 *
 * @author Dejan
 */
public class MunicipalityDAO extends GenericDAOImpl<Municipality>{
    public MunicipalityDAO() {
        super((Class<Municipality>)new Municipality().getClass());
    }
}
