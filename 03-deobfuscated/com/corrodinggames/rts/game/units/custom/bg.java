/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.utility.ab;

public final class bg {
    String a;
    TeamTag b;
    UnitConfig c;
    com.corrodinggames.rts.game.units.custom.UnitTypeComparator d;  // 02b game/q
    float e;
    float f;
    float g;
    float h;
    boolean i;
    boolean j;
    int k;
    int l;
    boolean m;
    boolean n;
    LocalizedString o;
    boolean p;

    public boolean a() {
        return this.n || this.m;
    }

    public void a(ModUnitRegistry l2, ab ab2, String string) throws bo {
        this.b = ab2.a(string, "anyRuleInGroup", (TeamTag) null);
        this.c = ab2.a(l2, string, "searchTags", (UnitConfig) null);
        this.d = (com.corrodinggames.rts.game.units.custom.UnitTypeComparator) ab2.a(string, "searchTeam", (Enum) com.corrodinggames.rts.game.units.custom.UnitTypeComparator.own, com.corrodinggames.rts.game.units.custom.UnitTypeComparator.class);
        this.e = ab2.i(string, "searchDistance");
        this.f = this.e * this.e;
        this.g = ab2.a(string, "searchOffsetX", Float.valueOf(0.0f)).floatValue();
        this.h = ab2.a(string, "searchOffsetY", Float.valueOf(0.0f)).floatValue();
        this.i = ab2.a(string, "excludeIncompleteBuildings", (Boolean)false);
        this.j = ab2.a(string, "excludeNonBuildings", (Boolean)false);
        this.k = ab2.b(string, "minCount", Integer.MIN_VALUE);
        this.l = ab2.b(string, "maxCount", Integer.MAX_VALUE);
        this.p = ab2.a(string, "checkEachTile", (Boolean)true);
        this.m = ab2.a(string, "aiSuggestionOnly", (Boolean)false);
        this.n = ab2.a(string, "blocksPlacement", Boolean.valueOf(!this.m));
        if (this.m && this.n) {
            throw new bo("[" + string + "]: Cannot use aiSuggestionOnly and blocksPlacement at the same time");
        }
        this.o = ModLoader.a(ab2, string, "cannotPlaceMessage", (String)null);
    }
}
