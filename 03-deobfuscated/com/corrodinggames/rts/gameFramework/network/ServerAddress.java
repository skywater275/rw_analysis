/*
 * v19.133f2: 02b j/ap.java 全文直译 (ServerAddress)
 * e 字段 GameInput 修正 → ServerListener (02b j/ap: final ao e)
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.ServerListener;
import java.net.InetAddress;

strictfp class ServerAddress {
    InetAddress a;
    int b = 1;
    boolean c;
    boolean d;
    final /* synthetic */ ServerListener e;  // 02b j/ap.java: final ao e (v19.133f2 修正)

    ServerAddress(ServerListener ao2) {
        this.e = ao2;
        this.b = 1;
    }
}
