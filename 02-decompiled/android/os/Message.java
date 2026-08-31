/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Messenger
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package android.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message$1;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

public final class Message
implements Parcelable {
    public int a;
    public int b;
    public int c;
    public Object d;
    public Messenger e;
    public int f = -1;
    int g;
    long h;
    Bundle i;
    Handler j;
    Runnable k;
    Message l;
    private static final Object n = new Object();
    private static Message o;
    private static int p;
    private static boolean q;
    public static final Parcelable.Creator m;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Message a() {
        Object object = n;
        synchronized (object) {
            if (o != null) {
                Message message = o;
                o = message.l;
                message.l = null;
                message.g = 0;
                --p;
                return message;
            }
        }
        return new Message();
    }

    public static Message a(Handler handler) {
        Message message = Message.a();
        message.j = handler;
        return message;
    }

    public void b() {
        if (this.f()) {
            if (q) {
                throw new IllegalStateException("This message cannot be recycled because it is still in use.");
            }
            return;
        }
        this.c();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void c() {
        this.g = 1;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = -1;
        this.h = 0L;
        this.j = null;
        this.k = null;
        this.i = null;
        Object object = n;
        synchronized (object) {
            if (p < 50) {
                this.l = o;
                o = this;
                ++p;
            }
        }
    }

    public Bundle d() {
        if (this.i == null) {
            this.i = new Bundle();
        }
        return this.i;
    }

    public boolean e() {
        return (this.g & 2) != 0;
    }

    public void a(boolean bl) {
        this.g = bl ? (this.g |= 2) : (this.g &= 0xFFFFFFFD);
    }

    boolean f() {
        return (this.g & 1) == 1;
    }

    void g() {
        this.g |= 1;
    }

    public String toString() {
        return this.a(SystemClock.a());
    }

    String a(long l) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ when=");
        stringBuilder.append("corrodinggames-unsupported");
        if (this.j != null) {
            if (this.k != null) {
                stringBuilder.append(" callback=");
                stringBuilder.append(this.k.getClass().getName());
            } else {
                stringBuilder.append(" what=");
                stringBuilder.append(this.a);
            }
            if (this.b != 0) {
                stringBuilder.append(" arg1=");
                stringBuilder.append(this.b);
            }
            if (this.c != 0) {
                stringBuilder.append(" arg2=");
                stringBuilder.append(this.c);
            }
            if (this.d != null) {
                stringBuilder.append(" obj=");
                stringBuilder.append(this.d);
            }
            stringBuilder.append(" target=");
            stringBuilder.append(this.j.getClass().getName());
        } else {
            stringBuilder.append(" barrier=");
            stringBuilder.append(this.b);
        }
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        if (this.k != null) {
            throw new RuntimeException("Can't marshal callbacks across processes.");
        }
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        if (this.d != null) {
            try {
                Parcelable parcelable = (Parcelable)this.d;
                parcel.writeInt(1);
                parcel.writeParcelable(parcelable, n);
            }
            catch (ClassCastException classCastException) {
                throw new RuntimeException("Can't marshal non-Parcelable objects across processes.");
            }
        } else {
            parcel.writeInt(0);
        }
        parcel.writeLong(this.h);
        parcel.writeBundle(this.i);
        Messenger.writeMessengerOrNullToParcel((Messenger)this.e, (Parcel)parcel);
        parcel.writeInt(this.f);
    }

    private void a(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.d = parcel.readParcelable(this.getClass().getClassLoader());
        }
        this.h = parcel.readLong();
        this.i = parcel.readBundle();
        this.e = Messenger.readMessengerOrNullFromParcel((Parcel)parcel);
        this.f = parcel.readInt();
    }

    static /* synthetic */ void a(Message message, Parcel parcel) {
        message.a(parcel);
    }

    static {
        p = 0;
        q = true;
        m = new Message$1();
    }
}
