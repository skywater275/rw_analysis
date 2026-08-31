/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects;
import com.corrodinggames.rts.game.units.custom.RangeValue;
import com.corrodinggames.rts.game.units.custom.CurveType;
import com.corrodinggames.rts.game.units.custom.ParameterAnimator;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.effects.b;
import com.corrodinggames.rts.game.units.custom.effects.EffectConfig;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import java.util.ArrayList;
import java.util.Locale;

public strictfp class LogicBoolean {
    public boolean a;
    protected String b;
    protected LocalizedString displayName;
    protected LocalizedString descriptionText;
    protected boolean isCurrencyType;
    protected boolean showInResourceUI;
    protected LocalizedString abbreviationName;
    protected LocalizedString formatString;
    public LogicBoolean parentResourceType;
    public boolean isGlobalResource;
    public boolean isTransferable;
    public boolean l = true;
    Integer m;
    public boolean n;
    public boolean o;
    public boolean depletedWhenEmpty;
    public b q = com.corrodinggames.rts.game.units.custom.effects.b.a;
    boolean r;
    float s;
    protected boolean d;
    protected boolean u;
    protected boolean t;  // 02b e.a.t: 团队修正标志 (a(am)/a(am,D) 分支)
    LogicBoolean v;  // 02b e.a.v: e.a 类型
    public boolean hasMaxLimit;
    public float x;
    public com.corrodinggames.rts.gameFramework.rendering.Texture y;
    public boolean z;
    static ArrayList arrayList1 = new ArrayList();
    static ArrayList arrayList2 = new ArrayList();

    protected LocalizedString c;  // 02b e/a.java L16: protected bb c (effects/config/b 链)
    public static ArrayList C = new ArrayList();
    public static final LogicBoolean D = a(new com.corrodinggames.rts.game.units.custom.effects.config.c());  // 02b e.a.D = a(new e/a/c())
    public static final LogicBoolean E = a(new com.corrodinggames.rts.game.units.custom.effects.config.d());  // 02b e.a.E = a(new e/a/d())
    public static final LogicBoolean F = a(new com.corrodinggames.rts.game.units.custom.effects.config.b());  // 02b e.a.F = a(new e/a/b())
    public static final LogicBoolean G;
    public static final LogicBoolean shieldResourceType;
    public static final LogicBoolean hitPointResourceType;

    public boolean a() {
        return this.r;
    }

    public float b() {
        return this.s;
    }

    public boolean c() {
        return this.u;
    }

    public boolean d() {
        return this.d;
    }

    public static void e() {
        for (Object object : arrayList1) {  // 02b e.a.A
            ((LogicBoolean) object).g();
        }
        ArrayList arrayList = new ArrayList();
        for (LogicBoolean a2 : (java.util.Collection<LogicBoolean>) (java.util.Collection) arrayList1) {  // 02b e.a.A
            if (!a2.a) continue;
            arrayList.add(a2);
        }
        arrayList2 = arrayList;
    }

    public static ArrayList f() {
        return arrayList2;
    }

    public void g() {
        if (this.u) {
            this.a = true;
            return;
        }
        EffectConfig d2 = null;  // 02b custom/e/d = EffectConfig
        for (ModUnitRegistry l2 : (java.util.Collection<ModUnitRegistry>) (java.util.Collection) com.corrodinggames.rts.game.units.custom.ModUnitRegistry.d) {
            EffectConfig d3 = l2.a(this);  // 02b custom/l.a(e.a) → e/d
            if (d3 == null || d2 != null && !(d2.c < d3.c)) continue;
            d2 = d3;
        }
        boolean bl2 = this.a = d2 != null;
        if (d2 != null) {
            this.displayName = d2.g;
            this.descriptionText = d2.h;
            this.isCurrencyType = d2.i;
            this.showInResourceUI = d2.j;
            this.m = d2.d;
            this.n = d2.e;
            this.o = d2.o;
            this.depletedWhenEmpty = d2.isSelfDestructUnit;  // 02b e/d.p
            this.q = d2.r;
            this.abbreviationName = d2.t;
            this.formatString = d2.u;
            this.parentResourceType = d2.displayResourceType;  // 02b e/d.w
            this.isGlobalResource = d2.y;
            this.l = d2.q;
            this.isTransferable = d2.x;
            this.r = d2.l;
            this.s = d2.m;
            this.v = d2.A;
            this.hasMaxLimit = d2.k;
            this.x = d2.s;
            this.y = d2.B;
            this.z = d2.C;
        }
    }

    public Integer h() {
        return this.m;
    }

    public String i() {
        if (this.displayName == null) {
            return this.b;
        }
        return this.displayName.getLocalizedText();  // 02b bb.b()
    }

    public String j() {
        if (this.descriptionText != null) {
            return this.descriptionText.getLocalizedText();  // 02b bb.b()
        }
        return this.i();  // 02b e.a.j(): 委托 i()
    }

    public String a(double d2, boolean bl2) {
        String string = this.o ? "" + (int)d2 : com.corrodinggames.rts.gameFramework.GameUtils.c(d2);
        string = a(string, this.q);
        return this.a(bl2) + string + this.b(bl2);
    }

    public static String a(String string, b b2) {
        String string2;
        if (b2 == com.corrodinggames.rts.game.units.custom.effects.b.a) {
            return string;
        }
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bQ.disableDigitGrouping) {
            return string;
        }
        String string3 = string;
        String string4 = "";
        String string5 = "";
        int n2 = string3.indexOf(".");
        if (n2 != -1) {
            string5 = string3.substring(n2);
            string3 = string3.substring(0, n2);
        }
        if (string3.length() <= 3) {
            return string;
        }
        if (b2 == com.corrodinggames.rts.game.units.custom.effects.b.b) {
            string2 = " ";
        } else if (b2 == com.corrodinggames.rts.game.units.custom.effects.b.c) {
            string2 = ",";
        } else {
            throw new RuntimeException("Unhandled grouping style: " + (Object)((Object)b2));
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n3 = string3.length() % 3;
        if (n3 != 0) {
            stringBuilder.append(string3.substring(0, n3));
        }
        for (int i2 = n3; i2 < string3.length(); i2 += 3) {
            if (i2 != 0) {
                stringBuilder.append(string2);
            }
            stringBuilder.append(string3.substring(i2, i2 + 3));
        }
        if (string5 == "") {
            return stringBuilder.toString();
        }
        return stringBuilder.toString() + string5;
    }

    public static String a(long l2, b b2) {
        if (b2 == com.corrodinggames.rts.game.units.custom.effects.b.a) {
            return "" + l2;
        }
        if (b2 == com.corrodinggames.rts.game.units.custom.effects.b.b) {
            return String.format(Locale.US, "%,d", l2).replace(",", " ");
        }
        if (b2 == com.corrodinggames.rts.game.units.custom.effects.b.c) {
            return String.format(Locale.US, "%,d", l2);
        }
        throw new RuntimeException("Unhandled grouping style: " + (Object)((Object)b2));
    }

    public String a(boolean bl2) {
        if (this.abbreviationName != null) {
            return this.abbreviationName.getLocalizedText();  // 02b bb.b()
        }
        if (bl2 && this.isCurrencyType) {
            return "";
        }
        return this.i() + ": ";  // 02b e.a.a(Z): 委托 i()
    }

    public String b(boolean bl2) {
        if (this.formatString != null) {
            return this.formatString.getLocalizedText();  // 02b bb.b()
        }
        return "";
    }

    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return this.y;
    }

    protected LogicBoolean() {
    }

    public static LogicBoolean a(String string) {
        string = string.toLowerCase(Locale.ENGLISH);
        for (LogicBoolean a2 : (java.util.Collection<LogicBoolean>) (java.util.Collection) C) {
            if (!a2.b.equalsIgnoreCase(string)) continue;
            return a2;
        }
        return null;
    }

    public static LogicBoolean a(LogicBoolean a2) {
        for (LogicBoolean a3 : (java.util.Collection<LogicBoolean>) (java.util.Collection) arrayList1) {  // 02b e.a.A
            if (!a3.b.equals(a2.b)) continue;
            throw new RuntimeException("Built in resource already exists:" + a2.b);
        }
        LogicBoolean a4 = a2;  // 02b e.a 类型
        arrayList1.add(a4);  // 02b e.a.A
        C.add(a4);
        return a4;
    }

    public static LogicBoolean a(String string, boolean bl2, boolean bl3) {
        for (LogicBoolean a2 : (java.util.Collection<LogicBoolean>) (java.util.Collection) arrayList1) {  // 02b e.a.A
            if (!a2.b.equals(string)) continue;
            return a2;
        }
        LogicBoolean a3 = new LogicBoolean();  // 02b e.a 类型
        a3.b = string;
        a3.u = bl2;
        a3.t = bl3;
        arrayList1.add(a3);  // 02b e.a.A
        return a3;
    }

    public static LogicBoolean b(String string) {
        for (LogicBoolean a2 : (java.util.Collection<LogicBoolean>) (java.util.Collection) arrayList1) {  // 02b e.a.A
            if (!a2.b.equals(string)) continue;
            return a2;
        }
        return null;
    }

    private String a(double d2) {
        String string = com.corrodinggames.rts.gameFramework.GameUtils.a(d2, 1);
        return a(string, this.q);
    }

    public String a(double d2, boolean bl2, boolean bl3) {
        String string = bl3 && this.showInResourceUI ? "" : this.isGlobalResource + ": ";
        if (this == D) {
            string = "$";
        }
        if (bl2) {
            if (d2 > 0.0) {
                return "+" + string + this.a(d2);
            }
            return "-" + string + this.a(-d2);
        }
        if (d2 > 0.0) {
            return string + this.a(d2);
        }
        return string + this.a(d2);
    }

    public String toString() {
        return "resource(" + this.b + ")";
    }

    public double a(UnitInstance am2) {  // 02b e.a.a(am)
        if (this.t) {  // 02b e.a.t: 团队修正标志
            return am2.player.getTeamStatModifiers().a(this);  // 02b n.c(e.a)
        }
        return am2.getStatsCollection().a(this);  // 02b am.a(e.a)
    }

    public void a(UnitInstance am2, double d2) {  // 02b e.a.a(am,D)
        if (this.t) {
            am2.player.getTeamStatModifiers().a(this, d2);  // 02b n.V().a(e.a,D)
        } else {
            am2.getStatsCollection().a(this, d2);  // 02b am.df().a(e.a,D)
        }
    }

    public void b(UnitInstance am2, double d2) {  // 02b e.a.b(am,D)
        if (this.t) {
            am2.player.getTeamStatModifiers().do_b(this, d2);  // 02b n.V().b(e.a,D)
        } else {
            am2.getStatsCollection().do_b(this, d2);  // 02b am.df().b(e.a,D)
        }
    }

    static {
        shieldResourceType = a(new com.corrodinggames.rts.game.units.custom.effects.config.f());  // 02b e.a.H = a(new e/a/f())
        G = a(new com.corrodinggames.rts.game.units.custom.effects.config.e());  // 02b e.a.G = a(new e/a/e())
        hitPointResourceType = a(new com.corrodinggames.rts.game.units.custom.effects.config.e());  // 02b e.a.G = a(new e/a/e())
    }
}
