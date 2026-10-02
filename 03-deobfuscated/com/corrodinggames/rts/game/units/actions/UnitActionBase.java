/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;

public class UnitActionBase {
    public static UnitActionBase a = new UnitActionBase();

    public boolean isAffordable(UnitInstance am2) {
        return false;
    }

    public boolean isVisible(UnitInstance am2) {
        return false;
    }

    public String getDisabledReason(UnitInstance am2) {
        return null;
    }

    public boolean isAffordable(UnitInstance am2, boolean bl) {
        return true;
    }

    public boolean isBlocked(UnitInstance am2) {
        return false;
    }

    public CustomActionBase isAffordable() {
        return null;
    }

    public CustomActionBase isVisible() {
        return null;
    }

    public void isAffordable(UnitInstance am2, UnitInstance am3) {
    }

    public void a(UnitInstance am2, UnitInstance am3) {  // 02b a/a.java L38: a(am,am) 空实现
    }

    public boolean a(UnitInstance am2, boolean bl) {  // 02b a/a.java L22: a(am,Z) 默认可用
        return true;
    }

    public CustomActionBase a() {  // 02b a/a.java L30-32: a() 返回资源组件
        return null;
    }

    public CustomActionBase b() {  // 02b a/a.java L34-36: b() 返回资源组件
        return null;
    }
}