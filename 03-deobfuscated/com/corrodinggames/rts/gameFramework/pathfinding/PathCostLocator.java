/*
 * 02b k/c.java 直译: 寻路代价定位抽象类 (PathCostCalc.a 字段类型; h.java 父类)
 */
package com.corrodinggames.rts.gameFramework.pathfinding;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTransform;

public abstract class PathCostLocator {
    public abstract UnitTransform a(UnitInstance am2);  // 02b: af a(am)

    public abstract UnitTransform b(UnitInstance am2);  // 02b: af b(am)

    public abstract void c(UnitInstance am2);  // 02b: void c(am)

    public abstract void d(UnitInstance am2);  // 02b: void d(am)
}
