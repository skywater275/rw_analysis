/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.b;
import a.a.h;
import a.a.r;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

class e
extends h {
    boolean a;
    private ArrayList i;
    final /* synthetic */ b b;

    public e(b b2, DatagramSocket datagramSocket, SocketAddress socketAddress) {
        this.b = b2;
        super(datagramSocket);
        this.d = socketAddress;
    }

    @Override
    protected void a(DatagramSocket datagramSocket, r r2) {
        this.i = new ArrayList();
        this.c = datagramSocket;
        this.g = r2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected a.a.a.h a() {
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
            return (a.a.a.h)this.i.remove(0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void a(a.a.a.h h2) {
        ArrayList arrayList = this.i;
        synchronized (arrayList) {
            if (!this.a) {
                this.a = true;
                super.a(this.c, this.g);
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

    @Override
    protected void a(String string) {
        System.out.println(this.getPort() + ": " + string);
    }
}
