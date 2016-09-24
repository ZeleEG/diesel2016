/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.beans;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dejan
 */
@ManagedBean(name = "physicalEntity")
@ViewScoped
@Entity
@Table(name = "physicalentity")
public class PhysicalEntity implements Serializable {
    @Id
    @JoinColumn(name = "u_id")
    private User user;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @JoinColumn(name = "c_id")
    private City city;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "image_url")
    private String image_url;

}
