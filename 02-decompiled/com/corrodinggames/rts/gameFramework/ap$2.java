/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnPreparedListener
 */
package com.corrodinggames.rts.gameFramework;

import android.media.MediaPlayer;
import com.corrodinggames.rts.gameFramework.ap;

class ap$2
implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ ap a;

    ap$2(ap ap2) {
        this.a = ap2;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
