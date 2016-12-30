package com.varghese.oilandgas.dto;

import java.util.Date;
import java.util.Map;

/**
 * Created by newuser on 12/14/16.
 */
public class DailyExtractionReport {

    private Map<Date,Integer> oilExtractionReportMap;

    private Map<String,Integer> gasExtractionReportMap;

    public Map<Date, Integer> getOilExtractionReportMap() {
        return oilExtractionReportMap;
    }

    public void setOilExtractionReportMap(Map<Date, Integer> oilExtractionReportMap) {
        this.oilExtractionReportMap = oilExtractionReportMap;
    }

    public Map<String, Integer> getGasExtractionReportMap() {
        return gasExtractionReportMap;
    }

    public void setGasExtractionReportMap(Map<String, Integer> gasExtractionReportMap) {
        this.gasExtractionReportMap = gasExtractionReportMap;
    }
}
