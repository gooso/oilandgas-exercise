package com.varghese.oilandgas.repositories;

import com.varghese.oilandgas.dto.DailyExtractionReport;
import com.varghese.oilandgas.dto.ReserveSumReport;

/**
 * Created by newuser on 12/14/16.
 */
public class ReserveRepositoryFacade {

    ReserveRepository repository;

    public ReserveSumReport getTotalReservesCount() {

        return repository.getTotalReserveCounts();
    }


    public DailyExtractionReport getAggregateExtractionsByDate() {
        return repository.getAggregateExtractionsByDate();
    }

    public void setRepository(ReserveRepository repository) {
        this.repository = repository;
    }
}
