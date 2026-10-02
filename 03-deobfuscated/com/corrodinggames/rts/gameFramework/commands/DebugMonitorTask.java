/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.commands;

import com.corrodinggames.rts.gameFramework.commands.DebugServer;
import com.corrodinggames.rts.gameFramework.GlobalState;

class DebugMonitorTask
implements Runnable {
    final /* synthetic */ DebugServer a;

    DebugMonitorTask(DebugServer a2) {
        this.a = a2;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        if (this.a.f) {
            this.a.f = false;
            return;
        }
        if (com.corrodinggames.rts.gameFramework.commands.DebugServer.c) {
            if (l2.bL == null) {
                return;
            }
            l2.bN.F = true;
            if (!l2.bN.j()) {
                com.corrodinggames.rts.gameFramework.commands.DebugServer.e += 1.0f;
            }
            if (com.corrodinggames.rts.gameFramework.commands.DebugServer.e > 5.0f) {
                com.corrodinggames.rts.gameFramework.commands.DebugServer.e = 0.0f;
                System.gc();
                System.gc();
                l2.bN.e();
            }
        }
        if (com.corrodinggames.rts.gameFramework.commands.DebugServer.d && l2.bL != null) {
            l2.bL.g();
        }
    }
}
