/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.custom.b.e;
import com.corrodinggames.rts.game.units.custom.b.f;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.m.ag;

public strictfp class d
implements Comparable {
    String a;
    boolean b = false;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    q h = com.corrodinggames.rts.game.q.f;
    boolean i;
    public float j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public int o = -1;
    public float p = 1.0f;
    public boolean q;
    public LogicBoolean r;
    public LogicBoolean s;
    public LogicBoolean t;
    public boolean u;
    public e v;
    public e[] w;
    public float x;
    public int y;
    public boolean z;
    LogicBoolean A;
    LogicBoolean B;
    public com.corrodinggames.rts.gameFramework.m.e C;
    public float D;
    public float E;
    public LogicBoolean F;
    public f G;
    public float H;
    public boolean I;
    public int J = -1;
    public int K = -1;
    public int L = -1;
    public boolean M;
    public LogicBoolean N;
    public int O;
    public int P;
    public float Q;
    public float R;
    public float S;
    public float T;
    public float U;
    public float V;
    public LogicBoolean W;
    public LogicBoolean X;
    public boolean Y;
    public boolean Z;
    public float aa;
    public float ab;
    public LogicBoolean ac;
    public LogicBoolean ad;
    public int ae = -1;
    public boolean af;
    public int ag = -1;
    public ag ah;
    public LogicBoolean ai;

    public int a(d d2) {
        if (d2 == null) {
            return 0;
        }
        float f2 = this.H - d2.H;
        if (f2 < 0.0f) {
            return -1;
        }
        if (f2 > 0.0f) {
            return 1;
        }
        return 0;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((d)object);
    }
}
