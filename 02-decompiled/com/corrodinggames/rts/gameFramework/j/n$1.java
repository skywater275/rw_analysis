/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.j.t;
import com.corrodinggames.rts.gameFramework.l;
import java.util.List;
import java.util.concurrent.Callable;

final class n$1
implements Callable {
    final /* synthetic */ String a;
    final /* synthetic */ List b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;

    n$1(String string, List list, boolean bl, boolean bl2) {
        this.a = string;
        this.b = list;
        this.c = bl;
        this.d = bl2;
    }

    public t a() {
        try {
            n.a("Running doSingleRequest:" + this.a);
            return n.a(this.b, this.a, this.c);
        }
        catch (Exception exception) {
            l.e("Error on doSingleRequest:" + this.a + " - " + exception.getMessage());
            if (this.d) {
                exception.printStackTrace();
            }
            return null;
        }
    }

    public /* synthetic */ Object call() {
        return this.a();
    }
}
