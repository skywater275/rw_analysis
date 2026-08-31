/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.h;

import com.corrodinggames.rts.gameFramework.l;
import java.util.Collections;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

strictfp class b
extends ResourceBundle {
    ResourceBundle a;
    ResourceBundle b;

    public b(ResourceBundle resourceBundle, ResourceBundle resourceBundle2) {
        this.a = resourceBundle;
        this.b = resourceBundle2;
    }

    public Enumeration getKeys() {
        l.e("MultipleResourceBundle: Slow get keys");
        Vector<String> vector = new Vector<String>();
        vector.addAll(Collections.list(this.a.getKeys()));
        if (this.b != null) {
            for (String string : Collections.list(this.b.getKeys())) {
                if (vector.contains(string)) continue;
                vector.add(string);
            }
        }
        return vector.elements();
    }

    @Override
    protected Object handleGetObject(String string) {
        Object object;
        try {
            object = this.a.getObject(string);
        }
        catch (MissingResourceException missingResourceException) {
            object = null;
        }
        if (object == null && this.b != null) {
            try {
                object = this.b.getObject(string);
            }
            catch (MissingResourceException missingResourceException) {
                object = null;
            }
        }
        return object;
    }
}
