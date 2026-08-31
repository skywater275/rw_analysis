/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.corrodinggames.rts.java.c.b;
import com.corrodinggames.rts.java.c.l;
import com.corrodinggames.rts.java.c.m;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class k
extends Socket {
    b a;
    boolean b = false;
    l c;
    m d;
    SteamID e;

    public k(b b2, SteamID steamID) {
        this.a = b2;
        this.e = steamID;
        this.c = new l(this);
        this.d = new m(this);
    }

    @Override
    public void bind(SocketAddress socketAddress) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public synchronized void close() {
        if (!this.b) {
            this.b = true;
            com.corrodinggames.rts.gameFramework.l.e("Closing steam socket");
        }
        if (this.c != null) {
            this.c.a(new byte[0]);
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
        return this.c;
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
        return this.d;
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
        return this.b;
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public boolean isInputShutdown() {
        return this.c != null;
    }

    @Override
    public boolean isOutputShutdown() {
        return this.d != null;
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
        return "<SteamSocket>";
    }
}
