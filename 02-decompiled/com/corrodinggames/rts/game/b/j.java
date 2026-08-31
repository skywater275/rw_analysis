/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.b;

import android.graphics.Rect;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.b.k;
import com.corrodinggames.rts.gameFramework.l;
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

public strictfp class j {
    public String a;
    public com.corrodinggames.rts.gameFramework.m.e b;
    public String c;
    int d;
    int e;
    int f;
    int g;
    int h = 0;
    int i = 0;
    int j;
    float k;
    public int l;
    public int m = Integer.MAX_VALUE;
    public short n;
    public b o;
    public boolean p = false;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    private HashMap x = new HashMap();
    static String t = "[EMBED]";
    static ArrayList u = new ArrayList();
    Rect v = new Rect();
    int w = -1;

    public String a(b b2, Element element) {
        Element element2 = (Element)element.getElementsByTagName("properties").item(0);
        if (element2 != null) {
            NodeList nodeList = element2.getElementsByTagName("property");
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Element element3 = (Element)nodeList.item(i);
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

    public static Element a(b b2, String string) {
        try {
            InputStream inputStream = b2.d("tilesets/", string);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element element = document.getDocumentElement();
            return element;
        }
        catch (Exception exception) {
            com.corrodinggames.rts.gameFramework.l.B().a("Unable to load or parse sourced tileset: tilesets/" + string, 1);
            throw new f("Unable to load or parse sourced tileset: tilesets/" + string, exception);
        }
    }

    public j(b b2, String string, int n2) {
        this.o = b2;
        this.l = n2;
        Element element = com.corrodinggames.rts.game.b.j.a(b2, string);
        this.a = string;
        this.a(element);
    }

    public j(b b2, Element element) {
        this.o = b2;
        this.l = Integer.parseInt(element.getAttribute("firstgid"));
        String string = element.getAttribute("source");
        if (string != null && !string.equals("")) {
            element = com.corrodinggames.rts.game.b.j.a(b2, string);
            this.a = string;
        }
        this.a(element);
    }

    public void a(Element element) {
        Object object;
        NodeList nodeList = element.getElementsByTagName("image");
        if (nodeList.getLength() > 0) {
            object = (Element)nodeList.item(0);
            String string = object.getAttribute("source");
            string = string.trim();
            this.c = com.corrodinggames.rts.gameFramework.l.k(string);
        }
        if ((object = this.a(this.o, element)) != null) {
            this.c = com.corrodinggames.rts.game.b.j.a((String)object, this.c);
        }
        if (this.c == null) {
            throw new f("Map tileset is missing an image tag or embedded image data");
        }
        this.d = this.o.n;
        this.e = this.o.o;
        if (element.hasAttribute("tilewidth")) {
            this.d = Integer.parseInt(element.getAttribute("tilewidth"));
            this.e = Integer.parseInt(element.getAttribute("tileheight"));
        }
        if (com.corrodinggames.rts.gameFramework.l.C()) {
            this.d = this.o.n;
            this.e = this.o.o;
        }
        int n2 = 0;
        if (element.hasAttribute("spacing")) {
            n2 = Integer.parseInt(element.getAttribute("spacing"));
        }
        this.f = this.d + n2;
        this.g = this.e + n2;
        NodeList nodeList2 = element.getElementsByTagName("tile");
        for (int i2 = 0; i2 < nodeList2.getLength(); ++i2) {
            Element element2 = (Element)nodeList2.item(i2);
            int n3 = Integer.parseInt(element2.getAttribute("id"));
            n3 += this.l;
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
        for (k k2 : u) {
            if (!string.equalsIgnoreCase(k2.f)) continue;
            return k2.d;
        }
        k k3 = new k();
        k3.b = false;
        k3.e = null;
        k3.f = string;
        k3.c = t;
        k3.d = t + com.corrodinggames.rts.game.b.k.a;
        k3.g = string2;
        ++com.corrodinggames.rts.game.b.k.a;
        u.add(k3);
        return k3.d;
    }

    public static com.corrodinggames.rts.gameFramework.m.e a(String string) {
        Object object;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String string2 = "tilesets/bitmaps/";
        if (string.startsWith(t)) {
            string2 = t;
        }
        k k2 = null;
        for (Object object2 : u) {
            if (!string.equalsIgnoreCase(((k)object2).d) || !string2.equalsIgnoreCase(((k)object2).c)) continue;
            k2 = object2;
            break;
        }
        if (k2 != null) {
            if (k2.f != null) {
                com.corrodinggames.rts.gameFramework.m.e e2;
                Object object2;
                object = com.corrodinggames.rts.game.b.e.a(k2.f, "base64", "");
                object2 = new BufferedInputStream((InputStream)object);
                boolean bl = false;
                try {
                    e2 = l2.bO.a((InputStream)object2, bl);
                }
                catch (RuntimeException runtimeException) {
                    runtimeException.printStackTrace();
                    throw new f("Error loading embedded base64 image:" + k2.g + " - " + runtimeException.getMessage());
                }
                if (e2 == null) {
                    throw new f("Embedded tilesetBitmap is null for: " + string);
                }
                k2.e = e2;
                k2.f = null;
            }
            k2.b = true;
            return k2.e;
        }
        try {
            object = l2.bL.d(string2, string);
        }
        catch (IOException iOException) {
            throw new f("Image file could not be found or loaded: " + string2 + string, iOException);
        }
        boolean bl = false;
        com.corrodinggames.rts.gameFramework.m.e e3 = l2.bO.a((InputStream)object, bl);
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
        k k3 = new k();
        k3.b = true;
        k3.e = e3;
        k3.c = string2;
        k3.d = string;
        u.add(k3);
        return k3.e;
    }

    public static void a() {
        for (k k2 : u) {
            k2.b = false;
        }
    }

    public static void b() {
        Iterator iterator = u.iterator();
        while (iterator.hasNext()) {
            k k2 = (k)iterator.next();
            if (k2.b) continue;
            if (k2.e != null) {
                k2.e.o();
                k2.e = null;
            }
            k2.f = null;
            iterator.remove();
        }
    }

    void c() {
        this.b = com.corrodinggames.rts.game.b.j.a(this.c);
        this.j = this.b.m() / this.f;
        if (this.j == 0) {
            this.j = 1;
        }
        this.k = 1.0f / (float)this.j;
    }

    public Properties a(int n2) {
        return (Properties)this.x.get(new Integer(n2));
    }

    public final void a(int n2, Rect rect) {
        int n3 = n2 % this.j;
        int n4 = (int)((float)n2 * this.k);
        int n5 = this.h + n3 * this.f;
        int n6 = this.i + n4 * this.g;
        rect.a = n5;
        rect.b = n6;
        rect.c = n5 + this.d;
        rect.d = n6 + this.e;
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
        return n2 >= this.l && n2 <= this.m;
    }

    public void d() {
        this.b = null;
        this.o = null;
        this.x = null;
    }

    public Integer b(String string, String string2) {
        for (Map.Entry entry : this.x.entrySet()) {
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
        if (this.b == null) {
            com.corrodinggames.rts.gameFramework.l.e("getIndexOffsetByPosition tilesetBitmap == null");
            n4 = 3;
        } else if (this.d == 0) {
            com.corrodinggames.rts.gameFramework.l.e("getIndexOffsetByPosition tileWidth==0");
            n4 = 3;
        } else {
            n4 = this.b.m() / this.d;
        }
        return n2 + n3 * n4;
    }
}
