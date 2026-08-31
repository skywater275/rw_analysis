/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.platform.SoundFactory;
import com.corrodinggames.rts.gameFramework.platform.Sound;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;

import com.corrodinggames.rts.java.UpdateChecker;
import com.corrodinggames.rts.java.LicenseValidator;

public class PerformanceMonitor
extends com.corrodinggames.rts.gameFramework.platform.Sound {
    com.corrodinggames.rts.java.audio.Sound a;  // 02b q.java: java/audio/Sound (v19.133f2 修正)
    final /* synthetic */ UpdateChecker b;

    public PerformanceMonitor(UpdateChecker o2, String string, SoundFactory h2) {  // 02b q.java: q(o, String, a/h) (v19.133f2 修正)
        super(string, h2);
        this.b = o2;
    }


    public void a(float f, float f2, int n, int n2, float f3) {
        LicenseValidator p2 = (LicenseValidator) this.b.c.isEnabled();  // 02b ad.a() (v19.133f2 修正)
        if (p2 == null) {
            return;
        }
        p2.b = f;
        p2.c = f2;
        p2.d = n;
        p2.e = n2;
        p2.f = f3;
        p2.a = this;
        this.b.b.offer(p2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(float f2, float f3, int n2, int n3, float f4) {
        if (this.a == null) {
            GlobalState.e("Sound not loaded");
            return;
        }
        Object object = this.b.b();
        synchronized (object) {
            float f5 = 0.0f;
            float f6 = GameUtils.f(f2, f3);  // 02b f.f(float,float) (v19.133f2 修正)
            this.a.play(f6, f4, f5);
        }
    }


    public int a() {
        return this.a.getBytesUsed();
    }
}
