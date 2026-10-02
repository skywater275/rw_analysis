/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;

import com.corrodinggames.rts.gameFramework.pathfinding.UnitList;
import com.corrodinggames.rts.gameFramework.pathfinding.NodeQueue;
import com.corrodinggames.rts.gameFramework.pathfinding.AStarNode;
import com.corrodinggames.rts.gameFramework.GlobalState;

public strictfp final class NodePool {
    int a;
    int b;
    public static int c;
    final UnitList d;  // 02b k/m.java L12: a=UnitList (ByteIndexedMap 幻觉)
    final NodeQueue e = new NodeQueue();  // 02b L13: e=NodeQueue (SendWorker 幻觉)

    NodePool() {  // 02b L16: strictfp m() 为构造器 (03 误写为方法 — final d 赋值只能在构造器)
        int n2 = 1000;
        this.d = new UnitList(n2 + 100);
        for (int i = 0; i < n2; ++i) {
            this.d.a(new AStarNode());
        }
    }

    AStarNode a() {  // 02b L26: n a() (WebAPIClient 幻觉)
        if (this.d.b == 0) {
            ++c;
            return new AStarNode();
        }
        return this.d.b();
    }

    final void a(AStarNode n2) {
        if (n2 != null) {
            this.d.b(n2);
        }
    }

    void b() {
        if (this.d.size() > 50000) {
            GlobalState.e("PathOpenList: resetPool:memoryPool over 50000 clearing");
            this.d.clear();
        }
        this.e.a(this);
    }

    public void a(int n2, int n3) {
        this.b();
        this.a = n2;
        this.b = n3;
    }

    public final void a(int n2, short s2, short s3) {
        AStarNode n3 = this.a();  // 02b L58: n var4
        n3.a(s2, s3);
        n3.a(n2, this.a, this.b);
        this.e.a(n3);
    }

    public final AStarNode c() {
        AStarNode n2 = this.e.a();  // 02b L65: n var1
        if (n2 != null) {
            this.a(n2);
        }
        return n2;
    }
}
