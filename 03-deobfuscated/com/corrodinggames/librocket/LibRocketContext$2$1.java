/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;

import com.corrodinggames.librocket.LibRocketContext$2;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.rts.gameFramework.GlobalState;

class LibRocketContext$2$1
implements Runnable {
    final /* synthetic */ Root a;
    final /* synthetic */ LibRocketContext$2 b;

    LibRocketContext$2$1(LibRocketContext$2 var1_1, Root root) {
        this.b = var1_1;
        this.a = root;
    }

    @Override
    public void run() {
        if (this.b.a.a) {
            GlobalState.b("AskPasswordCallBack already called");
            return;
        }
        this.b.a.a = true;
        String string = this.a.getPopupText();
        this.a.closeAlertOnly();
        this.b.c.a(string);
    }
}
