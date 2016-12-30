package com.varghese.oilandgas.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by newuser on 12/12/16.
 */
@Entity
@Table( name="reserves", indexes = {
        @Index(columnList = "site,well", name="reserves_site_well_idx")
})
public class Reserve {

    @Id @GeneratedValue
    private int id;
    private String site;
    private String well;
    private int oil;
    private int naturalGas;
    private int currentOil;
    private int currentNaturalGas;
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    private Date updatedDate;

    public int getCurrentOil() {
        return currentOil;
    }

    public void setCurrentOil(int currentOil) {
        this.currentOil = currentOil;
    }

    public int getCurrentNaturalGas() {
        return currentNaturalGas;
    }

    public void setCurrentNaturalGas(int currentNaturalGas) {
        this.currentNaturalGas = currentNaturalGas;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getWell() {
        return well;
    }

    public void setWell(String well) {
        this.well = well;
    }

    public int getOil() {
        return oil;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public int getNaturalGas() {
        return naturalGas;
    }

    public void setNaturalGas(int naturalGas) {
        this.naturalGas = naturalGas;
    }
}
