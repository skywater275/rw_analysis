/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.actions.SellAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;

public interface UnitTypeHandle {
    public boolean C();

    public boolean w();

    public UnitInstance a();

    public SellAction d();

    public int c();

    public int b(int var1);

    public CustomActionBase u();

    public CustomActionBase d(int var1);

    public CustomActionBase B();

    public Texture z();

    public boolean y();

    public float D();

    public int g();

    public boolean j();

    public boolean l();

    public boolean k();

    public boolean m();

    public boolean n();

    public MovementTypeEnum o();

    public boolean p();

    public be q();

    public String e();

    public String f();

    public String i();

    public void h();

    public ArrayList a(int var1);

    public String v();

    public UnitConfig x();

    public int a(UnitInstance var1);
}
