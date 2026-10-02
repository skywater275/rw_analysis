/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.b.d;
import com.corrodinggames.rts.game.units.custom.b.e;
import com.corrodinggames.rts.game.units.custom.b.f;
import com.corrodinggames.rts.game.units.custom.b.g;
import com.corrodinggames.rts.game.units.custom.b.i;
import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.bn;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public strictfp class c
extends a {
    static Paint a = new Paint();
    static ag b = new ag();
    m c = new m();
    m d = new m();
    m e = new m();
    m f = new m();
    m g = new m();
    boolean h;
    static final PointF i = new PointF();
    static final g j = new g("");
    static final Rect k = new Rect();
    static final RectF l = new RectF();

    private static d c(l l2, String string) {
        for (d d2 : l2.q) {
            if (!string.equalsIgnoreCase(d2.a)) continue;
            return d2;
        }
        return null;
    }

    public static g a(l l2, String string) {
        if (string == null || string.equals("")) {
            return null;
        }
        if (string.equalsIgnoreCase("NONE")) {
            return null;
        }
        g g2 = new g(string);
        l2.a(g2);
        return g2;
    }

    /*
     * WARNING - void declaration
     */
    public static void a(l l2, ab ab2) {
        c c2 = null;
        String string = "decal_";
        m m2 = ab2.e(string);
        for (String string2 : m2) {
            String string3;
            Object object;
            boolean bl2;
            Object object3;
            Serializable serializable;
            Object object4;
            Integer n2;
            LogicBoolean logicBoolean;
            l2.a("1.15p9", 115009, string2, "decals");
            String string4 = string2.substring(string.length());
            d d2 = new d();
            d2.a = string4;
            if (string4.contains(",")) {
                throw new bo("[" + string2 + "]Decal name: '" + string4 + "' cannot have ','");
            }
            if (string4.contains(" ")) {
                throw new bo("[" + string2 + "]Decal name: '" + string4 + "' cannot have spaces");
            }
            if (string4.contains("(")) {
                throw new bo("[" + string2 + "]Decal name: '" + string4 + "' cannot have '('");
            }
            d2.G = (f)ab2.a(string2, "layer", com.corrodinggames.rts.game.units.custom.b.f.d, f.class);
            d2.H = ab2.a(string2, "order", Float.valueOf(0.0f)).floatValue();
            d2.c = ab2.a(string2, "onlyWhenSelectedByOwnPlayer", (Boolean)false);
            d2.d = ab2.a(string2, "onlyWhenSelectedByEnemyPlayer", (Boolean)false);
            d2.e = ab2.a(string2, "onlyWhenSelectedByAllyNotOwnPlayer", (Boolean)false);
            d2.f = ab2.a(string2, "onlyWhenSelectedByAnyPlayer", (Boolean)false);
            int n3 = 0;
            if (d2.c) {
                ++n3;
            }
            if (d2.d) {
                ++n3;
            }
            if (d2.e) {
                ++n3;
            }
            if (d2.f) {
                ++n3;
            }
            if (n3 >= 2) {
                throw new bo("[" + string2 + "]Doesn't make sense to set more than one onlyWhenSelectedBy* to true at a time.");
            }
            d2.b = n3 > 0;
            d2.g = ab2.a(string2, "includeParentsSelection", (Boolean)false);
            d2.h = (q)ab2.a(string2, "onlyTeam", q.f, q.class);
            d2.i = ab2.a(string2, "onlyPlayersWithUnitControl", (Boolean)false);
            d2.j = ab2.a(string2, "onlyWithZoomLevelOrMore", Float.valueOf(0.0f)).floatValue();
            boolean bl3 = false;
            if (d2.G == com.corrodinggames.rts.game.units.custom.b.f.e) {
                bl3 = true;
            }
            d2.k = ab2.a(string2, "onlyWhileActive", (Boolean)false);
            d2.l = ab2.a(string2, "onlyWhileAlive", (Boolean)bl3);
            d2.m = ab2.a(string2, "onlyInPreview", (Boolean)false);
            d2.n = ab2.a(string2, "onlyOnNonPreview", (Boolean)false);
            if (d2.m && d2.n) {
                throw new bo("[" + string2 + "]decal with both onlyInPreview and onlyOnNonPreview will never be visible");
            }
            if (d2.j > 5.0f) {
                throw new bo("[" + string2 + "]decal with onlyWithZoomLevelOrMore:" + d2.j + " will never be visible");
            }
            if (d2.j < 0.0f) {
                throw new bo("[" + string2 + "]onlyWithZoomLevelOrMore:" + d2.j + " cannot be less than zero");
            }
            Integer n4 = ab2.b(string2, "onlyOnBodyFrameOf", (Integer)null);
            if (n4 != null) {
                d2.o = n4;
                if (d2.o < 0) {
                    throw new bo("[" + string2 + "]onlyOnBodyFrameOf cannot be: " + d2.o);
                }
            }
            if ((logicBoolean = ab2.c(l2, string2, "imageScale", null)) != null) {
                if (LogicBoolean.isStaticNumber(logicBoolean)) {
                    d2.p = LogicBoolean.getKnownStaticNumber(logicBoolean);
                } else {
                    d2.q = true;
                    d2.r = logicBoolean;
                }
            }
            LogicBoolean logicBoolean2 = ab2.c(l2, string2, "imageScaleX", null);
            LogicBoolean logicBoolean3 = ab2.c(l2, string2, "imageScaleY", null);
            if (logicBoolean2 != null || logicBoolean3 != null) {
                d2.q = true;
                d2.s = logicBoolean2;
                d2.t = logicBoolean3;
            }
            if ((n2 = ab2.b(string2, "total_frames", (Integer)null)) != null) {
                d2.J = n2;
                if (d2.J < 1) {
                    throw new bo("[" + string2 + "] TOTAL_FRAMES cannot be: " + d2.J + " (must be 1 or more)");
                }
            }
            d2.M = ab2.a(string2, "frame_verticalOrdering", (Boolean)false);
            d2.K = ab2.b(string2, "frame_width", -1);
            d2.L = ab2.b(string2, "frame_height", -1);
            if (d2.K != -1 && d2.J != -1) {
                throw new bo("[" + string2 + "]TOTAL_FRAMES and frame_width cannot be both set");
            }
            if (d2.L != -1 && d2.L <= 0) {
                throw new bo("[" + string2 + "]frame_height cannot be: " + d2.L);
            }
            if (d2.K != -1 && d2.K <= 0) {
                throw new bo("[" + string2 + "]frame_width cannot be: " + d2.K);
            }
            if (d2.J != -1 && d2.J <= 0) {
                throw new bo("[" + string2 + "]TOTAL_FRAMES cannot be: " + d2.J);
            }
            if (d2.L != -1 || d2.K != -1 || d2.J != -1) {
                d2.I = true;
            }
            boolean bl4 = ab2.a(string2, "teamColors", (Boolean)false);
            com.corrodinggames.rts.gameFramework.m.e e2 = l2.a(ab2, string2, "image");
            if (e2 != null) {
                object4 = new e();
                ((e)object4).a = e2;
                if (((e)object4).a != null && bl4) {
                    ((e)object4).b = l2.a(e2, l2.ac);
                }
                ((e)object4).a(d2);
                d2.v = object4;
                d2.u = true;
            }
            if ((object4 = ab2.b(string2, "imageStack", (String)null)) != null && !((String)object4).equals("")) {
                d2.u = true;
                serializable = new ArrayList();
                for (String string5 : object3 = ((String)object4).split(",")) {
                    Object object2;
                    void var24_33;
                    String[] stringArray;
                    String string6 = string5.trim();
                    bl2 = bl4;
                    object = null;
                    if (string6.contains("(") && string6.contains(")")) {
                        stringArray = al.b(string6, "(");
                        if (stringArray == null) {
                            throw new bo("[" + string2 + "]imageStack: Unexpected format for: " + (String)object4);
                        }
                        String string7 = stringArray[0];
                        object = stringArray[1].trim();
                    }
                    stringArray = var24_33.split("\\*");
                    String string8 = stringArray[0];
                    int n5 = 1;
                    if (stringArray.length >= 2) {
                        n5 = Integer.parseInt(stringArray[1]);
                    }
                    if (object != null) {
                        if (!((String)object).endsWith(")")) {
                            throw new bo("[" + string2 + "]imageStack: Missing ')' in: " + (String)object4);
                        }
                        object = ((String)object).substring(0, ((String)object).length() - 1);
                        object2 = al.a((String)object, ",", false, false);
                        Iterator iterator = ((ArrayList)object2).iterator();
                        while (iterator.hasNext()) {
                            String string9 = (String)iterator.next();
                            if (string9.trim().equals("")) continue;
                            String[] stringArray2 = al.b(string9, "=");
                            if (stringArray2 == null) {
                                throw new RuntimeException("[" + string2 + "]imageStack: Unexpected key format for: " + (String)object4);
                            }
                            String string10 = stringArray2[0].trim();
                            String string11 = stringArray2[1].trim();
                            if (string10.equalsIgnoreCase("teamColors")) {
                                bl2 = ab.g(string2, "imageStack", string11);
                                continue;
                            }
                            if (string10.equalsIgnoreCase("teamColor")) {
                                bl2 = ab.g(string2, "imageStack", string11);
                                continue;
                            }
                            throw new RuntimeException("[" + string2 + "]imageStack: Unknown parameter: " + string10);
                        }
                    }
                    object2 = new e();
                    ((e)object2).a = l2.a(l2.F, string8, l2.ab, string2, "imageStack");
                    if (((e)object2).a == null) {
                        throw new bo("[" + string2 + "]failed to load image " + string8);
                    }
                    if (bl2) {
                        ((e)object2).b = l2.a(((e)object2).a, l2.ac);
                    }
                    ((e)object2).a(d2);
                    for (int i = 0; i < n5; ++i) {
                        ((ArrayList)serializable).add(object2);
                    }
                }
                if (((ArrayList)serializable).size() > 0) {
                    d2.w = ((ArrayList)serializable).toArray(new e[0]);
                }
            }
            d2.x = ab2.a(string2, "stack_hOffset", Float.valueOf(1.0f)).floatValue();
            d2.y = ab2.b(string2, "stack_frameOffset", 0);
            d2.A = ab2.c(l2, string2, "stack_indexStart", null);
            d2.B = ab2.c(l2, string2, "stack_indexCount", null);
            serializable = ab2.a(string2, "stack_drawInReverseOrder", (Boolean)null);
            if (serializable != null && ((Boolean)serializable).booleanValue() || serializable == null && d2.x < 0.0f) {
                d2.z = true;
            }
            d2.N = ab2.a(l2, string2, "frame", null, LogicBoolean$ReturnType.number);
            d2.O = ab2.b(string2, "addBodyFrameMultipliedBy", 0);
            d2.F = ab2.a(l2, string2, "isVisible", (LogicBoolean)null);
            d2.R = ab2.a(string2, "xOffsetRelative", Float.valueOf(0.0f)).floatValue();
            d2.S = ab2.a(string2, "yOffsetRelative", Float.valueOf(0.0f)).floatValue();
            d2.W = ab2.c(l2, string2, "xOffsetAbsolute", null);
            d2.X = ab2.c(l2, string2, "yOffsetAbsolute", null);
            if (LogicBoolean.isStaticNumber(d2.W)) {
                d2.T = LogicBoolean.getKnownStaticNumber(d2.W);
                d2.W = null;
            }
            if (LogicBoolean.isStaticNumber(d2.X)) {
                d2.U = LogicBoolean.getKnownStaticNumber(d2.X);
                d2.X = null;
            }
            d2.V = ab2.a(string2, "hOffset", Float.valueOf(0.0f)).floatValue();
            d2.aa = ab2.a(string2, "dirOffset", Float.valueOf(0.0f)).floatValue();
            d2.ab = ab2.a(string2, "pivotOffset", Float.valueOf(0.0f)).floatValue();
            d2.Y = ab2.a(string2, "alwaysStartDirAtZero", "alwayStartDirAtZero", (Boolean)false);
            d2.Z = ab2.a(string2, "alwaysStartHeightAtZero", (Boolean)false);
            if (d2.R != 0.0f) {
                // empty if block
            }
            d2.ac = ab2.b(l2, string2, "basePosition", null);
            d2.ad = ab2.b(l2, string2, "drawLineTo", null);
            object3 = ab2.b(string2, "basePositionFromLegEnd", (String)null);
            String[] stringArray = null;
            if (object3 != null || stringArray != null) {
                String[] stringArray3;
                if (object3 != null && stringArray != null) {
                    throw new bo("[" + string2 + "]basePositionFromLegEnd and basePositionFromLegMiddle cannot be used at the same time");
                }
                if (stringArray != null) {
                    stringArray3 = stringArray;
                    d2.af = true;
                } else {
                    stringArray3 = object3;
                }
                d2.ae = l2.b((String)stringArray3);
                if (d2.ae == -1) {
                    throw new bo("[" + string2 + "]basePositionFromLeg* failed to find: " + (String)stringArray3);
                }
            }
            if ((string3 = ab2.b(string2, "basePositionFromTurret", (String)null)) != null) {
                bn bn2 = l2.e(string3);
                if (bn2 == null) {
                    throw new bo("[" + string2 + "]basePositionFromTurret failed to find: " + string3);
                }
                d2.ag = bn2.e;
            }
            if (d2.ae != -1 && d2.ag != -1) {
                throw new bo("[" + string2 + "]basePositionFromTurret and basePositionFromLeg cannot be used at the same time");
            }
            if ((d2.ae != -1 || d2.ag != -1) && d2.ac != null) {
                throw new bo("[" + string2 + "]basePositionFromTurret/basePositionFromLeg cannot be used at the same time as basePosition");
            }
            d2.C = l2.a(ab2, string2, "image_shadow");
            d2.D = ab2.a(string2, "shadowOffsetX", Float.valueOf(1.0f)).floatValue();
            d2.E = ab2.a(string2, "shadowOffsetY", Float.valueOf(1.0f)).floatValue();
            d2.P = ab2.a(string2, "color", (Integer)-1);
            d2.Q = ab2.a(string2, "lineWidth", Float.valueOf(1.0f)).floatValue();
            float f2 = 1.0f;
            LogicBoolean object22 = ab2.c(l2, string2, "alpha", null);
            if (object22 != null) {
                if (LogicBoolean.isStaticNumber(object22)) {
                    float f3 = LogicBoolean.getKnownStaticNumber(object22);
                    if (f3 < 0.0f || f3 > 1.0f) {
                        throw new bo("[" + string2 + "]alpha should be between 0-1");
                    }
                    f2 = f3;
                } else {
                    d2.ai = object22;
                }
            }
            if (d2.P != -1 || d2.Q != 1.0f || f2 != 1.0f) {
                int n6;
                d2.ah = new ag();
                d2.ah.b(d2.P);
                if (d2.P != -1) {
                    aa.a(d2.ah);
                }
                if ((n6 = (int)((float)d2.ah.f() * f2)) < 0) {
                    n6 = 0;
                }
                if (n6 > 255) {
                    n6 = 255;
                }
                d2.ah.c(n6);
                d2.ah.a(d2.Q);
            }
            bl2 = true;
            if (LogicBoolean.isStaticFalse(d2.F)) {
                bl2 = false;
            }
            if (d2.ad == null && !d2.u) {
                bl2 = false;
            }
            if (f2 == 0.0f) {
                bl2 = false;
            }
            l2.q.add(d2);
            if (!bl2) continue;
            if (c2 == null) {
                c2 = new c();
                l2.b(c2);
            }
            if (!d2.b) {
                c2.h = true;
            }
            if (d2.C != null && d2.G != com.corrodinggames.rts.game.units.custom.b.f.a) {
                c2.c.add(d2);
            }
            if ((object = d2.G == com.corrodinggames.rts.game.units.custom.b.f.c ? c2.f : (d2.G == com.corrodinggames.rts.game.units.custom.b.f.b ? c2.e : (d2.G == com.corrodinggames.rts.game.units.custom.b.f.e ? c2.g : (d2.G == com.corrodinggames.rts.game.units.custom.b.f.a ? c2.c : (d2.G == com.corrodinggames.rts.game.units.custom.b.f.f ? null : c2.d))))) == null) continue;
            ((m)object).add(d2);
            Collections.sort(object);
        }
    }

    @Override
    public void b(j j2, float f2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
    }

    @Override
    public void a(j j2) {
    }

    @Override
    public void d(j j2, float f2) {
        this.a(j2, f2, com.corrodinggames.rts.game.units.custom.b.f.a, this.c);
        this.a(j2, f2, com.corrodinggames.rts.game.units.custom.b.f.b, this.e);
    }

    @Override
    public void e(j j2, float f2) {
        this.a(j2, f2, com.corrodinggames.rts.game.units.custom.b.f.c, this.f);
    }

    @Override
    public void c(j j2, float f2) {
        this.a(j2, f2, com.corrodinggames.rts.game.units.custom.b.f.d, this.d);
    }

    @Override
    public void f(j j2, float f2) {
        this.a(j2, f2, com.corrodinggames.rts.game.units.custom.b.f.e, this.g);
    }

    public static Rect a(d d2, e e2, com.corrodinggames.rts.gameFramework.m.e e3, int n2) {
        int n3 = e2.c;
        int n4 = e2.d;
        int n5 = 0;
        int n6 = 0;
        if (n2 > 0) {
            int n7;
            if (!d2.M) {
                n7 = 0;
                int n8 = n2;
                int n9 = e2.f;
                if (n2 >= n9) {
                    n7 += n2 / n9;
                    n8 = n2 % n9;
                }
                n5 = n8 * n3;
                n6 = n7 * n4;
            } else {
                n7 = n2;
                int n10 = 0;
                int n11 = e2.e;
                if (n2 >= n11) {
                    n10 += n2 / n11;
                    n7 = n2 % n11;
                }
                n5 = n10 * n3;
                n6 = n7 * n4;
            }
        }
        Rect rect = k;
        rect.a = n5;
        rect.b = n6;
        rect.c = n5 + n3;
        rect.d = n6 + n4;
        return rect;
    }

    public static RectF a(d d2, e e2, com.corrodinggames.rts.gameFramework.m.e e3, float f2, float f3) {
        int n2 = e2.c;
        int n3 = e2.d;
        RectF rectF = l;
        rectF.a = f2 -= (float)(n2 / 2);
        rectF.c = f2 + (float)n2;
        rectF.b = f3 -= (float)(n3 / 2);
        rectF.d = f3 + (float)n3;
        return rectF;
    }

    public final void a(j j2, float f2, f f3, m m2) {
        com.corrodinggames.rts.game.units.custom.b.c.a(j2, f2, f3, m2, null);
    }

    public static final void a(j j2, float f2, f f3, m m2, PointF pointF) {
        Object object;
        int n2 = m2.a;
        if (n2 == 0) {
            return;
        }
        boolean bl2 = j2.cG;
        boolean bl3 = false;
        am am2 = j2.dr();
        if (am2 != null) {
            if (am2.cG) {
                bl3 = true;
            }
            if ((object = am2.dr()) != null && ((am)object).cG) {
                bl3 = true;
            }
        }
        object = com.corrodinggames.rts.gameFramework.l.B();
        float f4 = ((com.corrodinggames.rts.gameFramework.l)object).cX;
        boolean bl4 = f3 == com.corrodinggames.rts.game.units.custom.b.f.a;
        Object[] objectArray = m2.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            float f10;
            float f11;
            float f12;
            float f13;
            float f14;
            float f15;
            Object object2;
            Object object3;
            d d2 = (d)objectArray[i2];
            if (d2.b && !bl2 && !bl3 || d2.j > f4 || d2.k && !j2.bT() || d2.l && j2.bV) continue;
            if (d2.b) {
                boolean bl5 = bl2;
                if (d2.g && bl3) {
                    bl5 = true;
                }
                if (!bl5) continue;
                n n3 = ((com.corrodinggames.rts.gameFramework.l)object).bs;
                n n4 = j2.bX;
                if (n3 != null && (d2.c && n4 != n3 || d2.d && !n4.c(((com.corrodinggames.rts.gameFramework.l)object).bs) || d2.e && (!n4.d(((com.corrodinggames.rts.gameFramework.l)object).bs) || n4 == n3))) continue;
            }
            if (d2.F != null && !d2.F.read(j2) || d2.o >= 0 && j2.a != d2.o || d2.i && !((com.corrodinggames.rts.gameFramework.l)object).bS.m(j2) || d2.h != q.f && d2.h != null && ((com.corrodinggames.rts.gameFramework.l)object).bs != null && !((com.corrodinggames.rts.gameFramework.l)object).bs.a(d2.h, j2.bX) || d2.m && !j2.cp || d2.n && j2.cp) continue;
            if (d2.ae != -1) {
                object3 = j2.dT;
                object2 = j2.x.ax;
                if (object3 == null || ((i[])object3).length <= d2.ae || object2 == null || ((ba[])object2).length <= d2.ae) continue;
                i i3 = object3[d2.ae];
                ba ba2 = object2[d2.ae];
                f15 = j2.eo + i3.b;
                f14 = j2.ep + i3.c;
                f13 = j2.eq + i3.d;
                f12 = i3.i + i3.r + ba2.R + 90.0f;
                f11 = i3.i + i3.r + ba2.R;
                if (d2.af) {
                    float f16 = j2.cg;
                    if (j2.x.dE) {
                        f16 = j2.cL[j2.x.dG].a;
                    }
                    f10 = com.corrodinggames.rts.gameFramework.f.k(f16);
                    f9 = com.corrodinggames.rts.gameFramework.f.j(f16);
                    f8 = f10 * ba2.k - f9 * ba2.j;
                    f7 = f9 * ba2.k + f10 * ba2.j;
                    f6 = com.corrodinggames.rts.gameFramework.f.d(i3.b, i3.c, f8, f7);
                    f5 = com.corrodinggames.rts.gameFramework.f.a(i3.b, i3.c, f8, f7);
                    f12 = f6 + 90.0f;
                    f11 = f6 + 90.0f;
                }
            } else if (d2.ag != -1) {
                if (d2.ag >= j2.cL.length) continue;
                int n5 = d2.ag;
                object2 = j2.F(n5);
                f15 = object2.a;
                f14 = object2.b;
                f13 = j2.eq + object2.c;
                f12 = j2.cL[n5].a + 90.0f;
                f11 = j2.cL[n5].a;
            } else {
                if (d2.ac == null) {
                    object3 = j2;
                } else {
                    object3 = d2.ac.readUnit(j2);
                    if (object3 == null) continue;
                }
                f15 = object3.eo;
                f14 = object3.ep;
                f13 = object3.eq;
                if (pointF != null && d2.ac == null) {
                    f15 = pointF.a;
                    f14 = pointF.b;
                    f12 = 0.0f;
                }
                f12 = object3.cg + 90.0f;
                f11 = object3.cg;
                if (d2.ac == null && j2.x.dC) {
                    float f17 = j2.cL[j2.x.dG].a;
                    f12 = f17 + 90.0f;
                    f11 = f17;
                }
            }
            if (d2.Y) {
                f12 = 0.0f;
            }
            if (d2.Z) {
                f13 = 0.0f;
            }
            f11 += d2.ab;
            f12 += d2.aa;
            f15 += d2.T;
            f14 += d2.U;
            if (d2.W != null) {
                f15 += d2.W.readNumber(j2);
            }
            if (d2.X != null) {
                f14 += d2.X.readNumber(j2);
            }
            if (d2.R != 0.0f || d2.S != 0.0f) {
                float f18 = com.corrodinggames.rts.gameFramework.f.k(f11);
                float f19 = com.corrodinggames.rts.gameFramework.f.j(f11);
                float f20 = d2.R;
                float f21 = d2.S;
                f15 += f18 * f21 - f19 * f20;
                f14 += f19 * f21 + f18 * f20;
            }
            f13 += d2.V;
            if (bl4 && d2.C != null) {
                y y2 = ((com.corrodinggames.rts.gameFramework.l)object).bO;
                float f22 = f15 - ((com.corrodinggames.rts.gameFramework.l)object).cw + d2.D;
                float f23 = f14 - ((com.corrodinggames.rts.gameFramework.l)object).cx + d2.E;
                float f24 = f12;
                Paint paint = j2.R();
                com.corrodinggames.rts.gameFramework.m.e e2 = d2.C;
                y2.k();
                y2.a(f24, f22, f23);
                y2.a(e2, f22, f23, paint);
                y2.l();
                return;
            }
            if (d2.u) {
                y y3 = ((com.corrodinggames.rts.gameFramework.l)object).bO;
                float f25 = f15 - ((com.corrodinggames.rts.gameFramework.l)object).cw;
                float f26 = f14 - ((com.corrodinggames.rts.gameFramework.l)object).cx - f13;
                float f27 = f12;
                Paint paint = d2.ah;
                if (paint == null) {
                    paint = j2.aN();
                }
                if (d2.ai != null && (f10 = d2.ai.readNumber(j2)) != 1.0f) {
                    Paint paint2 = a;
                    paint2.b(paint.e());
                    paint2.a(paint.c());
                    int n6 = (int)((float)paint.f() * f10);
                    if (n6 < 0) {
                        n6 = 0;
                    }
                    if (n6 > 255) {
                        n6 = 255;
                    }
                    paint2.c(n6);
                    paint = paint2;
                }
                int n7 = d2.N != null ? (int)d2.N.readNumber(j2) : 0;
                n7 += j2.a * d2.O;
                if (d2.v != null) {
                    e e3 = d2.v;
                    com.corrodinggames.rts.gameFramework.m.e e4 = e3.b != null ? e3.b[j2.bX.R()] : e3.a;
                    y3.k();
                    y3.a(f27, f25, f26);
                    f7 = d2.p;
                    f6 = d2.p;
                    if (d2.q) {
                        if (d2.r != null) {
                            f7 = f5 = d2.r.readNumber(j2);
                            f6 = f5;
                        }
                        if (d2.s != null) {
                            f7 *= d2.s.readNumber(j2);
                        }
                        if (d2.t != null) {
                            f6 *= d2.t.readNumber(j2);
                        }
                    }
                    if (f7 != 1.0f || f6 != 1.0f) {
                        y3.a(f7, f6, f25, f26);
                    }
                    if (!d2.I) {
                        y3.a(e4, f25, f26, paint);
                    } else {
                        Rect rect = com.corrodinggames.rts.game.units.custom.b.c.a(d2, e3, e4, n7);
                        RectF rectF = com.corrodinggames.rts.game.units.custom.b.c.a(d2, e3, e4, f25, f26);
                        y3.a(e4, rect, rectF, paint);
                    }
                    y3.l();
                }
                if (d2.w != null) {
                    f9 = d2.p;
                    f8 = d2.p;
                    if (d2.q) {
                        if (d2.r != null) {
                            f9 = f7 = d2.r.readNumber(j2);
                            f8 = f7;
                        }
                        if (d2.s != null) {
                            f9 *= d2.s.readNumber(j2);
                        }
                        if (d2.t != null) {
                            f8 *= d2.t.readNumber(j2);
                        }
                    }
                    e[] eArray = d2.w;
                    int n8 = 0;
                    int n9 = eArray.length;
                    if (d2.A != null) {
                        n8 = (int)d2.A.readNumber(j2);
                        if (n8 < 0) {
                            n8 = 0;
                        }
                        if (n8 >= eArray.length) {
                            n8 = eArray.length;
                        }
                    }
                    if (d2.B != null) {
                        int n10 = (int)d2.B.readNumber(j2);
                        n9 = n8 + n10;
                        if (n9 < 0) {
                            n9 = 0;
                        }
                        if (n9 >= eArray.length) {
                            n9 = eArray.length;
                        }
                    }
                    for (int i4 = n8; i4 < n9; ++i4) {
                        int n11 = i4;
                        if (d2.z) {
                            n11 = d2.w.length - 1 - n11;
                        }
                        e e5 = eArray[n11];
                        com.corrodinggames.rts.gameFramework.m.e e6 = e5.b != null ? e5.b[j2.bX.R()] : e5.a;
                        float f28 = (float)n11 * d2.x;
                        y3.k();
                        y3.a(f27, f25, f26 - f28);
                        if (f9 != 1.0f || f8 != 1.0f) {
                            y3.a(f9, f8, f25, f26 - f28);
                        }
                        int n12 = n7 + n11 * d2.y;
                        Rect rect = com.corrodinggames.rts.game.units.custom.b.c.a(d2, e5, e6, n12);
                        RectF rectF = com.corrodinggames.rts.game.units.custom.b.c.a(d2, e5, e6, f25, f26 - f28);
                        y3.a(e6, rect, rectF, paint);
                        y3.l();
                    }
                }
            }
            object3 = null;
            if (d2.ad != null) {
                object3 = d2.ad.readUnit(j2);
            }
            if (object3 == null) continue;
            float f29 = f15 - ((com.corrodinggames.rts.gameFramework.l)object).cw;
            float f30 = f14 - ((com.corrodinggames.rts.gameFramework.l)object).cx - f13;
            float f31 = object3.eo - ((com.corrodinggames.rts.gameFramework.l)object).cw;
            float f32 = object3.ep - ((com.corrodinggames.rts.gameFramework.l)object).cx - object3.eq;
            Paint paint = d2.ah;
            if (paint == null) {
                paint = b;
            }
            if (d2.ai != null && (f9 = d2.ai.readNumber(j2)) != 1.0f) {
                Paint paint3 = a;
                paint3.b(paint.e());
                int n13 = (int)((float)paint.f() * f9);
                if (n13 < 0) {
                    n13 = 0;
                }
                if (n13 > 255) {
                    n13 = 255;
                }
                paint3.c(n13);
                paint = paint3;
            }
            ((com.corrodinggames.rts.gameFramework.l)object).bO.a(f29, f30, f31, f32, paint);
        }
    }

    static /* synthetic */ d b(l l2, String string) {
        return com.corrodinggames.rts.game.units.custom.b.c.c(l2, string);
    }
}
