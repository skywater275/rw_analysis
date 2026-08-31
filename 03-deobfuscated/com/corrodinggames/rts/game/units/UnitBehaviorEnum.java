/*
 * v19.133f46 整写: 02b game/units/o.java 直译 (单位行为枚举 a-e: land/air/sea/buildings/bio)
 * 修复: CFR extends Enum 非法语法 → 标准 enum; 常量匿名子类内联 (删 $1-$5);
 *       getName/isAvailable 误译 → a()/b()/a(boolean)/a(int,int) (02b o.java L19-49 铁证);
 *       am.c → UnitInstance.c (02b am L569); ao.d/e → MovementTypeEnum.d/e (02b units/ao);
 *       UpdateChecker$1 数字污染 → 1 (F25)
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.gameFramework.GlobalState;

public strictfp enum UnitBehaviorEnum {

    a("land", 0) {
        public boolean a(UnitTypeHandle as2) {
            if (as2 == null) {
                return false;
            }
            UnitInstance am2 = UnitInstance.c(as2);
            if (am2.hasSpawnedDeathEffect() || as2.j()) {
                return false;
            }
            return am2.h() != MovementTypeEnum.d && am2.h() != MovementTypeEnum.e;
        }
    },
    b("air", 1) {
        public boolean a(UnitTypeHandle as2) {
            if (as2 == null) {
                return false;
            }
            UnitInstance am2 = UnitInstance.c(as2);
            if (am2.hasSpawnedDeathEffect() || as2.j()) {
                return false;
            }
            return am2.h() == MovementTypeEnum.d;
        }
    },
    c("sea", 2) {
        public boolean a(UnitTypeHandle as2) {
            if (as2 == null) {
                return false;
            }
            UnitInstance am2 = UnitInstance.c(as2);
            if (am2.hasSpawnedDeathEffect() || as2.j()) {
                return false;
            }
            return am2.h() == MovementTypeEnum.e;
        }
    },
    d("buildings", 3) {
        public boolean a(UnitTypeHandle as2) {
            if (as2 == null) {
                return false;
            }
            UnitInstance am2 = UnitInstance.c(as2);
            return !am2.hasSpawnedDeathEffect() && as2.j();
        }
    },
    e("bio", 4) {
        public boolean a(UnitTypeHandle as2) {
            if (as2 == null) {
                return false;
            }
            UnitInstance am2 = UnitInstance.c(as2);
            return am2.hasSpawnedDeathEffect();
        }
    };

    // $FF: synthetic field
    private static final UnitBehaviorEnum[] f = new UnitBehaviorEnum[]{a, b, c, d, e};

    private UnitBehaviorEnum(String var1, int var2) {}

    public abstract boolean a(UnitTypeHandle var1);

    public String a() {
        return this.name();
    }

    public UnitBehaviorEnum a(boolean bl) {
        return !bl ? this.a(1, 0) : this.a(-1, 0);
    }

    public UnitBehaviorEnum a(int n2, int n3) {
        int n4 = this.ordinal() + n2;
        n4 %= values().length;
        if (n4 < 0) {
            n4 += values().length;
        }
        UnitBehaviorEnum n5 = values()[n4];
        if (!n5.b()) {
            if (n3 > 30) {
                GlobalState.e("jumpBy recursion limit hit");
                return n5;
            }
            n5 = n5.a(n2, n3 + 1);
        }
        return n5;
    }

    public boolean b() {
        return true;
    }
}
