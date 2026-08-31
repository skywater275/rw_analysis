/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;

import com.corrodinggames.librocket.LibRocketContext;
import com.corrodinggames.librocket.LibRocketContext$2$1;
import com.corrodinggames.librocket.LibRocketContext$2$2;
import com.corrodinggames.librocket.DocumentWrapper;
import com.corrodinggames.librocket.e;
import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;
import com.corrodinggames.rts.gameFramework.utility.k;

class LibRocketContext$2
implements Runnable {
    final k a = new k(false);
    final /* synthetic */ ScriptEngine b;
    final /* synthetic */ PasswordManager c;
    final /* synthetic */ LibRocketContext d;

    LibRocketContext$2(LibRocketContext a2, ScriptEngine scriptEngine, PasswordManager ae2) {
        this.d = a2;
        this.b = scriptEngine;
        this.c = ae2;
    }

    @Override
    public void run() {
        MainUIController root = this.b.getRoot();
        e e2 = new e(this.c.algorithmName != null ? this.c.algorithmName : "Join", new LibRocketContext$2$1(this, root));
        e2.c = true;
        LibRocketContext$2$2 a$2$2 = new LibRocketContext$2$2(this, root);
        e e3 = new e(this.c.displayLabel != null ? this.c.displayLabel : "Close", a$2$2);
        String string = "Password Required";
        String string2 = "This server requires LibRocketContext password to join";
        if (this.c.passwordHash != null) {
            string = "Server Question";
            string2 = this.c.passwordHash;
            string2 = com.corrodinggames.rts.gameFramework.steam.Localization.c(string2);
        }
        if (this.c.saltValue != null) {
            string = this.c.saltValue;
        }
        String string3 = "";
        DocumentWrapper d2 = new DocumentWrapper();
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
