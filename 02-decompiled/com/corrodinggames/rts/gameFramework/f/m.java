/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.f.n;
import com.corrodinggames.rts.gameFramework.l;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class m {
    public static int a = 95;
    g b;
    l c;
    Paint d;
    boolean e;
    ArrayList f = new ArrayList();

    m(l l2, g g2) {
        this.b = g2;
        this.c = l2;
        this.a();
    }

    public void a() {
        this.d = new Paint();
        this.d.a(255, 255, 255, 255);
        this.d.a(true);
        this.d.c(true);
        this.d.a(Typeface.a(Typeface.c, 1));
        this.c.a(this.d, 16.0f);
    }

    public synchronized void b() {
        this.e = false;
        this.f.clear();
    }

    public synchronized void c() {
        Iterator iterator = this.f.iterator();
        while (iterator.hasNext()) {
            n n2 = (n)iterator.next();
            if (this.f.size() <= 15) continue;
            iterator.remove();
        }
    }

    public synchronized n a(String string, String string2) {
        l l2 = l.B();
        n n2 = new n();
        n2.a = string;
        n2.b = string2;
        n2.c = System.currentTimeMillis();
        n2.d = new SimpleDateFormat("HH:mm:ss").format(new Date());
        if (string != null && !string.equals("") && !l2.bQ.showPlayerChatInGame) {
            if (!this.e) {
                this.e = true;
                this.a(null, "[WARNING: A player send a chat message, but you have chat muted in your settings]");
            }
            return n2;
        }
        this.c();
        this.f.add(n2);
        return n2;
    }

    public synchronized int a(float f2, int n2) {
        l l2 = l.B();
        this.c();
        int n3 = 20;
        int n4 = (int)(20.0f * l2.cj);
        boolean bl = l2.a();
        for (int j = this.f.size() - 1; j >= 0; --j) {
            n n5 = (n)this.f.get(j);
            if (!n5.b()) continue;
            String string = n5.a == null || n5.a.equals("") ? n5.b : n5.a + ": " + n5.b;
            if (bl) {
                string = n5.d + ": " + string;
            }
            if (n5.e > 0) {
                int n6 = n5.a();
                int n7 = n6 / n5.e;
                if (n7 < 0) {
                    n7 = 0;
                }
                if (n7 < string.length()) {
                    string = string.substring(0, n7);
                }
            }
            this.d.b(n5.f);
            l2.bO.a(string, (float)n3, (float)n2, this.d);
            n2 += n4;
        }
        return n2;
    }
}
