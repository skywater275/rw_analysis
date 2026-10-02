/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.ArrayList;

public class ba {
    int a;
    String b;
    boolean c;
    public float d;
    public float e;
    public float f = 2.0f;
    public float g = 0.0f;
    public boolean h = true;
    public float i;
    public float j;
    public float k;
    public boolean l;
    public float m = 1.0f;
    public boolean n = true;
    public boolean o = false;
    public boolean p;
    public LogicBoolean q;
    public float r = 1.0f;
    public float s = 1.0f;
    public float t = 0.0f;
    public float u = 3.0f;
    public float v = 0.3f;
    public float w;
    public e x;
    public e[] y;
    public boolean z;
    public float A;
    public e B;
    public e[] C;
    public e D;
    public boolean E;
    public boolean F = true;
    public boolean G = true;
    public boolean H;
    public boolean I = true;
    public boolean J = true;
    public float K = 7.0f;
    public int L = 3;
    public boolean M = true;
    public float N = 16.0f;
    public float O = 50.0f;
    public boolean P = false;
    public boolean Q = false;
    public float R = 0.0f;
    public int[] S;
    public float T;

    public void a(ba ba2) {
        this.d = ba2.d;
        this.e = ba2.e;
        this.i = ba2.i;
        this.j = ba2.j;
        this.k = ba2.k;
        this.f = ba2.f;
        this.g = ba2.g;
        this.h = ba2.h;
        this.l = ba2.l;
        this.m = ba2.m;
        this.n = ba2.n;
        this.o = ba2.o;
        this.t = ba2.t;
        this.p = ba2.p;
        this.r = ba2.r;
        this.s = ba2.s;
        this.u = ba2.u;
        this.w = ba2.w;
        this.x = ba2.x;
        this.y = ba2.y;
        this.A = ba2.A;
        this.z = ba2.z;
        this.B = ba2.B;
        this.C = ba2.C;
        this.D = ba2.D;
        this.E = ba2.E;
        this.F = ba2.F;
        this.G = ba2.G;
        this.v = ba2.v;
        this.H = ba2.H;
        this.I = ba2.I;
        this.J = ba2.J;
        this.K = ba2.K;
        this.L = ba2.L;
        this.M = ba2.M;
        this.N = ba2.N;
        this.O = ba2.O;
        this.P = ba2.P;
        this.Q = ba2.Q;
        this.R = ba2.R;
        this.T = ba2.T;
    }

