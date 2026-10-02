/*
 * Decompiled with CFR 0.152.
 */
package com;

import com.Element;
import java.util.ArrayList;

public class ElementList {
    ArrayList elements = new ArrayList();

    public Element getFirst() {
        if (this.elements.size() > 0) {
            return (Element)this.elements.get(0);
        }
        return null;
    }
}
