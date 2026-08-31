/*
 * v19.133f2: 02b j/ao$1.java 全文直译 (AcceptFilter)
 * extends PacketDecoder 修正 → AddressFilter (02b a/a.c); GameInput 修正 → ServerListener (02b ao)
 */
package com.corrodinggames.rts.gameFramework.network;

import network.reliableudp.AddressFilter;
import com.corrodinggames.rts.gameFramework.network.ServerListener;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

strictfp class ServerListener$1
extends AddressFilter {  // 02b a/a.c (v19.133f2 修正)
    final /* synthetic */ ServerListener a;  // 02b ao (v19.133f2 修正)

    ServerListener$1(ServerListener ao2) {
        this.a = ao2;
    }

    public boolean a(SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            return this.a.a(((InetSocketAddress) socketAddress).getAddress(), false);  // 02b ao L39: a(InetAddress,boolean)
        }
        GlobalState.e("AcceptFilter: Unhandled SocketAddress type:" + socketAddress.getClass().getName());  // 02b l.e
        return true;
    }
}
