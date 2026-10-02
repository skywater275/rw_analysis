/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Debug
 */
package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Paint;
import android.os.Debug;
import android.util.Log;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.k.g;
import com.corrodinggames.rts.gameFramework.k.i;
import com.corrodinggames.rts.gameFramework.k.k;
import com.corrodinggames.rts.gameFramework.k.m;
import com.corrodinggames.rts.gameFramework.k.n;
import com.corrodinggames.rts.gameFramework.k.p;
import com.corrodinggames.rts.gameFramework.l;
import java.util.LinkedList;

public strictfp final class o
implements Runnable {
    private final com.corrodinggames.rts.gameFramework.k.l C;
    boolean a = true;
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public short[] e;
    public byte[] f;
    private k D;
    int g;
    int h;
    int i = 5;
    int j = 0;
    int k = 0;
    int[][] l;
    byte[][] m;
    i n;
    final m o = new m();
    final m p = new m();
    final p q = new p();
    final p r = new p();
    volatile boolean s = true;
    static LinkedList t = new LinkedList();
    static int u;
    int v;
    Thread w;
    Object x = new Object();
    long y;
    long z;
    Object A = new Object();
    Paint B = new Paint();

    public void a(k k2) {
        if (!this.s) {
            throw new RuntimeException("setupNewPath: last path not yet finished");
        }
        this.s = false;
        this.a(k2.o, k2);
        k2.v = true;
        this.D = k2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a() {
        if (this.w == null) {
            throw new RuntimeException("thread==null");
        }
        Object object = this.x;
        synchronized (object) {
            this.x.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        com.corrodinggames.rts.gameFramework.l.aq();
        while (this.a) {
            Object object = this.x;
            synchronized (object) {
                if (this.D == null) {
                    try {
                        this.x.wait();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
            if (this.D == null) continue;
            this.b();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b() {
        LinkedList linkedList;
        if (this.D instanceof com.corrodinggames.rts.gameFramework.k.f) {
            this.f();
            linkedList = t;
        } else {
            linkedList = this.e();
        }
        Object object = this.C.G;
        synchronized (object) {
            this.D.f();
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.D.a(linkedList);
            this.D = null;
            this.s = true;
            this.C.G.notifyAll();
        }
    }

    o(com.corrodinggames.rts.gameFramework.k.l l2) {
        this.C = l2;
        this.v = u++;
    }

    public synchronized void c() {
        if (this.w != null) {
            throw new RuntimeException("thread!=null");
        }
        this.w = new Thread(this);
        this.w.setName("PathSolver-" + this.v);
        this.w.setPriority(10);
        this.w.setDaemon(true);
        this.w.start();
    }

    public void a(b b2) {
        this.g = b2.u.n;
        this.h = b2.u.o;
        this.l = new int[2][this.g * this.h];
        this.m = new byte[2][this.g * this.h];
        this.d();
    }

    public void d() {
        int n2 = this.g * this.h * 42 + 1;
        this.i += n2;
        boolean bl = false;
        if (this.i > Integer.MAX_VALUE - n2 || this.i < 0 || bl) {
            this.i = 5;
            for (int i2 = 0; i2 < 2; ++i2) {
                for (int i3 = 0; i3 < this.g; ++i3) {
                    for (int i4 = 0; i4 < this.h; ++i4) {
                        this.l[i2][i3 * this.h + i4] = -1;
                    }
                }
            }
        }
    }

    public final int a(int n2, int n3) {
        if (this.b[n2 * this.h + n3] == -1 || this.c[n2 * this.h + n3] == -1 || this.d[n2 * this.h + n3] == -1) {
            return -1;
        }
        return this.b[n2 * this.h + n3] + this.c[n2 * this.h + n3] + this.d[n2 * this.h + n3] * 10;
    }

    public void a(ao ao2, k k2) {
        if (ao2 == null) {
            throw new RuntimeException("MovementType is null");
        }
        i i2 = this.C.a(ao2);
        if (i2 == null) {
            throw new RuntimeException("Could not get costs for:" + ao2.toString());
        }
        this.n = i2;
        this.b = k2.y;
        this.c = k2.z;
        this.d = k2.A;
        this.e = k2.B;
        this.f = k2.C;
        if (this.b == null) {
            throw new RuntimeException("linkToPath failed mapCosts_mapCosts is null");
        }
        if (this.c == null) {
            throw new RuntimeException("linkToPath failed mapCosts_buildingCosts is null");
        }
        if (this.d == null) {
            throw new RuntimeException("linkToPath failed mapCosts_objectCosts is null");
        }
    }

    public void a(ao ao2, short s2, short s3, Float f2) {
        if (com.corrodinggames.rts.gameFramework.k.l.a) {
            Log.d("RustedWarfare", "path start is:" + s2 + "," + s3);
        }
        p p2 = new p(s2, s3);
        p2.a(this, (byte)0, this.i);
        if (f2 == null) {
            p2.a(this, (byte)0, (byte)0);
            p2.b(this, (byte)0, true);
        } else {
            p2.a(this, (byte)0, f2.floatValue());
            p2.b(this, (byte)0, true);
        }
        p2.a(this, (byte)0, false);
    }

    public void a(short s2, short s3, short s4) {
        if (com.corrodinggames.rts.gameFramework.k.l.a) {
            Log.d("RustedWarfare", "path end is:" + s2 + "," + s3 + " size:" + s4);
        }
        p p2 = new p(s2, s3);
        p2.a(this, (byte)1, (byte)0);
        p2.b(this, (byte)1, true);
        p2.a(this, (byte)1, this.i);
        p2.a(this, (byte)1, false);
    }

    static int b(int n2, int n3) {
        if (n2 == n3) {
            return 0;
        }
        int n4 = n2 - n3;
        if (n4 < 0) {
            n4 = -n4;
        }
        if (n4 > 4) {
            n4 = 8 - n4;
        }
        if (n4 == 1) {
            return 4;
        }
        if (n4 == 2) {
            return 21;
        }
        return 25;
    }

    static int c(int n2, int n3) {
        if (n2 == n3) {
            return 0;
        }
        int n4 = Math.abs(n2 - n3);
        if (n4 > 4) {
            n4 = 8 - n4;
        }
        if (n4 == 1) {
            return 4;
        }
        if (n4 == 2) {
            return 21;
        }
        if (n4 == 3) {
            return 4;
        }
        if (n4 == 4) {
            return 0;
        }
        if (n4 == 5) {
            return 0;
        }
        return 25;
    }

    public LinkedList e() {
        short s2;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        if (com.corrodinggames.rts.gameFramework.k.l.l && !com.corrodinggames.rts.gameFramework.l.B().bH) {
            Debug.startMethodTracing((String)"pathTrace", (int)110000000);
        }
        k k2 = this.D;
        int n7 = k2.p ? 7 : 1;
        int n8 = k2.q;
        if (com.corrodinggames.rts.gameFramework.k.l.a) {
            Log.d("RustedWarfare", "starting path for:" + this.n.a.toString());
        }
        p p2 = this.r;
        p p3 = this.q;
        short s3 = this.h;
        int n9 = this.g;
        this.y = com.corrodinggames.rts.gameFramework.l.V();
        short s4 = k2.h;
        short s5 = k2.i;
        boolean bl = k2.k;
        this.d();
        this.a(k2.o, k2.h, k2.i, k2.j);
        int n10 = k2.l;
        int n11 = k2.m;
        short s6 = k2.n;
        LinkedList<p> linkedList = new LinkedList<p>();
        if (s4 == n10 && s5 == n11) {
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                Log.d("RustedWarfare", "no point pathing when start=end");
            }
            linkedList.clear();
            linkedList.add(new p((short)n10, (short)n11));
            return linkedList;
        }
        if (this.n.a.equals((Object)ao.a)) {
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                Log.d("RustedWarfare", "no point pathing for none");
            }
            linkedList.clear();
            return linkedList;
        }
        int n12 = 0;
        int n13 = n10;
        int n14 = n11;
        short s7 = s6;
        if (this.e != null) {
            int n15;
            short[] sArray = this.e;
            n6 = sArray[s4 * s3 + s5];
            n5 = 1;
            if (n6 == -1) {
                n5 = 0;
            } else {
                for (n15 = (int)(n10 - s6); n15 <= n10 + s6; n15 = (short)(n15 + 1)) {
                    for (n4 = (short)(n11 - s6); n4 <= n11 + s6; n4 = (short)(n4 + 1)) {
                        if (n15 < 0 || n15 >= n9 || n4 < 0 || n4 >= s3 || n6 != sArray[n15 * s3 + n4]) continue;
                        n5 = 0;
                    }
                }
            }
            if (n5 != 0) {
                float f2;
                int n16;
                if (com.corrodinggames.rts.gameFramework.k.l.a) {
                    Log.d("RustedWarfare", "end is blocked on isolated groups");
                }
                n15 = n13;
                n4 = n14;
                float f3 = -1.0f;
                for (n16 = (int)(n10 - 25); n16 <= n10 + 25; n16 = (short)(n16 + 1)) {
                    for (n3 = (int)(n11 - 25); n3 <= n11 + 25; n3 = (short)(n3 + 1)) {
                        if (n16 < 0 || n16 >= n9 || n3 < 0 || n3 >= s3 || n6 != sArray[n16 * s3 + n3] && sArray[n16 * s3 + n3] != 0) continue;
                        f2 = com.corrodinggames.rts.gameFramework.f.a((float)n16, (float)n3, (float)n10, (float)n11);
                        if (f3 != -1.0f && !(f2 < f3)) continue;
                        f3 = f2;
                        n15 = n16;
                        n4 = n3;
                        s7 = 0;
                    }
                }
                if (f3 == -1.0f) {
                    for (n16 = 0; n16 < n9; n16 = (short)(n16 + 1)) {
                        for (n3 = 0; n3 < s3; n3 = (int)((short)(n3 + 1))) {
                            if (n6 != sArray[n16 * s3 + n3] && sArray[n16 * s3 + n3] != 0) continue;
                            f2 = com.corrodinggames.rts.gameFramework.f.a((float)n16, (float)n3, (float)n10, (float)n11);
                            if (f3 != -1.0f && !(f2 < f3)) continue;
                            f3 = f2;
                            n15 = n16;
                            n4 = n3;
                            s7 = 0;
                        }
                    }
                }
                n13 = n15;
                n14 = n4;
                if (com.corrodinggames.rts.gameFramework.k.l.a) {
                    long l2 = System.currentTimeMillis() - this.y;
                    Log.d("RustedWarfare", "fakeNode search was:" + l2);
                }
            }
        }
        boolean bl2 = true;
        block6: for (n6 = (int)(n13 - s7); n6 <= n13 + s7; n6 = (short)(n6 + 1)) {
            for (n5 = (int)(n14 - s7); n5 <= n14 + s7; n5 = (short)(n5 + 1)) {
                if (n6 < 0 || n6 >= n9 || n5 < 0 || n5 >= s3 || this.a(n6, n5) == -1) continue;
                bl2 = false;
                break block6;
            }
        }
        if (bl2) {
            n6 = n13;
            n5 = n14;
            float f4 = -1.0f;
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                Log.d("RustedWarfare", "end is blocked on non isolated groups");
            }
            for (n4 = (short)(n13 - 9); n4 <= n13 + 9; n4 = (short)(n4 + 1)) {
                for (int n17 = (int)(n14 - 9); n17 <= n14 + 9; n17 = (short)(n17 + 1)) {
                    if (n4 < 0 || n4 >= n9 || n17 < 0 || n17 >= s3 || this.a(n4, n17) == -1) continue;
                    float f5 = com.corrodinggames.rts.gameFramework.f.a((float)n4, (float)n17, (float)n13, (float)n14);
                    if (f4 != -1.0f && !(f5 < f4)) continue;
                    f4 = f5;
                    n6 = n4;
                    n5 = n17;
                    s7 = 0;
                }
            }
            if (f4 == -1.0f) {
                for (n4 = 0; n4 < n9; n4 = (short)(n4 + 1)) {
                    for (short s8 = 0; s8 < s3; s8 = (short)((short)(s8 + 1))) {
                        if (this.a(n4, s8) == -1) continue;
                        float f6 = com.corrodinggames.rts.gameFramework.f.a((float)n4, (float)s8, (float)n13, (float)n14);
                        if (f4 != -1.0f && !(f6 < f4)) continue;
                        f4 = f6;
                        n6 = n4;
                        n5 = s8;
                        s7 = 0;
                    }
                }
            }
            n13 = n6;
            n14 = n5;
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                long l3 = System.currentTimeMillis() - this.y;
                Log.d("RustedWarfare", "fakeNode search was:" + l3);
            }
        }
        if (com.corrodinggames.rts.gameFramework.k.l.a && (n13 != n10 || n14 != n11)) {
            Log.d("RustedWarfare", "Moved end to fakeEndX:" + n13 + " fakeEndY:" + n14);
        }
        this.o.a(n13, n14);
        this.o.a(0, s4, s5);
        this.p.a(s4, s5);
        this.a((short)n13, (short)n14, s7);
        this.p.a(0, (short)n13, (short)n14);
        bl2 = false;
        n6 = 0;
        n5 = -1;
        short s9 = -1;
        short s10 = -1;
        short s11 = -1;
        int n18 = 400;
        n3 = 0;
        while (!bl2) {
            byte by;
            boolean bl3;
            m m2;
            n n19;
            ++n3;
            if (k2.w) {
                linkedList.clear();
                return linkedList;
            }
            ++n12;
            if (n18 > 0) {
                --n18;
            } else {
                n6 = n6 == 0 ? 1 : 0;
            }
            byte by2 = 0;
            if (n6 != 0) {
                by2 = 1;
            }
            if ((n19 = (m2 = n6 == 0 ? this.o : this.p).c()) == null) {
                if (n6 != 0) continue;
                if (!com.corrodinggames.rts.gameFramework.k.l.a) break;
                Log.d("RustedWarfare", "listNode==null for normal side ending path");
                break;
            }
            p p4 = p3.a(n19);
            int n20 = p4.a(this, by2);
            byte by3 = p4.c(this, by2);
            n2 = p4.d(this, by2) ? 1 : 0;
            s2 = 0;
            if (n6 == 0) {
                if (p4.a == n13 && p4.b == n14) {
                    if (com.corrodinggames.rts.gameFramework.k.l.a) {
                        Log.d("RustedWarfare", "Over goal: fakeEnd");
                    }
                    s2 = 1;
                }
                if (com.corrodinggames.rts.gameFramework.f.d(p4.a - n10) <= s6 && com.corrodinggames.rts.gameFramework.f.d(p4.b - n11) <= s6) {
                    if (com.corrodinggames.rts.gameFramework.k.l.a) {
                        Log.d("RustedWarfare", "Over goal: real end");
                    }
                    s2 = 1;
                }
            }
            if ((bl3 = p4.b(this, (byte)(1 - by2))) || s2 != 0) {
                p2.a(p4);
                if (!bl3) {
                    if (com.corrodinggames.rts.gameFramework.k.l.a) {
                        Log.d("RustedWarfare", "Not closedOnOtherSide");
                    }
                    n5 = p3.a;
                    s9 = p3.b;
                    bl2 = true;
                    break;
                }
                p p5 = p2.f(this, by2);
                if (p5 == null) {
                    Log.d("RustedWarfare", "findPath: otherConnection==null");
                    Log.d("RustedWarfare", "currentNode:" + p3.a + "," + p3.b);
                    Log.d("RustedWarfare", "currentNode cost normal:" + p3.a(this, (byte)0));
                    Log.d("RustedWarfare", "currentNode cost opposite:" + p3.a(this, (byte)1));
                    linkedList.clear();
                    return linkedList;
                }
                if (n6 == 0) {
                    if (com.corrodinggames.rts.gameFramework.k.l.a) {
                        Log.d("RustedWarfare", "closing path runFromOppositeSide=false");
                    }
                    s10 = p3.a;
                    s11 = p3.b;
                    n5 = p5.a;
                    s9 = p5.b;
                } else {
                    if (com.corrodinggames.rts.gameFramework.k.l.a) {
                        Log.d("RustedWarfare", "closing path runFromOppositeSide=true");
                    }
                    s10 = p5.a;
                    s11 = p5.b;
                    n5 = p3.a;
                    s9 = p3.b;
                }
                bl2 = true;
                break;
            }
            p4.a(this, by2, true);
            byte by4 = 0;
            byte by5 = 7;
            if (n2 != 0) {
                by4 = 0;
                by5 = 7;
            } else {
                by = 2;
                if (this.f != null && this.f[p4.a * s3 + p4.b] > 1) {
                    by = 1;
                }
                by4 = (byte)(by3 - by);
                by5 = (byte)(by3 + by);
            }
            for (by = by4; by <= by5; by = (byte)((byte)(by + 1))) {
                byte by6;
                int n21;
                int n22;
                p2.a(p4);
                byte by7 = by;
                if (by7 > 7) {
                    by7 = (byte)(by7 - 8);
                }
                if (by7 < 0) {
                    by7 = (byte)(by7 + 8);
                }
                if (by7 == 0) {
                    p2.a = (short)(p2.a + 1);
                }
                if (by7 == 1) {
                    p2.a = (short)(p2.a + 1);
                    p2.b = (short)(p2.b + 1);
                }
                if (by7 == 2) {
                    p2.b = (short)(p2.b + 1);
                }
                if (by7 == 3) {
                    p2.b = (short)(p2.b + 1);
                    p2.a = (short)(p2.a - 1);
                }
                if (by7 == 4) {
                    p2.a = (short)(p2.a - 1);
                }
                if (by7 == 5) {
                    p2.a = (short)(p2.a - 1);
                    p2.b = (short)(p2.b - 1);
                }
                if (by7 == 6) {
                    p2.b = (short)(p2.b - 1);
                }
                if (by7 == 7) {
                    p2.b = (short)(p2.b - 1);
                    p2.a = (short)(p2.a + 1);
                }
                if (p2.a < 0 || p2.a >= n9 || p2.b < 0 || p2.b >= s3 || (n22 = p2.a(this)) == -1) continue;
                int n23 = p2.a(this, by2);
                if (p2.b(this, by2)) continue;
                if (p2.a != p4.a && p2.b != p4.b) {
                    if (this.a(p2.a, p4.b) == -1 || this.a(p4.a, p2.b) == -1) continue;
                    n21 = n20 + (14 + n22) + 1;
                } else {
                    n21 = n20 + (10 + n22) + 1;
                }
                if (by3 != by7) {
                    if (n2 == 0) {
                        n21 += com.corrodinggames.rts.gameFramework.k.o.b(by3, by7);
                    } else if (n6 == 0) {
                        n21 = bl ? (n21 += com.corrodinggames.rts.gameFramework.k.o.c(by3, by7)) : (n21 += com.corrodinggames.rts.gameFramework.k.o.b(by3, by7));
                    }
                }
                if (this.f != null) {
                    n21 += (4 - this.f[p2.a * s3 + p2.b]) * n7;
                }
                if (n8 > 0 && this.f != null && (by6 = this.f[p2.a * s3 + p2.b]) <= n8) {
                    n21 += 100;
                }
                if (n23 >= this.i && n21 >= n23) continue;
                p2.a(this, by2, by7);
                p2.a(this, by2, false);
                p2.a(this, by2, n21);
                m2.a(n21 - this.i, p2.a, p2.b);
            }
        }
        if (com.corrodinggames.rts.gameFramework.k.l.a) {
            Log.d("RustedWarfare", "grid path finshed in :" + n3 + " ticks");
            long l4 = System.currentTimeMillis() - this.y;
            Log.d("RustedWarfare", "grid path done in:" + l4);
        }
        long l5 = System.currentTimeMillis();
        if (!bl2) {
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                Log.d("RustedWarfare", "could not find end node");
            }
            long l6 = System.currentTimeMillis();
            float f7 = -1.0f;
            p p6 = new p();
            for (n2 = 0; n2 < n9; n2 = (short)(n2 + 1)) {
                for (s2 = 0; s2 < s3; s2 = (short)(s2 + 1)) {
                    p6.a((short)n2, s2);
                    if (!p6.e(this, (byte)0)) continue;
                    float f8 = com.corrodinggames.rts.gameFramework.f.a((float)n2, (float)s2, (float)n10, (float)n11);
                    if (f7 != -1.0f && !(f8 < f7)) continue;
                    f7 = f8;
                    n5 = n2;
                    s9 = s2;
                }
            }
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                long l7 = System.currentTimeMillis() - l6;
                Log.d("RustedWarfare", "got closest node in:" + l7);
            }
        }
        linkedList.clear();
        if (n5 != -1 && s9 != -1) {
            LinkedList linkedList2 = this.a((byte)0, (short)n5, s9);
            linkedList.addAll(this.a(linkedList2));
        }
        if (s10 != -1 && s11 != -1) {
            LinkedList linkedList3 = this.a((byte)1, s10, s11);
            linkedList.addAll(linkedList3);
        }
        if (linkedList.size() > 1) {
            linkedList.remove(0);
        }
        this.z = com.corrodinggames.rts.gameFramework.l.V();
        if (com.corrodinggames.rts.gameFramework.k.l.a) {
            long l8 = this.z - this.y;
            Log.d("RustedWarfare", "path(" + k2.e + ") finished in:" + l8);
        }
        if (com.corrodinggames.rts.gameFramework.k.l.l && !com.corrodinggames.rts.gameFramework.l.B().bH) {
            Debug.stopMethodTracing();
            com.corrodinggames.rts.gameFramework.k.l.l = false;
        }
        return linkedList;
    }

    public LinkedList a(byte by, short s2, short s3) {
        p p2;
        LinkedList<p> linkedList = new LinkedList<p>();
        p p3 = new p(s2, s3);
        linkedList.add(p3);
        while ((p2 = p3.f(this, by)) != null) {
            linkedList.add(p2);
            p3 = p2;
        }
        return linkedList;
    }

    public LinkedList a(LinkedList linkedList) {
        LinkedList<p> linkedList2 = new LinkedList<p>();
        for (p p2 : linkedList) {
            linkedList2.addFirst(p2);
        }
        return linkedList2;
    }

    public void f() {
        this.g();
    }

    /*
     * WARNING - void declaration
     */
    public void g() {
        short s2;
        void var18_19;
        byte by;
        short s3;
        short s4;
        com.corrodinggames.rts.gameFramework.k.f f2 = (com.corrodinggames.rts.gameFramework.k.f)this.D;
        g g2 = new g(this.g, this.h);
        if (com.corrodinggames.rts.gameFramework.k.l.l && !com.corrodinggames.rts.gameFramework.l.B().bH) {
            Debug.startMethodTracing((String)"pathTrace", (int)110000000);
        }
        int n2 = 7;
        byte by3 = 0;
        if (com.corrodinggames.rts.gameFramework.k.l.a) {
            Log.d("RustedWarfare", "starting path for:" + this.n.a.toString());
        }
        p p2 = this.r;
        p p3 = this.q;
        int n3 = this.h;
        int n4 = this.g;
        this.y = com.corrodinggames.rts.gameFramework.l.V();
        short s42 = f2.h;
        short s5 = f2.i;
        boolean bl = f2.k;
        this.d();
        this.a(f2.o, f2.h, f2.i, f2.j);
        short s6 = f2.l;
        short by4 = f2.m;
        short s7 = f2.n;
        LinkedList linkedList = new LinkedList();
        if (this.n.a.equals((Object)ao.a)) {
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                Log.d("RustedWarfare", "no point pathing for none");
            }
            return;
        }
        int n5 = 0;
        short s8 = s6;
        short by5 = by4;
        short s9 = s7;
        if (this.e != null) {
            short n7;
            short[] sArray = this.e;
            s4 = sArray[s42 * n3 + s5];
            s3 = 1;
            if (s4 == -1) {
                s3 = 0;
            } else {
                for (n7 = (short)(s6 - s7); n7 <= s6 + s7; n7 = (short)(n7 + 1)) {
                    for (short s10 = (short)(by4 - s7); s10 <= by4 + s7; s10 = (short)(s10 + 1)) {
                        if (n7 < 0 || n7 >= n4 || s10 < 0 || s10 >= n3 || s4 != sArray[n7 * n3 + s10]) continue;
                        s3 = 0;
                    }
                }
            }
            if (s3 != 0) {
                byte by2;
                float f3;
                short s11;
                if (com.corrodinggames.rts.gameFramework.k.l.a) {
                    Log.d("RustedWarfare", "end is blocked on isolated groups");
                }
                n7 = s8;
                short n8 = by5;
                float f4 = -1.0f;
                for (s11 = (short)(s6 - 25); s11 <= s6 + 25; s11 = (short)(s11 + 1)) {
                    for (by = (short)(by4 - 25); by <= by4 + 25; by = (short)(by + 1)) {
                        if (s11 < 0 || s11 >= n4 || by < 0 || by >= n3 || s4 != sArray[s11 * n3 + by] && sArray[s11 * n3 + by] != 0) continue;
                        f3 = com.corrodinggames.rts.gameFramework.f.a((float)s11, (float)by, (float)s6, (float)by4);
                        if (f4 != -1.0f && !(f3 < f4)) continue;
                        f4 = f3;
                        n7 = s11;
                        byte by6 = by;
                        s9 = 0;
                    }
                }
                if (f4 == -1.0f) {
                    for (s11 = 0; s11 < n4; s11 = (short)(s11 + 1)) {
                        for (by = 0; by < n3; by = (short)(by + 1)) {
                            if (s4 != sArray[s11 * n3 + by] && sArray[s11 * n3 + by] != 0) continue;
                            f3 = com.corrodinggames.rts.gameFramework.f.a((float)s11, (float)by, (float)s6, (float)by4);
                            if (f4 != -1.0f && !(f3 < f4)) continue;
                            f4 = f3;
                            n7 = s11;
                            by2 = by;
                            s9 = 0;
                        }
                    }
                }
                s8 = n7;
                var18_19 = by2;
                if (com.corrodinggames.rts.gameFramework.k.l.a) {
                    long l2 = System.currentTimeMillis() - this.y;
                    Log.d("RustedWarfare", "fakeNode search was:" + l2);
                }
            }
        }
        boolean bl2 = true;
        block6: for (s4 = (short)(s8 - s9); s4 <= s8 + s9; s4 = (short)(s4 + 1)) {
            for (s3 = (short)(var18_19 - s9); s3 <= var18_19 + s9; s3 = (short)(s3 + 1)) {
                if (s4 < 0 || s4 >= n4 || s3 < 0 || s3 >= n3 || this.a(s4, s3) == -1) continue;
                bl2 = false;
                break block6;
            }
        }
        if (bl2) {
            short s12;
            s4 = s8;
            s3 = var18_19;
            float f5 = -1.0f;
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                Log.d("RustedWarfare", "end is blocked on non isolated groups");
            }
            short s13 = (short)(s8 - 9);
            while (s12 <= s8 + 9) {
                for (short s14 = (short)(var18_19 - 9); s14 <= var18_19 + 9; s14 = (short)(s14 + 1)) {
                    if (s12 < 0 || s12 >= n4 || s14 < 0 || s14 >= n3 || this.a(s12, s14) == -1) continue;
                    float f6 = com.corrodinggames.rts.gameFramework.f.a((float)s12, (float)s14, (float)s8, (float)var18_19);
                    if (f5 != -1.0f && !(f6 < f5)) continue;
                    f5 = f6;
                    s4 = s12;
                    s3 = s14;
                    s9 = 0;
                }
                s12 = (short)(s12 + true);
            }
            if (f5 == -1.0f) {
                short s15;
                boolean bl3 = false;
                while (s15 < n4) {
                    for (int n6 = 0; n6 < n3; n6 = (int)((short)(n6 + 1))) {
                        if (this.a(s15, n6) == -1) continue;
                        float f7 = com.corrodinggames.rts.gameFramework.f.a((float)s15, (float)n6, (float)s8, (float)var18_19);
                        if (f5 != -1.0f && !(f7 < f5)) continue;
                        f5 = f7;
                        s4 = s15;
                        s3 = (short)n6;
                        s9 = 0;
                    }
                    s15 = (short)(s15 + true);
                }
            }
            s8 = s4;
            s2 = s3;
            if (com.corrodinggames.rts.gameFramework.k.l.a) {
                long l3 = System.currentTimeMillis() - this.y;
                Log.d("RustedWarfare", "fakeNode search was:" + l3);
            }
        }
        if (com.corrodinggames.rts.gameFramework.k.l.a && (s8 != s6 || s2 != by4)) {
            Log.d("RustedWarfare", "Moved end to fakeEndX:" + s8 + " fakeEndY:" + s2);
        }
        this.o.a(s8, s2);
        this.o.a(0, s42, s5);
        this.p.a(s42, s5);
        this.a(s8, s2, s9);
        this.p.a(0, s8, s2);
        bl2 = false;
        s4 = 1;
        s3 = (short)-1;
        int n7 = -1;
        int n8 = -1;
        int n9 = -1;
        int n10 = 0;
        while (!bl2) {
            byte by7;
            ++n10;
            if (f2.w) {
                return;
            }
            ++n5;
            by = 0;
            by = 1;
            m m2 = this.p;
            n n11 = m2.c();
            if (n11 == null) break;
            p p4 = p3.a(n11);
            int n12 = p4.a(this, by);
            byte by8 = (byte)(g2.a(p4) - 1);
            boolean bl4 = g2.b(p4);
            boolean bl5 = false;
            g2.a(p4, true);
            byte by9 = 0;
            byte by10 = 7;
            if (bl4) {
                by9 = 0;
                by10 = 7;
            } else {
                by7 = 2;
                if (this.f != null && this.f[p4.a * n3 + p4.b] > 1) {
                    by7 = 1;
                }
                by9 = (byte)(by8 - by7);
                by10 = (byte)(by8 + by7);
            }
            for (by7 = by9; by7 <= by10; by7 = (byte)((byte)(by7 + 1))) {
                byte by11;
                int n13;
                int n14;
                p2.a(p4);
                byte by12 = by7;
                if (by12 > 7) {
                    by12 = (byte)(by12 - 8);
                }
                if (by12 < 0) {
                    by12 = (byte)(by12 + 8);
                }
                if (by12 == 0) {
                    p2.a = (short)(p2.a + 1);
                }
                if (by12 == 1) {
                    p2.a = (short)(p2.a + 1);
                    p2.b = (short)(p2.b + 1);
                }
                if (by12 == 2) {
                    p2.b = (short)(p2.b + 1);
                }
                if (by12 == 3) {
                    p2.b = (short)(p2.b + 1);
                    p2.a = (short)(p2.a - 1);
                }
                if (by12 == 4) {
                    p2.a = (short)(p2.a - 1);
                }
                if (by12 == 5) {
                    p2.a = (short)(p2.a - 1);
                    p2.b = (short)(p2.b - 1);
                }
                if (by12 == 6) {
                    p2.b = (short)(p2.b - 1);
                }
                if (by12 == 7) {
                    p2.b = (short)(p2.b - 1);
                    p2.a = (short)(p2.a + 1);
                }
                if (p2.a < 0 || p2.a >= n4 || p2.b < 0 || p2.b >= n3 || (n14 = p2.a(this)) == -1) continue;
                int n15 = p2.a(this, by);
                if (g2.c(p2)) continue;
                if (p2.a != p4.a && p2.b != p4.b) {
                    if (this.a(p2.a, p4.b) == -1 || this.a(p4.a, p2.b) == -1) continue;
                    n13 = n12 + (14 + n14) + 1;
                } else {
                    n13 = n12 + (10 + n14) + 1;
                }
                if (by8 != by12 && !bl4) {
                    n13 += com.corrodinggames.rts.gameFramework.k.o.b(by8, by12);
                }
                if (this.f != null) {
                    n13 += (4 - this.f[p2.a * n3 + p2.b]) * n2;
                }
                if (by3 > 0 && this.f != null && (by11 = this.f[p2.a * n3 + p2.b]) <= by3) {
                    n13 += 100;
                }
                if (n15 >= this.i && n13 >= n15) continue;
                g2.a(p2, (byte)(by12 + 1));
                g2.a(p2, false);
                p2.a(this, by, n13);
                m2.a(n13 - this.i, p2.a, p2.b);
            }
        }
        if (com.corrodinggames.rts.gameFramework.k.l.a) {
            Log.d("RustedWarfare", "grid path finshed in :" + n10 + " ticks");
            long l4 = System.currentTimeMillis() - this.y;
            Log.d("RustedWarfare", "grid path done in:" + l4);
        }
        long l5 = System.currentTimeMillis();
        g2.c = s8;
        g2.d = s2;
        f2.b = g2;
        com.corrodinggames.rts.gameFramework.k.l.e = f2;
        this.z = com.corrodinggames.rts.gameFramework.l.V();
        if (com.corrodinggames.rts.gameFramework.k.l.a) {
            long l6 = this.z - this.y;
            Log.d("RustedWarfare", "path(" + f2.e + ") finished in:" + l6);
        }
        if (com.corrodinggames.rts.gameFramework.k.l.l && !com.corrodinggames.rts.gameFramework.l.B().bH) {
            Debug.stopMethodTracing();
            com.corrodinggames.rts.gameFramework.k.l.l = false;
        }
    }

    static {
        t.add(new p(-9, -9));
        t.add(new p(-9, -9));
        t.add(new p(-9, -9));
        u = 0;
    }
}
