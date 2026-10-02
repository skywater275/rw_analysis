/*
 * Decompiled with CFR 0.152.
 */
package android.os;

import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;

public class Handler {
    final MessageQueue a;
    final Looper b;
    final Handler$Callback c;
    final boolean d;

    public void a(Message message) {
    }

    public void b(Message message) {
        if (message.k != null) {
            Handler.d(message);
        } else {
            if (this.c != null && this.c.a(message)) {
                return;
            }
            this.a(message);
        }
    }

    public Handler() {
        this(null, false);
    }

    public Handler(Looper looper) {
        this(looper, null, false);
    }

    public Handler(Handler$Callback handler$Callback, boolean bl) {
        this.b = Looper.d();
        if (this.b == null) {
            throw new RuntimeException("Can't create handler inside thread that has not called Looper.prepare()");
        }
        this.a = this.b.b;
        this.c = handler$Callback;
        this.d = bl;
    }

    public Handler(Looper looper, Handler$Callback handler$Callback, boolean bl) {
        this.b = looper;
        this.a = looper.b;
        this.c = handler$Callback;
        this.d = bl;
    }

    public final Message a() {
        return Message.a(this);
    }

    public final boolean a(Runnable runnable) {
        return this.a(Handler.b(runnable), 0L);
    }

    public final boolean c(Message message) {
        return this.a(message, 0L);
    }

    public final boolean a(Message message, long l) {
        if (l < 0L) {
            l = 0L;
        }
        return this.b(message, SystemClock.a() + l);
    }

    public boolean b(Message message, long l) {
        MessageQueue messageQueue = this.a;
        if (messageQueue == null) {
            RuntimeException runtimeException = new RuntimeException(this + " sendMessageAtTime() called with no mQueue");
            Log.a("Looper", runtimeException.getMessage(), runtimeException);
            return false;
        }
        return this.a(messageQueue, message, l);
    }

    private boolean a(MessageQueue messageQueue, Message message, long l) {
        message.j = this;
        if (this.d) {
            message.a(true);
        }
        return messageQueue.a(message, l);
    }

    public String toString() {
        return "Handler (" + this.getClass().getName() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
    }

    private static Message b(Runnable runnable) {
        Message message = Message.a();
        message.k = runnable;
        return message;
    }

    private static void d(Message message) {
        message.k.run();
    }
}
