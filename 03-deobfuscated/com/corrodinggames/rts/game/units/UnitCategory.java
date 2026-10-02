/*
 * v19.133f46 整写: 02b game/units/n.java 直译 (单位分类枚举 a-f)
 * 修复: CFR extends Enum 非法语法 → 标准 enum; 常量匿名子类内联 (删 $1-$6);
 *       getName/isAvailable 误译 → a()/b()/a(boolean)/a(int,int) (02b n.java L20-34 铁证);
 *       h.L() 裸名 → Factory.L(); 抽象方法 a(UnitTypeHandle) 还原 ($N 已用 a 名)
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.Locale;

public strictfp enum UnitCategory {

    a("all", 0) {
        public boolean a(UnitTypeHandle as2) {
            return true;
        }
    },
    b("types", 1) {
        public boolean a(UnitTypeHandle as2) {
            Factory h2 = Factory.L();
            if (h2 != null && h2.F != null) {
                return h2.F.a(as2);
            }
            return false;
        }
    },
    c("terrain", 2) {
        public boolean a(UnitTypeHandle as2) {
            return false;
        }
        public boolean b() {
            return false;
        }
    },
    d("modded", 3) {
        public boolean a(UnitTypeHandle as2) {
            if (as2 == null) {
                return false;
            }
            if (as2 instanceof ModUnitRegistry) {
                ModUnitRegistry l2 = (ModUnitRegistry) as2;
                if (l2.J == null) {
                    return false;
                }
                Factory h2 = Factory.L();
                return h2 == null || h2.E == null || l2.J == h2.E;
            }
            return false;
        }
    },
    e("search", 4) {
        public boolean a(UnitTypeHandle as2) {
            Factory h2 = Factory.L();
            if (h2 == null) {
                return false;
            }
            if (h2.H == null) {
                return false;
            }
            if (h2.I) {
                h2.I = false;
                h2.J = h2.H.toLowerCase().trim();
            }
            if (as2 == null) {
                return false;
            }
            if (as2.i() != null && as2.i().toLowerCase(Locale.ROOT).contains(h2.J)) {
                return true;
            }
            return as2.i() != null && as2.e().toLowerCase(Locale.ROOT).contains(h2.J);
        }
        public boolean b() {
            Factory h2 = Factory.L();
            if (h2 == null) {
                return false;
            }
            return h2.H != null;
        }
    },
    f("actions", 5) {
        public boolean a(UnitTypeHandle as2) {
            return as2 == null;
        }
    };

    // $FF: synthetic field
    private static final UnitCategory[] g = new UnitCategory[]{a, b, c, d, e, f};

    private UnitCategory(String var1, int var2) {}

    public abstract boolean a(UnitTypeHandle var1);

    public String a() {
        return this.name();
    }

    public boolean b() {
        return true;
    }

    public UnitCategory a(boolean bl) {
        return !bl ? this.a(1, 0) : this.a(-1, 0);
    }

    public UnitCategory a(int n2, int n3) {
        int n4 = this.ordinal() + n2;
        n4 %= values().length;
        if (n4 < 0) {
            n4 += values().length;
        }
        UnitCategory n5 = values()[n4];
        if (!n5.b()) {
            if (n3 > 30) {
                GlobalState.e("jumpBy recursion limit hit");
                return n5;
            }
            n5 = n5.a(n2, n3 + 1);
        }
        return n5;
    }
}
