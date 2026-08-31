/*
 * v19.115p 批5 重建: 02b custom/a/a/l.java (队伍标签动作) 63 行直译
 * 类型映射: h=UnitConfig, bX=player(PlayerState)
 * 依赖补缺: CustomUnitType.j(boolean)/b(UnitConfig); (a(UnitConfig) 已有)
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class l
extends CustomActionBase {
    boolean a;
    UnitConfig b;
    UnitConfig c;
    UnitConfig d;
    UnitConfig e;

    public static void a(ModUnitRegistry l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.actions.d d2, String string3, boolean bl2) {
        // 02b L17-38: 临时标签/全局队伍标签 解析
        boolean var7 = ab2.a(string, string2 + "resetToDefaultTags", Boolean.valueOf(false)).booleanValue();
        UnitConfig var8 = ab2.a(l2, string, string2 + "temporarilyAddTags", (UnitConfig)null);
        UnitConfig var9 = ab2.a(l2, string, string2 + "temporarilyRemoveTags", (UnitConfig)null);
        if (var7 || var8 != null || var9 != null) {
            l l2_ = new l();
            l2_.a = var7;
            l2_.b = var8;
            l2_.c = var9;
            d2.ac.add(l2_);
        }
        UnitConfig var13 = ab2.a(l2, string, string2 + "addGlobalTeamTags", (UnitConfig)null);
        UnitConfig var11 = ab2.a(l2, string, string2 + "removeGlobalTeamTags", (UnitConfig)null);
        if (var13 != null || var11 != null) {
            l l3 = new l();
            l3.d = var13;
            l3.e = var11;
            d2.ac.add(l3);
        }
    }

    @Override
    public boolean a(CustomUnitType j2, GameAction s2, PointF pointF, UnitInstance am2, int n2) {
        // 02b L40-62: 应用标签修改
        if (this.a) {
            j2.j(false);  // 02b: j.j(boolean) 重置默认标签
        }
        if (this.c != null) {
            j2.b(this.c);  // 02b: j.b(h) 移除临时标签
        }
        if (this.b != null) {
            j2.a(this.b);  // 02b: j.a(h) 添加临时标签
        }
        if (this.d != null) {
            j2.player.b(this.d);  // 02b: bX.b(h) 全局添加队伍标签
        }
        if (this.e != null) {
            j2.player.c(this.e);  // 02b: bX.c(h) 全局移除队伍标签
        }
        return true;
    }
}
