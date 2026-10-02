/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.media.SoundPool
 */
package com.corrodinggames.rts.gameFramework.platform;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;

import android.content.Context;
import android.media.SoundPool;
import com.corrodinggames.rts.R$raw;
import com.corrodinggames.rts.gameFramework.platform.SoundInstance;
import com.corrodinggames.rts.gameFramework.platform.SoundPlayRequest;
import com.corrodinggames.rts.gameFramework.platform.SoundThread;
import com.corrodinggames.rts.gameFramework.platform.SoundFactory;
import com.corrodinggames.rts.gameFramework.platform.Sound;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ad;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class AndroidSoundFactory
extends SoundFactory {
    LinkedBlockingQueue a = new LinkedBlockingQueue();
    final int b = 15;
    ad c = new ad(15);
    SoundThread d;
    int e = 1000;
    Context f;
    SoundPool g;

    public AndroidSoundFactory() {
        for (int k = 0; k < 15; ++k) {
            this.c.isEnabled(new SoundPlayRequest());
        }
    }

    @Override
    public void a(Context context) {
        if (this.f != null) {
            GlobalState.e("AndroidSoundFactory:setContext context already set");
            return;
        }
        this.f = context;
        this.g = new SoundPool(16, 3, 0);
    }

    @Override
    public void a() {
        if (this.d != null) {
            throw new RuntimeException("soundThread!=null");
        }
        this.d = new SoundThread(this);
        this.d.start();
    }

    @Override
    public Sound a(int n2) {
        String string = com.corrodinggames.rts.gameFramework.GameUtils.a(com.corrodinggames.rts.R$raw.class, n2);
        SoundInstance b2 = new SoundInstance(this, string, this);
        b2.a = this;
        b2.b = this.g.load(this.f, n2, 1);
        return b2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Sound a(String string, AssetStream j2, boolean bl) {
        int n2;
        Object object;
        SoundFactory a2;
        block16: {
            long l2;
            long l3;
            FileDescriptor fileDescriptor;
            a2 = this;
            if (!bl) {
                a2 = null;
            }
            if (!j2.a()) {
                try {
                    object = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(this.f, "audio", "ogg");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream((File)object);
                        com.corrodinggames.rts.gameFramework.GameUtils.a(j2, fileOutputStream);
                        fileOutputStream.close();
                        try {
                            j2.close();
                        }
                        catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        try (FileInputStream fileInputStream = new FileInputStream((File)object);){
                            FileDescriptor fileDescriptor2 = fileInputStream.getFD();
                            long l4 = 0L;
                            long l5 = fileInputStream.available();
                            n2 = this.g.load(fileDescriptor2, l4, l5, 1);
                            break block16;
                        }
                    }
                    finally {
                        ((File)object).delete();
                    }
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    return null;
                }
            }
            try {
                fileDescriptor = j2.b();
                l3 = 0L;
                l2 = j2.available();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return null;
            }
            n2 = this.g.load(fileDescriptor, l3, l2, 1);
            try {
                j2.close();
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        SoundInstance soundInstance = new SoundInstance(this, string, a2);
        soundInstance.a = this;
        soundInstance.b = n2;
        return soundInstance;
    }
}
