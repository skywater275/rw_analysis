/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.game.HumanPlayer;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class NetworkAuth {
    public boolean a(PacketDecoder c2, String string, String string2) {
        return true;
    }

    public boolean a(PacketDecoder c2, PlayerState n2, String string, boolean bl) {
        return true;
    }

    public void b(PacketDecoder c2, String string, String string2) {
    }

    public void a(int n2, String string, String string2, PacketDecoder c2) {
    }

    public String a(PacketDecoder c2, String string) {
        return null;
    }

    public void c(PacketDecoder c2, String string, String string2) {
    }

    public void b(PacketDecoder c2, String string) {
    }

    public void a(PlayerState n2) {
    }

    public String a(PacketDecoder c2, String string, int n2, int n3, String string2, HumanPlayer e2) {
        GlobalState.e("new player Joining packageName:" + string2 + ", appVersion:" + n3 + ", playerName:" + string + " ip:" + c2.g() + " id:" + c2.c);
        return null;
    }

    public void a() {
    }

    public boolean a(PacketDecoder c2) {
        return false;
    }

    public boolean b(PacketDecoder c2) {
        return false;
    }

    public void b() {
        GlobalState.e("NetworkCallbacks:startGameEvent()");
    }

    public void c() {
    }

    public void a(PasswordManager ae2) {
    }

    public void d() {
    }

    public boolean e() {
        return false;
    }
}
