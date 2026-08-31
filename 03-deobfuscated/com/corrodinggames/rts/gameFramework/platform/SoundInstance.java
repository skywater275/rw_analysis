/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.platform;

import android.util.Log;
import com.corrodinggames.rts.gameFramework.platform.AndroidSoundFactory;
import com.corrodinggames.rts.gameFramework.platform.SoundPlayRequest;
import com.corrodinggames.rts.gameFramework.platform.SoundFactory;
import com.corrodinggames.rts.gameFramework.platform.Sound;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class SoundInstance
extends Sound {
    AndroidSoundFactory a;
    int b;
    final /* synthetic */ AndroidSoundFactory c;

    public SoundInstance(AndroidSoundFactory a2, String string, SoundFactory h2) {
        super(string, h2);
        this.c = a2;
        this.b = -1;
    }

    @Override
    public void a(float f2, float f3, int n2, int n3, float f4) {
        SoundPlayRequest c2 = (SoundPlayRequest) this.c.c.isEnabled();
        if (c2 == null) {
            return;
        }
        c2.b = f2;
        c2.c = f3;
        c2.e = n3;
        c2.f = f4;
        c2.a = this;
        boolean bl = false;
        GlobalState l2 = GlobalState.B();
        if (l2 != null && l2.bQ != null && l2.bQ.androidNoSoundPrioritiesDebug) {
            bl = true;
        }
        if (bl) {
            c2.d = 0;
        } else {
            --this.c.e;
            if (this.c.e <= 1) {
                this.c.e = 1000;
            }
            c2.d = this.c.e;
        }
        this.c.a.offer(c2);
    }

    public void b(float f2, float f3, int n2, int n3, float f4) {
        if (this.b == 0) {
            Log.d("RustedWarfare", "Sound not loaded");
            return;
        }
        int n4 = this.a.g.play(this.b, f2, f3, n2, n3, f4);
        if (n4 == 0) {
            // empty if block
        }
    }

    @Override
    public int a() {
        return 0;
    }
}
