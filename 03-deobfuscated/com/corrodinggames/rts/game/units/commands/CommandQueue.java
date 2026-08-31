/*
 * v19.115f: 02b units/d/r.java 静态建造队列查询重建 (简化实现)
 * 锚点: 02b-decompiled/.../units/d/r.java L98-114 (a(y,float,float,boolean) 返回 au)
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.WeaponAction;

public class CommandQueue {
    public static WeaponAction a(UnitType y2, float f2, float f3, boolean bl) {  // 02b d.r.a(y,f,f,b): 获取建造武器
        WeaponAction au2 = y2.ao();
        if (au2 != null) {
            au2.k = f3;
            au2.m = true;
            return au2;
        }
        return null;
    }
}
