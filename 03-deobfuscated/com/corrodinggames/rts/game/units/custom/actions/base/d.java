/*
 * v19.131 重写: 02b custom/a/a/d.java (播放动画 ActionDef) 直译
 * 类型映射: o=AnimationReference; a/a(父)=base/CustomActionBase; 第5参 a/d=actions.d(UnitActionDef)
 * 原 v19.115o 误建为 UnitActionDef 数据类字段, 与 a/a/d 字段序不符
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import com.corrodinggames.rts.game.units.custom.AnimationReference;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class d
extends CustomActionBase {
    public boolean a;
    public boolean b;
    public AnimationReference c;
    public AnimationReference d;
    public boolean e;
    public int f = Integer.MIN_VALUE;
    public int g = Integer.MIN_VALUE;

    public static void a(ModUnitRegistry l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.actions.d d2, String string3, boolean bl2) {
        boolean bl3 = ab2.a(string, string2 + "finishPlayingLastAnimation", Boolean.valueOf(false)).booleanValue();
        boolean bl4 = ab2.a(string, string2 + "stopLastAnimation", Boolean.valueOf(false)).booleanValue();
        AnimationReference animationReference = l2.a(ab2.b(string, string2 + "playAnimation", (String)null), (AnimationReference)null);
        AnimationReference animationReference2 = l2.a(ab2.b(string, string2 + "playAnimationIfNotPlaying", (String)null), (AnimationReference)null);
        if (animationReference != null && animationReference2 != null) {
            throw new RuntimeException("Cannot use playAnimation and playAnimationIfNotPlaying at same time");
        }
        if (bl4 && bl3) {
            throw new RuntimeException("Cannot use stopLastAnimation and finishPlayingLastAnimation at same time");
        }
        if (animationReference != null || animationReference2 != null || bl3 || bl4) {
            d d3 = new d();
            d3.a = bl3;
            d3.b = bl4;
            d3.c = animationReference;
            d3.d = animationReference2;
            d3.e = ab2.a(string, string2 + "playAnimation_lowPriority", Boolean.valueOf(false)).booleanValue();
            d2.ac.add(d3);
        }
    }
}
