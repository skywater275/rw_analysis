/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnInfoListener
 */
package com.corrodinggames.rts.gameFramework;

import android.media.MediaPlayer;
import com.corrodinggames.rts.gameFramework.MusicPlayer;

class MusicPlayer$1
implements MediaPlayer.OnInfoListener {
    final /* synthetic */ MusicPlayer a;

    MusicPlayer$1(MusicPlayer ap2) {
        this.a = ap2;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int n, int n2) {
        return true;
    }
}
