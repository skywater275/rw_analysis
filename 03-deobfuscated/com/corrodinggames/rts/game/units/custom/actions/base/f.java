/*
 * v19.115p 批5 重建: 02b custom/a/a/f.java (消息动作) 81 行直译
 * 类型映射: ag=ModLoader, aj=aj(保持原名), ad=NetEngine, l(gf)=GlobalState,
 *   bS.i=InGameUI.i(WaypointManager), j=CustomUnitType, dt()=ModUnitRegistry
 * 依赖补缺: NetEngine.a(String,String)→registerRelayServer; ModUnitRegistry.i() 无参; PlayerState.c(PlayerState) 实例
 * v19.132w: 调用点 NetEngine.a → registerRelayServer (02b j/ad L1214 a(String,String) 铁证)
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.regex.Pattern;

public class f
extends CustomActionBase {
    aj a;
    aj b;
    aj c;
    aj d;
    aj e;
    aj f;
    static final Pattern g = Pattern.compile("%\\{([^\\]]*?)\\}");

    public static void a(ModUnitRegistry l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.actions.d d2, String string3, boolean bl2) throws bo {
        // 02b L23-41: showMessageToPlayer 等 6 个消息解析
        aj var7 = ModLoader.a(l2, ab2, string, "showMessageToPlayer", (String)null);
        aj var8 = ModLoader.a(l2, ab2, string, "showMessageToAllPlayers", (String)null);
        aj var9 = ModLoader.a(l2, ab2, string, "showMessageToAllEnemyPlayers", (String)null);
        aj var10 = ModLoader.a(l2, ab2, string, "showQuickWarLogToPlayer", (String)null);
        aj var11 = ModLoader.a(l2, ab2, string, "showQuickWarLogToAllPlayers", (String)null);
        aj var12 = ModLoader.a(l2, ab2, string, "debugMessage", (String)null);
        if (var7 != null || var8 != null || var9 != null || var10 != null || var11 != null || var12 != null) {
            f f2 = new f();
            f2.a = var7;
            f2.b = var8;
            f2.c = var9;
            f2.d = var10;
            f2.e = var11;
            f2.f = var12;
            d2.ac.add(f2);
        }
    }

    public String a(CustomUnitType j2, String string) {
        // 02b L43-49: 原文直译 (var2==null → null, 无操作)
        if (string == null) {
            string = null;
        }
        return string;
    }

    @Override
    public boolean a(CustomUnitType j2, GameAction s2, PointF pointF, UnitInstance am2, int n2) {
        // 02b L51-79: 按玩家条件发送消息/战争日志
        GlobalState l2 = GlobalState.B();
        if (this.a != null && j2.player == l2.bs) {
            NetEngine.registerRelayServer((String)null, this.a(j2, this.a.b(j2)));
        }
        if (this.b != null) {
            NetEngine.registerRelayServer((String)null, this.a(j2, this.b.b(j2)));
        }
        if (this.c != null && l2.bs != null && j2.player.c(l2.bs)) {
            NetEngine.registerRelayServer((String)null, this.a(j2, this.c.b(j2)));
        }
        if (this.d != null && j2.player == l2.bs) {
            l2.bS.i.a(this.a(j2, this.d.b(j2)));
        }
        if (this.e != null) {
            l2.bS.i.a(this.a(j2, this.e.b(j2)));
        }
        if (this.f != null && l2.bv && l2.bl) {
            String string = j2.dt().i() + "(" + j2.eh + ") Debug: " + this.a(j2, this.f.b(j2));
            NetEngine.registerRelayServer((String)null, string);
        }
        return true;
    }
}
