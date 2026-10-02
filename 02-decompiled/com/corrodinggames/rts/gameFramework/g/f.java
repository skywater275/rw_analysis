/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.custom.e.a;
import java.util.ArrayList;

public enum f {
    a,
    b,
    c,
    d,
    e,
    f;


    public int a(n n2) {
        switch (this) {
            default: {
                return 0;
            }
            case b: {
                int n3 = n2.v();
                ArrayList arrayList = com.corrodinggames.rts.game.units.custom.e.a.f();
                for (a a2 : arrayList) {
                    float f2;
                    if (!a2.d() || (f2 = a2.b()) == 0.0f) continue;
                    n3 = (int)((float)n3 + f2 * (float)n2.b(a2));
                }
                return n3;
            }
            case c: {
                return n2.T.n;
            }
            case d: {
                return n2.T.o;
            }
            case e: {
                return n2.T.n + n2.T.o;
            }
            case f: 
        }
        return (int)n2.o;
    }
}
