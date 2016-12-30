package com.varghese.oilandgas.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by newuser on 12/14/16.
 */
public class TimeSeriesViz {

    private long maxDateUTC;

    private Map<String,Integer> data =  new HashMap<String,Integer>();

    public long getMaxDateUTC() {
        return maxDateUTC;
    }

    public void setMaxDateUTC(long maxDateUTC) {
        this.maxDateUTC = maxDateUTC;
    }

}
