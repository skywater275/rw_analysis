/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;

final class AbstractCommandSlot$1
extends AbstractBuildAction {
    AbstractCommandSlot$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String a() {
        return "-Increases HP, attack damage, and range";
    }


    public String getDescription() {  // 02b a$1 a() 描述文本 (GameAction 抽象)
        return "-Increases HP, attack damage, and range";
    }

    public String getLabel() {  // 02b a$1 b() 标签 (GameAction 抽象)
        return "Upgrade";
    }

    public String b() {
        return "Upgrade";
    }


    public int getResourceCost() {  // 02b a$1 c() = 1200 (c()=getResourceCost, 与 BuildActionSlot$1 模式一致)
        return 1200;
    }

    @Override
    public float K() {
        return 0.001f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        BuildActionSlot b2 = (BuildActionSlot)am2;  // 02b a$1 L37: (b)var1
        if (b2.j || b2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }


    public boolean b(UnitInstance am2) {
        BuildActionSlot b2 = (BuildActionSlot)am2;  // 02b a$1 L42: (b)var1
        return !b2.j;
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {
        return ActionCategory.c;  // 02b a$1 L51: t.c (t = units.a.t = ActionCategory)
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
