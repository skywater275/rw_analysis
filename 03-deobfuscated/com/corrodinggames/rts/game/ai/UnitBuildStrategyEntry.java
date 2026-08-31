/*
 * v19.133f53 整写: 02b game/a/e.java 直译 (UnitBuildStrategy 条目, 3 字段+构造)
 * 修复: c 字段 NeutralPlayer→UnitBuildStrategy (02b L11 final d c);
 *       CFR 误拼接方法 a(UnitType)/a(float,float) 删除 (02b e.java 无)
 */
package com.corrodinggames.rts.game.ai;

import com.corrodinggames.rts.game.units.UnitTypeHandle;

public class UnitBuildStrategyEntry {
    public UnitTypeHandle a;
    public float b;
    final /* synthetic */ UnitBuildStrategy c;  // 02b a/e.java L11: final d c

    public UnitBuildStrategyEntry(UnitBuildStrategy d2, UnitTypeHandle as2, float f) {
        this.c = d2;
        this.a = as2;
        this.b = f;
    }
}
