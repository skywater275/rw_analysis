/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bc;
import com.corrodinggames.rts.gameFramework.h.a;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public class bb {
    public static final bb a = bb.a("");
    public bc[] b;
    public String c;
    public int d = -1;
    public String e;

    public static bb a(String string) {
        bb bb2 = new bb();
        ArrayList<bc> arrayList = new ArrayList<bc>();
        bc bc2 = new bc();
        bc2.a = null;
        bc2.b = string;
        arrayList.add(bc2);
        bb2.b = arrayList.toArray(new bc[0]);
        bb2.b();
        return bb2;
    }

    public static bb b(String string) {
        bb bb2 = new bb();
        bb2.e = string;
        bb2.b();
        return bb2;
    }

    public bb() {
    }

    public bb(bc[] bcArray) {
        this.b = bcArray;
    }

    public boolean a() {
        if (this.b != null) {
            for (bc bc2 : this.b) {
                if (bc2.b == null || "".equals(bc2.b)) continue;
                return false;
            }
        }
        return true;
    }

    public void a(String string, String string2) {
        if (this.b != null) {
            for (bc bc2 : this.b) {
                bc2.a(string, string2);
            }
        } else {
            l.b("LocaleString: replaceAll with null strings");
        }
        this.d = -1;
    }

    public String b() {
        if (this.d == com.corrodinggames.rts.gameFramework.h.a.c) {
            return this.c;
        }
        if (this.e != null) {
            this.d = com.corrodinggames.rts.gameFramework.h.a.c;
            this.c = com.corrodinggames.rts.gameFramework.h.a.a(this.e, new Object[0]);
            return this.c;
        }
        String string = com.corrodinggames.rts.gameFramework.h.a.c();
        for (bc bc2 : this.b) {
            if (!string.equals(bc2.a)) continue;
            this.d = com.corrodinggames.rts.gameFramework.h.a.c;
            this.c = bc2.b;
            return this.c;
        }
        for (bc bc2 : this.b) {
            if (bc2.a != null) continue;
            this.d = com.corrodinggames.rts.gameFramework.h.a.c;
            this.c = bc2.b;
            return this.c;
        }
        this.d = com.corrodinggames.rts.gameFramework.h.a.c;
        this.c = "<NO DEFAULT TEXT FOUND>";
        return this.c;
    }
}
