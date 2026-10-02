/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.o;
import com.corrodinggames.rts.gameFramework.utility.s;
import com.corrodinggames.rts.gameFramework.x;

public strictfp abstract class w
extends bq {
    public long eh;
    public static x ei = new x();
    public boolean ej = false;
    public boolean ek = false;
    public boolean el;
    public int em = 2;
    public int en = 0;
    public float eo;
    public float ep;
    public float eq = 0.0f;
    private static final o a = new o();
    public static final s er = new s("fastGameObjectList");

    public void S(int n) {
        this.em = n;
    }

    @Override
    public void a(as as2) {
        as2.a(this.ej);
        as2.a(this.ek);
        as2.a(this.em);
    }

    public void a(k k2) {
        this.ej = k2.e();
        this.ek = k2.e();
        this.em = k2.f();
    }

    public w() {
        this(false);
    }

    public w(boolean bl) {
        if (!bl) {
            l l2 = l.B();
            if (this.eh != 0L) {
                throw new RuntimeException("ID for GameObject is already set at:" + this.eh);
            }
            this.eh = l2.bX.y();
            if (this.eh == 0L) {
                throw new RuntimeException("Adding object with id:0 class:" + this.getClass().getSimpleName());
            }
            a.a(this);
            er.a(this);
        } else {
            this.eh = 0L;
        }
    }

    public abstract void a(float var1);

    public abstract void a(float var1, boolean var2);

    public abstract void d(float var1);

    public abstract void e(float var1);

    public void p(float f2) {
    }

    public abstract boolean c(float var1);

    public abstract boolean f(float var1);

    public boolean a(l l2) {
        return true;
    }

    public void a() {
        if (this.eh != 0L) {
            a.b(this);
            er.remove(this);
        }
        this.ej = true;
    }

    public static w a(long l2, Class clazz, boolean bl) {
        if (l2 == -1L) {
            return null;
        }
        w[] wArray = er.a();
        int n2 = er.size();
        for (int j = 0; j < n2; ++j) {
            w w2 = wArray[j];
            if (w2.eh != l2) continue;
            if (clazz.isInstance(w2)) {
                return w2;
            }
            String string = w2.getClass().getName();
            String string2 = clazz.getName();
            string = string.replace("com.corrodinggames.rts.", "");
            string2 = string2.replace("com.corrodinggames.rts.", "");
            ad.g("object id:" + l2 + " was found, but with type " + string + " instead of " + string2);
        }
        if (!bl) {
            ad.g("getFromId:" + l2 + " was not found");
        }
        return null;
    }

    public static am a(long l2, boolean bl) {
        return (am)w.a(l2, am.class, bl);
    }

    public static y b(long l2, boolean bl) {
        return (y)w.a(l2, y.class, bl);
    }

    public static o dK() {
        a.a();
        return a;
    }

    public static void dL() {
        a.a();
        am.bG();
    }
}
