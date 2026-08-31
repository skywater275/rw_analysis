/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.u;
import java.io.IOException;

public strictfp class j {
    public e a;
    y b;
    ag c;
    ae d;
    Paint e = new Paint();
    Rect f = new Rect(-101, 0, -1, 100);
    boolean g;

    public j() {
        this.c = new ag();
    }

    public j(String string) {
        this();
        try {
            this.d = new ae(string);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.c.a(this.d);
        if (this.d.o != 0) {
            this.g = true;
        }
    }

    public boolean a() {
        if (this.d != null && this.d.o != 0) {
            return true;
        }
        return this.g;
    }

    public void a(y y2) {
        this.a(y2, y2.m(), y2.n(), 10);
    }

    public void a(y y2, int n2, int n3, int n4) {
        if (this.g) {
            return;
        }
        if (this.a != null && (n2 > this.a.m() || n3 > this.a.l())) {
            this.a.o();
            this.a = null;
            this.b = null;
        }
        if (this.a == null) {
            try {
                this.a = y2.a(n2 + n4, n3 + n4, true);
                this.b = y2.a(this.a);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.g = true;
                l.a(u.b, (Throwable)outOfMemoryError);
                return;
            }
        }
        this.b.a(n2, n3);
    }

    public void b() {
        l l2 = l.B();
        l2.bO.b(this.f, this.e);
        l2.bO.b(this.a, 0.0f, 0.0f, (Paint)this.c);
    }
}
