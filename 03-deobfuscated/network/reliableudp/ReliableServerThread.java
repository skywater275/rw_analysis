/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.GameFlagImpl;

import network.reliableudp.DataReceiver;
import network.reliableudp.core.Packet;
import network.reliableudp.ReliableServerSocket;
import network.reliableudp.AddressFilter;
import network.reliableudp.ReliableClientSocket;
import network.reliableudp.ConnectionEntry;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;

class ReliableServerThread
extends Thread {
    final /* synthetic */ ReliableServerSocket a;

    public ReliableServerThread(ReliableServerSocket b2) {
        super("ReliableServerSocket");
        this.a = b2;
        this.setDaemon(true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        byte[] byArray = new byte[65535];
        while (true) {
            DatagramPacket datagramPacket = new DatagramPacket(byArray, byArray.length);
            ReliableClientSocket e2 = null;
            SocketAddress socketAddress = null;
            try {
                try {
                    ReliableServerSocket.a(this.a).receive(datagramPacket);
                }
                catch (IOException iOException) {
                    ReliableServerSocket.a(this.a, "IOException receiving packet:" + iOException.getMessage() + " isConnected:" + ReliableServerSocket.a(this.a).isConnected());
                    if (!ReliableServerSocket.a(this.a).isConnected()) {
                        this.a.close();
                    }
                    throw new IOException(iOException);
                }
                socketAddress = datagramPacket.getSocketAddress();
                Object object = ReliableServerSocket.b(this.a);
                synchronized (object) {
                    DataReceiver a2 = (DataReceiver) ReliableServerSocket.c(this.a).get(socketAddress);
                    if (a2 != null) {
                        a2.a(datagramPacket.getData(), datagramPacket.getLength());
                        continue;
                    }
                }
                object = ReliableServerSocket.b(this.a);
                synchronized (object) {
                    e2 = (ReliableClientSocket) ReliableServerSocket.b(this.a).get(socketAddress);
                }
                if (e2 == null && (object = this.a.udpSocket) != null && !((AddressFilter) object).a(socketAddress)) continue;
                object = network.reliableudp.core.Packet.b(datagramPacket.getData(), 0, datagramPacket.getLength());
                if (!this.a.isClosed() && e2 == null) {
                    ConnectionEntry g2;
                    if (object instanceof network.reliableudp.core.SynPacket) {
                        ConnectionEntry g3;
                        long l = System.currentTimeMillis();
                        if (ReliableServerSocket.d(this.a).size() > 0) {
                            int n = 10000;
                            if (ReliableServerSocket.d(this.a).size() > 20) {
                                n = 5000;
                            }
                            if (ReliableServerSocket.d(this.a).size() > 200) {
                                n = 3000;
                            }
                            Iterator iterator = ReliableServerSocket.d(this.a).entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry entry = (Map.Entry) iterator.next();
                                if (((ConnectionEntry) entry.getValue()).a + (long)n >= l) continue;
                                iterator.remove();
                            }
                        }
                        if ((g3 = (ConnectionEntry) ReliableServerSocket.d(this.a).get(socketAddress)) != null) {
                            g3.b.a((network.reliableudp.core.SynPacket) object);
                        } else {
                            g3 = new ConnectionEntry();
                            g3.a = l;
                            g3.b = new ReliableClientSocket(this.a, ReliableServerSocket.a(this.a), socketAddress);
                            g3.b.a((network.reliableudp.core.SynPacket) object);
                            ReliableServerSocket.d(this.a).put(socketAddress, g3);
                        }
                    }
                    if (object instanceof network.reliableudp.core.AckPacket && (g2 = (ConnectionEntry) ReliableServerSocket.d(this.a).get(socketAddress)) != null) {
                        ReliableClientSocket e3 = g2.b;
                        if (!e3.b((network.reliableudp.core.Packet) object)) {
                            ReliableServerSocket.a(this.a, "lightweight ack failed ack:" + ((network.reliableudp.core.Packet) object).n());
                            continue;
                        }
                        ReliableServerSocket.a(this.a, socketAddress, e3);
                        e2 = e3;
                        ReliableServerSocket.d(this.a).remove(socketAddress);
                    }
                }
                if (e2 == null) continue;
                e2.a((network.reliableudp.core.Packet) object);
            }
            catch (IOException iOException) {
                if (this.a.isClosed()) break;
                ReliableServerSocket.a(this.a, "IOException client " + socketAddress + " - " + iOException.getMessage());
            }
            catch (IllegalArgumentException illegalArgumentException) {
                if (this.a.isClosed()) break;
                ReliableServerSocket.a(this.a, "IllegalArgumentException " + socketAddress + " - " + illegalArgumentException.getMessage());
            }
        }
    }
}
