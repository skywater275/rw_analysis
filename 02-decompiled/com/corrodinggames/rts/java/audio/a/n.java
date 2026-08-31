/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.a;

class n {
    static final float[] a;

    static {
        int n2;
        a = new float[16384];
        for (n2 = 0; n2 < 16384; ++n2) {
            n.a[n2] = (float)Math.sin(((float)n2 + 0.5f) / 16384.0f * ((float)Math.PI * 2));
        }
        for (n2 = 0; n2 < 360; n2 += 90) {
            n.a[(int)((float)n2 * 45.511112f) & 0x3FFF] = (float)Math.sin((float)n2 * ((float)Math.PI / 180));
        }
    }
}
