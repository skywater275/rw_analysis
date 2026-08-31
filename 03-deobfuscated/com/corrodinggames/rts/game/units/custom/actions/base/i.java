/*
 * v19.115p 批5 重建: 02b custom/a/a/i.java (sendMessage 带数据消息) 54 行直译
 * 类型映射: h=UnitConfig, af=af(enum), LogicBoolean=effects.LogicBoolean,
 *   VariableScope/MemoryWriter=logicBooleans 包
 * 依赖补缺: VariableScope() 无参构造; MemoryWriter.writeToMemory(VariableScope,CustomUnitType);
 *   LogicBoolean.readUnit(CustomUnitType) (02b 字节码 readUnit:(Ly;) 调用点实参为 j)
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriter;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class i
extends CustomActionBase {
    VariableScope$MemoryWriter a;
    LogicBoolean b;
    UnitConfig c;

    public static void a(ModUnitRegistry l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.actions.d d2, String string3, boolean bl2) {
        // 02b L19-36: sendMessageTo/WithData/WithTags 解析
        LogicBoolean var7 = ab2.b(l2, string, string2 + "sendMessageTo", (LogicBoolean)null);
        VariableScope$MemoryWriter var8 = null;
        String var9 = ab2.b(string, string2 + "sendMessageWithData", (String)null);
        if (var9 != null) {
            var8 = VariableScope.createGenericKeyValueWriter(var9, l2, string, string2 + "sendMessageWithData");
        }
        UnitConfig var10 = ab2.a(l2, string, string2 + "sendMessageWithTags", (UnitConfig)null);
        if (var7 != null) {
            i i2 = new i();
            i2.b = var7;
            i2.a = var8;
            i2.c = var10;
            d2.ac.add(i2);
        }
    }

    @Override
    public boolean a(CustomUnitType j2, GameAction s2, PointF pointF, UnitInstance am2, int n2) {
        // 02b L38-53: 读目标单位 → 构建变量域 → 发送消息事件
        if (this.b != null) {
            UnitInstance var6 = this.b.readUnit(j2);
            if (var6 != null) {
                VariableScope var7 = null;
                if (this.a != null) {
                    var7 = new VariableScope();
                    this.a.writeToMemory(var7, j2);
                }
                var6.a(af.q, j2, this.c, var7);  // 02b: am.a(af.q, j, h, VariableScope)
            }
        }
        return true;
    }
}
