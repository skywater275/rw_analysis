/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import java.util.HashMap;

public strictfp class ByteSlot {
    public byte a;
    HashMap b = new HashMap();

    public void a(ByteSlot b2) {
        this.b.put(b2.a, b2);
    }
}
