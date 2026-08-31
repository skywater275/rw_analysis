/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.utility.ByteArrayStream;
import com.corrodinggames.rts.gameFramework.core.PlatformBackend;

import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import com.corrodinggames.rts.gameFramework.network.PacketHandler;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.network.SteamSocket;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ByteArrayStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public strictfp final class SendWorker
implements Runnable {
    Boolean a = true;
    OutputStream b;
    BufferedOutputStream c;
    DataOutputStream d;
    ByteArrayStream e = new ByteArrayStream();  // 02b j/e.java: utility.w e
    final /* synthetic */ PacketDecoder f;  // 02b: 外部类 ad → 03 PacketDecoder (PlatformBackend 为误标)

    public synchronized void a(NetworkPacket au2) {
        if (this.f.a) {
            return;
        }
        this.f.f.add(au2);
        this.notifyAll();
    }

    public synchronized void a() {
        this.notifyAll();
    }

    public synchronized void b() {
        try {
            if (this.f.f.isEmpty() && !this.f.a && !this.f.b) {
                this.wait(10000L);
            }
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    SendWorker(PacketDecoder c2) {
        this.f = c2;
        try {
            this.b = c2.d.getOutputStream();
            this.c = new BufferedOutputStream(this.b);
            this.d = new DataOutputStream(this.c);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        Thread.currentThread().setName("SendWorker-" + this.f.g());
        try {
            while (this.a.booleanValue() && !this.f.a) {
                while (!this.f.f.isEmpty() && !this.f.a) {
                    Object object;
                    Object object2;
                    NetworkPacket au2 = (NetworkPacket) this.f.f.remove();  // au 为幻觉类名
                    if (au2 instanceof PacketHandler) {
                        object2 = (PacketHandler) au2;
                        if (this.f.l == ((PacketHandler) object2).f && this.f.r) {
                            object = new OutputNetStream();
                            ((OutputNetStream) object).a(((PacketHandler) object2).g);
                            au2 = ((OutputNetStream) object).b(176);
                        } else {
                            object = new OutputNetStream();
                            ((OutputNetStream) object).a(((PacketHandler) object2).g);
                            ((OutputNetStream) object).a(((PacketHandler) object2).f.packetLength);
                            ((OutputNetStream) object).a(((PacketHandler) object2).f.packetData);
                            au2 = ((OutputNetStream) object).b(175);
                        }
                        this.f.l = ((PacketHandler) object2).f;
                    } else if (this.f.q) {
                        this.f.l = au2;
                    }
                    if (this.f.d instanceof SteamSocket) {
                        object2 = (SteamSocket) this.f.d;
                        ((SteamSocket) object2).a(au2);
                    } else if (this.f.d instanceof network.reliableudp.ReliableSocket) {  // 02b: 可靠 UDP 分支
                        boolean bl = false;
                        if (au2.packetData.length > 500) {
                            object = new ByteArrayStream(8 + au2.packetData.length);
                            bl = true;
                        } else {
                            object = this.e;
                            ((ByteArrayStream) object).a();
                        }
                        boolean bl2 = au2.isSystemPacket;
                        DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)object);
                        dataOutputStream.writeInt(au2.packetData.length);
                        dataOutputStream.writeInt(au2.packetType);
                        dataOutputStream.write(au2.packetData);
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        network.reliableudp.ReliableSocket h2 = (network.reliableudp.ReliableSocket)this.f.d;
                        h2.a(((ByteArrayStream) object).a, 0, ((ByteArrayStream) object).b(), bl2);
                        if (bl) {
                            ((java.io.OutputStream) object).close();  // ServerResult 为幻觉 (object 是 ByteArrayStream)
                        }
                    } else {
                        this.d.writeInt(au2.packetData.length);
                        this.d.writeInt(au2.packetType);
                        this.d.write(au2.packetData);
                        this.d.flush();
                    }
                    if (au2.packetType == -1) continue;
                    try {
                        Thread.sleep(au2.packetType);
                    }
                    catch (InterruptedException interruptedException) {}
                }
                if (this.f.b) {
                    this.f.a = true;
                    break;
                }
                this.b();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            GlobalState.b("network:SendWorker", iOException.getMessage());
        }
        com.corrodinggames.rts.gameFramework.network.PacketDecoder.a(this.f, false, true);
    }
}
