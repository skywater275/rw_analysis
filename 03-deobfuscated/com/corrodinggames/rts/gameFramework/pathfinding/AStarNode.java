/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;

import com.corrodinggames.rts.gameFramework.GameUtils;

public strictfp final class AStarNode
implements Comparable {
    public short tileX;
    public short tileY;
    public int gScore;

    public final void a(short s, short s2) {
        this.tileX = s;
        this.tileY = s2;
    }

    public final void a(int n2, int n3, int n4) {
        int n5;
        int n6 = n3 - this.tileX;
        int n7 = n4 - this.tileY;
        n6 = n6 > 0 ? n6 : -n6;
        n7 = n7 > 0 ? n7 : -n7;
        this.gScore = n5 = n2 + (n6 + n7) * 11 + GameUtils.c(n6, n7) * -7;
    }

    public final int a(AStarNode n2) {
        if (this.gScore == n2.gScore) {
            if (this.tileX - n2.tileX != 0) {
                return this.tileX - n2.tileX;
            }
            return this.tileY - n2.tileY;
        }
        return this.gScore - n2.gScore;
    }

    public String toString() {
        return "PathOpenListNode [x=" + this.tileX + ", y=" + this.tileY + ", score=" + this.gScore + "]";
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((AStarNode) object);
    }
}
