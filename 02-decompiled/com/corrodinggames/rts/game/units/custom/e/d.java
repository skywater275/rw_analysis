/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.e.b;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.ab;

public strictfp class d {
    public String a;
    public a b;
    public float c;
    public Integer d;
    public boolean e;
    public boolean f;
    bb g;
    bb h;
    boolean i;
    boolean j;
    public boolean k;
    public boolean l;
    public float m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public b r = com.corrodinggames.rts.game.units.custom.e.b.a;
    public int s;
    public bb t;
    public bb u;
    public String v;
    public a w;
    public boolean x;
    public boolean y;
    public String z;
    public a A;
    public e B;
    public boolean C;

    public d(boolean bl) {
        this.f = bl;
    }

    public void a(l l2, ab ab2, String string, String string2) {
        bb bb2;
        this.a = string2;
        this.g = ag.a(ab2, string, "displayName", null);
        this.h = ag.a(ab2, string, "displayNameShort", null);
        if (this.h == null) {
            this.h = this.g;
        }
        this.i = ab2.a(string, "displayNameHideWhenIconShownInHUD", (Boolean)false);
        this.j = ab2.a(string, "displayNameHideWhenIconShownInText", (Boolean)false);
        this.l = ab2.a(string, "hidden", (Boolean)false);
        float f2 = 1.0f;
        boolean bl = ab2.a(string, "includeInStats", (Boolean)true);
        if (!bl) {
            f2 = 0.0f;
        }
        if (this.l || !this.f) {
            f2 = 0.0f;
        }
        this.m = ab2.a(string, "valueInStats", Float.valueOf(f2)).floatValue();
        if (!bl && this.m != 0.0f) {
            throw new bo("[" + string + "]includeInStats==false expects valueInStats==0");
        }
        if (this.m < 0.0f) {
            throw new bo("[" + string + "]valueInStats cannot be < 0 (is:" + this.m + ")");
        }
        this.k = ab2.a(string, "stackHorizontal", (Boolean)false);
        this.c = ab2.a(string, "priority", Float.valueOf(0.0f)).floatValue();
        this.d = ab2.a(string, "displayColor", (Integer)null);
        this.e = ab2.a(string, "displayColorUseInText", (Boolean)true);
        this.n = ab2.a(string, "displayWithRounding", (Boolean)true);
        this.o = ab2.a(string, "displayRoundedDown", (Boolean)false);
        this.p = ab2.a(string, "displayWhenZero", (Boolean)false);
        boolean bl2 = !this.l && this.f;
        this.q = ab2.a(string, "displayInHud", (Boolean)bl2);
        if (this.q && !this.f) {
            throw new bo("[" + string + "]displayInHud:true currently only supported on global resources");
        }
        if (this.q && this.l) {
            throw new bo("[" + string + "]displayInHud:true only supported non-hidden resources");
        }
        this.s = ab2.b(string, "displayPos", 0);
        this.r = (b)ab2.a(string, "displayDigitGrouping", com.corrodinggames.rts.game.units.custom.e.b.a, b.class);
        this.t = ag.a(ab2, string, "displayTextPrefix", null);
        this.u = ag.a(ab2, string, "displayTextPostfix", null);
        bb bb3 = ag.a(ab2, string, "displayPrefixInHUD", null);
        if (bb3 != null) {
            if (this.t != null) {
                throw new bo("[" + string + "]displayPrefixInHUD and displayTextPrefix are aliases, don't use both");
            }
            this.t = bb3;
        }
        if ((bb2 = ag.a(ab2, string, "displayPostfixInHUD", null)) != null) {
            if (this.u != null) {
                throw new bo("[" + string + "]displayPostfixInHUD and displayTextPostfix are aliases, don't use both");
            }
            this.u = bb2;
        }
        this.v = ab2.b(string, "displayTextAppendResource", (String)null);
        String string3 = ab2.b(string, "appendResourceInHUD", (String)null);
        if (string3 != null) {
            if (this.v != null) {
                throw new bo("[" + string + "]displayTextAppendResource and appendResourceInHUD are aliases, don't use both");
            }
            this.v = string3;
        }
        this.x = ab2.a(string, "displayTextAppendResourceWithGap", (Boolean)false);
        this.y = ab2.a(string, "appendResourceInHUD_whenThisZero", (Boolean)true);
        this.B = l2.a(ab2, string, "iconImage", true);
        if (this.B != null && (this.B.m() > 100 || this.B.l() > 100)) {
            throw new bo("[" + string + "]iconImage: Image is too big, keep under 100x100");
        }
        this.C = ab2.a(string, "iconImageUseInText", (Boolean)true);
        if (this.i && this.B == null) {
            throw new bo("[" + string + "]displayNameHideWhenIconShownInHUD: Cannot use without iconImage");
        }
        if (this.j && this.B == null) {
            throw new bo("[" + string + "]displayNameHideWhenIconShownInText: Cannot use without iconImage");
        }
        String string4 = this.f ? "g_" : "l_";
        string4 = string4 + this.a;
        this.b = com.corrodinggames.rts.game.units.custom.e.a.a(string4, false, this.f);
        if (this.b.u) {
            throw new RuntimeException("Cannot define resource with a built-in name: " + string4);
        }
        if (!this.f) {
            String string5;
            this.z = string5 = ab2.b(string, "equivalentGlobalResourceForAI", (String)null);
        }
    }

    public void a(l l2) {
        if (this.z != null) {
            this.A = l2.k(this.z);
            if (this.A == null) {
                throw new bo("[resource]equivalentGlobalResourceForAI: Failed to find resource: " + this.z);
            }
            if (!this.A.t) {
                throw new bo("[resource]equivalentGlobalResourceForAI: Expected global resource for: " + this.z);
            }
        }
        if (this.v != null) {
            this.w = l2.k(this.v);
            if (this.w == null) {
                throw new bo("[resource]displayTextAppendResource: Failed to find resource: " + this.v);
            }
        }
    }
}
