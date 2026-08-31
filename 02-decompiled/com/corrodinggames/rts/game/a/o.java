/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.i;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public abstract class o
extends bq {
    public int Q;
    protected final a R;
    public float S;
    public float T;
    public float U;
    public boolean V;
    static final ArrayList W = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.S);
        as2.a(this.T);
        as2.a(this.U);
    }

    public void a(k k2) {
        this.S = k2.g();
        this.T = k2.g();
        this.U = k2.g();
    }

    public o(a a2) {
        ++a2.aI;
        this.Q = a2.aI;
        this.R = a2;
        this.R.bm.add(this);
        this.R.bn.add(this);
    }

    public o(a a2, float f2, float f3) {
        this(a2);
        this.S = f2;
        this.T = f3;
    }

    public void p() {
        this.R.bm.remove(this);
        this.R.bn.remove(this);
        this.V = true;
    }

    public boolean c(float f2, float f3) {
        float f4;
        float f5 = f.a(this.S, this.T, f2, f3);
        return f5 < (f4 = this.U) * f4;
    }

    public boolean b(am am2) {
        float f2;
        float f3 = f.a(this.S, this.T, am2.eo, am2.ep);
        return f3 < (f2 = this.U + am2.cj) * f2;
    }

    public boolean a(am am2, float f2) {
        float f3;
        float f4 = f.a(this.S, this.T, am2.eo, am2.ep);
        return f4 < (f3 = this.U + am2.cj + f2) * f3;
    }

    public float c(am am2) {
        return f.a(this.S, this.T, am2.eo, am2.ep);
    }

    public float a(i i2) {
        return f.a(this.S, this.T, i2.S, i2.T);
    }

    public float d(float f2, float f3) {
        return f.a(this.S, this.T, f2, f3);
    }

    public PointF w() {
        PointF pointF = new PointF();
        float f2 = (float)(Math.random() * 360.0);
        float f3 = (float)(Math.random() * (double)this.U);
        pointF.a(this.S + f.k(f2) * f3, this.T + f.j(f2) * f3);
        return pointF;
    }

    public PointF e(as as2) {
        l l2 = l.B();
        PointF pointF = new PointF();
        float f2 = this.U;
        ao ao2 = ao.b;
        am am2 = null;
        if (as2 == ar.d) {
            f2 = 600.0f;
            ao2 = ao.e;
        }
        for (int i2 = 0; i2 < 15; ++i2) {
            int n2;
            ar ar2 = null;
            boolean bl = false;
            boolean bl2 = false;
            if (this instanceof i) {
                i i3 = (i)this;
                if (i2 < 6 && as2 == ar.J) {
                    ar2 = ar.J;
                }
                if (ar2 != null) {
                    com.corrodinggames.rts.game.units.y y2 = null;
                    if (am2 == null) {
                        am2 = am.c(as2);
                    }
                    if (am2 instanceof com.corrodinggames.rts.game.units.y) {
                        y2 = (com.corrodinggames.rts.game.units.y)am2;
                    }
                    n2 = i3.c(ar2);
                    if (y2 != null && n2 > 1) {
                        int n3 = -1;
                        int n4 = f.a(0, n2 - 1);
                        am[] amArray = am.bE.a();
                        int n5 = am.bE.size();
                        for (int i4 = 0; i4 < n5; ++i4) {
                            am am3 = amArray[i4];
                            if (am3.bX != this.R || !i3.a(am3) || !am3.bT() || !this.R.i(am3) || am3.r() != ar2 || ++n3 != n4) continue;
                            float f3 = am3.eo;
                            float f4 = am3.ep;
                            boolean bl3 = f.a(0, 1) == 0;
                            float f5 = f3;
                            float f6 = f4;
                            if (bl3) {
                                f6 += f.c(-150.0f, 150.0f);
                            } else {
                                f5 += f.c(-150.0f, 150.0f);
                            }
                            boolean bl4 = false;
                            W.clear();
                            am am4 = null;
                            l2.bS.a(y2, f3, f4, f5, f6, bl4, W, am4);
                            if (W.size() > 0) {
                                PointF pointF2 = (PointF)W.get(0);
                                pointF.a(pointF2.a, pointF2.b);
                                bl = true;
                                continue;
                            }
                            bl2 = true;
                        }
                    }
                }
            }
            if (bl2) continue;
            if (!bl) {
                float f7 = (float)(Math.random() * 360.0);
                float f8 = (float)(Math.random() * (double)f2);
                pointF.a(this.S + f.k(f7) * f8, this.T + f.j(f7) * f8);
            }
            l2.bL.a(pointF.a, pointF.b);
            int n6 = l2.bL.T;
            int n7 = l2.bL.U;
            if (l2.bL.c(n6, n7) && ((n2 = y.c(n6, n7, ao2)) > 5 || n2 == 0) && d.a(as2, pointF.a, pointF.b, this.R)) {
                return pointF;
            }
            if (as2 != ar.d) continue;
            f2 += 100.0f;
        }
        return null;
    }
}
