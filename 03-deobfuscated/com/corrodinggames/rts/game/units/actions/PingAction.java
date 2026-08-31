/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Rect;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.PingType;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;

public class PingAction
extends GameAction {
    public PingType k;
    static ArrayList b = new ArrayList();
    static Rect c;

    public PingAction() {
        this(PingType.a);  // 02b units/a/j.java L23: this(k.a) (k=枚举类名)
    }

    public PingAction(PingType k2) {
        super("c_6_" + k2.name());
        this.k = k2;
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }

    @Override
    public int getResourceCost() {
        return 0;
    }

    public UnitRegistry w() {  // 02b j.java L39: ar w()
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.j;
    }


    public ActionCategory f() {
        return ActionCategory.a;
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b j.java L55: a()
        return "Ping Map" + this.k.a();
    }

    @Override
    public String getLabel() {
        return this.k.b();
    }

    public String K() {
        return this.k.c();
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public boolean s() {
        return true;
    }

    public static PingAction a(ActionId c2) {
        for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) b) {
            if (!s2.d(c2)) continue;
            return (PingAction) s2;
        }
        return null;
    }


    public ArrayList q(UnitInstance am2) {
        return b;
    }


    public Texture j() {  // 02b j.java L94: m.e j()
        return com.corrodinggames.rts.gameFramework.effects.HUDManager.s[9].i;
    }

    @Override
    public Rect v() {
        int n2 = 7 + this.k.ordinal();
        c.a(29 * n2, 0, 29 * n2 + 28, 28);  // 02b j.java L100: c.a (Rect)
        return c;
    }


    public /* synthetic */ UnitTypeHandle i() {  // 02b j.java L105: as i() → this.w()
        return this.w();
    }

    static {
        for (PingType k2 : PingType.values()) {  // 02b L110: k.values()
            b.add(new PingAction(k2));  // 02b units/a/j.java L115: b.add(new j(var3))
        }
        c = new Rect();
    }
}
