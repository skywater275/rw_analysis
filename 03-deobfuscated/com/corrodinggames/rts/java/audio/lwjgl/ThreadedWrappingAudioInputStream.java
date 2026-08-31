/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadedWrappingAudioInputStream
extends InputStream {
    InputStream source;
    PipedInputStream bufferInputStream;
    PipedOutputStream bufferOutputStream;
    byte[] transferBuffer = new byte[1024];
    boolean isClosed = false;
    boolean sourceEnded = false;
    final int bufferSize = 60000;
    int totalBytesRead;

    public ThreadedWrappingAudioInputStream(InputStream inputStream) {
        this.source = inputStream;
        try {
            this.bufferOutputStream = new PipedOutputStream();
            this.bufferInputStream = new PipedInputStream(this.bufferOutputStream, 60000);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void backgroundFillBuffer() throws IOException {
        GlobalState.e("at backgroundFillBuffer with: " + this.bufferInputStream.available());
        if (this.sourceEnded) {
            return;
        }
        int n2 = 59999;
        while (!this.isClosed) {
            int n3 = n2 - this.bufferInputStream.available();
            if (n3 < this.transferBuffer.length) {
                return;
            }
            int n4 = this.source.read(this.transferBuffer);
            if (n4 == -1) {
                this.sourceEnded = true;
                this.bufferOutputStream.close();
                return;
            }
            this.totalBytesRead += n4;
            this.bufferOutputStream.write(this.transferBuffer, 0, n4);
        }
    }

    @Override
    public int read() {
        try {
            if (this.bufferInputStream.available() == 0 && !this.sourceEnded) {
                GlobalState.e("Exhausted stream");
            }
            return this.bufferInputStream.read();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    @Override
    public int read(byte[] byArray, int n2, int n3) {
        try {
            if (this.bufferInputStream.available() == 0 && !this.sourceEnded) {
                GlobalState.e("Exhausted stream");
            }
            return this.bufferInputStream.read(byArray, n2, n3);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    @Override
    public int read(byte[] byArray) {
        return this.read(byArray, 0, byArray.length);
    }

    @Override
    public void close() {
        this.isClosed = true;
    }
}
