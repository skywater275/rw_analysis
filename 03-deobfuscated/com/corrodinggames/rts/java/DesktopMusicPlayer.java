/*
 * v19.133f2: 02b java/n.java 全文直译 (桌面音乐播放器, extends as=PacketBuilder)
 * 注意: 02b gameFramework/m/n (空 Renderer) = 03 NullRenderer, 与本类不同
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.GameTimer;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.java.ResourceLoader;
import com.corrodinggames.rts.java.MusicTrack;
import com.corrodinggames.rts.java.audio.Music;

public class DesktopMusicPlayer extends PacketBuilder {  // 02b java/n extends as

    MusicTrack a;  // 02b m a
    ResourceLoader b;  // 02b l b
    Music c;  // 02b Music c
    boolean d = false;
    boolean e = false;
    boolean f = false;

    public DesktopMusicPlayer(ResourceLoader l2) {  // 02b n(l)
        this.b = l2;
    }

    public void a(GameTimer ar2) {  // 02b n.a(ar): L23
        this.a = (MusicTrack) ar2;
    }

    public void a(boolean bl) {  // 02b n.a(boolean): L27
        synchronized (this.b.f()) {
            this.d = true;
            this.e = bl;
            this.f = false;
            GlobalState.e("Queued:" + this.a.b);
            if (this.c != null) {
                GlobalState.e("startPlaying: Stopping old music");
                this.c.stop();
            }
            this.c = this.a.c;
        }
    }

    public void f() {  // 02b n.f(): L42
        if (!this.f) {
            synchronized (this.b.f()) {
                if (this.c != null) {
                    GlobalState.e("Now playing:" + this.a.b);
                    if (this.e) {
                        this.c.setVolume(this.c.getVolume());
                        this.c.setLooping(true);
                        this.c.play();
                    } else {
                        this.c.setVolume(this.c.getVolume());
                        this.c.play();
                    }
                    this.f = true;
                } else {
                    GlobalState.e("realPlay: playingMusic==null");
                }
            }
        }
    }

    public void a() {  // 02b n.a(): L65 (暂停)
        synchronized (this.b.f()) {
            if (this.c != null) {
                this.c.pause();
            }
        }
    }

    public void b() {  // 02b n.b(): L74 (恢复)
        synchronized (this.b.f()) {
            if (this.c != null && !this.c.isPlaying()) {
                this.c.play();
            }
        }
    }

    public void d() {  // 02b n.d(): L83 (停止并清空)
        synchronized (this.b.f()) {
            if (this.c != null) {
                this.c.stop();
                this.f = false;
                this.d = false;
                this.c = null;
            }
        }
    }

    public void e() {  // 02b n.e(): L95 (停止)
        synchronized (this.b.f()) {
            if (this.c != null) {
                this.c.stop();
            }
        }
    }

    public boolean c() {  // 02b n.c(): L104
        synchronized (this.b.f()) {
            return this.f && this.c != null ? this.c.isPlaying() : false;
        }
    }

    public void a(float f2) {  // 02b n.a(float): L110 (音量)
        synchronized (this.b.f()) {
            if (this.c != null) {
                if (f2 > 0.05f && !this.f && this.d) {
                    this.f();
                }
                this.c.setVolume(f2);
            } else {
                GlobalState.e("setVolume: playingMusic==null");
            }
        }
    }
}
