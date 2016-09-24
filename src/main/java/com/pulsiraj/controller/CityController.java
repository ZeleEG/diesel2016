/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.controller;

import com.pulsiraj.beans.City;
import com.pulsiraj.dao.object.CityDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Dejan
 */
@ManagedBean(name = "cityCont")
@ApplicationScoped
public class CityController {
    public List<City> cityList;

    @PostConstruct
    public void init() {
        updateCityList();
    }
    
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
    
    public List<City> listCities() {
        CityDAO dao = new CityDAO();
        return dao.listEntities();
    }
    
    public void updateCityList() {
        cityList = listCities();
    }
}
