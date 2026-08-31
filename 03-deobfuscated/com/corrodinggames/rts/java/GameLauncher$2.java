/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import android.os.Looper;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.GameLauncher;
import java.util.concurrent.Semaphore;

class GameLauncher$2
implements Runnable {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ GameLauncher b;  // Main 幻觉名修正

    GameLauncher$2(GameLauncher main, Semaphore semaphore){
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
