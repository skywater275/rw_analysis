/*
 * v19.115p 批5 重建: 02b custom/a/a/e.java (attachments 附挂动作) 200 行直译
 * 类型映射: j=CustomUnitType, l=ModUnitRegistry, ab=KeyValue解析器, d=ActionValidator(本包),
 *   b.n=animation.UnitTrait(附挂槽位), y=UnitType, ak=UnitShield, m=CustomArrayList,
 *   s=GameAction, h=UnitConfig, bp=bp(生成单位列表), l(gf)=GlobalState
 * 依赖补缺: ModUnitRegistry.i(String)→UnitTrait / aA 字段; CustomUnitType.a(UnitTrait);
 *   bp.a(CustomArrayList,PlayerState,CustomUnitType,boolean) 签名修复 (02b 字节码铁证)
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.animation.UnitTrait;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.ArrayList;

public class e
extends CustomActionBase {
    public bp a;
    public ArrayList b;
    public int c;
    public boolean d;
    public boolean e;
    public boolean f;

    public static void a(ModUnitRegistry l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.actions.d d2, String string3, boolean bl2) throws bo {
        // 02b L24-64: attachments_addNewUnits 解析
        bp bp2 = bp.a(l2, ab2, string, string2 + "attachments_addNewUnits");
        int n2 = ab2.b(string, string2 + "attachments_deleteNumUnits", Integer.valueOf(0)).intValue();
        boolean bl3 = ab2.a(string, string2 + "attachments_disconnect", Boolean.valueOf(false)).booleanValue();
        boolean bl4 = ab2.a(string, string2 + "attachments_unload", Boolean.valueOf(false)).booleanValue();
        boolean bl5 = ab2.a(string, string2 + "disconnectFromParent", Boolean.valueOf(false)).booleanValue();
        if (!bp2.b() || n2 != 0 || bl5 || bl3 || bl4) {
            e e2 = new e();
            e2.a = bp2;
            String string4 = ab2.b(string, "attachments_onlyOnSlots", (String)null);
            if (string4 != null) {
                String[] stringArray = string4.split(",");
                for (String string5 : stringArray) {
                    string5 = string5.trim();
                    if (string5.equals("")) {
                        continue;
                    }
                    UnitTrait n3 = l2.i(string5);  // 02b: var0.i(var18) → custom.b.n (附挂槽位)
                    if (e2.b == null) {
                        e2.b = new ArrayList();
                    }
                    if (n3 == null) {
                        throw new bo("[" + string + "]attachments_onlyOnSlots: Could not find attachment slot with name: " + string5);
                    }
                    e2.b.add(n3);
                }
            }
            e2.c = n2;
            e2.f = bl5;
            e2.d = bl3;
            e2.e = bl4;
            d2.ac.add(e2);
        }
    }

    @Override
    public boolean a(CustomUnitType j2, GameAction s2, PointF pointF, UnitInstance am2, int n2) {
        // 02b L66-199: 解除附挂 + 删除单位 + 附挂新单位 + 断开父级
        int n3;
        boolean bl2;
        if ((this.d || this.e) && j2.C != null && j2.C.size() > 0) {
            for (n3 = j2.C.size() - 1; n3 >= 0; --n3) {
                UnitInstance am3 = (UnitInstance)j2.C.get(n3);
                if (am3 == null) {
                    continue;
                }
                if (this.b != null) {
                    bl2 = false;
                    for (Object object : this.b) {
                        if (((UnitTrait)object).a() != n3) {
                            continue;
                        }
                        bl2 = true;
                        break;
                    }
                    if (!bl2) {
                        continue;
                    }
                }
                if (!(am3 instanceof UnitType)) {
                    GlobalState.e("Failed to deattach unit:" + am3.r().i() + " is not an OrderableUnit");
                    continue;
                }
                UnitType y2 = (UnitType)am3;
                if (this.e) {
                    boolean bl3 = j2.B.size() % 2 == 0;
                    j2.a((UnitInstance)y2, true, bl3);
                    break;
                }
                y2.bx();
                break;
            }
        }
        if (this.c != 0) {
            block2: for (n3 = 0; n3 < this.c; ++n3) {
                if (j2.C == null || j2.C.size() <= 0) {
                    continue;
                }
                for (int i2 = j2.C.size() - 1; i2 >= 0; --i2) {
                    UnitInstance am4 = (UnitInstance)j2.C.get(i2);
                    if (am4 == null) {
                        continue;
                    }
                    if (this.b != null) {
                        bl2 = false;
                        for (Object object : this.b) {
                            if (((UnitTrait)object).a() != i2) {
                                continue;
                            }
                            bl2 = true;
                            break;
                        }
                        if (!bl2) {
                            continue;
                        }
                    }
                    am4.canBuild();  // 02b: var17.ci() (03 侧 ci 语义名现状)
                    continue block2;
                }
            }
        }
        if (this.a != null) {
            CustomArrayList m2 = new CustomArrayList();
            this.a.a(m2, j2.player, j2, true);  // 02b: bp.a(m, n, am, bl) — m=CustomArrayList n=PlayerState (字节码铁证)
            for (Object object : m2) {
                UnitInstance am5 = (UnitInstance)object;
                boolean bl4 = false;
                if (!(am5 instanceof UnitType)) {
                    GlobalState.e("Failed to attach unit:" + am5.r().i() + " is not an OrderableUnit");
                    continue;
                }
                UnitType object2 = (UnitType)am5;
                if (this.b != null) {
                    for (UnitTrait n4 : (java.util.Collection<UnitTrait>)(java.util.Collection)this.b) {
                        if (j2.a(n4) != null || !j2.a(object2, n4)) {
                            continue;
                        }
                        object2.cQ = -9999;
                        bl4 = true;
                        break;
                    }
                } else {
                    for (Object slotObject : j2.x.aA) {  // 02b: var1.x.aA — j.x=ModUnitRegistry, aA=槽位表
                        UnitTrait n4 = (UnitTrait)slotObject;
                        if (j2.a(n4) != null || !j2.a(object2, n4)) {
                            continue;
                        }
                        object2.cQ = -9999;
                        bl4 = true;
                        break;
                    }
                }
                if (bl4) {
                    continue;
                }
                object2.a();
            }
        }
        if (this.f) {
            if (j2.cO != null) {
                j2.bx();
            }
            if (j2.cN != null) {
                if (j2.cN instanceof UnitShield) {
                    ((UnitShield)((Object)j2.cN)).e(j2);  // 02b: ak.e(var1) — ak=UnitShield
                } else {
                    GlobalState.g("transportedBy is not a TransportInterface");
                    j2.cN = null;
                }
            }
        }
        return true;
    }
}
