package com.pulsiraj.dao.object;

import com.pulsiraj.beans.User;
import com.pulsiraj.dao.pattern.GenericDAOImpl;

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
}
