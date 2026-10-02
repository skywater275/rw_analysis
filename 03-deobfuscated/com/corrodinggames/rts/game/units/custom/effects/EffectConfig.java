/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects;

import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.effects.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.effects.b;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class EffectConfig {
    public String unitConfigId;
    public LogicBoolean b;
    public float c;
    public Integer d;
    public boolean e;
    public boolean f;
    public LocalizedString g;  // 02b e/d.g: displayName
    public LocalizedString h;  // 02b e/d.h: displayNameShort
    public boolean i;  // 02b e/d.i: displayNameHideWhenIconShownInHUD
    public boolean j;  // 02b e/d.j: displayNameHideWhenIconShownInText
    public boolean k;
    public boolean l;
    public float m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean isSelfDestructUnit;
    public boolean q;
    public b r = com.corrodinggames.rts.game.units.custom.effects.b.a;
    public int s;
    public LocalizedString t;
    public LocalizedString u;
    public String v;
    public LogicBoolean displayResourceType;
    public boolean x;
    public boolean y;
    public String z;
    public LogicBoolean A;
    public Texture B;  // 02b e/d.B = m.e (Texture): iconImage
    public boolean C;

    public EffectConfig(boolean bl) {
        this.f = bl;
    }

    public void applyConfig(ModUnitRegistry l2, com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2) throws bo {
        LocalizedString bb2;
        this.unitConfigId = string2;
        this.g = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayName", null);
        this.h = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayNameShort", null);
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
        this.isSelfDestructUnit = ab2.a(string, "displayWhenZero", (Boolean)false);
        boolean bl2 = !this.l && this.f;
        this.q = ab2.a(string, "displayInHud", (Boolean)bl2);
        if (this.q && !this.f) {
            throw new bo("[" + string + "]displayInHud:true currently only supported on global resources");
        }
        if (this.q && this.l) {
            throw new bo("[" + string + "]displayInHud:true only supported non-hidden resources");
        }
        this.s = ab2.b(string, "displayPos", 0);
        this.r = (b)ab2.a(string, "displayDigitGrouping", com.corrodinggames.rts.game.units.custom.effects.b.a, b.class);
        this.t = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayTextPrefix", null);
        this.u = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayTextPostfix", null);
        LocalizedString bb3 = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayPrefixInHUD", null);
        if (bb3 != null) {
            if (this.t != null) {
                throw new bo("[" + string + "]displayPrefixInHUD and displayTextPrefix are aliases, don't use both");
            }
            this.t = bb3;
        }
        if ((bb2 = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayPostfixInHUD", null)) != null) {
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
        string4 = string4 + this.unitConfigId;
        this.b = com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.a(string4, false, this.f);
        if (this.b.u) {
            throw new RuntimeException("Cannot define resource with LogicBoolean built-in name: " + string4);
        }
        if (!this.f) {
            String string5;
            this.z = string5 = ab2.b(string, "equivalentGlobalResourceForAI", (String)null);
        }
    }

    public void applyConfig(ModUnitRegistry l2) throws bo {
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
            this.displayResourceType = l2.k(this.v);
            if (this.displayResourceType == null) {
                throw new bo("[resource]displayTextAppendResource: Failed to find resource: " + this.v);
            }
        }
    }

    public void a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry modUnitRegistry) throws bo {  // 02b e/d.java L152-171: 解析全局资源引用
        if (this.z != null) {
            this.A = modUnitRegistry.k(this.z);
            if (this.A == null) {
                throw new com.corrodinggames.rts.game.units.custom.bo("[resource]equivalentGlobalResourceForAI: Failed to find resource: " + this.z);
            }
            if (!this.A.t) {
                throw new com.corrodinggames.rts.game.units.custom.bo("[resource]equivalentGlobalResourceForAI: Expected global resource for: " + this.z);
            }
        }
        if (this.v != null) {
            this.displayResourceType = modUnitRegistry.k(this.v);
            if (this.displayResourceType == null) {
                throw new com.corrodinggames.rts.game.units.custom.bo("[resource]displayTextAppendResource: Failed to find resource: " + this.v);
            }
        }
    }


    public void a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry modUnitRegistry, com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2) throws bo {  // 02b e/d.java L49-149 直译
        this.unitConfigId = string2;
        this.g = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayName", (String)null);
        this.h = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayNameShort", (String)null);
        if (this.h == null) {
            this.h = this.g;
        }
        this.i = ab2.a(string, "displayNameHideWhenIconShownInHUD", (Boolean)false).booleanValue();
        this.j = ab2.a(string, "displayNameHideWhenIconShownInText", (Boolean)false).booleanValue();
        this.l = ab2.a(string, "hidden", (Boolean)false).booleanValue();
        float f2 = 1.0f;
        boolean bl = ab2.a(string, "includeInStats", (Boolean)true).booleanValue();
        if (!bl) {
            f2 = 0.0f;
        }
        if (this.l || !this.f) {
            f2 = 0.0f;
        }
        this.m = ab2.a(string, "valueInStats", Float.valueOf(f2)).floatValue();
        if (!bl && this.m != 0.0f) {
            throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]includeInStats==false expects valueInStats==0");
        }
        if (this.m < 0.0f) {
            throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]valueInStats cannot be < 0 (is:" + this.m + ")");
        }
        this.k = ab2.a(string, "stackHorizontal", (Boolean)false).booleanValue();
        this.c = ab2.a(string, "priority", Float.valueOf(0.0f)).floatValue();
        this.d = ab2.a(string, "displayColor", (Integer)null);
        this.e = ab2.a(string, "displayColorUseInText", (Boolean)true).booleanValue();
        this.n = ab2.a(string, "displayWithRounding", (Boolean)true).booleanValue();
        this.o = ab2.a(string, "displayRoundedDown", (Boolean)false).booleanValue();
        this.isSelfDestructUnit = ab2.a(string, "displayWhenZero", (Boolean)false).booleanValue();
        boolean bl2 = !this.l && this.f;
        this.q = ab2.a(string, "displayInHud", (Boolean)bl2).booleanValue();
        if (this.q && !this.f) {
            throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]displayInHud:true currently only supported on global resources");
        }
        if (this.q && this.l) {
            throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]displayInHud:true only supported non-hidden resources");
        }
        this.s = ab2.b(string, "displayPos", 0);
        this.r = (com.corrodinggames.rts.game.units.custom.effects.b)ab2.a(string, "displayDigitGrouping", (Enum)com.corrodinggames.rts.game.units.custom.effects.b.a, com.corrodinggames.rts.game.units.custom.effects.b.class);
        this.t = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayTextPrefix", (String)null);
        this.u = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayTextPostfix", (String)null);
        com.corrodinggames.rts.game.units.custom.LocalizedString localizedString = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayPrefixInHUD", (String)null);
        if (localizedString != null) {
            if (this.t != null) {
                throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]displayPrefixInHUD and displayTextPrefix are aliases, don't use both");
            }
            this.t = localizedString;
        }
        com.corrodinggames.rts.game.units.custom.LocalizedString localizedString2 = com.corrodinggames.rts.game.units.custom.ModLoader.a(ab2, string, "displayPostfixInHUD", (String)null);
        if (localizedString2 != null) {
            if (this.u != null) {
                throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]displayPostfixInHUD and displayTextPostfix are aliases, don't use both");
            }
            this.u = localizedString2;
        }
        this.v = ab2.b(string, "displayTextAppendResource", (String)null);
        String string3 = ab2.b(string, "appendResourceInHUD", (String)null);
        if (string3 != null) {
            if (this.v != null) {
                throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]displayTextAppendResource and appendResourceInHUD are aliases, don't use both");
            }
            this.v = string3;
        }
        this.x = ab2.a(string, "displayTextAppendResourceWithGap", (Boolean)false).booleanValue();
        this.y = ab2.a(string, "appendResourceInHUD_whenThisZero", (Boolean)true).booleanValue();
        this.B = modUnitRegistry.a(ab2, string, "iconImage", true);
        if (this.B != null && (this.B.m() > 100 || this.B.l() > 100)) {
            throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]iconImage: Image is too big, keep under 100x100");
        }
        this.C = ab2.a(string, "iconImageUseInText", (Boolean)true).booleanValue();
        if (this.i && this.B == null) {
            throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]displayNameHideWhenIconShownInHUD: Cannot use without iconImage");
        }
        if (this.j && this.B == null) {
            throw new com.corrodinggames.rts.game.units.custom.bo("[" + string + "]displayNameHideWhenIconShownInText: Cannot use without iconImage");
        }
        String string4 = this.f ? "g_" : "l_";
        string4 = string4 + this.unitConfigId;
        this.b = com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.a(string4, false, this.f);
        if (this.b.u) {
            throw new RuntimeException("Cannot define resource with a built-in name: " + string4);
        }
        if (!this.f) {
            this.z = ab2.b(string, "equivalentGlobalResourceForAI", (String)null);
        }
    }

}
