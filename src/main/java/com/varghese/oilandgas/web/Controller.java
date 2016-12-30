package com.varghese.oilandgas.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.varghese.oilandgas.dto.DailyExtractionReport;
import com.varghese.oilandgas.dto.ReserveSumReport;
import com.varghese.oilandgas.model.Reserve;
import com.varghese.oilandgas.repositories.ReserveRepository;
import com.varghese.oilandgas.repositories.ReserveRepositoryFacade;
import com.varghese.oilandgas.repositories.ReserveRepositoryHibernateImpl;
import com.varghese.oilandgas.util.LinearRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by newuser on 12/12/16.
 */
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    ReserveRepositoryFacade reserveRepositoryFacade;

    @GetMapping("/")
    public String root() {
        return "reserves";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/forecast.json")
    @ResponseBody
    public String regressionChartVisual() {
        ReserveSumReport totalReservesCapacity = reserveRepositoryFacade.getTotalReservesCount();

        DailyExtractionReport report = reserveRepositoryFacade.getAggregateExtractionsByDate();


        long[] keys = new long[report.getOilExtractionReportMap().entrySet().size()];
        long[] values = new long[report.getOilExtractionReportMap().entrySet().size()];

        int i = 0;
        int remaining = totalReservesCapacity.getTotalOil()+ totalReservesCapacity.getTotalNaturalGas();

        for(Map.Entry<Date,Integer> entry: report.getOilExtractionReportMap().entrySet()) {
            keys[i] =  entry.getKey().getTime();
            remaining = remaining - entry.getValue();
            values[i] = remaining;
            entry.setValue(remaining);
            i++;
        }

       long emptyWellsPredictionDate =  LinearRegression.getZeroPrediction(keys,values);

        // Add prediction date to data set
        report.getOilExtractionReportMap().put(new Date(emptyWellsPredictionDate),0);


        return ScatterHelper.generateScatterRegressionJson(emptyWellsPredictionDate,report.getOilExtractionReportMap());


    }

    @GetMapping("/daily.json")
    @ResponseBody
    public String dailyExtractionVisual() {
        DailyExtractionReport report = reserveRepositoryFacade.getAggregateExtractionsByDate();





        return TimeSeriesVizHelper.generateTimeSeriesJson(report.getOilExtractionReportMap());
    }

    @GetMapping("/bullets.json")
    @ResponseBody
    public String reservesVisual() {

        // get all reserves
        // create visuals

        ReserveSumReport totalReservesCapacity = reserveRepositoryFacade.getTotalReservesCount();

        List<BulletViz> bulletVizs = new ArrayList<>();

        BulletViz bulletViz = new BulletViz();
        bulletViz.setCapacity(totalReservesCapacity.getTotalOil() + totalReservesCapacity.getTotalNaturalGas());
        bulletViz.setCurrent(totalReservesCapacity.getTotalExistingOil() + totalReservesCapacity.getTotalExistingGas());
        bulletViz.setTitle("Current Reserves");
        bulletViz.setSubtitle((totalReservesCapacity.getTotalExistingOil() + totalReservesCapacity.getTotalExistingGas() ) + " / " + (totalReservesCapacity.getTotalOil() + totalReservesCapacity.getTotalNaturalGas()));
        bulletVizs.add(bulletViz);
/*
        BulletViz bulletViz2 = new BulletViz();
        bulletViz2.setCapacity(totalReservesCapacity.getTotalNaturalGas());
        bulletViz2.setCurrent(totalReservesCapacity.getTotalExistingGas());
        bulletViz2.setTitle("Gas Reserves");
        bulletViz2.setSubtitle(totalReservesCapacity.getTotalExistingGas() + " / " + totalReservesCapacity.getTotalNaturalGas());

        bulletVizs.add(bulletViz2);*/

        String json = BulletVizHelper.generateBulletVizJson(bulletVizs);

        return json;
    }


    public void setReserveRepositoryFacade(ReserveRepositoryFacade reserveRepositoryFacade) {
        this.reserveRepositoryFacade = reserveRepositoryFacade;
    }
}
