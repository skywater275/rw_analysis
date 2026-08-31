/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.af;
import com.corrodinggames.rts.gameFramework.f.ah;
import com.corrodinggames.rts.gameFramework.f.ai;
import com.corrodinggames.rts.gameFramework.f.ak;
import com.corrodinggames.rts.gameFramework.f.d;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.m;

public class aj {
    m a;
    Rect b;
    Paint c;
    Paint d;

    public void a(float f2, float f3) {
        l l2 = l.B();
        int n2 = 0;
        int n3 = com.corrodinggames.rts.gameFramework.f.d.a(this.c);
        for (ak ak2 : this.a) {
            int n4 = 0;
            af af2 = null;
            for (af af3 : ak2.a) {
                Object object;
                af af4;
                if (af2 != null) {
                    n4 += af2.a(this.c);
                }
                int n5 = (int)(f2 + (float)n4 + (float)this.b.d());
                n5 -= ak2.b / 2;
                int n6 = (int)(f3 + (float)this.b.b + (float)(n3 / 2) + (float)(n2 * n3));
                if (!(af3 instanceof ai)) {
                    if (af3 instanceof ah) {
                        af4 = (ah)af3;
                        object = ((ah)af4).a;
                        l2.bO.a((e)object, (float)n5, (float)n6 - (float)((e)object).q * ((ah)af4).b, (Paint)ae.c, 0.0f, ((ah)af4).b);
                    }
                    af2 = af3;
                    continue;
                }
                af4 = (ai)af3;
                object = af4.b(this.c);
                l2.bO.a(af4.d, (float)n5, (float)n6, (Paint)object);
                af2 = af3;
            }
            ++n2;
        }
    }
}
