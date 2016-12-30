package com.varghese.oilandgas.web;

import com.fasterxml.jackson.databind.node.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by newuser on 12/14/16.
 */
public final class TimeSeriesVizHelper {


    public static String generateTimeSeriesJson(Map<Date,Integer> data) {

        JsonNodeFactory factory = JsonNodeFactory.instance;

        ArrayNode list = factory.arrayNode();

        for(Map.Entry<Date,Integer> entry: data.entrySet()) {

            if(entry.getValue() > 0) {
                ArrayNode set = factory.arrayNode();
                ValueNode numberNode = factory.numberNode(entry.getValue());
                ValueNode dateNode = factory.numberNode(entry.getKey().getTime());

                set.add(dateNode);
                set.add(numberNode);

                list.add(set);
            }
        }

        return list.toString();

    }
}
