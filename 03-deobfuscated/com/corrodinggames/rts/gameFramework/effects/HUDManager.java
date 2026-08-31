/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.effects;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.network.ServerResult;
import com.corrodinggames.rts.gameFramework.FileSystem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.effects.HUDElementRenderer;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.GameObject;

public final class HUDManager {
    public int a = 0;
    public int b = 80;
    public int c = 100;
    public static HUDElement[] f = new HUDElement[0];
    public static int g = 0;
    public static boolean h;
    public int i;
    public int j;
    private boolean[] y = new boolean[5];
    public static Shader k;
    public Texture l;
    public Texture m;
    public static final RectF n = new RectF();
    public final Paint w = new Paint();  // 02b d/c L44  // 02b d/c.java L35
    public static final Rect o = new Rect();  // 02b L36
    public static final Rect tempRect2 = new Rect();  // 02b L37: p
    public static final Paint q = new Paint();  // 02b L38
    public static final Paint r = new Paint();  // 02b L39
    public static HUDElementRenderer[] s;

    public static void a(HUDElement h2, GameObject w2) {  // 02b d/c.java javap: 挂接元素到对象 + 相对偏移 (SubBuildingType1 链)
        if (h2 == null) {
            return;
        }
        h2.b = w2;
        h2.I -= w2.eo;
        h2.J -= w2.ep;
        h2.K -= w2.eq;
    }
    DrawLayer t = null;
    boolean u = false;
    boolean v = false;
    public final Paint activePaint = new Paint();
    float x = 0.0f;

        public HUDElement c(float f2, float f3, float f4) {
        this.a();
        HUDElement e2 = this.b(f2, f3, f4, SoundEffect.a, false, DrawLayer.c);
        if (e2 != null) {
            e2.aq = 13;
            e2.ae = true;
            e2.ak = 3.0f;
            e2.aj = 0.5f;
            e2.ag = 7;
            e2.ap = 0;
            e2.V = 35.0f;
            e2.W = e2.V - 10.0f;
            e2.r = true;
            e2.E = 1.0f;
            e2.G = 0.5f;
            e2.F = 0.5f;
        }
        return e2;
    }

        public HUDElement c(float f2, float f3, float f4, float f5, int n2) {
        HUDElement e2 = this.b(f2, f3, f4, SoundEffect.a, false, DrawLayer.a);
        if (e2 != null) {
            e2.aq = 4;
            e2.g = HUDElement.i;
            e2.ap = GameUtils.a(0, 2);
            e2.Y = f5;
            e2.an = true;
            e2.P = com.corrodinggames.rts.gameFramework.GameUtils.k(f5) * 0.15f;
            e2.Q = com.corrodinggames.rts.gameFramework.GameUtils.j(f5) * 0.15f;
            e2.W = e2.V = 30.0f;
            e2.r = true;
            e2.ar = 1;
            e2.G = 0.8f;
            e2.F = 2.3f;
            if (n2 != 0) {
                e2.B = new LightingColorFilter(n2, 0);
            }
        }
        return e2;
    }

            public HUDElement c(float f2, float f3, float f4, int n2) {
        HUDElement e2 = this.d(f2, f3, f4, n2);
        if (e2 != null) {
            e2.aq = 11;
        }
        return e2;
    }

    public HUDElement d(float f2, float f3, float f4, int n2) {
        this.a();
        HUDElement e2 = this.b(f2, f3, f4, SoundEffect.a, false, DrawLayer.c);
        if (e2 != null) {
            e2.aq = 6;
            e2.W = e2.V = 30.0f;
            e2.r = true;
            e2.G = 0.2f;
            e2.F = 1.3f;
            e2.ar = 1;
            if (n2 != 0) {
                e2.B = new LightingColorFilter(n2, 0);
            }
        }
        return e2;
    }

    public HUDElement d(float f2, float f3, float f4) {
        HUDElement e2 = this.b(f2, f3, f4, 0.3f, 0.7f);
        if (e2 != null) {
            e2.aq = 14;
            e2.ap = GameUtils.a(0, 5);
            e2.w = 0.5f;
        }
        return e2;
    }

    public HUDElement e(float f2, float f3, float f4) {
        HUDElement e2 = this.b(f2, f3, f4, 1.0f, 1.0f);
        if (e2 != null) {
            // empty if block
        }
        return e2;
    }