    public static void a(ba ba2, l l2, ab ab2, String string, boolean bl2, ArrayList arrayList) {
        Float f2;
        Float f3;
        Boolean bl3;
        Boolean bl4;
        Boolean bl5;
        Boolean bl6;
        Boolean bl7;
        e e2;
        e e3;
        e e4;
        e e5;
        e e6;
        Float f4;
        Object object;
        int n2;
        if (!bl2) {
            ba2.l = true;
            ba2.J = false;
        }
        if ((n2 = ab2.b(string, "copyFrom", 0).intValue()) != 0) {
            if (n2 - 1 >= arrayList.size()) {
                throw new RuntimeException("copyFrom: Leg/Arm copy target not loaded yet: " + n2);
            }
            object = (ba)arrayList.get(n2 - 1);
            ba2.a((ba)object);
        }
        ba2.d = ab2.i(string, "x");
        ba2.e = ab2.i(string, "y");
        ba2.b = string.replace("_", "");
        ba2.c = bl2;
        object = ab2.a(string, "attach_x", (Float)null);
        if (object != null) {
            ba2.j = ((Float)object).floatValue();
        }
        if ((f4 = ab2.a(string, "attach_y", (Float)null)) != null) {
            ba2.k = f4.floatValue();
        }
        ba2.f = ab2.a(string, "liftingHeightOffset", Float.valueOf(ba2.f)).floatValue();
        ba2.g = ab2.a(string, "targetHeight", Float.valueOf(ba2.g)).floatValue();
        ba2.h = ab2.a(string, "targetHeightRelative", (Boolean)ba2.h);
        ba2.i = ab2.a(string, "endDirOffset", Float.valueOf(0.0f)).floatValue();
        ba2.l = ab2.a(string, "lockMovement", (Boolean)ba2.l);
        ba2.m = ab2.a(string, "estimatingPositionMultiplier", Float.valueOf(ba2.m)).floatValue();
        ba2.q = ab2.a(l2, string, "hidden", ba2.q);
        ba2.p = ba2.q == LogicBoolean.trueBoolean;
        ba2.r = ab2.a(string, "alpha", Float.valueOf(ba2.r)).floatValue();
        Float f5 = ab2.a(string, "moveSpeed", (Float)null);
        if (f5 != null) {
            ba2.s = f5.floatValue();
        }
        ba2.t = ab2.b(string, "moveWarmUp", Float.valueOf(ba2.t)).floatValue();
        ba2.u = ab2.a(string, "rotateSpeed", Float.valueOf(ba2.u)).floatValue();
        Float f6 = ab2.a(string, "resetAngle", (Float)null);
        if (f6 != null) {
            ba2.w = f6.floatValue();
        }
        boolean bl8 = ab2.a(string, "image_end_teamColors", (Boolean)false);
        e e7 = l2.a(ab2, string, "image_foot");
        if (e7 != null) {
            ba2.B = e7;
            ba2.C = bl8 ? l2.a(ba2.B, l2.ac) : null;
        }
        if ((e6 = l2.a(ab2, string, "image_end")) != null) {
            ba2.B = e6;
            ba2.C = bl8 ? l2.a(ba2.B, l2.ac) : null;
        }
        if ((e5 = l2.a(ab2, string, "image_foot_shadow")) != null) {
            ba2.D = e5;
        }
        if ((e4 = l2.a(ab2, string, "image_end_shadow")) != null) {
            ba2.D = e4;
        }
        if ((e3 = l2.a(ab2, string, "image_leg")) != null) {
            ba2.x = e3;
        }
        if ((e2 = l2.a(ab2, string, "image_middle")) != null) {
            ba2.x = e2;
        }
        boolean bl9 = ab2.a(string, "image_middle_teamColors", (Boolean)false);
        ba2.y = ba2.x != null && bl9 ? l2.a(ba2.x, l2.ac) : null;
        Float f7 = ab2.a(string, "heightSpeed", (Float)null);
        if (f7 != null) {
            ba2.v = f7.floatValue();
        }
        if ((bl7 = ab2.a(string, "draw_foot_on_top", (Boolean)null)) != null) {
            ba2.H = bl7;
        }
        if ((bl6 = ab2.a(string, "dust_effect", (Boolean)null)) != null) {
            ba2.I = bl6;
        }
        if ((bl5 = ab2.a(string, "drawLegWhenZoomedOut", (Boolean)null)) != null) {
            ba2.F = bl5;
            ba2.E = true;
        }
        if ((bl4 = ab2.a(string, "drawFootWhenZoomedOut", (Boolean)null)) != null) {
            ba2.G = bl4;
            ba2.E = true;
        }
        if (!(ba2.E || ba2.l || ba2.P)) {
            if (l2.cW < 30) {
                ba2.F = false;
            }
            if (l2.cW < 20) {
                ba2.G = false;
            }
        }
        if ((bl3 = ab2.a(string, "explodeOnDeath", (Boolean)null)) != null) {
            ba2.J = bl3;
        }
        if ((f3 = ab2.a(string, "holdDisMin", (Float)null)) != null) {
            ba2.K = f3.floatValue();
        }
        ba2.L = ab2.b(string, "holdDisMin_maxMovingLegs", ba2.L);
        ba2.M = ab2.a(string, "hold_moveOnlyIfFurthest", (Boolean)ba2.M);
        ba2.n = ab2.a(string, "holdDisMin_checkNeighbours", (Boolean)ba2.n);
        ba2.o = ab2.a(string, "favourOppositeSideNeighbours", (Boolean)ba2.o);
        Float f8 = ab2.a(string, "holdDisMax", (Float)null);
        if (f8 != null) {
            ba2.N = f8.floatValue();
        }
        if ((f2 = ab2.a(string, "hardLimit", (Float)null)) != null) {
            ba2.O = f2.floatValue();
        }
        ba2.P = ab2.a(string, "drawOverBody", (Boolean)ba2.P);
        if (ba2.P) {
            l2.ay = true;
        }
        ba2.Q = ab2.a(string, "drawUnderAllUnits", (Boolean)ba2.Q);
        if (ba2.Q) {
            l2.az = true;
        }
        if (ba2.Q && ba2.P) {
            throw new RuntimeException("Both drawUnderAllUnits and drawOverBody can not be set true at the same time in leg/arm");
        }
        ba2.R = ab2.a(string, "drawDirOffset", Float.valueOf(ba2.R)).floatValue();
        ba2.T = ab2.a(string, "spinRate", Float.valueOf(ba2.T)).floatValue();
    }
}
