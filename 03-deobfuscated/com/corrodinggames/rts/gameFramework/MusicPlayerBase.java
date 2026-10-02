/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

// v19.132 新建: 02b gameFramework/aq.java (35 行) 抽象音乐播放器基类直译
// 03 的 MusicPlayer.java = 02b ap (extends PacketBuilder MediaPlayer 实现),
// aq 基类缺失导致 MusicController.a (02b am: static aq a) 类型错配 void d()
public abstract class MusicPlayerBase {
    protected MusicController e;  // 02b aq.java L9: protected am e (am=MusicController)

    public void a(int n2) {}

    public void a(float f2) {}

    public abstract GameTimer a(String string);

    public abstract PacketBuilder a();

    public abstract void a(MusicController am2);

    public abstract void b();

    public boolean c() {
        return false;
    }

    public boolean d() {
        return true;
    }

    public int e() {
        return 0;
    }
}