        public HUDElement f(float f2, float f3, float f4) {
        HUDElement e2 = this.b(f2, f3, f4, SoundEffect.a, false, DrawLayer.b);
        if (e2 != null) {
            e2.aq = 8;
            e2.W = e2.V = 480.0f;
            e2.r = false;
            e2.ar = 1;
            e2.ae = true;
            e2.ak = 0.0f;
            e2.G = 0.5f;
            e2.G = 1.0f;
            int n2 = GameUtils.a(0, 100);
            if (n2 > 80) {
                e2.aj = com.corrodinggames.rts.gameFramework.GameUtils.c(0.1f, 0.15f);
                e2.ag = 15;
            } else if (n2 > 60) {
                e2.aj = com.corrodinggames.rts.gameFramework.GameUtils.c(0.06f, 0.16f);
                e2.ah = true;
                e2.ag = 6;
                e2.r = true;
            } else {
                e2.aj = com.corrodinggames.rts.gameFramework.GameUtils.c(0.06f, 0.16f);
                e2.ah = true;
                e2.ag = 3;
                e2.r = true;
            }
        }
        return e2;
    }

                

    public int d = 110;

    public int e = 120;

    public static final Rect p = new Rect();  // 02b d/c.java L37

    public HUDElement a(DrawLayer h2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        int n2 = 0;
        int n3 = l2.b();
        if (n3 < 13) {
            n2 = -this.j;
        } else if (n3 < 28) {
            n2 = -this.i;
        }
        int n4 = this.a;
        if (h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.a && n4 > this.b + n2) {
            return null;
        }
        if (h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.b && n4 > this.c + n2) {
            return null;
        }
        if (h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.c && n4 > this.d + n2) {
            return null;
        }
        if (h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.d && n4 > this.e + n2) {
            return null;
        }
        HUDElement e2 = null;
        e2 = this.a(true, null);
        if (e2 == null && (h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.e || h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.d)) {
            e2 = this.a(false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c);
        }
        if (e2 != null) {
            if (!e2.o) {
                e2.o = true;
                ++this.a;
            }
            return e2;
        }
        return null;
    }

    private HUDElement a(boolean bl, DrawLayer h2) {
        HUDElement[] eArray = f;
        int n2 = eArray.length;
        if (bl && h2 == null) {
            for (int i = 0; i < n2; ++i) {
                HUDElement e2 = eArray[i];
                if (e2.o) continue;
                if (g == i) {
                    ++g;
                }
                return e2;
            }
            return null;
        }
        for (int i = 0; i < n2; ++i) {
            HUDElement e3 = eArray[i];
            if (bl && e3.o || h2 != null && !e3.q.a(h2)) continue;
            return e3;
        }
        return null;
    }

    public void a(float f2, float f3, float f4) {
        this.a(f2, f3, f4, 0.0f, 20.0f);
    }

    public void a(float f2, float f3, float f4, float f5, float f6) {
        for (int i = 0; i < 7; ++i) {
            HUDElement e2 = this.b(f2 + GameUtils.c(-20.0f, 20.0f), f3 + GameUtils.c(-20.0f, 20.0f), f4);
            if (e2 == null) continue;
            e2.U = f5 + GameUtils.c(0.0f, f6);
            e2.aj = GameUtils.c(0.3f, 0.6f);
        }
    }

    public float a(float f2, float f3) {
        return GameUtils.c(f2, f3);
    }

    public void b(DrawLayer h2) {  // 02b d.c.b(d.h): 设置当前渲染层
        this.t = h2;
    }

    public HUDElement b(float f2, float f3, float f4) {
        this.a();
        HUDElement e2 = this.b(f2, f3, f4, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c);
        if (e2 != null) {
            float f5;
            e2.aq = 1;
            e2.ae = true;
            e2.ak = 0.0f;
            e2.aj = 0.5f;
            e2.ag = 12;
            e2.ap = 0;
            e2.V = 35.0f;
            e2.W = e2.V - 10.0f;
            e2.r = true;
            e2.E = 0.7f;
            e2.Y = this.a(-180.0f, 180.0f);
            e2.G = f5 = this.a(0.8f, 1.0f);
            e2.F = f5;
        }
        return e2;
    }


