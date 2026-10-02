/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;

import com.corrodinggames.rts.gameFramework.pathfinding.AStarNode;
import com.corrodinggames.rts.gameFramework.pathfinding.PathSolverRunner;
import com.corrodinggames.rts.gameFramework.GlobalState;

public strictfp final class PathNode {
    public short a;
    public short b;

    public PathNode() {
    }

    public PathNode(short s, short s2) {
        this.a(s, s2);
    }

    public final PathNode a(short s, short s2) {
        this.a = s;
        this.b = s2;
        return this;
    }

    public final PathNode a(PathNode p2) {
        this.a = p2.a;
        this.b = p2.b;
        return this;
    }

    public final PathNode a(AStarNode n2) {
        this.a = n2.tileX;
        this.b = n2.tileY;
        return this;
    }

    public final int a(PathSolverRunner o2) {
        short s = this.a;
        short s2 = this.b;
        if (o2.blockedLandGrid[s * o2.h + s2] == -1 || o2.blockedWaterGrid[s * o2.h + s2] == -1 || o2.blockedAirGrid[s * o2.h + s2] == -1) {
            return -1;
        }
        return o2.blockedLandGrid[s * o2.h + s2] + o2.blockedWaterGrid[s * o2.h + s2] + o2.blockedAirGrid[s * o2.h + s2] * 10;
    }

    public final int a(PathSolverRunner o2, byte by) {
        return o2.l[by][this.a * o2.h + this.b];
    }

    public final void a(PathSolverRunner o2, byte by, int n2) {
        o2.l[by][this.a * o2.h + this.b] = n2;
    }

    public final void a(PathSolverRunner o2, byte by, boolean bl) {
        if (bl) {
            byte[] byArray = o2.m[by];
            int n2 = this.a * o2.h + this.b;
            byArray[n2] = (byte)(byArray[n2] | 0x10);
        } else {
            byte[] byArray = o2.m[by];
            int n3 = this.a * o2.h + this.b;
            byArray[n3] = (byte)(byArray[n3] & 0xFFFFFFEF);
        }
    }

    public final boolean b(PathSolverRunner o2, byte by) {
        if (o2.l[by][this.a * o2.h + this.b] < o2.i) {
            return false;
        }
        return (o2.m[by][this.a * o2.h + this.b] & 0x10) != 0;
    }

    public final byte c(PathSolverRunner o2, byte by) {
        return (byte)(o2.m[by][this.a * o2.h + this.b] & 7);
    }

    public final boolean d(PathSolverRunner o2, byte by) {
        return (o2.m[by][this.a * o2.h + this.b] & 8) != 0;
    }

    public final void b(PathSolverRunner o2, byte by, boolean bl) {
        if (bl) {
            byte[] byArray = o2.m[by];
            int n2 = this.a * o2.h + this.b;
            byArray[n2] = (byte)(byArray[n2] | 8);
        } else {
            byte[] byArray = o2.m[by];
            int n3 = this.a * o2.h + this.b;
            byArray[n3] = (byte)(byArray[n3] & 0xFFFFFFF7);
        }
    }

    public final void a(PathSolverRunner o2, byte by, byte by2) {
        byte[] byArray = o2.m[by];
        int n2 = this.a * o2.h + this.b;
        byArray[n2] = (byte)(byArray[n2] & 0xFFFFFFF0);
        byte[] byArray2 = o2.m[by];
        int n3 = this.a * o2.h + this.b;
        byArray2[n3] = (byte)(byArray2[n3] | by2 & 0xF);
    }

    public final void a(PathSolverRunner o2, byte by, float f2) {
        int n2 = (int)(f2 / 360.0f * 8.0f + 0.5f);
        if (n2 < 0) {
            n2 += 8;
        }
        if (n2 > 7) {
            n2 -= 8;
        }
        if (n2 < 0) {
            n2 += 8;
        }
        if (n2 > 7) {
            n2 -= 8;
        }
        if (n2 < 0 || n2 > 7) {
            GlobalState.e("setCurrentDirectionFromAngle: dir:" + n2 + " direction:" + f2);
            n2 = 0;
        }
        this.a(o2, by, (byte)n2);
    }

    public final boolean e(PathSolverRunner o2, byte by) {
        return o2.l[by][this.a * o2.h + this.b] >= o2.i;
    }

    public final PathNode f(PathSolverRunner o2, byte by) {
        PathNode p2 = new PathNode();
        if (this.a(o2, by, p2)) {
            return p2;
        }
        return null;
    }

    public final boolean a(PathSolverRunner o2, byte by, PathNode p2) {
        if (!this.e(o2, by)) {
            p2.a((short)-1, (short)-1);
            return false;
        }
        byte by2 = this.c(o2, by);
        if (this.d(o2, by)) {
            p2.a((short)-1, (short)-1);
            return false;
        }
        int n2 = 0;
        int n3 = 0;
        if (by2 == 0) {
            ++n2;
        }
        if (by2 == 1) {
            ++n2;
            ++n3;
        }
        if (by2 == 2) {
            ++n3;
        }
        if (by2 == 3) {
            ++n3;
            --n2;
        }
        if (by2 == 4) {
            --n2;
        }
        if (by2 == 5) {
            --n2;
            --n3;
        }
        if (by2 == 6) {
            --n3;
        }
        if (by2 == 7) {
            --n3;
            ++n2;
        }
        short s2 = (short)(this.a - n2);
        short s3 = (short)(this.b - n3);
        p2.a(s2, s3);
        return true;
    }
}
