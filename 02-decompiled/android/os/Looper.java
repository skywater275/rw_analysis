/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.util.Printer
 */
package android.os;

import android.os.Message;
import android.os.MessageQueue;
import android.util.Printer;

public final class Looper {
    static final ThreadLocal a = new ThreadLocal();
    private static Looper d;
    final MessageQueue b;
    final Thread c;
    private Printer e;

    private static void a(boolean bl) {
        if (a.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        a.set(new Looper(bl));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void a() {
        Looper.a(false);
        Class<Looper> clazz = Looper.class;
        synchronized (Looper.class) {
            if (d != null) {
                throw new IllegalStateException("The main Looper has already been prepared.");
            }
            d = Looper.d();
            // ** MonitorExit[var0] (shouldn't be in output)
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Looper b() {
        Class<Looper> clazz = Looper.class;
        synchronized (Looper.class) {
            // ** MonitorExit[var0] (shouldn't be in output)
            return d;
        }
    }

    public static void c() {
        Looper looper = Looper.d();
        if (looper == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        MessageQueue messageQueue = looper.b;
        Message message;
        while ((message = messageQueue.a()) != null) {
            Printer printer = looper.e;
            if (printer != null) {
                printer.println(">>>>> Dispatching to " + message.j + " " + message.k + ": " + message.a);
            }
            message.j.b(message);
            if (printer != null) {
                printer.println("<<<<< Finished to " + message.j + " " + message.k);
            }
            message.c();
        }
        return;
    }

    public static Looper d() {
        return (Looper)a.get();
    }

    private Looper(boolean bl) {
        this.b = new MessageQueue(bl);
        this.c = Thread.currentThread();
    }

    public Thread e() {
        return this.c;
    }

    public String toString() {
        return "Looper (" + this.c.getName() + ", tid " + this.c.getId() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
    }
}
