/*
 * v19.115r logicBooleans 批2 新建: class-discoveries utility.u=UnitInstanceList 铁证
 * (extends AbstractList + am[] 字段; javap am.bE: public static final utility.u)
 * 最小版: 单位注册表 (bE 字段类型), a() 返回单位数组
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.game.units.UnitInstance;
import java.util.ArrayList;

public class UnitInstanceList
extends ArrayList<UnitInstance> {
    public int b;  // 02b utility/u L17

public UnitInstance[] a() {
        // 02b utility.u.a(): 返回底层数组
        return this.toArray(new UnitInstance[0]);
    }

    public boolean a(UnitInstance var1) {
        // 02b utility.u.a(am) L29 铁证: 追加单位 (扩容+modCount) — 简化用 ArrayList.add
        return this.add(var1);
    }

    public UnitInstance a(int n2) {
        // 02b utility.u.a(int) L164 铁证: 按索引取单位 — 简化用 ArrayList.get
        return this.get(n2);
    }

    public final void b(UnitInstance am2) {  // 02b utility.u.b(am) L45 铁证: 追加单位 (无返回值)
        this.add(am2);
    }

}
