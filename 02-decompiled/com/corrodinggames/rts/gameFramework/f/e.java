/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.bo;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public class e {
    public String a;
    public String b;
    public float c;
    public float d;

    public e(String string, String string2) {
        this.a = string;
        this.b = string2;
    }

    public e(String string, float f) {
        this.a = string;
        this.c = f;
        this.b = null;
    }

    public static ArrayList a() {
        l l2 = l.B();
        ArrayList<e> arrayList = new ArrayList<e>();
        bo bo2 = null;
        if (l2.bs != null) {
            bo2 = l2.bY.a(l2.bs);
        }
        if (bo2 != null) {
            e e2;
            if (l2.ce != null && l2.ce.k) {
                e2 = new e("Lasted till wave: " + l2.ce.r, "");
                arrayList.add(e2);
                if (!l2.ce.l) {
                    e2 = new e("Wave difficulty: " + l2.bX.c(l2.ce.y), "");
                    arrayList.add(e2);
                }
            }
            e2 = new e("Game Time", f.a((long)(l2.by / 1000)));
            arrayList.add(e2);
            e2 = new e("=============================", "");
            arrayList.add(e2);
            e2 = new e("Units Killed", bo2.c);
            arrayList.add(e2);
            e2 = new e("Buildings Killed", bo2.d);
            arrayList.add(e2);
            e2 = new e("Experimentals Killed", bo2.e);
            arrayList.add(e2);
            e2 = new e("=============================", "");
            arrayList.add(e2);
            e2 = new e("Units Lost", bo2.f);
            arrayList.add(e2);
            e2 = new e("Buildings Lost", bo2.g);
            arrayList.add(e2);
            e2 = new e("Experimentals Lost", bo2.h);
            arrayList.add(e2);
            e2 = new e("=============================", "");
            arrayList.add(e2);
        }
        return arrayList;
    }
}
