/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

import com.corrodinggames.rts.java.audio.backend.n;
import java.util.Random;

public final class m {
    public static Random a = new Random();

    public static float a(float f) {
        return n.a[(int)(f * 2607.5945f) & 0x3FFF];
    }

    public static float b(float f) {
        return n.a[(int)((f + 1.5707964f) * 2607.5945f) & 0x3FFF];
    }

    public static int a(int n2) {
        return a.nextInt(n2 + 1);
    }

    public static int b(int n2) {
        if (n2 == 0) {
            return 1;
        }
        --n2;
        n2 |= n2 >> 1;
        n2 |= n2 >> 2;
        n2 |= n2 >> 4;
        n2 |= n2 >> 8;
        n2 |= n2 >> 16;
        return n2 + 1;
    }

    public static float a(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        if (f > f3) {
            return f3;
        }
        return f;
    }
}
