/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;
import com.corrodinggames.rts.gameFramework.ByteIndexedMap;

import com.corrodinggames.rts.gameFramework.pathfinding.UnitList;
import com.corrodinggames.rts.gameFramework.pathfinding.AStarSearch;
import com.corrodinggames.rts.gameFramework.pathfinding.NodePool;
import com.corrodinggames.rts.gameFramework.pathfinding.AStarNode;

public strictfp final class NodeQueue
extends AStarSearch {
    boolean a;
    int b;
    final UnitList c = new UnitList(100);  // 02b k/a (ByteIndexedMap 为误标, UnitList=02b k/a)
    final UnitList d = new UnitList(900);  // 02b k/a

    @Override
    public void a(AStarNode n2) {
        int n3 = n2.gScore;
        if (n3 <= this.b) {
            if (n3 == this.b) {
                this.c.b(n2);
                return;
            }
            this.c();
            this.b = n3;
            this.c.a(n2);
            return;
        }
        this.d.b(n2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AStarNode a() {
        AStarNode n2;
        block5: {
            block4: {
                AStarNode n3;
                long l = -1L;
                if (this.c.b <= 0) break block4;
                AStarNode n4 = n3 = this.c.b();
                return n4;
            }
            if (this.d.b != 0) break block5;
            this.b = Integer.MAX_VALUE;
            AStarNode n5 = null;
            return n5;
        }
        this.d();
        AStarNode n6 = n2 = this.c.b();
        return n6;
    }

    @Override
    public void b() {
        this.a((NodePool) null);
    }

    public void a(NodePool m2) {
        if (m2 != null) {
            AStarNode[] nArray = this.c.a();
            for (int i = this.c.b - 1; i >= 0; --i) {
                AStarNode n2 = nArray[i];
                m2.a(n2);
            }
            AStarNode[] nArray2 = this.d.a();
            for (int i = this.d.b - 1; i >= 0; --i) {
                AStarNode n3 = nArray2[i];
                m2.a(n3);
            }
        }
        this.c.clear();
        this.d.clear();
        this.b = Integer.MAX_VALUE;
        this.a = true;
    }

    private void c() {
        AStarNode[] nArray = this.c.a();
        int n2 = this.c.b;
        for (int i = 0; i < n2; ++i) {
            AStarNode n3 = nArray[i];
            this.d.a(n3);
        }
        this.c.clear();
    }

    private void d() {
        int n2;
        AStarNode n3;
        int n4;
        long l = -1L;
        int n5 = Integer.MAX_VALUE;
        UnitList a2 = this.d;  // 02b k/a
        AStarNode[] nArray = a2.a();
        for (n4 = a2.b - 1; n4 >= 0; --n4) {
            n3 = nArray[n4];
            n2 = n3.gScore;
            if (n2 >= n5) continue;
            n5 = n2;
        }
        for (n4 = a2.b - 1; n4 >= 0; --n4) {
            n3 = nArray[n4];
            if (n3.gScore != n5) continue;
            this.c.a(n3);
            n2 = a2.b - 1;
            nArray[n4] = nArray[n2];
            nArray[n2] = null;
            a2.b = n2;
        }
        this.b = n5;
    }
}
