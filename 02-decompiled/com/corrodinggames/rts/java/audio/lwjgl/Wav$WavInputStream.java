/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.s;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;

public class Wav$WavInputStream
extends FilterInputStream {
    public int channels;
    public int sampleRate;
    public int dataRemaining;

    public Wav$WavInputStream(a a2) {
        super(a2.a());
        try {
            if (this.read() != 82 || this.read() != 73 || this.read() != 70 || this.read() != 70) {
                throw new c("RIFF header not found: " + a2);
            }
            this.skipFully(4);
            if (this.read() != 87 || this.read() != 65 || this.read() != 86 || this.read() != 69) {
                throw new c("Invalid wave file header: " + a2);
            }
            int n = this.seekToChunk('f', 'm', 't', ' ');
            int n2 = this.read() & 0xFF | (this.read() & 0xFF) << 8;
            if (n2 != 1) {
                String string;
                switch (n2) {
                    case 2: {
                        string = "ADPCM";
                        break;
                    }
                    case 3: {
                        string = "IEEE float";
                        break;
                    }
                    case 6: {
                        string = "8-bit ITU-T G.711 A-law";
                        break;
                    }
                    case 7: {
                        string = "8-bit ITU-T G.711 u-law";
                        break;
                    }
                    case 65534: {
                        string = "Extensible";
                        break;
                    }
                    default: {
                        string = "Unknown";
                    }
                }
                throw new c("WAV files must be PCM, unsupported format: " + string + " (" + n2 + ")");
            }
            this.channels = this.read() & 0xFF | (this.read() & 0xFF) << 8;
            if (this.channels != 1 && this.channels != 2) {
                throw new c("WAV files must have 1 or 2 channels: " + this.channels);
            }
            this.sampleRate = this.read() & 0xFF | (this.read() & 0xFF) << 8 | (this.read() & 0xFF) << 16 | (this.read() & 0xFF) << 24;
            this.skipFully(6);
            int n3 = this.read() & 0xFF | (this.read() & 0xFF) << 8;
            if (n3 != 16) {
                throw new c("WAV files must have 16 bits per sample: " + n3);
            }
            this.skipFully(n - 16);
            this.dataRemaining = this.seekToChunk('d', 'a', 't', 'a');
        }
        catch (Throwable throwable) {
            s.a(this);
            throw new c("Error reading WAV file: " + a2, throwable);
        }
    }

    private int seekToChunk(char c2, char c3, char c4, char c5) {
        while (true) {
            boolean bl = this.read() == c2;
            bl &= this.read() == c3;
            bl &= this.read() == c4;
            bl &= this.read() == c5;
            int n = this.read() & 0xFF | (this.read() & 0xFF) << 8 | (this.read() & 0xFF) << 16 | (this.read() & 0xFF) << 24;
            if (n == -1) {
                throw new IOException("Chunk not found: " + c2 + c3 + c4 + c5);
            }
            if (bl) {
                return n;
            }
            this.skipFully(n);
        }
    }

    private void skipFully(int n) {
        while (n > 0) {
            long l = this.in.skip(n);
            if (l <= 0L) {
                throw new EOFException("Unable to skip.");
            }
            n = (int)((long)n - l);
        }
    }

    @Override
    public int read(byte[] byArray) {
        if (this.dataRemaining == 0) {
            return -1;
        }
        int n = Math.min(super.read(byArray), this.dataRemaining);
        if (n == -1) {
            return -1;
        }
        this.dataRemaining -= n;
        return n;
    }
}
