/*
 * v19.115p 批5 重建: 02b custom/a/a/o.java (运输动作) 114 行直译
 * 类型映射: g=TeamTag(deserializeTags 语义名), h=UnitConfig, bp=bp, e.a=effects.LogicBoolean
 * 依赖补缺: CustomUnitType.C(UnitInstance)/D(UnitInstance)/L();
 *   LogicBoolean.readUnit(CustomUnitType)
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class o
extends CustomActionBase {
    public bp a;
    public int b;
    public UnitConfig c;
    public boolean d;
    public boolean e;
    public int f = -1;
    public LogicBoolean g;

    public static void a(ModUnitRegistry l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.actions.d d2, String string3, boolean bl2) throws bo {
        // 02b L23-53: addUnitsIntoTransport 等解析
        bp var7 = bp.a(l2, ab2, string, string2 + "addUnitsIntoTransport");
        int var8 = ab2.b(string, string2 + "deleteNumUnitsFromTransport", Integer.valueOf(0)).intValue();
        UnitConfig var9 = TeamTag.deserializeTags(ab2.b(string, "deleteNumUnitsFromTransport_onlyWithTags", (String)null), (UnitConfig)null);  // 02b: g.a(String,h)
        boolean var10 = ab2.a(string, string2 + "startUnloadingTransport", Boolean.valueOf(false)).booleanValue();
        boolean var11 = ab2.a(string, string2 + "forceUnloadTransportNow", Boolean.valueOf(false)).booleanValue();
        int var12 = ab2.b(string, string2 + "forceUnloadTransportNow_onlyOnSlot", Integer.valueOf(-1)).intValue();
        LogicBoolean var13 = ab2.b(l2, string, string2 + "transportTargetNow", (LogicBoolean)null);
        if (var12 != -1 && !var11) {
            throw new bo("forceUnloadTransportNow_onlyOnSlot expects forceUnloadTransportNow");
        }
        if (!var7.b() || var8 > 0 || var10 || var11 || var13 != null) {
            o o2 = new o();
            if (!var7.b()) {
                o2.a = var7;
            }
            if (var8 > 0) {
                o2.b = var8;
                o2.c = var9;
            }
            o2.d = var10;
            o2.e = var11;
            o2.f = var12;
            o2.g = var13;
            d2.ac.add(o2);
        }
    }

    @Override
    public boolean a(CustomUnitType j2, GameAction s2, PointF pointF, UnitInstance am2, int n2) {
        // 02b L55-113: 删除单位/装载/卸载/目标跳转
        int i2;
        UnitInstance var8;
        if (this.b != 0) {
            for (i2 = 0; i2 < this.b; ++i2) {
                if (j2.B.size() > 0) {
                    for (int i3 = j2.B.size() - 1; i3 >= 0; --i3) {
                        var8 = (UnitInstance)j2.B.get(i3);
                        if (var8 == null) {
                            GlobalState.b("deleteNumUnitsFromTransport unit==null");
                        } else if (this.c == null || TeamTag.deserializeTags(this.c, var8.de())) {  // 02b: g.a(h,h)
                            j2.B.remove(i3);
                            j2.D(var8);  // 02b: j.D(am)
                            if (var8 != null) {
                                var8.canBuild();  // 02b: var8.ci()
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (this.a != null) {
            CustomArrayList m2 = new CustomArrayList();
            this.a.a(m2, j2.player, j2, false);
            for (Object object : m2) {
                UnitInstance var9 = (UnitInstance)object;
                var8 = var9;
                var8.eo = j2.eo;
                var8.ep = j2.ep;
                var8.eq = j2.eq;
                j2.C(var8);  // 02b: j.C(am)
            }
        }
        if (this.d) {
            j2.L();  // 02b: j.L() 开始卸载
        }
        if (this.e) {
            for (i2 = j2.B.size() - 1; i2 >= 0; --i2) {
                if (this.f == -1 || this.f == i2) {
                    boolean bl2 = j2.B.size() % 2 == 0;
                    j2.a((UnitInstance)j2.B.get(i2), true, bl2);
                }
            }
        }
        if (this.g != null) {
            UnitInstance var11 = this.g.readUnit(j2);
            if (var11 != null && var11.bL && j2.d(var11, true)) {
                j2.C(j2);  // 02b: j.C(j) 跳转
            }
        }
        return true;
    }
}
