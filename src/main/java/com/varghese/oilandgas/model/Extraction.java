package com.varghese.oilandgas.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by newuser on 12/12/16.
 */
@Entity
@Table( name="extractions")
public class Extraction {

    @Id @GeneratedValue
    private int id;
    private Date extractionDate;
    private int oilAmount;
    private int naturalGasAmount;

    @CreationTimestamp
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name="reserve_id")
    private Reserve reserve;

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getExtractionDate() {
        return extractionDate;
    }

    public void setExtractionDate(Date extractionDate) {
        this.extractionDate = extractionDate;
    }

    public int getOilAmount() {
        return oilAmount;
    }

    public void setOilAmount(int oilAmount) {
        this.oilAmount = oilAmount;
    }

    public int getNaturalGasAmount() {
        return naturalGasAmount;
    }

    public void setNaturalGasAmount(int naturalGasAmount) {
        this.naturalGasAmount = naturalGasAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
