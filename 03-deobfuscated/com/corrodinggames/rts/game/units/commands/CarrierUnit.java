/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public interface CarrierUnit {
    public boolean dA();

    public void b(BuilderUnit var1);

    public void a(BuilderUnit var1);

    public int h(UnitTypeHandle var1);

    public int f(boolean var1);

    public int a(com.corrodinggames.rts.game.units.actions.ActionId var1, boolean var2);  // 02b units/d/l.java L19: a(c,boolean)

    public boolean dy();

    public void a(PointF var1);

    public void dz();

    public BuilderUnit dw();

    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList dx();  // 02b units/d/l.java L29: utility.m dx()

    public boolean c(BuilderUnit var1);
}
