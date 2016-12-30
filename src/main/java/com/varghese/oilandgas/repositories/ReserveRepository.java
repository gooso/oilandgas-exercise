package com.varghese.oilandgas.repositories;

import com.varghese.oilandgas.dto.DailyExtractionReport;
import com.varghese.oilandgas.dto.ReserveSumReport;
import com.varghese.oilandgas.model.Reserve;

import java.util.List;
import java.util.Map;

/**
 * Created by newuser on 12/12/16.
 */
public interface ReserveRepository {

    public Reserve getReserve(int reserveId);

    public List<Reserve> getAllReserves();

    public ReserveSumReport getTotalReserveCounts();

    public DailyExtractionReport getAggregateExtractionsByDate();

}
