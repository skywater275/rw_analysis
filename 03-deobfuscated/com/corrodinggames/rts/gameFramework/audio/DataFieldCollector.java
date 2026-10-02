/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.audio;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.audio.DataField;
import com.corrodinggames.rts.gameFramework.audio.DataFieldInt;
import com.corrodinggames.rts.gameFramework.audio.DataFieldFloat;
import com.corrodinggames.rts.gameFramework.audio.DataFieldLong;
import com.corrodinggames.rts.gameFramework.audio.DataFieldProvider;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public class DataFieldCollector {
    private final DataFieldProvider fieldName;
    private final DataFieldInt fieldValue;
    private final ArrayList fieldFlags = new ArrayList();

    public DataFieldCollector() {
        this(DataFieldProvider.a, com.corrodinggames.rts.gameFramework.audio.DataFieldInt.a);  // f=DataFieldProvider 枚举
    }

    public DataFieldCollector(DataFieldProvider f2, DataFieldInt c2) {
        this.fieldName = f2;
        this.fieldValue = c2;
    }

    public void a() {
        if (this.fieldName == DataFieldProvider.a) {
            return;
        }
        ArrayList arrayList = PlayerState.b(false);  // n=PlayerState (实测存在)
        if (this.fieldValue == com.corrodinggames.rts.gameFramework.audio.DataFieldInt.a) {
            for (PlayerState n2 : (java.util.Collection<PlayerState>) (java.util.Collection) arrayList) {
                this.fieldFlags.add(new DataFieldLong(n2));
            }
        } else if (this.fieldValue == com.corrodinggames.rts.gameFramework.audio.DataFieldInt.b) {
            ArrayList arrayList2 = PlayerState.f();
            for (Integer n3 : (java.util.Collection<Integer>) (java.util.Collection) arrayList2) {
                ArrayList<PlayerState> arrayList3 = new ArrayList<PlayerState>();
                for (PlayerState n4 : (java.util.Collection<PlayerState>) (java.util.Collection) arrayList) {
                    if (n4.r != n3) continue;
                    arrayList3.add(n4);
                }
                this.fieldFlags.add(new DataField(n3, arrayList3));
            }
        } else if (this.fieldValue == com.corrodinggames.rts.gameFramework.audio.DataFieldInt.c) {
            ArrayList<PlayerState> arrayList4;
            int n5 = 0;
            ArrayList arrayList5 = PlayerState.f();
            for (Object comparable : arrayList5) {  // F17: raw ArrayList 元素 Object (L55 已有 (Integer)cast)
                arrayList4 = new ArrayList<PlayerState>();
                for (PlayerState n6 : (java.util.Collection<PlayerState>) (java.util.Collection) arrayList) {
                    if (n6.r != (Integer)comparable) continue;
                    arrayList4.add(n6);
                }
                if (n5 >= arrayList4.size()) continue;
                n5 = arrayList4.size();
            }
            if (n5 <= 1) {
                for (Object comparable : arrayList) {  // F17: raw ArrayList 元素 Object (L63 已有 (PlayerState)cast)
                    this.fieldFlags.add(new DataFieldLong((PlayerState) comparable));
                }
            } else {
                for (Object comparable : arrayList5) {  // F17: raw ArrayList 元素 Object (L55 已有 (Integer)cast)
                    arrayList4 = new ArrayList();
                    for (PlayerState n6 : (java.util.Collection<PlayerState>) (java.util.Collection) arrayList) {
                        if (n6.r != (Integer)comparable) continue;
                        arrayList4.add(n6);
                    }
                    this.fieldFlags.add(new DataField((Integer)comparable, arrayList4));
                    for (PlayerState n6 : arrayList4) {
                        this.fieldFlags.add(new DataFieldLong(n6));
                    }
                }
            }
        }
        // 02b 无对应: fieldValue 字段误调用删除 (F59)
    }

    public void b() {
        for (DataFieldFloat d2 : (java.util.Collection<DataFieldFloat>) (java.util.Collection) this.fieldFlags) {
            d2.b(this.fieldName);
        }
    }

    public void c() {
        int n2 = this.fieldName.ordinal() + 1;
        if (n2 >= DataFieldProvider.values().length) {
            n2 = 0;
        }
        DataFieldProvider f2 = DataFieldProvider.values()[n2];
        DataFieldInt c2 = com.corrodinggames.rts.gameFramework.audio.DataFieldInt.c;
        GlobalState l2 = GlobalState.B();
        l2.a(f2, c2);
    }

    public String a(DataFieldFloat d2) {
        if (this.fieldValue == com.corrodinggames.rts.gameFramework.audio.DataFieldInt.c && d2 instanceof DataFieldLong) {
            return "   " + a(this.fieldName, DataFieldFloat.b(d2));  // 本类静态 a(DataFieldProvider,int) + DataFieldFloat 合成 b
        }
        return a(this.fieldName, DataFieldFloat.b(d2));  // 本类静态 a + DataFieldFloat 合成 b
    }

    public static String a(DataFieldProvider f2, int n2) {
        switch (f2) {
            case a: {
                return "" + n2;
            }
            case b: {
                return "+" + com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.a((long)n2, com.corrodinggames.rts.game.units.custom.effects.b.c);  // 03 静态格式化 a(long,b) 等价 (c.D 幻觉)
            }
        }
        return com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.a((long)n2, com.corrodinggames.rts.game.units.custom.effects.b.c);  // 03 静态格式化等价
    }

    public ArrayList d() {
        return this.fieldFlags;
    }

    public DataFieldProvider e() {
        return this.fieldName;
    }

    public DataFieldInt f() {
        return this.fieldValue;
    }
}
