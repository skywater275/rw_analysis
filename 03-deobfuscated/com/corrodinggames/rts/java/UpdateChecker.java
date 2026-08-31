/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import android.content.Context;
import com.corrodinggames.rts.gameFramework.platform.SoundFactory;
import com.corrodinggames.rts.gameFramework.platform.Sound;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ad;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import com.corrodinggames.rts.java.audio.backend.AudioSourceBase;
import com.corrodinggames.rts.java.audio.backend.c;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.LicenseValidator;
import com.corrodinggames.rts.java.PerformanceMonitor;
import com.corrodinggames.rts.java.ScreenshotCapture;
import java.util.concurrent.LinkedBlockingQueue;

public class UpdateChecker
extends com.corrodinggames.rts.gameFramework.platform.SoundFactory {
    final int a = 15;
    LinkedBlockingQueue b = new LinkedBlockingQueue();
    ad c = new ad(15);
    ScreenshotCapture d;
    Context e;
    public OpenALAudio f;

    public Object b() {
        return this.f;
    }

    public UpdateChecker(OpenALAudio openALAudio) {
        for (int k = 0; k < 15; ++k) {
            this.c.isEnabled(new LicenseValidator());  // 02b ad.a(Object) (v19.133f2)
        }
        this.f = openALAudio;
    }


    public void a(Context context) {
        if (this.e != null) {
            GlobalState.e("SlickSoundFactory:setContext context already set");
            return;
        }
        this.e = context;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public Sound a(int n2) {  // 02b o.java L41: a(int) 返回 a/i (Sound) (v19.133f2 修正)
        String string = GameUtils.f(n2);  // 02b f.f(int) (v19.133f2 修正)
        PerformanceMonitor q2 = new PerformanceMonitor(this, string, this);
        String string2 = GameUtils.f(n2);  // 02b f.f(int) (v19.133f2 修正)
        if (string2 == null) {
            throw new RuntimeException("Failed to find sound for res id:" + n2);
        }
        Object object = this.b();
        synchronized (object) {
            AudioSourceBase a2 = new AudioSourceBase(string2);  // 02b audio/a/a(String) (v19.133f2 修正)
            q2.a = this.f.newSound(a2);
        }
        return q2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public Sound a(String string, AssetStream j2, boolean bl) {  // 02b o.java L57: a(String, utility/j, boolean) 返回 a/i (v19.133f2 修正)
        UpdateChecker o2 = this;
        if (!bl) {
            o2 = null;
        }
        PerformanceMonitor q2 = new PerformanceMonitor(this, string, o2);
        try {
            Object object = this.b();
            synchronized (object) {
                q2.a = this.f.newSound(new AudioSourceBase(j2, j2.d()));  // 02b o L63: new audio/a/a(var2, var2.d()) (v19.133f2 修正)
            }
        }
        catch (com.corrodinggames.rts.java.audio.backend.c c2) {  // 02b o L65: audio/a/c (v19.133f2 修正)
            c2.printStackTrace();
            return null;
        }
        return q2;
    }

    @Override
    public void a() {
        if (this.d != null) {
            throw new RuntimeException("startThreads: soundThread!=null");
        }
        this.d = new ScreenshotCapture(this);
        this.d.start();
    }
}
