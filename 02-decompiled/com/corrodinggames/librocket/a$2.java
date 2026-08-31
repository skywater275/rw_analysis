/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;

import com.corrodinggames.librocket.a;
import com.corrodinggames.librocket.a$2$1;
import com.corrodinggames.librocket.a$2$2;
import com.corrodinggames.librocket.d;
import com.corrodinggames.librocket.e;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.j.ae;
import com.corrodinggames.rts.gameFramework.utility.k;

class a$2
implements Runnable {
    final k a = new k(false);
    final /* synthetic */ ScriptEngine b;
    final /* synthetic */ ae c;
    final /* synthetic */ a d;

    a$2(a a2, ScriptEngine scriptEngine, ae ae2) {
        this.d = a2;
        this.b = scriptEngine;
        this.c = ae2;
    }

    @Override
    public void run() {
        Root root = this.b.getRoot();
        e e2 = new e(this.c.f != null ? this.c.f : "Join", new a$2$1(this, root));
        e2.c = true;
        a$2$2 a$2$2 = new a$2$2(this, root);
        e e3 = new e(this.c.g != null ? this.c.g : "Close", a$2$2);
        String string = "Password Required";
        String string2 = "This server requires a password to join";
        if (this.c.b != null) {
            string = "Server Question";
            string2 = this.c.b;
            string2 = com.corrodinggames.rts.gameFramework.h.a.c(string2);
        }
        if (this.c.e != null) {
            string = this.c.e;
        }
        String string3 = "";
        d d2 = new d();
        d2.b = string;
        d2.c = string2;
        d2.d = string3;
        d2.e = e3;
        d2.f = e2;
        d2.h = false;
        d2.i = a$2$2;
        this.d.b.a(d2);
    }
}
