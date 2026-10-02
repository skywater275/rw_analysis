/*
 * v19.133f72 整写: 02b units/g/b.java 直译 (组件类型枚举 movementSpeed/specialActionBlock)
 * 修复: CFR extends Enum 非法语法 → 标准 enum (F84); 常量匿名体内联 (02b b$1/b$2);
 *       CustomGroundUnit 误标清除
 */
package com.corrodinggames.rts.game.units.weapons;

public strictfp enum ComponentType {

    a("movementSpeed", 0) {
        UnitComponent a() {
            return new FloatComponent();
        }
    },
    b("specialActionBlock", 1) {
        UnitComponent a() {
            return new TimerComponent();
        }
    };

    // $FF: synthetic field
    private static final ComponentType[] c = new ComponentType[]{a, b};

    private ComponentType(String var1, int var2) {}

    abstract UnitComponent a();
}
