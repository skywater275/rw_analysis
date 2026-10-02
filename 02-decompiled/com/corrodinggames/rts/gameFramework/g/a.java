/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.g.b;
import com.corrodinggames.rts.gameFramework.g.c;
import com.corrodinggames.rts.gameFramework.g.d;
import com.corrodinggames.rts.gameFramework.g.e;
import com.corrodinggames.rts.gameFramework.g.f;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public class a {
    private final f a;
    private final c b;
    private final ArrayList c = new ArrayList();

    public a() {
        this(f.a, com.corrodinggames.rts.gameFramework.g.c.a);
    }

    public a(f f2, c c2) {
        this.a = f2;
        this.b = c2;
    }

    public void a() {
        if (this.a == f.a) {
            return;
        }
        ArrayList arrayList = n.b(false);
        if (this.b == com.corrodinggames.rts.gameFramework.g.c.a) {
            for (n n2 : arrayList) {
                this.c.add(new e(n2));
            }
        } else if (this.b == com.corrodinggames.rts.gameFramework.g.c.b) {
            ArrayList arrayList2 = n.f();
            for (Integer n3 : arrayList2) {
                ArrayList<n> arrayList3 = new ArrayList<n>();
                for (n n4 : arrayList) {
                    if (n4.r != n3) continue;
                    arrayList3.add(n4);
                }
                this.c.add(new b(n3, arrayList3));
            }
        } else if (this.b == com.corrodinggames.rts.gameFramework.g.c.c) {
            ArrayList<n> arrayList4;
            int n5 = 0;
            ArrayList arrayList5 = n.f();
            for (Comparable<Integer> comparable : arrayList5) {
                arrayList4 = new ArrayList<n>();
                for (n n6 : arrayList) {
                    if (n6.r != (Integer)comparable) continue;
                    arrayList4.add(n6);
                }
                if (n5 >= arrayList4.size()) continue;
                n5 = arrayList4.size();
            }
            if (n5 <= 1) {
                for (Comparable<Integer> comparable : arrayList) {
                    this.c.add(new e((n)comparable));
                }
            } else {
                for (Comparable<Integer> comparable : arrayList5) {
                    arrayList4 = new ArrayList();
                    for (n n6 : arrayList) {
                        if (n6.r != (Integer)comparable) continue;
                        arrayList4.add(n6);
                    }
                    this.c.add(new b((Integer)comparable, arrayList4));
                    for (n n6 : arrayList4) {
                        this.c.add(new e(n6));
                    }
                }
            }
        }
        this.b();
    }

    public void b() {
        for (d d2 : this.c) {
            d2.b(this.a);
        }
    }

    public void c() {
        int n2 = this.a.ordinal() + 1;
        if (n2 >= f.values().length) {
            n2 = 0;
        }
        f f2 = f.values()[n2];
        c c2 = com.corrodinggames.rts.gameFramework.g.c.c;
        l l2 = l.B();
        l2.a(f2, c2);
    }

    public String a(d d2) {
        if (this.b == com.corrodinggames.rts.gameFramework.g.c.c && d2 instanceof e) {
            return "   " + com.corrodinggames.rts.gameFramework.g.a.a(this.a, d.b(d2));
        }
        return com.corrodinggames.rts.gameFramework.g.a.a(this.a, d.b(d2));
    }

    public static String a(f f2, int n2) {
        switch (f2) {
            case a: {
                return "" + n2;
            }
            case b: {
                return "+" + com.corrodinggames.rts.game.units.custom.e.a.c.D.a(n2, true);
            }
        }
        return com.corrodinggames.rts.game.units.custom.e.a.c.D.a(n2, true);
    }

    public ArrayList d() {
        return this.c;
    }

    public f e() {
        return this.a;
    }

    public c f() {
        return this.b;
    }
}
