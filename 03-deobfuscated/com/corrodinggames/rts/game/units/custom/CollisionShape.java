/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class CollisionShape {
    public CustomArrayList a = new CustomArrayList();  // 02b custom/i.a = utility.m

    public CollisionShape() {
    }

    public CollisionShape(UnitConfig h2) {  // 02b custom/i.i(h)
        if (h2 == null) {
            return;
        }
        for (TeamTag g2 : h2.a) {
            this.a.add(g2);
        }
    }

    public boolean a(UnitConfig h2) {  // 02b custom/i.a(h)
        if (h2 == null) {
            return false;
        }
        boolean bl = false;
        for (TeamTag g2 : h2.a) {
            if (!this.a(g2)) continue;
            bl = true;
        }
        return bl;
    }

    public boolean a(TeamTag g2) {
        if (!this.a.contains(g2)) {
            this.a.add(g2);
            return true;
        }
        return false;
    }

    public boolean b(UnitConfig h2) {  // 02b custom/i.b(h)
        if (h2 == null) {
            return false;
        }
        boolean bl = false;
        for (TeamTag g2 : h2.a) {
            if (!this.a.remove(g2)) continue;
            bl = true;
        }
        return bl;
    }

    public UnitConfig a() {  // 02b custom/i.a() 返回 h
        if (this.a.size() == 0) {
            return TeamTag.d;
        }
        return new UnitConfig((TeamTag[])this.a.toArray(TeamTag.c));
    }
}
