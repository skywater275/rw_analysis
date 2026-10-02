/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.ay;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.i;
import com.corrodinggames.rts.gameFramework.j.j;
import com.corrodinggames.rts.gameFramework.l;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class h
extends Socket {
    c a;
    int b;
    boolean c = false;
    i d;
    j e;

    public void a(au au2) {
        this.a.a(new ay(this.b, au2));
    }

    public h(c c2, int n) {
        this.a = c2;
        this.b = n;
        this.d = new i(this);
        this.e = new j(this);
    }

    @Override
    public void bind(SocketAddress socketAddress) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public synchronized void close() {
        if (!this.c) {
            this.c = true;
            l.e("Closing steam socket");
        }
        if (this.d != null) {
            this.d.a(new byte[0]);
        }
    }

    @Override
    public void connect(SocketAddress socketAddress, int n2) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public void connect(SocketAddress socketAddress) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public SocketChannel getChannel() {
        throw new RuntimeException("Not supported");
    }

    @Override
    public InetAddress getInetAddress() {
        return null;
    }

    @Override
    public InetAddress getLocalAddress() {
        return null;
    }

    @Override
    public SocketAddress getLocalSocketAddress() {
        return null;
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        return null;
    }

    @Override
    public InputStream getInputStream() {
        return this.d;
    }

    @Override
    public boolean getKeepAlive() {
        return true;
    }

    @Override
    public int getLocalPort() {
        return 5555;
    }

    @Override
    public boolean getOOBInline() {
        return false;
    }

    @Override
    public OutputStream getOutputStream() {
        return this.e;
    }

    @Override
    public int getPort() {
        return 5555;
    }

    @Override
    public synchronized int getReceiveBufferSize() {
        return 512;
    }

    @Override
    public boolean getReuseAddress() {
        return false;
    }

    @Override
    public synchronized int getSendBufferSize() {
        return 512;
    }

    @Override
    public int getSoLinger() {
        return 0;
    }

    @Override
    public synchronized int getSoTimeout() {
        return 0;
    }

    @Override
    public boolean getTcpNoDelay() {
        return true;
    }

    @Override
    public int getTrafficClass() {
        return 0;
    }

    @Override
    public boolean isBound() {
        return true;
    }

    @Override
    public boolean isClosed() {
        return this.c;
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public boolean isInputShutdown() {
        return this.d != null;
    }

    @Override
    public boolean isOutputShutdown() {
        return this.e != null;
    }

    @Override
    public void sendUrgentData(int n2) {
    }

    @Override
    public void setKeepAlive(boolean bl) {
    }

    @Override
    public void setOOBInline(boolean bl) {
    }

    @Override
    public void setPerformancePreferences(int n2, int n3, int n4) {
    }

    @Override
    public synchronized void setReceiveBufferSize(int n2) {
    }

    @Override
    public void setReuseAddress(boolean bl) {
    }

    @Override
    public synchronized void setSendBufferSize(int n2) {
    }

    @Override
    public void setSoLinger(boolean bl, int n2) {
    }

    @Override
    public synchronized void setSoTimeout(int n2) {
    }

    @Override
    public void setTcpNoDelay(boolean bl) {
    }

    @Override
    public void setTrafficClass(int n2) {
    }

    @Override
    public void shutdownInput() {
    }

    @Override
    public void shutdownOutput() {
    }

    @Override
    public String toString() {
        return "<ForwardedSocket>";
    }
}
