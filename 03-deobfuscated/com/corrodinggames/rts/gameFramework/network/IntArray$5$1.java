/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

strictfp class IntArray$5$1
implements Runnable {
    final /* synthetic */ IntArray$5 a;

    IntArray$5$1(IntArray$5 intArray$5) {
        this.a = intArray$5;
    }

    @Override
    public void run() {
        // 02b ad$5$1.java L15-18: 断开日志 + 大厅 UI 刷新
        this.a.c.m("already disconnected");
        this.a.b.bS.g.l();
    }
}
