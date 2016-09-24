/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.converter;

import com.pulsiraj.beans.City;
import com.pulsiraj.controller.CityController;
import com.pulsiraj.dao.object.CityDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Dejan
 */
@FacesConverter("cityConverter")
public class CityConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                CityDAO dao = new CityDAO();
                List<City> cities = dao.listEntities();
                for (City city : cities) {
                    if (city.getC_id() == Integer.parseInt(value)) {
                        return city;
                    }
                }
                return null;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid discipline."));
            }
        }
        else {
            return null;
        }
    }
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
         if(object != null) {
            return String.valueOf(((City) object).getC_id());
        }
        else {
            return null;
        }
    } 
}

