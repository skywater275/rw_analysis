/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

public abstract class HUDOverlay {  // 02b f/b.java (v19.133f3: 构造补缺)
    String a;

    HUDOverlay(String string) {  // 02b f/b.java L6: b(String)
        this.a = string;
    }

    void b(String string) {
        this.a = string;
    }

    String a() {
        return this.a;
    }

    abstract void b();
}
