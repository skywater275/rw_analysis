/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.ab;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.m;

public class aa
extends bq {
    int a;
    PointF b = new PointF();

    public void a() {
        this.a = 1;
    }

    @Override
    public void a(as as2) {
        as2.a(0);
        as2.a(this.a);
    }

    public void a(k k2) {
        k2.f();
        this.a = k2.f();
    }

    public void a(float f) {
    }

    public ab b() {
        ab ab2 = new ab(this);
        ab2.e = this.a++;
        return ab2;
    }

    public ab c() {
        ab ab2 = new ab(this);
        ab2.e = -1;
        ab2.b = true;
        return ab2;
    }

    public void a(m m2, y y2, m m3, float f2, int n2) {
        int n3 = 0;
        while (!m3.isEmpty()) {
            y y3 = null;
            float f3 = -1.0f;
            PointF pointF = null;
            int n4 = -1;
            Object[] objectArray = m3.a();
            Object[] objectArray2 = m2.a();
            int n5 = m2.size();
            for (int i2 = 0; i2 < n5; ++i2) {
                y y4 = (y)objectArray2[i2];
                if (y4.ad != y2 || y4.aj) continue;
                float f4 = -1.0f;
                PointF pointF2 = null;
                int n6 = -1;
                for (int i3 = 0; i3 < m3.a; ++i3) {
                    PointF pointF3 = (PointF)objectArray[i3];
                    float f5 = y2.eo + pointF3.a;
                    float f6 = y2.ep + pointF3.b;
                    float f7 = f.a(y4.eo, y4.ep, f5, f6);
                    if (f4 != -1.0f && !(f7 < f4)) continue;
                    f4 = f7;
                    pointF2 = pointF3;
                    n6 = i3;
                }
                if (!(f4 > f3)) continue;
                y3 = y4;
                f3 = f4;
                pointF = pointF2;
                n4 = n6;
            }
            if (y3 == null) break;
            ++n3;
            y3.aj = true;
            y3.ak = pointF.a;
            y3.al = pointF.b;
            y3.am = f2;
            y3.ao = f3;
            y3.ah = (short)(n2 + 1);
            m3.remove(n4);
        }
    }

    public void a(m m2, y y2) {
        boolean bl = false;
        while (true) {
            y y3 = null;
            for (y y4 : m2) {
                if (y4.ad != y2 || !(y4.ao > 0.0f) || y3 != null && !(y4.ao > y3.ao) || !y4.aj || !(y4.ao > 100.0f)) continue;
                y3 = y4;
            }
            if (y3 == null) break;
            y3.aj = false;
            Object object = null;
            float f2 = 0.0f;
            y y5 = y3;
            int n2 = f.a((int)y5.ao);
            for (y y6 : m2) {
                if (y6.ad != y2 || !(y6.ao > 0.0f) || y6 == y5) continue;
                int n3 = f.a((int)y6.ao) + n2;
                int n4 = 0;
                n4 += f.c(y5.eo, y5.ep, y2.eo + y6.ak, y2.ep + y6.al);
                float f3 = (n4 += f.c(y6.eo, y6.ep, y2.eo + y5.ak, y2.ep + y5.al)) - n3;
                if (!(f3 < f2)) continue;
                f2 = f3;
                object = y6;
            }
            if (object == null) continue;
            float f4 = y5.ak;
            float f5 = y5.al;
            y5.ak = ((y)object).ak;
            y5.al = ((y)object).al;
            y5.ao = f.a(y5.eo, y5.ep, y2.eo + y5.ak, y2.ep + y5.al);
            ((y)object).ak = f4;
            ((y)object).al = f5;
            ((y)object).ao = f.a(((y)object).eo, ((y)object).ep, y2.eo + ((y)object).ak, y2.ep + ((y)object).al);
        }
    }

    public m a(int n2, float f2, float f3) {
        int n3 = 1;
        int n4 = 0;
        int n5 = 6;
        int n6 = n5 / 2;
        float f4 = 2.0f + f2 * 2.0f * 1.5f;
        m m2 = new m();
        int n7 = n2;
        if (n7 % 2 != 0) {
            ++n7;
        }
        float f5 = f.k(f3);
        float f6 = f.j(f3);
        for (int i2 = 0; i2 < n7; ++i2) {
            int n8 = n3 % 2 == 0 ? n6 + n3 / 2 : n6 - (n3 + 1) / 2;
            float f7 = (float)(n8 - n6) * f4;
            float f8 = (float)(-n4) * f4;
            float f9 = f8 * f5 - f7 * f6;
            float f10 = f7 * f5 + f8 * f6;
            m2.add(new PointF(f9, f10));
            if (++n3 <= n5) continue;
            n3 = 0;
            ++n4;
        }
        return m2;
    }
}
