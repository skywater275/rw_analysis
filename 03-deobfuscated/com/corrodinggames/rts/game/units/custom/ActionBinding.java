/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;

public strictfp class ActionBinding {
    String a;
    String b;
    String c;
    UnitTypeHandle d;
    boolean e;
    public boolean f;

    public void a() throws bo {
        if (!this.e) {
            this.d = ModUnitRegistry.s(this.c);
            if (this.d == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("AllUnitTypes: " + ModUnitRegistry.E());
                if (this.f) {
                    throw new bo("Could not find unit type:" + this.c + " used on:" + this.a + " in section:" + this.b + " (Note: Prefix with 'unitref' if not using a unit type here)");
                }
                throw new bo("Could not find unit type:" + this.c + " used on:" + this.a + " in section:" + this.b);
            }
        }
    }

    /* 02b v.java L31: 空方法; 子类 CustomPhysics.b() 抛 checked bo (02b x.java L21),
       覆写链要求父类声明 throws bo (R8 移除 throws, javap 无 throws 铁证) */
    public void b() throws bo {
    }

    public UnitTypeHandle c() {
        return this.d;
    }

    public String d() {
        if (this.e) {
            if (this.d != null) {
                return this.d.i();
            }
            return "(Error: known type is null)";
        }
        return this.c;
    }
}
