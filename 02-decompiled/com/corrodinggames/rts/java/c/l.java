/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.c;

import com.corrodinggames.rts.java.c.k;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;

public class l
extends InputStream {
    LinkedBlockingDeque a = new LinkedBlockingDeque();
    boolean b = true;
    byte[] c = new byte[1];
    final /* synthetic */ k d;

    public l(k k2) {
        this.d = k2;
    }

    public void a(byte[] byArray) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(byArray);
        this.a.add(byteBuffer);
    }

    @Override
    public int read() {
        int n;
        while ((n = this.read(this.c, 0, 1)) <= 0) {
        }
        int n2 = this.c[0] & 0xFF;
        return n2;
    }

    @Override
    public int read(byte[] byArray) {
        return this.read(byArray, 0, byArray.length);
    }

    @Override
    public int read(byte[] byArray, int n2, int n3) {
        if (this.d.b) {
            throw new IOException("closed");
        }
        int n4 = 0;
        int n5 = n3;
        int n6 = n2;
        while (true) {
            int n7;
            ByteBuffer byteBuffer;
            if (this.d.b) {
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
                com.corrodinggames.rts.gameFramework.l.e("First packet from:" + this.d.e);
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
