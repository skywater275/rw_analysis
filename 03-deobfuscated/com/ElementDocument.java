/*
 * Decompiled with CFR 0.152.
 */
package com;

import com.Element;
import java.util.ArrayList;
import java.util.HashMap;

public class ElementDocument
extends Element {
    public static final int NONE = 0;
    public static final int FOCUS = 2;
    public static final int MODAL = 4;
    public String documentPath;
    public HashMap metadata;
    public boolean translatedToUnicode;
    public ArrayList pageTimers = new ArrayList(1);

    @Override
    public void show() {
        this.show(2);
    }

    public native void show(int var1);

    public void showWithWorkaround(int n) {
    }

    @Override
    public native void hide();

    public void closeDocument() {
        this.close();
    }

    private native void close();

    public native void pullToFront();

    public native void pushToBack();

    public Object getMetadata(String string) {
        if (this.metadata == null) {
            return null;
        }
        return this.metadata.get(string);
    }

    public Object getMetadata(String string, Object object) {
        if (this.metadata == null) {
            return object;
        }
        Object v = this.metadata.get(string);
        if (v == null) {
            return object;
        }
        return v;
    }

    public Float getMetadataFloat(String string) {
        Object object = this.getMetadata(string);
        if (object instanceof Float) {
            return (Float)object;
        }
        return null;
    }

    public void setMetadata(String string, Object object) {
        if (this.metadata == null) {
            this.metadata = new HashMap();
        }
        this.metadata.put(string, object);
    }

    public void setMetadataFloat(String string, Float f) {
        this.setMetadata(string, f);
    }

    public HashMap getMetadataMap() {
        if (this.metadata == null) {
            this.metadata = new HashMap();
        }
        return this.metadata;
    }
}
