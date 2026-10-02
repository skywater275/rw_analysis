/*
 * Decompiled with CFR 0.152.
 */
package a.a.a;

public class i
implements Runnable {
    boolean a;
    String b;
    private Runnable c;
    private long d;
    private long e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private Object j = new Object();

    public i(String string, Runnable runnable) {
        this.b = string;
        this.c = runnable;
        this.d = 0L;
        this.e = 0L;
    }

    public void a() {
        this.a = true;
        Thread thread = new Thread((Runnable)this, this.b);
        thread.setDaemon(true);
        thread.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        block15: while (!this.i) {
            Object object = this;
            synchronized (object) {
                while (!this.g && !this.i) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                if (this.i) {
                    break;
                }
            }
            object = this.j;
            synchronized (object) {
                this.h = false;
                this.f = false;
                if (this.d > 0L) {
                    try {
                        this.j.wait(this.d);
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                if (this.f) {
                    continue;
                }
            }
            if (!this.h) {
                this.c.run();
            }
            if (this.e <= 0L) continue;
            while (true) {
                object = this.j;
                synchronized (object) {
                    this.h = false;
                    try {
                        this.j.wait(this.e);
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    if (this.f) {
                        continue block15;
                    }
                    if (this.h) {
                        continue;
                    }
                }
                this.c.run();
            }
        }
        if (this.i) {
            this.c = null;
        }
    }

    public synchronized void a(long l) {
        this.a(l, 0L);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void a(long l, long l2) {
        this.d = l;
        this.e = l2;
        if (this.g) {
            throw new IllegalStateException("already scheduled");
        }
        this.g = true;
        this.notify();
        Object object = this.j;
        synchronized (object) {
            this.j.notify();
        }
    }

    public synchronized boolean b() {
        return this.g;
    }

    public synchronized boolean c() {
        return !this.b();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void d() {
        Object object = this.j;
        synchronized (object) {
            this.h = true;
            this.j.notify();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void e() {
        this.g = false;
        Object object = this.j;
        synchronized (object) {
            this.f = true;
            this.j.notify();
        }
    }

    public synchronized void f() {
        this.e();
        this.i = true;
        this.notify();
    }
}
