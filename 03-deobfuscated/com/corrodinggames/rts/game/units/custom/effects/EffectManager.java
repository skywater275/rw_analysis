/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.DirectionConfig;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.effects.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.effects.DataValue;
import com.corrodinggames.rts.game.units.custom.effects.ActionType$1;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.Collections;

public strictfp final class EffectManager {
    public static final EffectManager a = new EffectManager().a();
    public final CustomArrayList b = new CustomArrayList();  // 02b e/f.b = utility.m (CustomArrayList)
    boolean c;

    public EffectManager a() {
        this.c = true;
        return this;
    }

    public static EffectManager d(EffectManager f2) {  // 02b e/f.java: d(f) 克隆 (v19.133d)
        EffectManager f3 = new EffectManager();
        f3.b(f2);
        return f3;
    }


    public void do_b() {
        this.b.clear();
    }

    public boolean c() {
        if (this.b.a == 0) {
            return true;
        }
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (e2.amountValue == 0.0) continue;
            return false;
        }
        return true;
    }

    public double a(LogicBoolean a2) {
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (e2.resourceTypeRef != a2) continue;
            return e2.amountValue;
        }
        return 0.0;
    }

    public double do_b(LogicBoolean a2) {
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (e2.resourceTypeRef == a2) {
                n2 = (int)((double)n2 + e2.amountValue);
            }
            if (e2.resourceTypeRef.v != a2) continue;
            n2 = (int)((double)n2 + e2.amountValue);
        }
        return n2;
    }

    public void a(EffectManager f2) {
        this.do_b();
        this.do_b(f2);
    }

    public void a(LogicBoolean a2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (e2.resourceTypeRef != a2) continue;
            e2.amountValue = d;
            return;
        }
        DataValue e3 = new DataValue(a2);
        e3.amountValue = d;
        this.b.add(e3);
    }

    public void a(double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            e2.amountValue *= d;
        }
    }

    public void do_b(LogicBoolean a2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        if (d == 0.0) {
            return;
        }
        CustomArrayList m2 = this.b;
        int n = m2.a;
        Object[] objectArray = m2.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (e2.resourceTypeRef != a2) continue;
            e2.amountValue += d;
            return;
        }
        DataValue e3 = new DataValue(a2);
        e3.amountValue = d;
        m2.add(e3);
    }

    public void c(LogicBoolean a2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        if (d == 0.0) {
            return;
        }
        CustomArrayList m2 = this.b;
        int n = m2.a;
        Object[] objectArray = m2.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (e2.resourceTypeRef != a2) continue;
            e2.amountValue += d;
            return;
        }
        DataValue e3 = new DataValue(a2);
        e3.amountValue = d;
        m2.add(e3);
    }

    public void d(LogicBoolean a2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        this.do_b(a2, -d);
    }

    public void a(CustomActionBase b2, double d, double d2) {
        if ((double)b2.b >= d && (double)b2.b <= d2) {
            this.c(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.D, b2.b);
        }
        this.a(b2.k, d, d2);
    }

    public void do_b(CustomActionBase b2, double d, double d2) {
        if ((double)b2.b >= d && (double)b2.b <= d2) {
            this.c(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.D, -b2.b);
        }
        this.do_b(b2.k, d, d2);
    }

    public void a(CustomActionBase b2) {  // 02b e/f.java L189: 添加自定义动作资源成本
        this.c(LogicBoolean.D, (double) b2.b);
        this.b(b2.k);
    }

    public void b(EffectManager f2) {  // 02b e/f.java L193: 合并资源 (锁定检查)
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        for (Object object : f2.b) {
            e e2 = (e) object;
            this.a(e2.a, e2.b);
        }
    }

    public void a(ResourceComponent b2) {
        this.c(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.D, b2.b);
        this.do_b(b2.customResources);
    }

    public void do_b(EffectManager f2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            this.do_b(e2.resourceTypeRef, e2.amountValue);
        }
    }

    public void a(EffectManager f2, double d, double d2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (!(e2.amountValue >= d) || !(e2.amountValue <= d2)) continue;
            this.do_b(e2.resourceTypeRef, e2.amountValue);
        }
    }

    public void a(EffectManager f2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            this.do_b(e2.resourceTypeRef, e2.amountValue * d);
        }
    }

    public void c(EffectManager f2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            this.d(e2.resourceTypeRef, e2.amountValue);
        }
    }

    // ===== 02b e/f.java 静态方法族 (L273-467) 简化直译: 资源效果聚合/检查/应用 =====
    public static EffectManager a(EffectManager f2, EffectManager f3) {  // 02b L273: a(f,f)
        EffectManager b = new EffectManager();
        b.do_b(f2);
        b.do_b(f3);
        return b;
    }

    public static EffectManager b(EffectManager f2, double d2) {  // 02b L287: b(f,double)
        EffectManager b = new EffectManager();
        b.a(f2, d2);
        return b;
    }

    public static EffectManager b(EffectManager f2, EffectManager f3) {  // 02b L280: b(f,f) 组合(资源合并)
        EffectManager b = new EffectManager();
        b.do_b(f2);
        b.c(f3);
        return b;
    }

    public static int a(EffectManager f2, UnitInstance am2) {  // 02b L299: a(f,am) 可购买次数
        int n = 9999;
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (!(e2.amountValue > 0.0)) continue;
            double d2 = e2.resourceTypeRef.isGlobalResource ? am2.player.c(e2.resourceTypeRef) : am2.player.c(e2.resourceTypeRef);  // TODO: 02b 单位侧 am.a(a) 缺失简化
            n = com.corrodinggames.rts.gameFramework.GameUtils.c(n, (int)(d2 / e2.amountValue));
        }
        return n;
    }

    public static boolean b(EffectManager f2, UnitInstance am2) {  // 02b L322: b(f,am) 资源检查
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            double d2 = e2.resourceTypeRef.isGlobalResource ? am2.player.c(e2.resourceTypeRef) : am2.player.c(e2.resourceTypeRef);  // TODO: 单位侧简化
            if (e2.amountValue > d2) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(EffectManager f2, UnitInstance am2, double d2) {  // 02b L343: a(f,am,double) 比例资源检查
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            double d3 = e2.resourceTypeRef.isGlobalResource ? am2.player.c(e2.resourceTypeRef) : am2.player.c(e2.resourceTypeRef);  // TODO: 单位侧简化
            if (e2.amountValue * d2 > d3) {
                return false;
            }
        }
        return true;
    }

    public static void c(EffectManager f2, UnitInstance am2) {  // 02b L364: c(f,am) 应用效果
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            EffectManager f3 = e2.resourceTypeRef.isGlobalResource ? am2.player.V() : am2.player.V();  // TODO: 02b 单位侧 am.df() 缺失简化
            f3.do_b(e2.resourceTypeRef, e2.amountValue);
        }
    }

    public static void d(EffectManager f2, UnitInstance am2) {  // 02b L400: d(f,am) 扣除效果
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            EffectManager f3 = e2.resourceTypeRef.isGlobalResource ? am2.player.V() : am2.player.V();  // TODO: 单位侧简化
            f3.do_b(e2.resourceTypeRef, e2.amountValue);
        }
    }

    public static void c(EffectManager f2, UnitInstance am2, double d2) {  // 02b L418: c(f,am,double) 比例扣除
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            EffectManager f3 = e2.resourceTypeRef.isGlobalResource ? am2.player.V() : am2.player.V();  // TODO: 单位侧简化
            f3.do_b(e2.resourceTypeRef, e2.amountValue * d2);
        }
    }
    public static void do_b(EffectManager f2, UnitInstance am2, double d2) {  // 02b L382: b(f,am,double) 扣除效果
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            EffectManager f3;
            if (e2.resourceTypeRef.t) {
                f3 = am2.player.V();
                f3.d(e2.resourceTypeRef, e2.amountValue * d2);
            } else {
                f3 = am2.getDefaultStatModifiers();
                f3.d(e2.resourceTypeRef, e2.amountValue * d2);
            }
        }
    }

    public static boolean a(EffectManager f2, UnitInstance am2, UnitInstance am3) {  // 02b L436: a(f,am,am) 资源转移
        boolean bl = false;
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            LogicBoolean a2 = e2.resourceTypeRef;
            double d2 = e2.amountValue;
            if (d2 != 0.0) {
                double d3 = a2.a(am2);
                double d4 = a2.a(am3);
                if (d2 >= 0.0) {
                    if (d3 > 0.0) {
                        double d5 = com.corrodinggames.rts.gameFramework.GameUtils.a(d3, d2);
                        a2.b(am2, -d5);
                        a2.b(am3, d5);
                        bl = true;
                    }
                } else if (d4 > 0.0) {
                    d2 = -d2;
                    double d6 = com.corrodinggames.rts.gameFramework.GameUtils.a(d4, d2);
                    a2.b(am3, -d6);
                    a2.b(am2, d6);
                    bl = true;
                }
            }
        }
        return bl;
    }

    public void do_b(EffectManager f2, double d, double d2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (!(e2.amountValue >= d) || !(e2.amountValue <= d2)) continue;
            this.d(e2.resourceTypeRef, e2.amountValue);
        }
    }

    public String a(boolean bl, boolean bl2, int n2, boolean bl3, boolean bl4) {
        ThemeColors ae2 = new ThemeColors();
        this.a(ae2, bl, bl2, n2, bl3, bl4, null, 0);
        return ae2.a();
    }

    public void a(ThemeColors ae2, boolean bl, boolean bl2, int n2, boolean bl3, boolean bl4, UnitInstance am2, int n3) {
        int n4 = this.b.a;
        if (n4 == 0) {
            return;
        }
        String string = bl ? "\n" : " | ";
        int n5 = 0;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n4; ++i) {
            double d;
            DataValue e2 = (DataValue) objectArray[i];
            if (!(e2.amountValue > 0.0) && !bl4 || n5 >= n2) continue;
            LogicBoolean a2 = e2.resourceTypeRef;
            if (!bl3 && a2.a()) continue;
            boolean bl5 = false;
            if (a2.y != null && a2.z) {
                bl5 = true;
                int n6 = ae2.c() - 2;
                if (n6 < 2) {
                    n6 = 2;
                }
                ae2.a(a2.y, n6 * 3, n6);
            }
            String string2 = a2.a(e2.amountValue, false, bl5) + string;
            boolean bl6 = false;
            int n7 = 0;
            if (a2.m != null && a2.n) {
                bl6 = true;
                n7 = a2.m;
            }
            if (am2 != null && (d = a2.a(am2)) < e2.amountValue) {
                bl6 = true;
                n7 = n3;
            }
            if (bl6) {
                ae2.a(string2, n7);
            } else {
                ae2.b(string2);
            }
            ++n5;
        }
    }

    public void a(OutputNetStream as2) {
        if (this.b.a == 0) {
            as2.c(-1);
            return;
        }
        as2.c(0);
        as2.a((short)this.b.a);
        int n2 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n2; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            as2.c(e2.resourceTypeRef.b);
            as2.a(e2.amountValue);
        }
    }

    public void a(InputNetStream k2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        byte by = k2.d();
        if (by == -1) {
            return;
        }
        int n2 = k2.v();
        this.b.clear();
        for (int i = 0; i < n2; ++i) {
            LogicBoolean a2 = com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.b(k2.readString());
            double d = k2.h();
            if (a2 == null || d == 0.0) continue;
            DataValue e2 = new DataValue(a2, d);
            this.b.add(e2);
        }
    }

    public int d() {
        int n2 = 0;
        int n3 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n3; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (e2.amountValue == 0.0) continue;
            ++n2;
        }
        return n2;
    }

    public boolean e(EffectManager f2) {
        if (this.d() != f2.d()) {
            return false;
        }
        int n2 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n2; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            double d = f2.a(e2.resourceTypeRef);
            if (com.corrodinggames.rts.gameFramework.GameUtils.b(e2.amountValue, d)) continue;
            return false;
        }
        return true;
    }

    public boolean f(EffectManager f2) {
        int n2 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n2; ++i) {
            double d;
            DataValue e2 = (DataValue) objectArray[i];
            if (!(e2.amountValue > 0.0) || !((d = f2.do_b(e2.resourceTypeRef)) > 0.0)) continue;
            return true;
        }
        return false;
    }

    public EffectManager a(UnitInstance am2) {
        EffectManager f2 = new EffectManager();
        int n2 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n2; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            double d = e2.resourceTypeRef.t ? am2.player.c(e2.resourceTypeRef) : am2.player.c(e2.resourceTypeRef);
            if (!(d < e2.amountValue)) continue;
            double d2 = e2.amountValue - d;
            f2.do_b(e2.resourceTypeRef, d2);
        }
        if (f2.c()) {
            return a;
        }
        return f2;
    }

    public String a(UnitInstance am2, String string, int n2, boolean bl) {
        String string2 = null;
        int n3 = 0;
        int n4 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n4; ++i) {
            double d;
            DataValue e2 = (DataValue) objectArray[i];
            if (!bl && e2.resourceTypeRef.a() || !((d = e2.resourceTypeRef.t ? am2.player.c(e2.resourceTypeRef) : am2.player.c(e2.resourceTypeRef)) < e2.amountValue)) continue;
            double d2 = e2.amountValue - d;
            String string3 = e2.resourceTypeRef.i();
            string2 = string2 == null ? string3 : string2 + string + string3;
            if (++n3 > n2) break;
        }
        return string2;
    }

    public void g(EffectManager f2) {
        this.do_b();
        this.do_b(f2);
    }

    public void c(LogicBoolean a2) {
        CustomArrayList m2 = this.b;
        int n2 = m2.a;
        Object[] objectArray = m2.a();
        for (int i = 0; i < n2; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (e2.resourceTypeRef != a2) continue;
            return;
        }
        DataValue e3 = new DataValue(a2);
        e3.amountValue = 0.0;
        m2.add(e3);
    }

    public void e() {
        Collections.sort(this.b, new EffectManager$1(this));
    }

    public void b(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2, double d2) {  // 02b e/f.java b(a,double): 累加资源值
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        if (d2 == 0.0D) {
            return;
        }
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList customArrayList = this.b;
        int n2 = customArrayList.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.custom.effects.e e3 = (com.corrodinggames.rts.game.units.custom.effects.e)customArrayList.get(i2);
            if (e3.a != a2) continue;
            e3.b += d2;
            return;
        }
        com.corrodinggames.rts.game.units.custom.effects.e e4 = new com.corrodinggames.rts.game.units.custom.effects.e(a2);
        e4.b = d2;
        customArrayList.add(e4);
    }

}
