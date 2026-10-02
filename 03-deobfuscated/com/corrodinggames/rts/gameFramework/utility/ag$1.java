/*
 * v19.133f62 补建: 02b utility/ag$1.java 直译 (延迟关闭 zip 线程)
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.GlobalState;

final class ag$1 implements Runnable {

    final String a;
    final ah b;

    ag$1(String var1, ah var2) {
        this.a = var1;
        this.b = var2;
    }

    public void run() {
        try {
            Thread.sleep(1500L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        GlobalState.e("Running delayed close of zip: " + this.a);
        this.b.a();
    }
}
