/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.RangeValue;
import com.corrodinggames.rts.game.units.custom.KeyframePoint;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.CurveType;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.Collections;
import java.util.Iterator;

public final class AnimationCurve {
    CurveType a;
    int b;
    String c;
    float d;
    public RangeValue[] e;
    private CustomArrayList g = new CustomArrayList();  // 02b custom/c.java L19: utility.m g
    public final boolean f = false;

    public KeyframePoint reset() {
        KeyframePoint b2 = null;
        if (this.g.size() > 0) {
            b2 = (KeyframePoint) this.g.get(this.g.size() - 1);
            if (b2.h) {
                b2 = null;
            }
        }
        return b2;
    }

    public void reset(CustomUnitType j2, float f2, float f3, boolean bl) {
        if (bl) {
            return;
        }
        if (f3 <= f2) {
            return;
        }
        RangeValue[] aArray = this.e;
        int n2 = aArray.length;
        int n3 = -1;
        while (n3 + 1 < n2 && !(f2 <= aArray[n3 + 1].a)) {
            ++n3;
        }
        int n4 = n3;
        while (n4 + 1 < n2 && !(f3 <= aArray[n4 + 1].a)) {
            ++n4;
        }
        if (n4 <= n3) {
            return;
        }
        for (int i = n3 + 1; i <= n4; ++i) {
            KeyframePoint b2 = (KeyframePoint) aArray[i];
            b2.a(j2);
        }
    }

    public void b() throws bo {
        KeyframePoint b2;
        if (this.a == com.corrodinggames.rts.game.units.custom.CurveType.i && (b2 = this.reset()) != null) {  // 02b custom/c.java L63: d.i (d=CurveType)
            b2.finalize();
        }
    }

    public void reset(ModUnitRegistry l2, float f2, String string, String string2) throws bo {
        float f3;
        if (this.a == com.corrodinggames.rts.game.units.custom.CurveType.i) {
            KeyframePoint b2 = this.reset();
            if (b2 == null) {
                b2 = new KeyframePoint(f2, 0.0f);
                this.g.add(b2);
            }
            b2.a(l2, string, string2);
            return;
        }
        try {
            f3 = Float.parseFloat(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw new bo("Failed to parse float:" + string2);
        }
        this.reset(f2, f3);
    }

    public void reset(float f2, float f3) throws bo {
        RangeValue a2;
        if (this.a == com.corrodinggames.rts.game.units.custom.CurveType.i) {
            throw new bo("Adding key frame value to event set");
        }
        if (this.g.size() == 0 && f2 > 0.0f && f3 != 0.0f) {
            a2 = new RangeValue(0.0f, 0.0f);
            this.g.add(a2);
        }
        a2 = new RangeValue(f2, f3);
        this.g.add(a2);
    }

    public void reset(float f2) {
        for (Iterator iterator = this.g.iterator(); iterator.hasNext(); ) {  // 02b custom/c.java L110: (a)var2.next()
            RangeValue a2 = (RangeValue) iterator.next();
            a2.a *= f2;
        }
    }

    public void c() {
        Collections.sort(this.g);
        RangeValue a2 = null;
        RangeValue a3 = null;
        for (Iterator iterator = this.g.iterator(); iterator.hasNext(); this.d = a3.a) {  // 02b custom/c.java L121: (a)var2.next()
            a3 = (RangeValue) iterator.next();
            if (a2 != null) {
                a3.c = 1.0f / (a3.a - a2.a);
                a3.d = a3.b - a2.b;
            }
            a2 = a3;
        }
        this.e = (RangeValue[])this.g.toArray(new RangeValue[0]);
        this.g = null;
    }

    public float b(float f2) {
        RangeValue[] aArray = this.e;
        int n2 = aArray.length;
        if (n2 == 1 || f2 <= aArray[0].a) {
            return aArray[0].b;
        }
        if (f2 >= this.d) {
            return aArray[n2 - 1].b;
        }
        int n3 = 1;
        while (f2 > aArray[n3].a) {
            if (++n3 < n2) continue;
            return aArray[n2 - 1].b;
        }
        RangeValue a2 = aArray[n3 - 1];
        RangeValue a3 = aArray[n3];
        float f3 = (f2 - a2.a) * a3.c;
        float f4 = a2.b + a3.d * f3;
        return f4;
    }
}
