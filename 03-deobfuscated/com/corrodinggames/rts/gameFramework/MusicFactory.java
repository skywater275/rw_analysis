/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 */
package com.corrodinggames.rts.gameFramework;

import android.media.MediaPlayer;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.GameInput;
import com.corrodinggames.rts.gameFramework.MusicPlayer;
import com.corrodinggames.rts.gameFramework.TeamColor;
import com.corrodinggames.rts.gameFramework.GameTimer;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public class MusicFactory
extends MusicPlayerBase {  // 02b an.java: extends aq
    ArrayList a = new ArrayList();
    ArrayList b = new ArrayList();
    ArrayList c = new ArrayList();
    boolean d = false;


    public GameTimer a(String string) {
        return new GameInput(string, this);
    }


    public PacketBuilder a() {
        MusicPlayer ap2 = new MusicPlayer(this);
        return ap2;
    }


    public void a(MusicController am2) {
        this.e = am2;
        if (this.d) {
            GlobalState.e("AndroidMusicFactory already loaded");
        }
        GlobalState.e("AndroidMusicFactory - load");
        this.d = true;
        this.a.add(new MediaPlayer());
        this.a.add(new MediaPlayer());
        this.b.addAll(this.a);
    }


    public void b() {
    }
}
