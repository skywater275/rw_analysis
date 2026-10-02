/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.f.aq;
import com.corrodinggames.rts.gameFramework.f.ar;
import com.corrodinggames.rts.gameFramework.f.as;
import com.corrodinggames.rts.gameFramework.f.at;
import com.corrodinggames.rts.gameFramework.f.au;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public strictfp class ap {
    private l a;
    private Paint b;
    private ArrayList c = new ArrayList();

    public ap(l l2) {
        this.a = l2;
        this.a();
    }

    public void a() {
        this.b = new Paint();
        this.b.a(255, 255, 255, 255);
        this.b.a(true);
        this.b.c(true);
        this.b.a(Typeface.a(Typeface.c, 1));
        this.a.a(this.b, 14.0f);
    }

    public synchronized void b() {
        this.c.clear();
    }

    public synchronized void a(am am2) {
        ar ar2 = new ar(am2.eo, am2.ep, am2.r());
        ar2.c = l.V();
        this.a(ar2);
    }

    public synchronized void b(am am2) {
        at at2 = new at(am2.eo, am2.ep, am2.r());
        at2.c = l.V();
        this.a(at2);
    }

    public synchronized void c(am am2) {
        as as2 = new as(am2.eo, am2.ep, am2.bI());
        as2.c = l.V();
        this.a(as2);
    }

    public synchronized void a(String string) {
        aq aq2 = new aq(string);
        aq2.c = l.V();
        this.a(aq2);
    }

    public synchronized void a(String string, int n2) {
        aq aq2 = new aq(string);
        aq2.c = l.V();
        aq2.d = n2;
        aq2.i = true;
        this.a(aq2);
    }

    private void a(au au2) {
        boolean bl = false;
        for (au au3 : this.c) {
            if (!au3.a(au2)) continue;
            au3.b(au2);
            bl = true;
            break;
        }
        if (bl) {
            Collections.sort(this.c);
        } else {
            this.c.add(0, au2);
        }
    }

    public synchronized void a(float f2) {
        this.c();
        l l2 = l.B();
        int n2 = (int)(l2.cm - 130.0f * l2.cj);
        int n3 = 20;
        int n4 = (int)(20.0f * l2.cj);
        for (au au2 : this.c) {
            String string = au2.a();
            if (!l2.bQ.showWarLogOnScreen && !au2.i) continue;
            if (au2.c + au2.d < System.currentTimeMillis()) break;
            if (au2.h) {
                this.b.a(255, 160, 160, 160);
            } else {
                this.b.a(255, 255, 255, 255);
            }
            l2.bO.a(string, (float)n3, (float)n2, this.b);
            n2 -= n4;
        }
    }

    public synchronized void c() {
        Iterator iterator = this.c.iterator();
        while (iterator.hasNext()) {
            au au2 = (au)iterator.next();
            if (au2.c + 20000L >= System.currentTimeMillis()) continue;
            iterator.remove();
        }
    }

    public synchronized void d() {
        if (this.c.isEmpty()) {
            return;
        }
        for (au au2 : this.c) {
            if (au2.h) continue;
            au2.h = true;
            this.a.b(au2.e, au2.f);
            break;
        }
    }
}
