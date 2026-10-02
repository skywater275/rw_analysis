/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Binder
 */
package android.os;

import android.os.Binder;
import android.os.Message;
import android.os.MessageQueue$IdleHandler;
import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;

public final class MessageQueue {
    private final boolean c;
    private long d;
    Message a;
    private final ArrayList e = new ArrayList();
    private MessageQueue$IdleHandler[] f;
    private boolean g;
    private boolean h;
    static Object b = new Object();

    private long b() {
        return 100L;
    }

    private void a(long l) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(long l, int n) {
        Object object = b;
        synchronized (object) {
            try {
                b.wait(n);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void b(long l) {
        Object object = b;
        synchronized (object) {
            b.notifyAll();
        }
    }

    MessageQueue(boolean bl) {
        this.c = bl;
        this.d = this.b();
    }

    protected void finalize() {
        try {
            this.c();
        }
        finally {
            super.finalize();
        }
    }

    private void c() {
        if (this.d != 0L) {
            this.a(this.d);
            this.d = 0L;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    Message a() {
        long l = this.d;
        if (l == 0L) {
            return null;
        }
        int n = -1;
        int n2 = 0;
        while (true) {
            Object object;
            if (n2 != 0) {
                Binder.flushPendingCommands();
            }
            System.out.println("corroding: nativePollOnce:" + l + "," + n2);
            this.a(l, n2);
            MessageQueue messageQueue = this;
            synchronized (messageQueue) {
                long l2;
                block21: {
                    l2 = SystemClock.a();
                    object = null;
                    Message message = this.a;
                    if (message != null && message.j == null) {
                        do {
                            object = message;
                        } while ((message = message.l) != null && !message.e());
                    }
                    if (message != null) {
                        if (l2 < message.h) {
                            n2 = (int)Math.min(message.h - l2, Integer.MAX_VALUE);
                            break block21;
                        } else {
                            this.h = false;
                            if (object != null) {
                                ((Message)object).l = message.l;
                            } else {
                                this.a = message.l;
                            }
                            message.l = null;
                            return message;
                        }
                    }
                    n2 = -1;
                }
                if (this.g) {
                    this.c();
                    return null;
                }
                if (n < 0 && (this.a == null || l2 < this.a.h)) {
                    n = this.e.size();
                }
                if (n <= 0) {
                    this.h = true;
                    continue;
                }
                if (this.f == null) {
                    this.f = new MessageQueue$IdleHandler[Math.max(n, 4)];
                }
                this.f = this.e.toArray(this.f);
            }
            for (int i = 0; i < n; ++i) {
                MessageQueue$IdleHandler messageQueue$IdleHandler = this.f[i];
                this.f[i] = null;
                boolean bl = false;
                try {
                    bl = messageQueue$IdleHandler.a();
                }
                catch (Throwable throwable) {
                    Log.c("MessageQueue", "IdleHandler threw exception", throwable);
                }
                if (bl) continue;
                object = this;
                synchronized (object) {
                    this.e.remove(messageQueue$IdleHandler);
                    continue;
                }
            }
            n = 0;
            n2 = 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    boolean a(Message message, long l) {
        if (message.j == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }
        if (message.f()) {
            throw new IllegalStateException(message + " This message is already in use.");
        }
        MessageQueue messageQueue = this;
        synchronized (messageQueue) {
            if (this.g) {
                IllegalStateException illegalStateException = new IllegalStateException(message.j + " sending message to a Handler on a dead thread");
                Log.a("MessageQueue", illegalStateException.getMessage(), illegalStateException);
                message.b();
                return false;
            }
            message.g();
            message.h = l;
            Message message2 = this.a;
            if (message2 == null || l == 0L || l < message2.h) {
                message.l = message2;
                this.a = message;
                boolean bl = this.h;
            } else {
                boolean bl = this.h && message2.j == null && message.e();
                while (true) {
                    Message message3 = message2;
                    message2 = message2.l;
                    if (message2 == null || l < message2.h) break;
                    if (!bl || !message2.e()) continue;
                    bl = false;
                }
                message.l = message2;
                message3.l = message;
            }
            this.b(this.d);
        }
        return true;
    }
}
