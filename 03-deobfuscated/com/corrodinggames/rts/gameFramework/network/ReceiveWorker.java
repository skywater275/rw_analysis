/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.core.PlatformBackend;

import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

strictfp final class ReceiveWorker
implements Runnable {
    Boolean a = true;
    final /* synthetic */ PacketDecoder b;

    ReceiveWorker(PacketDecoder c2, PacketDecoder$1 packetDecoder$1) {
        // T0 javap: d(c, c$1) 合成构造 (02b FernFlower 遗漏)
        this(c2);
    }
    private ReceiveWorker(PacketDecoder c2) {
        this.b = c2;
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        Thread.currentThread().setName("ReceiveWorker-" + this.b.g());
        try {
            this.a();
        }
        catch (EOFException eOFException) {
            this.b.a("network:ReceiveWorker: EOF reading packet", eOFException);
        }
        catch (IOException iOException) {
            if (!this.b.a) {
                iOException.printStackTrace();
            }
            if (GlobalState.aZ && iOException instanceof SocketException && !this.b.a) {
                String string;
                GlobalState l2 = GlobalState.B();
                if (!l2.bX.C && l2.bX.aW && (string = iOException.getMessage()) != null && string.contains("EBADF")) {
                    l2.i("Warning: This disconnect likely due to iOS removing sockets of background apps. Avoid minimising the game in multiplayer. Note: Games can be rejoined.");
                }
            }
            this.b.c("network:ReceiveWorker: " + iOException.getMessage());
        }
        catch (OutOfMemoryError outOfMemoryError) {
            GlobalState.c(outOfMemoryError);
            this.b.c("network:ReceiveWorker OutOfMemoryError: " + outOfMemoryError.getMessage());
        }
        PacketDecoder.a(this.b, true, false);
    }

    void a() throws IOException {
        InputStream inputStream = this.b.d.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        while (this.a.booleanValue() && !this.b.a && !this.b.d.isClosed()) {
            int n2 = dataInputStream.readInt();
            int n3 = dataInputStream.readInt();
            if (n2 > 20000000) {
                this.b.b("readData(): new packet of type:" + n3 + " has size of:" + n2);
            }
            if (n2 > 10000) {
                int n4 = 50000000;
                if (PacketDecoder.a(this.b).C) {
                    n4 = 1000000;
                }
                if (!this.b.p) {
                    n4 = 10000;
                }
                if (n2 > n4) {
                    this.b.b("Requested packet too large rejecting (max:" + n4 + ")");
                    return;
                }
            }
            if (n2 < 0) {
                this.b.b("Requested packet negative size:" + n2 + " rejecting");
                return;
            }
            NetworkPacket au2 = new NetworkPacket(n3);
            au2.packetData = new byte[n2];
            this.b.V = 0;
            this.b.U = n2;
            int n5 = 0;
            au2.connection = this.b;
            while (n5 < n2 && !this.b.a) {
                int n6 = dataInputStream.read(au2.packetData, n5, n2 - n5);
                if (n6 == -1) {
                    this.b.b("we got to the end of the stream?!?");
                    return;
                }
                ++this.b.P;
                this.b.V = n5 += n6;
            }
            this.b.U = 0;
            this.b.V = 0;
            if (this.b.a) continue;
            if (au2.packetLength > 100) {
                PacketDecoder.a(this.b).c(au2);
                continue;
            }
            PacketDecoder.a(this.b).aN.add(au2);
        }
    }
}
