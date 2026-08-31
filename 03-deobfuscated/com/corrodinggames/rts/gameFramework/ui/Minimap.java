/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.gameFramework.GameSaver;
import com.corrodinggames.rts.gameFramework.KeyTrigger;
import com.corrodinggames.rts.gameFramework.ui.StatsPanel;
import com.corrodinggames.rts.gameFramework.ui.LineBuffer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.Rect;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.effects.GameHUD;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.ui.UpdateChecker$1;
import com.corrodinggames.rts.gameFramework.ui.MinimapConfig;
import com.corrodinggames.rts.gameFramework.ui.MinimapMarker;
import com.corrodinggames.rts.gameFramework.ui.MinimapMode;
import com.corrodinggames.rts.gameFramework.ui.LineBuffer;
import com.corrodinggames.rts.gameFramework.ui.MinimapTile;
import com.corrodinggames.rts.gameFramework.ui.MinimapUnit;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import java.util.ArrayList;
import java.util.Iterator;

public class Minimap {
    float float1;
    float float2;
    public float minimapWidth = 120.0f;
    public float minimapHeight = 120.0f;
    public boolean isVisible;
    public boolean isInteractive;
    public int tileRenderWidth;
    public int tileRenderHeight;
    public float i;
    public float j;
    int k;
    int l;
    boolean m;
    final Paint n = new Paint();
    final Paint o = new Paint();
    final Paint p = new Paint();
    float q = 0.0f;
    float r = 0.0f;
    final Paint s = new UniquePaint();  // 02b f/o.java L41: new ag() (ThemePaint 为幻觉名)
    final Paint t = new Paint();
    final Paint u = new Paint();
    final Paint v = new Paint();
    public final Rect boundsRect = new Rect();
    final Paint x = new UniquePaint();  // 02b f/o.java L41: new ag() (ThemePaint 为幻觉名)
    final Paint y = new UniquePaint();  // 02b f/o.java L41: new ag() (ThemePaint 为幻觉名)
    final Paint z = new UniquePaint();  // 02b f/o.java L41: new ag() (ThemePaint 为幻觉名)
    final Paint A = new UniquePaint();  // 02b f/o.java L41: new ag() (ThemePaint 为幻觉名)
    final Paint B = new UniquePaint();  // 02b f/o.java L41: new ag() (ThemePaint 为幻觉名)
    final Paint C = new UniquePaint();  // 02b f/o.java L41: new ag() (ThemePaint 为幻觉名)
    final Paint D = new UniquePaint();  // 02b f/o.java L41: new ag() (ThemePaint 为幻觉名)
    final Rect E = new Rect();
    Texture F;
    TextureManagerInterface G;
    Texture H;
    TextureManagerInterface I;
    public com.corrodinggames.rts.gameFramework.rendering.Texture J;
    TextureManagerInterface K;
    float L = 0.0f;
    float M;
    float N;
    public boolean O = false;
    public boolean P = false;
    public float Q = 0.0f;
    int R = 30;
    int S = -1;
    public com.corrodinggames.rts.gameFramework.rendering.Texture T;  // 02b L68: m.e T (GameResult 为幻觉名)
    public com.corrodinggames.rts.gameFramework.rendering.Texture U;
    final Rect V = new Rect();
    UniquePaint[] W;
    UniquePaint X;
    float Y;
    public final ArrayList Z = new ArrayList();
    public final ArrayList aa = new ArrayList();
    private final ArrayList ag = new ArrayList();
    Rect ab = new Rect();
    static ArrayList ac = new ArrayList();
    Point ad = new Point();
    com.corrodinggames.rts.gameFramework.rendering.DrawCommand ae = new Minimap$1(this);  // 02b L80: m.m ae (StatsPanel 为幻觉名)
    ArrayList af = new ArrayList();

    public void a(int n2, int n3, float f2, com.corrodinggames.rts.game.units.UnitInstance am2) {  // 02b L84: am (SelectionGroup 为幻觉名)
        boolean bl = am2 != null && am2.bI();
        for (MinimapMarker q2 : (java.util.Collection<MinimapMarker>) (java.util.Collection) this.Z) {
            if (q2.isVisible != bl || GameUtils.d(n2 - q2.markerX) >= 40 || GameUtils.d(n3 - q2.markerY) >= 40) continue;
            q2.markerRadius += f2;
            return;
        }
        MinimapMarker q3 = new MinimapMarker(this, f2, n2, n3, bl);
        this.Z.add(q3);
    }

