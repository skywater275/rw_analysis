/*
 * 02b a/a/b.java 直译: networkSocks 压力测试 (extends TestCase)
 * 02b Debug.java L638-642: runAllLeakTests 使用
 */
package com.corrodinggames.rts.platform.net;

import com.corrodinggames.rts.gameFramework.GlobalState;

public class b extends TestCase {
    public void a() {
        this.b();
    }

    public void b() {
        GlobalState.e("networkSocks");
        GlobalState l2 = GlobalState.B();
        for (int i = 0; i < 10000; ++i) {
            l2.bX.registerRelayServer(false);  // 02b: bX.b(false)
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            l2.bX.registerRelayServer("test");  // 02b: bX.b("test")
        }
        GlobalState.e("done");
        try {
            Thread.sleep(100000L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
