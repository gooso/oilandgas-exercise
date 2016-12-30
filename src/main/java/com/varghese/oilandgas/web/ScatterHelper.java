package com.varghese.oilandgas.web;

import com.fasterxml.jackson.databind.node.*;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by newuser on 12/12/16.
 */
public final class ScatterHelper {


    public static String generateScatterRegressionJson(long predictedDate, Map<Date,Integer> dataPoints) {
        JsonNodeFactory factory = JsonNodeFactory.instance;

        ObjectNode objectNode = factory.objectNode();
        ArrayNode regressionPointsArray = factory.arrayNode();
        ArrayNode firstRegressionPoint = factory.arrayNode();

        ValueNode predictedDateNode =  factory.numberNode(predictedDate);
        ValueNode zeroNode =  factory.numberNode(0);

        firstRegressionPoint.add(predictedDateNode);
        firstRegressionPoint.add(zeroNode);


        regressionPointsArray.add(firstRegressionPoint);



        objectNode.set("regressionpoints",regressionPointsArray);


        ArrayNode dataArray = factory.arrayNode();

        for(Map.Entry<Date,Integer> entry: dataPoints.entrySet()) {
            ArrayNode arrNode = factory.arrayNode();
            arrNode.add(entry.getKey().getTime());
            arrNode.add(entry.getValue());

            dataArray.add(arrNode);
        }



        objectNode.set("data",dataArray);


        System.out.println(objectNode);

        return objectNode.toString();
    }
}
