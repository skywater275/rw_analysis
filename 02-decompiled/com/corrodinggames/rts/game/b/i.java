/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.b;

import com.corrodinggames.rts.game.b.a;
import com.corrodinggames.rts.game.b.b;
import java.util.ArrayList;
import java.util.Properties;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public strictfp class i {
    public int a;
    public String b;
    public ArrayList c;
    public int d;
    public int e;
    public Properties f;

    public a a(String string) {
        if (this.c != null) {
            for (a a2 : this.c) {
                if (!string.equalsIgnoreCase(a2.b)) continue;
                return a2;
            }
        }
        return null;
    }

    public i(Element element, b b2) {
        Object object;
        Element element2;
        int n;
        NodeList nodeList;
        this.b = element.getAttribute("name");
        if (element.hasAttribute("width")) {
            this.d = Integer.parseInt(element.getAttribute("width"));
        }
        if (element.hasAttribute("height")) {
            this.e = Integer.parseInt(element.getAttribute("height"));
        }
        this.c = new ArrayList();
        Element element3 = (Element)element.getElementsByTagName("properties").item(0);
        if (element3 != null && (nodeList = element3.getElementsByTagName("property")) != null) {
            this.f = new Properties();
            for (n = 0; n < nodeList.getLength(); ++n) {
                element2 = (Element)nodeList.item(n);
                object = element2.getAttribute("name");
                String string = element2.getAttribute("value");
                this.f.setProperty((String)object, string);
            }
        }
        nodeList = element.getElementsByTagName("object");
        n = 0;
        while (n < nodeList.getLength()) {
            element2 = (Element)nodeList.item(n);
            object = new a(element2, b2, this);
            ((a)object).a = n++;
            this.c.add(object);
        }
    }
}
