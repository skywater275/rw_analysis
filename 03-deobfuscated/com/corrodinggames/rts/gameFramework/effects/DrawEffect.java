/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.effects;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.gameFramework.network.ServerResult;

import com.corrodinggames.rts.gameFramework.effects.HUDManager;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.IOException;

public strictfp class DrawEffect
extends GameObject {
    public float a;
    public boolean b = true;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    HUDElement i;
    public int j = 0;
    public int k = 0;
    public int l = -1;
    float m;
    float n;
    float o;
    float p;
    float q;
    float r;
    DrawLayer s;
    public float t;
    public boolean u = false;
    static HUDElement v;
    static HUDElement w;
    private final HUDManager x;

    public static void b() {
        HUDManager c2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bR;
        HUDElement e2 = new HUDElement(c2);
        DrawEffect.a(e2, false);
        e2.aq = 18;
        e2.t = 15.0f;
        v = e2;
        e2 = new HUDElement(c2);
        DrawEffect.b(e2, false);
        w = e2;
    }


    @Override
    /* 覆写 BaseGameObject.serializeToStream (已 throws) */
    public void serializeToStream(OutputNetStream as2) throws IOException {
        as2.a(this.eo);
        as2.a(this.ep);
        as2.a(this.a);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        this.eo = k2.readFloat();
        this.ep = k2.readFloat();
        this.a = k2.readFloat();
        this.b = false;
        super.a(k2);
    }

    public DrawEffect(HUDManager c2) {
        this.x = c2;
    }

    public static void a(HUDElement e2, boolean bl) {
        e2.c();
        e2.aq = 5;
        e2.ap = bl ? GameUtils.a(0, 1) : 0;
        e2.Y = 0.0f;
        e2.an = true;
        e2.P = 0.1f;
        e2.R = 0.5f;
        e2.u = true;
        e2.W = e2.V = 300.0f;
        e2.r = true;
        e2.s = true;
        e2.t = 40.0f;
        e2.as = false;
        e2.ar = (short)2;
        e2.G = 0.4f;
        e2.F = 1.5f;
        e2.g = HUDElement.k;
    }

    public static void b(HUDElement e2, boolean bl) {
        e2.c();
        e2.aq = 7;
        e2.ap = bl ? GameUtils.a(0, 3) : 0;
        e2.Y = 0.0f;
        e2.an = true;
        e2.P = 0.0f;
        e2.R = 0.2f;
        e2.u = true;
        e2.W = e2.V = 50.0f;
        e2.r = true;
        e2.s = true;
        e2.t = 10.0f;
        e2.as = false;
        e2.ar = (short)2;
        e2.g = HUDElement.n;
    }

    public static DrawEffect a(float f2, float f3) {
        DrawEffect f4 = DrawEffect.a(f2, f3, v);
        f4.a = 280.0f;
        f4.f = 10.0f;
        f4.c = 10.0f;
        f4.m = 0.03f;
        f4.n = 0.03f;
        f4.p = 6.0f;
        f4.q = 6.0f;
        f4.s = DrawLayer.a;
        f4.r = 180.0f;
        f4.j = -16777216;
        return f4;
    }

    public static DrawEffect b(float f2, float f3) {
        DrawEffect f4 = DrawEffect.a(f2, f3, w);
        f4.a = 330.0f;
        f4.f = 10.0f;
        f4.c = 10.0f;
        f4.m = 0.1f;
        f4.n = 0.03f;
        f4.p = 4.0f;
        f4.q = 4.0f;
        f4.s = DrawLayer.a;
        return f4;
    }

    public static DrawEffect a(float f2, float f3, HUDElement e2) {
        HUDManager c2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bR;
        DrawEffect f4 = new DrawEffect(c2);
        f4.eo = f2;
        f4.ep = f3;
        f4.a = 100.0f;
        f4.f = 10.0f;
        f4.i = e2;
        if (e2 == null) {
            f4.i = new HUDElement(c2);
            com.corrodinggames.rts.gameFramework.GlobalState.b("Error: Emitter create srcEffect==null");
        }
        return f4;
    }

    public boolean c() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.cP.b(this.eo, this.ep);
    }


    public void a(float f2) {
        this.t = GameUtils.a(this.t, f2);
        if (this.t > 0.0f) {
            return;
        }
        if (this.b) {
            this.c += f2;
            boolean bl = false;
            if (this.c > this.f) {
                this.d += f2;
                if (this.d > this.g) {
                    HUDElement e2;
                    this.d = 0.0f;
                    this.e += 1.0f;
                    if (this.e > this.h) {
                        this.c = 0.0f;
                        this.e = 0.0f;
                    }
                    if ((this.u || this.c()) && (e2 = this.x.b(this.eo, this.ep, 0.0f, SoundEffect.a, false, this.s)) != null) {
                        e2.a(this.i);
                        e2.P += com.corrodinggames.rts.gameFramework.GameUtils.c(-this.m, this.m);
                        e2.Q += com.corrodinggames.rts.gameFramework.GameUtils.c(-this.n, this.n);
                        e2.R += com.corrodinggames.rts.gameFramework.GameUtils.c(-this.o, this.o);
                        e2.Y = com.corrodinggames.rts.gameFramework.GameUtils.c(-this.r, this.r);
                        e2.I = this.eo;
                        e2.J = this.ep;
                        e2.I += com.corrodinggames.rts.gameFramework.GameUtils.c(-this.p, this.p);
                        e2.J += com.corrodinggames.rts.gameFramework.GameUtils.c(-this.q, this.q);
                        if (this.j != 0) {
                            e2.x = this.j;
                        }
                        if (this.l >= 0) {
                            e2.y = this.k;
                            e2.z = this.l;
                        }
                    }
                }
            }
        }
        this.a -= f2;
        if (this.a < 0.0f) {
            this.a();
        }
    }


    public boolean a(GlobalState l2) {
        return false;
    }


    public boolean c(float f2) {
        return false;
    }


    public void e(float f2) {
    }


    public void a(float f2, boolean bl) {
    }


    public void d(float f2) {
    }


    public boolean f(float f2) {
        return false;
    }
}
