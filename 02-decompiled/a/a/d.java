/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.a;
import a.a.a.h;
import a.a.b;
import a.a.c;
import a.a.e;
import a.a.g;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;

class d
extends Thread {
    final /* synthetic */ b a;

    public d(b b2) {
        this.a = b2;
        super("ReliableServerSocket");
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
            e e2 = null;
            SocketAddress socketAddress = null;
            try {
                try {
                    b.a(this.a).receive(datagramPacket);
                }
                catch (IOException iOException) {
                    b.a(this.a, "IOException receiving packet:" + iOException.getMessage() + " isConnected:" + b.a(this.a).isConnected());
                    if (!b.a(this.a).isConnected()) {
                        this.a.close();
                    }
                    throw new IOException(iOException);
                }
                socketAddress = datagramPacket.getSocketAddress();
                Object object = b.b(this.a);
                synchronized (object) {
                    a a2 = (a)b.c(this.a).get(socketAddress);
                    if (a2 != null) {
                        a2.a(datagramPacket.getData(), datagramPacket.getLength());
                        continue;
                    }
                }
                object = b.b(this.a);
                synchronized (object) {
                    e2 = (e)b.b(this.a).get(socketAddress);
                }
                if (e2 == null && (object = this.a.a) != null && !((c)object).a(socketAddress)) continue;
                object = h.b(datagramPacket.getData(), 0, datagramPacket.getLength());
                if (!this.a.isClosed() && e2 == null) {
                    g g2;
                    if (object instanceof a.a.a.g) {
                        g g3;
                        long l = System.currentTimeMillis();
                        if (b.d(this.a).size() > 0) {
                            int n = 10000;
                            if (b.d(this.a).size() > 20) {
                                n = 5000;
                            }
                            if (b.d(this.a).size() > 200) {
                                n = 3000;
                            }
                            Iterator iterator = b.d(this.a).entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry entry = iterator.next();
                                if (((g)entry.getValue()).a + (long)n >= l) continue;
                                iterator.remove();
                            }
                        }
                        if ((g3 = (g)b.d(this.a).get(socketAddress)) != null) {
                            g3.b.a((a.a.a.g)object);
                        } else {
                            g3 = new g();
                            g3.a = l;
                            g3.b = new e(this.a, b.a(this.a), socketAddress);
                            g3.b.a((a.a.a.g)object);
                            b.d(this.a).put(socketAddress, g3);
                        }
                    }
                    if (object instanceof a.a.a.a && (g2 = (g)b.d(this.a).get(socketAddress)) != null) {
                        e e3 = g2.b;
                        if (!e3.b((h)object)) {
                            b.a(this.a, "lightweight ack failed ack:" + ((h)object).n());
                            continue;
                        }
                        b.a(this.a, socketAddress, e3);
                        e2 = e3;
                        b.d(this.a).remove(socketAddress);
                    }
                }
                if (e2 == null) continue;
                e2.a((h)object);
            }
            catch (IOException iOException) {
                if (this.a.isClosed()) break;
                b.a(this.a, "IOException client " + socketAddress + " - " + iOException.getMessage());
            }
            catch (IllegalArgumentException illegalArgumentException) {
                if (this.a.isClosed()) break;
                b.a(this.a, "IllegalArgumentException " + socketAddress + " - " + illegalArgumentException.getMessage());
            }
        }
    }
}
