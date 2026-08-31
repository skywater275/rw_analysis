/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.resources;


import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;

public class d {   // v19.115m: 02b custom.d.c public (base 包访问)
    public final com.corrodinggames.rts.game.units.custom.effects.LogicBoolean currentAmount;  // 02b d/d.java: custom.e.a = LogicBoolean (L297 注释铁证)
    public double maxAmount;
    public com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean changeRate;  // 02b d/d.java: LogicBoolean (logicBooleans 域)

    public d(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2, com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean logicBoolean) {  // 02b: d(e.a, LogicBoolean)  // 02b d/d.java: d(custom.e.a, LogicBoolean)
        this.currentAmount = a2;  // hprrentAmount 为幻觉字段名
        if (this.changeRate instanceof LogicBoolean$StaticValueBoolean) {
            this.maxAmount = ((LogicBoolean$StaticValueBoolean)this.changeRate).getStaticValue();
        } else {
            this.changeRate = logicBoolean;
        }
    }


   // 02b custom.d.c.d(am)/e(am) 简化: 资源存储操作 (完整体待 resources 战役; CustomActionBase 资源动作使用)
   public void d(com.corrodinggames.rts.game.units.UnitInstance var1) {
   }

   public void e(com.corrodinggames.rts.game.units.UnitInstance var1) {
   }
}
