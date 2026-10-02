/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import android.os.Looper;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.Main;
import java.util.concurrent.Semaphore;

class Main$2
implements Runnable {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ Main b;  // Main 幻觉名修正

    Main$2(Main main, Semaphore semaphore){
        this.b = main;
        this.a = semaphore;
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();  // 02b: l.aq() (ReplayWriter 战役已确认映射)
        Looper.a();
        this.a.release(1);
        Looper.c();
    }
}
