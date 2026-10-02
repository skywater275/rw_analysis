/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.ay;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.h;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.w;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

strictfp final class e
implements Runnable {
    Boolean a = true;
    OutputStream b;
    BufferedOutputStream c;
    DataOutputStream d;
    w e = new w();
    final /* synthetic */ c f;

    public synchronized void a(au au2) {
        if (this.f.a) {
            return;
        }
        this.f.f.add(au2);
        this.notifyAll();
    }

    public synchronized void a() {
        this.notifyAll();
    }

    public synchronized void b() {
        try {
            if (this.f.f.isEmpty() && !this.f.a && !this.f.b) {
                this.wait(10000L);
            }
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    e(c c2) {
        this.f = c2;
        this.b = c2.d.getOutputStream();
        this.c = new BufferedOutputStream(this.b);
        this.d = new DataOutputStream(this.c);
    }

    @Override
    public void run() {
        l.aq();
        Thread.currentThread().setName("SendWorker-" + this.f.g());
        try {
            while (this.a.booleanValue() && !this.f.a) {
                while (!this.f.f.isEmpty() && !this.f.a) {
                    Object object;
                    Object object2;
                    au au2 = (au)this.f.f.remove();
                    if (au2 instanceof ay) {
                        object2 = (ay)au2;
                        if (this.f.l == ((ay)object2).f && this.f.r) {
                            object = new as();
                            ((as)object).a(((ay)object2).g);
                            au2 = ((as)object).b(176);
                        } else {
                            object = new as();
                            ((as)object).a(((ay)object2).g);
                            ((as)object).a(((ay)object2).f.b);
                            ((as)object).a(((ay)object2).f.c);
                            au2 = ((as)object).b(175);
                        }
                        this.f.l = ((ay)object2).f;
                    } else if (this.f.q) {
                        this.f.l = au2;
                    }
                    if (this.f.d instanceof h) {
                        object2 = (h)this.f.d;
                        ((h)object2).a(au2);
                    } else if (this.f.d instanceof a.a.h) {
                        boolean bl = false;
                        if (au2.c.length > 500) {
                            object = new w(8 + au2.c.length);
                            bl = true;
                        } else {
                            object = this.e;
                            ((w)object).a();
                        }
                        boolean bl2 = au2.e;
                        DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)object);
                        dataOutputStream.writeInt(au2.c.length);
                        dataOutputStream.writeInt(au2.b);
                        dataOutputStream.write(au2.c);
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        a.a.h h2 = (a.a.h)this.f.d;
                        h2.a(((w)object).a, 0, ((w)object).b(), bl2);
                        if (bl) {
                            ((w)object).close();
                        }
                    } else {
                        this.d.writeInt(au2.c.length);
                        this.d.writeInt(au2.b);
                        this.d.write(au2.c);
                        this.d.flush();
                    }
                    if (au2.d == -1) continue;
                    try {
                        Thread.sleep(au2.d);
                    }
                    catch (InterruptedException interruptedException) {}
                }
                if (this.f.b) {
                    this.f.a = true;
                    break;
                }
                this.b();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            l.b("network:SendWorker", iOException.getMessage());
        }
        com.corrodinggames.rts.gameFramework.j.c.a(this.f, false, true);
    }
}
