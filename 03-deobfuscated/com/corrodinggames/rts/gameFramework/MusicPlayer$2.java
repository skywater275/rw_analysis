/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnPreparedListener
 */
package com.corrodinggames.rts.gameFramework;

import android.media.MediaPlayer;
import com.corrodinggames.rts.gameFramework.MusicPlayer;

class MusicPlayer$2
implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ MusicPlayer a;

    MusicPlayer$2(MusicPlayer ap2) {
        this.a = ap2;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
