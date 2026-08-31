package com.corrodinggames.rts.game.units.custom;

// 02b custom/q.java 直译: ModUnitRegistry 名称排序器 (02b ag L1046 引用)
import java.util.Comparator;

class q
implements Comparator {
    public strictfp int a(ModUnitRegistry var1, ModUnitRegistry var2) {
        return var1.M != null && var2.M != null ? var1.M.compareTo(var2.M) : 0;
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((ModUnitRegistry) object, (ModUnitRegistry) object2);
    }
}
