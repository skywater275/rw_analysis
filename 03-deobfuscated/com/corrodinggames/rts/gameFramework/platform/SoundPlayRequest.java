/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.platform;

import com.corrodinggames.rts.gameFramework.platform.SoundInstance;

public class SoundPlayRequest {
    SoundInstance a;  // 02b PlatformInput 幻觉名 (f88)
    float b;
    float c;
    int d;
    int e;
    float f;

    public void a() {
        this.a.b(this.b, this.c, this.d, this.e, this.f);
    }
}