    public void a(Context context) {
        this.o.a(Paint$Style.b);
        this.o.a(1.0f);
        this.s.a(255, 255, 255, 255);
        this.s.a(Paint$Style.b);
        this.s.a(1.0f);
        this.W = new UniquePaint[11];
        for (int n4 = 0; n4 <= 10; ++n4) {  // 02b L110
            this.W[n4] = new UniquePaint();  // 02b L111
            this.W[n4].b(-16777216);
            this.W[n4].a(Paint$Style.a);
            this.W[n4].c(n4 * 25);
        }
        this.X = new UniquePaint();  // 02b L117
        this.X.b(-16777216);
        this.X.a(Paint$Style.a);
        this.t.a(255, 255, 0, 0);
        this.t.a(Paint$Style.b);
        this.t.a(2.0f);
        this.u.a(155, 255, 0, 0);
        this.u.a(Paint$Style.b);
        this.u.a(2.0f);
        this.v.a(200, 12, 227, 219);
        this.v.a(Paint$Style.b);
        this.v.a(2.0f);
        this.x.b(-16711936);
        this.y.b(-256);
        this.z.b(-65536);
        this.A.b(a(this.x.e()));  // 02b L132: 本类静态 a(int)
        this.B.b(a(this.y.e()));
        this.C.b(a(this.z.e()));
        this.D.a(210, 255, 255, 255);
    }

    public static int a(int n2) {
        int n3 = Color.a(Color.a(n2), (int)((float)Color.b(n2) * 0.5f), (int)((float)Color.c(n2) * 0.5f), (int)((float)Color.d(n2) * 0.5f));
        return n3;
    }

