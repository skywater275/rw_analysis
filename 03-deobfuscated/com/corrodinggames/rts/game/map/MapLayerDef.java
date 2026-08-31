/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.map;

import com.corrodinggames.rts.game.map.MapSpawn;
import com.corrodinggames.rts.game.map.MapEngine;
import java.util.ArrayList;
import java.util.Properties;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public strictfp class MapLayerDef {
    public int layerName;
    public String layerClass;
    public ArrayList offsetX;
    public int offsetY;
    public int parallaxX;
    public Properties parallaxY;

    public MapSpawn a(String string) {
        if (this.offsetX != null) {
            for (MapSpawn a2 : (java.util.Collection<MapSpawn>) (java.util.Collection) this.offsetX) {
                if (!string.equalsIgnoreCase(a2.unitTypeName)) continue;
                return a2;
            }
        }
        return null;
    }

    public MapLayerDef(Element element, MapEngine b2) throws MapException {
        Object object;
        Element element2;
        int n;
        NodeList nodeList;
        this.layerClass = element.getAttribute("name");
        if (element.hasAttribute("width")) {
            this.offsetY = Integer.parseInt(element.getAttribute("width"));
        }
        if (element.hasAttribute("height")) {
            this.parallaxX = Integer.parseInt(element.getAttribute("height"));
        }
        this.offsetX = new ArrayList();
        Element element3 = (Element)element.getElementsByTagName("properties").item(0);
        if (element3 != null && (nodeList = element3.getElementsByTagName("property")) != null) {
            this.parallaxY = new Properties();
            for (n = 0; n < nodeList.getLength(); ++n) {
                element2 = (Element)nodeList.item(n);
                object = element2.getAttribute("name");
                String string = element2.getAttribute("value");
                this.parallaxY.setProperty((String)object, string);
            }
        }
        nodeList = element.getElementsByTagName("object");
        n = 0;
        while (n < nodeList.getLength()) {
            element2 = (Element)nodeList.item(n);
            object = new MapSpawn(element2, b2, this);
            ((MapSpawn) object).spawnIndex = n++;
            this.offsetX.add(object);
        }
    }
}