    public HUDElement a(float f2, float f3, float f4, float f5, float f6, float f7) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!l2.bL.a(f2, f3, l2.bs) && !l2.bL.a(f5, f6, l2.bs)) {
            return null;
        }
        HUDElement e2 = this.b(f2, f3, f4, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c);
        if (e2 != null) {
            e2.an = false;
            e2.W = e2.V = 5.0f;
            e2.r = true;
            e2.E = 1.0f;
            e2.L = true;
            e2.M = f5;
            e2.N = f6;
            e2.O = f7;
        }
        return e2;
    }

    public HUDElement a(float f2, float f3, float f4, float f5) {
        return this.a(f2, f3, f4, f5, 0);
    }

    public HUDElement a(float f2, float f3, float f4, float f5, int n2) {
        return this.a(f2, f3, f4, f5, n2, 0);
    }

    public HUDElement b(float f2, float f3, float f4, float f5, int n2) {
        return this.a(f2, f3, f4, f5, n2, 1);
    }

    public HUDElement a(float f2, float f3, float f4, float f5, int n2, int n3) {
        this.a();
        HUDElement e2 = this.b(f2, f3, f4, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c);
        if (e2 != null) {
            e2.g = com.corrodinggames.rts.gameFramework.effects.HUDElement.j;
            e2.ae = true;
            if (n3 == 1) {
                e2.aq = 3;
                e2.ak = 1.0f;
                e2.aj = 0.4f;
                e2.ag = 4;
            } else {
                e2.aq = 3;
                e2.ak = 0.0f;
                e2.aj = 0.5f;
                e2.ag = 3;
            }
            e2.Y = f5;
            e2.ap = 0;
            e2.W = e2.V = 20.0f;
            e2.r = false;
            if (n2 != 0) {
                e2.B = new LightingColorFilter(n2, 0);
            }
        }
        return e2;
    }


    public HUDElement a(com.corrodinggames.rts.gameFramework.GameObject w2, int n2) {
        return this.a(w2, n2, 0.5f);
    }

    public HUDElement a(com.corrodinggames.rts.gameFramework.GameObject w2, int n2, float f2) {
        this.b();
        HUDElement e2 = this.b(w2.eo, w2.ep, w2.eq, n2);
        if (e2 != null) {
            e2.I = 0.0f;
            e2.J = 0.0f;
            e2.K = 0.0f;
            e2.W = e2.V = 400.0f;
            e2.E = 0.3f;
            e2.G = f2;
            e2.b = w2;
        }
        return e2;
    }

    public HUDElement a(float f2, float f3, float f4, int n2) {
        if (this.t == null && !this.v) {
            this.a();
        }
        return this.b(f2, f3, f4, n2);
    }

    public HUDElement b(float f2, float f3, float f4, int n2) {
        HUDElement e2 = this.b(f2, f3, f4, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, true, com.corrodinggames.rts.gameFramework.effects.DrawLayer.b);
        if (e2 != null) {
            e2.e = false;
            e2.g = com.corrodinggames.rts.gameFramework.effects.HUDElement.h;
            e2.aq = 2;
            e2.W = e2.V = 10.0f;
            e2.r = true;
            e2.E = 0.5f;
            e2.ar = (short)2;
            e2.d = true;
            if (n2 != 0) {
                e2.x = n2;
                e2.B = new LightingColorFilter(n2, 0);
            }
        }
        return e2;
    }

    public HUDElement b(float f2, float f3, float f4, float f5) {
        this.a();
        HUDElement e2 = this.b(f2, f3, f4, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.b);
        if (e2 != null) {
            e2.g = com.corrodinggames.rts.gameFramework.effects.HUDElement.l;
            e2.aq = 0;
            e2.ap = 13;
            e2.ar = 1;
            e2.r = true;
            e2.E = 0.8f;
            e2.V = e2.W = 80.0f;
            e2.Y = GameUtils.c(-180.0f, 180.0f);
            e2.G = GameUtils.c(0.6f, 0.8f);
            e2.F = 1.5f;
            e2.P = com.corrodinggames.rts.gameFramework.GameUtils.k(f5) * 0.13f * GameUtils.c(1.0f, 1.5f) + GameUtils.c(-0.01f, 0.01f);
            e2.Q = com.corrodinggames.rts.gameFramework.GameUtils.j(f5) * 0.13f * GameUtils.c(1.0f, 1.5f) + GameUtils.c(-0.01f, 0.01f);
        }
        return e2;
    }

    public HUDElement a(float f2, float f3, float f4, int n2, float f5, float f6) {
        HUDElement e2 = this.b(f2, f3, f4, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c);
        if (e2 != null) {
            e2.g = com.corrodinggames.rts.gameFramework.effects.HUDElement.l;
            e2.aq = 6;
            e2.W = e2.V = 120.0f;
            e2.r = true;
            e2.G = 0.2f;
            e2.F = 0.9f;
            e2.ar = 1;
            e2.E = 0.5f;
            e2.P = f5;
            e2.Q = f6;
            if (n2 != 0) {
                n2 = Color.a(255, 0, 0, 200);
            }
            if (n2 != 0) {
                e2.B = new LightingColorFilter(n2, 0);
            }
        }
        return e2;
    }

    public void a(float f2, float f3, float f4, int n2, float f5, float f6, float f7) {
        this.a(f2, f3, 0.0f, 0, 0.0f, 0.0f);
        for (int i2 = -180; i2 < 180; i2 += 45) {
            float f8;
            float f9 = f7 + (float)i2;
            float f10 = f2 + com.corrodinggames.rts.gameFramework.GameUtils.k(f9) * -5.0f;
            HUDElement e2 = this.b(f10, f8 = f3 + com.corrodinggames.rts.gameFramework.GameUtils.j(f9) * -5.0f, 0.0f, f9);
            if (e2 == null) continue;
            e2.ar = (short)2;
            e2.s = true;
            e2.t = 7.0f;
        }
    }





    public HUDElement b(float f2, float f3, float f4, float f5, float f6) {
        this.b();
        HUDElement e2 = this.b(f2, f3, f4, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c);
        if (e2 != null) {
            float f7;
            e2.g = com.corrodinggames.rts.gameFramework.effects.HUDElement.m;
            e2.aq = 12;
            e2.ap = com.corrodinggames.rts.gameFramework.GameUtils.a(0, 7);  // 02b: f.a(0,7) (ui.ActionPanel 为幻觉)
            e2.V = GameUtils.c(400.0f, 800.0f);
            e2.W = e2.V - 150.0f;
            e2.r = true;
            e2.G = f7 = GameUtils.c(0.6f, 1.0f);
            e2.F = f7;
            e2.ar = (short)2;
            e2.v = true;
            e2.as = true;
            float f8 = GameUtils.c(-180.0f, 180.0f);
            float f9 = GameUtils.c(0.4f, 1.2f) * f5;
            e2.P = com.corrodinggames.rts.gameFramework.GameUtils.k(f8) * f9;
            e2.Q = com.corrodinggames.rts.gameFramework.GameUtils.j(f8) * f9;
            e2.R = GameUtils.c(0.6f, 2.7f) * f6;
            e2.Y = GameUtils.c(-180.0f, 180.0f);
            e2.K += 1.0f;
        }
        return e2;
    }


    public void a() {
        this.u = true;
    }

    public HUDElement a(float f2, float f3, float f4, SoundEffect d2, boolean bl, DrawLayer h2) {  // v19.115q: 02b d.d=SoundEffect
        HUDElement e2 = this.b(f2, f3, f4, d2, bl, h2);
        if (e2 != null) {
            e2.p = true;
        }
        return e2;
    }

    public HUDElement b(float f2, float f3, float f4, SoundEffect d2, boolean bl, DrawLayer h2) {  // v19.115q: 02b d.d=SoundEffect
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.t != null) {
            h2 = this.t;
            this.t = null;
        }
        boolean bl2 = this.v;
        this.v = false;
        if (this.u) {
            this.u = false;
            if (!l2.cP.b(f2, f3)) {
                return null;
            }
        }
        if (!bl && l2.bL != null && !l2.bL.a(f2, f3, l2.bs)) {
            return null;
        }
        if (l2.cO.b(f2, f3)) {
            if (h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.a) {
                h2 = com.corrodinggames.rts.gameFramework.effects.DrawLayer.b;
            } else if (h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.b) {
                h2 = com.corrodinggames.rts.gameFramework.effects.DrawLayer.c;
            } else if (h2 == com.corrodinggames.rts.gameFramework.effects.DrawLayer.c) {
                h2 = com.corrodinggames.rts.gameFramework.effects.DrawLayer.d;
            }
        } else if (bl2 || !l2.cP.b(f2, f3)) {
            // empty if block
        }
        HUDElement e2 = this.a(h2);
        if (e2 == null) {
            return null;
        }
        e2.c();
        e2.q = h2;
        e2.aq = 0;
        e2.an = true;
        e2.I = f2;
        e2.J = f3;
        e2.K = f4;
        e2.E = 1.0f;
        if (d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.d || d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.e || d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.f) {
            e2.ap = 7;
            e2.V = 12.0f;
            e2.r = true;
            e2.Q = -0.3f;
            e2.E = 0.7f;
            if (d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.f) {
                e2.ap = 3;
                e2.Q = -0.7f;
                e2.V = 24.0f;
                e2.E = 0.7f;
            }
            if (d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.e) {
                e2.ap = 4;
                e2.V = 15.0f;
                e2.E = 0.4f;
            }
        }
        if (d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.c) {
            e2.ap = 1;
            e2.V = 25.0f;
            e2.r = true;
        }
        if (d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.g) {
            e2.ap = 5;
            e2.V = 42.0f;
            e2.r = true;
            e2.Q = 0.1f;
            e2.E = 2.0f;
        }
        if (d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.h) {
            e2.ap = 6;
            e2.V = 39.0f;
            e2.r = true;
            e2.Q = 0.1f;
            e2.E = 2.0f;
        }
        if (d2 == com.corrodinggames.rts.gameFramework.effects.SoundEffect.i) {
            e2.ap = 14;
            e2.V = 39.0f;
            e2.r = true;
            e2.Q = 0.1f;
            e2.E = 0.7f;
        }
        e2.W = e2.V;
        return e2;
    }

    public int a(String string) {
        for (int i2 = 0; i2 < s.length; ++i2) {
            if (s[i2] == null) continue;
            if (s[i2].a != null && s[i2].a.equalsIgnoreCase(string)) {  // 02b L859: s[var2].a (d.c 全限定为幻觉)
                return i2;
            }
            if (!("" + i2).equals(string)) continue;
            return i2;
        }
        return -1;
    }

    public int a(float f2, int n2) {
        if (!this.y[n2]) {
            return 0;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        int n3 = 0;
        HUDElement[] eArray = f;
        for (int i2 = 0; i2 < g; ++i2) {
            boolean bl;
            HUDElement e2 = eArray[i2];
            if (!e2.o || e2.ar != n2 || !(bl = e2.a(l2, false))) continue;
            ++n3;
        }
        return n3;
    }

    public void a(boolean bl) {
        if (bl) {
            return;
        }
        for (int i2 = 0; i2 < f.length; ++i2) {
            HUDElement e2 = f[i2];
            if (!e2.o) continue;
            e2.o = false;
            --this.a;
        }
        if (this.a != 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.a("EffectEngine::removeAll: effectListActiveSize == " + this.a);
        }
        g = 0;
    }

    public void b() {
        // v19.115q ay战役补缺: javap d.c.class b() 铁证 (bR.b() 取消离屏限制) — 简化 TODO
    }

    public void a(Context context) {  // 02b d/c.java L621 (HUDManager 特效引擎)
    }


    public void a(float f2) {  // 02b d.c.a(float) L872-910: HUD 元素帧更新 + 自动相机跟随
        GlobalState l2 = GlobalState.B();
        HUDElement[] hudElementArray = f;
        for (int i2 = 0; i2 < g; ++i2) {
            HUDElement hudElement = hudElementArray[i2];
            if (hudElement.o && !hudElement.p) {
                hudElement.b(f2);
            }
        }
        if (h) {
            while (g > 0) {
                HUDElement hudElement2 = hudElementArray[g - 1];
                if (hudElement2.o) break;
                --g;
            }
        }
        this.x += f2;
        if (this.x > 10.0f) {
            this.x = 0.0f;
            int n2 = l2.cu + com.corrodinggames.rts.gameFramework.GameUtils.a(0, (int)l2.cA);
            int n3 = l2.cv + com.corrodinggames.rts.gameFramework.GameUtils.a(0, (int)l2.cB);
            l2.bL.a((float)n2, (float)n3);
            int n4 = l2.bL.T;
            int n5 = l2.bL.U;
            com.corrodinggames.rts.game.map.MapLayer mapLayer = l2.bL.d(n4, n5);
            if (mapLayer != null && mapLayer.g && !mapLayer.h) {
                l2.bL.a(n4, n5);
                this.f((float)(l2.bL.T + 10), (float)(l2.bL.U - 10 + 10), 0.0f);
            }
        }
    }


    public int b(float f2) {  // 02b d.c.b(float) L912-941: 返回活跃元素数
        GlobalState l2 = GlobalState.B();
        int n2 = 0;
        for (int i2 = 0; i2 < this.y.length; ++i2) {
            this.y[i2] = false;
        }
        for (int i3 = 0; i3 < g; ++i3) {
            HUDElement hudElement = f[i3];
            if (hudElement.o) {
                if (!this.y[hudElement.ar]) {
                    this.y[hudElement.ar] = true;
                }
                if (hudElement.p) {
                    hudElement.b(f2);
                }
                if (hudElement.as) {
                    if (hudElement.a(l2, true)) {
                        ++n2;
                    }
                }
            }
        }
        return n2;
    }

}