/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.o;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.i;
import com.corrodinggames.rts.gameFramework.u;
import java.io.IOException;

public class h
extends e {
    public static ae x;
    public static ae y;
    public static ae z;
    public static boolean A;
    boolean B = false;
    boolean C = false;
    private e H;
    private e I;
    int D;
    int E;
    o F;
    public static float G;

    public static synchronized void C() {
        if (A) {
            return;
        }
        try {
            com.corrodinggames.rts.gameFramework.l.e("Loading team shaders...");
            x = new i("assets/shaders/pureGreenTeamColor.frag", true);
            x.a("teamColor", -1);
            x.c();
            y = new i("assets/shaders/hueAddTeamColor.frag", false);
            y.a("teamColorAmount", 0.15f);
            y.a("teamColor", -1);
            y.c();
            z = new i("assets/shaders/hueShiftTeamColor.frag", false);
            z.a("teamColor", -1);
            z.c();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        A = true;
    }

    public void D() {
        if (!A) {
            com.corrodinggames.rts.gameFramework.m.h.C();
        }
    }

    @Override
    public String a() {
        if (this.H == null) {
            return "LazyColoring (error sourceBitmap==null)";
        }
        return "LazyColoring(" + this.E + "):" + this.H.a();
    }

    public h(e e2, int n2, o o2, int n3) {
        if (e2 == null) {
            throw new RuntimeException("baseImage==null");
        }
        this.H = e2;
        this.D = n2;
        this.F = o2;
        this.E = n3;
        this.H.a(this);
        this.k = null;
    }

    public void c(boolean bl) {
        if (com.corrodinggames.rts.gameFramework.l.az()) {
            if (bl) {
                // empty if block
            }
            this.D();
            if (this.F == com.corrodinggames.rts.game.o.b) {
                this.a(y);
            } else if (this.F == com.corrodinggames.rts.game.o.d) {
                this.a(z);
            } else {
                this.a(x);
            }
            this.I = this.H;
            this.C = true;
            return;
        }
        if (this.H.A()) {
            com.corrodinggames.rts.gameFramework.l.e("Lazy loaded bitmap using errored image: " + this.H.a());
            this.I = this.H;
            return;
        }
        try {
            if (bl) {
                com.corrodinggames.rts.gameFramework.l.e("Loading in lazy loaded bitmap:" + this.H.a() + " team:" + this.E);
            }
            long l2 = br.a();
            this.H.i();
            this.I = this.H.h();
            this.I.j();
            e[] eArray = new e[]{this.I};
            int[] nArray = new int[]{this.D};
            int[] nArray2 = new int[]{this.E};
            long l3 = br.a();
            if (this.F == com.corrodinggames.rts.game.o.b) {
                com.corrodinggames.rts.game.n.b(this.H, eArray, nArray);
            } else if (this.F == com.corrodinggames.rts.game.o.d) {
                com.corrodinggames.rts.game.n.a(this.H, eArray, nArray, nArray2);
            } else {
                com.corrodinggames.rts.game.n.a(this.H, eArray, nArray);
            }
            double d2 = br.a(l3);
            this.I.p();
            this.I.s();
            this.H.q();
            this.H = null;
            double d3 = br.a(l2);
            if (d3 > 1.0) {
                com.corrodinggames.rts.gameFramework.l.e((this.F == com.corrodinggames.rts.game.o.a ? "Standard " : "Hue ") + "Colouring took:" + br.a(d3) + " (" + br.a(d2) + ")");
            }
            G = (float)((double)G + d3);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.e("Colouring failed with OOM");
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.c, (Throwable)outOfMemoryError);
            this.I = com.corrodinggames.rts.gameFramework.l.B().bO.r();
        }
    }

    @Override
    public Bitmap b() {
        if (this.C && !com.corrodinggames.rts.gameFramework.l.az()) {
            com.corrodinggames.rts.gameFramework.l.e("Team shader coloring now disabled. Recoloring image: " + this.H.a());
            this.B = false;
            this.C = false;
            this.a((ae)null);
        }
        if (!this.B) {
            this.c(true);
            this.B = true;
        }
        return this.I.k;
    }

    @Override
    public e c() {
        if (this.C && !com.corrodinggames.rts.gameFramework.l.az()) {
            com.corrodinggames.rts.gameFramework.l.e("Team shader coloring now disabled. Recoloring image: " + this.H.a());
            this.B = false;
            this.C = false;
            this.a((ae)null);
        }
        if (!this.B) {
            if (G > 60.0f) {
                // empty if block
            }
            this.c(true);
            this.B = true;
        }
        if (this.I == null) {
            throw new RuntimeException("coloredBitmap==null");
        }
        return this.I;
    }

    @Override
    public void w() {
        if (!this.B) {
            this.c(false);
            this.B = true;
        }
    }

    @Override
    public int u() {
        if (!this.B & this.H != null) {
            return this.H.u();
        }
        return super.u();
    }
}
