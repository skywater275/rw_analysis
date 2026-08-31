/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.TagFilter;
import com.corrodinggames.rts.game.GameFlagImpl;

import network.reliableudp.ReliableServerSocket;
import network.reliableudp.ReliableSocket;
import network.reliableudp.ReliableProfile;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

class ReliableClientSocket
extends ReliableSocket {
    boolean a;
    private ArrayList i;
    final /* synthetic */ ReliableServerSocket b;

    public ReliableClientSocket(ReliableServerSocket b2, DatagramSocket datagramSocket, SocketAddress socketAddress) {
        super(datagramSocket);
        this.b = b2;
        this.remoteAddress = socketAddress;
    }

    @Override
    protected void a(DatagramSocket datagramSocket, ReliableProfile r2) {
        this.i = new ArrayList();
        this.udpSocket = datagramSocket;
        this.profile = r2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected network.reliableudp.core.Packet a() {
        ArrayList arrayList = this.i;
        synchronized (arrayList) {
            while (this.i.isEmpty()) {
                try {
                    this.i.wait();
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            return (network.reliableudp.core.Packet)this.i.remove(0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void a(network.reliableudp.core.Packet h2) {
        ArrayList arrayList = this.i;
        synchronized (arrayList) {
            if (!this.a) {
                this.a = true;
                super.a(this.udpSocket, this.profile);
            }
            this.i.add(h2);
            this.i.notify();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void b() {
        ArrayList arrayList = this.i;
        synchronized (arrayList) {
            this.i.clear();
            this.i.add(null);
            this.i.notify();
        }
    }


    protected void a(String string) {
        System.out.println(this.getPort() + ": " + string);
    }
}
