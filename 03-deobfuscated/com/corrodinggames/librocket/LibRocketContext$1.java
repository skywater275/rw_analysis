/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;

import com.corrodinggames.librocket.LibRocketContext;

class LibRocketContext$1
implements Runnable {
    final /* synthetic */ LibRocketContext a;

    LibRocketContext$1(LibRocketContext a2) {
        this.a = a2;
    }

    @Override
    public void run() {
        this.a.b.c.getRoot().event_unicodeEntered();
    }
}
