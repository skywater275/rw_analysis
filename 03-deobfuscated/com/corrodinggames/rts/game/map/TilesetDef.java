/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.map;
import com.corrodinggames.rts.game.HumanPlayer;

import android.graphics.Rect;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.TMXMapLoader;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public strictfp class TilesetDef {
    public String tilesetName;
    public com.corrodinggames.rts.gameFramework.rendering.Texture tilesetTexture;
    public String sourcePath;
    int tileWidth;
    int tileHeight;
    int spacing;
    int margin;
    int firstGid = 0;
    int tileCount = 0;
    int tileProperties;
    float tileEntries;
    public int columns;
    public int m = Integer.MAX_VALUE;
    public short n;
    public MapEngine o;
    public boolean p = false;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    private HashMap x = new HashMap();
    static String t = "[EMBED]";
    static ArrayList u = new ArrayList();
    Rect v = new Rect();
    int w = -1;

    public String a(MapEngine b2, Element element) {
        Element element2 = (Element)element.getElementsByTagName("properties").item(0);
        if (element2 != null) {
            NodeList nodeList = element2.getElementsByTagName("property");
            for (int tileCount = 0; tileCount < nodeList.getLength(); ++tileCount) {
                Element element3 = (Element)nodeList.item(tileCount);
                String string = element3.getAttribute("name");
                if (!string.equals("embedded_png")) continue;
                String string2 = element3.getAttribute("value");
                if (string2 != null && !string2.equals("")) {
                    return string2;
                }
                Node node = element3.getFirstChild();
                if (node == null) continue;
                String string3 = node.getNodeValue();
                return string3;
            }
        }
        return null;
    }

    public static Element a(MapEngine b2, String string) throws MapException {
        try {
            InputStream inputStream = b2.d("tilesets/", string);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element element = document.getDocumentElement();
            return element;
        }
        catch (Exception exception) {
            com.corrodinggames.rts.gameFramework.GlobalState.B().a("Unable to load or parse sourced tileset: tilesets/" + string, 1);
            throw new MapException("Unable to load or parse sourced tileset: tilesets/" + string, exception);
        }
    }

    public TilesetDef(MapEngine b2, String string, int n2) throws MapException {
        this.o = b2;
        this.columns = n2;
        Element element = a(b2, string);
        this.tilesetName = string;
        this.a(element);
    }

    public TilesetDef(MapEngine b2, Element element) throws MapException {
        this.o = b2;
        this.columns = Integer.parseInt(element.getAttribute("firstgid"));
        String string = element.getAttribute("source");
        if (string != null && !string.equals("")) {
            element = a(b2, string);
            this.tilesetName = string;
        }
        this.a(element);
    }

    public void a(Element element) throws MapException {
        Object object;
        NodeList nodeList = element.getElementsByTagName("image");
        if (nodeList.getLength() > 0) {
            object = (Element)nodeList.item(0);
            String string = ((Element)object).getAttribute("source");
            string = string.trim();
            this.sourcePath = com.corrodinggames.rts.gameFramework.GlobalState.enqueueKeyEvent(string);
        }
        if ((object = this.a(this.o, element)) != null) {
            this.sourcePath = a((String)object, this.sourcePath);
        }
        if (this.sourcePath == null) {
            throw new MapException("Map tileset is missing an image tag or embedded image data");
        }
        this.tileWidth = this.o.tilePixelWidth;
        this.tileHeight = this.o.tilePixelHeight;
        if (element.hasAttribute("tilewidth")) {
            this.tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
            this.tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.C()) {
            this.tileWidth = this.o.tilePixelWidth;
            this.tileHeight = this.o.tilePixelHeight;
        }
        int n2 = 0;
        if (element.hasAttribute("spacing")) {
            n2 = Integer.parseInt(element.getAttribute("spacing"));
        }
        this.spacing = this.tileWidth + n2;
        this.margin = this.tileHeight + n2;
        NodeList nodeList2 = element.getElementsByTagName("tile");
        for (int i2 = 0; i2 < nodeList2.getLength(); ++i2) {
            Element element2 = (Element)nodeList2.item(i2);
            int n3 = Integer.parseInt(element2.getAttribute("id"));
            n3 += this.columns;
            Properties properties = new Properties();
            Element element3 = (Element)element2.getElementsByTagName("properties").item(0);
            if (element3 != null) {
                NodeList nodeList3 = element3.getElementsByTagName("property");
                for (int i3 = 0; i3 < nodeList3.getLength(); ++i3) {
                    Element element4 = (Element)nodeList3.item(i3);
                    String string = element4.getAttribute("name");
                    String string2 = element4.getAttribute("value");
                    if ("unit".equalsIgnoreCase(string) || "customUnit".equalsIgnoreCase(string)) {
                        this.s = true;
                    }
                    properties.setProperty(string, string2);
                }
            }
            this.x.put(new Integer(n3), properties);
        }
    }

    public static String a(String string, String string2) {
        for (TileEntry k2 : (java.util.Collection<TileEntry>) (java.util.Collection) u) {
            if (!string.equalsIgnoreCase(k2.tileName)) continue;
            return k2.tileId;
        }
        TileEntry k3 = new TileEntry();
        k3.textureLoaded = false;
        k3.texture = null;
        k3.tileName = string;
        k3.path = t;
        k3.tileId = t + TileEntry.a;
        k3.src = string2;
        ++TileEntry.a;
        u.add(k3);
        return k3.tileId;
    }

    /* 02b j.java L200: 仅抛 f; IOException 已内部 catch 消化 (d() L216) → 去过度 IOException */
    public static com.corrodinggames.rts.gameFramework.rendering.Texture a(String string) throws MapException {
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        String string2 = "tilesets/bitmaps/";
        if (string.startsWith(t)) {
            string2 = t;
        }
        TileEntry k2 = null;
        for (Object object2 : u) {
            if (!string.equalsIgnoreCase(((TileEntry) object2).tileId) || !string2.equalsIgnoreCase(((TileEntry) object2).path)) continue;
            k2 = (TileEntry) object2;
            break;
        }
        if (k2 != null) {
            if (k2.tileName != null) {
                com.corrodinggames.rts.gameFramework.rendering.Texture e2;
                Object object2;
                object = com.corrodinggames.rts.game.map.TMXMapLoader.a(k2.tileName, "base64", "");
                object2 = new BufferedInputStream((InputStream)object);
                boolean bl = false;
                try {
                    e2 = l2.bO.a((InputStream)object2, bl);
                }
                catch (RuntimeException runtimeException) {
                    runtimeException.printStackTrace();
                    throw new MapException("Error loading embedded base64 image:" + k2.src + " - " + runtimeException.getMessage());
                }
                if (e2 == null) {
                    throw new MapException("Embedded tilesetBitmap is null for: " + string);
                }
                k2.texture = e2;
                k2.tileName = null;
            }
            k2.textureLoaded = true;
            return k2.texture;
        }
        try {
            object = l2.bL.d(string2, string);
        }
        catch (IOException iOException) {
            throw new MapException("Image file could not be found or loaded: " + string2 + string, iOException);
        }
        boolean bl = false;
        com.corrodinggames.rts.gameFramework.rendering.Texture e3 = l2.bO.a((InputStream)object, bl);
        try {
            if (object != null) {
                ((InputStream)object).close();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        if (e3 == null) {
            throw new RuntimeException("tilesetBitmap is null for: " + string);
        }
        e3.a("tilesets/" + string);
        TileEntry k3 = new TileEntry();
        k3.textureLoaded = true;
        k3.texture = e3;
        k3.path = string2;
        k3.tileId = string;
        u.add(k3);
        return k3.texture;
    }

    public static void a() {
        for (TileEntry k2 : (java.util.Collection<TileEntry>) (java.util.Collection) u) {
            k2.textureLoaded = false;
        }
    }

    public static void b() {
        Iterator iterator = u.iterator();
        while (iterator.hasNext()) {
            TileEntry k2 = (TileEntry) iterator.next();
            if (k2.textureLoaded) continue;
            if (k2.texture != null) {
                k2.texture.o();
                k2.texture = null;
            }
            k2.tileName = null;
            iterator.remove();
        }
    }

    /* 02b j.java L302: 仅抛 f (调 a(String) 链) → 去过度 IOException */
    void c() throws MapException {
        this.tilesetTexture = a(this.sourcePath);
        this.tileProperties = this.tilesetTexture.m() / this.spacing;
        if (this.tileProperties == 0) {
            this.tileProperties = 1;
        }
        this.tileEntries = 1.0f / (float)this.tileProperties;
    }

    public Properties a(int n2) {
        return (Properties)this.x.get(new Integer(n2));
    }

    public final void a(int n2, Rect rect) {
        int n3 = n2 % this.tileProperties;
        int n4 = (int)((float)n2 * this.tileEntries);
        int n5 = this.firstGid + n3 * this.spacing;
        int n6 = this.tileCount + n4 * this.margin;
        rect.a = n5;
        rect.b = n6;
        rect.c = n5 + this.tileWidth;
        rect.d = n6 + this.tileHeight;
    }

    public final Rect b(int n2) {
        if (this.w == n2) {
            return this.v;
        }
        this.w = n2;
        this.a(n2, this.v);
        return this.v;
    }

    public void c(int n2) {
        this.m = n2;
    }

    public boolean d(int n2) {
        return n2 >= this.columns && n2 <= this.m;
    }

    public void d() {
        this.tilesetTexture = null;
        this.o = null;
        this.x = null;
    }

    public Integer b(String string, String string2) {
        Iterator iterator2 = this.x.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator2.next();
            Integer n2 = (Integer)entry.getKey();
            Properties properties = (Properties)entry.getValue();
            String string3 = properties.getProperty(string);
            if (string3 == null || !string3.equals(string2)) continue;
            return n2;
        }
        return null;
    }

    public int a(int n2, int n3) {
        int n4;
        if (this.tilesetTexture == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("getIndexOffsetByPosition tilesetBitmap == null");
            n4 = 3;
        } else if (this.tileWidth == 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("getIndexOffsetByPosition tileWidth==0");
            n4 = 3;
        } else {
            n4 = this.tilesetTexture.m() / this.tileWidth;
        }
        return n2 + n3 * n4;
    }
}
