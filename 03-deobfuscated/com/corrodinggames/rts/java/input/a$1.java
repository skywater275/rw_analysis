/*
 * Decompiled with CFR 0.152.
 * 02b java/c/a$1.java 直译: 邀请弹窗回调 (Root=MainUIController 03 语义名)
 */
package com.corrodinggames.rts.java.input;

import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.java.input.a;

class a$1
implements Runnable {
    final /* synthetic */ a a;

    a$1(a a2) {
        this.a = a2;
    }

    @Override
    public void run() {
        MainUIController mainUIController = ScriptEngine.getInstance().getRoot();
        com.corrodinggames.librocket.e e2 = new com.corrodinggames.librocket.e("Join", this.a);
        mainUIController.showPopupWithButtons("Invite", "'" + this.a.b + "' has invited you to join a game", true, e2, (com.corrodinggames.librocket.e)null);
    }
}
