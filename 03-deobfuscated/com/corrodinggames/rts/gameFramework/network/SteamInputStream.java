/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.OSEnum;

import com.corrodinggames.rts.gameFramework.network.SteamSocket;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;

public class SteamInputStream
extends InputStream {
    LinkedBlockingDeque a = new LinkedBlockingDeque();
    boolean b = true;
    byte[] c = new byte[1];
    final /* synthetic */ SteamSocket d;

    public SteamInputStream(SteamSocket h2) {
        this.d = h2;
    }

    public void a(byte[] byArray) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(byArray);
        this.a.add(byteBuffer);
    }

    @Override
    /* 覆写 InputStream.read (声明 throws IOException) */
    public int read() throws IOException {
        int n;
        while ((n = this.read(this.c, 0, 1)) <= 0) {
        }
        int n2 = this.c[0] & 0xFF;
        return n2;
    }

    @Override
    /* 覆写 InputStream.read(byte[]) */
    public int read(byte[] byArray) throws IOException {
        return this.read(byArray, 0, byArray.length);
    }

    @Override
    /* 覆写 InputStream.read(byte[],int,int) */
    public int read(byte[] byArray, int n2, int n3) throws IOException {
        if (this.d.c) {
            throw new IOException("closed");
        }
        int n4 = 0;
        int n5 = n3;
        int n6 = n2;
        while (true) {
            int n7;
            ByteBuffer byteBuffer;
            if (this.d.c) {
                throw new IOException("Closed");
            }
            try {
                byteBuffer = (ByteBuffer)this.a.take();
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
                return n4;
            }
            if (byteBuffer == null) continue;
            if (this.b) {
                this.b = false;
                GlobalState.e("First packet from forwarded:" + this.d.b);
            }
            if (byteBuffer.remaining() <= n5) {
                n7 = byteBuffer.remaining();
                byteBuffer.get(byArray, n6, n7);
            } else {
                n7 = n5;
                byteBuffer.get(byArray, n6, n5);
                this.a.addFirst(byteBuffer);
            }
            n4 += n7;
            n6 += n7;
            if ((n5 -= n7) < 0) {
                throw new IOException("bytesNeeded<0:" + n5);
            }
            if (n5 == 0) break;
        }
        return n4;
    }
}
