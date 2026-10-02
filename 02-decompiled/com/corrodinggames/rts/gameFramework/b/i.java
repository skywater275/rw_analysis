/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.b;

import com.corrodinggames.rts.gameFramework.b.af;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.c;
import com.corrodinggames.rts.gameFramework.b.j;
import com.corrodinggames.rts.gameFramework.b.k;
import com.corrodinggames.rts.gameFramework.b.x;
import com.corrodinggames.rts.gameFramework.l;
import java.util.List;

public class i
extends c {
    protected List a;
    private final List b;
    private b c;
    private b d;

    private void a(b b2) {
        this.a();
        for (int i2 = 0; i2 < this.a.size(); ++i2) {
            this.b.add(new x(b2.b(), b2.c(), false));
        }
    }

    private void a() {
        for (x x2 : this.b) {
            x2.j();
        }
        this.b.clear();
    }

    public b a(b b2, k k2, j j2) {
        if (b2 instanceof x ? !((x)b2).k() : this.d == b2 && this.c != null) {
            return this.c;
        }
        if (this.b.size() != this.a.size() || this.d != b2) {
            this.a(b2);
        }
        this.d = b2;
        b b3 = b2;
        int n2 = this.b.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            x x2 = (x)this.b.get(i2);
            af af2 = (af)this.a.get(i2);
            k2.c(x2);
            j2.a(b3, af2, i2 == 0);
            k2.d();
            b3 = x2;
            l.e("FilterGroup: renderTarget");
        }
        this.c = b3;
        return b3;
    }
}