    public void a() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!com.corrodinggames.rts.gameFramework.ui.InGameUI.bR) {
            this.float1 = (int)(l2.cl - (this.minimapWidth + 0.0f));
            this.float2 = 0.0f;
        } else {
            this.float1 = 0.0f;
            this.float2 = (int)(l2.cm - (this.minimapHeight + 0.0f));
        }
    }

    public int b() {
        return (int)(this.float2 + this.minimapHeight);
    }

    public void a(com.corrodinggames.rts.game.map.MapEngine b2, boolean bl) {  // 02b L159: a(b.b,boolean) (HUDOverlay 为幻觉名)
        this.af.clear();
        if (bl) {
            this.m = true;
            return;
        }
        this.tileRenderWidth = 1;  // 02b L164: g=1 (UpdateChecker$1 为幻觉)
        this.tileRenderHeight = 1;
        this.i = 1.0f;
        this.j = 1.0f;
        this.isInteractive = false;
        this.isVisible = false;
    }

    public void c() {
        if (this.J != null) {
            this.J.o();
            this.J = null;
        }
        if (this.F != null) {
            this.F.o();
            this.F = null;
        }
        if (this.T != null) {
            this.T.o();
            this.T = null;
        }
        if (this.U != null) {
            this.U.o();
            this.U = null;
        }
        if (this.I != null) {
            this.I.q();
            this.I = null;
        }
        if (this.H != null) {
            this.H.o();
            this.H = null;
        }
        this.K = null;
        this.isVisible = false;
    }

    public float d() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.cq;
    }

    public void e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.f();  // 02b L215: this.f() (isInteractive 为幻觉名)
        com.corrodinggames.rts.gameFramework.GlobalState.e("Creating minimap image buffers..");
        if (this.F == null) {
            this.F = l2.bO.a((int)this.minimapWidth, (int)this.minimapHeight, false);
            this.G = l2.bO.b(this.F);
        }
        if (this.J == null) {
            this.J = l2.bO.a((int)this.minimapWidth, (int)this.minimapHeight, false);
            this.K = l2.bO.b(this.J);
        }
        if (this.H == null) {
            this.H = l2.bO.a((int)this.minimapWidth, (int)this.minimapHeight, false);
            this.I = l2.bO.b(this.H);
        }
    }

    public void f() {
        this.minimapHeight = this.d();  // 02b L235: c = d() (minimapHeight 为幻觉名)
        this.minimapWidth = this.minimapHeight;
        this.a();  // 02b L237: a()
    }

    public void g() {
        boolean bl = true;
        long l2 = ExtraManager.a();
        com.corrodinggames.rts.gameFramework.GlobalState.e("--setting up minimap--");
        GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.f();  // 02b L245: this.f()
        this.tileRenderWidth = l3.bL.mapHeight * l3.bL.tilePixelWidth;
        this.tileRenderHeight = l3.bL.tileWidth * l3.bL.tilePixelHeight;
        if (this.tileRenderWidth <= 0) {
            this.tileRenderWidth = 1;  // 02b L249
        }
        if (this.tileRenderHeight <= 0) {
            this.tileRenderHeight = 1;
        }
        this.i = 1.0f / (float)this.tileRenderWidth;
        this.j = 1.0f / (float)this.tileRenderHeight;
        this.isInteractive = true;
        this.e();  // 02b L259: this.e()
        this.Z.clear();
        this.aa.clear();
        this.ag.clear();
        java.util.Iterator iterator = l3.bL.A.iterator();  // 02b L263: 显式迭代 (raw ArrayList)
        while (iterator.hasNext()) {
            Point point = (Point)iterator.next();
            this.ag.add(new MinimapTile(this, point.a, point.b));
        }
        this.G.clearScreen(-16777216);
        this.K.clearScreen(-16777216);
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            Rect rect = new Rect(0, 0, (int)this.minimapWidth, (int)this.minimapHeight);
            l3.bL.groundLayer.a(this.G, 0.0f, 0.0f, 0.0f, 0.0f, this.tileRenderWidth, this.tileRenderHeight, this.minimapWidth / (float)this.tileRenderWidth, this.minimapHeight / (float)this.tileRenderHeight, false, false, false);
            Paint paint = new Paint();
            paint.a(50, 0, 0, 0);
            this.G.clearScreen(rect, paint);
        } else {
            int n2 = 2;
            for (int i2 = 0; i2 < n2; ++i2) {
                for (int i3 = 0; i3 < n2; ++i3) {
                    this.I.clearScreen(-16777216);
                    int n3 = (int)this.minimapWidth / n2;
                    int n4 = (int)this.minimapHeight / n2;
                    int n5 = this.tileRenderWidth / n2;
                    int n6 = this.tileRenderHeight / n2;
                    l3.bL.groundLayer.a(this.I, n5 * i2, n6 * i3, n5 * i2, n6 * i3, n5, n6, this.minimapWidth / (float)n5, this.minimapHeight / (float)n6, false, false, false);
                    Rect rect = new Rect(0, 0, (int)this.minimapWidth, (int)this.minimapHeight);
                    Rect rect2 = new Rect(n3 * i2, n4 * i3, n3 * (i2 + 1), n4 * (i3 + 1));
                    Paint paint = new Paint();
                    paint.a(true);
                    paint.d(true);
                    paint.b(true);
                    this.K.loadImageFromResource(this.H, rect, rect2, paint);
                }
            }
            Rect rect = new Rect(0, 0, (int)this.minimapWidth, (int)this.minimapHeight);
            this.G.clearScreen(-16777216);
            Paint paint = new Paint();
            paint.a(true);
            paint.d(true);
            paint.b(true);
            paint.a(200, 255, 255, 255);
            this.G.loadImageFromResource(this.J, rect, rect, paint);
        }
        this.I.clearScreen(-16777216);
        this.K.clearScreen(-16777216);
        this.M = 50.0f;
        this.b(0.0f, 1.0f);  // 02b L718: b(f,f) (float1 为幻觉名)
        this.isVisible = true;
        double d2 = ExtraManager.a(l2);
        com.corrodinggames.rts.gameFramework.GlobalState.e("Minimap map render took:" + ExtraManager.a(d2));
    }

    void a(float f2, float f3) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.ab.a(0, (int)(f2 * this.minimapHeight), (int)this.minimapWidth, (int)(f3 * this.minimapHeight));
        this.I.loadImageFromResource(this.F, this.ab, this.ab, null);
        com.corrodinggames.rts.game.map.MapEngine b2 = l2.bL;
        if (b2.tileHeight) {
            float f4;
            boolean bl = b2.visibilityGrid;
            UniquePaint ag2 = this.W[5];  // 02b L71: m.ag[] W (KeyTrigger 为幻觉名)
            UniquePaint ag3 = this.W[10];
            UniquePaint ag4 = this.X;
            ag4.c(255);
            if (bl) {
                ag3 = this.W[7];
                f4 = 1.0f - (1.0f - (float)ag2.f() / 255.0f) * (1.0f - (float)ag3.f() / 255.0f);
                ag4.c((int)(f4 * 255.0f));
            }
            f4 = this.minimapWidth / (float)b2.mapHeight;
            float f5 = this.minimapHeight / (float)b2.tileWidth;
            int n2 = 0;
            int n3 = 0;
            int n4 = (int)(f2 * (float)b2.tileWidth) - 1;
            int n5 = (int)(f3 * (float)b2.tileWidth) + 1;
            if (n4 < 0) {
                n4 = 0;
            }
            if (n5 < 0) {
                n5 = 0;
            }
            if (n4 > l2.bL.tileWidth) {
                n4 = b2.tileWidth;
            }
            if (n5 > l2.bL.tileWidth) {
                n5 = b2.tileWidth;
            }
            int n6 = 0;
            byte[][] byArray = l2.bs.N;
            if (byArray != null) {
                int n7 = b2.mapHeight;
                Rect rect = this.V;
                for (int i2 = n4; i2 < n5; ++i2) {
                    for (int i3 = 0; i3 < n7; ++i3) {
                        int n8;
                        byte by = byArray[i3][i2];
                        if (by == 0) continue;
                        int n9 = i3;
                        for (n8 = i3; n8 < n7 - 1 && byArray[n8][i2] == by; ++n8) {
                        }
                        i3 = n8;
                        rect.a(n2 + (int)((float)n9 * f4), n3 + (int)((float)i2 * f5), n2 + (int)((float)(n8 + 1) * f4), n3 + (int)((float)(i2 + 1) * f5));
                        UniquePaint ag5 = by == 10 ? ag4 : ag2;  // 02b: m.ag
                        this.I.b(rect, ag5);
                        if (++n6 <= 2) continue;
                        n6 = 0;
                    }
                }
            }
        }
        this.K.loadImageFromResource(this.H, this.ab, this.ab, null);
        this.J.p();
        if (com.corrodinggames.rts.gameFramework.GlobalState.aY) {
            // empty if block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static LineBuffer a(int n2, Paint paint) {
        ArrayList arrayList = ac;
        synchronized (arrayList) {
            LineBuffer s2 = null;
            for (LineBuffer s3 : (java.util.Collection<LineBuffer>) (java.util.Collection) ac) {
                if (s3.d < n2 || s2 != null && s3.d >= s2.d) continue;
                s2 = s3;
            }
            if (s2 != null) {
                ac.remove(s2);
                s2.c = paint;
                return s2;
            }
        }
        return new LineBuffer(n2 + 15, paint);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void a(LineBuffer s2) {
        s2.c = null;
        s2.b = 0;
        ArrayList arrayList = ac;
        synchronized (arrayList) {
            if (ac.size() < 20) {
                ac.add(s2);
                return;
            }
            Iterator iterator = ac.iterator();
            while (iterator.hasNext()) {
                LineBuffer s3 = (LineBuffer) iterator.next();
                if (s3.d >= s2.d) continue;
                iterator.remove();
                ac.add(s2);
                return;
            }
        }
    }

    void a(com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2, int n2, int n3, float f2, float f3) {  // 02b L454: m.y (StatsPanel 为幻觉名)
        int n4;
        int n5;
        int n6;
        int n7;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        int n8 = 1;  // 02b: var24
        if (this.minimapWidth < 50.0f) {
            n7 = 0;
            n6 = 0;
            n5 = 1;
            n4 = 1;
            n8 = 1;
        } else if (this.minimapWidth < 120.0f) {
            n7 = 0;
            n6 = 0;
            n5 = 2;
            n4 = 2;
            n8 = 2;
        } else {
            n7 = -1;
            n6 = -1;
            n5 = 2;
            n4 = 2;
            n8 = 3;
        }
        n6 += n2;
        n5 += n2;
        n4 += n3;
        n7 += n3;
        boolean bl = false;
        if (l2.bs.b() || l2.cb.j()) {
            bl = true;
        }
        for (int i2 = -1; i2 < com.corrodinggames.rts.game.PlayerState.c; ++i2) {
            int n9;
            int n10;
            Object object;
            PlayerState n11 = com.corrodinggames.rts.game.PlayerState.u(i2);
            if (n11 == null) continue;
            Paint paint = n11.ae;
            if (l2.bQ.useMinimapAllyColors) {
                if (bl) {
                    this.p.b(com.corrodinggames.rts.game.PlayerState.i(n11.r));
                    paint = this.p;
                } else if (l2.bs == n11) {
                    paint = this.x;
                } else if (l2.bs.d(n11)) {
                    paint = this.y;
                } else if (l2.bs.c(n11)) {
                    paint = this.z;
                }
            }
            int n12 = 0;
            if (n11.a(true, false) > 0) {
                com.corrodinggames.rts.game.units.UnitInstance[] amArray6 = com.corrodinggames.rts.game.units.UnitInstance.bE.a();  // 02b L512: am.bE (am 包为幻觉)
                n10 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
                for (n9 = 0; n9 < n10; ++n9) {
                    UnitInstance am2 = amArray6[n9];
                    if (am2.player != n11 || !am2.cR) continue;
                    ++n12;
                }
            }
            if (n12 > 0) {
                paint.a((float)n8);
                object = a(n12, paint);  // 02b L526: 本类静态
                ((LineBuffer) object).e = !l2.bQ.renderWithLineWidth;
                UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
                int n13 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
                for (n10 = 0; n10 < n13; ++n10) {
                    UnitInstance am3 = amArray[n10];
                    if (am3.player != n11 || !am3.cR) continue;
                    ((LineBuffer) object).a(am3.cS, am3.cT);
                }
                if (((LineBuffer) object).b != 0) {
                    l2.bO.a((com.corrodinggames.rts.gameFramework.rendering.DrawCommand) object);  // 02b L539: (m.m)var25
                }
            }
            object = n11.af;
            if (l2.bQ.useMinimapAllyColors) {
                if (bl) {
                    this.p.b(com.corrodinggames.rts.game.PlayerState.i(n11.r));
                    paint = this.p;
                } else if (l2.bs == n11) {
                    object = this.A;
                } else if (l2.bs.d(n11)) {
                    object = this.B;
                } else if (l2.bs.c(n11)) {
                    object = this.C;
                }
            }
            n9 = 0;
            com.corrodinggames.rts.game.units.UnitInstance[] amArray5 = com.corrodinggames.rts.game.units.UnitInstance.bE.a();  // 02b L512
            int n14 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
            for (int i3 = 0; i3 < n14; ++i3) {
                com.corrodinggames.rts.game.units.UnitInstance a2 = amArray5[i3];
                if (a2.player != n11 || !a2.cR) continue;  // 02b L517: bX/cR
                ++n9;
            }
            if (n9 <= 0) continue;
            ((Paint)object).a((float)n8);
            LineBuffer lineBuffer2 = a(n9, (Paint)object);  // 02b L526: s var25
            com.corrodinggames.rts.game.units.UnitInstance[] objectArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
            int n15 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
            for (n14 = 0; n14 < n15; ++n14) {
                com.corrodinggames.rts.game.units.UnitInstance a3 = objectArray[n14];
                if (a3.player != n11 || !a3.cR) continue;
                lineBuffer2.a(a3.cS, a3.cT);
            }
            if (lineBuffer2.b == 0) continue;
            l2.bO.a(lineBuffer2);
        }
    }

    public void a(int n2, int n3, MinimapMode r2) {
        MinimapConfig p2 = new MinimapConfig(this);
        p2.minimapWidth = n2;
        p2.minimapHeight = n3;
        p2.renderMode = r2;
        p2.worldScaleX = 0.9f;
        p2.worldScaleY = 0.9f;
        this.aa.add(p2);
    }

    public void h() {
        Point point;
        Object object2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.m = false;
        this.k = (int)this.float1;
        this.l = (int)this.float2;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();  // 02b L606: am.bE (am 包为幻觉)
        int n2 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!am2.isDead && am2.cN == null && am2.canMove() && am2.isOnRadar() && !am2.u()) {
                object2 = this.b(am2.eo, am2.ep);  // 02b L613: b(f,f) (float2 为幻觉名)
                am2.cS = ((Point)object2).a;
                am2.cT = ((Point)object2).b;
                am2.cR = true;
                continue;
            }
            am2.cR = false;
        }
        Object[] objectArray = com.corrodinggames.rts.gameFramework.effects.GameHUD.w.b();  // 02b L622: d.a.w (EffectBase 为幻觉名)
        int n3 = com.corrodinggames.rts.gameFramework.effects.GameHUD.w.size();
        for (n2 = 0; n2 < n3; ++n2) {
            object2 = (com.corrodinggames.rts.gameFramework.effects.GameHUD) objectArray[n2];  // 02b L627: (d.a)var12
            if (((com.corrodinggames.rts.gameFramework.effects.GameHUD) object2).n || !((com.corrodinggames.rts.gameFramework.effects.GameHUD) object2).u) continue;
            point = this.b(((com.corrodinggames.rts.gameFramework.effects.GameHUD) object2).g, ((com.corrodinggames.rts.gameFramework.effects.GameHUD) object2).h);
            ((com.corrodinggames.rts.gameFramework.effects.GameHUD) object2).l = point.a;
            ((com.corrodinggames.rts.gameFramework.effects.GameHUD) object2).m = point.b;
            ((com.corrodinggames.rts.gameFramework.effects.GameHUD) object2).k = true;
        }
        PlayerState n4 = l2.bs;
        for (Object object2_571 : this.ag) {
            ((MinimapTile) object2_571).e = false;
            if (!l2.bL.a(n4, ((MinimapTile) object2_571).a, ((MinimapTile) object2_571).b)) continue;
            ((MinimapTile) object2_571).e = true;
            point = this.b(((MinimapTile) object2_571).a * l2.bL.tilePixelWidth, ((MinimapTile) object2_571).b * l2.bL.tilePixelHeight);
            ((MinimapTile) object2_571).c = point.a;
            ((MinimapTile) object2_571).d = point.b;
        }
    }

    public void a(float f2) {
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU && !com.corrodinggames.rts.gameFramework.GlobalState.aW) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.L = GameUtils.a(this.L, f2);
        if (this.L == 0.0f) {
            this.L = 15.0f;
            this.e();  // 02b L655: this.e() (tileRenderHeight 为幻觉名)
        }
        this.Y += f2;
        if (this.Y > 15.0f) {
            Object object = null;
            for (Object object2 : this.Z) {
                if (((MinimapMarker) object2).markerValue != 0.0f) {  // 02b q.java e=markerValue
                    ((MinimapMarker) object2).markerRadius = 0.0f;
                } else if (((MinimapMarker) object2).markerRadius > 15.0f) {
                    ((MinimapMarker) object2).markerRadius = 0.0f;
                    ((MinimapMarker) object2).markerValue = 300.0f;
                    MinimapConfig p2 = new MinimapConfig(this);
                    p2.minimapWidth = ((MinimapMarker) object2).markerX;  // 02b b
                    p2.minimapHeight = ((MinimapMarker) object2).markerY;  // 02b c
                    if (((MinimapMarker) object2).isVisible) {  // 02b a
                        p2.renderMode = com.corrodinggames.rts.gameFramework.ui.MinimapMode.a;
                    } else {
                        p2.renderMode = com.corrodinggames.rts.gameFramework.ui.MinimapMode.b;
                        p2.worldScaleX = 0.4f;
                        p2.worldScaleY = 0.8f;
                    }
                    this.aa.add(p2);
                }
                ((MinimapMarker) object2).markerRadius = GameUtils.a(((MinimapMarker) object2).markerRadius, 2.0f * this.Y);  // 02b L687: f.a (formatDuration 为幻觉名)
                ((MinimapMarker) object2).markerValue = GameUtils.a(((MinimapMarker) object2).markerValue, this.Y);
                if (((MinimapMarker) object2).markerRadius != 0.0f || ((MinimapMarker) object2).markerValue != 0.0f) continue;
                object = object2;
            }
            if (object != null) {
                this.Z.remove(object);
            }
            for (Object object2 : this.aa) {
                if (((MinimapConfig) object2).renderMode == com.corrodinggames.rts.gameFramework.ui.MinimapMode.d || !l2.cQ.b(((MinimapConfig) object2).minimapWidth, ((MinimapConfig) object2).minimapHeight)) continue;  // 02b L702: p.e/a/b
                ((MinimapConfig) object2).worldScaleX = 0.0f;
                ((MinimapConfig) object2).worldScaleY = 0.0f;
            }
            this.Y = 0.0f;
        }
    }

    public float b(float f2) {
        return f2 * this.i * this.minimapWidth;
    }

    public Point b(float f2, float f3) {
        if (!this.isInteractive) {
            this.ad.a(-1, -1);
            return this.ad;
        }
        int n2 = (int)(f2 * this.i * this.minimapWidth + this.float1);
        int n3 = (int)(f3 * this.j * this.minimapHeight + this.float2);
        this.ad.a(n2, n3);
        return this.ad;
    }

    public Point c(float f2, float f3) {
        if (f2 < this.float1 || f3 < this.float2 || f2 > this.float1 + this.minimapWidth || f3 > this.float2 + this.minimapHeight) {
            return null;
        }
        int n2 = (int)((f2 - this.float1) / this.minimapWidth * (float)this.tileRenderWidth);
        int n3 = (int)((f3 - this.float2) / this.minimapHeight * (float)this.tileRenderHeight);
        this.ad.a(n2, n3);
        return this.ad;
    }

    public float c(float f2) {
        if (f2 < this.float1) {
            return this.float1;
        }
        if (f2 > this.float1 + this.minimapWidth) {
            return this.float1 + this.minimapWidth;
        }
        return f2;
    }

    public float d(float f2) {
        if (f2 < this.float2) {
            return this.float2;
        }
        if (f2 > this.float2 + this.minimapHeight) {
            return this.float2 + this.minimapHeight;
        }
        return f2;
    }

    public void e(float f2) {
        float f3;
        Point point;
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface y2 = l2.bO;  // 02b L751: m.y (GameSaver 为幻觉名)
        this.a();  // 02b L752: a()
        if (this.J != null && !com.corrodinggames.rts.gameFramework.GameUtils.e(this.minimapWidth, this.minimapHeight, 5.0f)) {  // 02b L753: f.e (MusicController 为幻觉名)
            com.corrodinggames.rts.gameFramework.GlobalState.b("minimap", "minimap size has changed, reseting");
            this.c();  // 02b L755: c()
        }
        if (!this.isVisible || this.J == null) {
            this.g();  // 02b L759: g()
        }
        if (this.k != (int)this.float1 || this.l != (int)this.float2 || this.m) {
            this.h();  // 02b L763: h()
        }
        if (l2.bL.tileHeight) {
            if (this.O && !this.P) {
                this.M = GameUtils.a(this.M, 1.0f);
                if (this.M == 0.0f) {
                    this.M = 40.0f;
                    this.O = false;
                    this.Q = 0.0f;
                    this.P = true;
                }
            }
            if (this.P) {
                this.N = GameUtils.a(this.N, 1.0f);
                if (this.N == 0.0f) {
                    this.N = 3.0f;
                    if (this.J != null) {
                        float f4 = this.Q - 0.005f;
                        this.Q = (float)((double)this.Q + 0.04);
                        if (f4 < 0.0f) {
                            f4 = 0.0f;
                        }
                        if (this.Q >= 1.0f) {
                            this.Q = 1.0f;
                            this.P = false;
                        }
                        this.a(f4, this.Q);  // 02b: a(FF) javap 铁证
                    }
                }
            }
        }
        y2.b(this.J, this.float1, this.float2, this.n);
        this.boundsRect.a((int)this.float1, (int)this.float2, (int)(this.float1 + this.minimapWidth), (int)((double)(this.float2 + this.minimapHeight) - 0.4));
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        Object[] objectArray = com.corrodinggames.rts.game.MovementController.a.a();  // 02b f.a 静态 (projectiles 为幻觉包名)
        int n2 = com.corrodinggames.rts.game.MovementController.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            object = (MovementController)objectArray[i2];
            if (!((MovementController)object).D) continue;
            bl3 = true;
            bl2 = true;
        }
        for (MinimapConfig p2 : (java.util.Collection<MinimapConfig>) (java.util.Collection) this.aa) {
            if (p2.renderMode == com.corrodinggames.rts.gameFramework.ui.MinimapMode.b) continue;
            bl = true;
            if (p2.renderMode == com.corrodinggames.rts.gameFramework.ui.MinimapMode.d) continue;
            bl2 = true;
        }
        if (!bl && !bl3) {
            this.o.a(255, 100, 100, 100);
            this.o.a(1.0f);
            if (com.corrodinggames.rts.gameFramework.ui.InGameUI.bO) {
                this.o.a(115, 0, 0, 0);
                this.o.a(2.0f);
            }
        } else {
            this.q += 5.0f * f2;
            if (this.q > 180.0f) {
                this.q -= 180.0f;
            }
            float f5 = GameUtils.sinFast(this.q);
            if (bl3) {
                this.o.a(255, 0, (int)(0.0f + f5 * 230.0f), 0);
            } else if (!bl2) {
                this.o.a(255, 12, (int)(0.0f + f5 * 220.0f), (int)(0.0f + f5 * 220.0f));
            } else {
                this.o.a(255, (int)(0.0f + f5 * 230.0f), 0, 0);
            }
            this.o.a(2.0f);
        }
        y2.b(this.boundsRect, this.o);
        for (MinimapTile t2 : (java.util.Collection<MinimapTile>) (java.util.Collection) this.ag) {
            if (!t2.e) continue;
            this.V.a(t2.c, t2.d, t2.c + 2, t2.d + 2);
            y2.b(this.V, this.D);
        }
        this.a(y2, 0, 0, 0.0f, 1.0f);  // 02b L866: a(m.y,int,int,float,float)
        if (this.af.size() != 0) {
            Iterator iterator = this.af.iterator();
            while (iterator.hasNext()) {
                MinimapUnit u2 = (MinimapUnit) iterator.next();
                if (u2.a.isDead) {
                    iterator.remove();
                    continue;
                }
                object = u2.a;
                point = this.b(((UnitInstance) object).eo, ((UnitInstance) object).ep);  // 02b L877: b(f,f)
                if (((UnitInstance) object).a(point.a, point.b)) continue;
                y2.a((float)point.a, (float)point.b, 4.0f, ((UnitInstance) object).player.ae);
            }
        }
        n2 = com.corrodinggames.rts.game.MovementController.a.a;
        for (int i3 = 0; i3 < n2; ++i3) {
            object = (MovementController)objectArray[i3];
            if (!((MovementController)object).D && (((MovementController)object).q == null || !((MovementController)object).q.D) || ((MovementController)object).j == null) continue;
            point = this.b(((MovementController)object).eo, ((MovementController)object).ep);  // 02b L891
            f3 = 2.0f;
            if (((MovementController)object).D) {
                f3 = 4.0f;
            }
            y2.a((float)point.a, (float)point.b, f3, ((MovementController)object).j.player.ae);
        }
        Object object2 = this.b(l2.cw, l2.cx);  // 02b L901
        this.E.a = ((Point)object2).a;
        this.E.b = ((Point)object2).b;
        object2 = this.b(l2.cw + l2.cA, l2.cx + l2.cB);  // 02b L904
        this.E.c = ((Point)object2).a;
        this.E.d = ((Point)object2).b;
        if (this.E.a < this.boundsRect.a) {
            this.E.a = this.boundsRect.a;
        }
        if (this.E.c > this.boundsRect.c) {
            this.E.c = this.boundsRect.c;
        }
        if (this.E.b < this.boundsRect.b) {
            this.E.b = this.boundsRect.b;
        }
        if (this.E.d > this.boundsRect.d) {
            this.E.d = this.boundsRect.d;
        }
        y2.b(this.E, this.s);
        this.r += 6.0f * f2;
        if (this.r > 180.0f) {
            this.r -= 180.0f;
        }
        java.util.Iterator iterator2 = this.aa.iterator();  // 02b L929
        while (iterator2.hasNext()) {
            float f6;
            Paint paint;
            MinimapConfig p3 = (MinimapConfig) iterator2.next();
            object = this.b((float)p3.minimapWidth, (float)p3.minimapHeight);  // 02b L933
            float f7 = p3.worldScaleX;
            f3 = 0.05f;
            if (p3.renderMode == com.corrodinggames.rts.gameFramework.ui.MinimapMode.b) {
                paint = this.u;
                f3 = 0.03f;
                f6 = GameUtils.sinFast(this.r);
                paint.a((int)(50.0f + f6 * 190.0f), (int)(50.0f + f6 * 190.0f), 0, 0);
            } else if (p3.renderMode == com.corrodinggames.rts.gameFramework.ui.MinimapMode.d) {
                paint = this.v;
            } else {
                paint = this.t;
                f6 = GameUtils.sinFast(this.r);
                paint.a((int)(50.0f + f6 * 190.0f), (int)(50.0f + f6 * 190.0f), 0, 0);
            }
            f7 = GameUtils.b(f7, f3, 1.0f);
            if (p3.renderMode == com.corrodinggames.rts.gameFramework.ui.MinimapMode.b) {
                f6 = this.minimapWidth * f7;
                float f8 = this.minimapHeight * f7;
                y2.a(this.c((float)((Point)object).a - f6), this.d((float)((Point)object).b - f8), this.c((float)((Point)object).a + f6), this.d((float)((Point)object).b + f8), paint);  // 02b L955: c()/d() 钳位
                y2.a(this.c((float)((Point)object).a + f6), this.d((float)((Point)object).b - f8), this.c((float)((Point)object).a - f6), this.d((float)((Point)object).b + f8), paint);
            } else {
                y2.a(this.c((float)((Point)object).a - this.minimapWidth * f7), this.d((float)((Point)object).b), this.c((float)((Point)object).a + this.minimapWidth * f7), this.d((float)((Point)object).b), paint);  // 02b L958
                y2.a(this.c((float)((Point)object).a), this.d((float)((Point)object).b - this.minimapHeight * f7), this.c((float)((Point)object).a), this.d((float)((Point)object).b + this.minimapHeight * f7), paint);
            }
            p3.worldScaleX = GameUtils.a(p3.worldScaleX, 0.04f * f2);
            if (p3.worldScaleX != 0.0f) continue;
            p3.worldScaleY = GameUtils.a(p3.worldScaleY, 0.005f * f2);
            if (p3.worldScaleY != 0.0f) continue;
            iterator2.remove();  // 02b L966: var17.remove()
        }
    }

    public void a(com.corrodinggames.rts.game.units.UnitInstance am2) {  // 02b L973: a(am)
        if (this.af.contains(am2)) {
            return;
        }
        MinimapUnit u2 = new MinimapUnit(this);
        u2.a = am2;
        this.af.add(u2);
    }

}
