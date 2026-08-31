/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.resources;


import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.resources.CommandSlotBase;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.resources.d;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.ArrayList;

public class c
extends CommandSlotBase {
    public final CustomArrayList a = new CustomArrayList();  // 02b d/c.java L21: m a (DirectionConfig 为幻觉)
    boolean b;
    public int storageLimit;
    public int d;
    public int e;
    public int int4;

    /* 02b d/c.java L29: 抛 checked bo (R8 移除 throws) */
    public static c hasint(ModUnitRegistry l2, ab ab2, String string, String string2, c c2) throws bo {  // 02b d/c.java L29: a(l,ab,String,String,c) (ResourceStorage 为幻觉)
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return c2;
        }
        try {
            c c3 = hasint(l2, string3);  // 02b L35: a(var0,var5) (03 名 hasint)
            return c3;
        }
        catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            throw new bo("[" + string + "]" + string2 + ": " + runtimeException.getMessage());
        }
    }

    /* 02b d/c.java L44: 委托链抛 bo */
    public static c hasint(ModUnitRegistry l2, String string) throws bo {
        return hasint(l2, string, false);  // 02b L45: a(var0,var1,false) (03 名 hasint)
    }

    /* 02b d/c.java L48: 大量抛 checked bo */
    public static c hasint(ModUnitRegistry l2, String string, boolean bl) throws bo {
        c c2 = new c();  // 02b L49: c var3 = new c()
        ArrayList arrayList = al.a(string, ",", "|", false);
        for (String string2 : (java.util.Collection<String>) (java.util.Collection) arrayList) {
            String string3;
            String string4;
            int n2 = al.a(string2, "=", ":");
            if (n2 == -1) {
                if (!bl) {
                    throw new bo("Unknown price format:" + string);
                }
                string4 = "credits";
                string3 = string2;
            } else {
                string4 = string2.substring(0, n2).trim();
                string3 = string2.substring(n2 + 1);
            }
            if (string4.equals("hasFlag")) {
                c2.e = CustomActionBase.a(c2.e, string3);
                continue;
            }
            if (string4.equals("hasMissingFlag")) {
                c2.int4 = CustomActionBase.a(c2.int4, string3);  // 02b f 字段 = 03 int4
                continue;
            }
            if (string4.equals("setFlag")) {
                c2.storageLimit = CustomActionBase.a(c2.storageLimit, string3);  // 02b c 字段 = 03 storageLimit
                continue;
            }
            if (string4.equals("unsetFlag")) {
                c2.d = CustomActionBase.a(c2.d, string3);
                continue;
            }
            com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2 = l2.j(string4);
            if (a2 == null) {
                throw new bo("Could not find resource type:" + string4 + " from [" + string + "]");
            }
            LogicBoolean logicBoolean = LogicBooleanLoader.parseNumberBlock(l2, string3);
            if (logicBoolean == null) {
                throw new bo("Value missing for:" + string4 + " from [" + string + "]");
            }
            if (!(logicBoolean instanceof LogicBoolean$StaticValueBoolean)) {
                c2.b = true;
            }
            d d2 = new d(a2, logicBoolean);  // 02b L94: d var12 (ResourceFlow 为幻觉)
            c2.a.add(d2);
        }
        return c2;
    }


    public boolean b(UnitInstance am2) {  // 02b d/c.java L101: b(am) 可负担检查 (hasint 为误名)
        return this.b(am2, 1.0);
    }


    public boolean b(UnitInstance am2, double d2) {  // 02b d/c.java L105: b(am,double) 覆盖抽象
        if (!(am2 instanceof UnitType)) {
            return false;
        }
        UnitType y2 = (UnitType)am2;
        int n2 = this.a.a;
        Object[] objectArray = this.a.a();
        for (int i = 0; i < n2; ++i) {
            double d3;
            d d4 = (d)objectArray[i];  // 02b L114: d var8
            double d5 = d4.changeRate != null ? (double)d4.changeRate.readNumber(y2) * d2 : d4.maxAmount * d2;  // 02b d.c/d.b
            if (!(d5 > 0.0) || !((d3 = d4.currentAmount.a(y2)) < d5)) continue;  // 02b d.a
            return false;
        }
        return this.isEnabled(y2);
    }

    public void d(UnitInstance am2) {
        if (!(am2 instanceof UnitType)) {
            com.corrodinggames.rts.gameFramework.GlobalState.n("DynamicResourcePrice doesn't work on: " + am2.c());
            return;
        }
        UnitType y2 = (UnitType)am2;
        int n2 = this.a.a;
        Object[] objectArray = this.a.a();
        for (int i = 0; i < n2; ++i) {
            d d2 = (d)objectArray[i];  // 02b d var6 (ResourceFlow 为幻觉)
            double d3 = d2.changeRate != null ? (double)d2.changeRate.readNumber(y2) : d2.maxAmount;  // 02b d.c/d.b
            d2.currentAmount.a(y2, d3);  // 02b d.a
        }
        this.f(y2);  // 02b L158: this.f(var2) (int4 字段当方法)
        CustomActionBase.d(y2);
    }


    public void a(UnitInstance am2) {  // 02b d/c.java L163: a(am) 扣费 (覆盖 CommandSlotBase 抽象)
        this.a(am2, 1.0);  // 02b L164: this.a(var1, 1.0D)  // 02b L164: this.a(var1, 1.0D)
    }


    public void a(UnitInstance am2, double d2) {  // 02b d/c.java L167: a(am,double) 扣费
        if (!(am2 instanceof UnitType)) {
            com.corrodinggames.rts.gameFramework.GlobalState.n("DynamicResourcePrice doesn't work on: " + am2.c());
            return;
        }
        UnitType y2 = (UnitType)am2;
        int n2 = this.a.a;
        Object[] objectArray = this.a.a();
        for (int i = 0; i < n2; ++i) {
            d d3 = (d)objectArray[i];  // 02b d var8
            double d4 = d3.changeRate != null ? (double)d3.changeRate.readNumber(y2) : d3.maxAmount;  // 02b d.c/d.b
            d3.currentAmount.b(y2, -d4 * d2);  // 02b d.a
        }
        this.f(y2);  // 02b L187: this.f(var4)
        CustomActionBase.d(y2);
    }

    public void reset(UnitInstance am2) {
        if (!(am2 instanceof UnitType)) {
            com.corrodinggames.rts.gameFramework.GlobalState.n("DynamicResourcePrice doesn't work on: " + am2.c());
            return;
        }
        UnitType y2 = (UnitType)am2;
        int n2 = this.a.a;
        Object[] objectArray = this.a.a();
        for (int i = 0; i < n2; ++i) {
            d d2 = (d)objectArray[i];  // 02b d var6 (ResourceFlow 为幻觉)
            double d3 = d2.changeRate != null ? (double)d2.changeRate.readNumber(y2) : d2.maxAmount;  // 02b d.c/d.b
            d2.currentAmount.b(y2, d3);  // 02b d.a
        }
        this.f(y2);  // 02b L212: this.f(var2)
        CustomActionBase.d(y2);
    }

    public void f(UnitInstance am2) {
        if (this.d != 0) {
            am2.cF &= ~this.d;
        }
        if (this.storageLimit != 0) {
            am2.cF |= this.storageLimit;
        }
    }

    public boolean isEnabled(UnitInstance am2) {
        if (this.e != 0 && !hasint(am2.cF, this.e)) {  // 02b L229: a(var1.cF,this.e)
            return false;
        }
        return this.int4 == 0 || !hasint4(am2.cF, this.int4);  // 02b L230: b(var1.cF,this.f)
    }

    public static boolean hasint(int n2, int n3) {
        return (n3 & n2) == n3;
    }

    public static boolean hasint4(int n2, int n3) {
        return (n3 & n2) != 0;
    }
}
