/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.GameTimer;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.gameFramework.PingTimer;
import com.corrodinggames.rts.gameFramework.TextPacketBuilder;

public class ReplayRecorder
extends PacketBuilder {
    TextPacketBuilder a;
    PingTimer b;

    public ReplayRecorder(PingTimer av2) {
        this.b = av2;
    }


    public void a(GameTimer ar2) {
        this.a = (TextPacketBuilder) ar2;
    }


    public void a(boolean bl) {
    }


    public void a() {
    }


    public void b() {
    }


    public void d() {
    }


    public void e() {
    }


    public boolean c() {
        return true;
    }


    public void a(float f) {
    }
}
