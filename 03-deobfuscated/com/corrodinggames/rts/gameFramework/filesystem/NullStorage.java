/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.filesystem;

import com.corrodinggames.rts.gameFramework.filesystem.StorageBackend;

public class NullStorage
extends StorageBackend {

    public String b() {
        return "/[NONE]/";
    }


    public String[] b(String string, boolean bl) {
        return null;
    }


    public boolean e() {
        return false;
    }


    public String d() {
        return null;
    }
}
