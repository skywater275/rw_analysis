/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.TMXMapLoader;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalc;
import com.corrodinggames.rts.gameFramework.pathfinding.PathSolver;
import com.corrodinggames.rts.gameFramework.pathfinding.PathSolverRunner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public strictfp final class PathFinder {
    public static final boolean m = false;  // 02b k/l.java L30
    static final boolean a = false;
    static boolean b = !com.corrodinggames.rts.gameFramework.GlobalState.as;
    static boolean c = false;
    static boolean d = false;
    public static PathCostCalc pathSolver;
    static boolean f;
    static boolean g;
    static boolean h;
    static int i;
    static boolean j;
    static ArrayList k;
    static boolean l;
    public static final boolean useCache;
    public boolean cacheEnabled = true;
    PathSolverRunner o = new PathSolverRunner(this);  // 02b l.java L32: o o (KeyCodeMapper 为幻觉名)
    boolean p = true;
    MapEngine q;  // 02b L34: com.corrodinggames.rts.game.b.b q (ByteSlot 为幻觉名)
    int r;
    short s;
    short t;
    ArrayList u = new ArrayList();
    PathSolver[] v = new PathSolver[0];
    public Paint debugPaint = new Paint();
    public PathSolver landSolver;
    public PathSolver waterSolver;
    public PathSolver airSolver;
    public PathSolver A;
    public PathSolver B;
    public PathSolver C;
    public PathSolver D;
    public PathSolver E;
    Paint F = new Paint();
    Object G = new Object();
    ArrayList H;
    LinkedList I = new LinkedList();
    LinkedList J = new LinkedList();
    Object K = new Object();

    public PathSolver a(MovementTypeEnum ao2) {
        for (PathSolver i2 : this.v) {
            if (i2.a != ao2) continue;
            return i2;
        }
        return null;
    }

    public boolean a(MovementTypeEnum ao2, int n, int n2) {
        PathSolver i2 = this.a(ao2);  // 02b L72: i var4 (CrashHandler 为幻觉名)
        return this.a(i2, n, n2);
    }

    public boolean b(MovementTypeEnum ao2, int n, int n2) {
        PathSolver i2 = this.a(ao2);  // 02b L77: i var4
        return this.a(i2, n, n2, true);
    }

    public boolean a(PathSolver i2, int n, int n2) {
        return this.a(i2, n, n2, false);
    }

    public boolean a(PathSolver i2, int n2, int n3, boolean bl) {
        if (!this.q.c(n2, n3)) {
            return true;
        }
        if (i2.a == MovementTypeEnum.d) {
            return false;
        }
        int n4 = n2 * this.t + n3;
        if (!bl && i2.e[n4] == -1) {
            return true;
        }
        return i2.d[n4] == -1 || i2.f[n4] == -1;
    }

    public final int b(PathSolver i2, int n2, int n3) {
        if (!this.q.c(n2, n3)) {
            return -1;
        }
        if (i2.a == MovementTypeEnum.d) {
            return 0;
        }
        int n4 = n2 * this.t + n3;
        if (i2.d[n4] == -1 || i2.e[n4] == -1 || i2.f[n4] == -1) {
            return -1;
        }
        return i2.d[n4] + i2.e[n4] + i2.f[n4] * 10;
    }

    public final int c(PathSolver i2, int n2, int n3) {
        if (!this.q.c(n2, n3)) {
            return -1;
        }
        if (i2.a == MovementTypeEnum.d) {
            return 4;
        }
        if (i2.j == null) {
            return -1;
        }
        int n4 = n2 * this.t + n3;
        return i2.j[n4];
    }

    public boolean a(int n2, int n3) {
        if (!this.q.c(n2, n3)) {
            return true;
        }
        int n4 = n2 * this.t + n3;
        if (this.D.d[n4] != -1) {
            return false;
        }
        return this.A.d[n4] != -1;
    }

    public boolean b(int n2, int n3) {
        if (!this.q.c(n2, n3)) {
            return true;
        }
        int n4 = n2 * this.t + n3;
        if (this.C.d[n4] != -1) {
            return false;
        }
        return this.E.d[n4] != -1;
    }

    public synchronized void a(MapEngine b2, boolean bl) {  // 02b L138: a(b.b,boolean)
        this.d();
        com.corrodinggames.rts.gameFramework.GlobalState.e("PathEngine: Setting up map costs");
        boolean bl2 = false;
        if (bl && this.q != null && this.q == b2 && this.s == b2.tileWidth && this.t == b2.mapHeight) {  // 02b L142: var1.u.n/.u.o
            if (this.r == com.corrodinggames.rts.gameFramework.pathfinding.PathSolver.a(b2)) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("PathEngine: Keeping existing map costs");
                bl2 = true;
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("PathEngine: Error: Map checksum does not match!!!");
            }
        }
        if (bl2) {
            // empty if block
        }
        this.q = b2;
        this.r = com.corrodinggames.rts.gameFramework.pathfinding.PathSolver.a(b2);
        this.s = (short)b2.tileWidth;  // 02b L157: var1.u.n
        this.t = (short)b2.mapHeight;  // 02b L158: var1.u.o
        pathSolver = null;
        this.u.clear();
        this.v = new PathSolver[0];
        this.landSolver = new PathSolver(this, MovementTypeEnum.a, this.s, this.t);
        this.waterSolver = new PathSolver(this, MovementTypeEnum.b, this.s, this.t);
        this.waterSolver.b();
        this.waterSolver.a((UnitType)null);  // 02b L165: this.y.a((y)null)
        this.airSolver = new PathSolver(this, MovementTypeEnum.c, this.s, this.t);
        this.A = new PathSolver(this, MovementTypeEnum.e, this.s, this.t);
        this.A.b();
        this.A.a((UnitType)null);  // 02b L169
        this.B = new PathSolver(this, MovementTypeEnum.d, this.s, this.t);
        this.C = new PathSolver(this, MovementTypeEnum.f, this.s, this.t);
        this.C.b();
        this.C.a((UnitType)null);  // 02b L173
        this.D = new PathSolver(this, MovementTypeEnum.g, this.s, this.t);
        this.D.b();
        this.D.a((UnitType)null);  // 02b L176
        this.E = new PathSolver(this, MovementTypeEnum.h, this.s, this.t);
        this.E.b();
        this.E.a((UnitType)null);  // 02b L179
        for (PathSolverRunner o2 : (java.util.Collection<PathSolverRunner>) (java.util.Collection) this.H) {
            o2.a(b2);
        }
        this.o.a(b2);
        com.corrodinggames.rts.gameFramework.GlobalState.e("PathEngine: Ready");
    }

    public void a() {
        int n2;
        int n3;
        int n4;
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PathSolver i2 = this.waterSolver;  // 02b L193: i var2
        Rect rect = new Rect();
        float f2 = l2.cw;
        float f3 = l2.cx;
        float f4 = l2.cA;
        float f5 = l2.cB;
        TMXMapLoader e2 = l2.bL.groundLayer;
        int n5 = (int)(f2 * this.q.float1 - 1.0f);  // 02b b.b.r
        if (n5 < 0) {
            n5 = 0;
        }
        if ((n4 = (int)(f3 * this.q.float2 - 1.0f)) < 0) {  // 02b b.b.s
            n4 = 0;
        }
        if ((n3 = (int)((f2 + f4) * this.q.float1 + 1.0f)) > this.s - 1) {
            n3 = this.s - 1;
        }
        if ((n2 = (int)((f3 + f5) * this.q.float2 + 1.0f)) > this.t - 1) {
            n2 = this.t - 1;
        }
        for (int i3 = n5; i3 < n3 + 1; ++i3) {
            for (int i4 = n4; i4 < n2 + 1; ++i4) {
                MapLayer g2 = e2.a(i3, i4);
                if (g2 == null) continue;
                int n6 = i3 * this.q.tilePixelWidth;  // 02b b.b.n
                int n7 = i4 * this.q.tilePixelHeight;  // 02b b.b.o
                rect.a(n6, n7, n6 + this.q.tilePixelWidth, n7 + this.q.tilePixelHeight);
                rect.a((int)(-f2), (int)(-f3));
                boolean bl = rect.b((int)(l2.bS.x / l2.cX), (int)(l2.bS.y / l2.cX));
                if (g && !bl) continue;
                int n8 = i2.d[i3 * this.t + i4];
                int n9 = i2.e[i3 * this.t + i4];
                int n10 = i2.f[i3 * this.t + i4];
                n8 = n8 == -1 ? 255 : (n8 *= 2);
                n9 = n9 == -1 ? 255 : (n9 *= 2);
                if (n10 == -1) {
                    n10 = 255;
                } else {
                    if (n10 != 0) {
                        n10 += 30;
                    }
                    n10 *= 2;
                }
                this.F.a(128, n8, n9, n10);
                l2.bO.b(rect, this.F);
                if (!bl || i2.f == null) continue;
                l2.bO.a("o:" + i2.f[i3 * this.t + i4], (float)rect.d(), (float)rect.e(), l2.dp);
            }
        }
    }

    public void a(UnitType y2) {  // 02b L269: a(y) (y=UnitType)
        if (y2 != null) {
            com.corrodinggames.rts.game.PlayerState.b(y2);
        }
        for (PathSolver i2 : this.v) {
            i2.c(y2);
        }
        this.waterSolver.a(y2);
        this.C.a(y2);
        this.D.a(y2);
        this.E.a(y2);
    }

    public void b() {
        for (PathSolver i2 : this.v) {
            i2.e();
        }
    }

    public PathFinder() {
        this.H = new ArrayList();
        this.H.add(new PathSolverRunner(this));
        int n2 = com.corrodinggames.rts.gameFramework.GameUtils.c();  // 02b L301: f.c()
        if (n2 > 1) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("PathEngine", "We have " + n2 + " cores, creating extra solvers");
            this.H.add(new PathSolverRunner(this));
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("PathEngine", "We only have one core, using single solver");
        }
        for (PathSolverRunner o2 : (java.util.Collection<PathSolverRunner>) (java.util.Collection) this.H) {
            o2.c();
        }
    }

    public void c() {
        for (PathCostCalculator k2 : (java.util.Collection<PathCostCalculator>) (java.util.Collection) this.I) {
            k2.w = true;
        }
        this.I.clear();
        this.h();
    }

    public void d() {
        for (PathCostCalculator k2 : (java.util.Collection<PathCostCalculator>) (java.util.Collection) this.I) {
            this.a(k2);
        }
        this.I.clear();
        this.h();
    }

    public void a(PathSolver i2, boolean bl) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!bl) {
            if (i2.k + 50 < l2.bx) {
                i2.k = l2.bx - 40;
                i2.e();
            }
            i2.a(bl);
        } else {
            if (i2.k + 30 < l2.bx) {
                i2.k = l2.bx;
                i2.e();
            }
            i2.a(bl);
        }
    }

    public PathCostCalculator a(boolean bl) {
        PathCostCalculator k2 = com.corrodinggames.rts.game.units.UnitType.L ? new PathCostCalc(this, bl) : new PathCostCalculator(this, bl);
        return k2;
    }

    public void a(PathCostCalculator k2, boolean bl) {
        this.a(k2, bl, false);
    }

    public void a(PathCostCalculator k2, boolean bl, boolean bl2) {
        if (!this.p) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("PathEngine", "Cannot start new path, not running");
            return;
        }
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PathSolver i2 = this.a(k2.o);  // 02b L380: i var5
        this.a(i2, bl);
        k2.e();
        k2.t = 300.0f;
        int n2 = com.corrodinggames.rts.gameFramework.GameUtils.d(k2.h - k2.l);  // 02b L384: f.d
        int n3 = com.corrodinggames.rts.gameFramework.GameUtils.d(k2.i - k2.m);  // 02b L385: f.d
        if (n2 < 15 && n3 < 15) {
            k2.t = 12.0f;
        } else if (n2 < 50 && n3 < 50) {
            k2.t = 16.0f;
        } else if (n2 < 200 && n3 < 200) {
            k2.t = 24.0f;
        } else if (n2 < 400 && n3 < 400) {
            k2.t = 50.0f;
        } else if (n2 < 1000 && n3 < 1000) {
            k2.t = 100.0f;
        } else if (n2 < 2000 && n3 < 2000) {
            k2.t = 200.0f;
        }
        if (!l2.bX.B && !l2.cb.i()) {
            k2.t = n2 < 1000 && n3 < 1000 ? 180.0f : 360.0f;
        }
        if (k2.r) {
            k2.t *= 2.0f;
            k2.t += 50.0f;
        }
        k2.s = k2.t;
        if (!this.cacheEnabled || bl2) {
            this.o.a(k2);
            this.o.b();
            this.I.add(k2);
        } else {
            this.b(k2);
            this.I.add(k2);
        }
    }

    public void a(float f2) {
        this.i();
    }

    public void b(float f2) {
        for (PathSolver i2 : this.v) {
            i2.p = 0;
            if (!i2.o) continue;
            i2.o = false;
            i2.c(null);
        }
        this.i();
        this.d(f2);
    }

    public void c(float f2) {
        if (j) {
            for (Object object : k) {
                ((PathCostCalculator) object).h();
                ((PathCostCalculator) object).g();
            }
        }
        if (d) {
            UnitInstance am2;
            Object object;
            boolean bl = true;
            object = com.corrodinggames.rts.gameFramework.GlobalState.B();
            if (((com.corrodinggames.rts.gameFramework.GlobalState)object).bS.bZ.b > 0 && (am2 = ((com.corrodinggames.rts.gameFramework.GlobalState)object).bS.bZ.a(0)) instanceof UnitType) {
                UnitType y2 = (UnitType)am2;  // 02b L464: y var5 (GameSaver 为幻觉名)
                if (y2.au != null) {
                    y2.au.d(y2);
                    bl = false;
                }
            }
            if (bl) {
                // empty if block
            }
        }
        if (f) {  // 02b L477: if(f) (PathCostCalc 类名当布尔为幻觉)
            this.a();
        }
        if (h) {
            // empty if block
        }
    }

    public boolean e() {
        for (PathCostCalculator k2 : (java.util.Collection<PathCostCalculator>) (java.util.Collection) this.I) {
            if (!(k2.t <= 0.0f) || k2.c()) continue;
            return true;
        }
        return false;
    }

    public String f() {
        String string2;  // 02b L519: String var6
        Iterator iterator = this.I.iterator();
        String string = null;
        int n2 = 0;
        while (iterator.hasNext()) {
            PathCostCalculator k2 = (PathCostCalculator) iterator.next();
            if (!(k2.t <= 0.0f) || k2.c()) continue;
            if (string == null) {
                float f2 = com.corrodinggames.rts.gameFramework.GameUtils.b((float)k2.h, (float)k2.i, (float)k2.l, (float)k2.m);  // 02b L511: f.b
                string = "[distance:" + f2 + ", allowedDelay:" + k2.s + " lowPriority:" + k2.r + "]";
            }
            ++n2;
        }
        string2 = "(total:" + n2 + ") ";
        if (string != null) {
            string2 = string2 + string;
        }
        return string2;
    }

    private void d(float f2) {
        Iterator iterator = this.I.iterator();
        while (iterator.hasNext()) {
            PathCostCalculator k2 = (PathCostCalculator) iterator.next();
            if (k2.t <= 0.0f) {
                k2.t = 0.0f;
                k2.u = true;
                if (j) {
                    k.add(k2);
                    if (k.size() > 10) {
                        k.remove(0);
                    }
                }
                if (!k2.c()) {
                    if (com.corrodinggames.rts.gameFramework.GlobalState.B().isNetworkedOrReplay()) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("PathEngine", "updateUnfinishedPaths: path wasn't solved, isGoingToBlockThisFrame did not protect");
                    }
                    this.a(k2);
                }
                if (!k2.c()) continue;
                iterator.remove();
                continue;
            }
            k2.t -= f2;
        }
    }

    private PathCostCalculator g() {
        PathCostCalculator k2 = null;
        for (PathCostCalculator k3 : (java.util.Collection<PathCostCalculator>) (java.util.Collection) this.J) {
            if (k2 != null && !(k2.t > k3.t)) continue;
            k2 = k3;
        }
        if (k2 == null) {
            throw new RuntimeException("Failed to find any paths");
        }
        if (!this.J.remove(k2)) {
            throw new RuntimeException("Failed remove found path");
        }
        return k2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void b(PathCostCalculator k2) {
        Object object = this.K;
        synchronized (object) {
            this.J.add(k2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void h() {
        Object object = this.K;
        synchronized (object) {
            this.J.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void i() {
        LinkedList linkedList = this.J;
        if (linkedList.size() > 0) {
            Object object = this.K;
            synchronized (object) {
                PathSolverRunner o2;  // 02b L600: o var3
                while (linkedList.size() > 0 && (o2 = this.j()) != null) {
                    PathCostCalculator k2 = this.g();
                    if (k2.v) continue;
                    this.a(o2, k2);
                }
            }
        }
    }

    private PathSolverRunner j() {
        for (PathSolverRunner o2 : (java.util.Collection<PathSolverRunner>) (java.util.Collection) this.H) {
            if (!o2.s) continue;
            return o2;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(PathCostCalculator k2) {
        if (!k2.v) {
            while (true) {
                Object object = this.G;
                synchronized (object) {
                    PathSolverRunner o2 = this.j();  // 02b L635: o var3
                    if (o2 != null) {
                        this.a(o2, k2);
                        break;
                    }
                    try {
                        this.G.wait(2000L);
                    }
                    catch (InterruptedException interruptedException) {
                        // empty catch block
                    }
                }
            }
        }
        boolean bl = false;
        long l2 = com.corrodinggames.rts.gameFramework.GlobalState.V();
        while (true) {
            Object object = this.G;
            synchronized (object) {
                if (k2.c()) {
                    break;
                }
                bl = true;
                this.i();
                try {
                    this.G.wait(2000L);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
        }
        if (bl && b) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("PathEngine", "We were blocked path(" + k2.e + ") for:" + (com.corrodinggames.rts.gameFramework.GlobalState.V() - l2));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(PathSolverRunner o2, PathCostCalculator k2) {
        PathCostCalculator k3 = k2;
        synchronized (k3) {
            if (!k2.v) {
                o2.a(k2);
                o2.a();
            }
        }
    }

    static {
        f = false;
        g = false;
        h = false;
        i = 20;
        j = false;
        k = new ArrayList();
        l = false;
        useCache = false;
    }



}