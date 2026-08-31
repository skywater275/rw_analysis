/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.TeamColor;
import com.corrodinggames.rts.gameFramework.GameTimer;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.gameFramework.TextPacketBuilder;
import com.corrodinggames.rts.gameFramework.ReplayRecorder;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class PingTimer
extends MusicPlayerBase {  // 02b av extends aq (MusicPlayerBase); TeamColor 错标修正
    boolean a = false;


    public GameTimer a(String string) {
        return new TextPacketBuilder(string, this);
    }


    public PacketBuilder a() {
        ReplayRecorder ax2 = new ReplayRecorder(this);
        return ax2;
    }


    public void a(MusicController am2) {
        GlobalState.e("Null musicFactory - load");
        this.e = am2;
    }


    public void b() {
    }
}
