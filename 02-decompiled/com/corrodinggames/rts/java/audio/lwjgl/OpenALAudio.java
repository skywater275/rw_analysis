/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.openal.AL
 *  org.lwjgl.openal.AL10
 *  org.lwjgl.openal.OpenALException
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.Audio;
import com.corrodinggames.rts.java.audio.AudioDevice;
import com.corrodinggames.rts.java.audio.AudioRecorder;
import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.d;
import com.corrodinggames.rts.java.audio.a.e;
import com.corrodinggames.rts.java.audio.a.i;
import com.corrodinggames.rts.java.audio.a.m;
import com.corrodinggames.rts.java.audio.a.o;
import com.corrodinggames.rts.java.audio.lwjgl.JavaSoundAudioRecorder;
import com.corrodinggames.rts.java.audio.lwjgl.Ogg$Music;
import com.corrodinggames.rts.java.audio.lwjgl.Ogg$Sound;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio$1;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio$2;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudioDevice;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALMusic;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALSound;
import com.corrodinggames.rts.java.audio.lwjgl.Wav$Music;
import com.corrodinggames.rts.java.audio.lwjgl.Wav$Sound;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Locale;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.OpenALException;

public class OpenALAudio
implements Audio {
    private final int deviceBufferSize;
    private final int deviceBufferCount;
    private d idleSources;
    private d allSources;
    private i soundIdToSource;
    private e sourceToSoundId;
    private long nextSoundId = 0L;
    private o extensionToSoundClass = new o();
    private o extensionToMusicClass = new o();
    private OpenALSound[] recentSounds;
    private int mostRecetSound = -1;
    ArrayList music = new ArrayList();
    boolean noDevice = false;

    public OpenALAudio() {
        this(16, 9, 512);
    }

    public OpenALAudio(int n, int n2, int n3) {
        this.deviceBufferSize = n3;
        this.deviceBufferCount = n2;
        this.registerSound("ogg", Ogg.Sound.class);
        this.registerMusic("ogg", Ogg.Music.class);
        this.registerSound("wav", Wav.Sound.class);
        this.registerMusic("wav", Wav.Music.class);
        try {
            AL.create();
        }
        catch (LWJGLException lWJGLException) {
            this.noDevice = true;
            lWJGLException.printStackTrace();
            return;
        }
        catch (OpenALException openALException) {
            this.noDevice = true;
            openALException.printStackTrace();
            return;
        }
        catch (NullPointerException nullPointerException) {
            this.noDevice = true;
            nullPointerException.printStackTrace();
            return;
        }
        this.allSources = new d(false, n);
        for (int k = 0; k < n; ++k) {
            int n4 = AL10.alGenSources();
            if (AL10.alGetError() != 0) break;
            this.allSources.a(n4);
        }
        this.idleSources = new d(this.allSources);
        this.soundIdToSource = new i();
        this.sourceToSoundId = new e();
        FloatBuffer floatBuffer = (FloatBuffer)BufferUtils.createFloatBuffer((int)6).put(new float[]{0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f}).flip();
        AL10.alListener((int)4111, (FloatBuffer)floatBuffer);
        FloatBuffer floatBuffer2 = (FloatBuffer)BufferUtils.createFloatBuffer((int)3).put(new float[]{0.0f, 0.0f, 0.0f}).flip();
        AL10.alListener((int)4102, (FloatBuffer)floatBuffer2);
        FloatBuffer floatBuffer3 = (FloatBuffer)BufferUtils.createFloatBuffer((int)3).put(new float[]{0.0f, 0.0f, 0.0f}).flip();
        AL10.alListener((int)4100, (FloatBuffer)floatBuffer3);
        this.recentSounds = new OpenALSound[n];
    }

    public void registerSound(String string, Class clazz) {
        if (string == null) {
            throw new IllegalArgumentException("extension cannot be null.");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("soundClass cannot be null.");
        }
        this.extensionToSoundClass.a((Object)string, clazz);
    }

    public void registerMusic(String string, Class clazz) {
        if (string == null) {
            throw new IllegalArgumentException("extension cannot be null.");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("musicClass cannot be null.");
        }
        this.extensionToMusicClass.a((Object)string, clazz);
    }

    @Override
    public OpenALSound newSound(a a2) {
        if (a2 == null) {
            throw new IllegalArgumentException("file cannot be null.");
        }
        Class clazz = (Class)this.extensionToSoundClass.a(a2.b().toLowerCase(Locale.ROOT));
        if (clazz == null) {
            throw new c("Unknown file extension for sound: " + a2);
        }
        try {
            return (OpenALSound)clazz.getConstructor(OpenALAudio.class, a.class).newInstance(this, a2);
        }
        catch (Exception exception) {
            throw new c("Error creating sound " + clazz.getName() + " for file: " + a2, exception);
        }
    }

    @Override
    public OpenALMusic newMusic(a a2) {
        if (a2 == null) {
            throw new IllegalArgumentException("file cannot be null.");
        }
        Class clazz = (Class)this.extensionToMusicClass.a(a2.b().toLowerCase(Locale.ROOT));
        if (clazz == null) {
            throw new c("Unknown file extension for music: " + a2);
        }
        try {
            return (OpenALMusic)clazz.getConstructor(OpenALAudio.class, a.class).newInstance(this, a2);
        }
        catch (Exception exception) {
            throw new c("Error creating music " + clazz.getName() + " for file: " + a2, exception);
        }
    }

    int obtainSource(boolean bl) {
        if (this.noDevice) {
            return 0;
        }
        int n = this.idleSources.b;
        for (int k = 0; k < n; ++k) {
            int n2 = this.idleSources.b(k);
            int n3 = AL10.alGetSourcei((int)n2, (int)4112);
            if (n3 == 4114 || n3 == 4115) continue;
            if (bl) {
                this.idleSources.c(k);
            } else {
                long l;
                if (this.sourceToSoundId.e(n2)) {
                    l = (Long)this.sourceToSoundId.a(n2);
                    this.sourceToSoundId.b(n2);
                    this.soundIdToSource.b(l);
                }
                l = this.nextSoundId++;
                this.sourceToSoundId.a(n2, l);
                this.soundIdToSource.a(l, n2);
            }
            AL10.alSourceStop((int)n2);
            AL10.alSourcei((int)n2, (int)4105, (int)0);
            AL10.alSourcef((int)n2, (int)4106, (float)1.0f);
            AL10.alSourcef((int)n2, (int)4099, (float)1.0f);
            AL10.alSource3f((int)n2, (int)4100, (float)0.0f, (float)0.0f, (float)1.0f);
            return n2;
        }
        return -1;
    }

    void freeSource(int n) {
        if (this.noDevice) {
            return;
        }
        AL10.alSourceStop((int)n);
        AL10.alSourcei((int)n, (int)4105, (int)0);
        if (this.sourceToSoundId.e(n)) {
            long l = (Long)this.sourceToSoundId.b(n);
            this.soundIdToSource.b(l);
        }
        this.idleSources.a(n);
    }

    void freeBuffer(int n) {
        if (this.noDevice) {
            return;
        }
        int n2 = this.idleSources.b;
        for (int k = 0; k < n2; ++k) {
            int n3 = this.idleSources.b(k);
            if (AL10.alGetSourcei((int)n3, (int)4105) != n) continue;
            if (this.sourceToSoundId.e(n3)) {
                long l = (Long)this.sourceToSoundId.b(n3);
                this.soundIdToSource.b(l);
            }
            AL10.alSourceStop((int)n3);
            AL10.alSourcei((int)n3, (int)4105, (int)0);
        }
    }

    void stopSourcesWithBuffer(int n) {
        if (this.noDevice) {
            return;
        }
        int n2 = this.idleSources.b;
        for (int k = 0; k < n2; ++k) {
            int n3 = this.idleSources.b(k);
            if (AL10.alGetSourcei((int)n3, (int)4105) != n) continue;
            if (this.sourceToSoundId.e(n3)) {
                long l = (Long)this.sourceToSoundId.b(n3);
                this.soundIdToSource.b(l);
            }
            AL10.alSourceStop((int)n3);
        }
    }

    void pauseSourcesWithBuffer(int n) {
        if (this.noDevice) {
            return;
        }
        int n2 = this.idleSources.b;
        for (int k = 0; k < n2; ++k) {
            int n3 = this.idleSources.b(k);
            if (AL10.alGetSourcei((int)n3, (int)4105) != n) continue;
            AL10.alSourcePause((int)n3);
        }
    }

    void resumeSourcesWithBuffer(int n) {
        if (this.noDevice) {
            return;
        }
        int n2 = this.idleSources.b;
        for (int k = 0; k < n2; ++k) {
            int n3 = this.idleSources.b(k);
            if (AL10.alGetSourcei((int)n3, (int)4105) != n || AL10.alGetSourcei((int)n3, (int)4112) != 4115) continue;
            AL10.alSourcePlay((int)n3);
        }
    }

    public void backgroundUpdate() {
        if (this.noDevice) {
            return;
        }
        for (int k = 0; k < this.music.size(); ++k) {
            ((OpenALMusic)this.music.get(k)).backgroundUpdate();
        }
    }

    public boolean hasDevice() {
        return this.noDevice;
    }

    public void update() {
        if (this.noDevice) {
            return;
        }
        for (int k = 0; k < this.music.size(); ++k) {
            ((OpenALMusic)this.music.get(k)).update();
        }
    }

    public long getSoundId(int n) {
        if (!this.sourceToSoundId.e(n)) {
            return -1L;
        }
        return (Long)this.sourceToSoundId.a(n);
    }

    public void stopSound(long l) {
        if (!this.soundIdToSource.d(l)) {
            return;
        }
        int n = (Integer)this.soundIdToSource.a(l);
        AL10.alSourceStop((int)n);
    }

    public void pauseSound(long l) {
        if (!this.soundIdToSource.d(l)) {
            return;
        }
        int n = (Integer)this.soundIdToSource.a(l);
        AL10.alSourcePause((int)n);
    }

    public void resumeSound(long l) {
        if (!this.soundIdToSource.d(l)) {
            return;
        }
        int n = (Integer)this.soundIdToSource.a(l);
        if (AL10.alGetSourcei((int)n, (int)4112) == 4115) {
            AL10.alSourcePlay((int)n);
        }
    }

    public void setSoundGain(long l, float f2) {
        if (!this.soundIdToSource.d(l)) {
            return;
        }
        int n = (Integer)this.soundIdToSource.a(l);
        AL10.alSourcef((int)n, (int)4106, (float)f2);
    }

    public void setSoundLooping(long l, boolean bl) {
        if (!this.soundIdToSource.d(l)) {
            return;
        }
        int n = (Integer)this.soundIdToSource.a(l);
        AL10.alSourcei((int)n, (int)4103, (int)(bl ? 1 : 0));
    }

    public void setSoundPitch(long l, float f2) {
        if (!this.soundIdToSource.d(l)) {
            return;
        }
        int n = (Integer)this.soundIdToSource.a(l);
        AL10.alSourcef((int)n, (int)4099, (float)f2);
    }

    public void setSoundPan(long l, float f2, float f3) {
        if (!this.soundIdToSource.d(l)) {
            return;
        }
        int n = (Integer)this.soundIdToSource.a(l);
        AL10.alSource3f((int)n, (int)4100, (float)m.b((f2 - 1.0f) * (float)Math.PI / 2.0f), (float)0.0f, (float)m.a((f2 + 1.0f) * (float)Math.PI / 2.0f));
        AL10.alSourcef((int)n, (int)4106, (float)f3);
    }

    public void dispose() {
        if (this.noDevice) {
            return;
        }
        int n = this.allSources.b;
        for (int k = 0; k < n; ++k) {
            int n2 = this.allSources.b(k);
            int n3 = AL10.alGetSourcei((int)n2, (int)4112);
            if (n3 != 4116) {
                AL10.alSourceStop((int)n2);
            }
            AL10.alDeleteSources((int)n2);
        }
        this.sourceToSoundId.a();
        this.soundIdToSource.a();
        AL.destroy();
        while (AL.isCreated()) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    @Override
    public AudioDevice newAudioDevice(int n, boolean bl) {
        if (this.noDevice) {
            return new OpenALAudio$1(this, bl);
        }
        return new OpenALAudioDevice(this, n, bl, this.deviceBufferSize, this.deviceBufferCount);
    }

    @Override
    public AudioRecorder newAudioRecorder(int n, boolean bl) {
        if (this.noDevice) {
            return new OpenALAudio$2(this);
        }
        return new JavaSoundAudioRecorder(n, bl);
    }

    protected void retain(OpenALSound openALSound, boolean bl) {
        ++this.mostRecetSound;
        this.mostRecetSound %= this.recentSounds.length;
        if (bl && this.recentSounds[this.mostRecetSound] != null) {
            this.recentSounds[this.mostRecetSound].stop();
        }
        this.recentSounds[this.mostRecetSound] = openALSound;
    }

    public void forget(OpenALSound openALSound) {
        for (int k = 0; k < this.recentSounds.length; ++k) {
            if (this.recentSounds[k] != openALSound) continue;
            this.recentSounds[k] = null;
        }
    }
}
