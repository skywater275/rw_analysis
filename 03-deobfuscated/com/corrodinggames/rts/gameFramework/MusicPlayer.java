/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.res.AssetFileDescriptor
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnInfoListener
 *  android.media.MediaPlayer$OnPreparedListener
 */
package com.corrodinggames.rts.gameFramework;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.corrodinggames.rts.gameFramework.MusicFactory;
import com.corrodinggames.rts.gameFramework.GameInput;
import com.corrodinggames.rts.gameFramework.ServerAddress$1;
import com.corrodinggames.rts.gameFramework.ServerAddress$2;
import com.corrodinggames.rts.gameFramework.GameTimer;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MusicPlayer
extends PacketBuilder {
    MediaPlayer a;
    GameInput b;
    MusicFactory c;

    public MusicPlayer(MusicFactory an2) {
        this.c = an2;
        MediaPlayer mediaPlayer = null;
        if (an2.b.size() == 0) {
            throw new RuntimeException("Music player pool empty");
        }
        mediaPlayer = (MediaPlayer)an2.b.remove(0);
        an2.c.add(this);
        this.a = mediaPlayer;
    }


    public void a(GameTimer ar2) {
        this.b = (GameInput) ar2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void a(boolean bl) {
        try {
            MediaPlayer mediaPlayer = this.a;
            mediaPlayer.reset();
            AssetFileDescriptor assetFileDescriptor = null;
            if (this.b.b.startsWith("music")) {
                String string = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.e(this.b.b);
                try {
                    assetFileDescriptor = this.c.e.w.d().b(string);
                }
                catch (RuntimeException iOException) {
                    throw new RuntimeException(iOException);
                }
                mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            } else {
                String string = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.e(this.b.b);
                if (com.corrodinggames.rts.gameFramework.utility.ae.a(string) == null) {
                    mediaPlayer.setDataSource(string);
                } else {
                    AssetStream j2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.k(string);
                    if (j2 == null) {
                        throw new RuntimeException("openAssetSteam() null for '" + string + "'");
                    }
                    File file = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(this.c.e.w, "music", "ogg");
                    GlobalState.e("Temp file needed for this music from zipped/abstract mod file");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        GameUtils.a(j2, fileOutputStream);
                        fileOutputStream.close();
                        j2.close();
                        try (FileInputStream fileInputStream = new FileInputStream(file);){
                            mediaPlayer.setDataSource(fileInputStream.getFD(), 0L, (long)fileInputStream.available());
                        }
                    }
                    finally {
                        file.delete();
                    }
                }
            }
            if (bl) {
                mediaPlayer.setLooping(true);
            }
            mediaPlayer.setVolume(0.0f, 0.0f);
            mediaPlayer.setOnInfoListener((MediaPlayer.OnInfoListener)new MusicPlayer$1(this));
            mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener)new MusicPlayer$2(this));
            mediaPlayer.prepareAsync();
            if (assetFileDescriptor != null) {
                assetFileDescriptor.close();
            }
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public void a() {
        this.a.pause();
    }


    public void b() {
        this.a.start();
    }


    public boolean c() {
        return this.a.isPlaying();
    }


    public void d() {
        if (this.a != null) {
            this.a.stop();
        }
    }


    public void e() {
        if (this.a != null) {
            this.a.stop();
        }
        this.a = null;
        this.c.c.remove(this);
        this.c.b.add(this.a);
    }


    public void a(float f2) {
        this.a.setVolume(f2, f2);
    }
}
