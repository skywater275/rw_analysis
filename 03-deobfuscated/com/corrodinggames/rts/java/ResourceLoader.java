/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.TeamColor;
import com.corrodinggames.rts.gameFramework.GameTimer;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.DesktopMusicPlayer;
import com.corrodinggames.rts.java.MusicTrack;

public class ResourceLoader
extends com.corrodinggames.rts.gameFramework.MusicPlayerBase {  // 02b java/l extends aq (MusicPlayerBase); MusicPlayer 错标修正
    volatile boolean a;
    public OpenALAudio b;
    boolean c = false;

    public Object f() {
        return this.b;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void a(float f2) {
        Object object = this.f();
        synchronized (object) {
            if (this.a) {
                return;
            }
            long l2 = ExtraManager.a();
            this.b.update();
            double d = ExtraManager.a(l2);
            if (d > 16.0) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("music poll took:" + ExtraManager.a(d));
            }
        }
        super.a(f2);
    }


    public void a(int n2) {
    }

    public ResourceLoader(OpenALAudio openALAudio) {
        this.b = openALAudio;
    }


    public GameTimer a(String string) {  // 02b l.java L46: a(String) 返回 ar, new m(var1, this) (v19.133f2 修正)
        return new MusicTrack(string, this);
    }


    public PacketBuilder a() {  // 02b l.java L50: a() 返回 as, new n(this) (v19.133f2 修正)
        DesktopMusicPlayer n2 = new DesktopMusicPlayer(this);
        return n2;
    }


    public void a(MusicController am2) {
        this.e = am2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void b() {
        Object object = this.f();
        synchronized (object) {
            this.a = true;
        }
    }


    public boolean c() {
        return true;
    }


    public boolean d() {
        return true;
    }


    public int e() {
        return 100;
    }
}
