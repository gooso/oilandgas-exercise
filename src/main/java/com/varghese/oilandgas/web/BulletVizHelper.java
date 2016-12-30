package com.varghese.oilandgas.web;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.List;

/**
 * Created by newuser on 12/12/16.
 */
public final class BulletVizHelper {

    public static String generateBulletVizJson(List<BulletViz> bulletVizList) {

        JsonNodeFactory factory = JsonNodeFactory.instance;

        ArrayNode list = factory.arrayNode();

        for(BulletViz bullet: bulletVizList) {
            ObjectNode node = factory.objectNode();
            ArrayNode ranges = factory.arrayNode();
            ArrayNode measures = factory.arrayNode();
            ArrayNode markers = factory.arrayNode();

            measures.add(bullet.getCurrent());
            measures.add(bullet.getCapacity());

            ranges.add(bullet.getCapacity());
            markers.add(bullet.getCurrent());

            TextNode title = factory.textNode(bullet.getTitle());
            TextNode subtitle = factory.textNode(bullet.getSubtitle());

            ranges.add(bullet.getCapacity());

            node.set("title", title);
            node.set("subtitle", subtitle);
            node.set("ranges", ranges);
            node.set("measures", measures);
            node.set("markers", markers);
            list.add(node);

        }

        return list.toString();

    }
}
