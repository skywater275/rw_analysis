/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.gameFramework.SleepThread;

import android.graphics.Paint;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.pathfinding.PathNode;
import com.corrodinggames.rts.gameFramework.pathfinding.PathSolver;
import com.corrodinggames.rts.gameFramework.pathfinding.PathFinder;
import java.util.LinkedList;
import java.io.IOException;

public strictfp class PathCostCalculator {
    private PathFinder a;
    protected int e;
    protected static int f;
    public int g;
    protected short h;
    protected short i;
    protected Float j;
    protected boolean k;
    protected short l;
    protected short m;
    protected short n;
    protected MovementTypeEnum o;
    public boolean p;
    public int q;
    public boolean r;
    public float s;
    public float t;
    public boolean u;
    protected boolean v;
    protected boolean w;
    protected LinkedList x;
    public byte[] y;
    public byte[] z;
    public byte[] A;
    public short[] B;
    public byte[] C;

    public PathCostCalculator(PathFinder l2, boolean bl) {
        this.a = l2;
        if (bl) {
            this.e = f++;
        }
        com.corrodinggames.rts.gameFramework.GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.g = l3.bx;
    }

    /* 02b k/k.java L53: as2.a(String,boolean) 抛 IOException */
    public void a(OutputNetStream as2) throws IOException {
        if (this.x == null) {
            as2.a(false);
        } else {
            as2.a(true);
            as2.a("p", true);
            as2.a(this.x.size());
            if (this.x.size() != 0) {
                PathNode p2 = (PathNode) this.x.get(0);
                as2.a(p2.a);
                as2.a(p2.b);
                for (int j = 1; j < this.x.size(); ++j) {
                    int n2;
                    boolean bl;
                    PathNode p3 = (PathNode) this.x.get(j);
                    int n3 = p3.a - p2.a;
                    int n4 = p3.b - p2.b;
                    boolean bl2 = bl = com.corrodinggames.rts.gameFramework.GameUtils.d(n3) > 1 || com.corrodinggames.rts.gameFramework.GameUtils.d(n4) > 1;  // 02b L69: f.d
                    if (bl) {
                        com.corrodinggames.rts.gameFramework.GlobalState.e("writeOutCompressedPath: out of range:" + n3 + "," + n4);
                        n2 = 128;
                    } else {
                        n2 = n3 + 1 + (n4 + 1 << 2);
                    }
                    as2.c(n2);
                    if (bl) {
                        as2.a(p3.a);
                        as2.a(p3.b);
                    }
                    p2 = p3;
                }
            }
            as2.a("p");
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        boolean bl = k2.readBoolean();
        if (!bl) {
            this.x = null;
        } else {
            k2.a("p", true);
            int n2 = k2.readInt();
            if (n2 > 160000 || n2 < 0) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("readInCompressedPath: Path too big at:" + n2);
                n2 = -1;
            }
            if (n2 != -1) {
                this.u = true;
                if (this.x == null) {
                    this.x = new LinkedList();
                }
                this.x.clear();
            }
            if (n2 > 0) {
                short s2 = k2.v();
                short s3 = k2.v();
                this.x.add(new PathNode(s2, s3));
                for (int j = 1; j < n2; ++j) {
                    byte by = k2.d();
                    int n3 = 3;
                    int n4 = 12;
                    if (by < 128) {
                        boolean bl2;
                        int n5 = (by & n3) - 1;
                        int n6 = ((by & n4) >> 2) - 1;
                        boolean bl3 = bl2 = com.corrodinggames.rts.gameFramework.GameUtils.d(n5) > 1 || com.corrodinggames.rts.gameFramework.GameUtils.d(n6) > 1;  // 02b L125: f.d
                        if (bl2) {
                            com.corrodinggames.rts.gameFramework.GlobalState.e("readInCompressedPath: out of range but shouldn't be:" + n5 + "," + n6 + " for: " + by);
                        }
                        s2 = (short)(s2 + n5);
                        s3 = (short)(s3 + n6);
                        this.x.add(new PathNode(s2, s3));
                        continue;
                    }
                    com.corrodinggames.rts.gameFramework.GlobalState.e("readInCompressedPath: out of range unpack:" + s2 + "," + s3);
                    s2 = k2.v();
                    s3 = k2.v();
                    this.x.add(new PathNode(s2, s3));
                }
            }
            k2.d("p");
        }
    }

    public void e() {
        PathSolver i2 = this.a.a(this.o);
        if (i2 == null) {
            throw new RuntimeException("Could not get costs for:" + this.o.toString());
        }
        this.y = i2.d;
        this.z = i2.e;
        this.A = i2.f;
        this.B = i2.g;
        this.C = i2.j;
    }

    public void f() {
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
    }

    public void a(MovementTypeEnum ao2, short s2, short s3, Float f2, boolean bl) {
        if (ao2 == null) {
            throw new RuntimeException("MovementType is null");
        }
        this.o = ao2;
        this.h = s2;
        this.i = s3;
        this.j = f2;
        this.k = bl;
        if (this.h < 0) {
            this.h = 0;
        }
        if (this.i < 0) {
            this.i = 0;
        }
        if (this.h > this.a.s - 1) {
            this.h = (short)(this.a.s - 1);
        }
        if (this.i > this.a.t - 1) {
            this.i = (short)(this.a.t - 1);
        }
        if (this.a.a(ao2) == null) {
            throw new RuntimeException("Could not get costs for:" + ao2.toString());
        }
    }

    public void a(short s2, short s3, short s4) {
        if (s2 < 0) {
            s2 = 0;
        }
        if (s3 < 0) {
            s3 = 0;
        }
        if (s2 > this.a.s - 1) {
            s2 = (short)(this.a.s - 1);
        }
        if (s3 > this.a.t - 1) {
            s3 = (short)(this.a.t - 1);
        }
        this.l = s2;
        this.m = s3;
        this.n = s4;
    }

    public boolean b() {
        return false;
    }

    public boolean a(PathCostCalculator k2) {
        return this == k2;
    }

    public com.corrodinggames.rts.gameFramework.pathfinding.h a(UnitInstance am2) {  // 02b k.k.a(am) 返回 k.c = h
        return null;
    }

    public LinkedList a() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bX.B || l2.cb.i()) {
            if (this.u) {
                return this.x;
            }
            return null;
        }
        return this.x;
    }

    protected boolean c() {
        return this.x != null;
    }

    protected void a(LinkedList linkedList) {
        this.x = linkedList;
    }

    public void g() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        Paint paint = new Paint();
        paint.a(2.0f);
        paint.a(100, 0, 100, 0);
        paint.a(225, 0, 0, 255);
        l2.bO.a(this.h * b2.tilePixelWidth + b2.selectedTileX - com.corrodinggames.rts.gameFramework.GlobalState.B().cu, (float)(this.i * b2.tilePixelHeight + b2.selectedTileY - com.corrodinggames.rts.gameFramework.GlobalState.B().cv), (float)(this.l * b2.tilePixelWidth + b2.selectedTileX - com.corrodinggames.rts.gameFramework.GlobalState.B().cu), (float)(this.m * b2.tilePixelHeight + b2.selectedTileY - com.corrodinggames.rts.gameFramework.GlobalState.B().cv), paint);
    }

    public void h() {
        if (this.x != null) {
            com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            MapEngine b2 = l2.bL;
            if (this.x.size() >= 1) {
                for (int i2 = 1; i2 < this.x.size(); ++i2) {
                    PathNode p2 = (PathNode) this.x.get(i2);
                    PathNode p3 = (PathNode) this.x.get(i2 - 1);
                    Paint paint = new Paint();
                    paint.a(255, 0, 255, 0);
                    paint.a(2.0f);
                    int n2 = p2.a * b2.tilePixelWidth + b2.selectedTileX - com.corrodinggames.rts.gameFramework.GlobalState.B().cu;
                    int n3 = p2.b * b2.tilePixelHeight + b2.selectedTileY - com.corrodinggames.rts.gameFramework.GlobalState.B().cv;
                    int n4 = p3.a * b2.tilePixelWidth + b2.selectedTileX - com.corrodinggames.rts.gameFramework.GlobalState.B().cu;
                    int n5 = p3.b * b2.tilePixelHeight + b2.selectedTileY - com.corrodinggames.rts.gameFramework.GlobalState.B().cv;
                    l2.bO.a(n2, (float)n3, (float)n4, (float)n5, paint);
                }
            }
        }
    }
}
