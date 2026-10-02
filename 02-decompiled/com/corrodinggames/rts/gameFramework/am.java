/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.am$1;
import com.corrodinggames.rts.gameFramework.am$2;
import com.corrodinggames.rts.gameFramework.an;
import com.corrodinggames.rts.gameFramework.aq;
import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.gameFramework.at;
import com.corrodinggames.rts.gameFramework.au;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.i.b;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.HashMap;

public class am {
    public static aq a = new an();
    Object b = new Object();
    Object c = new Object();
    volatile float d = 1.0f;
    au e;
    volatile boolean f = false;
    volatile boolean g = true;
    float h = 0.0f;
    int i = 0;
    boolean j = false;
    as k;
    boolean l;
    String m;
    boolean n;
    boolean o;
    float p;
    float q;
    float r;
    public boolean s;
    public String t;
    public boolean u;
    String v;
    Context w;
    boolean x;
    boolean y;
    int z;
    as A;
    boolean B;
    boolean C;
    float D;
    boolean E = false;
    public boolean F = false;
    boolean G = false;
    float H;
    ArrayList I = new ArrayList();
    static HashMap J = new HashMap();
    static int K = 0;
    boolean L;
    boolean M;
    long N = -1L;

    public float a() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bQ.musicVolume * l2.bQ.masterVolume;
    }

    public boolean b() {
        if (com.corrodinggames.rts.gameFramework.l.ax()) {
            return false;
        }
        if (this.u) {
            return false;
        }
        return this.a() > 0.01f;
    }

    public void a(Context context) {
        this.w = context;
        if (com.corrodinggames.rts.gameFramework.l.ax()) {
            return;
        }
        a.a(this);
        this.k = a.a();
        this.A = a.a();
        at.c();
        if (a.d()) {
            this.e = new au(this);
            this.e.start();
        }
    }

    public void c() {
        if (!com.corrodinggames.rts.gameFramework.l.av()) {
            this.l = false;
            this.m = null;
            this.x = true;
            this.B = false;
        }
        this.y = true;
        this.u = false;
    }

    static ar a(String string, boolean bl) {
        ar ar2;
        ar ar3 = (ar)J.get(string);
        if (ar3 != null) {
            return ar3;
        }
        try {
            ar2 = a.a(string);
        }
        catch (ArithmeticException arithmeticException) {
            com.corrodinggames.rts.gameFramework.l.a("Error loading:" + string, (Throwable)arithmeticException);
            if (++K > 2 && K <= 4) {
                com.corrodinggames.rts.gameFramework.l.B().i("Failed to load music track:" + string + ". Music track skipped.");
            }
            if (!bl) {
                throw new RuntimeException(arithmeticException);
            }
            return null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a("OutOfMemoryError loading:" + string, (Throwable)outOfMemoryError);
            com.corrodinggames.rts.gameFramework.l.aC();
            System.gc();
            com.corrodinggames.rts.gameFramework.l.aC();
            if (++K < 3) {
                com.corrodinggames.rts.gameFramework.l.B().i("Ran out of memory loading music track:" + string + ". Music track skipped.");
            }
            if (!bl) {
                throw new RuntimeException(outOfMemoryError);
            }
            return null;
        }
        catch (Exception exception) {
            com.corrodinggames.rts.gameFramework.l.a("Exception loading:" + string, (Throwable)exception);
            if (++K > 2 && K <= 4) {
                com.corrodinggames.rts.gameFramework.l.B().i("Unknown error loading music track:" + string + ". Music track skipped.");
            }
            if (!bl) {
                throw new RuntimeException(exception);
            }
            return null;
        }
        if (bl) {
            J.put(string, ar2);
        }
        return ar2;
    }

    public ArrayList d() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : at.a.b()) {
            arrayList.add(string);
        }
        for (String string : at.b.b()) {
            arrayList.add(string);
        }
        for (String string : at.a.b()) {
            arrayList.add(string);
        }
        return arrayList;
    }

    public String a(at at2) {
        return this.a(at2, at2);
    }

    public String a(at at2, at at3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        at at4 = com.corrodinggames.rts.gameFramework.f.c(at2.b().length + at3.b().length) < at2.b().length ? at2 : at3;
        String[] stringArray = at4.b();
        return at4.a(stringArray[com.corrodinggames.rts.gameFramework.f.c(stringArray.length)]);
    }

    public synchronized void e() {
        this.s = true;
        this.u = false;
        this.t = null;
    }

    public synchronized void a(String string) {
        this.s = true;
        this.u = false;
        this.t = string;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void a(float f2) {
        if (com.corrodinggames.rts.gameFramework.l.ax()) {
            return;
        }
        if (!a.d()) {
            if (!this.L) {
                this.b(f2);
            }
            this.g = true;
        }
        this.N = com.corrodinggames.rts.gameFramework.l.V();
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bT.H.a()) {
            this.e();
        }
        if (this.v != null) {
            ad.a(null, this.v);
            this.v = null;
        }
        if (this.p != this.a()) {
            this.p = this.a();
            this.o = true;
        }
        Object object = this.c;
        synchronized (object) {
            this.d = f2;
            if (this.L) {
                if (!this.M) {
                    this.M = true;
                    com.corrodinggames.rts.gameFramework.l.n("Music subsystem crashed, music has been disabled to keep your game running. Please send your logs.");
                }
                return;
            }
            if (!this.g) {
                this.h += f2;
                ++this.i;
                if (this.h > 320.0f && this.i > 80 && !this.j) {
                    this.j = true;
                    com.corrodinggames.rts.gameFramework.l.n("Lockup detected in music subsystem");
                }
            } else {
                this.h = 0.0f;
                this.i = 0;
            }
            this.g = false;
            this.f = true;
            this.c.notifyAll();
        }
    }

    public String b(String string) {
        string = com.corrodinggames.rts.gameFramework.f.k(string);
        string = com.corrodinggames.rts.gameFramework.f.g(string);
        string = string.replace("[noloop]", "");
        string = string.replace("_", " ");
        return string;
    }

    public boolean b(float f2) {
        try {
            this.c(f2);
            return true;
        }
        catch (Exception exception) {
            com.corrodinggames.rts.gameFramework.l.a("Music system crashed", (Throwable)exception);
            this.L = true;
            com.corrodinggames.rts.gameFramework.l.e("Stopping music");
            try {
                this.g();
            }
            catch (Exception exception2) {
                com.corrodinggames.rts.gameFramework.l.a("crash stopping music", (Throwable)exception2);
            }
            return false;
        }
    }

    public void c(float f2) {
        if (com.corrodinggames.rts.gameFramework.l.ax()) {
            return;
        }
        a.a(f2);
        if (!this.b()) {
            if (this.l && this.k.c()) {
                this.g();
                this.l = false;
                this.B = false;
            }
            return;
        }
        boolean bl = false;
        if (!this.l) {
            bl = true;
        }
        if (this.n) {
            if (!this.C) {
                this.q += f2;
            }
            if (this.q > 600.0f) {
                this.r += f2;
                if (this.r > 100.0f) {
                    this.r = 0.0f;
                    if (!this.l || !this.k.c()) {
                        bl = true;
                        this.q = 0.0f;
                    }
                }
            }
        } else {
            this.q += f2;
            if (this.q > 3600.0f) {
                com.corrodinggames.rts.gameFramework.l.e("Next music track, timer:" + this.q);
                bl = true;
                this.q = 0.0f;
            }
        }
        if (this.y) {
            b b2 = com.corrodinggames.rts.gameFramework.f.g.z();
            if (b2 != null && b2.N) {
                bl = true;
            }
            this.y = false;
        }
        if (bl || this.s) {
            Object object;
            Object object2;
            Object object3;
            boolean bl2 = this.s;
            String string = this.t;
            if (this.s) {
                com.corrodinggames.rts.gameFramework.l.e("Next music track requested");
                this.s = false;
                this.q = 0.0f;
                this.t = null;
            }
            String string2 = null;
            boolean bl3 = false;
            Object object4 = null;
            if (string != null) {
                object3 = com.corrodinggames.rts.gameFramework.l.B().bZ.i();
                ((ArrayList)object3).addAll(this.d());
                object2 = string;
                if (string.endsWith(".ogg") || string.endsWith(".wav")) {
                    string = this.b(string);
                }
                object = ((ArrayList)object3).iterator();
                while (object.hasNext()) {
                    String string3 = (String)object.next();
                    String string4 = this.b(string3);
                    if (!string4.equalsIgnoreCase((String)object2)) continue;
                    bl3 = true;
                    string2 = string3;
                    break;
                }
                if (string2 == null) {
                    com.corrodinggames.rts.gameFramework.l.e("Failed to find requested music: " + (String)object2);
                }
            }
            object3 = com.corrodinggames.rts.gameFramework.f.g.z();
            if (string2 == null && object3 != null && ((b)object3).P < 10 && ((b)object3).N && ((ArrayList)(object2 = ((b)object3).q())).size() > 0) {
                bl3 = true;
                object4 = object3;
                string2 = (String)((ArrayList)object2).get(com.corrodinggames.rts.gameFramework.f.a(0, ((ArrayList)object2).size() - 1));
                if (bl2 || this.I.contains(string2)) {
                    for (int i2 = 0; i2 < 30 && (string2.equals(this.m) || this.I.contains(string2)); ++i2) {
                        string2 = (String)((ArrayList)object2).get(com.corrodinggames.rts.gameFramework.f.a(0, ((ArrayList)object2).size() - 1));
                        if (i2 <= 20) continue;
                        this.I.clear();
                    }
                }
                com.corrodinggames.rts.gameFramework.l.e("Playing music from mod:" + ((b)object3).a() + " - '" + string2 + "'");
            }
            if (string2 == null) {
                string2 = this.x ? this.a(at.a) : this.a(at.b, at.a);
                if (bl2 || this.I.contains(string2)) {
                    for (int i3 = 0; i3 < 30 && (string2.equals(this.m) || this.I.contains(string2)); ++i3) {
                        string2 = this.a(at.b, at.a);
                        if (i3 <= 20) continue;
                        this.I.clear();
                    }
                }
            }
            if (!string2.equals(this.m)) {
                this.m = string2;
                this.x = false;
                this.q = 0.0f;
                this.n = bl3 || string2.contains("[noloop]");
                this.I.add(string2);
                if (this.I.size() > 4) {
                    this.I.remove(0);
                }
                if (bl2) {
                    this.v = "Now playing: " + this.b(string2);
                }
                as as2 = this.k;
                this.k = this.A;
                this.A = as2;
                try {
                    object = am.a(string2, false);
                }
                catch (RuntimeException runtimeException) {
                    runtimeException.printStackTrace();
                    if (this.z < 3) {
                        this.v = "Failed to open music track: " + string2;
                        ++this.z;
                    }
                    if (object4 != null) {
                        ++((b)object4).P;
                    }
                    return;
                }
                try {
                    this.k.a((ar)object);
                    this.k.a(!this.n);
                }
                catch (RuntimeException runtimeException) {
                    runtimeException.printStackTrace();
                    if (this.z < 3) {
                        this.v = "Failed to play music track: " + string2;
                        ++this.z;
                    }
                    if (object4 != null) {
                        ++((b)object4).P;
                    }
                    return;
                }
                this.E = false;
                if (!bl2 && this.B) {
                    this.E = true;
                }
                if (this.l) {
                    this.B = true;
                }
                this.C = true;
                this.G = false;
                this.D = 1.0f;
                this.l = true;
            } else if (bl2) {
                com.corrodinggames.rts.gameFramework.l.e("Same music found");
            }
        }
        if (this.C || this.o) {
            float f3;
            float f4;
            boolean bl4 = a.c();
            this.D = !bl4 ? (this.F ? (this.D -= f2 * 0.1f) : (this.D -= f2 * 0.006f)) : (this.F ? (this.D -= f2 * 0.1f) : (this.E ? (this.D -= f2 * 0.003f) : (this.D -= f2 * 0.008f)));
            if (!bl4) {
                f4 = this.D * this.a();
                f3 = (1.0f - this.D) * this.a();
            } else {
                f4 = (this.D * 2.0f - 1.0f) * this.a();
                f3 = (1.0f - this.D * 2.0f) * this.a();
            }
            f4 = com.corrodinggames.rts.gameFramework.f.b(f4, 0.0f, 1.0f);
            f3 = com.corrodinggames.rts.gameFramework.f.b(f3, 0.0f, 1.0f);
            if (this.C) {
                if (this.D <= 0.0f) {
                    this.C = false;
                    this.E = false;
                    if (this.B && !this.G) {
                        this.G = true;
                        this.A.d();
                    }
                    if (this.l) {
                        this.k.a(this.a(), this.a());
                    }
                } else {
                    this.H += f2;
                    if (this.H > 10.0f) {
                        this.H = 0.0f;
                        if (this.B && !this.G) {
                            this.A.a(f4, f4);
                            if (f4 < 0.02f) {
                                this.G = true;
                                this.A.d();
                            }
                        }
                        if (this.l) {
                            this.k.a(f3, f3);
                        }
                    }
                }
            } else if (this.l) {
                this.k.a(f3, f3);
            }
        }
        this.o = false;
    }

    public void f() {
        Log.a("RustedWarfare", "Music:pause()");
        am$1 am$1 = new am$1(this);
        am$1.start();
    }

    public void g() {
        if (this.l) {
            this.k.a();
        }
        if (this.B) {
            this.A.a();
        }
    }

    public void h() {
        am$2 am$2 = new am$2(this);
        am$2.start();
    }

    public void i() {
        a.b();
        if (this.B) {
            this.A.d();
            this.A.e();
        }
        if (this.k != null) {
            this.k.d();
            this.k.e();
        }
        this.k = null;
        this.m = null;
        this.l = false;
    }

    public boolean j() {
        return this.C;
    }
}
