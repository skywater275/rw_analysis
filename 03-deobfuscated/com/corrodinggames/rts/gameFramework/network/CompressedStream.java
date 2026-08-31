/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public strictfp class CompressedStream {
    public GZIPOutputStream gzipOutputStream;
    public BufferedOutputStream bufferedOutputStream;
    public String fileName;
    public ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
    public DataOutputStream dataOutputStream;
    public boolean isClosed = false;

    /* 02b j/at.java L19: flush/finish 抛 IOException */
    public void a() throws IOException {
        this.dataOutputStream.flush();
        if (this.bufferedOutputStream != null) {
            this.bufferedOutputStream.flush();
        }
        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.finish();
        }
    }

    /* 02b j/at.java L31: close 抛 IOException */
    public void b() throws IOException {
        if (!this.isClosed) {
            this.dataOutputStream.close();
        } else {
            GlobalState.isKeyJustPressed("TODO: Cannot yet close wrapped stream");
        }
    }

    /* 02b j/at.java L40: GZIPOutputStream 构造抛 IOException */
    public CompressedStream(boolean bl) throws IOException {
        OutputStream outputStream;
        if (bl) {
            this.gzipOutputStream = new GZIPOutputStream(this.byteArrayStream);
            this.bufferedOutputStream = new BufferedOutputStream(this.gzipOutputStream);
            outputStream = this.bufferedOutputStream;
        } else {
            outputStream = this.byteArrayStream;
        }
        this.dataOutputStream = new DataOutputStream(outputStream);
    }
}
