/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.text.Html
 *  android.text.Spanned
 */
package com.corrodinggames.rts.gameFramework.j;

import android.text.Html;
import android.text.Spanned;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.b;
import com.corrodinggames.rts.gameFramework.j.c;
import java.util.concurrent.ConcurrentLinkedQueue;

public class a {
    private ConcurrentLinkedQueue a = new ConcurrentLinkedQueue();

    public String a(String string) {
        return f.i(string);
    }

    public void a(int n, String string, String string2, c c2) {
        string2 = string2.trim();
        b b2 = new b(this, n, string, string2, c2);
        this.a.add(b2);
        if (this.a.size() > 45) {
            this.a.poll();
        }
    }

    public int a(c c2, int n) {
        if (c2 == null) {
            return 0;
        }
        int n2 = c2.c;
        int n3 = 0;
        for (b b2 : this.a) {
            if (b2.d != n2 || f.a(b2.e, System.nanoTime()) >= (long)n || b2.c.startsWith("-i ") || b2.c.startsWith("-qc ")) continue;
            ++n3;
            if (b2.c == null) continue;
            if (f.a(b2.c, '\n') >= 3) {
                n3 += 2;
            }
            if (f.a(b2.c, '\n') >= 6) {
                n3 += 2;
            }
            if (f.a(b2.c, '\n') < 9) continue;
            n3 += 2;
        }
        return n3;
    }

    public String a() {
        String string = "";
        for (b b2 : this.a) {
            string = string + b2.a() + "\n";
        }
        return string;
    }

    public ConcurrentLinkedQueue b() {
        return this.a;
    }

    public String a(boolean bl) {
        String string = "";
        if (!bl) {
            for (b b2 : this.a) {
                string = string + b2.b() + "<br/>\n";
            }
        } else {
            for (b b3 : this.a) {
                string = b3.b() + "<br/>\n" + string;
            }
        }
        return "<pre>" + string + "</pre>";
    }

    public Spanned b(boolean bl) {
        return Html.fromHtml((String)this.a(bl));
    }

    public void c() {
        this.a.clear();
    }
}
